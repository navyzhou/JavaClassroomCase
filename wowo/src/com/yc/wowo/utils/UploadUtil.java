package com.yc.wowo.utils;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.*;

import sun.misc.BASE64Decoder;

@SuppressWarnings("unchecked")
public class UploadUtil {
	public static String PATH="../upload";
	private static final String ALLOWED="gif,png,jpg,txt,doc,xls"; //文件上传的文件类型
	private static final String DENIDE="exe,bat,jsp,html"; //不允许上传的文件类型
	private static final int SINGLEFILESIZE=2*1024*1024; //单个文件最大大小
	private static final int TOTALMAXSIZE=20*1024*1024; //每次上传的总大小
	
	
	public Map<String,String> upload(PageContext pagecontext){
		Map<String,String> map=new HashMap<String,String>();
		
		SmartUpload upload=new SmartUpload();
	
		try {
			//初始化
			upload.initialize(pagecontext);
			upload.setDeniedFilesList(DENIDE);
			upload.setAllowedFilesList(ALLOWED);
			upload.setMaxFileSize(SINGLEFILESIZE);
			upload.setTotalMaxFileSize(TOTALMAXSIZE);
			upload.setCharset("utf-8");

			//开始上传
			upload.upload();
			
			Request request=upload.getRequest();
			
			//取出所有的普通表单元素，并将其存入到map中
			Enumeration<String> names=request.getParameterNames();
			String str;
			while(names.hasMoreElements()){
				str=names.nextElement();
				map.put(str,request.getParameter(str));
			}
			
			//获取所有要上传文件
			Files files=upload.getFiles();
			
			//说明确实有文件要上传
			if(files!=null && files.getCount()>0){
				WatermarkUtil util=new WatermarkUtil();
				String filePath;
				Collection<File> cols=files.getCollection();
				String fname=null;
				String fpath="";
				String fieldName=null;
				for(File file:cols){
					if(!file.isMissing()){ //如果上传的时候没有丢失数据
						fname=new Date().getTime()+""+new Random().nextInt(1000)+"."+file.getFileExt();
						filePath=pagecontext.getServletContext().getRealPath(PATH+"/"+fname);
						file.saveAs(filePath);
						util.mark(filePath,filePath,Color.red,"源辰信息");
						fpath+=PATH+"/"+fname+",";
						fieldName=file.getFieldName();
					}
				}
				
				if(fieldName!=null){
					if(fpath.contains(",")){
						fpath=fpath.substring(0,fpath.lastIndexOf(","));
					}
					map.put(fieldName,fpath);
				}
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 图片上传
	 * @param picData：图片数据
	 * @param path：要保存的文件路径
	 * @return:图片上传后的路径
	 */
	public String upload(PageContext pagecontext,String picData,String path){
		String realpath=null;
		
		BASE64Decoder base64=new BASE64Decoder();//解码对象
		FileOutputStream fos=null;
		try {
			byte[] buffer=base64.decodeBuffer(picData);//将图片字符串变成字节数组
			//将字节数组中图片的数据写入到一个图片文件中
			if(path==null){
				String fname=new Date().getTime()+""+new Random().nextInt(1000)+".png";
				String filePath=pagecontext.getServletContext().getRealPath(PATH+"/"+fname);
				fos=new FileOutputStream(filePath);
				realpath=PATH+"/"+fname;
			}else{
				fos=new FileOutputStream(path);
				realpath=path;
			}
			fos.write(buffer);
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
			realpath=null;
		} finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return realpath;
	}
}

package com.yc.mytomcat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 响应对象
 * @author navy
 */
public class Response {
	private OutputStream out; //输出流
	private String projectName = null;
	
	public Response(OutputStream out){
		this.out=out;
	}
	
	/**
	 * path=/a/servlet  /a/web/servlet /a  /a/   http://127.0.0.1:8080/a/web/servlet?  http://127.0.0.1:8080/a/
	 */
	public void redirect(String path){ 
		String flag=path.substring(1); //去掉最前面的/   a/servlet  a/web/servlet   a/  a  a/servlet
		String url = null;
		if(flag.contains("/") && flag.indexOf("/")!=flag.length()-1){ 
			projectName=flag.substring(0,flag.indexOf("/"));
			url=flag.substring(flag.indexOf("/")); //请求的资源地址
			
			//查看当前项目中有没有配置web.xml文件
			List<ServletMapper> servlerMapper=WebxmlObject.webObject.get(projectName);
			
			if(servlerMapper==null || servlerMapper.size()<=0){ //说明此项目没有配置servlet信息
				//将当前资源路径当成静态资源读取返回
				url=url.replace("/","\\");
				
				System.out.println(url);
				
				String fileStr=getFileToString(Config.getConfig().getProperty("path")+"\\"+projectName+url );
				if(fileStr!=null){
					sendFile(fileStr);
				}else{
					error404();
				}
			}else{ //说明有配置项
				boolean bl=false;
				for(ServletMapper mapper:servlerMapper){
					if(url.equals(mapper.getServletUrl())){ //说明要调用当前这个servlet的处理类处理
						bl=true;
						break;
					}
				}
				
				if(!bl){//如果没有找到,则当成静态资源读取返回，则处理方式跟下面的else中的处理方式一样
					
				}
			}
		}else{ //如果项目名后面没有东西，则访问该项目的web.xml中默认页面   // a  a/
			projectName=flag.replace("/","");
			Map<String,List<String>> welcomeList=WebxmlObject.welcomeList;
			if(welcomeList!=null && welcomeList.size()>0){
				//判断此项目有没有在web.xml中配置默认访问页面
				if(welcomeList.containsKey(projectName)){ //说明这个项目在web.xml中配置了默认页面
					List<String> list=welcomeList.get(projectName);
					for(String str:list){
						System.out.println(str);
					}
				}else{//说明没有在web.xml配置默认页面
					sendTomcatDefaultPage();
				}
			}else{ //说明没有在web.xml中配置默认页面
				sendTomcatDefaultPage();
			}
		}
		
	}
	
	public void sendTomcatDefaultPage(){
		//首先读取服务器配置的默认页面
		String url=Config.getConfig().getProperty("defaultPage");
		
		if(url.contains(",")){ //如果有逗号说明配置了多个默认页面
			String[] urls=url.split(",");
			String filePage=null;
			int i=0,len=0;
			for(i=0,len=urls.length;i<len;i++){
				filePage=getFileToString( Config.getConfig().getProperty("path")+"\\"+projectName+"\\"+urls[i] );
				if(filePage!=null){ //说明已经读到了
					break;
				}
			}
			if(i>=len){//说明不是从中间跳出的循环，也就是说没有找到服务器配置的默认页面
				error404();
			}else{
				sendFile(filePage);
			}
		}else{
			//到项目中查找该页面是否存在 
			String filePage=getFileToString( Config.getConfig().getProperty("path")+"\\"+projectName+"\\"+url );
			if(filePage!=null){
				sendFile(filePage);
			}else{
				error404();
			}
		}
	}
	
	/**
	 * 读取指定的文件
	 * @param path：要读取的文件文件
	 * @return：如果文件存在，则返回该文件的字符串，如果不存在返回null
	 */
	public String getFileToString(String path){
		File fl=new File(path);
		if(fl.exists()){
			FileInputStream fis=null;
			StringBuffer sbf=new StringBuffer();
			int len=-1;
			byte[] bt=new byte[1024];
			try {
				fis=new FileInputStream(fl);
				
				while( (len=fis.read(bt))!=-1 ){
					sbf.append( new String( bt,0,len ));
				}
				return sbf.toString();
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				if(fis!=null){
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}else{
			return null;
		}
		return null;
	}
	
	/**
	 * 发送页面文件的  支持的格式html css js htm
	 * @param str:要发送的文件内容
	 */
	public void sendFile(String str){
		//String msg="HTTP/1.1 200 OK\r\nContent-Type:text/html\r\nContent-Length:"+str.getBytes().length+"\r\n\r\n"+str;
		String msg="HTTP/1.1 200 OK\r\nContent-Type:text/html\r\n\r\n"+str;
		try {
			out.write(msg.getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void error404(){
		System.out.println("404...");
		try {
			String err="<h1>File Not Found<h1>";
			String message="HTTP/1.1 404 File Not Found\r\nContent-Type:text/html;charset=utf-8\r\nContent-Length:"+err.length()+"\r\n\r\n"+err;
			out.write(message.getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

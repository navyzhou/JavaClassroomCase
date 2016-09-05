package com.yc.mytomcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求对象
 * @author navy
 */
public class Request {
	private String url; //请求的资源地址
	private String method; //请求方式
	private String protocolVersion; //请求的协议版本
	//请求的参数列表
	private Map<String,String> parameter=new HashMap<String,String>();
	private InputStream is;

	public Request(InputStream is){
		this.is=is; 
		//解析这个流文件
		try {
			parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void parse() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(is));
		//一行一行的去读
		StringBuffer sbf=new StringBuffer(); 
		String line;
		//先取出第一行 GET /index.html?uname=a&pwd=a HTTP/1.1

		//while( (line=br.readLine())!=null ){
		if( (line=br.readLine())!=null ){
			if(sbf.length()==0){ //说明读的是第一行数据
				sbf.append(line);
				parseCommandLine(line);
			}else{
				sbf.append(line);
			}
			//后面的命令我没有处理了 ，对于post而言，我们必须去读取请求头中的Content-Length
		}
	}

	/**
	 * 解析请求头信息
	 * @param command: GET /index.html?uname=a&pwd=a HTTP/1.1
	 */
	private void parseCommandLine(String command){
		if(command!=null && !"".equals(command)){
			String[] strs=command.split(" ");
			this.method=strs[0];
			this.protocolVersion=strs[2];
			if("GET".equals(method)){
				doGet(strs[1]);
			}else if("POST".equals(method)){
				doPost(strs[1]);
			}else{
				throw new RuntimeException("HTTP协议错误...");
			}
		}
	}

	/**
	 * get请求的处理
	 * @param string：/index.html?uname=a&pwd=a
	 */
	private void doGet(String str) {
		//先判断请求地址中有没有附加请求数据
		if(str.contains("?")){ //说明有参数
			String params=str.substring(str.indexOf("?")+1);
			String[] param=params.split("&");
			String[] temp;
			for(String s:param){
				temp=s.split("="); //取出每个参数的键和值
				this.parameter.put(temp[0],temp[1]);
			}
			url=str.substring(0,str.indexOf("?")); //截取请求地址
		}else{
			url=str;
		}
	}

	private void doPost(String str) {

	}

	public String getUrl() {
		return url;
	}

	public String getMethod() {
		return method;
	}

	public String getProtocolVersion() {
		return protocolVersion;
	}

	public Map<String, String> getParameter() {
		return parameter;
	}
}

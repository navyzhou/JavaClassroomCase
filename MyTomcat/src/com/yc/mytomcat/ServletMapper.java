package com.yc.mytomcat;

import java.util.Map;

/**
 * 每个项目中每个web.xml中的每个servlet映射对象
 * @author navy
 */
public class ServletMapper {
	private String servletName; //servlet的名称
	private String servletClass;  //servlet的处理类的路径
	private String servletUrl; //servlet的映射路径
	private Map<String,String> initParams; //实例化参数
	
	protected void setServletName(String servletName) {
		this.servletName = servletName;
	}

	protected void setServletClass(String servletClass) {
		this.servletClass = servletClass;
	}

	protected void setServletUrl(String servletUrl) {
		this.servletUrl = servletUrl;
	}

	public String getServletName() {
		return servletName;
	}
	
	public String getServletClass() {
		return servletClass;
	}
	
	public String getServletUrl() {
		return servletUrl;
	}
	
	public Map<String, String> getInitParams() {
		return initParams;
	}

	@Override
	public String toString() {
		return "ServletMapper [servletName=" + servletName + ", servletClass=" + servletClass + ", servletUrl="
				+ servletUrl + "]";
	}
}

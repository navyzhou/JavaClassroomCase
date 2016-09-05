package com.yc.wowo.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 编码集过滤器
 * @author Administrator
 */
public class CharacterEncodingFilter implements Filter {
	private String encoding="utf-8";

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		if(arg0.getInitParameter("encoding")!=null){
			encoding=arg0.getInitParameter("encoding");
		}
	}

	@Override
	public void destroy() {
		
	}
}

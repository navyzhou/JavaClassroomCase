package com.yc.wowo.listeners;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.yc.wowo.biz.IRolesBiz;
import com.yc.wowo.biz.impl.RolesBizImpl;
import com.yc.wowo.entities.Roles;
import com.yc.wowo.utils.AttributeData;

/**
 * 应用程序加载的监听
 * @author navy
 */
public class WowoServletContextListener implements ServletContextListener{
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		arg0.getServletContext().removeAttribute(AttributeData.ALLROLES);
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//将用户角色查询出来存放到application中
		IRolesBiz roleBiz=new RolesBizImpl();
		List<Roles> roles=roleBiz.find();
		arg0.getServletContext().setAttribute(AttributeData.ALLROLES,roles);
	}
}

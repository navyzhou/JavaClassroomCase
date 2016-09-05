package com.yc.wowo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.wowo.biz.IRolesBiz;
import com.yc.wowo.biz.impl.RolesBizImpl;
import com.yc.wowo.entities.Roles;
import com.yc.wowo.utils.AttributeData;

@SuppressWarnings("unchecked")
public class RolesServlet extends BasicServlet {
	private static final long serialVersionUID = -6864909821000805350L;

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//super.doPost(request, response);
		String op=request.getParameter("op");
		
		if("findAllRoles".equals(op)){ //获取所有的角色信息
			findAllRoles(request,response);
		}else if("addRoles".equals(op)){ //添加角色
			addRoles(request,response);
		}else if("findRolesByPage".equals(op)){ //分页查询
			findRolesByPage(request,response);
		}else if("deleteRoles".equals(op)){ //删除角色信息
			deleteRoles(request,response);
		}else if("updateRoles".equals(op)){ //更新操作
			updateRoles(request,response);
		}
	}

	/**
	 * 更新角色信息
	 * @param request
	 * @param response
	 */
	private void updateRoles(HttpServletRequest request,HttpServletResponse response) {
		String rname=request.getParameter("rname");
		String mark=request.getParameter("mark");
		String rid=request.getParameter("rid");
		IRolesBiz roleBiz=new RolesBizImpl();
		
		int result=roleBiz.update(rname, mark, rid);
		if(result>0){ //如果更新成功，一定要记得修改appliction中的角色列表的值
			this.getServletContext().setAttribute(AttributeData.ALLROLES,roleBiz.find());
		}
		this.out(response,result);
	}

	/**
	 * 删除角色信息
	 * @param request
	 * @param response
	 */
	private void deleteRoles(HttpServletRequest request,HttpServletResponse response) {
		String rid=request.getParameter("rid");
		IRolesBiz roleBiz=new RolesBizImpl();
		int result=roleBiz.del(rid);
		if(result>0){ //更新application中的值
			this.getServletContext().setAttribute(AttributeData.ALLROLES,roleBiz.find());
		}
		this.out(response,result);
	}

	/**
	 * 分页查询角色信息
	 * @param request
	 * @param response
	 */
	private void findRolesByPage(HttpServletRequest request,HttpServletResponse response) {
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		IRolesBiz roleBiz=new RolesBizImpl();
		List<Roles> list=roleBiz.find(Integer.parseInt(pageNo),Integer.parseInt(pageSize));//获取分页数据
		//获取总角色数
		List<Roles> rs=(List<Roles>) this.getServletContext().getAttribute(AttributeData.ALLROLES);
		this.out(response,list,rs.size());
	}

	/**
	 * 添加角色信息
	 * @param request
	 * @param response
	 */
	private void addRoles(HttpServletRequest request,HttpServletResponse response) {
		String rname=request.getParameter("rname");
		String mark=request.getParameter("mark");
		IRolesBiz roleBiz=new RolesBizImpl();
		
		int result=roleBiz.add(rname, mark);
		if(result>0){ //如果添加成功，一定要记得修改appliction中的角色列表的值
			this.getServletContext().setAttribute(AttributeData.ALLROLES,roleBiz.find());
		}
		this.out(response,result);
	}

	/**
	 * 查询所有的角色信息
	 * @param request
	 * @param response
	 */
	private void findAllRoles(HttpServletRequest request,HttpServletResponse response) {
		Object roles=this.getServletContext().getAttribute(AttributeData.ALLROLES);
		List<Roles> list=null;
		if(roles!=null){
			list=(List<Roles>)roles;
		}else{
			IRolesBiz roleBiz=new RolesBizImpl();
			list=roleBiz.find();
			this.getServletContext().setAttribute(AttributeData.ALLROLES,list);
		}
		//将所有的角色信息返回给用户
		this.out(response,list);
	}
}

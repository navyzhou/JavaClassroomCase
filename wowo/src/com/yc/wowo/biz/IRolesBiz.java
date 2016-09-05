package com.yc.wowo.biz;

import java.util.List;

import com.yc.wowo.entities.Roles;

public interface IRolesBiz {
	/**
	 * 查找所有的角色信息
	 * @return
	 */
	public List<Roles> find();
	
	/**
	 * 分页查询
	 * @param pageNo：要查询的页数
	 * @param pageSize：每页显示的条数
	 * @return
	 */
	public List<Roles> find(Integer pageNo,Integer pageSize);
	
	/**
	 * 添加角色
	 * @param rname：角色名
	 * @param mark：角色描述
	 * @return
	 */
	public Integer add(String rname,String mark);
	
	/**
	 * 根据ID删除角色
	 * @param rid：要删除的角色ID
	 * @return
	 */
	public Integer del(String rid);
	
	/**
	 * 修改角色信息
	 * @param rname：角色名称
	 * @param mrak：描述
	 * @param rid：要修改的角色ID
	 * @return
	 */
	public Integer update(String rname,String mrak,String rid);
}

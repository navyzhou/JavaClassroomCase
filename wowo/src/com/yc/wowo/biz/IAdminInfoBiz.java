package com.yc.wowo.biz;

import java.util.List;
import java.util.Map;

import com.yc.wowo.entities.AdminInfo;

public interface IAdminInfoBiz {
	/**
	 * 用户登录
	 * @param name：管理员编号、邮箱
	 * @param pwd：密码
	 * @return：满足条件的管理员信息
	 */
	public AdminInfo login(String name,String pwd,String rid);

	/**
	 * 根据编号查询管理员信息
	 * @param aid：要查询的管理员编号
	 * @return：满足条件的管理员信息
	 */
	public AdminInfo find(Integer aid);
	
	/**
	 * 分页查询管理员信息
	 * @param pageNo：要查询的页数，此字段为null说明查询所有
	 * @param pageSize：每页显示的条数
	 * @return：满足条件的管理员信息
	 */
	public List<AdminInfo> find(Integer pageNo,Integer pageSize);
	
	/**
	 * 根据角色分页查询管理员信息
	 * @param rid：管理员角色，为null则说明查询所有角色
	 * @param pageNo：要查询的页数，此字段为null说明查询所有
	 * @param pageSize：每页显示的条数
	 * @return：满足条件的管理员信息
	 */
	public List<AdminInfo> find(Integer rid,Integer pageNo,Integer pageSize);
	
	/**
	 * 修改指定管理员的状态
	 * @param aid：要修改的管理员编号，同时修改多个时请用逗号,隔开
	 * @param status：修改后的状态
	 * @param mark：状态说明
	 * @return：成功返回一个大于0的数
	 */
	public Integer update(String aid,String status,String mark);
	
	/**
	 * 修改管理员图像
	 * @param aid：要修改图像的管理员编号
	 * @param photo：修改后的图像地址
	 * @return：成功返回一个大于0的数
	 */
	public Integer update(Integer aid,String photo);
	
	/**
	 * 修改管理员密码
	 * @param aid：要修改的管理员编号
	 * @param oldPwd：管理员的旧密码
	 * @param newPwd：管理员新密码
	 * @return：成功返回一个大于0的数
	 */
	public Integer update(Integer aid,String oldPwd,String newPwd);
	
	/**
	 * 管理员密码重置
	 * @param aid：要重置的管理员编号，多个请用逗号隔开
	 * @return：成功返回一个大于0的数
	 */
	public Integer update(String aid);
	
	/**
	 * 修改管理员信息
	 * @param aname：姓名
	 * @param rid：角色
	 * @param tel：联系方式
	 * @param photo：电话号码
	 * @param aid：要修改的管理员编号
	 * @return：成功返回一个大于0的数
	 */
	public Integer update(String aname,String rid,String tel,String photo,String aid);
	
	/**
	 * 邮箱的重新绑定
	 * @param aid：管理员编号
	 * @param email：新的邮箱地址
	 * @return：成功返回一个大于0的数
	 */
	public Integer update(String aid,String email);
	
	/**
	 * 添加管理员信息
	 * @param aname：姓名
	 * @param pwd：密码
	 * @param rid：角色
	 * @param email：注册邮箱
	 * @param tel：联系方式
	 * @param photo：管理员图像
	 * @return：成功返回一个大于0的数
	 */
	public Integer add(String aname,String pwd,String rid,String email,String tel,String photo);
	
	/**
	 * 删除管理员信息
	 * @param aid：要删除的管理员编号，多个请用逗号隔开
	 * @return：成功返回一个大于0的数
	 */
	public Integer del(String aid);
	
	/**
	 * 获取总记录数
	 * @param rid：如果为null，则获取所有，如果不为null，则根据角色编号统计
	 * @return
	 */
	public int getTotal(Integer rid);
	
	public List<AdminInfo> find(Map<String,String> parsms,Integer pageNo,Integer pageSize);

}

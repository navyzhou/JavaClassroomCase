package com.yc.wowo.biz.impl;

import java.util.List;
import java.util.Map;

import com.yc.wowo.biz.IAdminInfoBiz;
import com.yc.wowo.dao.IAdminInfoDao;
import com.yc.wowo.dao.impl.AdminInfoDaoImpl;
import com.yc.wowo.entities.AdminInfo;

public class AdminInfoBizImpl implements IAdminInfoBiz {
	@Override
	public AdminInfo login(String name, String pwd,String rid) {
		if(name==null || "".equals(name)){
			return null;
		}
		
		if(pwd==null || "".equals(pwd)){
			return null;
		}
		
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.login(name, pwd,rid);
	}

	@Override
	public AdminInfo find(Integer aid) {
		if(aid==null || "".equals(aid)){
			return null;
		}
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.find(aid);
	}

	@Override
	public List<AdminInfo> find(Integer pageNo, Integer pageSize) {
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.find(pageNo, pageSize);
	}

	@Override
	public List<AdminInfo> find(Integer rid, Integer pageNo, Integer pageSize) {
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.find(rid, pageNo, pageSize);
	}

	@Override
	public Integer update(String aid, String status, String mark) {
		if(aid==null || "".equals(aid)){
			return null;
		}
		if(status==null || "".equals(status)){
			return null;
		}
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.update(aid, status, mark);
	}

	@Override
	public Integer update(Integer aid, String photo) {
		if(aid==null || "".equals(aid)){
			return null;
		}
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.update(aid, photo);
	}

	@Override
	public Integer update(Integer aid, String oldPwd, String newPwd) {
		if(aid==null || "".equals(aid)){
			return null;
		}
		
		if(oldPwd==null || "".equals(oldPwd)){
			return null;
		}
		
		if(newPwd==null || "".equals(newPwd)){
			return null;
		}
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.update(aid, oldPwd, newPwd);
	}

	@Override
	public Integer update(String aid) {
		if(aid==null || "".equals(aid)){
			return null;
		}
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.update(aid);
	}

	@Override
	public Integer update(String aname, String rid, String tel, String photo,String aid) {
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.update(aname, rid, tel, photo, aid);
	}

	@Override
	public Integer update(String aid, String email) {
		if(aid==null || "".equals(aid)){
			return null;
		}
		if(email==null || "".equals(email)){
			return null;
		}
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.update(aid, email);
	}

	@Override
	public Integer add(String aname, String pwd, String rid, String email,String tel, String photo) {
		if(aname==null || "".equals(aname)){
			return null;
		}
		if(pwd==null || "".equals(pwd)){
			return null;
		}
		if(email==null || "".equals(email)){
			return null;
		}
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.add(aname, pwd, rid, email, tel, photo);
	}

	@Override
	public Integer del(String aid) {
		if(aid==null || "".equals(aid)){
			return null;
		}
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.del(aid);
	}

	@Override
	public int getTotal(Integer rid) {
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.getTotal(rid);
	}

	@Override
	public List<AdminInfo> find(Map<String, String> parsms, Integer pageNo, Integer pageSize) {
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.find(parsms, pageNo, pageSize);
	}

}

package com.yc.wowo.entities;

import java.io.Serializable;

/**
 * 后台管理员实体类
 * @author navy
 */
public class AdminInfo implements Serializable{
	private static final long serialVersionUID = 8832402550643598232L;
	private transient Integer aid; //管理员编号
	private String aname; //管理员姓名
	private String pwd; //密码
	private Integer rid; //角色编号
	private String email; //注册邮箱
	private String tel; //联系方式
	private String photo; //图像
	private Integer status; //账号状态  0未审核  1审核未通过  2已审核可以正常登陆 3冻结
	private String mark; //状态说明
	
	private String rname; //角色名称

	@Override
	public String toString() {
		return "AdminInfo [aid=" + aid + ", aname=" + aname + ", pwd=" + pwd
				+ ", rid=" + rid + ", email=" + email + ", tel=" + tel
				+ ", photo=" + photo + ", status=" + status + ", mark=" + mark
				+ ", rname=" + rname + "]";
	}

	public Integer getAid() {
		return aid;
	}
	
	public Integer getAids() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhoto() {
		return photo;
	}
	
	public String getPhotos() {
		if(photo==null || "".equals(photo)){
			return "images/userpic.png";
		}else{
			return photo;
		}
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getStatus() {
		return status;
	}
	
	public String getStatusStr() {
		//0未审核  1审核未通过  2已审核可以正常登陆 3冻结
		if(status==0){
			return "未审核";
		}else if(status==1){
			return "审核未通过";
		}else if(status==2){
			return "已审核";
		}else if(status==3){
			return "冻结";
		}
		return "账号异常";
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public AdminInfo(Integer aid, String aname, String pwd, Integer rid,
			String email, String tel, String photo, Integer status,
			String mark) {
		super();
		this.aid = aid;
		this.aname = aname;
		this.pwd = pwd;
		this.rid = rid;
		this.email = email;
		this.tel = tel;
		this.photo = photo;
		this.status = status;
		this.mark = mark;
	}

	public AdminInfo() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aid == null) ? 0 : aid.hashCode());
		result = prime * result + ((aname == null) ? 0 : aname.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((mark == null) ? 0 : mark.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((rid == null) ? 0 : rid.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdminInfo other = (AdminInfo) obj;
		if (aid == null) {
			if (other.aid != null)
				return false;
		} else if (!aid.equals(other.aid))
			return false;
		if (aname == null) {
			if (other.aname != null)
				return false;
		} else if (!aname.equals(other.aname))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (mark == null) {
			if (other.mark != null)
				return false;
		} else if (!mark.equals(other.mark))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (rid == null) {
			if (other.rid != null)
				return false;
		} else if (!rid.equals(other.rid))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		return true;
	}
}

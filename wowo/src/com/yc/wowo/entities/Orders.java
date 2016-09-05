package com.yc.wowo.entities;

import java.io.Serializable;

/**
 * 订单信息
 * @author navy
 */
public class Orders implements Serializable{
	private static final long serialVersionUID = 7289083458637373471L;
	private String oid; //订单编号
	private String odate; //订单日期
	private Integer usid; //用户编号
	private Integer gaid; //活动编号
	private Integer nums; //订单份数
	private Double totalprice; //总额
	private Integer status; //订单状态

	private String uname; //会员名
	private String aname; //活动名
	private String flag; //备用
	private String mark;
	
	@Override
	public String toString() {
		return "Orders [oid=" + oid + ", odate=" + odate + ", usid=" + usid
				+ ", gaid=" + gaid + ", nums=" + nums + ", totalprice="
				+ totalprice + ", status=" + status + ", uname=" + uname
				+ ", aname=" + aname + ", flag=" + flag + ", mark=" + mark
				+ "]";
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getOdate() {
		return odate;
	}

	public void setOdate(String odate) {
		this.odate = odate;
	}

	public Integer getUsid() {
		return usid;
	}

	public void setUsid(Integer usid) {
		this.usid = usid;
	}

	public Integer getGaid() {
		return gaid;
	}

	public void setGaid(Integer gaid) {
		this.gaid = gaid;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	public Double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Orders(String oid, String odate, Integer usid, Integer gaid,
			Integer nums, Double totalprice, Integer status) {
		super();
		this.oid = oid;
		this.odate = odate;
		this.usid = usid;
		this.gaid = gaid;
		this.nums = nums;
		this.totalprice = totalprice;
		this.status = status;
	}

	public Orders() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gaid == null) ? 0 : gaid.hashCode());
		result = prime * result + ((nums == null) ? 0 : nums.hashCode());
		result = prime * result + ((odate == null) ? 0 : odate.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((totalprice == null) ? 0 : totalprice.hashCode());
		result = prime * result + ((usid == null) ? 0 : usid.hashCode());
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
		Orders other = (Orders) obj;
		if (gaid == null) {
			if (other.gaid != null)
				return false;
		} else if (!gaid.equals(other.gaid))
			return false;
		if (nums == null) {
			if (other.nums != null)
				return false;
		} else if (!nums.equals(other.nums))
			return false;
		if (odate == null) {
			if (other.odate != null)
				return false;
		} else if (!odate.equals(other.odate))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (totalprice == null) {
			if (other.totalprice != null)
				return false;
		} else if (!totalprice.equals(other.totalprice))
			return false;
		if (usid == null) {
			if (other.usid != null)
				return false;
		} else if (!usid.equals(other.usid))
			return false;
		return true;
	}
}

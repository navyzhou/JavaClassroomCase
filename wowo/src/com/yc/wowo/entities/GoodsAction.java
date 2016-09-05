package com.yc.wowo.entities;

import java.io.Serializable;

/**
 * 商品活动表
 * @author navy
 */
public class GoodsAction implements Serializable{
	private static final long serialVersionUID = 8458436758445103717L;
	private Integer gaid; //类型编号
	private Integer gid; //商品编号
    private double aprice; //活动价格
    private Integer personnum; //几人餐
    private String title; //-活动标题
    private String startdate; //活动开始时间
    private String startend; //活动结束时间
    private String tishi; //温馨提示
    private String content; //活动介绍
    
    private String gname; //商品名称
    private String sname; //店铺名称
    private String aname; //店家
    private String temp;
    private String flag;
    private String mark;
    
	@Override
	public String toString() {
		return "GoodsAction [gaid=" + gaid + ", gid=" + gid + ", aprice="
				+ aprice + ", personnum=" + personnum + ", title=" + title
				+ ", startdate=" + startdate + ", startend=" + startend
				+ ", tishi=" + tishi + ", content=" + content + ", gname="
				+ gname + ", sname=" + sname + ", aname=" + aname + ", temp="
				+ temp + ", flag=" + flag + ", mark=" + mark + "]";
	}

	public Integer getGaid() {
		return gaid;
	}

	public void setGaid(Integer gaid) {
		this.gaid = gaid;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public double getAprice() {
		return aprice;
	}

	public void setAprice(double aprice) {
		this.aprice = aprice;
	}

	public Integer getPersonnum() {
		return personnum;
	}

	public void setPersonnum(Integer personnum) {
		this.personnum = personnum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getStartend() {
		return startend;
	}

	public void setStartend(String startend) {
		this.startend = startend;
	}

	public String getTishi() {
		return tishi;
	}

	public void setTishi(String tishi) {
		this.tishi = tishi;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
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

	public GoodsAction(Integer gaid, Integer gid, double aprice,
			Integer personnum, String title, String startdate, String startend,
			String tishi, String content) {
		super();
		this.gaid = gaid;
		this.gid = gid;
		this.aprice = aprice;
		this.personnum = personnum;
		this.title = title;
		this.startdate = startdate;
		this.startend = startend;
		this.tishi = tishi;
		this.content = content;
	}

	public GoodsAction() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(aprice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((gaid == null) ? 0 : gaid.hashCode());
		result = prime * result + ((gid == null) ? 0 : gid.hashCode());
		result = prime * result
				+ ((personnum == null) ? 0 : personnum.hashCode());
		result = prime * result
				+ ((startdate == null) ? 0 : startdate.hashCode());
		result = prime * result
				+ ((startend == null) ? 0 : startend.hashCode());
		result = prime * result + ((tishi == null) ? 0 : tishi.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		GoodsAction other = (GoodsAction) obj;
		if (Double.doubleToLongBits(aprice) != Double
				.doubleToLongBits(other.aprice))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (gaid == null) {
			if (other.gaid != null)
				return false;
		} else if (!gaid.equals(other.gaid))
			return false;
		if (gid == null) {
			if (other.gid != null)
				return false;
		} else if (!gid.equals(other.gid))
			return false;
		if (personnum == null) {
			if (other.personnum != null)
				return false;
		} else if (!personnum.equals(other.personnum))
			return false;
		if (startdate == null) {
			if (other.startdate != null)
				return false;
		} else if (!startdate.equals(other.startdate))
			return false;
		if (startend == null) {
			if (other.startend != null)
				return false;
		} else if (!startend.equals(other.startend))
			return false;
		if (tishi == null) {
			if (other.tishi != null)
				return false;
		} else if (!tishi.equals(other.tishi))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	} 
}

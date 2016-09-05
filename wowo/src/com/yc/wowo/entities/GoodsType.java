package com.yc.wowo.entities;

import java.io.Serializable;

/**
 * 商品类型
 * @author navy
 */
public class GoodsType implements Serializable{
	private static final long serialVersionUID = -5560919229887763887L;
	private Integer tid; //类型编号
	private String tname; //类型编号类型名称
	private String des; //类型编号类型描述
	private Integer status; //类型编号类型状态
	
	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "GoodsType [tid=" + tid + ", tname=" + tname + ", des=" + des
				+ ", status=" + status + "]";
	}

	public GoodsType(Integer tid, String tname, String des, Integer status) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.des = des;
		this.status = status;
	}

	public GoodsType() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((des == null) ? 0 : des.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tid == null) ? 0 : tid.hashCode());
		result = prime * result + ((tname == null) ? 0 : tname.hashCode());
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
		GoodsType other = (GoodsType) obj;
		if (des == null) {
			if (other.des != null)
				return false;
		} else if (!des.equals(other.des))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tid == null) {
			if (other.tid != null)
				return false;
		} else if (!tid.equals(other.tid))
			return false;
		if (tname == null) {
			if (other.tname != null)
				return false;
		} else if (!tname.equals(other.tname))
			return false;
		return true;
	}
}

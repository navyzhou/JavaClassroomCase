package com.yc.wowo.entities;

import java.io.Serializable;

/**
 * 消息
 * @author navy
 */
public class Message implements Serializable{
	private static final long serialVersionUID = 7041642056925863085L;
	private Integer mid; //类型编号
	private String title; //消息标题
	private String content; //消息内容
	private String mdate; //消息时间
	private Integer aid; //管理员编号

	private String aname; //管理员姓名

	@Override
	public String toString() {
		return "Message [mid=" + mid + ", title=" + title + ", content="
				+ content + ", mdate=" + mdate + ", aid=" + aid + ", aname="
				+ aname + "]";
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public Integer getAid() {
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

	public Message(Integer mid, String title, String content, String mdate,
			Integer aid) {
		super();
		this.mid = mid;
		this.title = title;
		this.content = content;
		this.mdate = mdate;
		this.aid = aid;
	}

	public Message() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aid == null) ? 0 : aid.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((mdate == null) ? 0 : mdate.hashCode());
		result = prime * result + ((mid == null) ? 0 : mid.hashCode());
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
		Message other = (Message) obj;
		if (aid == null) {
			if (other.aid != null)
				return false;
		} else if (!aid.equals(other.aid))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (mdate == null) {
			if (other.mdate != null)
				return false;
		} else if (!mdate.equals(other.mdate))
			return false;
		if (mid == null) {
			if (other.mid != null)
				return false;
		} else if (!mid.equals(other.mid))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}

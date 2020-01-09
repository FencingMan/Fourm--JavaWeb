package com.web.bean;

public class Point {
	private Integer userId;
	private String time;
	private Integer pnum;
	private String ptype;
	private Integer aid;
	
	
	
	public Point(Integer userId, Integer pnum, String ptype, Integer aid) {
		super();
		this.userId = userId;
		this.pnum = pnum;
		this.ptype = ptype;
		this.aid = aid;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getPnum() {
		return pnum;
	}
	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	@Override
	public String toString() {
		return "Point [userId=" + userId + ", time=" + time + ", pnum=" + pnum
				+ ", ptype=" + ptype + "]";
	}
	public Point(Integer userId, String time, Integer pnum, String ptype) {
		super();
		this.userId = userId;
		this.time = time;
		this.pnum = pnum;
		this.ptype = ptype;
	}
	
	
	public Point(Integer userId, String time, Integer pnum, String ptype,
			Integer aid) {
		super();
		this.userId = userId;
		this.time = time;
		this.pnum = pnum;
		this.ptype = ptype;
		this.aid = aid;
	}
	public Point() {
	}
	
	
}

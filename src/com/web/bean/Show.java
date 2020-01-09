package com.web.bean;
/*
 * Article和Users表的整合
 */


public class Show {

	// 用户名
	private String uname;
	// 发布时间
	private String publishTime;
	// 浏览数
	private Integer browseNum;
	// 标题
	private String title;
	// 用户头像
	private String photoName;
	// 文章id
	private Integer aid;
	
	private Integer commentnum;
	
	
	
	public Integer getCommentnum() {
		return commentnum;
	}

	public void setCommentnum(Integer commentnum) {
		this.commentnum = commentnum;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Show() {

	}

	public Show(String uname) {
		super();
		this.uname = uname;
	}

	
	
	

	public Show(String uname, String publishTime, Integer browseNum,
			String title, String photoName, Integer aid) {
		super();
		this.uname = uname;
		this.publishTime = publishTime;
		this.browseNum = browseNum;
		this.title = title;
		this.photoName = photoName;
		this.aid = aid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getBrowseNum() {
		return browseNum;
	}

	public void setBrowseNum(Integer browseNum) {
		this.browseNum = browseNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	@Override
	public String toString() {
		return "Show [uname=" + uname + ", publishTime=" + publishTime
				+ ", browseNum=" + browseNum + ", title=" + title
				+ ", photoName=" + photoName + "]";
	}

}

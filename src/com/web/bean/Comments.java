package com.web.bean;

import java.util.List;

/**
 * 评论表
 * 
 * @author 117
 * 
 */
public class Comments {

	// 每条评论的编号
	private Integer cid;
	// 文章的id
	private Integer aid;
	// 用户id
	private Integer userId;
	// 用户名（昵称)
	private String uname;
	// 评论内容
	private String c_content;
	// 评论时间，默认为系统时间
	private String commentTime;
	// 回复上级编号（0表示默认回复帖子）
	private Integer superid;
	// 级别
	private Integer clevel;
	
	
	public Comments() {

	}

	public Comments(Integer cid) {
		super();
		this.cid = cid;
	}


	public Comments(Integer cid, Integer aid, Integer userId, String uname,
			String c_content, String commentTime, Integer superid,
			Integer clevel) {
		super();
		this.cid = cid;
		this.aid = aid;
		this.userId = userId;
		this.uname = uname;
		this.c_content = c_content;
		this.commentTime = commentTime;
		this.superid = superid;
		this.clevel = clevel;
		
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
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

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getC_content() {
		return c_content;
	}

	public void setC_content(String c_content) {
		this.c_content = c_content;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public Integer getSuperid() {
		return superid;
	}

	public void setSuperid(Integer superid) {
		this.superid = superid;
	}

	public Integer getClevel() {
		return clevel;
	}

	public void setClevel(Integer clevel) {
		this.clevel = clevel;
	}

	@Override
	public String toString() {
		return "Comments [cid=" + cid + ", aid=" + aid + ", userId=" + userId
				+ ", uname=" + uname + ", c_content=" + c_content
				+ ", commentTime=" + commentTime + ", superid=" + superid
				+ ", clevel=" + clevel + "]";
	}

}

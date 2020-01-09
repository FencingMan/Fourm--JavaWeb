package com.web.bean;

/*
 * 文章表
 */
public class Article {

	// 文章的id
	private Integer aid;
	// 作者的id
	private Integer userId;
	// 标题
	private String title;
	// 内容
	private String content;
	// 发布时间，默认为系统时间
	private String publishTime;
	// 最终修改时间，默认为系统时间
	private String finalAlterTime;
	// 浏览数
	private Integer browseNum;
	// 点赞数
	private Integer likeNum;
	// 收藏数
	private Integer collectNum;

	public Article() {

	}

	public Article(Integer aid) {
		super();
		this.aid = aid;
	}

	public Article(Integer aid, Integer userId, String title, String content,
			String publishTime, String finalAlterTime, Integer browseNum,
			Integer likeNum, Integer collectNum) {
		super();
		this.aid = aid;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.publishTime = publishTime;
		this.finalAlterTime = finalAlterTime;
		this.browseNum = browseNum;
		this.likeNum = likeNum;
		this.collectNum = collectNum;
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

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getFinalAlterTime() {
		return finalAlterTime;
	}

	public void setFinalAlterTime(String finalAlterTime) {
		this.finalAlterTime = finalAlterTime;
	}

	public Integer getBrowseNum() {
		return browseNum;
	}

	public void setBrowseNum(Integer browseNum) {
		this.browseNum = browseNum;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	@Override
	public String toString() {
		return "Article [aid=" + aid + ", userId=" + userId + ", title="
				+ title + ", content=" + content + ", publishTime="
				+ publishTime + ", finalAlterTime=" + finalAlterTime
				+ ", browseNum=" + browseNum + ", likeNum=" + likeNum
				+ ", collectNum=" + collectNum + "]";
	}

}

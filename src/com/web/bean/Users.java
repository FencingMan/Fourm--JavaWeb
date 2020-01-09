package com.web.bean;

import java.sql.Date;


/*
 * 用户表
 */
public class Users {

	// 用户id
	private Integer userId;
	// 用户名（昵称）
	private String uname;
	// 邮箱
	private String email;
	// 密码
	private String password;
	// 性别
	private String sex;
	// 出生年月日
	private Date birth;
	// 头像文件名
	private String photoName;
	// 积分
	private Integer point;
	
	private String lastLoginTime;
	
	

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Users() {

	}

	public Users(Integer userId) {
		this.userId = userId;
	}

	public Users(Integer userId, String uname, String email, String password,
			String sex, Date birth, String photoName, Integer point) {
		super();
		this.userId = userId;
		this.uname = uname;
		this.email = email;
		this.password = password;
		this.sex = sex;
		this.birth = birth;
		this.photoName = photoName;
		this.point = point;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", uname=" + uname + ", email="
				+ email + ", password=" + password + ", sex=" + sex
				+ ", birth=" + birth + ", photoName=" + photoName + ", point="
				+ point + "]";
	}

}

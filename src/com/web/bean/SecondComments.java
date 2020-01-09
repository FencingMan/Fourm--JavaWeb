package com.web.bean;

/*
 * 二级评论实体类
 */
public class SecondComments {

	//用户表
	private Users users = new Users();
	//评论表
	private Comments comments = new Comments();

	public SecondComments() {
		
	}

	public SecondComments(Users users, Comments comments) {
		super();
		this.users = users;
		this.comments = comments;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Comments getComments() {
		return comments;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "SecondComments [users=" + users + ", comments=" + comments
				+ "]";
	}

}

package com.web.bean;

public class FirstComments {

	private Users u = new Users();

	private Comments c = new Comments();

	public FirstComments() {
		
	}

	public FirstComments(Users u, Comments c) {
		super();
		this.u = u;
		this.c = c;
	}

	public Users getU() {
		return u;
	}

	public void setU(Users u) {
		this.u = u;
	}

	public Comments getC() {
		return c;
	}

	public void setC(Comments c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "FirstComments [u=" + u + ", c=" + c + "]";
	}

}

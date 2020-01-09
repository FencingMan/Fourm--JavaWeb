package com.web.test;

import java.sql.Date;
import java.sql.Timestamp;

import com.web.bean.Users;
import com.web.dao.UsersDAO;

public class Test {
	public static void main(String[] args) {
		UsersDAO dao = new UsersDAO();
		Users u = new Users();
		u.setUserId(1024);
		u = dao.get(u, "userId");
		String date = u.getLastLoginTime();
		// 用户为首次登录此论坛
		if (date == null) {
			dao.updateTime("update users set lastlogintime=sysdate where userid="
					+ u.getUserId());
			u = dao.get(u, "userId");
			dao.updatePoint(u, 50);
			System.out.println("首次登录论坛。。。+50积分");
		} else {
			//非首次登录论坛
			if (!date.substring(0, 10).equals(
					new Timestamp(System.currentTimeMillis()).toString()
							.substring(0, 10))){
				dao.updatePoint(u, 20);
				System.out.println("今日首次登录论坛 。。积分+20");
			}
			dao.updateTime("update users set lastlogintime=sysdate where userid="
					+ u.getUserId());
				
		}
	}
}

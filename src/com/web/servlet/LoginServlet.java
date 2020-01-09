package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.bean.Point;
import com.web.bean.Users;
import com.web.dao.PointDAO;
import com.web.dao.UsersDAO;

/**
 * 页面异步登录请求判断
 * 
 * @author 潘声文+杨潇
 * 
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		HttpSession hs = req.getSession();
		List<Integer> flag = new ArrayList<Integer>();
		flag.clear();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String inputcheckcode = req.getParameter("checkcode");
		String checkcode = (String) hs.getAttribute("code");
		if ("".equals(username)) {
			flag.add(1);
		}
		if ("".equals(password)) {
			flag.add(2);
		}
		if ("".equals(checkcode)) {
			flag.add(3);
		}

		if (!checkcode.equals(inputcheckcode)) {
			flag.add(4);
		}
		
		UsersDAO dao = new UsersDAO();
		
		Users u = new Users();
		if (flag.size() == 0) {
			//非邮箱登录（用户名登录）
			if(username.indexOf('@')==-1){
				u.setUname(username);
				u = dao.get(u, "uname");
				if(u!=null&&u.getPassword().equals(password)){
					updatePointAndTime(u,dao,hs);//YX 2019/10/10 17:46
					u = dao.get(u, "uname");
					hs.setAttribute("user", u);
					System.out.println("用户名登录");
				}else{
					System.out.println("登录失败");
					flag.add(6);
				}
			}else{
				//邮箱登录
				u.setEmail(username);
				u=dao.get(u, "email");
				if(u!=null&&u.getPassword().equals(password)){
					updatePointAndTime(u,dao,hs);//YX 2019/10/10 17:46
					u = dao.get(u, "uname");
					hs.setAttribute("user", u);
				}else{
					flag.add(6);
				}
			}
		}
		out.print(flag);

	}
	
	//更新用户登录时间并且更新积分
	public static void updatePointAndTime(Users u,UsersDAO dao,HttpSession hs){

		PointDAO pdao = new PointDAO();
		
		String date = u.getLastLoginTime();
		// 用户为首次登录此论坛
		if (date == null) {
			dao.updateTime("update users set lastlogintime=sysdate where userid="
					+ u.getUserId());
			hs.setAttribute("isFirstLogin", 1);
			pdao.save2(new Point(u.getUserId(),50,"首次登录赠送",0));
			u = dao.get(u, "userId");
			dao.updatePoint(u, 50);
		} else {
			//非首次登录论坛
			if (!date.substring(0, 10).equals(
					new Timestamp(System.currentTimeMillis()).toString()
							.substring(0, 10))){
				dao.updatePoint(u, 20);
				hs.setAttribute("isFirstLogin", 2);
				pdao.save2(new Point(u.getUserId(),20,"每日登录赠送",0));
				System.out.println("今日首次登录论坛 。。积分+20");
			}
			dao.updateTime("update users set lastlogintime=sysdate where userid="
					+ u.getUserId());
				
		}
	}
	
}

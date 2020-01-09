package com.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.bean.Users;
import com.web.dao.UsersDAO;
import com.web.server.LikeServer;
@WebServlet("/like")
public class LikeServlet extends HttpServlet{
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
			req.setCharacterEncoding("utf-8");
			HttpSession session = req.getSession();
			Users user = (Users)session.getAttribute("user");
			UsersDAO dao = new UsersDAO();
			int  like =Integer.parseInt(req.getParameter("like"));
			int userid = Integer.parseInt(req.getParameter("userid"));
			int aid = Integer.parseInt(req.getParameter("aid"));
			
			if(like==0){
				LikeServer L = new LikeServer();
				L.delLike(aid, userid);
				
			}
			if(like==1){
				LikeServer c = new LikeServer();
				c.addLike(aid, userid);
				user = dao.get(user, "userId");
				session.setAttribute("user", user);
			}
		}
}

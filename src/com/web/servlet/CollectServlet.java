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
import com.web.server.CollectServer;
@WebServlet("/collect")
public class CollectServlet extends HttpServlet{
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			
			req.setCharacterEncoding("utf-8");
			HttpSession session = req.getSession();
			Users user = (Users)session.getAttribute("user");
			UsersDAO dao = new UsersDAO();
			int  collect =Integer.parseInt(req.getParameter("collect"));
			int userid = Integer.parseInt(req.getParameter("userid"));
			int aid = Integer.parseInt(req.getParameter("aid"));
			
			if(collect==0){
				CollectServer c = new CollectServer();
				c.delCollect(aid, userid);
				
			}
			if(collect==1){
				CollectServer c = new CollectServer();
				c.addCollect(aid, userid);
				user = dao.get(user, "userId");
				session.setAttribute("user", user);
			}
			
		}
}

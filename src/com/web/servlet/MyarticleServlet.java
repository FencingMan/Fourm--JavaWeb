package com.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.net.aso.g;

import com.web.bean.Article;
import com.web.bean.Users;
import com.web.server.MyartilceServer;
@WebServlet("/myarticle")
public class MyarticleServlet extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			int type = Integer.parseInt( req.getParameter("type"));
			Integer aid =Integer.parseInt(req.getParameter("aid"));
			HttpSession hs = req.getSession();
			Users user = (Users) hs.getAttribute("user");
			if(type==1){
				MyartilceServer.delArticle(aid, user.getUserId());
				 resp.sendRedirect("showmyarticle");
			}
			if(type==2){
				MyartilceServer.delCollectArticle(aid, user.getUserId());
				resp.sendRedirect("showmycollect");
			}
		}
}

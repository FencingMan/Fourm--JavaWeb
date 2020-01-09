package com.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.bean.Article;
import com.web.bean.Users;
import com.web.server.MyartilceServer;
@WebServlet("/showmyarticle")
public class showMyarticleServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			HttpSession hs = req.getSession();
			Users user = (Users) hs.getAttribute("user");
			List<Article> articles = MyartilceServer.getArticles(user.getUserId());
			req.setAttribute("myarticles", articles);
			req.getRequestDispatcher("myarticle.jsp").forward(req, resp);
	}
}

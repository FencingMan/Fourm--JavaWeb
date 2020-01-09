package com.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.bean.Article;
import com.web.server.MyartilceServer;

@WebServlet("/showotherarticlelist")
public class ShowOtherArticleListServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer uid = Integer.parseInt(req.getParameter("uid"));
		List<Article> articles = MyartilceServer.getArticles(uid);
		System.out.println(articles);
		req.setAttribute("articles", articles);
		req.getRequestDispatcher("otherarticlelist.jsp").forward(req, resp);
	}
}

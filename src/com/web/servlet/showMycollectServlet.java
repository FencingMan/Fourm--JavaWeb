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
@WebServlet("/showmycollect")
public class showMycollectServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			HttpSession hs = req.getSession();
			Users user = (Users) hs.getAttribute("user");
			List<Article> collects = MyartilceServer.getCollectArticles(user.getUserId());
			System.out.println(collects);
			req.setAttribute("collects", collects);
			req.getRequestDispatcher("mycollect.jsp").forward(req, resp);
	}
}

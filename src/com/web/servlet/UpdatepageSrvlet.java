package com.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.bean.Article;
import com.web.dao.ArticleDAO;
@WebServlet("/updatepage")
public class UpdatepageSrvlet extends HttpServlet{

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			Integer aid =  Integer.parseInt(req.getParameter("aid"));
			ArticleDAO dao = new ArticleDAO();
			Article article  = dao.get(new Article(aid), "aid");
			req.setAttribute("article", article);
			req.getRequestDispatcher("article_update.jsp").forward(req, resp);
		}
}

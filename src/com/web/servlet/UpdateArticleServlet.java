package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.bean.Article;
import com.web.dao.ArticleDAO;
@WebServlet("/updateArticle")
public class UpdateArticleServlet extends HttpServlet{

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			Integer aid = Integer.parseInt(req.getParameter("aid"));
			ArticleDAO dao = new ArticleDAO();
			Article article = new Article();
			article.setAid(aid);
			article.setTitle(title);
			article.setContent(content);
			dao.update2(article, "aid");
			resp.sendRedirect("showmyarticle");
		}
}

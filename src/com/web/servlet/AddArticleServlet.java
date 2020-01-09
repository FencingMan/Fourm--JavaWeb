package com.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.bean.Article;
import com.web.bean.Point;
import com.web.bean.Users;
import com.web.dao.ArticleDAO;
import com.web.dao.PointDAO;
import com.web.dao.UsersDAO;
/**
 * 提交新发布的帖子
 * @author 杨潇
 *
 */
@WebServlet("/addArticle")
public class AddArticleServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		HttpSession session = req.getSession();
		Users user = (Users)session.getAttribute("user");
		Integer id = user.getUserId();
		ArticleDAO dao = new ArticleDAO();
		Article a = new Article();
		Integer aid = dao.queryForInt("select article_seq.nextval from dual");
		a.setAid(aid);
		a.setTitle(title);
		a.setUserId(id);
		a.setContent(content);
		dao.save2(a);
		PointDAO pdao = new PointDAO();
		pdao.save2(new Point(user.getUserId(),30,"发表文章",aid));
		UsersDAO udao = new UsersDAO();
		udao.updatePoint(user.getUserId(), 30);
		user = udao.get(user, "userId");
		session.setAttribute("user", user);
		resp.sendRedirect("showArticle?aid="+aid);
	}	
}

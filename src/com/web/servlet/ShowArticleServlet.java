package com.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.bean.Article;
import com.web.bean.Comments;
import com.web.bean.Point;
import com.web.bean.Users;
import com.web.dao.ArticleDAO;
import com.web.dao.CommentsDAO;
import com.web.dao.PointDAO;
import com.web.dao.UsersDAO;
import com.web.server.BrowseNumServer;
import com.web.server.CollectServer;
import com.web.server.LikeServer;
/**
 * 单篇文章的内容显示，
 * @author 杨潇
 *
 */
@WebServlet("/showArticle")
public class ShowArticleServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Integer aid = Integer.valueOf(req.getParameter("aid"));
		//获得文章信息
		ArticleDAO dao = new ArticleDAO();
		Article a = new Article();
		a.setAid(aid);
		a = dao.get(a, "aid");
		Integer BrowseNum =Integer.valueOf(a.getBrowseNum()+1);
		
		BrowseNumServer.addBrowseNum(aid, BrowseNum);
		
		//获得文章的作者信息
		Integer userId = a.getUserId();
		UsersDAO udao = new UsersDAO();
		Users u = new Users();
		u.setUserId(userId);
		u = udao.get(u, "userId");
		
		//获得登录用户信息
		HttpSession hs = req.getSession();
		Users loginUser = (Users) hs.getAttribute("user"); 
		
		//获得文章收藏数
		CollectServer sc = new CollectServer();
		Integer c = sc.getCollects(aid);
		
		//获得文章点赞数
		LikeServer ls  = new LikeServer();
		Integer l = ls.getLikes(aid);
		
		//查询登录用户是否收藏点赞该帖子和未登录 的收藏点赞设置
		boolean collect =false;
		boolean like =false;
		//userid测试  
		if(loginUser!=null){
			if(sc.getCollect(aid, loginUser.getUserId())!=0){
				collect =true;
			}
			if(ls.getLike(aid, loginUser.getUserId())!=0){
				like =true;
			}
		}

		PointDAO pdao = new PointDAO();
		
		if(loginUser==null){
			resp.sendRedirect("login.jsp");
		}else if(loginUser.getUserId().equals(a.getUserId())){
			req.setAttribute("zhutishu", dao.queryForInt("select count(*) from article where userid="+userId));
			req.setAttribute("article", a);
			req.setAttribute("user", u);
			req.setAttribute("collects", c);
			req.setAttribute("likes", l);
			req.setAttribute("collect", collect);
			req.setAttribute("like", like);
			req.getRequestDispatcher("comments?aid="+aid).forward(req, resp);
			return;
		}else if(pdao.query(aid, loginUser.getUserId())!=null){
			//设置反馈数据
			req.setAttribute("zhutishu", dao.queryForInt("select count(*) from article where userid="+userId));
			req.setAttribute("article", a);
			req.setAttribute("user", u);
			req.setAttribute("collects", c);
			req.setAttribute("likes", l);
			req.setAttribute("collect", collect);
			req.setAttribute("like", like);
			req.getRequestDispatcher("comments?aid="+aid).forward(req, resp);
			return;
		}else if(loginUser.getPoint()<20){
			resp.sendRedirect("warning.jsp");
		}else{
			
			pdao.save2(new Point(loginUser.getUserId(),-20,"查看帖子",aid));
			udao.updatePoint(loginUser.getUserId(), -20);
			loginUser = udao.get(loginUser, "userId");
				hs.setAttribute("user", loginUser);
			//设置反馈数据
			req.setAttribute("zhutishu", dao.queryForInt("select count(*) from article where userid="+userId));
			req.setAttribute("article", a);
			req.setAttribute("user", u);
			req.setAttribute("collects", c);
			req.setAttribute("likes", l);
			req.setAttribute("collect", collect);
			req.setAttribute("like", like);
			req.getRequestDispatcher("comments?aid="+aid).forward(req, resp);
		}
	}
}

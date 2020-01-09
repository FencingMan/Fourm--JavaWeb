package com.web.servlet;

import java.awt.SplashScreen;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.bean.Comments;
import com.web.bean.FirstComments;
import com.web.bean.Point;
import com.web.bean.Users;
import com.web.dao.CommentsDAO;
import com.web.dao.PointDAO;
import com.web.dao.UsersDAO;
import com.web.util.RowMapper;

/*
 * 二级评论
 */
@WebServlet("/second")
public class SecondCommentsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取每条评论编号
		String cid = req.getParameter("cid");
		//获取上级编号
		String superid = req.getParameter("superid");
		//获取回复内容
		String reply = req.getParameter("reply");
		//获取被评论人的名字
		String uname = new String(req.getParameter("beipinglunren").getBytes("iso-8859-1"),"utf-8");
		//获取文章ID
		String aid=req.getParameter("aid");
		// 设置字符编码
		String rtxt = new String(reply.getBytes("iso-8859-1"), "UTF-8");
		//获取session
		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute("user");
		//System.out.println(cid+" "+superid+" "+rtxt);
		//实例化评论表
		Comments c=new Comments();
		CommentsDAO dao=new CommentsDAO();
		c.setCid(dao.queryForInt("select seq_comments_cid.nextval from dual"));
		c.setC_content(rtxt);
		c.setAid(Integer.parseInt(aid));
		c.setSuperid(Integer.parseInt(cid));
		c.setClevel(2);
		//设置被评论人的名字
		c.setUname(uname);
		
		//登录用户id
		c.setUserId(user.getUserId());
		//保存到数据库
		int save2 = dao.save2(c);
		PointDAO pdao = new PointDAO();
		pdao.save2(new Point(user.getUserId(),10,"发表评论",Integer.parseInt(aid)));
		UsersDAO udao = new UsersDAO();
		udao.updatePoint(user.getUserId(), 10);
		user = udao.get(user, "userId");
		session.setAttribute("user", user);
		//重定向
		resp.sendRedirect("showArticle?aid="+aid);
		
	}
	
	
}

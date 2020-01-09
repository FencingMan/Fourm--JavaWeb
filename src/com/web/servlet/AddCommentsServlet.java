package com.web.servlet;
/**
 * 保存评论
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.bean.Comments;
import com.web.bean.Point;
import com.web.bean.Users;
import com.web.dao.CommentsDAO;
import com.web.dao.PointDAO;
import com.web.dao.UsersDAO;

@WebServlet("/first")
public class AddCommentsServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取输入框的内容
		String txt=req.getParameter("txt");
		//获取帖子编号
		String aid=req.getParameter("aid");
		//获取session
		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute("user");
		//设置字符编码
		String stxt=new String(txt);
		//实例化评论表
		Comments c=new Comments();
		CommentsDAO dao=new CommentsDAO();
		//获取cid递增序列
		int cid=dao.queryForInt("select seq_comments_cid.nextval from dual");
		c.setCid(cid);
		c.setC_content(stxt);
		c.setAid(Integer.parseInt(aid));
		c.setSuperid(0);
		c.setClevel(1);
		c.setUname(user.getUname());
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

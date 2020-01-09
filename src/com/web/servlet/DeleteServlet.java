package com.web.servlet;
/*
 * 删除评论
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.bean.Comments;
import com.web.bean.Users;
import com.web.dao.CommentsDAO;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String cid=req.getParameter("cid");
		String aid=req.getParameter("aid");
		CommentsDAO dao=new CommentsDAO();
		Comments com = new Comments();
		com.setCid(Integer.parseInt(cid));
		com = dao.get(com, "cid");
		//评论人id
		int userid = com.getUserId();
		HttpSession session = req.getSession();
		Users u = (Users)session.getAttribute("user");
		if(u!=null){
			if(u.getUserId()==userid){
				dao.delete(new Comments(Integer.parseInt(cid)), "cid");				
				resp.sendRedirect("showArticle?aid="+aid);
			}else{
				resp.sendRedirect("showArticle?aid="+aid);
			}
		}else{
			resp.sendRedirect("showArticle?aid="+aid);
		}
		//Comments c = dao.get(new Comments(Integer.parseInt(cid)), "cid");
		
		
		
	}

}

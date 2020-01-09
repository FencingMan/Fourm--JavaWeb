package com.web.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.bean.Collectlike;
import com.web.bean.Users;
import com.web.dao.CollectlikeDAO;
import com.web.dao.UsersDAO;
import com.web.server.SearchNameServer;

@WebServlet("/message")
public class MessageServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession se = req.getSession();
		Users user = (Users) se.getAttribute("user");
		
		//从session中获取新消息集合li2
		List<Integer> li2 = (List) se.getAttribute("li2");
		Collectlike colike = new Collectlike();
		CollectlikeDAO dao = new CollectlikeDAO();
		//遍历新消息集合li2
		for(Integer l:li2){
			//l为消息表id
			colike.setClid(l);
			//将新消息未读状态1改为已读状态0
			colike.setStatus(0);
			//数据库中同时更新
			dao.update2(colike, "clid");
		}
		
		//查询收藏点赞表
		CollectlikeDAO cdao = new CollectlikeDAO();
		List<Collectlike> clist = cdao.list(Collectlike.class);
		Collections.sort(clist, new Comparator<Collectlike>() {

			@Override
			public int compare(Collectlike o1, Collectlike o2) {
				
				return o2.getClid()-o1.getClid();
			}
		});
		req.setAttribute("clist", clist);
		
		//转发
		req.getRequestDispatcher("msg.jsp").forward(req, resp);
	}

}

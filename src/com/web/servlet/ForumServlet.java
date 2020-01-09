package com.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.bean.Collectlike;
import com.web.bean.Show;
import com.web.bean.Users;
import com.web.dao.CollectlikeDAO;
import com.web.server.SearchIdServer;
import com.web.server.ShowServer;

/**
 * 论坛首页初始化
 * @author 何志彤
 *
 */
@WebServlet("/showIndex")
public class ForumServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//实例化ShowServer
		ShowServer ss=new ShowServer();
		//将ShowServer中所有的值存放在showList中
		List<Show> showList = ss.getShow();
		HttpSession session = req.getSession();
		//获取session中的登录用户
		Users s = (Users)session.getAttribute("user");
		
		//存储登录用户收到的所有消息
		List<Integer> li = new ArrayList<Integer>();
		//存储登录用户收到的新消息
		List<Integer> li2 = new ArrayList<Integer>();
		
		//用户登录状态下
		if(s!=null){
			SearchIdServer search = new SearchIdServer();
			CollectlikeDAO dao = new CollectlikeDAO();
			//查询消息表中所有的数据
			List<Collectlike> list = dao.list(Collectlike.class);
			for(Collectlike cl : list){
				//获取发帖人的userid
				int clUid = search.getUidByAid(cl.getAid());
				//发帖人id和登录用户id相同时，把消息添加到集合li中
				if (clUid==s.getUserId()) {
					li.add(cl.getClid());
				}
			}
			Collectlike colike = new Collectlike();
			//遍历收到的所有消息
			for(Integer l:li){
				colike.setClid(l);
				colike = dao.get(colike, "clid");
				//当消息状态是未读状态时，把消息添加到新消息集合li2中
				if(colike.getStatus()==1){
					li2.add(l);				
				}
			}
		}
		
		session.setAttribute("lilen", li2.size());
		session.setAttribute("li2", li2);
		//绑定
		req.setAttribute("list", showList);
		//转发
		req.getRequestDispatcher("browseRank").forward(req, resp);
		
	}
}

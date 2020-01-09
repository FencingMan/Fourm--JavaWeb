package com.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.bean.Show;
import com.web.server.SeachServer;
@WebServlet("/seach")
public class SeachServlet extends HttpServlet{
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			int type=Integer.parseInt(req.getParameter("type"));
			String seach = req.getParameter("seach");
			if(type==1){
			  List<Show> result =  SeachServer.seachTitle(seach);
			  req.setAttribute("result", result);
			}
			if(type==2){
				List<Show> result =  SeachServer.seachUname(seach);
				req.setAttribute("result", result);
			}
			req.getRequestDispatcher("seach.jsp").forward(req, resp);
		}
}

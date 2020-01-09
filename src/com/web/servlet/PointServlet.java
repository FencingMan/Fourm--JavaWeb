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

import com.web.bean.Point;
import com.web.bean.Users;
import com.web.dao.PointDAO;
@WebServlet("/point")
public class PointServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Users u = (Users)session.getAttribute("user");
		PointDAO dao = new PointDAO();
		List<Point> list = dao.list(Point.class);
		List<Point> myPoint = new ArrayList<Point>();
		System.out.println(u.getUserId());
		for(Point p : list){
			if(p.getUserId().equals(u.getUserId())){
				myPoint.add(p);
			}

		}
		req.setAttribute("dingdanhao", System.currentTimeMillis());
		System.out.println(myPoint.size());
		req.setAttribute("myPoint", myPoint);
		req.getRequestDispatcher("MyPoint.jsp").forward(req, resp);
	}
}

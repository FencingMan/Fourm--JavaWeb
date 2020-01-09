package com.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 退出账户，清除session
 * @author 潘声文
 *
 */
@WebServlet("/exit")
public class ExitServlet extends HttpServlet{
	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			HttpSession hs = req.getSession();
			hs.removeAttribute("user");
			resp.sendRedirect("showIndex");
		}
}

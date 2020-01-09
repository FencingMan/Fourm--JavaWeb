package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.web.bean.Users;
import com.web.dao.UsersDAO;

/**
 * 此servlet用于用户的密码修改
 * @author Administrator
 *
 */
@WebServlet("/alterPsw")
public class AlterPasswordServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//json对象
		JSONObject root = new JSONObject();
		
		HttpSession session = req.getSession();
		Users user = (Users)session.getAttribute("user");
		//当前登录用户密码
		String password = user.getPassword();
		//输入框中的旧密码
		String psw = req.getParameter("psw");
		//新的密码
		String newPsw1 = req.getParameter("newpsw1");
		String newPsw2 = req.getParameter("newpsw2");
		root.put("flag", 0);
		//1表示旧密码不正确
		if("".equals(psw)||"".equals(newPsw1)||"".equals(newPsw2)){
			root.put("flag", 2);
			//密码框为空
		}else if(!psw.equals(password)){
			root.put("flag", 1);
			
		}else if(!newPsw1.equals(newPsw2)){
			root.put("flag", 3);
			//两次输入的值不相等
		}else{
			UsersDAO dao = new UsersDAO();
			Users u = new Users();
			u.setUserId(user.getUserId());
			u.setPassword(newPsw2);
			dao.update2(u, "userId");
			u = dao.get(u, "userId");
			session.setAttribute("user", u);
		}
		
		PrintWriter out = resp.getWriter();
		out.print(root.toString()); 
		
		
		
		
		
	}
}

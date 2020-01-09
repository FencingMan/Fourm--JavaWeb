package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
 * 此servlet用于验证邮箱和用户名是否已经存在
 * 
 * @author 杨潇
 * 
 */
@WebServlet("/checkUname")
public class CheckUnameServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		String newUname = req.getParameter("uname");
		String newEmail = req.getParameter("email");
		String newSex = req.getParameter("sex");
		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute("user");
		UsersDAO dao = new UsersDAO();
		List<Users> list = dao.list(Users.class);
		List<String> unameList = new ArrayList<String>();
		List<String> emailList = new ArrayList<String>();
		JSONObject root = new JSONObject();
		root.put("isunameExist", "0");
		root.put("isemailExist", "0");
		root.put("issucceed", "0");
		root.put("isalter", "0");
		root.put("isnull", "0");
		boolean flag = true;
		for (Users u : list) {
			unameList.add(u.getUname());
			emailList.add(u.getEmail());
		}
		if(newUname.equals(user.getUname())&&newEmail.equals(user.getEmail())&&newSex.equals(user.getSex())){
			root.put("isalter", "1");
			out.print(root.toString());
		}else{
		//判断用户名是否已存在
		if (!newUname.equals(user.getUname())) {
			if (unameList.contains(newUname)) {
				flag=false;
				root.put("isunameExist", "1");
			}
		}
		if("".equals(newEmail)||"".equals(newUname)){
			flag=false;
			root.put("isnull", "1");
		}
		//判断邮箱是否已存在
		if(!newEmail.equals(user.getEmail())){
			if (emailList.contains(newEmail)) {
				flag=false;
				root.put("isemailExist", "1");
			}
		}
		if(flag){
			user.setEmail(newEmail);
			user.setUname(newUname);
			user.setSex(newSex);
			dao.updateBase(user);
			session.setAttribute("user", user);
			root.put("issucceed", "1");
		}
		out.write(root.toString());
	}
	}
}

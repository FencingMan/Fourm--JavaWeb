package com.web.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用Servlet
 * @author Administrator
 *
 */
public abstract class BaseServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//设置字符编码
		req.setCharacterEncoding("UTF-8");
		//获取执行方法名称
		String method = req.getParameter("method");
		//如果没有传入参数,默认执行execute
		if(method==null){
			method = "execute";
		}
		try {
			//反射生成方法对象
			Method m = this.getClass().getDeclaredMethod(method, HttpServletRequest.class,HttpServletResponse.class);
			//执行方法
			String url = (String) m.invoke(this, req, resp);
			if(url.indexOf(":")!=-1){
				if(url.indexOf("forward")!=-1){
					//转发
					req.getRequestDispatcher(url.substring(url.indexOf(":")+1)).forward(req, resp);
				}else if(url.indexOf("redirect")!=-1){
					//重定向
					resp.sendRedirect(url.substring(url.indexOf(":")+1));
				}
			}else{
				//转发(默认)
				req.getRequestDispatcher(url).forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract String execute(HttpServletRequest req, HttpServletResponse resp);
	
}

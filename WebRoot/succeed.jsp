<%@page import="com.web.dao.UsersDAO"%>
<%@page import="com.web.bean.Users"%>
<%@page import="com.web.bean.Point"%>
<%@page import="com.web.dao.PointDAO"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'succeed.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%
    	Users user = (Users)session.getAttribute("user");
    	PointDAO pdao = new PointDAO();
		pdao.save2(new Point(user.getUserId(),10000,"积分充值",0));
		UsersDAO udao = new UsersDAO();
		udao.updatePoint(user.getUserId(), 10000);
		user = udao.get(user, "userId");
		session.setAttribute("user", user);
    	response.sendRedirect("point");
    
     %>
  </body>
</html>

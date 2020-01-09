<%@page import="com.web.server.SearchNameServer"%>
<%@page import="com.web.bean.Collectlike"%>
<%@page import="com.web.bean.Users"%>
<%@page import="com.web.dao.UsersDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的消息</title>
    <link rel="stylesheet" href="css/reset.css"/>
    <link rel="stylesheet" href="css/homeHead.css"/>
    <link rel="stylesheet" href="css/homePublic.css"/>
    <link rel="stylesheet" href="css/myMsg.css"/>
</head>
<body>
<header class="zyHead">
    <div class="zyHead_cen">
        <a href=""><img src="img/per-con.png" alt="" class="headPic1"/></a>
        <a href="showIndex" class="backIndex">返回首页</a>
        <div class="lt_login">
	            <ul>
	                <li><a href="myhomepage.jsp">${sessionScope.user.uname }</a></li>
                <li><a href="exit">退出</a></li>
	            </ul>
	        </div>
        <!-- 登入结束-->
    </div>
</header>
<div class="homeCen">
    <div class="homeCen_left">
        <ul>
            <li><a href="myhomepage.jsp">我的主页</a></li>
		            <li><a href="alter_user_info.jsp">基本设置</a></li>
		            <li><a href="showmyarticle">我的贴子</a></li>
		            <li class="on"><a href="message">我的消息</a></li>
				<li>
					<a href="point">我的积分</a>
				</li>
        </ul>
    </div>
    <div class="homeCen_right">
        <div class="baseHead">
            <p>我的消息</p>
        </div>
        <div class="myWrite_con">
        <%List<Collectlike> clist = (List<Collectlike>)request.getAttribute("clist"); %>
        <%HttpSession ses = request.getSession(); %>
        <%Users user = (Users)ses.getAttribute("user"); %>
       		<%for(Collectlike c:clist){ %>
       			<%SearchNameServer search = new SearchNameServer(); %>
	        	<%UsersDAO userdao = new UsersDAO(); %>
	        	<%if(!user.getUname().equals(search.getNameByUid(c.getUserid()))&&!!user.getUname().equals(search.getNameByAid(c.getAid()))){ %>
		        	<div class="myMsgCon">
		                <div class="myMsgCon_pic"></div>
		                <div class="myMsgCon_detail">
		                    <a href=""><%=search.getNameByUid(c.getUserid()) %><%=c.getCltype() %>了你的帖子</a>
		                </div>
		                <p class="myMsgCon_time"><%=c.getCltime() %></p>
		            </div>
	        	<%} %>
	        <%} %>
	
        
    </div>
</div>
</body>
</html>
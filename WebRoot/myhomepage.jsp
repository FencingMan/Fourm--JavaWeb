<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'myhomepage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/reset.css"/>
    <link rel="stylesheet" href="css/homeHead.css"/>
    <link rel="stylesheet" href="css/homePublic.css"/>
    <link rel="stylesheet" href="css/home.css"/>

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
            <li class="on"><a href="myhomepage.jsp">我的主页</a></li>
            <li><a href="alter_user_info.jsp">基本设置</a></li>
            <li><a href="showmyarticle">我的贴子</a></li>
            <li><a href="message">我的消息</a></li>
				<li>
					<a href="point">我的积分</a>
				</li>
        </ul>
    </div>
    <div class="homeCen_right">
        <div class="home_self"> <img src="upload/photo/${sessionScope.user.photoName }"/> </div>
        <!--男性为male，女性为female-->
        <div class="home_name">
        <c:if test="${sessionScope.user.sex eq '男' }">
        <p class="male">${sessionScope.user.uname }</p>
        </c:if>
        <c:if test="${sessionScope.user.sex eq '女' }">
        <p class="female">${sessionScope.user.uname }</p>
        </c:if>
        
        
        </div>
        <div class="home_msg">
            <ul>
                <li>${sessionScope.user.birth }</li>
                <li>长沙</li>
            </ul>
        </div>
    </div>
</div>
  </body>
</html>

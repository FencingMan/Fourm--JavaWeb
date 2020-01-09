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
    
    <title>My JSP 'myarticle.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/reset.css"/>
    <link rel="stylesheet" href="css/homeHead.css"/>
    <link rel="stylesheet" href="css/homePublic.css"/>
    <link rel="stylesheet" href="css/myWrite.css"/>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
  </head>
  
  <body>
	<header class="zyHead">
	    <div class="zyHead_cen">
	        <a href="showIndex"><img src="img/per-con.png" alt="" class="headPic1"/></a>
	        <a href="showIndex" class="backIndex">返回首页</a>
	        <div class="lt_login">
	            <ul>
	                <li><a href="alter_user_info.jsp">${sessionScope.user.uname }</a></li>
	                <li><a href="exit">退出</a></li>
	            </ul>
	        </div>
	    </div>
	</header>
	<div class="homeCen">
	    <div class="homeCen_left">
	        <ul>
		            <li class="on"><a>他的贴子</a></li>
	        </ul>
	    </div>
	    <div class="homeCen_right">
	        <div class="baseHead">
	            <ul>
	                <li ><a class="on">他的发帖</a></li>
	            </ul>
	        </div>
	        <div class="myWrite_con" >
	            <div class="writeHead">
	                <div class="writeHead1">帖子标题</div>
	                <div class="writeHead2">发表时间</div>
	                <div class="writeHead3">浏览量</div>
	                <div class="writeHead4">操作</div>
	            </div>
	            <c:if test="${!empty requestScope.articles}">
		            <div class="writeFoot" >
		            	<c:forEach items="${requestScope.articles}" var="a">
			            	<div class="writeFoot1"><p><a href="showArticle?aid=${a.aid }">${a.title }</a></p></div>
			                <div class="writeFoot2">${a.publishTime}</div>
			                <div class="writeFoot3">${a.browseNum}</div>
			                <div class="writeFoot4"><a href="showArticle?aid=${a.aid }">查看</a></div>
			           </c:forEach>
		            </div>
	            </c:if>
	        </div>
	       </div>
	    </div>
	</div>
</body>
</html>


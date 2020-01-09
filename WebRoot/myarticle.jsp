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
    
    <title>我的帖子</title>
    
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
		            <li class="on"><a href="showmyarticle">我的贴子</a></li>
		            <li><a href="message">我的消息</a></li>
				<li>
					<a href="point">我的积分</a>
				</li>
	        </ul>
	    </div>
	    <div class="homeCen_right">
	        <div class="baseHead">
	            <ul>
	                <li ><a href="javascript:;" class="on">我的发帖</a></li>
	                <li ><a href="showmycollect">我收藏的贴</a></li>
	            </ul>
	        </div>
	        <div class="myWrite_con" >
	            <div class="writeHead">
	                <div class="writeHead1">帖子标题</div>
	                <div class="writeHead2">发表时间</div>
	                <div class="writeHead3">浏览量</div>
	                <div class="writeHead4">操作</div>
	            </div>
	            <c:if test="${!empty requestScope.myarticles}">
		            <div class="writeFoot" >
		            	<c:forEach items="${requestScope.myarticles}" var="a">
			            	<div class="writeFoot1"><p><a href="showArticle?aid=${a.aid }">${a.title }</a></p></div>
			                <div class="writeFoot2">${a.publishTime}</div>
			                <div class="writeFoot3">${a.browseNum}</div>
			                <div class="writeFoot4"><a href="updatepage?aid=${a.aid }">修改</a>&nbsp;&nbsp;&nbsp;<a href="myarticle?type=1&aid=${a.aid }">删除</a></div>
			           </c:forEach>
		            </div>
	            </c:if>
	           <%--  <c:if test="${empty requestScope.myarticles } ">
	            	<div class="writeFoot" > 
	            			<div class="writeFoot1"><p>您还没有发帖欧</p></div>
			                <div class="writeFoot2"></div>
			                <div class="writeFoot3"></div>
			                <div class="writeFoot4"></div>
	            	</div>
	            </c:if>
	            --%>
	        </div>
	       </div>
	    </div>
	</div>
</body>
</html>


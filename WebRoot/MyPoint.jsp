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
    
    <title>我的积分</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
    <link rel="stylesheet" href="css/reset.css"/>
    <link rel="stylesheet" href="css/homeHead.css"/>
    <link rel="stylesheet" href="css/homePublic.css"/>
    <link rel="stylesheet" href="css/myWrite.css"/>

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
		            <li><a href="message">我的消息</a></li>
		            <li class="on"><a href="point">我的积分</a></li>
        </ul>
    </div>
    <div class="homeCen_right">
        <div class="baseHead">
            <ul>
                <li><a href="" class="on">积分明细查看</a></li>
                <li><a href="alipay.trade.page.pay.jsp?dingdanhao=${dingdanhao }">积分充值</a></li>
                <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
                <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
                <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
                <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
                <li><a href="cart.jsp">实物兑换</a></li>
                <li><a >剩余积分：${sessionScope.user.point }</a></li>
            </ul>
        </div>
        <div class="myWrite_con">
            <div class="writeHead">
                <div class="writeHead1">文章id</div>
                <div class="writeHead2">时间</div>
                <div class="writeHead3">积分数量</div>
                <div class="writeHead4">操作类型</div>
            </div>
            <div class="writeFoot">
            <c:forEach items="${myPoint }" var="point">
                <div class="writeFoot1"><p><a href="">${point.aid }</a></p></div>
                <div class="writeFoot2">${point.time }</div>
                <div class="writeFoot3">${point.pnum }</div>
                <div class="writeFoot4"><a href="javascript:">${point.ptype }</a></div>
                </c:forEach>
            </div>
        </div>
</div>
  </body>
</html>

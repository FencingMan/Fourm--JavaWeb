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
    
    <title>搜索</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/reset.css"/>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/search.css"/>
	<!-- bootstrap -->
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
	<script src="js/jquery-1.8.3.min.js"></script>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<style type="text/css">
			.msgnum{
			width:15px;
			height:15px;
			background-color: pink;/*#e43e20*/
			color: white;
			position: relative;
			left:618px;
			top:9px;
			border-radius:50%;
			display: inline-block;
			text-align: center;
			line-height: 15px;
		}
		</style>
  </head>
  
  <body>
<header class="ltHead">
		<div class="ltHead_cen">
			<a href="showIndex"><img src="img/logo.png" alt="" class="headPic1" />
			</a>
			<ul class="headNav">
				<li>
					<a href="showIndex">首页</a>
				</li>
				<li>
					<a href="seach.jsp">搜索</a>
				</li>
				<li>
					<a href="article_edit.jsp">发新帖</a>
				</li>
				<li>
					<a href="">我的积分</a>
				</li>
			</ul>
			<c:if test="${!empty user}">
				<span class="msgnum"><a id="mn" href="message"><%=session.getAttribute("lilen") %></a></span>
				<div class="lt_login">
					<ul>
						<li>
							<a href="myhomepage.jsp"><img src="upload/photo/${user.photoName}"
									style="width: 30px; height: 30x; margin-right: 15px" />${user.uname}</a>
						</li>
						<li>
							<a href="exit">退出</a>
						</li>
					</ul>
				</div>
			</c:if>
		</div>
		</header>
<div class="searchBody">
    <div class="writePending">
    	<form action="seach" method="post">
        <div class="newPending_head">
            <div class="tzHeng"></div>
            <div class="newPending_head_tittle">搜索  
            <select name="type" class="form-control" style="">
            	<option value="1"  selected>按标题</option>
            	<option value="2">按发贴人</option>
            </select>
            </div>
        </div>
        <br/><br/>
        <div class="writePending_con">
            <input type="text" name="seach" placeholder="请输入关键词..."/>
            <input type="submit" value="搜索"/>
        </div>
        </form>
       
    </div>
    <c:if test="${!empty result}">
    	<div class="indexMain_left_con">

					<c:forEach items="${result }" var="s">
						<!--有主题图循环开始-->
						<div class="indexCon_msg">
							<div class="indexCon_msg_pic">
								<img alt="" src="upload/photo/${s.photoName }">
							</div>
							<div class="indexCon_msg_detail">
								<a href="">
									<div class="indexCon_msg_detail_tittle">
										<span>TM</span>
										<p>
											<a href="showArticle?aid=${s.aid }">${s.title }</a>
										</p>
									</div> </a>
								<!--  <div class="havePic">
									<a href=""><div class="havePic_head"></div> </a>
								</div>-->
								<div class="indexCon_msg_detail_other">
									<ul>
										<li>
											${s.uname }
										</li>
										<li>
											${s.publishTime }
										</li>
										<li>
											${s.browseNum }
										</li>
										<li>
											${s.commentnum }
										</li>
									</ul>
								</div>
							</div>
							<div class="clear"></div>
						</div>
					</c:forEach>
					<!--有主题图循环结束-->

				</div>
    	
    </c:if>
    
    
</div>
</body>
</html>

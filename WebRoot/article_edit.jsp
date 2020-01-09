<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>发新帖</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css"
			href="app/editormd/examples/css/style.css">
		<link rel="stylesheet" type="text/css"
			href="app/editormd/css/editormd.min.css">
		<link rel="stylesheet" href="css/reset.css" />
		<link rel="stylesheet" href="css/public.css" />
		<link rel="stylesheet" href="css/write.css" />
		<script src="app/editormd/examples/js/jquery.min.js"></script>
		<script src="app/editormd/editormd.min.js"></script>
		<script src="js/bootstrap.js"></script>

		<!-- bootstrap -->
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
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
					<a href="point">我的积分</a>
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

		<div class="writeCon">
			<div class="writeCon_head">
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发新帖
				</p>
			</div>
			<form action="addArticle" method="post">
				<div class="writeCon_cen">
					<div class="writeMsg">
						<input type="text" name="title" placeholder="请输入标题" />
					</div>
					<div class="writeDown">
						<div class="editormd" id="test-editormd">
							<textarea class="editormd-markdown-textarea" name="markdown"
								id="editormd"></textarea>
							<!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
							<textarea class="editormd-html-textarea" name="content"></textarea>
						</div>
					</div>
				</div>
				<input type="submit" class="reform" value="发布" />
			</form>
		</div>



		<script type="text/javascript">
			var testEditor;

            $(function() {
                testEditor = editormd("test-editormd", {
                    width   : "100%",
                    height  : 640,
                    syncScrolling : "single",
                    path    : "app/editormd/lib/",
                    imageUpload : true,
  					imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
  					imageUploadURL : "<%=path %>/imgUpload"

		});
	});
</script>



	</body>

</html>

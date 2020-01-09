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

		<title>修改帖子</title>

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
	</head>


	<body>
		<header class="ltHead">
		<div class="ltHead_cen">
			<a href=""><img src="img/logo.png" alt="" class="headPic1" />
			</a>
			<ul class="headNav">
				<li>
					<a href="showmyarticle">返回</a>
				</li>
				
			</ul>
			<!-- 登入开始，未登入时以下隐藏-->
				<div class="lt_login">
					<ul>
						<li>
							<a href=""><img src="upload/photo/${sessionScope.user.photoName}"
									style="width: 30px; height: 30x; margin-right: 15px" />${sessionScopeuser.uname}</a>
						</li>
						<li>
							<a href="exit">退出</a>
						</li>
					</ul>
				</div>
			
			<!--登入结束-->
		</div>
		</header>

		<div class="writeCon">
			<div class="writeCon_head">
				<p>
					修改帖子
				</p>
			</div>

			<form action="updateArticle" method="post">
				<div class="writeCon_cen">
					<div class="writeMsg">
						<input type="hidden" name="aid" value="${requestScope.article.aid }"/>
						<input type="text" name="title" placeholder="请输入标题" value="${requestScope.article.title }" />
					</div>
					<div class="writeDown">
						<div class="editormd" id="test-editormd">
							<textarea class="editormd-markdown-textarea" name="markdown"
								id="editormd">${requestScope.article.content }</textarea>
							<!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
							<textarea class="editormd-html-textarea" name="content"></textarea>
						</div>
					</div>
				</div>
				<input type="submit" class="reform" value="修改" />
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

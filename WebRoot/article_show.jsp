<%@page import="com.web.dao.UsersDAO"%>
<%@page import="com.web.bean.Comments"%>
<%@page import="com.web.bean.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>看帖</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="app/editormd/examples/css/style.css" />
		<link rel="stylesheet" href="app/editormd/css/editormd.preview.css" />
		<link rel="stylesheet" href="css/reset.css" />
		<link rel="stylesheet" href="css/public.css" />
		<link rel="stylesheet" href="css/index.css" />
		<!-- bootstrap -->
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
		<script src="app/editormd/examples/js/jquery.min.js"></script>
		<script src="app/editormd/lib/marked.min.js"></script>
		<script src="app/editormd/lib/prettify.min.js"></script>
		<script src="app/editormd/lib/raphael.min.js"></script>
		<script src="app/editormd/lib/underscore.min.js"></script>
		<script src="app/editormd/lib/sequence-diagram.min.js"></script>
		<script src="app/editormd/lib/flowchart.min.js"></script>
		<script src="app/editormd/lib/jquery.flowchart.min.js"></script>
		<script src="app/editormd/editormd.js"></script>
		<script src="js/bootstrap.js"></script>
		<script src="js/tiezi.js"></script>
		<style>
.editormd-html-preview {
	width: 90%;
	margin: 0 auto;
}
	p{
		text-align: left;
		margin:0px;
	}
	
	
    
    
    .indexMain_right{
  float: left;
    margin-left:50px;
    width: 390px;
    position: relative;
}
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
        <a href=""><img src="img/logo.png" alt="" class="headPic1"/></a>
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
        <c:if test="${!empty sessionScope.user}">
        <span class="msgnum"><a id="mn" href="message"><%=session.getAttribute("lilen") %></a></span>
       		 <div class="lt_login">
       			 <ul>
       				 <li><a href="myhomepage.jsp"><img src="upload/photo/${sessionScope.user.photoName}" style="width:30px;height:30x;margin-right:15px"/>${sessionScope.user.uname}</a></li>
        			 <li><a href="exit">退出</a></li>
       			 </ul>
        	</div>
        </c:if>
    </div>
</header>
		
		<div class="indexMain">
			<div class="indexMain_left">
				<div class="tzCon">
					<div class="tzCon_head">
						<div class="tzCon_head_left">
							<img src="upload/photo/${requestScope.user.photoName }">
						</div>
						<div class="tzCon_head_right">
							<h1 style="text-align: left">
								${requestScope.article.title }
							</h1>
							<ul>
								<li>
									${requestScope.user.uname }
								</li>
								<li>
									${requestScope.article.publishTime }
								</li>
								<li>
									${requestScope.article.browseNum }
								</li>
							</ul>
						</div>
						<div class="clear"></div>
					</div>
					<div class="tzCon_con">
						<div id="layout">
							<div id="test-editormd-view2">
								<textarea id="append-test" style="display: none;">${requestScope.article.content }</textarea>
							</div>
						</div>
					</div>
					<div class="tzCon_foot">
						<!-- 点赞收藏部分 -->

						<!-- 如果是自己的帖子不显示收藏 -->
						<div class="tzCollect" style="display: none;">
							<div class="tzCollect_left" id="c">收藏</div>
							<div class="tzCollectLike_right" id="cn">${requestScope.collects }</div>
						</div>
						<c:if test="${empty sessionScope.user||!empty sessionScope.user&&requestScope.user.userId!=sessionScope.user.userId }">
							<script>
								$(".tzCollect").show();
							</script>
						</c:if>
						<c:if test="${requestScope.collect}">
							<script>
								$("#c").addClass('on');
							</script>
						</c:if>
						<!-- 登录用户才可点击收藏 -->
						<c:if test="${!empty sessionScope.user}">
							<script>
								var collect;
								$("#c").click(function() {

									if($(this).hasClass('on')) {
										$(this).removeClass('on');
										collect = 0;
										$("#cn").html(parseInt($("#cn").html()) - 1);
										$.post("collect", {
											"collect": collect,
											"userid": ${sessionScope.user.userId },
											"aid": ${requestScope.article.aid }
										});
									} else {
										$(this).addClass('on');
										collect = 1;
										$("#cn").html(parseInt($("#cn").html()) + 1);
										$.post("collect", {
											"collect": collect,
											"userid": ${sessionScope.user.userId },
											"aid": ${requestScope.article.aid }
										});
									}

								});
							</script>
						</c:if>

						<div class="tzLike">

							<div class="tzLike_left" id="L">
								点赞

							</div>
							<c:if test="${requestScope.like}">
								<script>
									$("#L").addClass('on');
								</script>
							</c:if>
							<div class="tzCollectLike_right" id="ln">
								${requestScope.likes }
							</div>

							<!-- 登录用户才可点赞 -->
							<c:if test="${!empty sessionScope.user}">

								<script type="text/javascript">
									var like;
									$("#L").click(function() {

										if($(this).hasClass('on')) {
											$(this).removeClass('on');
											like = 0;
											$("#ln").html(parseInt($("#ln").html()) - 1);
											$.post("like", {
												"like": like,
												"userid": ${sessionScope.user.userId },
												"aid": ${requestScope.article.aid }
											});
										} else {
											$(this).addClass('on');
											like = 1;
											$("#ln").html(parseInt($("#ln").html()) + 1);
											$.post("like", {
												"like": like,
												"userid": ${sessionScope.user.userId },
												"aid": ${requestScope.article.aid }
											});
										}

									});
								</script>
							</c:if>

						</div>
					</div>
				</div>
			

			<div class="writePending">
				<div class="newPending_head">
					<div class="tzHeng"></div>
					<div class="newPending_head_tittle">
						评论
					</div>
				</div>

				<div class="writePending_con">
					<input type="text" placeholder="写下你的评论..." id="txt" name="txt" />
					<input type="button" value="评论" onclick="comments('${param.aid }')" />
				</div>

				<c:if test="${!empty sessionScope.user }">
					<script type="text/javascript">
						function comments(id) {
							var txt = document.getElementById("txt").value;
							if(txt == null || txt == "") {
								window.confirm("请输入评论内容!!! ");
								return;
							}
							window.location.href = "first?aid=" + id + "&txt=" + txt;
						}
					</script>
				</c:if>
				<c:if test="${empty sessionScope.user }">
					<script type="text/javascript">
						function comments(id) {
							var boolean = confirm("请先登录");
							return;
						}
					</script>
				</c:if>

			</div>
			<div class="newPending">
				<div class="newPending_head">
					<div class="tzHeng"></div>
					<div class="newPending_head_tittle">
						最新评论
					</div>
				</div>
				<!--楼主可删除评论、自己可以删除自己的评论删除按钮酌情出现-->
				<c:if test="${empty firstComments }">
					<p>此帖子还没有人评论！！！</p>
				</c:if>
				<c:if test="${!empty firstComments }">
					<% List<Comments> firstComments=(List<Comments>)request.getAttribute("firstComments"); 
						UsersDAO dao = new UsersDAO();
					%>
					<% for(int i = 0 ; i < firstComments.size();i++) {%>
					<div class="newPending_son">
						<div class="pendPic"><img src="upload/photo/<%=dao.get(new Users(firstComments.get(i).getUserId()),"userId").getPhotoName() %>"/></div>
						<div class="pendDetail">
							<div class="pendDetail_head">
								<p>
									<%=firstComments.get(i).getUname() %>
									<span><%=firstComments.get(i).getCommentTime() %></span>
								</p>
							</div>
							<div class="pendDetail_con">
								<p>
									<%=firstComments.get(i).getC_content() %>
								</p>
							</div>
							<div class="pendDetail_btn">
								<ul>
									<li>
										1
									</li>
									<li class="replayBtn">
										回复
									</li>
									<li class="delateBtn">
										<a href="delete?cid=<%=firstComments.get(i).getCid() %>&aid=<%=firstComments.get(i).getAid() %>">删除</a>
									</li>
								</ul>
							</div>
							<div class="pendDetail_action">
								回复<%=firstComments.get(i).getUname() %>:<input type="text" class="form-control" id="reply<%=firstComments.get(i).getCid() %>" name="reply" />
								<button class="btn" onclick="scclick('<%=firstComments.get(i).getCid() %>','<%=firstComments.get(i).getUname() %>','<%=firstComments.get(i).getSuperid() %>','<%=firstComments.get(i).getAid() %>')">
									评论
								</button>
								<button>
									取消
								</button>
							</div>
							<c:if test="${empty secondComments }">
								<div id="pendDetail_replayCon">
									<p></p>
								</div>
							</c:if>
							<c:if test="${!empty secondComments }">
								<% 
								List<List<Comments>> secondCommentsList=(List<List<Comments>>)request.getAttribute("secondComments");
								List<Comments> secondComments =  secondCommentsList.get(i);
							%>
								<% for(int j=0;j<secondComments.size();j++){ %>
								<span id="user" class="user"><%=dao.getName(secondComments.get(j).getUserId()) %></span>回复:
								<%=secondComments.get(j).getUname() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span><%=secondComments.get(j).getC_content() %></span><button class="btn btn-success btn-xs" style="float:right" onclick="again('<%=dao.getName(secondComments.get(j).getUserId()) %>','<%=firstComments.get(i).getAid()%>','<%=firstComments.get(i).getCid()%>')">回复</button><br/><br/>
								<form class="">
									<div class="showone form-group" style="display:none">
										<input type="text" class="form-control" /><br/>
										<button class="btn btn-danger btn-xs" style="float:right">回复</button>
									</div>
								</form>
								<%} %>
								<script>
									function again(ab,cd,ef) {
										var huifu = window.prompt("请输入回复");
										if(huifu == null || huifu == "") {
											return;
										}
										window.location.href = "again?bplr=" + ab + "&aid=" + cd + "&txt=" + huifu + "&sid=" + ef ;
									}
								</script>
							</c:if>
						</div>

						<div class="clear"></div>
					</div>

					<%} %>

				</c:if>
				<script type="text/javascript">
					function scclick(c, d, s, a) {
						var reply = document.getElementById("reply" + c).value;
						window.location.href = "second?cid=" + c +"&beipinglunren="+d+ "&superid=" + s + "&reply=" + reply + "&aid=" + a;
						$("#reply" + c).val("");
					}
				</script>
				<!--测试内容结束、十条内容分页-->
			</div>
			</div>
		</div>
		<div class="indexMain_right">
			<a href="article_edit.jsp" class="newMsg">发新帖</a>
			<div class="myMsg">
				<div class="myMsg_con">
					<div class="myMsg_conPic">
						<img src="upload/photo/${requestScope.user.photoName }">
					</div>
					<p>
						${requestScope.user.uname }
					</p>
				</div>
				<div class="myMsg_footer">
					<ul>
						<li>
							<a href="showotherarticlelist?uid=${requestScope.user.userId }">
								<p>
									主题数
								</p>
								<p>
									${requestScope.zhutishu }
								</p>
							</a>
						</li>
						<li>
							<a>
								<p>
									精华数
								</p>
								<p>
									23
								</p>
							</a>
						</li>
						<li>
							<a>
								<p>
									注册排名
								</p>
								<p>
									23
								</p>
							</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="indexSearch">
				<input type="text" id="myinput" placeholder="请输入关键词" />
				<input type="submit" value="搜索" />
			</div>
			<div class="indexPublic">
						<div class="indexPublic_head">
							本站热帖
						</div>
						<div class="indexPublic_con">
							<ul class="weekHot">
								<c:forEach items="${browseRank }" var="rank">
								<li>
									<a href="showArticle?aid=${rank.aid }">${rank.title }</a><span>${rank.num }</span>
								</li>
								</c:forEach>
							</ul>
						</div>
					</div>
			<div class="indexPublic">
						<div class="indexPublic_head">
							友情链接
						</div>
						<div class="indexPublic_con">
							<ul class="indexLink">
								<li>
									<a href="https://www.csdn.net/">CSDN</a>
								</li>
								<li>
									<a href="http://bbs.xiaomi.cn/">小米社区</a>
								</li>
								<li>
									<a href="https://bbs.meizu.cn/">魅族社区</a>
								</li>
								<li>
									<a href="https://bbs.tianya.cn/">天涯论坛</a>
								</li>
								<li>
									<a href="http://www.cctv.com/">央视网_世界就在眼前</a>
								</li>
								<li>
									<a href="http://www.Google.com/">Google</a>
								</li>
							</ul>
						</div>
					</div>
					<div class="indexWxpublic">
						<p style="text-align: center">
							联系管理员
						</p>
					</div>
		</div>
		<div class="clear"></div>
		<footer class="publicFooter">
			<p style="text-align: center">
				Copyrigh &copy; 2019 CNMD 长沙草泥马有限公司 版权所有 湘ICP备16032224号-2
			</p>
		</footer>
		<script type="text/javascript">
			
			$(".pendDetail_btn li:nth-child(1)").click(function() {
				$(this).addClass("on");
			});
			$(".replayBtn").click(function() {
				$(".pendDetail_action").hide();
				$(this).parent().parent().next(".pendDetail_action").show();
			});
			$(".pendDetail_action button:last-of-type").click(function() {
				$(".pendDetail_action").hide();
			});

			//function() {
			var testEditormdView2;
			testEditormdView2 = editormd.markdownToHTML("test-editormd-view2", {
				htmlDecode: "style,script,iframe", // you can filter tags decode
				emoji: true,
				taskList: true,
				tex: true, // 默认不解析
				flowChart: true, // 默认不解析
				sequenceDiagram: true
				// 默认不解析
			});
			//}//setTimeout(,200);
		</script>
</body>
</html>

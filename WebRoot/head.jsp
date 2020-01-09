<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta charset="UTF-8">
		<title>论坛首页</title>
		<link rel="stylesheet" href="css/reset.css" />
		<link rel="stylesheet" href="css/public.css" />
		<link rel="stylesheet" href="css/index.css" />
		<!-- bootstrap -->
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="css/animate.css"/>
		<script src="js/jquery-1.8.3.min.js"></script>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.js"></script>
		<style>
		  .box1{
			position:absolute;
			top:100px;
			left:500px;
		    width:500px; height: 150px;
		    color:white;
		    font-size:30px;
		    text-align:center;
		    line-height:150px;
		    margin: 20px auto;
			border-radius:15px;
			display:none;
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
		  .box1{background: #57CFA1;}
		</style>
	</head>

	<body>
	  <div class="box1 animated bounceInDown" ></div>
	<c:if test="${isFirstLogin==1 }">
	<%session.removeAttribute("isFirstLogin"); %>
		<script>
		$(".box1").html("欢迎新用户登录，积分+50");
		$(".box1").show();
		setTimeout(function(){
			$(".box1").animate({top:"-600"},300);
		},2000);
		</script>
	</c:if>
	<c:if test="${isFirstLogin==2 }">
	<%session.removeAttribute("isFirstLogin"); %>
		<script>
		$(".box1").html("今日首次登录，积分+20");
		$(".box1").show();
		setTimeout(function(){
			$(".box1").animate({top:"-600"},300);
		},2000);
		</script>
	</c:if>
		<header class="ltHead">
		<div class="ltHead_cen">
			<a href="showIndex"><img src="img/logo.png" alt="" class="headPic1" /> </a>
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
			<button style="display: none;" data-toggle="modal"
				data-target="#failed" id="bfailed"></button>
			<c:if test="${empty user}">
				<!--未登入开始-->
				<div class="ltForm">

					<a href=""><img src="img/indexForm_bg.png" alt=""
							class="headPic2" /> </a>
					<ul>
						<li>
							<a data-toggle="modal" data-target="#login" id="alogin">登录</a>
						</li>
						<li>
							<a href="reg.jsp">注册</a>
						</li>
					</ul>
				</div>
			</c:if>
			<!-- 未登入结束-->
			<%HttpSession s = request.getSession(); %>
			
			<!-- 登入开始，未登入时以下隐藏-->
			<c:if test="${!empty user}">
				<span class="msgnum"><a id="mn" href="message"><%=s.getAttribute("lilen") %></a></span>
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
			<!--登入结束-->
		</div>


		<!--登录模态框  -->
		<!--模态框结构   fade 动画效果-->
		<div class="modal fade" id="login" data-backdrop="static">
			<!--窗口层-->
			<div class="modal-dialog modal-md">
				<!--内容层-->
				<div class="modal-content" style="background-image: ">
					<!--头部，身体， 底部-->
					<div class="modal-header">
						<h2 class="modal-title text-success">
							登录
							<span class="close" data-dismiss="modal">&times;</span>
						</h2>

					</div>
					<div class="modal-body">
						<!--输入框组-->
						<!--表单 -->
						<div class="row">
							<div class="col-md-8 col-md-offset-2">

								<div class="input-group" style="margin-bottom: 20px;">
									<span class="input-group-addon"
										style="margin: 0px; padding: 0px; background-color: white; border: 0px; width: 30px">
										<img src="img/user.png"
											style="width: 30px; height: 30px; margin-right: 20px;" /> </span>
									<input type="text" class="form-control" style="width: 300px;"
										name="user" id="user" placeholder="请输入用户ID或邮箱" />
									<span class="input-group-addon" id="msg1"
										style="display: none;"><font color="red">请输入账号</font> </span>
								</div>
								<div class="input-group">
									<span class="input-group-addon"
										style="margin: 0px; padding: 0px; background-color: white; border: 0px; width: 30px"">
										<img src="img/password.png"
											style="width: 30px; height: 30px; margin-right: 20px;" /> </span>
									<input type="password" class="form-control"
										style="width: 300px;" name="password" id="password"
										placeholder="请输入密码" />
									<span class="input-group-addon" id="msg2"
										style="display: none;"> <font color="red">请输入密码</font>
									</span>
								</div>
								<br />
								<div class="input-group">
									<span class="input-group-addon"
										style="margin: 0px; padding: 0px; background-color: white; border: 0px; width: 30px">
										<img src="img/yzm.png"
											style="width: 30px; height: 30px; margin-right: 20px;" /> </span>
									<input type="text" id="checkcode" name="checkcode"
										class="form-control" style="width: 70px;" name="password"
										id="password" placeholder="验证码" />
									<span class="input-group-addon"
										style="margin: 0px; padding: 0px; background-color: white; border: 0px; width: 30px">
										<img art="" src="checkcode" id="code"
											onclick="this.src='checkcode?'+Math.random();" /><a
										href="javascript:void(0);"
										onclick="document.getElementById('code').src='checkcode?'+Math.random();">看不清，换一张</a>
									</span>
									<span class="input-group-addon"
										style="margin: 0px; padding: 0px; background-color: white; border: 0px; width: 30px; display: none;"
										id="msg31"> <font color="red">请输入验证码</font> </span>
									<span class="input-group-addon"
										style="margin: 0px; padding: 0px; background-color: white; border: 0px; display: none;"
										id="msg32"> <font color="red">验证码有误</font> </span>

								</div>

							</div>
						</div>


<script>
	$('#alogin').click(function(){
		$('#msg1').hide();
		$('#msg2').hide();
	 	$('#msg31').hide();
		$('#msg32').hide();
	});
	
	$('#user').focus(function() {
		$('#msg1').hide();
	});
	$('#password').focus(function() {
		$('#msg2').hide();
	});
	$('#checkcode').focus(function() {
		$('#msg31').hide();
		$('#msg32').hide();
	});
</script>

					</div>
					<div class="modal-footer">
						<center>
							<input class="btn btn-success" style="width: 200px" id="denglu"
								type="button" value="登录">
							<button class="btn btn-danger" data-dismiss="modal">
								取消
							</button>
						</center>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
	$("#denglu").click(function() {
		$.post("login", {
			"username" : $("#user").val(),
			"password" : $("#password").val(),
			"checkcode" : $("#checkcode").val()
		}, function(flag) {
			if (flag.length == 2) {

				location.reload();
			}
			for (i in flag) {
				if (flag[i] == 1) {
					$('#msg1').show();
				}
				if (flag[i] == 2) {
					$('#msg2').show();
				}
				if (flag[i] == 3) {
					$('#msg31').show();
				}
				if (flag[i] == 4) {
					$('#msg32').show();
				}
				if (flag[i] == 6) {
					$('#bfailed').click();
					$('#code').click();
				}
			}
		});
	});
</script>
		<!-- 登录失败 -->
		<!--模态框结构   fade 动画效果-->
		<div class="modal fade" id="failed" data-backdrop="static">
			<!--窗口层-->
			<div class="modal-dialog modal-md">
				<!--内容层-->
				<div class="modal-content" style="background-image: ">
					<!--头部，身体， 底部-->
					<div class="modal-header">
						<h2 class="modal-title text-warning">
							loginfailed
							<span class="close" data-dismiss="modal">&times;</span>
						</h2>
					</div>
					<div class="modal-body">
						<h4 class="modal-title text-warning">
							抱歉您输入的账号或密码有误，请重新登录！
						</h4>
						<br />
					</div>
					<div class="modal-footer">
						<center>
							<button id="back" class="btn btn-success" data-dismiss="modal">
								&nbsp&nbsp确&nbsp&nbsp&nbsp定&nbsp&nbsp
							</button>
						</center>
					</div>
				</div>
			</div>
		</div>
		</header>
		</body>
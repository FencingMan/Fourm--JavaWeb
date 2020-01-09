<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>CNMD论坛</title>
		<meta name="keywords" content="盒老师">
		<meta name="content" content="盒老师">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
	    <link type="text/css" rel="stylesheet" href="css/reg.css">
	    <script type="text/javascript" src="js/jquery.min.js"></script>
	    <style type="text/css">
	    	.red{
				color: red;
			}
			.green{
				color: green;
			}
	    </style>
		<script>
		
			var flag1 = false;//判断用户名是否符合规范
			var flag2 = false;//判断密码是否符合规范
			var flag3 = false;//判断邮箱是否符合规范
			var flag4 = false;//判断上传的文件是否符合规范
			var flag5 = false;
			
			function changeMonth(month){
				$('#sel3').val();
				var year = document.getElementById("sel1").value;
				if(month == 2){
		    		//如果是闰年
				    if(year % 4 == 0 && year % 100 != 0||year % 400 == 0){
				        days = 29;
				    //如果是平年
				    }else{
				        days = 28;
				    }
					//如果是第4、6、9、11月
				}else if(month == 4 || month == 6 ||month == 9 ||month == 11){
				    days = 30;
				}else{
				    days = 31;
				}
				//生成1日—31日
				for(var i = 1; i <=days; i++){
				    var option = document.createElement('option');
				    option.setAttribute("value",i);
				    option.innerHTML = i;
				    sel3.appendChild(option);    
				}
			}
			
			//判断用户名是否符合规范
			function fun1(){
				var userrep = /^[A-Za-z0-9]+$/;
				var user = $('#username').val();
				//console.log(userrep.test(user));
				if(userrep.test(user)){
					$('.usertxt').html('√').addClass('green').removeClass('red');
					flag1=true;
				}else{
					$('.usertxt').html('X').addClass('red').removeClass('green');
				}
				
			}
			
			//判断密码是否符合规范(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)
			function fun2(){
				var pwdrep = /^[A-Za-z0-9]{3,}$/;//去g ^[a-zA-Z]\w{5,17}$
				var pwd = $('#password').val();
				var flag = pwdrep.test(pwd);
				//pwdrep.lastIndex=0;
				console.log(pwdrep.test(pwd));//false
				console.log(pwdrep.lastIndex);
				//pwdrep.lastIndex=0;
				console.log(pwdrep.test(pwd));//true
				console.log(pwdrep.lastIndex);
				//pwdrep.lastIndex=0;
				console.log(pwdrep.test(pwd));//false
				//pwdrep.lastIndex=0;
				console.log(pwdrep.test(pwd));//true
				//pwdrep.lastIndex=0;
				if(flag){
					$('.pwdtxt').html('√').removeClass('red').addClass('green');
					flag2 = true;
				}else{
					$('.pwdtxt').html('X').removeClass('green').addClass('red');
				}
			}
			
			//判断邮箱是否符合规范
			function fun3(){
				var emailrep = /^[a-z_0-9]{2,}@[a-z0-9]{2,}\.com$/;
				var email = $('#email').val();
				if(emailrep.test(email)){
					$('.mailtxt').html('√').addClass('green').removeClass('red');
					flag3 = true;
				}else{
					$('.mailtxt').html('X').addClass('red').removeClass('green');
				}
			}
			
			//判断上传的文件是否符合规范
			function fun4(){
			//String fileEnd = filename.substring(filename.indexOf('.')+1);
			//if (fileEnd.equals("jpg")||fileEnd.equals("png")) {
				var file = $('#file').val();
				var arr = file.split(".");
				//alert(arr[arr.length-1]);
				if(arr[arr.length-1]=="jpg"||arr[arr.length-1]=="png"||arr[arr.length-1]=="gif"){
					$('.filetxt').html('头像格式符合规范').addClass('green').removeClass('red');
					flag4 = true;
				}else{
					$('.filetxt').html('头像格式不符合规范').addClass('red').removeClass('green');
				}
			}
			
			function reg(){
				//判断协议是否勾选
				var flag5 = $('#agree').prop('checked');
				//alert($("#sel1 option:selected").val());				
				//alert($("#sel2 option:selected").val());				
				//alert($("#sel3 option:selected").val());				
				if(!flag1&&!flag2&&!flag3&&!flag4&&!flag5){
					alert("请先填写注册信息！");
				}else if(!flag1){
					alert("用户名格式有误！");
				}else if(!flag2){
					alert("密码格式有误！");
				}
				else if(!flag3){
					alert("邮箱格式有误！");
				}
				else if(!flag4){
					alert("头像文件格式有误！");
				}else if(!flag5){
					alert("请先阅读协议！");
				}else{
					alert("注册成功！");
					//window.location.href="upload";
					//window.open("login.jsp");
				}
			}
			
		</script>
	</head>
	<body class="login_bj" >
		
		<div class="zhuce_body">
			<div class="logo"><a href="#"><img src="img/logoindex.png" width="220" height="120" border="0"></a></div>
		    <div class="zhuce_kong">
		    	<div class="zc">
		        	<div class="bj_bai">
		            <h3>欢迎注册</h3>
	       	  	  	<form action="upload" method="post" enctype="multipart/form-data">
	       	  	  		<!-- accept="image/*" -->
	       	  	  		<img src="img/photo.png" style="vertical-align: middle;"/><span style="color:gray;line-height: 35px;">用户头像</span>
	       	  	  		<a href="javascript:;" class="file" style="vertical-align: middle;">选择文件<input type="file" name="file" id="file" onchange="fun4()"></a><span class="filetxt"></span><br />
		       	  	  	<input type="text" class="kuang_txt username" id="username" name="username" placeholder="用户名" autocomplete="off" onblur="fun1()"><span class="usertxt" value="{{old('username')}}"></span><br />
		                <input type="password" class="kuang_txt possword" id="password" name="password" placeholder="密码" autocomplete="off" onblur="fun2()"><span class="pwdtxt"></span><br />
		               	<input type="text" class="kuang_txt email" id="email" name="email" placeholder="邮箱" autocomplete="off" onblur="fun3()"><span class="mailtxt"></span><br />
		               	<span style="color:gray;line-height: 30px;">性别</span><input type="radio" name="sex" class="male" value="男" checked/>男
		                <input type="radio" name="sex" class="女" value="female"/>女<br/>
		                <span style="color:gray;line-height: 30px;">出生日期</span>
	                	<select name="sel1" id="sel1">
					        <script>
					        	//生成1900年-2100年
								for(var i = 1970; i<=2019;i++){
								    var option = document.createElement('option');
								    option.setAttribute('value',i);
								    option.innerHTML = i;
								    sel1.appendChild(option);
								}
					        </script>
					    </select>
					    年
					    <select name="sel2" id="sel2" onchange="changeMonth(this.value)">
					    	<script>
					    		//生成1月-12月
								for(var i = 1; i <=12; i++){
								    var option = document.createElement('option');
								    option.setAttribute('value',i);
								    option.innerHTML = i;
								    sel2.appendChild(option);    
								}
					    	</script>
					    </select>
					    月
					    <select name="sel3" id="sel3">
					    	<script>
					    		for(var i = 1; i <=31; i++){
								    var option = document.createElement('option');
								    option.setAttribute('value',i);
								    option.innerHTML = i;
								    sel3.appendChild(option);    
								}
					    	</script>
					    </select>
					    日
		                
		                <div>
		               		<input name="agree" type="checkbox" id="agree"><span>已阅读并同意<a href="#" target="_blank"><span class="lan">《CNMD论坛使用协议》</span></a></span>
		                </div>
		                <!--<input name="注册" type="sumbit" class="btn_zhuce" value="注册">  -->
		                <button type="submit" class="btn_zhuce" onclick="reg()">注册</button>
			            </div>
		            </form>
		        	<div class="bj_right">
		            	<p>使用以下账号直接登录</p>
		                <a href="#" class="zhuce_qq">QQ注册</a>
		                <a href="#" class="zhuce_wb">微博注册</a>
		                <a href="#" class="zhuce_wx">微信注册</a>
		                <p>已有账号？<a href="showIndex">立即登录</a></p>
		            </div>
		        </div>
		    </div>
		</div>
	</body>
</html>

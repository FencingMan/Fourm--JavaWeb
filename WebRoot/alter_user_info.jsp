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
    
    <title>用户信息修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/reset.css"/>
    <link rel="stylesheet" href="css/homeHead.css"/>
    <link rel="stylesheet" href="css/homePublic.css"/>
    <link rel="stylesheet" href="css/base.css"/>

  </head>
  
  <body>
    <header class="zyHead">
    <div class="zyHead_cen">
        <a href=""><img src="img/per-con.png" alt="" class="headPic1"/></a>
        <a href="" class="backIndex">返回首页</a>
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
            <li class="on"><a href="alter_user_info.jsp">基本设置</a></li>
            <li><a href="showmyarticle">我的贴子</a></li>
            <li><a href="message">我的消息</a></li>
				<li>
					<a href="point">我的积分</a>
				</li>
        </ul>
    </div>
    <div class="homeCen_right">
        <div class="baseHead">
            <ul>
                <li><a href="javascript:" class="on">我的资料</a></li>
                <li><a href="javascript:">头像</a></li>
                <li><a href="javascript:">密码</a></li>
            </ul>
        </div>
        <div>
        <div class="baseCon">
            <div class="baseCon_son">
                <div class="baseCon_son_left">
                    昵称
                </div>
                <div class="baseCon_son_right">
                    <input type="text" id="unametxt" value="${sessionScope.user.uname }"/>
                    
            <span id="msg1" style="display:none;color:red">昵称已存在</span>
                </div>
            </div>
            <div class="baseCon_son">
                <div class="baseCon_son_left">
                    邮箱
                </div>
                <div class="baseCon_son_right">
                    <input type="text" id="emailtxt" value="${sessionScope.user.email }" />
                    
            <span id="msg2" style="display:none;color:red">邮箱已存在</span>
                </div>
            </div>
            <div class="chooseSex">
            <c:if test="${sessionScope.user.sex eq '男' }">
                <p><input type="radio" name="sex" value="男" checked/><label>男</label></p>
                <p><input type="radio" name="sex" value="女"/><label>女</label></p>
                </c:if>
                <c:if test="${sessionScope.user.sex eq '女' }">
                <p><input type="radio" name="sex" value="男"/><label>男</label></p>
                <p><input type="radio" name="sex" value="女" checked/><label>女</label></p>
                </c:if>
            </div>
            <input type="button" value="确认修改" class="upload_base"/>
        </div>
        
         <div class="baseCon" style="display:none">
            <div class="upImg">
            
                <p>建议尺寸168*168，支持jpg、png、gif,最大不能超过50KB</p>
                <div class="mypic">
                    <img id="userphoto" src="upload/photo/${sessionScope.user.photoName }" alt=""/>
                </div>
                <form action="alterPhoto" method="post" enctype="multipart/form-data">
                <div class="Imgbtn">打开头像
                    <input type="file" name="myFile" class="uploadPic" onChange="change(this.value)"/>
                </div><br/>
                <div class="Imgbtn">上传头像
                    <input type="submit" class="uploadPic" id="uploadphoto"/>
                </div>
                </form>
            </div>
        </div>
        
        <div class="baseCon" style="display:none">
            <div class="baseCon_son">
                <div class="baseCon_son_left">
                    当前密码
                </div>
                <div class="baseCon_son_right">
                    <input type="text" id="oldpsw"/>
                </div>
            </div>
            <div class="baseCon_son">
                <div class="baseCon_son_left">
                    新密码
                </div>
                <div class="baseCon_son_right">
                    <input type="text" id="newpsw1"/>
                </div>
            </div>
            <div class="baseCon_son">
                <div class="baseCon_son_left">
                    确认密码
                </div>
                <div class="baseCon_son_right">
                    <input type="text" id="newpsw2"/>
                </div>
            </div>
            <input type="button" value="确认修改" id="alterpsw" class="upload_sure"/>
        </div>
        </div>
    </div>
</div>
  </body>
</html>
<script src="js/jquery.min.js"></script>
<script>
	//切换选项卡
	$(".baseHead ul li").each(function(i){
		$(this).click(function(){
		
			$(this).children().addClass("on");
			$(this).siblings().children().removeClass("on");
			$(".baseCon").eq(i).fadeIn().siblings().hide();
		});
	});
	
	//修改密码
	$("#alterpsw").click(function(){
		$.post("alterPsw",{
			"psw":$("#oldpsw").val(),
			"newpsw1":$("#newpsw1").val(),
			"newpsw2":$("#newpsw2").val()
		},function(date){
			if(date.flag==0){
				alert("修改成功！");
			}
			
			if(date.flag==1){
				alert("原密码不正确");
			}
			
			if(date.flag==2){
				alert("密码框不能为空");
			}
			
			if(date.flag==3){
				alert("新密码两次输入不正确 	");
			}
			
			
			
			
		},"json");
	});
	
	//上传照片
	function change(v){
		var path = $(".uploadPic").val();
		var filename = path.substr(path.lastIndexOf("\\")+1);
		$("#userphoto").attr("src","http://localhost:8080/imgUrl/"+filename);
	}
	
	
	$("#unametxt").focus(function(){
		$("#msg1").hide();
	});
	$("#emailtxt").focus(function(){
		$("#msg2").hide();
		});
	
	$(".upload_base").click(function(){
		$.post("checkUname",{"uname":$("#unametxt").val(),"email":$("#emailtxt").val(),"sex":$("input:radio:checked").val()},function(date){
			if(date.isunameExist==1){
				$("#msg1").show();
			}
			if(date.isnull==1){
				alert("用户名或邮箱不能为空");
			}
			if(date.issucceed==1){
				location.reload();
				alert("修改成功，页面即将刷新！");
			}
			if(date.isemailExist==1){
				$("#msg2").show();
			}
			if(date.isalter==1){
				alert("您未修改任何信息");
			}
		},"json");
	});
	
</script>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cart.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="css/cart.css" type="text/css"/>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
  </head>
  
  <body>
		<h1 class="cart-title">兑换商城</h1>
		<table>
			<tr>
				<th></th>
				<th>名称</th>
				<th>兑换数量</th>
				<th>剩余数量</th>
				<th>所需积分</th>
				<th>操作</th>
			</tr>
		</table>
		<br/>
		<br/>
		 <!--按钮调用来模态框-->
	  <button class="btn btn-primary" data-toggle="modal" data-target="#myModal" style="float:right">请输入收货地址</button>
	  
	  <!--模态框结构   fade 动画效果-->
	  <div class="modal fade" id="myModal" data-backdrop="static">
	    <!--窗口层-->
	    <div class="modal-dialog modal-sm">
	      <!--内容层-->
	      <div class="modal-content">
	        <!--头部，身体， 底部-->
	        <div class="modal-header">
	          <h2 class="modal-title text-success">
	         	   请填写正确的收货地址
	            <span class="close" data-dismiss="modal">&times;</span>
	          </h2>
	          
	        </div>
	        <div class="modal-body">
            <!--表单组-->
            <div class="form-group">
              <label>收货人：</label>
              <input type="text" class="form-control" />
            </div>
            <!--表单组-->
            <div class="form-group">
              <label>手机号码：</label>
              <input type="text" class="form-control" />
            </div>
            <!--表单组-->
            <div class="form-group">
              <label>详细地址：</label>
              <input type="text" class="form-control" />
            </div>
	        </div>
	        <div class="modal-footer">
	          <button class="btn btn-success" data-dismiss="modal" onclick="fun()">确认</button>
	          <button class="btn btn-danger" data-dismiss="modal">取消</button>
	        </div>    
	      </div>
	    </div>
	  </div>
	</body>
</html>
<script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/cart.js"></script>
<script>
	function fun(){
		window.location.href="article_show.jsp";
	}
</script>

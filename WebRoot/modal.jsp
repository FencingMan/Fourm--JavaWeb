<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'modal.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>

  </head>
  
  <body>
	  <!--按钮调用来模态框-->
	  <button class="btn btn-primary" data-toggle="modal" data-target="#myModal">调用模态框</button>
	  
	  <!--模态框结构   fade 动画效果-->
	  <div class="modal fade" id="myModal" data-backdrop="static">
	    <!--窗口层-->
	    <div class="modal-dialog modal-sm">
	      <!--内容层-->
	      <div class="modal-content">
	        <!--头部，身体， 底部-->
	        <div class="modal-header">
	          <h2 class="modal-title text-success">
	            请登录
	            <span class="close" data-dismiss="modal">&times;</span>
	          </h2>
	          
	        </div>
	        <div class="modal-body">
            <!--表单组-->
            <div class="form-group">
              <label>用户名：</label>
              <input type="text" class="form-control" />
            </div>
            <!--表单组-->
            <div class="form-group">
              <label>密码：</label>
              <input type="password" class="form-control" />
            </div>
            
            <div class="row">
              <div class="col-md-8 col-md-offset-2">
                <!--输入框组-->
                <div class="input-group" style="margin-bottom: 20px;">
                  <span class="input-group-addon">用户</span>
                  <input type="text" class="form-control" />
                </div>
                <div class="input-group">
                  <span class="input-group-addon">密码</span>
                  <input type="password" class="form-control" />
                </div>
              </div>
            </div>
            
	        </div>
	        <div class="modal-footer">
	          <button class="btn btn-success">登录</button>
	          <button class="btn btn-danger" data-dismiss="modal">取消</button>
	        </div>    
	      </div>
	    </div>
	  </div>
	</body>
</html>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script>
</script>

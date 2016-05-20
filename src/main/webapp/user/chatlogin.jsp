<%@ page language="java"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登录</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<link rel="stylesheet" href="<%=path %>/user/loginregister.css" type="text/css"> 
<script type="text/javascript">
	function nologin() {
		window.location.href="<%=path %>/robot/chat";
	}
	function register() {
		window.location.href="<%=path %>/user/register/2";
	}
</script>
</head>
<body>
	<center>
		<form:form action="user/login/2" method="post">
			<div class="fram">
				<br/>
				<img class="headimg"  alt="头像" src="<%=path %>/images/head.jpg"></img>
				<br/>
				<table class="content">	
					<tr class="tr">
						<td><img class="icon" src="images/name.png"></td>
						<td><input class="input" type="text" placeholder="请输入用户名" name="userName"/></td>
					</tr>
					<tr class="tr">
						<td><img class="icon" src="images/pwd.png"></td>
						<td><input class="input" type="text" placeholder="请输入密码" name="userPwd"/></td>
					</tr>
				</table>
				<input type="submit" class="loginbtn" value=""><br/>
				<input type="button" class="otherbtn" onclick="nologin()" value="免登录">
				<input type="button" class="otherbtn" onclick="register()" value="注册">
			</div>
		</form:form>
	</center>
</body>
</html>
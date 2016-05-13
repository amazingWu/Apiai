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
<style type="text/css">
	.input{
		width: 14em;
		height: 1.5em;
		
	}
	.h1{
		font-size: 2em;
		padding-top: 2em;
	}
	body {
		background-color:gray;
	}
}
}
</style>
<script type="text/javascript">
	function nologin() {
		window.location.href="<%=path %>/robot/robot";
	}
	function register() {
		window.location.href="<%=path %>/user/register";
	}
</script>
</head>
<body>
	<center>
		<h1 class="h1">登录</h1>
		<form:form action="user/login" method="post">
			<div style="height: 20em;background-color: #FADAFF">
			<div style="height: 5em;"></div>
				<tr>
				<td><label>用户名：</label></td>
				<td><input class="input" type="text" name="userName"/></td><br/>
			</tr><br/> 
			<tr>
				<td><label>密&nbsp;码:&nbsp;</label></td>
				<td><input class="input" type="text" name="userPwd"/></td><br/>
			</tr><br/> 
			<tr>
				<td colspan="3"><input type="submit" value="登录"></td>
				<td colspan="3"><input type="button" onclick="nologin()" value="免登录"></td>
				<td colspan="3"><input type="button" onclick="register()" value="注册"></td>
			</tr>
			</div>
		</form:form>
	</center>
</body>
</html>
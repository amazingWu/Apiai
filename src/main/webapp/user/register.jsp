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
<title>用户信息注册页面</title>
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
</style>
<script type="text/javascript">
	function login() {
		window.location.href="<%=path%>/user/login"
	}
</script>
</head>
<body>
	<center>
		<h1 class="h1">注册</h1>
		<form:form action="user/register" method="post" modelAttribute="robotUser">
			<div style="height: 20em;background-color: #FADAFF">
			<div style="height: 5em;"></div>
				<tr>
				<td><label>用户名：</label></td>
				<td><input class="input" type="text" name="userName"/></td><br/>
				<td><form:errors path="userName"/></td><br/>
			</tr><br/> 
			<tr>
				<td><label>密&nbsp;码:&nbsp;</label></td>
				<td><input class="input" type="text" name="userPwd"/></td><br/>
				<td><form:errors path="userPwd"/></td><br/>
			</tr><br/> 
			<tr>
				<td>邮&nbsp;箱:&nbsp;</td>
				<td><input class="input" type="text" name="userEmail"/></td><br/>
				<td><form:errors path="userEmail"/></td><br/>
			</tr><br/>
			<tr>
				<td colspan="3"><input type="submit" value="提交"></td>
				<td colspan="3"><input type="reset" value="重置"></td>
				<td colspan="3"><input type="button" onclick="login()" value="已有账号"></td>
			</tr>
			</div>
		</form:form>
	</center>
</body>
</html>
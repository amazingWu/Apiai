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
<link rel="stylesheet" href="<%=path %>/user/loginregister.css" type="text/css">
<script type="text/javascript">
	function login() {
		window.location.href="<%=path%>/user/chatlogin.jsp"
	}
</script>
</head>
<body>
	<center>
		
		<form:form action="user/register/2" method="post" modelAttribute="robotUser">
			<div class="fram">
				<table class="content">	
					<br/>
					<br/>
					<tr class="tr">
						<td><img class="icon" src="images/name.png"></td>
						<td><input class="input" type="text" placeholder="请输入用户名"  name="userName"/></td>
						<td><form:errors path="userName"/></td>
					<tr>
					<tr class="tr">
						<td><img class="icon" src="images/pwd.png"></td>
						<td><input class="input" type="text" placeholder="请输入密码" name="userPwd"/></td>
						<td><form:errors path="userPwd"/></td>
					</tr>
					<tr class="tr">
						<td><img class="icon" src="images/email.png"></td>
						<td><input class="input" type="text" placeholder="请输入邮箱"  name="userEmail"/></td>
						<td><form:errors path="userEmail"/></td>
					</tr>
				</table>
				<input type="submit" class="registerbtn" value=""/>
				<br/>
				<input type="reset" class="otherbtn"  value="重置"/>
				<input type="button" class="otherbtn"  onclick="login()" value="已有账号"/>
			</div>
		</form:form>
	</center>
</body>
</html>
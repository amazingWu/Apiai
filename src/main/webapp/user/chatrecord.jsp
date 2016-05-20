<%@page import="com.robot.example.entity.Record"%>
<%@page import="java.util.List"%>
<%@ page language="java"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>记录浏览</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<style type="text/css">
	ul,ol {list-style: none;height: 90%;} 
	p{
		margin: 0;
	}
	body {
	  padding: 0;
	  margin: 0;
	  background-color:rgba(229,229,229,1);
	  word-break:break-all;
	}
	li{
		text-align: left;
		display: block;
		border-bottom: 1px solid #000;
		margin-left: -2em;
		padding-top: 0.3em;
	}
	.content{
	font-size: 1.2em;
	}
	.time{
		font-size: 0.6em;
	}
	center {
		height: 200%;
	}
	.middle{
		height: 90%;
	}
	.bottom{
        margin-bottom:1em;
	}
	input {
		font-size: 1.2em;
	}
	.top{
			height: 2.5em;
			background-color:gray;
	}
	.title{padding-top: 0.7em; font-weight: bold;}
	.record{float: left;margin-top: -1em;}
	a { text-decoration: none; color: white;padding: 3px;background-color: #969595;} 
	a:hover { background-color: white;} 
}
</style>
<script type="text/javascript">
	function pre(start,offset){
		if(start>=offset){
			window.location.href="<%=path%>/robot/record/"+(start-offset)+"/"+offset+"/"+2;
		}else{
			window.location.href="<%=path%>/robot/record/0/"+offset+"/"+2;
		}
	}
	function next(num,start,offset){
		if(num<offset){
			
		}else{
			window.location.href="<%=path%>/robot/record/"+(start+offset)+"/"+offset+"/"+2;
		}
	}
</script>
</head>
<body>
	<center>
		<center class="top">
			<p class="title">智能机器人</p>
			<a class="record" href="<%=path%>/robot/chat">主页</a>
		</center>
		<div class="middle">
			<ul>
				<c:forEach items="${records }" var="record">
						<li>
							<p class="content">${record.content}</p>
							<p class="time">${record.postTime}</p>				
						</li>
						
				</c:forEach>
			</ul>
		</div>
		<div class="bottom">
			<%List<Record> list=(List<Record>)request.getAttribute("records");
				int num=list.size();
				int start=Integer.parseInt(request.getAttribute("start").toString());
				int offset=Integer.parseInt(request.getAttribute("offset").toString());
				if(start>0){%>
					<input type="button" onclick="pre(<%=start %>,<%=offset %>)" value="上一页"/>
				<%}
				if(offset>num){
					
				}else{%>
					<input type="button" onclick="next(<%=num %>,<%=start %>,<%=offset %>)" value="下一页"/>
				<%}
			%>
			
		</div>
	</center>
</body>
</html>
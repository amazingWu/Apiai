<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.robot.example.entity.model.ChatViewModel" %>
<%String path=request.getContextPath(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="UTF-8">
    <title>智能机器人</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/chatstyle.css" type="text/css"> 
    <meta name="viewport" content="width=device-width,initian-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <!-- 使用内部样式覆盖外部样式，如果用户已经登陆就显示用户头像 -->
     <style type="text/css">
     	a { text-decoration: none; color: white;padding: 3px;background-color: #969595;} 
		a:hover { background-color: white;} 
    	.chat-thread li[role="me"]:before {
			right: -60px;
			/* 用户头像 */
			<%try{
				String url=path+"/"+request.getAttribute("icon").toString();%>	
				background: url(<%=url%>);
				-moz-background-size:50px 50px; /* 老版本的 Firefox */
				background-size:50px 50px;
				background-repeat:no-repeat;
			<%}catch(NullPointerException e){
			}%>
		}
		.top{
			height: 2.5em;
			background-color:gray;
		}
		.title{padding-top: 0.7em;    font-weight: bold;}
		
		<%try{
			String name=request.getSession().getAttribute("userName").toString();
			if(name!=null){%>
				.login{display:none;}
				.record{float: right;margin-top: -2.2em;}
				.out{float: left;margin-top: -2.2em;}
			<%}else{%>
				.record,.out{display:none;}
				.login{float: right;margin-top: -2.2em;}
			<%}
		}catch(Exception ee){%>
			.record,.out{display:none;}
			.login{float: right;margin-top: -2.2em;}
		<%}%>
    </style>
    <script type="text/javascript">
    	//隐藏输入框
    	function sendhide(){
    		var send_div=document.getElementById("sent_div");
    		send_div.setAttribute("style", "display:none;")
    	}
    	function keydown(){
    		if (event.keyCode == 13)
    		  {
    		    myfunction();
    		  }
    	}
    	//发送事件
        function myfunction(){
                var message = document.getElementById("sentinput").value;
                ajaxRequest(message);
                //重置输入框
                document.getElementById("sentinput").value="";
        };
        
    	//javascript ajax
        var xmlHttpRequest = null;
        //js中的变量名应避免使用和html元素相同
        function ajaxRequest(sendtext) {
            if (window.ActiveXObject) // IE浏览器
            {
                xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
            }
            else if (window.XMLHttpRequest) // 除IE以外的其他浏览器
            {
                xmlHttpRequest = new XMLHttpRequest();
            }
            if (null != xmlHttpRequest && sendtext != null && sendtext != "") {
                // 准备向服务器发出一个请求
                /*
                 * GET方式向服务器发出一个请求
                 * xmlHttpRequest.open("GET", "AjaxServlet?sentinput=" + sentinput, true);
                 */
                /*
                 * POST方式向服务器发出一个请求
                 */
                xmlHttpRequest.open("POST", "RobotAjaxBack", true);
                 
                // 当发生状态变化时就调用这个回调函数
                xmlHttpRequest.onreadystatechange = ajaxCallBack;
                // 使用post提交时必须加上下面这行代码
                xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                // 向服务器发出一个请求
                xmlHttpRequest.send("sendinput="+sendtext);
                //将用户在输入框中输入的内容的添加进网页中,ajax请求完成后将输入框内容清空
                var li = document.createElement("li");
                li.setAttribute("role", "me");
                var text = document.createTextNode(sendtext);
                li.appendChild(text);
                document.getElementById("chat_ul").appendChild(li);
                //滚动窗口
                scroll_bottom();

            }
        }
        //javascript ajax回调函数
        //将返回信息包裹成如下节点，并添加到chat_url节点中
        //<li role="you">内容</li>
        function ajaxCallBack() {
            
        	//alert("fanhui :");
        	//4：ajax方法的成功执行
            if (xmlHttpRequest.readyState == 4) {
                //创建li节点
                var li = document.createElement("li");
                li.setAttribute("role", "you");
                //按照返回的状态码进行判断并作出相应的操作
                //200表示服务成功接收消息，并成功返回结果（见：w3cSchool）
                if (xmlHttpRequest.status == 200) {//200表成功
                    var content = xmlHttpRequest.responseText;
                    //文本添加进li结点中去
                    li.innerHTML = content;
                }
                else {
                    var content = xmlHttpRequest.responseText;
                    //文本添加进li结点中去
                    var text = document.createTextNode("与服务器连接出错了！");
                    li.appendChild(text);
                }
                //将li添加进chat_url
                document.getElementById("chat_ul").appendChild(li);
                //滚动窗口
                scroll_bottom();
            }
        }

        ////jquery ajax 手机浏览器很多不支持，因此采用原生的javascript
        //function Ajax() {
        //    var sentinput = $("#sentinput").val();
        //    $.ajax({
        //        type: "POST",
        //        url: "/Chat/AjaxBack",
        //        //提交的数据
        //        data: { sentinput},
        //        dataType: "text",
        //        async: false,
        //        beforeSend: function ajax_begin() {
        //            var message = $("#sentinput").val();
        //            $("#sentinput").val("");
        //            $("#chat_ul").append("<li role='me'>" + message + "</li>");
        //            scroll_bottom();
        //        },
        //        success: function ajax_suc(msg) {
        //            $("#chat_ul").append("<li role='you'>"+msg+"</li>");
        //            scroll_bottom();
        //        },
        //        complete: function ajax_complete() {
        //        },
        //        error: function ajax_err(msg) {
        //            $("#chat_ul").append("<li role='you'>服务链接失败了-.-</li>");
        //            scroll_bottom();
        //        },
        //    });
        //}
        
        //滚屏到屏幕最低端
        function scroll_bottom() {
            document.getElementById("msg_end").scrollIntoView();
        }
    </script>
</head>
<body>
	<center class="top">
		<p class="title">智能机器人</p>
		<a class="record" href="<%=path%>/robot/record/0/20/1">聊天记录</a>
		<a class="login" href="<%=path%>/user/login.jsp">登录</a>
		<a class="out" href="<%=path%>/user/out/1">退出</a>
	</center>
    <div id="convo">
        <ul id="chat_ul" class="chat-thread">
          <li role="${requestScope.model.getRole()}">${requestScope.model.getContent()}</li>
        </ul>
    </div>
    <div class="msg_end" id="msg_end"></div>
    <div id="sent_div">
         <input type="text" id="sentinput" name="sentinput" class="sent_input"  onkeydown="keydown()" x-webkit-speech="x-webkit-speech"/>
         <input type="submit" id="sent_btn" class="sent_btn" value="发送" onclick="myfunction()"/>
    </div>
    </body>

</html>
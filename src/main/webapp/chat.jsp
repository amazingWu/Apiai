<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.robot.example.entity.model.ChatViewModel" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="UTF-8">
    <title>智能机器人</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/chatstyle.css" type="text/css"> 
    <meta name="viewport" content="width=device-width,initian-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <script type="text/javascript">
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
                //将输入框中的添加进网页中,ajax请求完成后将输入框内容清空
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
            if (xmlHttpRequest.readyState == 4) {
                //创建li节点
                var li = document.createElement("li");
                li.setAttribute("role", "you");
                //按照返回的状态码进行判断并作出相应的操作
                if (xmlHttpRequest.status == 200) {//200表成功
                    var content = xmlHttpRequest.responseText;
                    //创建文本，并添加进li结点中去
                    li.innerHTML = content;
                }
                else {
                    var content = xmlHttpRequest.responseText;
                    //创建文本，并添加进li结点中去
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
        
        //滚屏
        function scroll_bottom() {
            document.getElementById("msg_end").scrollIntoView();
        }
    </script>
</head>
<body>
    <div id="convo">
        <ul id="chat_ul" class="chat-thread">
          <li role="${requestScope.model.getRole()}">${requestScope.model.getContent()}</li>
        </ul>
    </div>
    <div class="msg_end" id="msg_end"></div>
    </body>

</html>
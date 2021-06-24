<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="ctxpath"
    value="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=UTF-8">
<title>登录测试</title>
</head>
<body>
    <h2>Hello World!</h2>
    <div>
        <span>sessionId:</span>
        <% 
            HttpSession s= request.getSession(); 
            out.println(s.getId());
        %>
    </div>
    
    <input id="sessionId" type="hidden" value="<%=session.getId() %>" />
    <input id="text" type="text" />
    <button onclick="send()">发送消息</button>
    <hr />
    <button onclick="closeWebSocket()">关闭WebSocket连接</button>
    <hr />
    <div id="message"></div>
</body>

<script type="text/javascript" src="https://cdn.bootcss.com/sockjs-client/1.4.0/sockjs.min.js"></script> 
<script type="text/javascript">  
        var websocket = null;  
        if('WebSocket' in window) {
        	// 启动：第1步
            websocket = new WebSocket("ws://localhost/webSocketByTomcat/"+document.getElementById('sessionId').value);  
        } else if('MozWebSocket' in window) {
        	alert("2");
            websocket = new MozWebSocket("ws://localhost/webSocketByTomcat/"+document.getElementById('sessionId').value);
        } else {
        	alert("3");
            websocket = new SockJS("localhost/webSocketByTomcat/"+document.getElementById('sessionId').value);
        }
      
        //连接发生错误的回调方法  
        websocket.onerror = function () {  
        	alert("4");
            setMessageInnerHTML("WebSocket连接发生错误");  
        };  
      
        //连接成功建立的回调方法  
        websocket.onopen = function () {  
        	// 启动：第4步
            setMessageInnerHTML("WebSocket连接成功");  
        }  
      
        //接收到消息的回调方法  
        websocket.onmessage = function (event) {  
        	// 发送：第4步
            setMessageInnerHTML(event.data);
        }  
      
        //连接关闭的回调方法  
        websocket.onclose = function () {  
        	// 关闭：第4步
            setMessageInnerHTML("WebSocket连接关闭");  
        }  
      
        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。  
        window.onbeforeunload = function () {  
        	alert("8");
            closeWebSocket();  
        }  
      
        //将消息显示在网页上  
        function setMessageInnerHTML(innerHTML) {  
        	// 启动：第5步
        	// 发送：第5步
            document.getElementById('message').innerHTML += innerHTML + '<br/>';  
        }  
      
        //关闭WebSocket连接  
        function closeWebSocket() {  
        	// 关闭：第1步
            websocket.close();  
        }  
      
        //发送消息  
        function send() {  
        	// 发送：第1步
            var message = document.getElementById('text').value;  
        	var data = {
        			to : "6A31942C71DEA49E703B934AA0062D19",
        			msg: message
        	}
            websocket.send(JSON.stringify(data));  
        }  
    </script>
</html>
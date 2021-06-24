<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String ligent_itemName = request.getContextPath() + "/";
 	String ligent_filePath = ligent_itemName + "document/";
	String ligent_unitPath = ligent_itemName + "ligentres/";
	String ligent_uploadPath = ligent_itemName + "upload/";
	String ligent_basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + ligent_unitPath;
	String ligent_readPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8" />
    <title>工具管理系统</title>
    <meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<meta name="keywords" content="MrAdonis" />
	<meta name="description" content="MrAdonis" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="<%=ligent_unitPath%>img/favicon.png" type="image/x-icon">
	
    <!-- 基本样式 -->
	<link rel="stylesheet" href="<%=ligent_unitPath%>layui/css/layui.css" media="all">
	<link rel="stylesheet" href="<%=ligent_unitPath%>style/admin.css" media="all">
	<link rel="stylesheet" href="<%=ligent_unitPath%>style/login.css" media="all">
	<style type="text/css">
		 .bgimg{
		    position:fixed;
		    top: 0;
		    left: 0;
		    width:100%;
		    height:100%;
		    z-index:-10;
		    zoom: 1;
		    background-color: #fff;
		    background: url("<%=ligent_unitPath%>img/login/background.png");
		    background-size: cover;
		    -webkit-background-size: cover;
		    -o-background-size: cover;
		    background-position: center 0;
		}
		.layadmin-user-login-box{
			padding: 20px 30px;
		}
		@media screen and (max-width: 1400px) {
			.login-body-box{
				width: 1000px;
				margin: auto;
				position: relative;
				height: 500px;
			}
		}
		@media screen and (max-width: 1200px) {
			.login-body-box{
				width: 920px;
				margin: auto;
				position: relative;
				height: 500px;
			}
		}
		@media screen and (min-width: 1400px) {
			.login-body-box{
				width: 1200px;
				margin: auto;
				position: relative;
				height: 500px;
			}
		}
	</style>
</head>

<body class="bgimg">
    
	<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="padding:0;top:50%;margin-top:-270px;display:none;">
		
		<div class="login-body-box">
			<div style="float:left;width:50%;height:100%;">
				<img alt="" src="<%=ligent_unitPath%>img/login/marker.png" style="width:550px;">
			</div>
			<div style="float:right;width:50%;height:100%;">
				<div class="layadmin-user-login-main" style="background-color:#FFFFFF;margin:0;float:right;">
					<div class="layadmin-user-login-box layadmin-user-login-header" style="padding-bottom:0px;padding-top:30px;">
						<img alt="" src="<%=ligent_unitPath%>img/login/logo.png" style="height:70px;">
					</div>
					<div class="layadmin-user-login-box layadmin-user-login-header">
						<input id="errorCode" type="hidden" value="${errorCode }" />
						<input id="isDiringCode" type="hidden" value="${isDiringCode }" />
						<h2 style="font-size:26px;font-weight:500;">文件管理系统</h2>
						<!-- <p>在线聊天系统</p> -->
					</div>
					<div class="layadmin-user-login-box layadmin-user-login-body layui-form" style="padding-top:0px;">
						<div class="layui-form-item">
							<label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-cellphone"></label>
							<input id="LAY-user-cellphone" name="account" placeholder="用户名" type="text" lay-verify="required" class="layui-input layui-input-md">
						</div>
						<div class="layui-form-item">
							<label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-password"></label>
							<input id="LAY-user-password" name="password" placeholder="密码" type="password" lay-verify="required" class="layui-input layui-input-md">
						</div>
						<c:choose>
							<c:when test="${isDiringCode==1 }">
								<div class="layui-form-item">
									<div class="layui-row">
										<div class="layui-col-xs7">
											<label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-vercode"></label>
											<input id="LAY-user-vercode" name="vercode" type="text" lay-verify="required" placeholder="图形验证码" class="layui-input layui-input-md">
										</div>
										<div class="layui-col-xs5">
											<div style="margin-left: 10px;">
												<img src="<%=ligent_itemName%>main/captcha" class="layadmin-user-login-codeimg" id="LAY-user-get-vercode">
											</div>
										</div>
									</div>
								</div>
							</c:when>
						</c:choose>
						<div class="layui-form-item" style="margin-bottom: 20px;">
							<input type="checkbox" name="remember" lay-skin="primary" title="记住密码">
							<a href="javascript:;" class="layadmin-user-jump-change layadmin-link LAY-user-forget" style="margin-top: 7px;">忘记密码？</a>
						</div>
						<div class="layui-form-item" style="margin-bottom: 50px;">
							<button id="LAY-user-submit" class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit">登 入</button>
							<a href="javascript:;" class="layadmin-user-jump-change layadmin-link LAY-user-reg" style="margin-top: 7px;">在线注册</a>
						</div>
						
						<!-- <div class="layui-trans layui-form-item layadmin-user-login-other">
							<a href="javascript:;" class="layadmin-user-jump-change layadmin-link LAY-user-reg">注册帐号</a>
						</div> -->
					</div>
				</div>
			</div>
		</div>

	</div>
		
    <div class="layui-trans layadmin-user-login-footer">
		<p>© 2028
			<a href="http://www.layui.com/" target="_blank">layui.com</a>
		</p>
		<p>
			<span><a href="http://www.layui.com/admin/#get" target="_blank">获取授权</a></span>
			<span><a href="http://www.layui.com/admin/pro/" target="_blank">在线演示</a></span>
			<span><a href="http://www.layui.com/admin/" target="_blank">前往官网</a></span>
		</p>
	</div>
    
	<script>
		var ligent_itemName = "<%=ligent_itemName%>";
		var ligent_filePath = "<%=ligent_filePath%>";
		var ligent_unitPath = "<%=ligent_unitPath%>";
		var ligent_uploadPath = "<%=ligent_uploadPath%>";
		var ligent_basePath = "<%=ligent_basePath%>";
		var ligent_readPath = "<%=ligent_readPath%>";
	</script>
	
    <!--Basic Scripts-->
    <script src="<%=ligent_unitPath%>js/jquery-1.10.2.min.js"></script>
    <!--Layer-->
	<script src="<%=ligent_unitPath%>layui/layui.js"></script>
	
    <!--扩展控件-->
	<script src="<%=ligent_unitPath%>adonis/comm/tool.js?v=<%=System.currentTimeMillis() %>"></script>
	<script src="<%=ligent_unitPath%>adonis/comm/layer.js?v=<%=System.currentTimeMillis() %>"></script>
    
    <script src="<%=ligent_unitPath%>adonis/auth/account/login.js?v=<%=System.currentTimeMillis() %>"></script>
    
</body>
</html>
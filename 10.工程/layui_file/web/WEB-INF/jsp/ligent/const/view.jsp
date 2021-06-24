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
    <title>小升后台管理系统</title>
    <meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<meta name="keywords" content="小升后台管理系统" />
	<meta name="description" content="小升后台管理系统" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="<%=ligent_unitPath%>img/favicon.png" type="image/x-icon">
	
    <!-- 基本样式 -->
	<link rel="stylesheet" href="<%=ligent_unitPath%>layui/css/layui.css" media="all">
	<link rel="stylesheet" href="<%=ligent_unitPath%>style/admin.css" media="all">
	<!-- 扩展图标 -->
	<link rel="stylesheet" href="<%=ligent_unitPath%>style/iconfont/iconfont.css" media="all">
	<!-- 扩展样式 -->
	<link href="<%=ligent_unitPath%>style/style.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet"/>
	<style type="text/css">
		.layui-btn-group{
			padding-left: 18px;
		}
		.layui-form{
			padding: 18px;
		}
	</style>
</head>

<body>
	
	<div class="layui-fluid">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">
						<span class="childHeader"><!-- 页面加载完毕后自动生成 --></span>
						<span class="childTitle"></span>
					</div>
					<div class="layui-card-body">
						<div class="layui-form">
			              	<div class="layui-card-body layui-row layui-col-space10">
		              			<!-- <div style="margin-bottom: 5px;">
		              				<span style="margin-left: 50px; ">为了避免遗忘，请您填写真实信息，这将帮助您以后通过回答问题快速找回密码。</span>
		              			</div> -->
			              		<form class="layui-form" id="MyForm" style="width: 520px;">
			              			<input id="constKey" name="constKey" value="${constKey }" type="hidden">
			              			<div class="layui-form-item">
					              		<label class="layui-form-label" style="width: 200px;">是否启动登录页面验证码：</label>
					             		<div class="layui-input-block">
											<input name="isDiringCode" lay-filter="isDiringCode" value="1" type="radio" title="启动" <c:if test="${isDiringCode==1 }">checked="checked"</c:if>><div class="layui-unselect layui-form-radio"><i class="layui-anim layui-icon"></i><div>启动</div></div>
											<input name="isDiringCode" lay-filter="isDiringCode" value="0" type="radio" title="不启动" <c:if test="${isDiringCode==0 }">checked="checked"</c:if>><div class="layui-unselect layui-form-radio"><i class="layui-anim layui-icon"></i><div>不启动</div></div>
					              		</div>
					            	</div>
								</form>
			              	</div>
		            	</div>
					</div>
				</div>
			</div>
		</div>
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
	<script src="<%=ligent_unitPath%>adonis/comm/date.js?v=<%=System.currentTimeMillis() %>"></script>
	<script src="<%=ligent_unitPath%>adonis/comm/tool.js?v=<%=System.currentTimeMillis() %>"></script>
	<script src="<%=ligent_unitPath%>adonis/comm/util.js?v=<%=System.currentTimeMillis() %>"></script>
	<script src="<%=ligent_unitPath%>adonis/comm/layer.js?v=<%=System.currentTimeMillis() %>"></script>
    
    <script src="<%=ligent_unitPath%>adonis/ligent/const/view.js?v=<%=System.currentTimeMillis() %>"></script>
    
</body>
</html>
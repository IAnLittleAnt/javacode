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
</head>

<body class="layui-layout-body">
	
	
	<script>
		var ligent_itemName = "<%=ligent_itemName%>";
		var ligent_filePath = "<%=ligent_filePath%>";
		var ligent_unitPath = "<%=ligent_unitPath%>";
		var ligent_uploadPath = "<%=ligent_uploadPath%>";
		var ligent_basePath = "<%=ligent_basePath%>";
		var ligent_readPath = "<%=ligent_readPath%>";
		var isCloseLoading = false;
	</script>
	
	<!--Basic Scripts-->
    <script src="<%=ligent_unitPath%>js/jquery-1.10.2.min.js"></script>
	<!--Layer-->
	<script src="<%=ligent_unitPath%>layui/layui.js"></script>
	
    <script src="<%=ligent_unitPath%>adonis/mobile/index.js?v=<%=System.currentTimeMillis() %>"></script>
    
</body>

</html>
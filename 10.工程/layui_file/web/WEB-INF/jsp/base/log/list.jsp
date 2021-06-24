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
	<meta name="keywords" content="工具管理系统" />
	<meta name="description" content="工具管理系统" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="<%=ligent_unitPath%>img/favicon.png" type="image/x-icon">
	
    <!-- 基本样式 -->
	<link rel="stylesheet" href="<%=ligent_unitPath%>layui/css/layui.css" media="all">
	<link rel="stylesheet" href="<%=ligent_unitPath%>style/admin.css" media="all">
	<!-- 下拉树形菜单 -->
	<link href="<%=ligent_unitPath%>expand/ztree/css/cyType.css" rel="stylesheet"/>
	<link href="<%=ligent_unitPath%>expand/ztree/css/zTreeStyle.css" rel="stylesheet"/>
	<!-- 扩展样式 -->
	<link href="<%=ligent_unitPath%>style/style.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet"/>
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
						
						<div class="table-operate-btn layui-form" style="margin-bottom: 10px;">
							搜索：
							<div class="layui-inline">
								<input id="Lay-input-key" placeholder="输入关键字搜索..." class="layui-input" autocomplete="off">
							</div>
							<div class="layui-inline" style="width: 200px;">
								<input id="timeFrame" value="${timeFrame }" type="text" class="layui-input" placeholder=" - " style="width: 200px;">
							</div>
							<button data-type="search" class="layui-btn layui-btn-sm Lay-button-search layui-btn-event"><i class="layui-icon layui-icon-search"></i>搜索</button>
						</div>
						<table class="layui-hide" id="table-page" lay-filter="table-filter"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
    <!-- 开始 表格头部工具栏 -->
	<script type="text/html" id="toolbarDemo">
  		<div class="layui-btn-container">
			<c:forEach var="item" items="${actionList }" varStatus="i">
				<c:choose>
					<c:when test="${item.actionSeat == 1 }">
						<button ${item.actionEvent } class="layui-btn layui-btn-primary layui-btn-sm" ><i class="layui-icon ${item.actionIcon }"></i>${item.actionName }</button>
					</c:when>
				</c:choose>
			</c:forEach>
			<button lay-event="reset" class="layui-btn layui-btn-primary layui-btn-sm"><i class="layui-icon layui-icon-refresh-1"></i>刷新</button>
  		</div>
	</script>
    <!-- 开始 表格头部工具栏 -->
    
    <!-- 开始 表格行工具 -->
    <div id="barTool">
    	<c:forEach var="item" items="${actionList }" varStatus="i">
			<c:choose>
				<c:when test="${item.actionSeat == 2 }">
    				<a class="layui-btn layui-btn-primary layui-btn-xs" ${item.actionEvent }><i class='layui-icon ${item.actionIcon }'></i>${item.actionName }</a>
				</c:when>
			</c:choose>
		</c:forEach>
    </div>
	<!-- 结束 表格行工具 -->
	
    
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
	
	<!--下拉树形菜单-->
	<script src="<%=ligent_unitPath%>expand/ztree/js/jquery.ztree.all-3.5.min.js"></script>
	<script src="<%=ligent_unitPath%>expand/ztree/js/dropDownTool.js"></script>
	
	<!--扩展控件-->
	<script src="<%=ligent_unitPath%>adonis/comm/tool.js?v=<%=System.currentTimeMillis() %>"></script>
	<script src="<%=ligent_unitPath%>adonis/comm/util.js?v=<%=System.currentTimeMillis() %>"></script>
	<script src="<%=ligent_unitPath%>adonis/comm/layer.js?v=<%=System.currentTimeMillis() %>"></script>
    
    <script src="<%=ligent_unitPath%>adonis/base/log/list.js?v=<%=System.currentTimeMillis() %>"></script>
	<script src="<%=ligent_unitPath%>adonis/comm/list.js?v=<%=System.currentTimeMillis() %>"></script>
    
</body>
</html>
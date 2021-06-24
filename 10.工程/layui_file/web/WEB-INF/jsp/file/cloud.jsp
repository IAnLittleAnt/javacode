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
	<!-- 扩展样式 -->
	<link href="<%=ligent_unitPath%>style/style.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet"/>
	<!--右键菜单控件-->
	<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" rel="stylesheet">
	<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=ligent_unitPath%>expand/filecloud/css/tether.min.css" />
	<style type="text/css">
		/* 开始 主体样式 */
		.layui-card-body {
			margin: 0px;
			padding: 20px 10px;
			display: block;
			overflow-x: hidden;
			overflow-y: hidden;
			scrollbar-color: transparent transparent;
			scrollbar-track-color: transparent;
			-ms-scrollbar-track-color: transparent;
		}
		#fileCloud{
			width: 100%;
			height: 100%;
			overflow-x: hidden; 
			overflow-y: auto;
		}
		#docuList {
			padding-left: 0;
			list-style: none;
			scrollbar-color: transparent transparent;
			margin-top: 0;
			margin-bottom: 10px;
		}
		#docuList li {
			padding: 0px;
			width: 10%;
			font-size: 12px;
			float: left;
			height: 115px;
			line-height: 1.4;
			text-align: center;
		}
		.docu{
			cursor: pointer;
	        border: 1px solid #FFF;
			margin: 0px auto;
			width: 100px;
			height: 100%;
		}
		.docu img{
			margin-top: 10px;
			height: 60px;
			width: 60px;
			vertical-align: middle;
			border: 0;
		}
		.docu:hover{
	        border: 1px solid rgba(221, 221, 221, .8);
	        background-color: rgba(255, 255, 255, .1);
		}
		.docuName{
			padding: 0 10px;
			text-align: center;
			word-wrap: break-word;
			margin-top: 8px;
			/* 超出分布省略号代替 */
			overflow: hidden;
			text-overflow: ellipsis; 
			display: -webkit-box;
			-webkit-line-clamp: 2;
			-webkit-box-orient: vertical;
		}
		.pitchon{
	        border: 1px solid rgba(221, 221, 221, .8);
	        background-color: rgba(255, 255, 255, .1);
	        color: #e28431;
		}
		/* 结束 主体样式 */
		
		/* 开始 面包屑 */
		.crumbs{
			margin-left: 30px; 
			margin-bottom: 20px;
			line-height: 24px;
			font: 14px Arial,微软雅黑,宋体,新宋体,sans-serif;
			color: #3F3F3F;
		}
		.crumbs img{
			margin-right: 3px;
		}
		#docuCrumbs img{
			margin-left: 7px;
		}
		.crumb{
			color: #37f;
			cursor: pointer;
		}
		/* 结束 面包屑 */
		
		
		/* 开始 右键菜单 */
		#fileCloud {
			text-align: center;
		}
		ul li a .fa{
			width: 18px;
		}
		.context-menu.dropdown-menu {
			display: block;
			left: 0px;
			opacity: 0;
			position: absolute;
			top: 0px;
			transition: visibility 0s 0.1s, opacity 0.1s linear;
			visibility: hidden;
		}
		.context-menu.dropdown-menu.open {
			visibility: visible;
			opacity: 1;
			transition: opacity 0.1s linear;
		}
		.context-menu.dropdown-menu a {
			cursor: pointer;
		}
		.dropdown-submenu .dropdown-toggle:after {
			content: "\f0da";
			display: inline-block;
			float: right;
			font: normal normal normal 14px/1 FontAwesome;
			font-size: inherit;
			padding-top: 3px;
			text-rendering: auto;
			-webkit-font-smoothing: antialiased;
		}
		.dropdown-submenu .dropdown-menu {
			top: 0;
			left: 100%;
		}
		#cnxt-cursor {
			height: 0px;
			opacity: 0;
			position: absolute;
			visibility: hidden;
			width: 0px;
		}
		/* 结束 右键菜单 */
	</style>
</head>

<body style="background-color: #f2f2f2;overflow-y:hidden;">
	
	<div class="layui-fluid" style="padding-top: 15px;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">
						<span class="childHeader"><!-- 页面加载完毕后自动生成 --></span>
						<span class="childTitle"></span>
					</div>
					<div class="layui-card-body">
						<div class="crumbs">位置：
							<input id="docuUse" value="${docuUse }" type="hidden" />
							<input id="folderTypeId" value="${folder.typeId }" type="hidden" />
							<input id="folderTypeIcong" value="${folder.typeIcong }" type="hidden" />
							<input id="folderTypeIcon" value="${folder.typeIcon }" type="hidden" />
							<img src="${folder.typeIcon }" border="0" align="absmiddle"><span docuId="0" typeId="${folder.typeId }" class="docu docuFolder crumb">根目录</span><span id="docuCrumbs"></span>
							<span style="float:right;margin-right:50px;color:#ccc;">当前排序：按<span id="sortModeName">类型</span>排序</span>
						</div>
						<div id="fileCloud">
							<ul id="docuList">
	           					<%-- <c:forEach var="item" items="${docuList }" varStatus="i">
		           					<li>
										<div class="docu <c:choose><c:when test="${item.typeId==1 }">docuFolder</c:when><c:otherwise>docuFile</c:otherwise></c:choose>" docuId="${item.docuId }" typeId="${item.typeId }">
											<img src="${item.typeIcong }" />
											<span class="docuName" style="display:-webkit-box;">${item.docuName }</span>
										</div>
									</li>
								</c:forEach> --%>
	           				</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
    
    <!-- 开始 TABLE按钮工具 -->
    <div id="barTool">
    	<c:forEach var="item" items="${actionList }" varStatus="i">
			<c:choose>
				<c:when test="${item.actionSeat == 2 }">
    				<a class="layui-btn layui-btn-primary layui-btn-xs" ${item.actionEvent }><i class='layui-icon ${item.actionIcon }'></i>${item.actionName }</a>
				</c:when>
			</c:choose>
		</c:forEach>
    </div>
	<!-- 结束 TABLE按钮工具 -->
	
	<!-- 开始 编辑 -->
	<div id="Lay-modal-folder" class="layui-fluid layui-modal">
		<div class="layui-row">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-body">
						<div class="layui-form" lay-filter="Lay-modal-edit">
							<div class="layui-row layui-col-space10 layui-form-item">
								<div class="layui-col-lg12">
									<textarea name="content" placeholder="新建文件夹" class="layui-textarea"></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 结束 编辑 -->
	
	<script>
		var ligent_itemName = "<%=ligent_itemName%>";
		var ligent_filePath = "<%=ligent_filePath%>";
		var ligent_unitPath = "<%=ligent_unitPath%>";
		var ligent_uploadPath = "<%=ligent_uploadPath%>";
		var ligent_basePath = "<%=ligent_basePath%>";
		var ligent_readPath = "<%=ligent_readPath%>";
		/* 获取右键元素自定义参数 */
		var rightClickElement = {
				"docuid" : 0,
				"typeid" : 0
			}
	</script>
	
    <!--Basic Scripts-->
    <script src="<%=ligent_unitPath%>js/jquery-1.10.2.min.js"></script>
    <!--Layer-->
	<script src="<%=ligent_unitPath%>layui/layui.js"></script>
	
	<!--扩展控件-->
	<script src="<%=ligent_unitPath%>adonis/comm/tool.js?v=<%=System.currentTimeMillis() %>"></script>
	<script src="<%=ligent_unitPath%>adonis/comm/util.js?v=<%=System.currentTimeMillis() %>"></script>
	<script src="<%=ligent_unitPath%>adonis/comm/layer.js?v=<%=System.currentTimeMillis() %>"></script>
	
	<!--右键菜单控件-->
	<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<%=ligent_unitPath%>expand/filecloud/js/tether.min.js"></script>
	<script src="<%=ligent_unitPath%>expand/filecloud/js/jquery.context-menu.min.js"></script>
		
    <script src="<%=ligent_unitPath%>adonis/file/cloud.js?v=<%=System.currentTimeMillis() %>"></script>
	    
</body>
</html>
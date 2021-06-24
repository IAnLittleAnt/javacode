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
	<!-- 扩展样式 -->
	<link href="<%=ligent_unitPath%>style/style.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet"/>
	<style type="text/css">
		/* 开始 主体样式 */
		.layui-card-body {
			margin: 0px;
			padding: 20px 10px;
			min-height: 735px;
			display: block;
			overflow: hidden;
			overflow-y: auto;
			scrollbar-color: transparent transparent;
			scrollbar-track-color: transparent;
			-ms-scrollbar-track-color: transparent;
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
		/* ul.menu{
			list-style: none;
			position: absolute;
			z-index:9999;
			visibility: hidden;
			width: 150px;
			padding: 2px;
			box-sizing: border-box;
			background-color: #F0F0F0;
			position: fixed;
			border: 1px solid #dcf0ff;
			border-top-right-radius: 3px;
			border-top-left-radius: 3px;
			border-bottom-right-radius: 3px;
			border-bottom-left-radius: 3px;
		}
		ul.menu li{
			font-size: 12px;
			padding:10px;
			cursor:pointer;
			line-height: 16px;
			font-size: 12px;
			cursor: default;
			padding-left: 28px;
			padding-right: 10px;
			position: relative;
		}
		ul.menu li.bottom{border-bottom: none;}
		ul.menu li:hover{background-color: #91C9F7;} */
		/* 结束 右键菜单 */
		
		.f_view {
			visibility: hidden;
			position: absolute;
			background-color: #fff;
			box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
			width: 170px;
			border-radius: 0px;
			border: 0px solid #ddd;
			border-top: 0;
			z-index: 9999;
		}
		.f_view li {
			height: 30px;
			cursor: default;
			line-height: 30px;
			background: #fff;
			color: #333;
			border-top: 1px solid #F1F3F7;
			font-size: 12px;
			cursor: pointer;
			text-align: left;
			padding-left: 26px;
		}
		.f_view li:hover {
			background-color: #F1F3F7;
			color: #888;
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
						<div class="crumbs">位置：
							<input id="docuUse" value="${docuUse }" type="hidden" />
							<input id="folderTypeId" value="${folder.typeId }" type="hidden" />
							<input id="folderTypeIcong" value="${folder.typeIcong }" type="hidden" />
							<input id="folderTypeIcon" value="${folder.typeIcon }" type="hidden" />
							<img src="${folder.typeIcon }" border="0" align="absmiddle"><span docuId="0" typeId="${folder.typeId }" class="docu crumb">根目录</span><span id="docuCrumbs"></span>
						</div>
           				<ul id="docuList">
           					<c:forEach var="item" items="${docuList }" varStatus="i">
	           					<li>
									<div class="docu" docuId="${item.docuId }" typeId="${item.typeId }">
										<img src="${item.typeIcong }" />
										<span class="docuName" style="display:-webkit-box;">${item.docuName }</span>
									</div>
								</li>
							</c:forEach>
           				</ul>
						<ul class="menu f_view" id="menuAdd">
				           	<li class="uploadFile">上传文件</li>
				           	<li class="createFolder">新建文件夹</li>
				           	<li class="resetFile">刷新</li>
					 	</ul>
						<ul class="menu f_view" id="menuFolder">
				           	<li class="unfoldFolder">打开文件夹</li>
				           	<li class="downloadFolder">打包下载</li>
				           	<li class="updateName">重命名</li>
				           	<li class="deleteFile">删除</li>
					 	</ul>
						<ul class="menu f_view" id="menuFile">
				           	<li class="browseFile">浏览文件</li>
				           	<li class="downloadFile">下载文件</li>
				           	<li class="updateName">重命名</li>
				           	<li class="deleteFile">删除</li>
					 	</ul>
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
	<script src="<%=ligent_unitPath%>adonis/comm/util.js?v=<%=System.currentTimeMillis() %>"></script>
	<script src="<%=ligent_unitPath%>adonis/comm/layer.js?v=<%=System.currentTimeMillis() %>"></script>
    
    <script src="<%=ligent_unitPath%>adonis/file/visus.js?v=<%=System.currentTimeMillis() %>"></script>
	    
</body>
</html>
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
	<!-- 文件云样式 -->
	<link href="<%=ligent_unitPath%>expand/file/css/index.css" rel="stylesheet"/>
	<style type="text/css">
		.diskdao3, .diskdao{
			color: #FFFFFF;
		}
		.diskdao3:link, .diskdao:link{
			color: #FFFFFF;
		}
		.diskdao3:hover, .diskdao:hover{
			color: #FFFFFF;
		}
		.layui-card-body{font-size: 14px;}
		.f_name_title input{
			height: 30px; 
			width: 360px; 
			padding-left: 5px;
		}
		.follink img{
			margin-top: 4px;
			float: left;
		}
		.follink .f_name_title{
			line-height: 26px;
			margin-left: 3px;
			float: left;
			max-width: 400px;
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
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
					<div class="layui-card-body" style="min-height:755px;">
						<div class="mydisk_file_bar">
							<a class="diskdao3 uploadFile">上传文件</a>
							<a class="diskdao createFolder">新建文件夹</a>
						</div>
						
						<div class="n1" style="padding-bottom:0px;">
							<div class="f_tp" id="f_tp">位置：
								<input id="docuUse" value="${docuUse }" type="hidden" />
								<input id="folderTypeId" value="${folder.typeId }" type="hidden" />
								<input id="folderTypeIcon" value="${folder.typeIcon }" type="hidden" />
								<img src="${folder.typeIcon }" border="0" align="absmiddle"><span docuid="0" class="f_tpspan folderName">根目录</span><span id="docuCrumbs">&nbsp;<span class="txtgray"></span></span>
							</div>
							<div class="f_th">
								<div class="f_name">文件名/夹</div>
								<div class="f_size">大小</div>
								<div class="f_time">时间</div>
								<div class="f_down">下载</div>
								<div class="f_sel">操作</div>
							</div>
							<div id="docuList" style="margin-bottom: 120px;">
								<c:forEach var="item" items="${docuList }" varStatus="i">
									<c:choose>
										<c:when test="${item.typeId == 1 }">
						    				<div id="folder${item.docuId }" class="f_tb">
												<div class="f_name2">
													<span docuid="${item.docuId }" class="follink folderName">
														<img src="${item.typeIcon }" border="0" align="absmiddle">
														<span class="f_name_title">${item.docuName }</span>
													</span>
												</div>
												<div class="f_sel">
													<a docuid="${item.docuId }" typeId="${item.typeId }" class="f_sela handleDocu" tabindex="${item.docuId }"><span></span><span></span><span></span></a>
													<a docuid="${item.docuId }" typeId="${item.typeId }"  class="f_selb deleteDocu"></a>
													<div class="f_selc fs${item.docuId }" element-invisible=""></div>
													<div class="f_selc fs_${item.docuId }" element-invisible=""></div>
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<div id="docu${item.docuId }" class="f_tb">
												<div class="f_name">
													<span docuid="${item.docuId }" class="follink docuName">
														<img src="${item.typeIcon }" border="0" align="absmiddle">
														<span class="f_name_title">${item.docuName }</span>
													</span>
													<span class="finfos fks${item.docuId }"></span>
													<span docuid="${item.docuId }" username="${item.userName }" class="finfo descFile" style="display:inline;"></span>
													<span class="finfos fis${item.docuId }"></span>
												</div>
												<div class="f_size">${item.docuSizeUnit }</div>
												<div class="f_time">${item.createTime }</div>
												<div class="f_down">
													<font size="4" color="#F81">${item.downloadCount }</font>
												</div>
												<div class="f_sel">
													<a docuid="${item.docuId }" typeId="${item.typeId }" class="f_sela handleDocu" tabindex="${item.docuId }"><span></span><span></span><span></span></a>
													<a docuid="${item.docuId }" typeId="${item.typeId }" class="f_selb deleteDocu"></a>
													<div class="f_selc fs${item.docuId }" element-invisible=""></div>
													<div class="f_selc fs_${item.docuId }" element-invisible=""></div>
												</div>
											</div>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</div>
							
							<div class="mobad"></div>
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
    
    <script src="<%=ligent_unitPath%>adonis/file/view.js?v=<%=System.currentTimeMillis() %>"></script>
    
</body>
</html>
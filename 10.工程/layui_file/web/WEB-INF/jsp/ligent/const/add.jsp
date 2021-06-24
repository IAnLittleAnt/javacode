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
</head>

<body>
	
	<div class="layui-fluid">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">
						<span class="childHeader"><!-- 页面加载完毕后自动生成 --></span>
						<span class="childTitle">/&nbsp;新增常量信息</span>
						<span data-type="revert" class="rightButton layui-btn-event">返回</span>
					</div>
					<div class="layui-card-body">
						<div class="layui-form">
			              	<div class="layui-card-body layui-row layui-col-space10">
			              		<form class="layui-form from-scale" id="MyForm">
					            	<div class="layui-form-item">
					              		<label class="layui-form-label"><span class="must">*</span>常量编码：</label>
					             		<div class="layui-input-block">
					                		<input name="constKey" type="text" lay-verify="required" placeholder="" autocomplete="off" class="layui-input layui-form-danger">
					              		</div>
					            	</div>
					            	<div class="layui-form-item">
					              		<label class="layui-form-label"><span class="must">*</span>常量名称：</label>
					             		<div class="layui-input-block">
					                		<input name="constName" type="text" lay-verify="required" placeholder="" autocomplete="off" class="layui-input layui-form-danger">
					              		</div>
					            	</div>
					            	<div class="layui-form-item">
					              		<label class="layui-form-label"><span class="must">*</span>常量值：</label>
					             		<div class="layui-input-block">
					                		<input name="constValue" type="text" lay-verify="required" placeholder="" autocomplete="off" class="layui-input layui-form-danger">
					              		</div>
					            	</div>
					            	<div class="layui-form-item">
					              		<label class="layui-form-label">常量类型：</label>
					             		<div class="layui-input-block">
											<input name="valueType" type="radio" value="int" title="数字" lay-skin="primary" >
											<input name="valueType" type="radio" value="varchar" title="字符串" checked="checked" lay-skin="primary" >
					              		</div>
					            	</div>
					            	<div class="layui-form-item">
					              		<label class="layui-form-label">常量排序：</label>
					             		<div class="layui-input-block">
					             			<input name="constSort" type="text" lay-verify="required" placeholder="" autocomplete="off" class="layui-input layui-form-danger" onkeyup="this.value=this.value.replace(/[^0-9|{2}\.]/g,'')" >
					              		</div>
					            	</div>
					            	<div class="layui-form-item">
					              		<label class="layui-form-label">使用场景：</label>
					             		<div class="layui-input-block">
					                		<textarea name="used" placeholder="" class="layui-textarea"></textarea>
					              		</div>
					            	</div>
						          	<div class="layui-form-item">
					              		<div class="layui-input-block">
					                		<div class="layui-layer-btn layui-layer-btn-">
					                			<a data-type="submit" class="layui-layer-btn0 layui-btn-event">提交</a>
					                			<a data-type="reset" class="layui-layer-btn1 layui-btn-event">重置</a>
						              		</div>
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
    
    <script src="<%=ligent_unitPath%>adonis/ligent/const/add.js?v=<%=System.currentTimeMillis() %>"></script>
    
</body>
</html>
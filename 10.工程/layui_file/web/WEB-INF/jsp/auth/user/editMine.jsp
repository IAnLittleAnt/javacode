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
						<span class="childTitle"></span>
					</div>
					<div class="layui-card-body">
						<div class="layui-form">
			              	<div class="layui-card-body layui-row layui-col-space10">
			              		<form class="layui-form" id="MyForm">
			              			<input id="userId" name="userId" value="${authUser.userId }" type="hidden">
									<div class="layui-form-item">
						            	<div class="layui-inline">
						              		<label class="layui-form-label"><span class="must">*</span>姓名：</label>
						             		<div class="layui-input-inline">
						                		<input name="userName" value="${authUser.userName }" type="text" lay-verify="required" placeholder="" autocomplete="off" class="layui-input layui-form-danger">
						              		</div>
						            	</div>
						            	<div class="layui-inline">
						              		<label class="layui-form-label">账号：</label>
						              		<div class="layui-input-inline">
						                		<input name="userCode" value="${authUser.userCode }" type="text" disabled="disabled" lay-verify="required" placeholder="" autocomplete="off" class="layui-input layui-form-danger">
						              		</div>
						            	</div>
						          	</div>
						          	
						          	<div class="layui-form-item">
						            	<div class="layui-inline">
						              		<label class="layui-form-label">学历：</label>
						             		<div class="layui-input-inline">
						                		<select name="degree">
						                			<option value="" selected="selected">-请选择-</option>
								                	<option value="博士生" <c:if test="${'博士生'==authUser.degree }">selected="selected"</c:if> >博士生</option>
								                	<option value="研究生" <c:if test="${'研究生'==authUser.degree }">selected="selected"</c:if> >研究生</option>
								                	<option value="大学本科" <c:if test="${'大学本科'==authUser.degree }">selected="selected"</c:if> >大学本科</option>
								                	<option value="大学专科" <c:if test="${'大学专科'==authUser.degree }">selected="selected"</c:if> >大学专科</option>
								                	<option value="中专" <c:if test="${'中专'==authUser.degree }">selected="selected"</c:if> >中专</option>
								                	<option value="高中" <c:if test="${'高中'==authUser.degree }">selected="selected"</c:if> >高中</option>
								                	<option value="初中" <c:if test="${'初中'==authUser.degree }">selected="selected"</c:if> >初中</option>
								              	</select>
						              		</div>
						            	</div>
						            	<div class="layui-inline">
						              		<label class="layui-form-label">性别：</label>
						              		<div class="layui-input-inline">
						                		<input name="sexName" value="男" type="radio" <c:if test="${authUser.sexName=='男' }">checked="checked"</c:if> title="男"><div class="layui-unselect layui-form-radio"><i class="layui-anim layui-icon"></i><div>男</div></div>
												<input name="sexName" value="女" type="radio" <c:if test="${authUser.sexName=='女' }">checked="checked"</c:if> title="女"><div class="layui-unselect layui-form-radio"><i class="layui-anim layui-icon"></i><div>女</div></div>
						              		</div>
						            	</div>
						          	</div>
									<div class="layui-form-item" style="width: 60%;">
					              		<label class="layui-form-label">所在城市：</label>
					             		<div class="layui-input-block">
					                		<textarea name="address" placeholder="" class="layui-textarea">${authUser.address }</textarea>
					              		</div>
						          	</div>
									<div class="layui-form-item" style="width: 60%;">
					              		<label class="layui-form-label">个人说明：</label>
					             		<div class="layui-input-block">
					                		<textarea name="description" placeholder="" class="layui-textarea">${authUser.description }</textarea>
					              		</div>
						          	</div>
						          	
						          	<div class="layui-form-item">
					              		<div class="layui-input-block">
					                		<div class="layui-layer-btn layui-layer-btn-" style="float: left;">
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
    
    <script src="<%=ligent_unitPath%>adonis/auth/user/editMine.js?v=<%=System.currentTimeMillis() %>"></script>
    
</body>
</html>
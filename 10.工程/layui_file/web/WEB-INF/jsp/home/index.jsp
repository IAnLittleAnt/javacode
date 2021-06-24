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
</head>

<body>
	<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md12">
        <div class="layui-row layui-col-space15">
        	<div class="layui-col-md12">
            <div class="layui-card">
        
	     <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
		    <thead>
			 <tr id="fileListLevel1">
			
			 </tr>
		    </thead>
	     </table>
            </div>
            </div>
        	
          <div class="layui-col-md12" style=" margin-top: -25px;">
            <div class="layui-card">
              <div class="layui-card-header" style="color: #25c88a;font-size: 24px;">二级文件夹 </div>
              <div class="layui-card-body" id="fileListLevel2">
              </div>
            </div>
          </div>
        	
          <div class="layui-col-md10" style="margin-top: -10px;">
            <div class="layui-card">
              <div class="layui-card-header">文件列表</div>
              <div class="layui-card-body">
                <div class="layui-card-body" id="fileListLevel3">
                  <div id="fileCloud">
					<ul id="docuList">
					</ul>
				  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="layui-col-md2" style="margin-top: -10px;">
            <div class="layui-card">
              <div class="layui-card-header">文件说明</div>
              <div class="layui-card-body">
              <div id="docuDesc_height">
              <span id="docuDesc_id"></span>
              </div>
                 
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
	<script src="<%=ligent_unitPath%>adonis/comm/tool.js?v=<%=System.currentTimeMillis() %>"></script>
	<script src="<%=ligent_unitPath%>adonis/comm/layer.js?v=<%=System.currentTimeMillis() %>"></script>
	<script src="<%=ligent_unitPath%>adonis/index/index.js?v=<%=System.currentTimeMillis() %>"></script>
	  
	<script>
	  	layui.config({
	    	base: ligent_unitPath //静态资源所在路径
	  	}).extend({
	    	index: 'lib/index' //主入口模块
	  	}).use(['index'], function() {
	  		// 关闭加载层
	  		layer_closeAll();
	  	});
	</script>

</style>
<style type="text/css">
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
	 	.docu img{
			margin-top: 10px;
			height: 60px;
			width: 60px;
			vertical-align: middle;
			border: 0;
		}
		
		.docuFileName{
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
		.docu:hover{
	        border: 1px solid rgba(221, 221, 221, .8);
	        background-color: rgba(255, 255, 255, .1);
		}
		#fileCloud{
			width: 100%;
			height: 100%;
			overflow-x: hidden; 
			overflow-y: auto;
		}
	</style>

</body>
</html>
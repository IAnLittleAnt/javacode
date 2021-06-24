<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<!-- 强制360浏览器以极速模式打开页面 -->
    <meta name="renderer" content="webkit">
    <meta charset="utf-8" />
	<title>PDF在线浏览</title>
	<meta http-equiv="pragma" content="no-cache"> 
	<meta http-equiv="cache-control" content="no-cache, must-revalidate"> 
	<meta http-equiv="expires" content="0">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="<%=ligent_unitPath%>_img/favicon.png" type="image/x-icon">
</head>

<body>
	
	<input id="fileName" value="${fileName }" type="hidden">
	<div id="divPdf"></div>
    
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
	<!-- pdf -->
	<script src="<%=ligent_unitPath%>expand/pdfread/jquery.media.js"></script>
	
	<script type="text/javascript">
	/**
	 * 当页面加载完毕时运行
	 */
	document.onreadystatechange = function() {
		if (document.readyState == "complete") {
			var pdfPath = $("#fileName").val(); 
		 	$("#divPdf").media({
		        width: "100%",
		        height: $(window).height() - 45,
		        autoplay: true,
		        src: pdfPath
		    });
		}
	}
	</script>
	
</body>
</html>
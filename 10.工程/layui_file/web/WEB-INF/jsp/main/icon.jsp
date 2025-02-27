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
  	<style type="text/css">
  		.site-doc-icon {
			margin-bottom: 50px;
			font-size: 0;
		}
		.site-doc-icon li {
			display: inline-block;
			vertical-align: middle;
			width: 127px;
			height: 105px;
			line-height: 25px;
			padding: 20px 0;
			margin-right: -1px;
			margin-bottom: -1px;
			border: 1px solid #e2e2e2;
			font-size: 14px;
			text-align: center;
			color: #666;
			transition: all .3s;
			-webkit-transition: all .3s;
			cursor: pointer;
		}
		.site-doc-icon li .layui-icon {
			display: inline-block;
			font-size: 36px;
		}
		.site-doc-icon li .doc-icon-name, .site-doc-icon li .doc-icon-code {
			color: #c2c2c2;
		}
		.site-doc-icon li .doc-icon-fontclass {
			height: 40px;
			line-height: 20px;
			padding: 0 5px;
			font-size: 13px;
			color: #333;
		}
  	</style>
</head>

<body>
    
	<div class="layui-fluid" id="LAY-component-progress">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				
				<div class="layui-card">
					<div class="layui-card-header">使用说明</div>
					<div class="layui-card-body">
						<div class="site-text">
					      	<p>找到对应图标后，点击下方保存即可<em>！</em></p>
					    </div>
					</div>
				</div>
				
				<div class="layui-card">
					<div class="layui-card-header">内置图标一览表（140个）</div>
					<div class="layui-card-body">
						<div class="layui-btn-container">
							<ul class="site-doc-icon">
						      <li>
						        <i class="layui-icon layui-icon-rate-half"></i>
						        <div class="doc-icon-name">半星</div>
						        <div class="doc-icon-code">&amp;#xe6c9;</div>
						        <div class="doc-icon-fontclass">layui-icon-rate-half</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-rate"></i>
						        <div class="doc-icon-name">星星-空心</div>
						        <div class="doc-icon-code">&amp;#xe67b;</div>
						        <div class="doc-icon-fontclass">layui-icon-rate</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-rate-solid"></i>
						        <div class="doc-icon-name">星星-实心</div>
						        <div class="doc-icon-code">&amp;#xe67a;</div>
						        <div class="doc-icon-fontclass">layui-icon-rate-solid</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-cellphone"></i>
						        <div class="doc-icon-name">手机</div>
						        <div class="doc-icon-code">&amp;#xe678;</div>
						        <div class="doc-icon-fontclass">layui-icon-cellphone</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-vercode"></i>
						        <div class="doc-icon-name">验证码</div>
						        <div class="doc-icon-code">&amp;#xe679;</div>
						        <div class="doc-icon-fontclass">layui-icon-vercode</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-login-wechat"></i>
						        <div class="doc-icon-name">微信</div>
						        <div class="doc-icon-code">&amp;#xe677;</div>
						        <div class="doc-icon-fontclass">layui-icon-login-wechat</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-login-qq"></i>
						        <div class="doc-icon-name">QQ</div>
						        <div class="doc-icon-code">&amp;#xe676;</div>
						        <div class="doc-icon-fontclass">layui-icon-login-qq</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-login-weibo"></i>
						        <div class="doc-icon-name">微博</div>
						        <div class="doc-icon-code">&amp;#xe675;</div>
						        <div class="doc-icon-fontclass">layui-icon-login-weibo</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-password"></i>
						        <div class="doc-icon-name">密码</div>
						        <div class="doc-icon-code">&amp;#xe673;</div>
						        <div class="doc-icon-fontclass">layui-icon-password</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-username"></i>
						        <div class="doc-icon-name">用户名</div>
						        <div class="doc-icon-code">&amp;#xe66f;</div>
						        <div class="doc-icon-fontclass">layui-icon-username</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-refresh-3"></i>
						        <div class="doc-icon-name">刷新-粗</div>
						        <div class="doc-icon-code">&amp;#xe9aa;</div>
						        <div class="doc-icon-fontclass">layui-icon-refresh-3</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-auz"></i>
						        <div class="doc-icon-name">授权</div>
						        <div class="doc-icon-code">&amp;#xe672;</div>
						        <div class="doc-icon-fontclass">layui-icon-auz</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-spread-left"></i>
						        <div class="doc-icon-name">左向右伸缩菜单</div>
						        <div class="doc-icon-code">&amp;#xe66b;</div>
						        <div class="doc-icon-fontclass">layui-icon-spread-left</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-shrink-right"></i>
						        <div class="doc-icon-name">右向左伸缩菜单</div>
						        <div class="doc-icon-code">&amp;#xe668;</div>
						        <div class="doc-icon-fontclass">layui-icon-shrink-right</div>
						      </li>
						      
						      <li>
						        <i class="layui-icon layui-icon-snowflake"></i>
						        <div class="doc-icon-name">雪花</div>
						        <div class="doc-icon-code">&amp;#xe6b1;</div>
						        <div class="doc-icon-fontclass">layui-icon-snowflake</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-tips"></i>
						        <div class="doc-icon-name">提示说明</div>
						        <div class="doc-icon-code">&amp;#xe702;</div>
						        <div class="doc-icon-fontclass">layui-icon-tips</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-note"></i>
						        <div class="doc-icon-name">便签</div>
						        <div class="doc-icon-code">&amp;#xe66e;</div>
						        <div class="doc-icon-fontclass">layui-icon-note</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-home"></i>
						        <div class="doc-icon-name">主页</div>
						        <div class="doc-icon-code">&amp;#xe68e;</div>
						        <div class="doc-icon-fontclass">layui-icon-home</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-senior"></i>
						        <div class="doc-icon-name">高级</div>
						        <div class="doc-icon-code">&amp;#xe674;</div>
						        <div class="doc-icon-fontclass">layui-icon-senior</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-refresh"></i>
						        <div class="doc-icon-name">刷新</div>
						        <div class="doc-icon-code">&amp;#xe669;</div>
						        <div class="doc-icon-fontclass">layui-icon-refresh</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-refresh-1"></i>
						        <div class="doc-icon-name">刷新</div>
						        <div class="doc-icon-code">&amp;#xe666;</div>
						        <div class="doc-icon-fontclass">layui-icon-refresh-1</div>
						      </li>
						      
						      <li>
						        <i class="layui-icon layui-icon-flag"></i>
						        <div class="doc-icon-name">旗帜</div>
						        <div class="doc-icon-code">&amp;#xe66c;</div>
						        <div class="doc-icon-fontclass">layui-icon-flag</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-theme"></i>
						        <div class="doc-icon-name">主题</div>
						        <div class="doc-icon-code">&amp;#xe66a;</div>
						        <div class="doc-icon-fontclass">layui-icon-theme</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-notice"></i>
						        <div class="doc-icon-name">消息-通知</div>
						        <div class="doc-icon-code">&amp;#xe667;</div>
						        <div class="doc-icon-fontclass">layui-icon-notice</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-website"></i>
						        <div class="doc-icon-name">网站</div>
						        <div class="doc-icon-code">&amp;#xe7ae;</div>
						        <div class="doc-icon-fontclass">layui-icon-website</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-console"></i>
						        <div class="doc-icon-name">控制台</div>
						        <div class="doc-icon-code">&amp;#xe665;</div>
						        <div class="doc-icon-fontclass">layui-icon-console</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-face-surprised"></i>
						        <div class="doc-icon-name">表情-惊讶</div>
						        <div class="doc-icon-code">&amp;#xe664;</div>
						        <div class="doc-icon-fontclass">layui-icon-face-surprised</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-set"></i>
						        <div class="doc-icon-name">设置-空心</div>
						        <div class="doc-icon-code">&amp;#xe716;</div>
						        <div class="doc-icon-fontclass">layui-icon-set</div>
						      </li>
						      
						      <li>
						        <i class="layui-icon layui-icon-template-1"></i>
						        <div class="doc-icon-name">模板</div>
						        <div class="doc-icon-code">&amp;#xe656;</div>
						        <div class="doc-icon-fontclass">layui-icon-template-1</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-app"></i>
						        <div class="doc-icon-name">应用</div>
						        <div class="doc-icon-code">&amp;#xe653;</div>
						        <div class="doc-icon-fontclass">layui-icon-app</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-template"></i>
						        <div class="doc-icon-name">模板</div>
						        <div class="doc-icon-code">&amp;#xe663;</div>
						        <div class="doc-icon-fontclass">layui-icon-template</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-praise"></i>
						        <div class="doc-icon-name">赞</div>
						        <div class="doc-icon-code">&amp;#xe6c6;</div>
						        <div class="doc-icon-fontclass">layui-icon-praise</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-tread"></i>
						        <div class="doc-icon-name">踩</div>
						        <div class="doc-icon-code">&amp;#xe6c5;</div>
						        <div class="doc-icon-fontclass">layui-icon-tread</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-male"></i>
						        <div class="doc-icon-name">男</div>
						        <div class="doc-icon-code">&amp;#xe662;</div>
						        <div class="doc-icon-fontclass">layui-icon-male</div>
						      </li>
						      
						      <li>
						        <i class="layui-icon layui-icon-female"></i>
						        <div class="doc-icon-name">女</div>
						        <div class="doc-icon-code">&amp;#xe661;</div>
						        <div class="doc-icon-fontclass">layui-icon-female</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-camera"></i>
						        <div class="doc-icon-name">相机-空心</div>
						        <div class="doc-icon-code">&amp;#xe660;</div>
						        <div class="doc-icon-fontclass">layui-icon-camera</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-camera-fill"></i>
						        <div class="doc-icon-name">相机-实心</div>
						        <div class="doc-icon-code">&amp;#xe65d;</div>
						        <div class="doc-icon-fontclass">layui-icon-camera-fill</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-more"></i>
						        <div class="doc-icon-name">菜单-水平</div>
						        <div class="doc-icon-code">&amp;#xe65f;</div>
						        <div class="doc-icon-fontclass">layui-icon-more</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-more-vertical"></i>
						        <div class="doc-icon-name">菜单-垂直</div>
						        <div class="doc-icon-code">&amp;#xe671;</div>
						        <div class="doc-icon-fontclass">layui-icon-more-vertical</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-rmb"></i>
						        <div class="doc-icon-name">金额-人民币</div>
						        <div class="doc-icon-code">&amp;#xe65e;</div>
						        <div class="doc-icon-fontclass">layui-icon-rmb</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-dollar"></i>
						        <div class="doc-icon-name">金额-美元</div>
						        <div class="doc-icon-code">&amp;#xe659;</div>
						        <div class="doc-icon-fontclass">layui-icon-dollar</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-diamond"></i>
						        <div class="doc-icon-name">钻石-等级</div>
						        <div class="doc-icon-code">&amp;#xe735;</div>
						        <div class="doc-icon-fontclass">layui-icon-diamond</div>
						      </li>
						      
						      <li>
						        <i class="layui-icon layui-icon-fire"></i>
						        <div class="doc-icon-name">火</div>
						        <div class="doc-icon-code">&amp;#xe756;</div>
						        <div class="doc-icon-fontclass">layui-icon-fire</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-return"></i>
						        <div class="doc-icon-name">返回</div>
						        <div class="doc-icon-code">&amp;#xe65c;</div>
						        <div class="doc-icon-fontclass">layui-icon-return</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-location"></i>
						        <div class="doc-icon-name">位置-地图</div>
						        <div class="doc-icon-code">&amp;#xe715;</div>
						        <div class="doc-icon-fontclass">layui-icon-location</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-read"></i>
						        <div class="doc-icon-name">办公-阅读</div>
						        <div class="doc-icon-code">&amp;#xe705;</div>
						        <div class="doc-icon-fontclass">layui-icon-read</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-survey"></i>
						        <div class="doc-icon-name">调查</div>
						        <div class="doc-icon-code">&amp;#xe6b2;</div>
						        <div class="doc-icon-fontclass">layui-icon-survey</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-face-smile"></i>
						        <div class="doc-icon-name">表情-微笑</div>
						        <div class="doc-icon-code">&amp;#xe6af;</div>
						        <div class="doc-icon-fontclass">layui-icon-face-smile</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-face-cry"></i>
						        <div class="doc-icon-name">表情-哭泣</div>
						        <div class="doc-icon-code">&amp;#xe69c;</div>
						        <div class="doc-icon-fontclass">layui-icon-face-cry</div>
						      </li>
						      
						      <li>
						        <i class="layui-icon layui-icon-cart-simple"></i>
						        <div class="doc-icon-name">购物车</div>
						        <div class="doc-icon-code">&amp;#xe698;</div>
						        <div class="doc-icon-fontclass">layui-icon-cart-simple</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-cart"></i>
						        <div class="doc-icon-name">购物车</div>
						        <div class="doc-icon-code">&amp;#xe657;</div>
						        <div class="doc-icon-fontclass">layui-icon-cart</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-next"></i>
						        <div class="doc-icon-name">下一页</div>
						        <div class="doc-icon-code">&amp;#xe65b;</div>
						        <div class="doc-icon-fontclass">layui-icon-next</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-prev"></i>
						        <div class="doc-icon-name">上一页</div>
						        <div class="doc-icon-code">&amp;#xe65a;</div>
						        <div class="doc-icon-fontclass">layui-icon-prev</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-upload-drag"></i>
						        <div class="doc-icon-name">上传-空心-拖拽</div>
						        <div class="doc-icon-code">&amp;#xe681;</div>
						        <div class="doc-icon-fontclass">layui-icon-upload-drag</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-upload"></i>
						        <div class="doc-icon-name">上传-实心</div>
						        <div class="doc-icon-code">&amp;#xe67c;</div>
						        <div class="doc-icon-fontclass">layui-icon-upload</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-download-circle"></i>
						        <div class="doc-icon-name">下载-圆圈</div>
						        <div class="doc-icon-code">&amp;#xe601;</div>
						        <div class="doc-icon-fontclass">layui-icon-download-circle</div>
						      </li>
						      
						      <li>
						        <i class="layui-icon layui-icon-component"></i>
						        <div class="doc-icon-name">组件</div>
						        <div class="doc-icon-code">&amp;#xe857;</div>
						        <div class="doc-icon-fontclass">layui-icon-component</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-file-b"></i>
						        <div class="doc-icon-name">文件-粗</div>
						        <div class="doc-icon-code">&amp;#xe655;</div>
						        <div class="doc-icon-fontclass">layui-icon-file-b</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-user"></i>
						        <div class="doc-icon-name">用户</div>
						        <div class="doc-icon-code">&amp;#xe770;</div>
						        <div class="doc-icon-fontclass">layui-icon-user</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-find-fill"></i>
						        <div class="doc-icon-name">发现-实心</div>
						        <div class="doc-icon-code">&amp;#xe670;</div>
						        <div class="doc-icon-fontclass">layui-icon-find-fill</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-loading layui-icon layui-anim layui-anim-rotate layui-anim-loop"></i>
						        <div class="doc-icon-name">loading</div>
						        <div class="doc-icon-code">&amp;#xe63d;</div>
						        <div class="doc-icon-fontclass">layui-icon-loading</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-loading-1 layui-icon layui-anim layui-anim-rotate layui-anim-loop"></i>
						        <div class="doc-icon-name">loading</div>
						        <div class="doc-icon-code">&amp;#xe63e;</div>
						        <div class="doc-icon-fontclass">layui-icon-loading-1</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-add-1"></i>
						        <div class="doc-icon-name">添加</div>
						        <div class="doc-icon-code">&amp;#xe654;</div>
						        <div class="doc-icon-fontclass">layui-icon-add-1</div>
						      </li>
						      
						      <li>
						        <i class="layui-icon layui-icon-play"></i>
						        <div class="doc-icon-name">播放</div>
						        <div class="doc-icon-code">&amp;#xe652;</div>
						        <div class="doc-icon-fontclass">layui-icon-play</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-pause"></i>
						        <div class="doc-icon-name">暂停</div>
						        <div class="doc-icon-code">&amp;#xe651;</div>
						        <div class="doc-icon-fontclass">layui-icon-pause</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-headset"></i>
						        <div class="doc-icon-name">音频-耳机</div>
						        <div class="doc-icon-code">&amp;#xe6fc;</div>
						        <div class="doc-icon-fontclass">layui-icon-headset</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-video"></i>
						        <div class="doc-icon-name">视频</div>
						        <div class="doc-icon-code">&amp;#xe6ed;</div>
						        <div class="doc-icon-fontclass">layui-icon-video</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-voice"></i>
						        <div class="doc-icon-name">语音-声音</div>
						        <div class="doc-icon-code">&amp;#xe688;</div>
						        <div class="doc-icon-fontclass">layui-icon-voice</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-speaker"></i>
						        <div class="doc-icon-name">消息-通知-喇叭</div>
						        <div class="doc-icon-code">&amp;#xe645;</div>
						        <div class="doc-icon-fontclass">layui-icon-speaker</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-fonts-del"></i>
						        <div class="doc-icon-name">删除线</div>
						        <div class="doc-icon-code">&amp;#xe64f;</div>
						        <div class="doc-icon-fontclass">layui-icon-fonts-del</div>
						      </li>
						      
						      <li>
						        <i class="layui-icon layui-icon-fonts-code"></i>
						        <div class="doc-icon-name">代码</div>
						        <div class="doc-icon-code">&amp;#xe64e;</div>
						        <div class="doc-icon-fontclass">layui-icon-fonts-code</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-fonts-html"></i>
						        <div class="doc-icon-name">HTML</div>
						        <div class="doc-icon-code">&amp;#xe64b;</div>
						        <div class="doc-icon-fontclass">layui-icon-fonts-html</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-fonts-strong"></i>
						        <div class="doc-icon-name">字体加粗</div>
						        <div class="doc-icon-code">&amp;#xe62b;</div>
						        <div class="doc-icon-fontclass">layui-icon-fonts-strong</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-unlink"></i>
						        <div class="doc-icon-name">删除链接</div>
						        <div class="doc-icon-code">&amp;#xe64d;</div>
						        <div class="doc-icon-fontclass">layui-icon-unlink</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-picture"></i>
						        <div class="doc-icon-name">图片</div>
						        <div class="doc-icon-code">&amp;#xe64a;</div>
						        <div class="doc-icon-fontclass">layui-icon-picture</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-link"></i>
						        <div class="doc-icon-name">链接</div>
						        <div class="doc-icon-code">&amp;#xe64c;</div>
						        <div class="doc-icon-fontclass">layui-icon-link</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-face-smile-b"></i>
						        <div class="doc-icon-name">表情-笑-粗</div>
						        <div class="doc-icon-code">&amp;#xe650;</div>
						        <div class="doc-icon-fontclass">layui-icon-face-smile-b</div>
						      </li>
						      
						      <li>
						        <i class="layui-icon layui-icon-align-left"></i>
						        <div class="doc-icon-name">左对齐</div>
						        <div class="doc-icon-code">&amp;#xe649;</div>
						        <div class="doc-icon-fontclass">layui-icon-align-left</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-align-right"></i>
						        <div class="doc-icon-name">右对齐</div>
						        <div class="doc-icon-code">&amp;#xe648;</div>
						        <div class="doc-icon-fontclass">layui-icon-align-right</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-align-center"></i>
						        <div class="doc-icon-name">居中对齐</div>
						        <div class="doc-icon-code">&amp;#xe647;</div>
						        <div class="doc-icon-fontclass">layui-icon-align-center</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-fonts-u"></i>
						        <div class="doc-icon-name">字体-下划线</div>
						        <div class="doc-icon-code">&amp;#xe646;</div>
						        <div class="doc-icon-fontclass">layui-icon-fonts-u</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-fonts-i"></i>
						        <div class="doc-icon-name">字体-斜体</div>
						        <div class="doc-icon-code">&amp;#xe644;</div>
						        <div class="doc-icon-fontclass">layui-icon-fonts-i</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-tabs"></i>
						        <div class="doc-icon-name">Tabs 选项卡</div>
						        <div class="doc-icon-code">&amp;#xe62a;</div>
						        <div class="doc-icon-fontclass">layui-icon-tabs</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-radio"></i>
						        <div class="doc-icon-name">单选框-选中</div>
						        <div class="doc-icon-code">&amp;#xe643;</div>
						        <div class="doc-icon-fontclass">layui-icon-radio</div>
						      </li>
						      
						      <li>
						        <i class="layui-icon layui-icon-circle"></i>
						        <div class="doc-icon-name">单选框-候选</div>
						        <div class="doc-icon-code">&amp;#xe63f;</div>
						        <div class="doc-icon-fontclass">layui-icon-circle</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-edit"></i>
						        <div class="doc-icon-name">编辑</div>
						        <div class="doc-icon-code">&amp;#xe642;</div>
						        <div class="doc-icon-fontclass">layui-icon-edit</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-share"></i>
						        <div class="doc-icon-name">分享</div>
						        <div class="doc-icon-code">&amp;#xe641;</div>
						        <div class="doc-icon-fontclass">layui-icon-share</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-delete"></i>
						        <div class="doc-icon-name">删除</div>
						        <div class="doc-icon-code">&amp;#xe640;</div>
						        <div class="doc-icon-fontclass">layui-icon-delete</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-form"></i>
						        <div class="doc-icon-name">表单</div>
						        <div class="doc-icon-code">&amp;#xe63c;</div>
						        <div class="doc-icon-fontclass">layui-icon-form</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-cellphone-fine"></i>
						        <div class="doc-icon-name">手机-细体</div>
						        <div class="doc-icon-code">&amp;#xe63b;</div>
						        <div class="doc-icon-fontclass">layui-icon-cellphone-fine</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-dialogue"></i>
						        <div class="doc-icon-name">聊天 对话 沟通</div>
						        <div class="doc-icon-code">&amp;#xe63a;</div>
						        <div class="doc-icon-fontclass">layui-icon-dialogue</div>
						      </li>
						      
						      <li>
						        <i class="layui-icon layui-icon-fonts-clear"></i>
						        <div class="doc-icon-name">文字格式化</div>
						        <div class="doc-icon-code">&amp;#xe639;</div>
						        <div class="doc-icon-fontclass">layui-icon-fonts-clear</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-layer"></i>
						        <div class="doc-icon-name">窗口</div>
						        <div class="doc-icon-code">&amp;#xe638;</div>
						        <div class="doc-icon-fontclass">layui-icon-layer</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-date"></i>
						        <div class="doc-icon-name">日期</div>
						        <div class="doc-icon-code">&amp;#xe637;</div>
						        <div class="doc-icon-fontclass">layui-icon-date</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-water"></i>
						        <div class="doc-icon-name">水 下雨</div>
						        <div class="doc-icon-code">&amp;#xe636;</div>
						        <div class="doc-icon-fontclass">layui-icon-water</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-code-circle"></i>
						        <div class="doc-icon-name">代码-圆圈</div>
						        <div class="doc-icon-code">&amp;#xe635;</div>
						        <div class="doc-icon-fontclass">layui-icon-code-circle</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-carousel"></i>
						        <div class="doc-icon-name">轮播组图</div>
						        <div class="doc-icon-code">&amp;#xe634;</div>
						        <div class="doc-icon-fontclass">layui-icon-carousel</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-prev-circle"></i>
						        <div class="doc-icon-name">翻页</div>
						        <div class="doc-icon-code">&amp;#xe633;</div>
						        <div class="doc-icon-fontclass">layui-icon-prev-circle</div>
						      </li>
						      
						      <li>
						        <i class="layui-icon layui-icon-layouts"></i>
						        <div class="doc-icon-name">布局</div>
						        <div class="doc-icon-code">&amp;#xe632;</div>
						        <div class="doc-icon-fontclass">layui-icon-layouts</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-util"></i>
						        <div class="doc-icon-name">工具</div>
						        <div class="doc-icon-code">&amp;#xe631;</div>
						        <div class="doc-icon-fontclass">layui-icon-util</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-templeate-1"></i>
						        <div class="doc-icon-name">选择模板</div>
						        <div class="doc-icon-code">&amp;#xe630;</div>
						        <div class="doc-icon-fontclass">layui-icon-templeate-1</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-upload-circle"></i>
						        <div class="doc-icon-name">上传-圆圈</div>
						        <div class="doc-icon-code">&amp;#xe62f;</div>
						        <div class="doc-icon-fontclass">layui-icon-upload-circle</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-tree"></i>
						        <div class="doc-icon-name">树</div>
						        <div class="doc-icon-code">&amp;#xe62e;</div>
						        <div class="doc-icon-fontclass">layui-icon-tree</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-table"></i>
						        <div class="doc-icon-name">表格</div>
						        <div class="doc-icon-code">&amp;#xe62d;</div>
						        <div class="doc-icon-fontclass">layui-icon-table</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-chart"></i>
						        <div class="doc-icon-name">图表</div>
						        <div class="doc-icon-code">&amp;#xe62c;</div>
						        <div class="doc-icon-fontclass">layui-icon-chart</div>
						      </li>
						      
						      <li>
						        <i class="layui-icon layui-icon-chart-screen"></i>
						        <div class="doc-icon-name">图标 报表 屏幕</div>
						        <div class="doc-icon-code">&amp;#xe629;</div>
						        <div class="doc-icon-fontclass">layui-icon-chart-screen</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-engine"></i>
						        <div class="doc-icon-name">引擎</div>
						        <div class="doc-icon-code">&amp;#xe628;</div>
						        <div class="doc-icon-fontclass">layui-icon-engine</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-triangle-d"></i>
						        <div class="doc-icon-name">下三角</div>
						        <div class="doc-icon-code">&amp;#xe625;</div>
						        <div class="doc-icon-fontclass">layui-icon-triangle-d</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-triangle-r"></i>
						        <div class="doc-icon-name">右三角</div>
						        <div class="doc-icon-code">&amp;#xe623;</div>
						        <div class="doc-icon-fontclass">layui-icon-triangle-r</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-file"></i>
						        <div class="doc-icon-name">文件</div>
						        <div class="doc-icon-code">&amp;#xe621;</div>
						        <div class="doc-icon-fontclass">layui-icon-file</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-set-sm"></i>
						        <div class="doc-icon-name">设置-小型</div>
						        <div class="doc-icon-code">&amp;#xe620;</div>
						        <div class="doc-icon-fontclass">layui-icon-set-sm</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-add-circle"></i>
						        <div class="doc-icon-name">添加-圆圈</div>
						        <div class="doc-icon-code">&amp;#xe61f;</div>
						        <div class="doc-icon-fontclass">layui-icon-add-circle</div>
						      </li>
						      
						      
						      <li>
						        <i class="layui-icon layui-icon-404"></i>
						        <div class="doc-icon-name">404</div>
						        <div class="doc-icon-code">&amp;#xe61c;</div>
						        <div class="doc-icon-fontclass">layui-icon-404</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-about"></i>
						        <div class="doc-icon-name">关于</div>
						        <div class="doc-icon-code">&amp;#xe60b;</div>
						        <div class="doc-icon-fontclass">layui-icon-about</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-up"></i>
						        <div class="doc-icon-name">箭头 向上</div>
						        <div class="doc-icon-code">&amp;#xe619;</div>
						        <div class="doc-icon-fontclass">layui-icon-up</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-down"></i>
						        <div class="doc-icon-name">箭头 向下</div>
						        <div class="doc-icon-code">&amp;#xe61a;</div>
						        <div class="doc-icon-fontclass">layui-icon-down</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-left"></i>
						        <div class="doc-icon-name">箭头 向左</div>
						        <div class="doc-icon-code">&amp;#xe603;</div>
						        <div class="doc-icon-fontclass">layui-icon-left</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-right"></i>
						        <div class="doc-icon-name">箭头 向右</div>
						        <div class="doc-icon-code">&amp;#xe602;</div>
						        <div class="doc-icon-fontclass">layui-icon-right</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-circle-dot"></i>
						        <div class="doc-icon-name">圆点</div>
						        <div class="doc-icon-code">&amp;#xe617;</div>
						        <div class="doc-icon-fontclass">layui-icon-circle-dot</div>
						      </li>
						      
						      <li>
						        <i class="layui-icon layui-icon-search"></i>
						        <div class="doc-icon-name">搜索</div>
						        <div class="doc-icon-code">&amp;#xe615;</div>
						        <div class="doc-icon-fontclass">layui-icon-search</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-set-fill"></i>
						        <div class="doc-icon-name">设置-实心</div>
						        <div class="doc-icon-code">&amp;#xe614;</div>
						        <div class="doc-icon-fontclass">layui-icon-set-fill</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-group"></i>
						        <div class="doc-icon-name">群组</div>
						        <div class="doc-icon-code">&amp;#xe613;</div>
						        <div class="doc-icon-fontclass">layui-icon-group</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-friends"></i>
						        <div class="doc-icon-name">好友</div>
						        <div class="doc-icon-code">&amp;#xe612;</div>
						        <div class="doc-icon-fontclass">layui-icon-friends</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-reply-fill"></i>
						        <div class="doc-icon-name">回复 评论 实心</div>
						        <div class="doc-icon-code">&amp;#xe611;</div>
						        <div class="doc-icon-fontclass">layui-icon-reply-fill</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-menu-fill"></i>
						        <div class="doc-icon-name">菜单 隐身 实心</div>
						        <div class="doc-icon-code">&amp;#xe60f;</div>
						        <div class="doc-icon-fontclass">layui-icon-menu-fill</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-log"></i>
						        <div class="doc-icon-name">记录</div>
						        <div class="doc-icon-code">&amp;#xe60e;</div>
						        <div class="doc-icon-fontclass">layui-icon-log</div>
						      </li>
						      
						      <li>
						        <i class="layui-icon layui-icon-picture-fine"></i>
						        <div class="doc-icon-name">图片-细体</div>
						        <div class="doc-icon-code">&amp;#xe60d;</div>
						        <div class="doc-icon-fontclass">layui-icon-picture-fine</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-face-smile-fine"></i>
						        <div class="doc-icon-name">表情-笑-细体</div>
						        <div class="doc-icon-code">&amp;#xe60c;</div>
						        <div class="doc-icon-fontclass">layui-icon-face-smile-fine</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-list"></i>
						        <div class="doc-icon-name">列表</div>
						        <div class="doc-icon-code">&amp;#xe60a;</div>
						        <div class="doc-icon-fontclass">layui-icon-list</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-release"></i>
						        <div class="doc-icon-name">发布 纸飞机</div>
						        <div class="doc-icon-code">&amp;#xe609;</div>
						        <div class="doc-icon-fontclass">layui-icon-release</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-ok"></i>
						        <div class="doc-icon-name">对 OK</div>
						        <div class="doc-icon-code">&amp;#xe605;</div>
						        <div class="doc-icon-fontclass">layui-icon-ok</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-help"></i>
						        <div class="doc-icon-name">帮助</div>
						        <div class="doc-icon-code">&amp;#xe607;</div>
						        <div class="doc-icon-fontclass">layui-icon-help</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-chat"></i>
						        <div class="doc-icon-name">客服</div>
						        <div class="doc-icon-code">&amp;#xe606;</div>
						        <div class="doc-icon-fontclass">layui-icon-chat</div>
						      </li>
						      
						      <li>
						        <i class="layui-icon layui-icon-top"></i>
						        <div class="doc-icon-name">top 置顶</div>
						        <div class="doc-icon-code">&amp;#xe604;</div>
						        <div class="doc-icon-fontclass">layui-icon-top</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-star"></i>
						        <div class="doc-icon-name">收藏-空心</div>
						        <div class="doc-icon-code">&amp;#xe600;</div>
						        <div class="doc-icon-fontclass">layui-icon-star</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-star-fill"></i>
						        <div class="doc-icon-name">收藏-实心</div>
						        <div class="doc-icon-code">&amp;#xe658;</div>
						        <div class="doc-icon-fontclass">layui-icon-star-fill</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-close-fill"></i>
						        <div class="doc-icon-name">关闭-实心</div>
						        <div class="doc-icon-code">&amp;#x1007;</div>
						        <div class="doc-icon-fontclass">layui-icon-close-fill</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-close"></i>
						        <div class="doc-icon-name">关闭-空心</div>
						        <div class="doc-icon-code">&amp;#x1006;</div>
						        <div class="doc-icon-fontclass">layui-icon-close</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-ok-circle"></i>
						        <div class="doc-icon-name">正确</div>
						        <div class="doc-icon-code">&amp;#x1005;</div>
						        <div class="doc-icon-fontclass">layui-icon-ok-circle</div>
						      </li>
						      <li>
						        <i class="layui-icon layui-icon-add-circle-fine"></i>
						        <div class="doc-icon-name">添加-圆圈-细体</div>
						        <div class="doc-icon-code">&amp;#xe608;</div>
						        <div class="doc-icon-fontclass">layui-icon-add-circle-fine</div>
						      </li>
						    </ul>
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
	
    <script src="<%=ligent_unitPath%>adonis/main/icon.js?v=<%=System.currentTimeMillis() %>"></script>
    
</body>
</html>
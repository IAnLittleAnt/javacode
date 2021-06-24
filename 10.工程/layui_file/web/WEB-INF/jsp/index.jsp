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
	<!-- 扩展图标 -->
	<link rel="stylesheet" href="<%=ligent_unitPath%>style/iconfont/iconfont.css" media="all">
</head>

<body class="layui-layout-body">
	
	<div id="LAY_app">
		<div class="layui-layout layui-layout-admin">
			<div class="layui-header">
				<!-- 头部区域 -->
				<ul class="layui-nav layui-layout-left">
					<li class="layui-nav-item layadmin-flexible" lay-unselect>
						<a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
							<i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
						</a>
					</li>
					<li class="layui-nav-item layui-hide-xs" lay-unselect>
						<a href="http://layim.oiio.xin/home/index" target="_blank" title="前台">
							<i class="layui-icon layui-icon-website"></i>
						</a>
					</li>
					<li class="layui-nav-item" lay-unselect>
						<a href="javascript:;" layadmin-event="refresh" title="刷新">
							<i class="layui-icon layui-icon-refresh-3"></i>
						</a>
					</li>
					<li class="layui-nav-item layui-hide-xs" lay-unselect>
						<input type="text" placeholder="搜索..." autocomplete="off" class="layui-input layui-input-search" layadmin-event="serach">
					</li>
				</ul>
				<ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
					<li class="layui-nav-item" lay-unselect>
						<a lay-href="<%=ligent_itemName%>mail/mailer/inbox?isRead=0" layadmin-event="message" lay-text="消息中心">
							<i class="layui-icon layui-icon-notice"></i>
							<!-- 如果有新消息，则显示小圆点 -->
							<c:if test="${mailerCount>0}">
								<span class="layui-badge-dot"></span>
							</c:if>
						</a>
					</li>
					<li class="layui-nav-item layui-hide-xs" lay-unselect>
						<a href="javascript:;" layadmin-event="theme">
							<i class="layui-icon layui-icon-theme"></i>
						</a>
					</li>
					<li class="layui-nav-item layui-hide-xs" lay-unselect>
						<a href="javascript:;" layadmin-event="note">
							<i class="layui-icon layui-icon-note"></i>
						</a>
					</li>
					<li class="layui-nav-item layui-hide-xs" lay-unselect>
						<a href="javascript:;" layadmin-event="fullscreen">
							<i class="layui-icon layui-icon-screen-full"></i>
						</a>
					</li>
					<li class="layui-nav-item" lay-unselect>
						<a href="javascript:;">
							<cite>${loginUser.userName }</cite>
						</a>
						<dl class="layui-nav-child">
							<dd>
								<a lay-href="<%=ligent_itemName%>auth/user/editMine">基本资料</a>
							</dd>
							<dd>
								<a lay-href="<%=ligent_itemName%>user/password">修改密码</a>
							</dd>
							<hr>
							<dd style="text-align: center;">
								<a class="Lay-index-login" href="javascript:;">退出</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item layui-hide-xs" lay-unselect>
						<a href="javascript:;" layadmin-event="about"><i class="layui-icon layui-icon-more-vertical"></i></a>
					</li>
					<li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
						<a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
					</li>
				</ul>
			</div>
			
			<!-- 开始 侧边菜单 -->
			<div class="layui-side layui-side-menu">
				<div class="layui-side-scroll">
					<div class="layui-logo" lay-href="<%=ligent_itemName%>home/console">
						<span>工具管理系统</span>
					</div>
					<ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
						<c:forEach var="item" items="${menuList }" varStatus="i">
							<li lay-id="${item.menuId }" class="layui-nav-item">
								<c:choose>
									<c:when test="${item.isRoot == 1 }">
                 						<a href="javascript:;" lay-tips="${item.menuName }" lay-direction="2">
											<i class="layui-icon"><span class="icon iconfont">${item.menuIcon }</span></i>
											<cite>${item.menuName }</cite>
										</a>
									</c:when>
									<c:when test="${item.menuType == 1 }">
										<a lay-href="<%=ligent_itemName%>${item.menuPath }" lay-text="${item.menuText }">
											<i class="layui-icon"><span class="icon iconfont">${item.menuIcon }</span></i>
											<cite>${item.menuName }</cite>
										</a>
									</c:when>
									<c:when test="${item.menuType == 2 }">
										<a href="<%=ligent_itemName%>${item.menuPath }" target="_blank">
											<i class="layui-icon"><span class="icon iconfont">${item.menuIcon }</span></i>
											<cite>${item.menuName }</cite>
										</a>
									</c:when>
									<c:when test="${item.menuType == 3 }">
										<a lay-href="${item.menuPath }" lay-text="${item.menuText }">
											<i class="layui-icon"><span class="icon iconfont">${item.menuIcon }</span></i>
											<cite>${item.menuName }</cite>
										</a>
									</c:when>
									<c:when test="${item.menuType == 4 }">
										<a layadmin-event="${item.menuPath }">
											<i class="layui-icon"><span class="icon iconfont">${item.menuIcon }</span></i>
											<cite>${item.menuName }</cite>
										</a>
									</c:when>
								</c:choose>
								
								<c:choose>
                 					<c:when test="${not empty item.childList }">
										<dl class="layui-nav-child">
											<!-- 开始 孩子菜单 -->
											<c:forEach var="child" items="${item.childList }" varStatus="c">
												<dd lay-id="${child.menuId }">
													<c:choose>
           												<c:when test="${child.isRoot == 1 }">
           													<a href="javascript:;">${child.menuName }</a>
           												</c:when>
           												<c:when test="${child.menuType == 1 }">
           													<a lay-href="<%=ligent_itemName%>${child.menuPath }" lay-text="${child.menuText }">${child.menuName }</a>
           												</c:when>
           												<c:when test="${child.menuType == 2 }">
           													<a href="<%=ligent_itemName%>${child.menuPath }" target="_blank">${child.menuName }</a>
           												</c:when>
           												<c:when test="${child.menuType == 3 }">
           													<a lay-href="${child.menuPath }" lay-text="${child.menuText }">${child.menuName }</a>
           												</c:when>
           												<c:when test="${child.menuType == 4 }">
           													<a layadmin-event="${child.menuPath }">${child.menuName }</a>
           												</c:when>
           											</c:choose>
													<c:choose>
		                    							<c:when test="${not empty child.childList }">
		                    								<!-- 开始 孙子菜单 -->
		                    								<dl class="layui-nav-child">
			                    								<c:forEach var="sun" items="${child.childList }" varStatus="s">
																	<dd lay-id="${sun.menuId }">
																		<c:choose>
		                    												<c:when test="${sun.menuType == 1 }">
		                    													<a lay-href="<%=ligent_itemName%>${sun.menuPath }" lay-text="${sun.menuText }">${sun.menuName }</a>
		                    												</c:when>
		                    												<c:when test="${sun.menuType == 2 }">
		                    													<a href="<%=ligent_itemName%>${sun.menuPath }" target="_blank">${sun.menuName }</a>
		                    												</c:when>
		                    												<c:when test="${sun.menuType == 3 }">
		                    													<a lay-href="${sun.menuPath }" lay-text="${sun.menuText }">${sun.menuName }</a>
		                    												</c:when>
		                    												<c:when test="${sun.menuType == 4 }">
		                    													<a layadmin-event="${sun.menuPath }">${sun.menuName }</a>
		                    												</c:when>
		                    											</c:choose>
																	</dd>
																</c:forEach>
		                    								</dl>
															<!-- 结束 孙子菜单 -->
		                    							</c:when>
		                    						</c:choose>
												</dd>
											</c:forEach>
											<!-- 结束 孩子菜单 -->
										</dl>
                 					</c:when>
                 				</c:choose>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- 结束 侧边菜单 -->

			<!-- 开始 页面标签 -->
			<div class="layadmin-pagetabs" id="LAY_app_tabs">
				<div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
				<div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
				<div class="layui-icon layadmin-tabs-control layui-icon-down">
					<ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
						<li class="layui-nav-item" lay-unselect>
							<a href="javascript:;"></a>
							<dl class="layui-nav-child layui-anim-fadein">
								<dd layadmin-event="closeThisTabs">
									<a href="javascript:;">关闭当前标签页</a>
								</dd>
								<dd layadmin-event="closeOtherTabs">
									<a href="javascript:;">关闭其它标签页</a>
								</dd>
								<dd layadmin-event="closeAllTabs">
									<a href="javascript:;">关闭全部标签页</a>
								</dd>
							</dl>
						</li>
					</ul>
				</div>
				<div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
					<ul class="layui-tab-title" id="LAY_app_tabsheader">
						<li lay-id="<%=ligent_itemName%>home/console" lay-attr="<%=ligent_itemName%>home/console" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
					</ul>
				</div>
			</div>
			<!-- 结束 页面标签 -->
			
			<!-- 开始 主体内容 -->
			<div class="layui-body" id="LAY_app_body">
				<div class="layadmin-tabsbody-item layui-show">
					<iframe src="<%=ligent_itemName%>home/console" frameborder="0" class="layadmin-iframe"></iframe>
				</div>
			</div>
			<!-- 结束 主体内容 -->
			
			<!-- 辅助元素，一般用于移动设备下遮罩 -->
			<div class="layadmin-body-shade" layadmin-event="shade"></div>
		</div>
	</div>
	
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
	
	<!-- 加载sockjs -->
	<script src="https://cdn.bootcss.com/sockjs-client/1.4.0/sockjs.min.js"></script>
	
	<!--扩展控件-->
	<script src="<%=ligent_unitPath%>adonis/comm/tool.js?v=<%=System.currentTimeMillis() %>"></script>
	<script src="<%=ligent_unitPath%>adonis/comm/util.js?v=<%=System.currentTimeMillis() %>"></script>
	<script src="<%=ligent_unitPath%>adonis/comm/layer.js?v=<%=System.currentTimeMillis() %>"></script>
	
    <script src="<%=ligent_unitPath%>adonis/main/index.js?v=<%=System.currentTimeMillis() %>"></script>
    
</body>

</html>
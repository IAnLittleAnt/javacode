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
      <div class="layui-col-md8">
        <div class="layui-row layui-col-space15">
        	
        	<div class="layui-col-md6">
            <div class="layui-card">
              <div class="layui-card-header">聊天系统</div>
              <div class="layui-card-body">
                
                <table class="layui-table">
              <colgroup>
                <col width="100">
                <col>
              </colgroup>
              <tbody>
                <tr>
                  <td>基于框架</td>
                  <td>MySql+SSM+Socket</td>
                </tr>
                <tr>
                  <td>主要特色</td>
                  <td>文件管理</td>
                </tr>
                <tr>
                  <td>我的优点</td>
                  <td>适用于当前权限系统</td>
                </tr>
                <tr>
                  <td>案例下载</td>
                  <td style="padding-bottom: 0;">
                    <div class="layui-btn-container">
                      <a href="https://adonis.blog.csdn.net/article/details/111885696" target="_blank" class="layui-btn layui-btn-danger">查看教程</a>
                      <a href="http://layui.oiio.xin/home/index" target="_blank" class="layui-btn">案例下载</a>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
              </div>
            </div>
          </div>
        	<div class="layui-col-md6">
            <div class="layui-card">
              <div class="layui-card-header">权限系统</div>
              <div class="layui-card-body">
                
                <table class="layui-table">
              <colgroup>
                <col width="100">
                <col>
              </colgroup>
              <tbody>
                <tr>
                  <td>基于框架</td>
                  <td>MySql+SSM</td>
                </tr>
                <tr>
                  <td>主要特色</td>
                  <td>零门槛 / 响应式 / 清爽 / 极简</td>
                </tr>
                <tr>
                  <td>我的优点</td>
                  <td>权限管理个性化极高</td>
                </tr>
                <tr>
                  <td>案例下载</td>
                  <td style="padding-bottom: 0;">
                    <div class="layui-btn-container">
                      <a href="https://adonis.blog.csdn.net/article/details/111952112" target="_blank" class="layui-btn layui-btn-danger">查看教程</a>
                      <a href="http://layui.oiio.xin/home/index" target="_blank" class="layui-btn">案例下载</a>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
              </div>
            </div>
          </div>
        	
        	
          <div class="layui-col-md6">
            <div class="layui-card">
              <div class="layui-card-header">快捷方式</div>
              <div class="layui-card-body">
                
                <div class="layui-carousel layadmin-carousel layadmin-shortcut">
                  <div carousel-item>
                    <ul class="layui-row layui-col-space10">
                      <li class="layui-col-xs3">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});">
                          <i class="layui-icon layui-icon-console"></i>
                          <cite>主页一</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});">
                          <i class="layui-icon layui-icon-chart"></i>
                          <cite>主页二</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});">
                          <i class="layui-icon layui-icon-template-1"></i>
                          <cite>弹层</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});">
                          <i class="layui-icon layui-icon-chat"></i>
                          <cite>聊天</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});">
                          <i class="layui-icon layui-icon-find-fill"></i>
                          <cite>进度条</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});">
                          <i class="layui-icon layui-icon-survey"></i>
                          <cite>工单</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});">
                          <i class="layui-icon layui-icon-user"></i>
                          <cite>用户</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>设置</cite>
                        </a>
                      </li>
                    </ul>
                    <ul class="layui-row layui-col-space10">
                      <li class="layui-col-xs3">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                    </ul>
                    
                  </div>
                </div>
                
              </div>
            </div>
          </div>
          <div class="layui-col-md6">
            <div class="layui-card">
              <div class="layui-card-header">待办事项</div>
              <div class="layui-card-body">

                <div class="layui-carousel layadmin-carousel layadmin-backlog">
                  <div carousel-item>
                    <ul class="layui-row layui-col-space10">
                      <li class="layui-col-xs6">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});" class="layadmin-backlog-body">
                          <h3>待审评论</h3>
                          <p><cite>66</cite></p>
                        </a>
                      </li>
                      <li class="layui-col-xs6">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});" class="layadmin-backlog-body">
                          <h3>待审帖子</h3>
                          <p><cite>12</cite></p>
                        </a>
                      </li>
                      <li class="layui-col-xs6">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});" class="layadmin-backlog-body">
                          <h3>待审商品</h3>
                          <p><cite>99</cite></p>
                        </a>
                      </li>
                      <li class="layui-col-xs6">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});" class="layadmin-backlog-body">
                          <h3>待发货</h3>
                          <p><cite>20</cite></p>
                        </a>
                      </li>
                    </ul>
                    <ul class="layui-row layui-col-space10">
                      <li class="layui-col-xs6">
                        <a href="javascript:;" class="layadmin-backlog-body">
                          <h3>待审友情链接</h3>
                          <p><cite style="color: #FF5722;">5</cite></p>
                        </a>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="layui-col-md12">
            <div class="layui-card">
              <div class="layui-card-header">数据概览</div>
              <div class="layui-card-body">
                
                <div class="layui-carousel layadmin-carousel layadmin-dataview" data-anim="fade" lay-filter="LAY-index-dataview">
                  <div carousel-item id="LAY-index-dataview">
                    <div><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>
                    <div></div>
                    <div></div>
                  </div>
                </div>
                
              </div>
            </div>
            <div class="layui-card">
              <div class="layui-tab layui-tab-brief layadmin-latestData">
                <ul class="layui-tab-title">
                  <li class="layui-this">今日热搜</li>
                  <li>今日热帖</li>
                </ul>
                <div class="layui-tab-content">
                  <div class="layui-tab-item layui-show">
                    <table id="LAY-index-topSearch">1</table>
                  </div>
                  <div class="layui-tab-item">
                    <table id="LAY-index-topCard">2</table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <div class="layui-col-md4">
        <div class="layui-card">
          <div class="layui-card-header">猜您系统</div>
          <div class="layui-card-body layui-text">
            <table class="layui-table">
              <colgroup>
                <col width="100">
                <col>
              </colgroup>
              <tbody>
                <tr>
                  <td>开发中</td>
                  <td>文件管理系统</td>
                </tr>
                <tr>
                  <td>开发中</td>
                  <td>文件管理系统</td>
                </tr>
                <tr>
                  <td>开发中</td>
                  <td>文件管理系统</td>
                </tr>
                <tr>
                  <td></td>
                  <td style="padding-bottom: 0;">
                    <div class="layui-btn-container">
                      <a href="http://layui.oiio.xin/home/index" target="_blank" class="layui-btn">案例下载</a>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        
        <div class="layui-card">
          <div class="layui-card-header">效果报告</div>
          <div class="layui-card-body layadmin-takerates">
            <div class="layui-progress" lay-showPercent="yes">
              <h3>转化率（日同比 28% <span class="layui-edge layui-edge-top" lay-tips="增长" lay-offset="-15"></span>）</h3>
              <div class="layui-progress-bar" lay-percent="65%"></div>
            </div>
            <div class="layui-progress" lay-showPercent="yes">
              <h3>签到率（日同比 11% <span class="layui-edge layui-edge-bottom" lay-tips="下降" lay-offset="-15"></span>）</h3>
              <div class="layui-progress-bar" lay-percent="32%"></div>
            </div>
          </div>
        </div>
        
        <div class="layui-card">
          <div class="layui-card-header">实时监控</div>
          <div class="layui-card-body layadmin-takerates">
            <div class="layui-progress" lay-showPercent="yes">
              <h3>CPU使用率</h3>
              <div class="layui-progress-bar" lay-percent="58%"></div>
            </div>
            <div class="layui-progress" lay-showPercent="yes">
              <h3>内存占用率</h3>
              <div class="layui-progress-bar layui-bg-red" lay-percent="90%"></div>
            </div>
          </div>
        </div>
        
        <div class="layui-card">
          <div class="layui-card-header">
            作者心语
            <i class="layui-icon layui-icon-tips" lay-tips="要支持的噢" lay-offset="5"></i>
          </div>
          <div class="layui-card-body layui-text layadmin-text">
            <p>layui 是一款采用自身模块规范编写的前端 UI 框架，遵循原生 HTML/CSS/JS 的书写与组织形式，门槛极低，拿来即用。其外在极简，却又不失饱满的内在，体积轻盈，组件丰盈，从核心代码到 API 的每一处细节都经过精心雕琢，非常适合界面的快速开发。</p>
            <p>layui 首个版本发布于2016年金秋，她区别于那些基于 MVVM 底层的 UI 框架，却并非逆道而行，而是信奉返璞归真之道。准确地说，她更多是为服务端程序员量身定做，你无需涉足各种前端工具的复杂配置，只需面对浏览器本身，让一切你所需要的元素与交互，从这里信手拈来。 </p>
            <p>—— 贤心（<a href="https://www.layui.com/" target="_blank">layui.com</a>）</p>
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
	<script src="<%=ligent_unitPath%>adonis/comm/util.js?v=<%=System.currentTimeMillis() %>"></script>
	<script src="<%=ligent_unitPath%>adonis/comm/layer.js?v=<%=System.currentTimeMillis() %>"></script>
	
	<script>
  		layui.config({
    		base: ligent_unitPath //静态资源所在路径
  		}).extend({
    		index: 'lib/index' //主入口模块
  		}).use(['index', 'console'], function() {
  			$(".layui-progress-bar").attr("lay-percent","90%");
        	// 关闭关闭所有层
			layer_closeAll();
  		});
  	</script>
</body>
</html>
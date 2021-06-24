var setter, admin, form, element, router, search;

layui.config({
	base: ligent_unitPath // 静态资源所在路径
}).extend({
	index: 'lib/index' // 主入口模块
}).use(['index', 'element'], function() {
	// var $ = layui.$;
	setter = layui.setter,
	admin = layui.admin,
	form = layui.form,
	element = layui.element,
	router = layui.router(),
	search = router.search;
	
	
	// 根据浏览器和电脑分辨率获取iframe高度
	var clientHeight = getViewSizeWithScrollbar(false);
	layui.data(setter.tableName, {
        key: 'clientHeight',
        value: clientHeight
    });
	
	/*// 登录人编号
	var MrAdonis = layui.data(setter.tableName);
	layer_msg("你好用户"+MrAdonis.loginUserId+"，欢迎来电MrAdonis！", 1, 6, null);*/
	
	/* 设置左菜单第一个选中 */
	$("#LAY-system-side-menu li:first-child").addClass("layui-nav-itemed layui-this");
	//$("#LAY-system-side-menu li:first-child dd:first-child").addClass("layui-this");
	
	// 关闭加载层
	// layer_closeAll();
	
});

/**
 * 绑定jQuery事件
 */
$(document).ready(function() {
	/**
	 * 退出事件
	 */
	$('.Lay-index-login').on('click',function () {
		layui.data(setter.tableName, null); // 删除access_token
		window.location.href = ligent_itemName + "/main/login";
	});
	
});

/*function tabChange(layId){
	// 切换到指定Tab项
    element.tabChange('layadmin-system-side-menu', layId);
}

function tabOpen(action, ""){
	// 切换到指定Tab项
	index.openTabsPage('xxx.html','xx信息')
}*/


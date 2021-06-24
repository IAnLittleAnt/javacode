var iconCode;

layui.config({
	base: ligent_unitPath // 静态资源所在路径
}).extend({
	index: 'lib/index' // 主入口模块
}).use(['index', 'code'], function() {
	layui.code({
		elem: 'pre'
	});

	/**
	 * 点击图标框事件
	 */
	$('.site-doc-icon li').on('click',function () {
		$(".code-name").attr("style", "color:#333;");
		var icon = $(this).children().eq(2);
		icon.attr("style", "color:red;");
		iconCode = icon.html();
	});
	
});

/**
 * 返回的参数
 */
function callbackdata(){
	// 
	var data = {
			iconCode : iconCode.replace('&amp;',"&")
	}
	return data;
}
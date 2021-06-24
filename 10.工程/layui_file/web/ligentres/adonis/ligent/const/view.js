// layui模块
var _form;
// 当前操作的数据编号
var _currentKeyId;
// 初始化本页面数据
var _currentDate = {
		keyName: 'constId',		// 对象编号名
		list: ligent_itemName+'ligent/const/list',			// 表格页面路径
		edit: ligent_itemName+'ligent/const/edit',			// 编辑页面路径
		update: ligent_itemName+'ligent/const/updateConst'	// 编辑记录
};

layui.config({
	base: ligent_unitPath // 静态资源所在路径
}).extend({
	index: 'lib/index' // 主入口模块
}).use(['index', 'form'], function() {
	_form = layui.form,
	
	// 更新渲染表单
	_form.render();
	
	// 绑定报告周期选择事件
	_form.on('radio(isDiringCode)', function (data) {
		var constValue = data.value;
		var data = {
				constKey : $("#constKey").val(),
        		constValue : constValue
        }
        var action = ligent_itemName + "ligent/const/updateValue";
        $.post(action, data, function (data) {
			var json = JSON.parse(data);
			if(json.code == 0) {
				layer_msg(json.msg, 1, 2, null);
			} else {
				layer_msg(json.msg, 5, null, null);
			}
	    });
	});
	
});

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
	_currentKeyId = $("#"+_currentDate.keyName).val();
	
	/**
	 * 监听右上角按钮工具
	 */
	$('body').on('click', '.layui-btn-event', function(e){
		var type = $(this).data('type');
		active[type] ? active[type].call(this, e.target) : '';
	});
	var active = {
		revert: function(e) { 
			/** 返回 */
			layer_loading();
			window.location.href = _currentDate.list;
		},
		reset: function(e) { 
			/** 重置 */
			layer_loading();
			var action = _currentDate.edit+'?'+_currentDate.keyName+'='+_currentKeyId;
			window.location.href = action;
		},
		submit: function(e) { 
			/** 提交 */
			var constKey = $('input[name="constKey"]').val();
			if(!$.isValid(constKey)){
				layer_alert("请输入正确的常量编码!", 5, null);
				return;
			}
			var constName = $('input[name="constName"]').val();
			if($.isEmpty(constName)){
				layer_alert("常量名称不能为空!", 5, null);
				return;
			}
			var constValue = $('input[name="constValue"]').val();
			if($.isEmpty(constValue)){
				layer_alert("常量值不能为空!", 5, null);
				return;
			}
			
			layer_loading();
			var form = new FormData(document.getElementById("MyForm"));
			$.ajax({
	            url : _currentDate.update,
	            type : "post",
	            data : form,
	            processData : false,
	            contentType : false,
	            success : function(data){
	        		var json = JSON.parse(data);
	        		if(json.code == 0) {
	        			layer_msg(json.msg, 1, 1, _currentDate.list);
	        		} else {
	        			layer_closeAll();
	        			layer_msg(json.msg, 5, null, null);
	        		}
	            },
	            error : function(e){
        			layer_closeAll();
        			layer_msg('操作失败,请稍后再试!', 5, null, null);
	            }
	        });
		},
	};
	
});

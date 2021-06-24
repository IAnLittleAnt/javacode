// layui模块
var _table, _laydate;
//当前操作的数据编号
var _currentKeyId;
// 初始化本页面数据
var _currentDate = {
		keyName: 'userId',		// 对象编号名
		edit: ligent_itemName+'auth/user/editMine',		// 编辑页面路径
		update: ligent_itemName+'auth/user/updateUser'	// 编辑记录
};

layui.config({
	base: ligent_unitPath // 静态资源所在路径
}).extend({
	index: 'lib/index' // 主入口模块
}).use(['index', 'form', 'table', 'laydate'], function() {
	_table = layui.table,
	_laydate = layui.laydate,
	_currentKeyId = $("#"+_currentDate.keyName).val();
	
	// 初始化日期并赋值
	_laydate.render({
    	elem: '.layDate'
    });
	
	/**
	 * 监听右上角按钮工具
	 */
	$('body').on('click', '.layui-btn-event', function(e){
		var type = $(this).data('type');
		active[type] ? active[type].call(this, e.target) : '';
	});
	var active = {
		reset: function(e) { 
			/** 重置 */
			layer_loading();
			var action = _currentDate.edit+'?'+_currentDate.keyName+'='+_currentKeyId;
			window.location.href = action;
		},
		submit: function(e) { 
			/** 提交 */
			var userName = $('input[name="userName"]').val();
			if($.isEmpty(userName)){
    			layer_msg('员工姓名不能为空!', 5, null, null);
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
	        			var action = _currentDate.edit+'?'+_currentDate.keyName+'='+_currentKeyId;
	        			layer_msg(json.msg, 1, 1, action);
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

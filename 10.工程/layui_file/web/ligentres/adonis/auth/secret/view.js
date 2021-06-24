// layui模块
var _form;
// 初始化本页面数据
var _currentDate = {
		view: ligent_itemName+'auth/secret/view',			// 添加页面路径
		handle: ligent_itemName+'auth/secret/handleSecret'	// 新增记录
};

layui.config({
	base: ligent_unitPath // 静态资源所在路径
}).extend({
	index: 'lib/index' // 主入口模块
}).use(['index', 'form', 'laydate'], function() {
	_form = layui.form,
	
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
			var parentId = $("#parentId").val();
			window.location.href = _currentDate.view;
		},
		submit: function(e) { 
			/** 提交 */
			var emptyCount = 0;
			$('input[name="titles"]').each(function(){
				　　　if($.isEmpty(this.value)){
						emptyCount++;
				　　　}
				});
			if(emptyCount>0){
				layer_alert("提交失败，请完成所有问题再提交!", 5, null);
				return;
			}
			$('input[name="answers"]').each(function(){
				　　　if($.isEmpty(this.value)){
						emptyCount++;
				　　　}
				});
			if(emptyCount>0){
				layer_alert("提交失败，请完成所有答案再提交!", 5, null);
				return;
			}
			
			layer_loading();
			var form = new FormData(document.getElementById("MyForm"));
			$.ajax({
	            url : _currentDate.handle,
	            type : "post",
	            data : form,
	            processData : false,
	            contentType : false,
	            success : function(data){
	        		var json = JSON.parse(data);
	        		if(json.code == 0) {
	        			var menuId = $("#menuId").val();
	        			layer_msg(json.msg, 1, 1, _currentDate.view);
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

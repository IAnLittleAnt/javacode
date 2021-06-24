// layui模块
var _table, _form, _laydate;
// 表格对象、表格操作对象
var _tableIns, _tableCheckObj;
// 当前操作的数据编号
var _currentKeyId;
// 弹框提交标注（0刷新,1删除,2多行删除）
var _submitMark;
// 初始化本页面数据
var _currentDate = {
		tableName: '在线情况',		// 表格对象名
		keyName: 'userId',		// 对象编号名
		reset: ligent_itemName+'ligent/login/list',		// 页面刷新路径
		load: ligent_itemName+'ligent/login/findByLogin',	// 分页路径
};

layui.config({
	base: ligent_unitPath // 静态资源所在路径
}).extend({
	index: 'lib/index' // 主入口模块
}).use(['index', 'form', 'table', 'laydate'], function() {
	_table = layui.table,
	_form = layui.form,
	_laydate = layui.laydate;
	
	
	/**
	 * 渲染表格
	 */
	_tableIns = _table.render({
		elem: '#table-page',
	    height: getTableHeight(1),
		url: _currentDate.load,
		where: {
			  timeFrame: $('#timeFrame').val()
		},
		page: true,
		toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
	    defaultToolbar: ['filter', 'exports', 'print'],
		cols: [[
			 {field:'userName', title:'用户', align:'center', minWidth: 200}, 
			 {field:'userCode', title:'账号', align:'center', minWidth: 150}, 
			 {field:'sexName', title:'性别', align:'center', minWidth: 180}, 
			 {field:'loginState', title:'是否在线', align:'center', minWidth: 120, 
				 templet: function(res){
					 var stateName = res.loginState > 0 ? '<em class="c_red">在线</em>' : '<em>离线</em>';
					 return stateName;
				 }
			 }
		]],
        done: function (res, curr, count) {
        	/* 加载完毕事件 */
        	// 展示隐藏的模块
        	$(".toolButton").show();
        	// 监听排序事件
        	if(typeof tableSort != 'undefined' && tableSort instanceof Function)
        		tableSort();
        	// 关闭关闭所有层
			layer_closeAll();
        }
	});
	
	/**
	 * 监听表格头部工具栏事件
	 */
	_table.on('toolbar(table-filter)', function(obj){
		 switch(obj.event){
	      	case 'reset':
				/** 刷新页面 */
				layer_loading();
				window.location.href = _currentDate.reset;
	      		break;
		 };
	 });
	
	/**
	 * 监听右上角按钮工具
	 */
	$('body').on('click', '.layui-btn-event', function(e){
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
	var active = {
		search: function() { 
			/** 搜索 */
			resetTable();
		}
	};
	
});

/**
 * 弹框提交事件
 * @param layero 弹框doc对象
 */
function layerSubmit(layero){
	if(_submitMark == 0){
		// 刷新表格
		resetTable();
	}else{
		layer_alert("系统繁忙，请刷新后再试!", 5, null);
	}
}

/**
 * 树形下拉列表选中回调函数
 */
function zTreeCheck(){
	resetTable();
}

/**
 * 刷新
 */
function resetTable(){
	// $(".layui-laypage-btn")[0].click();
	_tableIns.reload({
		  where: { // 设定异步数据接口的额外参数
			  searchKey: $("#Lay-input-key").val(), 
			  roleId: $('input[name="Lay-select-role"]').val()
		  },
		  page: {
			  curr: 1 // 重新从第 1 页开始
		  }
	});
}

/**
 * 回车事件回调
 */
function enterEvent(){
	// 调用搜索点击事件
	$(".Lay-button-search").trigger("click");
}
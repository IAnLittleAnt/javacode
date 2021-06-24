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
		tableName: '操作日志',		// 表格对象名
		keyName: 'logId',		// 对象编号名
		reset: ligent_itemName+'base/log/list',		// 页面刷新路径
		load: ligent_itemName+'base/log/findByPage',	// 分页路径
};

layui.config({
	base: ligent_unitPath // 静态资源所在路径
}).extend({
	index: 'lib/index' // 主入口模块
}).use(['index', 'form', 'table', 'laydate'], function() {
	_table = layui.table,
	_form = layui.form,
	_laydate = layui.laydate;
	
	// 日期范围
    _laydate.render({
    	elem: '#timeFrame',
    	range: true
    });
	
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
			 {field:'content', title:'操作说明', align:'center', minWidth: 200}, 
			 {field:'userName', title:'操作人', align:'center', minWidth: 150}, 
			 {field:'ip', title:'IP地址', align:'center', minWidth: 180}, 
			 {field:'source', title:'操作终端', align:'center', minWidth: 120, 
				 templet: function(res){
					 /*操作终端（1后台，2安卓）*/
					 var stateName = res.source == 1 ? '后台' : '安卓';
					 return '<em>'+ stateName +'</em>';
				 }
			 }, 
			 /*{field:'logState', title:'状态', align:'center', minWidth: 120, 
				 templet: function(res){
					 var stateName = res.logState == 1 ? '成功' : '失败';
					 return '<em>'+ stateName +'</em>';
				 }
			 }, */
			 {field:'logTime', title:'操作时间', sort:true, align:'center', minWidth: 180}
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
 * 刷新表格
 */
function resetTable(){
	layer_loading();
	var data = {
			  searchKey: $("#Lay-input-key").val(), 
			  timeFrame: $('#timeFrame').val()
		}
	reloadTable(data);
}
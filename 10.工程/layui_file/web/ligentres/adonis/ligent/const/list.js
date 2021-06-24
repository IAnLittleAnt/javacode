// layui模块
var _table;
// 表格对象、表格操作对象
var _tableIns, _tableCheckObj;
// 当前操作的数据编号
var _currentKeyId;
// 弹框提交标注（0刷新,1删除,2多行删除）
var _submitMark;
// 初始化本页面数据
var _currentDate = {
		tableName: '常量',		// 表格对象名
		keyName: 'constId',		// 对象编号名
		reset: ligent_itemName+'ligent/const/list',			// 页面刷新路径
		load: ligent_itemName+'ligent/const/findByPage',	// 分页路径
		add: ligent_itemName+'ligent/const/add',			// 添加页面路径
		edit: ligent_itemName+'ligent/const/edit',			// 修改页面路径
		del: ligent_itemName+'ligent/const/deleteConst',	// 删除数据路径
};

layui.config({
	base: ligent_unitPath // 静态资源所在路径
}).extend({
	index: 'lib/index' // 主入口模块
}).use(['index', 'form', 'table'], function() {
	_table = layui.table;
	
	/**
	 * 渲染表格
	 */
	_tableIns = _table.render({
		elem: '#table-page',
	    height: getTableHeight(2),
		url: _currentDate.load,
		page: true,
		toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
	    defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
	    	title: '提示',
	    	layEvent: 'LAYTABLE_TIPS',
	      	icon: 'layui-icon-tips'
	    }],
		cols: [[
			 {field:'constKey', title:'常量编码', sort:true, minWidth:120, align:'center'}, 
			 {field:'constName', title:'常量名称', edit:'text', minWidth:200, align:'left', style:'cursor:pointer;'}, 
			 {field:'constValue', title:'常量值', edit:'text', minWidth:180, align:'left', style:'cursor:pointer;'}, 
			 {field:'valueType', title:'常量类型', width:100, align:'center', 
				 templet: function(res){
					 var typeName = res.valueType == 'int' ? '数字' : '字符串';
					 return '<em>'+ typeName +'</em>';
				 }
			 }, 
			 {field:'used', title:'使用场景', edit:'text', minWidth:180, align:'left', style:'cursor:pointer;', 
				 templet: function(res){
					 var used = $.isEmpty(res.used) || res.used.indexOf(",") < 0 ? res.used : (res.used.substring(0,res.used.indexOf(",")) + "...");
					 return '<em>'+ used +'</em>';
				 }
			 }, 
			 {field:'constId', title:'操作', minWidth:200, align:'center', toolbar:'#barTool'}
		]],
        done: function () {
        	// 展示隐藏的模块
        	$(".layui-elem-quote").show();
        	// 监听排序事件
        	if(typeof tableSort != 'undefined' && tableSort instanceof Function)
        		tableSort();
        	// 关闭关闭所有层
			layer_closeAll();
        }
	});
	

    /**
     * 监听单元格编辑
     */
    _table.on('edit(table-filter)', function(obj){
        var value = obj.value, 	// 得到修改后的值
        	data = obj.data, 	// 得到所在行所有键值
        	field = obj.field; 	// 得到字段
        // markId：0修改全部，1常量名称，2常量值，3使用场景
        var constName = null, constValue = null, used = null;
        if(field == "constName"){
        	markId = 1;
        	constName = value;
        }else if(field == "constValue"){
        	markId = 2;
        	constValue = value;
        }else if(field == "used"){
        	markId = 3;
        	used = value;
        }
        var data = {
        		constId : data.constId,
        		markId : markId,
        		constName : constName,
        		constValue : constValue,
        		used : used
        }
        var action = ligent_itemName + "ligent/const/updateConst";
        $.post(action, data, function (data) {
			var json = JSON.parse(data);
			if(json.code == 0) {
				layer_msg(json.msg, 1, 2, null);
			} else {
				layer_msg(json.msg, 5, null, null);
			}
	    });
    });
    
    /**
	 * 监听表格头部工具栏事件
	 */
	_table.on('toolbar(table-filter)', function(obj){
		 switch(obj.event){
	      	case 'add':
				/** 新增 */
				layer_loading();
				window.location.href = _currentDate.add;
	      		break;
	      	case 'reset':
				/** 刷新页面 */
				layer_loading();
				window.location.href = _currentDate.reset;
	      		break;
		 };
	 });
	
	/**
	 * 监听表格行工具
	 */
	_table.on('tool(table-filter)', function(obj){ // 注：tool是工具条事件名，table-filter是table原始容器的属性 lay-filter="对应的值"
		_tableCheckObj = obj;
		var data = _tableCheckObj.data; //	获得当前行数据
		var layEvent = _tableCheckObj.event; // 获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = _tableCheckObj.tr; //	获得当前行 tr 的DOM对象
		// 获取当前行用户编号
		_currentKeyId = data[_currentDate.keyName];
		
		if(layEvent === 'del'){
			/** 删除 */
			_submitMark = 1;
			delRecord();
		} else if(layEvent === 'edit'){
			/** 编辑 */
			layer_loading();
			var action = _currentDate.edit+'?'+_currentDate.keyName+'='+_currentKeyId;
			window.location.href = action;
		}
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
	}else if(_submitMark == 1){
		/** 删除数据 */
		layer_closeAll();
		layer_msg("删除成功!", 1, 1, null);
		// 删除对应行（tr）的DOM结构，并更新缓存
		_tableCheckObj.del();
	}else if(_submitMark == 2){
		/** 多数据删除 */
		_submitMark = 0;
		layer_closeAll();
		layer_msg("删除成功!", 1, 1, "#");
	}else{
		layer_alert("系统繁忙，请刷新后再试!", 5, null);
	}
}


/**
 * 刷新
 */
function resetTable(){
	layer_loading();
	var data = {
			  searchKey: $("#Lay-input-key").val()
		}
	reloadTable(data);
}

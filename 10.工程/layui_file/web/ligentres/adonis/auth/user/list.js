var _setter, admin, form, table;
// 表格对象、表格操作对象
var tableIns, tableCheckObj;
// 当前操作的数据编号
var currentKeyId;
// 弹框提交标注（1新增，2编辑,3删除,4多行删除）
var submitMark;
// 弹层
var layerIndex;

layui.config({
	base: ligent_unitPath // 静态资源所在路径
}).extend({
	index: 'lib/index' // 主入口模块
}).use(['index', 'form', 'table'], function() {
	_setter = layui.setter,
	admin = layui.admin,
	form = layui.form,
	table = layui.table;
	
	/**
	 * 渲染表格
	 */
	tableIns = table.render({
		elem: '#table-page',
		url: ligent_itemName + 'auth/user/findByPage',
		page: true,
		toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
	    defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
	    	title: '提示',
	    	layEvent: 'LAYTABLE_TIPS',
	      	icon: 'layui-icon-tips'
	    }],
		cols: [[
			 {type:'checkbox'}, 
			 {field:'userName', title:'用户名', sort:true, align:'center', minWidth: 120}, 
			 {field:'userCode', title:'用户账号', align:'center', minWidth: 120, style:'cursor: pointer;'}, 
			 {field:'isFrozen', title:'用户状态', sort:true, align:'center', minWidth: 80, 
				 templet: function(res){
					 var frozenName = res.isFrozen == 0 ? '正常' : '已冻结';
					 return '<em>'+ frozenName +'</em>';
				 }
			 }, 
			 {field:'createTime', title:'创建时间', sort:true, align:'center', minWidth: 180}, 
			 {field:'userId', title:'操作', align:'center', minWidth: 230, toolbar: '#barTool'}
		]],
        done: function () {
        	/* 加载完毕事件 */
        	// 展示隐藏的模块
        	$(".toolButton").show();
        	// 关闭关闭所有层
			layer_closeAll();
        }
	});
	
	/**
	 * 监听排序事件
	 */
	table.on('sort(table-filter)', function(obj){
		table.reload('table-page', {
			initSort: obj, //记录初始排序，如果不设的话，将无法标记表头的排序状态。
			where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
				field: obj.field, //当前排序的字段名
				order: obj.type //当前排序类型：desc（降序）、asc（升序）、null（空对象，默认排序）
			}
		});
	});
	
	/**
	 * 监听头工具栏事件
	 */
	 table.on('toolbar(table-filter)', function(obj){
		 switch(obj.event){
	      	case 'add':
				/** 新增用户 */
				submitMark = 1;
				currentKeyId = 0;
				layerIndex = layer_open_div("新增用户信息", "#Lay-modal-add");
	      		break;
	      	case 'dels':
				/** 删除选中数据 */
				submitMark = 4;
				var checkStatus = table.checkStatus('table-page'),
					data = checkStatus.data;
				var chk_values = []; 
				$.each(data, function (i, item) {
					chk_values.push(item.userId);
		        });
				var menuCount = chk_values.length;
				if(menuCount == 0){
		        	layer_msg("至少选择一行进行删除！", 5, 6, null);
		        	return;
				}
				var userIds = chk_values.join(',');
				var hint = "确定要删除["+menuCount+"]条用户信息吗？";
				var action = ligent_itemName + "auth/user/deleteUsers";
				var data = {
						userIds: userIds
					};
				layer_confirm(hint, action, data, null);
	      		break;
	      	case 'reset':
				/** 无缓存刷新页面 */
				layer_loading();
				window.location.href = ligent_itemName + "auth/user/list";
	      		break;
		 };
	 });
	
	/**
	 * 监听Table行按钮工具
	 */
	table.on('tool(table-filter)', function(obj){ // 注：tool是工具条事件名，table-filter是table原始容器的属性 lay-filter="对应的值"
		tableCheckObj = obj;
		var data = tableCheckObj.data; //	获得当前行数据
		var layEvent = tableCheckObj.event; // 获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = tableCheckObj.tr; //	获得当前行 tr 的DOM对象
		// 获取当前行用户编号
		currentKeyId = data.userId;
		
		if(layEvent === 'friend'){
			/** 查看 */
			// layer_msg("开发中", 5, 6, null);
			parent.layui.layim.add({
			    type: 'friend'
			        ,username: '麻花疼'
			        ,avatar: '//tva1.sinaimg.cn/crop.0.0.720.720.180/005JKVuPjw8ers4osyzhaj30k00k075e.jpg'
			        ,submit: function(group, remark, index){
			            layer.msg('好友申请已发送，请等待对方确认', {
			            	icon: 1
			            	,shade: 0.5
			            }, function(){
			            	layer.close(index);
			            });
			        }
				});
		} else if(layEvent === 'del'){
			/** 删除 */
			layer_closeAll();
			// 删除对应行（tr）的DOM结构，并更新缓存
			tableCheckObj.del();
			
			submitMark = 3;
			var hint = "确定要删除该用户信息吗？";
			var action = ligent_itemName + "auth/user/deleteUsers";
			var data = {
					userIds: currentKeyId
				};
			layer_confirm(hint, action, data, null);
		} else if(layEvent === 'edit'){
			/** 编辑 */
			submitMark = 2;
			layerIndex = layer_open_div("编辑用户信息", "#Lay-modal-edit");
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
	
	/** 角色下拉列表选择事件 */
	/*form.on('select(Lay-select-role)', function(data){
		resetTable();
	});*/
	
});


/**
 * 弹框初始化事件
 * @param layero 弹框doc对象
 */
function layerInitialise(layero){
	if(submitMark == 1){
		/** 新增数据 */
		form.render(null, 'Lay-modal-add');
	}else if(submitMark == 2){
		/** 编辑数据 */
		var action = ligent_itemName + "auth/user/fainById";
		data = {
				userId: currentKeyId
			};
		$.post(action, data, function (res) {
			var json = JSON.parse(res);
			if(json.code == 0) {
				var item = json.data;
				var roleIds = item.roleIds;
				if($.isEmpty(roleIds)){
					
				}else if($.isValid(roleIds)){
					$(layero).find('input[name="roleIds"][value="'+roleIds+'"]').attr("checked", "true");
				}else{
					$(layero).find('input[name="roleIds"]').each(function(){
						var role = $(this);
						for(j=0;j<roleIds.length;j++){
				            if(role.val() == roleIds[j]){
				            	role.attr("checked", "true");
				                break
				            }
				        }
					});
				}
				$(layero).find('input[name="userName"]').val(item.userName);
				// 更新渲染表单
				form.render('checkbox', 'Lay-modal-edit');
			}
	    });
	}
}


/**
 * 弹框提交事件
 * @param layero 弹框doc对象
 */
function layerSubmit(layero){
	if(submitMark == 1){
		/** 新增数据 */
		var chk_values = []; 
		$(layero).find('input[name="roleIds"]:checked').each(function(){
		　　　　chk_values.push($(this).val()); 
		});
		if(chk_values.length == 0){
			layer_alert("请选择用户角色!", 5, null);
        	return;
		}
		var userCode = $(layero).find('input[name="userCode"]').val();
		if(!$.isMobile(userCode)){
			layer_alert("用户账号格式不正确!", 5, null);
			return;
		}
		var userName = $(layero).find('input[name="userName"]').val();
		if($.isEmpty(userName)){
			layer_alert("用户姓名不能为空!", 5, null);
			return;
		}
		
		layer_closeAll();
		layer_loading();
		var roleIds = chk_values.join(',');
		
		var action = ligent_itemName + "auth/user/insertUser";
		var data = {
				userCode: userCode,
				userName: userName,
				roleIds: roleIds
			};
		$.post(action, data, function (data) {
			var json = JSON.parse(data);
			if(json.code == 0) {
				layer_msg(json.msg, 1, 1, ligent_itemName + "auth/user/list");
			} else {
				layer_msg(json.msg, 5, null, null);
			}
			layer.close(layerIndex);
	    });
	}else if(submitMark == 2){
		/** 编辑数据 */
		var chk_values = []; 
		$(layero).find('input[name="roleIds"]:checked').each(function(){
		　　　　chk_values.push($(this).val()); 
		});
		if(chk_values.length == 0){
			layer_alert("请选择用户角色!", 5, null);
        	return;
		}
		var userName = $(layero).find('input[name="userName"]').val();
		if($.isEmpty(userName)){
			layer_alert("用户姓名不能为空!", 5, null);
			return;
		}
		
		if(currentKeyId==1||currentKeyId==2){
			layer_alert("啦啦啦，您这个臭小子改不了我的信息!", 5, null);
			return;
		}
		
		layer_closeAll();
		layer_loading();
		var roleIds = chk_values.join(',');
		
		var action = ligent_itemName + "auth/user/updateUser";
		var data = {
				userId: currentKeyId,
				userName: userName,
				roleIds: roleIds
			};
		$.post(action, data, function (data) {
			var json = JSON.parse(data);
			if(json.code == 0) {
				layer_msg(json.msg, 1, 1, null);
				// 同步更新缓存对应的值
				tableCheckObj.update({
					userName: userName
				});
			} else {
				layer_msg(json.msg, 5, null, null);
			}
			layer.close(layerIndex);
	    });
	}else if(submitMark == 3){
		/** 删除数据 */
		layer_closeAll();
		layer_msg("删除成功!", 1, 1, null);
		// 删除对应行（tr）的DOM结构，并更新缓存
		tableCheckObj.del();
	}else if(submitMark == 4){
		/** 多数据删除 */
		layer_closeAll();
		layer_msg("删除成功!", 1, 1, null);
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
	tableIns.reload({
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



/**
 * 监听排序事件
 */
function tableSort(){
	_table.on('sort(table-filter)', function(obj){
		_table.reload('table-page', {
			initSort: obj, //记录初始排序，如果不设的话，将无法标记表头的排序状态。
			where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
				field: obj.field, //当前排序的字段名
				order: obj.type //当前排序类型：desc（降序）、asc（升序）、null（空对象，默认排序）
			}
		});
	});
}

/**
 * 单一删除数据
 */
function delRecord(){
	var hint = "确定要删除该"+_currentDate.tableName+"信息吗？";
	var data = {
			keyIds: _currentKeyId
		};
	layer_confirm(hint, _currentDate.del, data, null);
}

/**
 * 批量删除数据
 */
function delRecords(){
	var checkStatus = _table.checkStatus('table-page'),
		data = checkStatus.data;
	var chk_values = []; 
	$.each(data, function (i, item) {
		chk_values.push(item[_currentDate.keyName]);
	});
	var keyCount = chk_values.length;
	if(keyCount == 0){
		layer_msg("至少选择一行进行删除！", 5, 6, null);
		return;
	}
	var keyIds = chk_values.join(',');
	var hint = "确定要删除["+keyCount+"]条"+_currentDate.tableName+"信息吗？";
	var data = {
			keyIds: keyIds
		};
	layer_confirm(hint, _currentDate.del, data, null);
}


/**
 * 树形下拉列表选中回调函数
 */
function zTreeCheck(){
	resetTable();
}

/**
 * 刷新表格
 */
function reloadTable(data){
	// $(".layui-laypage-btn")[0].click();
	_tableIns.reload({
		  where: data, // 设定异步数据接口的额外参数,
		  page: {
			  curr: 1 // 重新从第 1 页开始
		  },
	      done: function () {
	    	  // 保存分页信息到Cookie
	    	  if(typeof putPageCookie != 'undefined' && putPageCookie instanceof Function)
	    		  putPageCookie();
	    	  // 保存条件信息到Cookie
	    	  if(typeof setWhereCookie != 'undefined' && setWhereCookie instanceof Function)
	    		  setWhereCookie();
	    	  // 初始化进度条
	    	  if(typeof _element != 'undefined' && _element){
	    		  _element.render('progress');
	    	  }
	    	  // 关闭关闭所有层
	    	  layer_closeAll();
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




/**
 * 保存分页信息到Cookie
 */
function putPageCookie(){
	if(typeof _currentDate.cookieKey != 'undefined' && _currentDate.cookieKey){
		//获取当前页
		var curr = $(".layui-laypage-skip .layui-input").val();
		//获取当前页条数
		var limit = $(".layui-laypage-limits").find("option:selected").val();
		
		if(!$.isValid(curr)){
			curr = 1;
		}
		if(!$.isValid(limit)){
			limit = 20;
		}
		setCookie("currentPageCurr_" + _currentDate.cookieKey, curr, 30);
		setCookie("currentPageLimit_" + _currentDate.cookieKey, limit, 30);  
	}
}

/**
 * 获取Cookie分页信息
 */
function gainPageCookie(){
	if(typeof _currentDate.cookieKey != 'undefined' && _currentDate.cookieKey){
		// 判断是否有cookie
		var curr = getCookie("currentPageCurr_" + _currentDate.cookieKey);
		var limit = getCookie("currentPageLimit_" + _currentDate.cookieKey);
		
		if(!$.isValid(curr)){
			curr = 1;
		}
		if(!$.isValid(limit)){
			limit = 20;
		}
		var data = {
				curr: curr,
				limit: limit,
			}
		return data;
	}
	return null;
}

/**
 * 保存条件信息到Cookie
 */
function putWhereCookie(whereNames, whereTypes){
	if(typeof _currentDate.cookieKey != 'undefined' && _currentDate.cookieKey){
		var cookieName = "currentWhere_"+_currentDate.cookieKey+"_";
		for(j = 0,len=whereNames.length; j < len; j++) {
		    var whereName = whereNames[j];
		    var whereValue = $(whereTypes[j]+"[name='"+whereName+"']").val();
		    if(!$.isEmpty(whereValue)){
		    	whereValue = _base64.encode(whereValue);
			}
			setCookie(cookieName+whereName, whereValue, 30);
		}
	}
}

/**
 * 获取Cookie条件信息
 */
function gainWhereCookie(whereNames, whereValues, whereTypes){
	if(typeof _currentDate.cookieKey != 'undefined' && _currentDate.cookieKey){
	    var cookieName = "currentWhere_"+_currentDate.cookieKey+"_";
		for(j = 0,len=whereNames.length; j < len; j++) {
		    var whereName = whereNames[j];
		    var whereValue = whereValues[j];
			var cookieValue = getCookie(cookieName+whereName);
			if(!$.isEmpty(cookieValue)){
				cookieValue = _base64.decode(cookieValue);
			}
			if($.isNumber(whereValue)){
				cookieValue = $.isNumber(cookieValue) ? cookieValue : whereValue;
			}else{
				cookieValue = $.isEmpty(cookieValue) ? whereValue : cookieValue;
			}
			$(whereTypes[j]+"[name='"+whereName+"']").val(cookieValue);
		}
	}
}
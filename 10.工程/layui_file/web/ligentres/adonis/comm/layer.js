
/**
 * Layer工具类
 * @author 10011037@qq.com
 * 2016-02-12
 * 来源于 https://www.layui.com/doc/modules/layer.html
 */


/**
 * 疯狂模式，关闭所有层
 */
function layer_closeAll(){
	/*layer.closeAll();
	top.layer.closeAll();*/
	layer.closeAll('loading');
	if(top.layer)
		top.layer.closeAll('loading');
	setTimeout(function() {
		layer.closeAll('loading');
		if(top.layer)
			top.layer.closeAll('loading');
	}, 0);
}

/**
 * 疯狂模式，关闭所有层
 */
function layer_closeLoading(){
	layer.closeAll('loading');
	if(top.layer)
		top.layer.closeAll('loading');
	setTimeout(function() {
		layer.closeAll('loading');
		if(top.layer)
			top.layer.closeAll('loading');
	}, 0);
}

/**
 * 关闭固定层
 */
function layer_close(index){
	layer.close(index);
}

/**
 * 打开Loading
 */
function layer_loading(){
	var index = layer.load(0, {
		  shade: [0.2, '#fff'] //0.2透明度的白色背景
		});
	return index;
}

/**
 * 信息框
 * @param hint 头部提示内容
 * @param icon 提示图标：默认不显示图标(0-6)
 * @param time 自动消失时间(秒)
 * @param url 自动跳转路径
 */
function layer_msg(hint, icon, time, url){
	icon = $.isValid(icon) ? icon : null;
	time = $.isValid(time) ? 1000 * time : null;
	// offset = $.isEmpty(time) ? '15px' : 'auto';
	var index = layer.msg(hint, {
		icon: icon,
		time: time,
		offset: '15px'
	}, function() {
		if($.isEmpty(url)){
			// 空不处理
		}else if(url.indexOf("#")>=0){
			// 回调函数
			if(typeof layerSubmit != 'undefined' && layerSubmit instanceof Function)
				layerSubmit();
		}else{
			// 跳转页面
			window.location.href = url;
		}
	});
	return index;
}

/**
 * 温馨提示
 * @param hint 提示内容
 * @param icon 提示图标：默认不显示图标(0-6)
 * @param url 确定后访问url,可以为空
 */
function layer_alert(hint, icon, url){
	icon = $.isValid(icon) ? icon : 1;
	var index = layer.alert(hint, {
	    icon: icon,
	    title: '温馨提示',
	    skin: 'layer-ext-moon'
	}, function(index){
		layer.close(index);
		if(!$.isEmpty(url)){
			layer_loading();
			// window.location.href = url;
			window.location = url;
		}
	}); 
	return index;
}

/**
 * div表弹框
 * @param hint 头部提示内容
 * @param icon 提示图标：默认不显示图标(0-6)
 */
function layer_open_div(hint, div){
	var MrAdonis = layui.data(_setter.tableName);
	var clientHeight = MrAdonis.clientHeight;
	var index = layer.open({
	     type: 1, 
	     title: hint, 
	     area: '620px', 
	     maxHeight: clientHeight, 
	     content: $(div).html(), 
	     btn: ['提交', '关闭'], 
		 success: function(layero){
			 // 加载完毕事件
			 if(typeof layerInitialise != 'undefined' && layerInitialise instanceof Function)
				 layerInitialise(layero);
	     },
		 yes: function(index, layero){
			 // 按钮1点击事件
			 if(typeof layerSubmit != 'undefined' && layerSubmit instanceof Function)
				 layerSubmit(layero);
	     },
	     btn2: function(index){
			 // 按钮2点击事件
	    	 layer.close(index);
	     }
	 });
	return index;
}

/**
 * 网页表弹框
 * @param hint 头部提示内容
 * @param action 页面路径
 */
function layer_open_page(hint, action){
	var index = layer.open({
	    type: 2, 
	    title: hint, 
	    area: ['70%', '80%'], 
	    content: action, 
	    btn: ['提交', '关闭'], 
		success: function(layero, index){
			// 加载完毕事件
			if(typeof layerInitialise != 'undefined' && layerInitialise instanceof Function)
				layerInitialise(layero);
	    },
		yes: function(index, layero){
			// 按钮1点击事件
			if(typeof layerSubmit != 'undefined' && layerSubmit instanceof Function)
				layerSubmit(layero);
	    },
	    btn2: function(index){
			// 按钮2点击事件
	    	layer.close(index);
	    }
	});
	return index;
}


/**
 * 询问框
 * @param hint 提示内容
 * @param action 请求后台的action
 * @param data 请求后台的参数
 * @param aim 请求后台成功后操作
 */
function layer_confirm(hint, action, data, aim){
	layer.confirm(hint, {
	    title: '温馨提示',
    	btn: ['确定','取消'] //按钮
	}, function(index){
		layer.close(index);
		layer_loading();
		// 删除操作
		$.post(action, data, function (res) {
			var json = JSON.parse(res);
			if (json.code == 0){
				if($.isEmpty(aim)){
					// 回调函数
					if(typeof layerSubmit != 'undefined' && layerSubmit instanceof Function)
						layerSubmit(json.msg);
				}else if(aim.indexOf("/")>=0){
					// 跳转页面
					layer_msg(json.msg, 1, 1, aim);
				}else if(aim.indexOf("#cue")>=0){
					// 提示操作
					layer_closeAll();
					layer_alert(json.msg, 1, null);
				}
			}else{
				layer_closeAll();
				layer_alert(json.msg, 3, null);
			}
	    });
	});
}
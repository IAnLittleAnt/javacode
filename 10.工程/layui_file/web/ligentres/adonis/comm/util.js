/**
 * 公共类
 * @author 10011037@qq.com
 * 2016-02-12
 */

/*// 10秒后自动关闭Loading加载层
var loadingInterval = setInterval(loadLoseEvent,1000*10);
function loadLoseEvent(){
	// 只在iframe子页面发挥作用
	var loading = parent.$(".loading-container");
	if(!loading){
		loading = parent.parent.$(".loading-container");
	}
	if(loading){
		loading.addClass("loading-inactive");
	}
	clearInterval(loadingInterval);
}*/

/**
 * 获取表格高度
 * label:标记(1:搜索+工具+分页，2:搜索+工具+分页+底部注意)
 */
function getTableHeight(label){
	var tableHeight = getViewSizeWithScrollbar(false);
	if(label==1){
		tableHeight = tableHeight - 45;
	}else if(label==2){
		tableHeight = tableHeight - 107;
	}
	return tableHeight;
}

/**
 * 根据浏览器和电脑分辨率获取iframe高度
 */
function getViewSizeWithScrollbar(isScreen){
	// 头部高度
	var headHeight = 90;
	var clientHeight = 0;
	if(isScreen){
		// 电脑分辨率-高度
		clientHeight = window.screen.height-headHeight;
	}else{
		// 包含滚动条
		if(window.innerWidth){
			clientHeight = window.innerHeight;
		}else if(document.documentElement.offsetWidth == document.documentElement.clientWidth){ 
			clientHeight = document.documentElement.offsetHeight;
		}else{ 
			clientHeight = document.documentElement.clientHeight + getScrollWith();
		} 
		clientHeight = clientHeight - headHeight;
	}
	return clientHeight;
}



/**
 * 监听键盘事件
 */
document.onkeydown = function mykeyDown(e){
    e = e||event;
    if(e.keyCode === 13){
    	/** 回车事件 */
		// 回调页面回车方法
    	if(typeof enterEvent != 'undefined' && enterEvent instanceof Function)
    		enterEvent();
    }else if((e.ctrlKey && e.keyCode === 82) || e.keyCode === 116) {
    	/** CTRL+R、F5刷新事件 */
		e.preventDefault();
		layer_loading();
		// layer_msg('刷新数据...', 6, 1, null);
		// 刷新当前展示的子页面
		parent.admin.events.refresh();
    }
}

/**
 * 当页面加载完毕时运行
 */
document.onreadystatechange = function() {
	if (document.readyState == "complete") {
		// 子头部面包屑
		var childObject = parent.$("#LAY-system-side-menu .layui-this a:first-child");
		var childHeader = $(".childHeader");
		if(childObject && childHeader && childObject.parent().html()){
			var rootMenu = childObject.parents(".layui-nav-item").children("a:first-child").children("cite").html();
			var childMenu = childObject.html();
			var html = '<a style="color:#333;" href="'+childObject.attr("lay-href")+'">'+childMenu+'</a>';
			childHeader.html(rootMenu + " / " + html);
		}
		
		// 关闭加载层
		// layer_closeAll();
		if(typeof isCloseLoading == 'undefined' || isCloseLoading){
			layer_closeAll();
			/*if(parent.layim){
				layer_closeAll();
			}*/
		}
	}
}

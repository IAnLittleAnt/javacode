// layui模块
var _setter, _form;
// 文件夹类型编号、文件类型小图标、文件类型图标
var _folderTypeId, _folderTypeIcon, _folderTypeIcong;
// 当前文件夹、文件用途、右击文件编号、右击文件类型、右击文件名称、右击文件后缀
var _paterId, _docuUse, _docuId, _typeId, _docuName, _docuSuffix;
// 剪切文件编号,排序方式(1类型,2名称,3日期)
var _cutDocuId,_sortMode;
// 弹框提交标注（1新增，2编辑，3备注内容），弹层
var _submitMark, _layerOpen;


layui.config({
	base: ligent_unitPath // 静态资源所在路径
}).extend({
	index: 'lib/index' // 主入口模块
}).use(['index'], function() {
	_setter = layui.setter;
	_form = layui.form;
	
	// 设置高度
	var clientHeight = document.documentElement.clientHeight-155;
	document.getElementById("fileCloud").style.height = clientHeight+"px";
	
	// 当前文件夹,剪切文件编号
	_paterId=0, _cutDocuId=0
	var sortModeArray = ["","类型","名称","日期"];
	// 获取文件夹类型编号
	_folderTypeId = $("#folderTypeId").val();
	_folderTypeIcon = $("#folderTypeIcon").val();
	_folderTypeIcong = $("#folderTypeIcong").val();
	_docuUse = $("#docuUse").val();

	
	/** 开始 点击事件 */
	let _debug = function(properties) {
		var key = properties.key;
		var iden = properties.iden;
		if(iden==3){
			/* 空白地方 */
			if("createFolder"==key){
				// 新增文件夹
				_submitMark = 1;
				_layerOpen = layer_open_div("新建文件夹", "#Lay-modal-folder");
			}else if("uploadFile"==key){
				// 上传文件
				uploadFile();
			}else if("pasteDocu"==key){
				// 粘贴
				pasteDocu(_cutDocuId);
				_cutDocuId = 0;
			}else if("refresh"==key){
				// 刷新
				bindingDocuList(_paterId);
			}else if("backRoot"==key){
				// 返回根目录
				bindingDocuList(0);
			}else if("backFront"==key){
				// 返回上一层
				backFront();
			}else if("sortType"==key){
				// 类型排序(1)
				_sortMode=1;
				setCookie("hasFileSortMode",_sortMode,100);
				$("#sortModeName").html(sortModeArray[_sortMode]);
				bindingDocuList(_paterId);
			}else if("sortName"==key){
				// 名称排序(2)
				_sortMode=2;
				setCookie("hasFileSortMode",_sortMode,100);
				$("#sortModeName").html(sortModeArray[_sortMode]);
				bindingDocuList(_paterId);
			}else if("sortDate"==key){
				// 日期排序(3)
				_sortMode=3;
				setCookie("hasFileSortMode",_sortMode,100);
				$("#sortModeName").html(sortModeArray[_sortMode]);
				bindingDocuList(_paterId);
			}
		}else{
			/* 文件夹、文件 */
			var docuId = rightClickElement['docuid'];
			// var typeId = rightClickElement['typeid'];
			if("openFolder"==key){
				// 打开文件夹
				bindingDocuList(docuId);
			}else if("openFile"==key){
				// 打开文件
				browseFile(docuId);
			}else if("downloadFolder"==key){
				// 压缩下载
				window.location.href = ligent_itemName + 'file/docu/downloadZips?docuId='+docuId;
			}else if("downloadFile"==key){
				// 下载文件
				downloadFile();
			}else if("cutDocu"==key){
				// 剪切
				_cutDocuId = docuId;
			}else if("updateName"==key){
				// 重命名
				_submitMark = 2;
				_layerOpen = layer_open_div("重命名", "#Lay-modal-folder");
			}else if("updateDesc"==key){
				// 备注内容
				_submitMark = 3;
				_layerOpen = layer_open_div("备注内容", "#Lay-modal-folder");
			}else if("deleteFile"==key){
				// 删除
				deleteFile();
			}
		}
	};
	// 初始化右键菜单
	initialiseMenu();
	/* 结束 点击事件 */
	
	
	/**
	 * 粘贴
	 */
	function pasteDocu(cutDocuId){
		var docuId = rightClickElement['docuid'];
		$.ajax({
			type: 'post',
			url: ligent_itemName + 'file/docu/updatePater',
			data: { 'docuId':cutDocuId, 'paterId': _paterId},
			dataType: 'json',
			success: function(res){
				if(res.code == 0) {
					bindingDocuList(_paterId);
				}else{
					layer_msg(res.msg, 5, 1, null);
				}
			}
		});
	}
	
	/**
	 * 删除文件/文件夹
	 */
	function deleteFile(){
		var typeId = rightClickElement['typeid'];
		var title = '确定删除该文件吗？';
		if(_folderTypeId==typeId){
			title = '确定删除该文件夹吗？';
		}
		layer.confirm(title, function(index){
			// 关闭窗口
			layer.close(index);
			var docuId = rightClickElement['docuid'];
			$.ajax({
				type : 'post',
				url : ligent_itemName + 'file/docu/deleteDocu',
				data : { 'docuId':docuId },
				dataType : 'json',
				success : function(res){
					if(res.code == 0) {
						$('.docu[docuId="'+docuId+'"]').parent().remove();
					}else{
						layer_msg(res.msg, 5, 1, null);
					}
				}
			});
    	});
	}

	/**
	 * 下载文件
	 */
	function downloadFile(){
		var docuId = rightClickElement['docuid'];
		window.location.href = ligent_itemName + "file/docu/download?docuId="+docuId;
	}
	
	/**
	 * 返回上一层
	 */
	function backFront(){
		if(_paterId==0){
			bindingDocuList(0);
		}else{
			$.ajax({
				type : 'post',
				url : ligent_itemName + 'file/docu/fainById',
				data : { 'docuId':_paterId },
				dataType : 'json',
				success : function(res){
					if(res.code == 0) {
						var docuId = res.data.paterId;
						bindingDocuList(docuId);
					}else{
						layer_msg(res.msg, 5, 1, null);
					}
				}
			});
		}
	}
	
	/**
	 * 上传文件
	 */
	function uploadFile(){
		var action = ligent_itemName + "file/docu/upload?paterId="+_paterId+"&docuUse="+_docuUse;
		var index = layer.open({
		     type: 2, 
		     title: '上传文件', 
		     area: ['70%', '80%'], 
				anim: 2,
				id: "layui-layim-chatlog",
		     content: action
		 });
	}
	
	/**
	 * 监听文件夹点击事件
	 */
	$('body').on('click', '.docuFolder', function(e){
		// 获取文件编号
		var docuId = $(this).attr("docuId");
		// 打开文件夹
		bindingDocuList(docuId);
	});
	
	/**
	 * 监听文件点击事件
	 */
	$('body').on('click', '.docuFile', function(e){
		// 获取文件编号
		var docuId = $(this).attr("docuId");
		// 打开文件
		browseFile(docuId);
	});
	
	/**
	 * 浏览文件
	 */
	function browseFile(docuId){
		$.ajax({
			type : 'post',
			url : ligent_itemName + 'file/docu/fainById',
			data : { 'docuId':docuId },
			dataType : 'json',
			success : function(res){
				if(res.code == 0) {
					var fileDocu = res.data;
					var fileUrl = fileDocu.docuPath;
					fileUrl = fileUrl.replace(/\//g,"\\");
				    if(fileDocu.browseMode >= 1 && fileDocu.browseMode <= 3){
			        	window.open(fileUrl);
				    }else{
						layer_msg('文件类型不支持在线预览', 6, 1, null);
				    }
				}else{
					layer_msg(res.msg, 1, 1, null);
				}
			}
		});
	}
	
	/**
	 * 初始化右键菜单
	 */
	function initialiseMenu(){
		// 文件夹
		$("#docuList .docuFolder").contextMenu({
			items: initialiseJson(1)
		});
		// 文件
		$("#docuList .docuFile").contextMenu({
			items: initialiseJson(2)
		});
		// 空白地方
		$("#fileCloud").contextMenu({
			items: initialiseJson(3)
		});
	}
	function initialiseJson(iden){
		let _menuItems;
		if(iden==1){
			// 文件夹
			_menuItems = [{
				type: "title",
				text: "文件夹"
			}, {
				type: "item",
				icon: "envelope-open-o",
				text: "打开",
				key: "openFolder",
				iden: iden,
				action: _debug
			}, {
				type: "item",
				icon: "download",
				text: "压缩下载",
				key: "downloadFolder",
				iden: iden,
				action: _debug
			}, {
				type: "divider"
			}, {
				type: "item",
				icon: "scissors",
				text: "剪切",
				key: "cutDocu",
				iden: iden,
				action: _debug
			}, {
				type: "item",
				icon: "pencil",
				text: "重命名",
				key: "updateName",
				iden: iden,
				action: _debug
			},
			{
				type: "item",
				icon: "commenting",
				text: "描述内容",
				key: "updateDesc",
				iden: iden,
				action: _debug
			},
			{
				type: "item",
				icon: "trash",
				text: "删除",
				key: "deleteFile",
				iden: iden,
				action: _debug
			}];
		}else if(iden==2){
			// 文件
			_menuItems = [{
				type: "title",
				text: "文件"
			}, {
				type: "item",
				icon: "search",
				text: "打开",
				key: "openFile",
				iden: iden,
				action: _debug
			}, {
				type: "item",
				icon: "download",
				text: "下载",
				key: "downloadFile",
				iden: iden,
				action: _debug
			}, {
				type: "divider"
			}, {
				type: "item",
				icon: "scissors",
				text: "剪切",
				key: "cutDocu",
				iden: iden,
				action: _debug
			}, {
				type: "item",
				icon: "pencil",
				text: "重命名",
				key: "updateName",
				iden: iden,
				action: _debug
			}, {
				type: "item",
				icon: "commenting",
				text: "描述内容",
				key: "updateDesc",
				iden: iden,
				action: _debug
			}, {
				type: "item",
				icon: "trash",
				text: "删除",
				key: "deleteFile",
				iden: iden,
				action: _debug
			}];
		}else if(iden==3){
			_menuItems = [{
				type: "title",
				text: "文件云"
			}, {
				type: "item",
				icon: "folder-o",
				text: "新建文件夹",
				key: "createFolder",
				iden: iden,
				action: _debug
			}, {
				type: "item",
				icon: "upload",
				text: "上传文件",
				key: "uploadFile",
				iden: iden,
				action: _debug
			}, {
				type: "divider"
			}, {
				type: "item",
				icon: "paste",
				text: "粘贴",
				key: "pasteDocu",
				iden: iden,
				action: _debug
			}, {
				type: "submenu",
				icon: "sort-alpha-desc",
				text: "排序方式",
				items: [{
					type: "title",
					text: "排序方式"
				}, {
					type: "item",
					text: "类型",
					key: "sortType",
					iden: iden,
					action: _debug
				}, {
					type: "item",
					text: "名称",
					key: "sortName",
					iden: iden,
					action: _debug
				}, {
					type: "item",
					text: "修改日期",
					key: "sortDate",
					iden: iden,
					action: _debug
				}]
			}, {
				type: "divider"
			}, {
				type: "item",
				icon: "refresh",
				text: "刷新",
				key: "refresh",
				iden: iden,
				action: _debug
			}, {
				type: "item",
				icon: "home",
				text: "返回根目录",
				key: "backRoot",
				iden: iden,
				action: _debug
			}, {
				type: "item",
				icon: "arrow-left",
				text: "返回上一层",
				key: "backFront",
				iden: iden,
				action: _debug
			}];
		}
		return _menuItems;
	}
	
	
	
	/**
	 * 重新绑定文件列表
	 */
	window.bindingDocuList = function(docuId){
		// 当前文件夹编号
		_paterId = docuId;
		
		/* 开始 面包屑 */
		if(docuId > 0){
			var docuCrumbs = $('#docuCrumbs span[docuid="'+docuId+'"]');
			if(docuCrumbs.length > 0){
				docuCrumbs.nextAll().remove();
			}else{
				var docuCrumbs = $('#docuCrumbs');
				var folderName = $('#docuList .docu[docuId="'+docuId+'"] span:last-child').html();
				dataHtml = '<img src="'+_folderTypeIcon+'" border="0" align="absmiddle">';
				dataHtml += '<span docuId="'+docuId+'" typeId="'+_folderTypeId+'" class="docu docuFolder crumb">'+folderName+'</span>';
				docuCrumbs.append(dataHtml);
			}
		}else{
			$('#docuCrumbs').html("");
		}
		/* 结束 面包屑 */

		/* 开始 文件列表 */
		var control = $('#docuList');
		control.empty();
		$.ajax({
			type : 'post',
			url : ligent_itemName + 'file/docu/findAllChild',
			data : { 'docuId':docuId, 'docuUse':_docuUse, 'sortMode':_sortMode },
			dataType : 'json',
			success : function(res){
				if(res) {
					$.each(res, function (i, item) {
						var className = item.typeId==1?"docuFolder":"docuFile";
						var docuDesc = item.typeId==0?"":($.isEmpty(item.docuDesc)?"":item.docuDesc);
						var dataHtml = $(['<li>'
					            ,'<div class="docu '+className+'" title="'+docuDesc+'" docuId="'+item.docuId+'" typeId="'+item.typeId+'">'
					            ,'<img src="'+item.typeIcong+'" />'
					            ,'<span class="docuName" style="display:-webkit-box;">'+item.docuName+'</span>'
					            ,'</div>'
					            ,'</li>'].join(''));
						control.append(dataHtml);
			        });
					// 初始化右键菜单
					initialiseMenu();
				}else{
					layer_msg(res.msg, 5, 1, null);
				}
			}
		});
		/* 结束 文件列表 */
	};
	
	/**
	 * 刷新文件夹(设置为全局变量，方便上传成功后刷新调用)
	 */
	window.refreshFolder = function(){
		bindingDocuList(_paterId);
	};
	
	/**
	 * 右键回调
	 */
	window.callbackRight = function(e){
		// 粘贴是否隐藏
		if(_cutDocuId==0){
			$(".fa-paste").parent().parent().hide();
		}else{
			$(".fa-paste").parent().parent().show();
		}
		
		$("#docuList li div").removeClass("pitchon");
		if(rightClickElement){
			for(var key in rightClickElement){
				rightClickElement[key] = e.attr(key);
			}
			var docuId = rightClickElement['docuid'];
			if(docuId){
				e.addClass("pitchon");
			}
		}
	};

	// 初始化页面信息
	window.initialiseDocuList = function(){
		// 获取cookie是否存在排序方式
		var hasFileSortMode = getCookie("hasFileSortMode");
		if(hasFileSortMode){
			_sortMode = hasFileSortMode;
		}else{
			_sortMode=1;
		}
		$("#sortModeName").html(sortModeArray[_sortMode]);
		// 最后加载
		window.bindingDocuList(0);
	};
	window.initialiseDocuList();
});

/**
 * 弹框初始化事件
 * @param layero 弹框doc对象
 */
function layerInitialise(layero){
	if(_submitMark == 1){
		$(layero).find('textarea[name="content"]').focus().val("");
	}else if(_submitMark == 2){
		$(layero).find('textarea[name="content"]').focus().val("");
		var docuId = rightClickElement['docuid'];
		$.ajax({
			type : 'post',
			url : ligent_itemName + 'file/docu/fainById',
			data : { 'docuId':docuId },
			dataType : 'json',
			success : function(res){
				if(res.code == 0) {
					var typeId = res.data.typeId;
					_docuSuffix = "";
					_docuName = res.data.docuName;
					if(_folderTypeId!=typeId){
						_docuSuffix = _docuName.substring(_docuName.lastIndexOf("."));
						_docuName = _docuName.substring(0, _docuName.lastIndexOf("."));
					}
					$(layero).find('textarea[name="content"]').val(_docuName);
					// 更新渲染表单
					// _form.render('select', 'Lay-modal-folder');
				}else{
					layer_msg(res.msg, 5, 1, null);
				}
			}
		});
	}else if(_submitMark == 3){
		$(layero).find('textarea[name="content"]').focus().val("");
		var docuId = rightClickElement['docuid'];
		$.ajax({
			type : 'post',
			url : ligent_itemName + 'file/docu/fainById',
			data : { 'docuId':docuId },
			dataType : 'json',
			success : function(res){
				if(res.code == 0) {
					$(layero).find('textarea[name="content"]').val(res.data.docuDesc);
				}else{
					layer_msg(res.msg, 5, 1, null);
				}
			}
		});
	}
}

/**
 * 弹框提交事件
 * @param layero 弹框doc对象
 */
function layerSubmit(layero){
	if(_submitMark == 1){
		/** 新建文件夹 */
		var docuName = $(layero).find('textarea[name="content"]').val();
		if($.isEmpty(docuName)){
			layer_alert("文件名不能为空!", 5, null);
			return;
		}
		docuName = docuName.replace(/\s+/g,""); 
		$.ajax({
			type : 'post',
			url : ligent_itemName + 'file/docu/insertFolder',
			data : { 'paterId':_paterId, 'docuUse':_docuUse, 'docuName':docuName },
			dataType : 'json',
			success : function(res){
				if(res.code == 0) {
					var item = res.data;
					var dataHtml = $(['<li>'
				            ,'<div class="docu docuFolder" docuId="'+item.docuId+'" typeId="'+_folderTypeId+'">'
				            ,'<img src="'+_folderTypeIcong+'" />'
				            ,'<span class="docuName" style="display:-webkit-box;">'+item.docuName+'</span>'
				            ,'</div>'
				            ,'</li>'].join(''));
		    		// 加入文件
		    		var control = $('#docuList');
		    		control.append(dataHtml);

					layer_close(_layerOpen);
					// 初始化右键菜单
					initialiseMenu();
				}else{
					layer_msg(res.msg, 5, 1, null);
				}
			}
		});
	}else if(_submitMark == 2){
		/** 重命名 */
		var docuName = $(layero).find('textarea[name="content"]').val();
		if($.isEmpty(docuName)){
			layer_alert("文件名不能为空!", 5, null);
			return;
		}
		docuName = docuName.replace(/\s+/g,""); 
		if(docuName==_docuName){
			return;
		}

		var docuId = rightClickElement['docuid'];
		// 重命名
		$.ajax({
			type : 'post',
			url : ligent_itemName + 'file/docu/updateName',
			data : { 'docuId':docuId, 'docuName':docuName },
			dataType : 'json',
			success : function(res){
				if(res.code == 0) {
					layer_close(_layerOpen);
					$("#docuList li div[docuid='"+docuId+"'] .docuName").html(docuName+_docuSuffix);
					// bindingDocuList(_paterId);
				}else{
					layer_msg(res.msg, 5, 1, null);
				}
			}
		});
	}else if(_submitMark == 3){
		/** 描述内容 */
		var docuDesc = $(layero).find('textarea[name="content"]').val();
		if($.isEmpty(docuDesc)){
			layer_alert("描述内容不能为空!", 5, null);
			return;
		}
		docuDesc = docuDesc.replace(/\s+/g,""); 
		
		var docuId = rightClickElement['docuid'];
		$.ajax({
			type : 'post',
			url : ligent_itemName + 'file/docu/updateDesc',
			data : { 'docuId':docuId, 'docuDesc':docuDesc },
			dataType : 'json',
			success : function(res){
				if(res.code == 0) {
					layer_close(_layerOpen);
					$("#docuList li div[docuid='"+docuId+"']").attr("title", docuDesc);
					// bindingDocuList(_paterId);
				}else{
					layer_msg(res.msg, 5, 1, null);
				}
			}
		});
	}else{
		layer_alert("系统繁忙，请刷新后再试!", 5, null);
	}
}
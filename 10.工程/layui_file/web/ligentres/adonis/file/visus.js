// 文件夹类型编号、文件类型小图标、文件类型图标
var _folderTypeId, _folderTypeIcon, _folderTypeIcong;
// 当前文件夹、文件用途、右击文件编号、右击文件类型、右击文件名称、右击文件后缀
var _paterId, _docuUse, _docuId, _typeId, _docuName, _docuSuffix;

layui.config({
	base: ligent_unitPath // 静态资源所在路径
}).extend({
	index: 'lib/index' // 主入口模块
}).use(['index'], function() {
	
	// 当前文件夹
	_paterId = 0;
	// 获取文件夹类型编号
	_folderTypeId = $("#folderTypeId").val();
	_folderTypeIcon = $("#folderTypeIcon").val();
	_folderTypeIcong = $("#folderTypeIcong").val();
	_docuUse = $("#docuUse").val();
	
	// 监听点击页面事件
	$('body').on('click', '.layui-card-body', function(e){
	    emptyTips(null);
	});
	// 监听右击页面事件
	$('body').on('mousedown', '.layui-card-body', function(e){
	    if(3 != e.which) {
	    	return;
	    }
		emptyTips(null);
	});
	// 监听鼠标滚轮事件
	$('body').on('mousewheel DOMMouseScroll', '.layui-card-body', function(e){
		emptyTips(null);
	});
	// 阻止浏览器默认右键点击事件
	document.oncontextmenu = function(e) {
	    return false;
	}
	
	/**
	 * 监听文件点击事件
	 */
	$('body').on('click', '.docu', function(e){
		// 清空所有右击弹框
		emptyTips();
		// 不再派发事件
		e.stopPropagation();
		// 获取文件编号
		var docuId = $(this).attr("docuId");
		// 文件类型编号
		var typeId = $(this).attr("typeId");
		if(_folderTypeId==typeId){
			// 打开文件夹
			bindingDocuList(docuId);
		}else{
			// 浏览文件
			browseFile(docuId);
		}
	});

	/**
	 * 监听空白地方右击事件
	 */
	$('body').on('mousedown', '.layui-card-body', function(e){
	    if(3 != e.which) {
	    	return;
	    }
		// 清空所有右击弹框
		emptyTips();
		// 不再派发事件
		e.stopPropagation();
		$("#menuAdd").css({'visibility':'visible', 'left':(e.offsetX+20)+'px', 'top':(e.offsetY+20)+'px'});
	});
	
	/**
	 * 监听文件右击事件
	 */
	$('body').on('mousedown', '#docuList li .docu', function(e){
	    if(3 != e.which) {
	    	return;
	    }
		// 清空所有右击弹框
		emptyTips();
		// 不再派发事件
		e.stopPropagation();
		
		// 样式控制
		var othis = $(this);
		$("#docuList li div").removeClass("pitchon");
		othis.addClass("pitchon");
		
		// 文件编号
		_docuId = $(this).attr("docuId");
		// 文件类型编号
		_typeId = $(this).attr("typeId");
		
		if(_folderTypeId==_typeId){
			$("#menuFolder").css({'visibility':'visible', 'left':e.clientX+'px', 'top':(e.clientY-30)+'px'});
		}else{
			$("#menuFile").css({'visibility':'visible', 'left':e.clientX+'px', 'top':(e.clientY-30)+'px'});
		}
	});
	
	/**
	 * 上传文件
	 */
	$('body').on('click', '.uploadFile', function(e){
		var action = ligent_itemName + "file/docu/upload?paterId="+_paterId+"&docuUse="+_docuUse;
		var index = layer.open({
		     type: 2, 
		     title: '上传文件', 
		     area: ['70%', '80%'], 
				anim: 2,
				id: "layui-layim-chatlog",
		     content: action
		 });
		// 清空所有右击弹框
		emptyTips();
	});
	
	// 新增文件夹
	$('body').on('click', '.createFolder', function(e){
		// 清空所有右击弹框
		emptyTips();
		// 不再派发事件
		e.stopPropagation();
		
		var dataHtml = $(['<li>'
	            ,'<div class="docu" docuId="0" typeId="'+_folderTypeId+'">'
	            ,'<img src="'+_folderTypeIcong+'" />'
	            ,'<span class="docuName" style="display:-webkit-box;"><input paterId="'+_paterId+'" type="text" style="width:78px;" placeholder="新建文件夹"></span>'
	            ,'</div>'
	            ,'</li>'].join(''));
		// 加入文件
		var control = $('#docuList');
		control.append(dataHtml);
	    $('.docuName input').focus().val("");
	});
	
	/**
	 * 文本框点击事件(新建文件夹、重命名)
	 */
	$('body').on('click', '.docuName input', function(e){
		/*防止触发文件点击事件*/
		// 不再派发事件
		e.stopPropagation();
	});
	
	/**
	 * 文本框失去焦点事件(新建文件夹、重命名)
	 */
	$('body').on('blur', '.docuName input', function(e){
		var othis = $(this);
		var docuName = $(this).val();
		var paterId = othis.attr('paterId');
		var docuId = othis.attr('docuId');
		var typeId = othis.attr('typeId');
		
		if($.isValid(docuId)){
			// 重命名
			if($.isEmpty(docuName) || docuName==_docuName){
				othis.parent().html(_docuName+_docuSuffix);
				return;
			}
			docuName = docuName.replace(/\s+/g,""); 
			$.ajax({
				type : 'post',
				url : ligent_itemName + 'file/docu/updateName',
				data : { 'docuId':docuId, 'docuName':docuName },
				dataType : 'json',
				success : function(res){
					if(res.code == 0) {
						othis.parent().html(docuName+_docuSuffix);
					}else{
						othis.parent().html(_docuName+_docuSuffix);
						layer_msg(res.msg, 5, 1, null);
					}
				}
			});
		}else{
			// 新建文件夹
			if($.isEmpty(docuName)){
				docuName = "新增文件夹";
			}
			$.ajax({
				type : 'post',
				url : ligent_itemName + 'file/docu/insertFolder',
				data : { 'paterId':paterId, 'docuUse':_docuUse, 'docuName':docuName },
				dataType : 'json',
				success : function(res){
					if(res.code == 0) {
						var docuId = res.data.docuId;
						// 修改文件夹名称
						othis.parent().html(res.data.docuName);
						// 获取新建文件夹对象
						$("li .docu[docuId='0']").attr("docuId",docuId);
					}else{
						layer_msg(res.msg, 5, 1, null);
					}
				}
			});
		}
	});
	
	/**
	 * 刷新
	 */
	$('body').on('click', '.resetFile', function(e){
		bindingDocuList(_paterId);
	});
	
	/**
	 * 打开文件夹
	 */
	$('body').on('click', '.unfoldFolder', function(e){
		bindingDocuList(_docuId);
	});
	
	/**
	 * 打包下载
	 */
	$('body').on('click', '.downloadFolder', function(e){
		window.location.href = ligent_itemName + 'file/docu/downloadZips?docuId='+_docuId;
	});
	
	/**
	 * 重命名
	 */
	$('body').on('click', '.updateName', function(e){
		// 清空所有右击弹框
		emptyTips();
		// 不再派发事件
		e.stopPropagation();
		var othis = $('.docu[docuId="'+_docuId+'"] .docuName');
		_docuName = othis.html();
		_docuSuffix = '';
		if(_folderTypeId!=_typeId){
			_docuSuffix = _docuName.substring(_docuName.lastIndexOf("."));
			_docuName = _docuName.substring(0, _docuName.lastIndexOf("."));
		}
		// <input paterId="'+_paterId+'" type="text" style="width:78px;" placeholder="新建文件夹">
		othis.html('<input docuId="'+_docuId+'" typeId="'+_typeId+'" type="text" style="width:78px;">');
	    $('.docuName input').focus().val(_docuName);
	});
	
	/**
	 * 删除
	 */
	$('body').on('click', '.deleteFile', function(e){
		var title = '确定删除该文件吗？';
		if(_folderTypeId==_typeId){
			title = '确定删除该文件夹吗？';
		}
		layer.confirm(title, function(index){
			// 关闭窗口
			layer.close(index);
			$.ajax({
				type : 'post',
				url : ligent_itemName + 'file/docu/deleteDocu',
				data : { 'docuId':_docuId },
				dataType : 'json',
				success : function(res){
					if(res.code == 0) {
						$('.docu[docuId="'+_docuId+'"]').parent().remove();
					}else{
						layer_msg(res.msg, 5, 1, null);
					}
				}
			});
    	});
	});
	
	/**
	 * 浏览文件
	 */
	$('body').on('click', '.browseFile', function(e){
		browseFile(_docuId);
	});
	
	/**
	 * 下载文件
	 */
	$('body').on('click', '.downloadFile', function(e){
		$.ajax({
			type: 'post',
			url: ligent_itemName + 'file/docu/fainById',
			data: { 'docuId':_docuId },
			dataType: 'json',
			success: function(res){
				if(res.code == 0) {
					var docu = res.data;
					window.location.href = ligent_itemName + "main/download?fileName="+docu.docuName+"&filePath="+docu.docuPath;
				}else{
					layer_msg(res.msg, 5, 1, null);
				}
			}
		});
	});
	
});

/**
 * 重新绑定文件列表
 */
function bindingDocuList(docuId){
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
			dataHtml += '<span docuId="'+docuId+'" typeId="'+_folderTypeId+'" class="docu crumb">'+folderName+'</span>';
			docuCrumbs.append(dataHtml);
		}
	}else{
		$('#docuCrumbs').html("");
	}
	/* 结束 面包屑 */

	/* 开始 文件列表 */
	var control = $('#docuList');
	control.empty();
	// 获取数据
	var data = {
			docuId: docuId,
			docuUse: _docuUse
		}
	$.post(ligent_itemName+'file/docu/findAllChild', data, function (data) {
		if(data){
			var json = JSON.parse(data);
			$.each(json, function (i, item) {
				var dataHtml = $(['<li>'
			            ,'<div class="docu" docuId="'+item.docuId+'" typeId="'+item.typeId+'">'
			            ,'<img src="'+item.typeIcong+'" />'
			            ,'<span class="docuName" style="display:-webkit-box;">'+item.docuName+'</span>'
			            ,'</div>'
			            ,'</li>'].join(''));
				control.append(dataHtml);
	        });
			
		}
    });
	/* 结束 文件列表 */
}

/**
 * 清空所有右击弹框
 */
var emptyTips = function () {
	// 移除所有选中的样式
	$("#docuList li div").removeClass("pitchon");
	// 关闭右键菜单
	$(".menu").css({'visibility':'hidden'});
};

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
				var fileUrl = ligent_readPath + fileDocu.docuPath;    // 文件url地址
			    if(fileDocu.browseMode == 1){
		        	// https://view.officeapps.live.com/op/view.aspx?src=http://www.fsyucai.com/document/caliche/2881/1542244223947.docx
		            var url = 'https://view.officeapps.live.com/op/view.aspx?src=' + fileUrl;
		            window.open(url, "_blank", "scrollbars=yes,resizable=1,modal=false,alwaysRaised=yes");
			    }else if(fileDocu.browseMode == 2){
		            var url = ligent_itemName + 'main/pdf?fileName=' + fileUrl;
		            window.open(url, "_blank", "scrollbars=yes,resizable=1,modal=false,alwaysRaised=yes");
			    }else if(fileDocu.browseMode == 3){
		        	window.open(fileUrl, "_blank", "scrollbars=yes,resizable=1,modal=false,alwaysRaised=yes");
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
 * 刷新文件夹
 */
function refreshFolder(){
	bindingDocuList(_paterId);
}
/*function bindingDocu(docu){
	var dataHtml = $(['<li>'
            ,'<div class="docu" docuId="'+docu.docuId+'" typeId="'+docu.typeId+'">'
            ,'<img src="'+docu.typeIcong+'" />'
            ,'<span class="docuName" style="display:-webkit-box;">'+docu.docuName+'</span>'
            ,'</div>'
            ,'</li>'].join(''));
	var control = $('#docuList');
	control.append(dataHtml);
}*/
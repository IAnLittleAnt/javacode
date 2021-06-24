var setter, admin, form, table;
// 表格对象、表格操作对象
var tableIns, tableCheckObj;
// 当前操作的数据编号
var currentKeyId;
// 弹框提交标注（1新增，2编辑,3删除,4多行删除）
var submitMark;
// 文件夹类型编号、文件类型图标、文件名称、文件后缀、当前文件夹、文件用途
var folderTypeId, folderTypeIcon, _docuName, _docuSuffix, thisDocuId, _docuUse;

layui.config({
	base: ligent_unitPath // 静态资源所在路径
}).extend({
	index: 'lib/index' // 主入口模块
}).use(['index', 'form', 'table'], function() {
	setter = layui.setter,
	admin = layui.admin,
	form = layui.form,
	table = layui.table;
	
	/*// 动态绑定页面高度
	var clientHeight = getViewSizeWithScrollbar(false);
	alert(clientHeight)
	$(".layui-card-body").height(clientHeight);*/
	// 当前文件夹
	thisDocuId = 0;
	// 获取文件夹类型编号
	folderTypeId = $("#folderTypeId").val();
	folderTypeIcon = $("#folderTypeIcon").val();
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
	 * 文件夹名称-鼠标点击事件
	 */
	$('body').on('click', '.folderName', function(e){
		// 不再派发事件
		e.stopPropagation();
		// 获取文件夹编号
		var docuId = $(this).attr("docuid");
		// 打开文件夹
		bindingDocuList(docuId);
	});
	
	/**
	 * 文件名称-鼠标点击事件
	 */
	$('body').on('click', '.docuName', function(e){
		// 不再派发事件
		e.stopPropagation();
		// 获取文件编号
		var docuId = $(this).attr("docuid");
		// 浏览文件
		browseFile(docuId);
	});
	
	/**
	 * 重命名点击事件
	 */
	$('body').on('click', '.updateName', function(e){
		// 清空所有右击弹框
		emptyTips();
		// 不再派发事件
		e.stopPropagation();
		// 获取文件夹编号
		var docuId = $(this).attr("docuid");
		var typeId = $(this).attr("typeid");
		var othis = $('.follink[docuid="'+docuId+'"]');
		if(typeId==folderTypeId){
			othis.removeClass("folderName");
		}else{
			othis.removeClass("docuName");
		}
		var oname = othis.find(".f_name_title");
		_docuSuffix = '';
		_docuName = oname.html();
		if(typeId!=folderTypeId){
			_docuSuffix = _docuName.substring(_docuName.lastIndexOf("."));
			_docuName = _docuName.substring(0, _docuName.lastIndexOf("."));
		}
		var html = '<input docuid="'+docuId+'" typeid="'+typeId+'" value="'+_docuName+'" />';
		oname.html(html);
	});
	
	/**
	 * 文本框点击事件
	 */
	$('body').on('click', '.f_name_title input', function(e){
		// 不再派发事件
		e.stopPropagation();
	});
	
	/**
	 * 失去焦点事件(新建文件夹、重命名)
	 */
	$('body').on('blur', '.f_name_title input', function(e){
		var othis = $(this);
		var docuName = $(this).val();
		var paterId = othis.attr('paterid');
		var docuId = othis.attr('docuid');
		var typeId = othis.attr('typeid');
		
		if($.isValid(docuId)){
			// 重命名
			if(typeId==folderTypeId){
				$('.follink[docuid="'+docuId+'"]').addClass("folderName");
			}else{
				$('.follink[docuid="'+docuId+'"]').addClass("docuName");
			}
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
						othis.parent().html(" "+docuName+_docuSuffix);
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
						othis.parent().html(" "+res.data.docuName);
						// 获取新建文件夹对象
						var thisFolder = $("#folder0");
						// 修改文件夹编号
						thisFolder.find(".f_name2").find(".folderName").attr("docuid",docuId);
						// 右侧追加按钮
						var html = '<div class="f_sel">'
							+ '<a docuid="'+docuId+'" typeId="'+folderTypeId+'" class="f_sela handleDocu" tabindex="'+docuId+'"><span></span><span></span><span></span></a>'
							+ '<a docuid="'+docuId+'" typeId="'+folderTypeId+'"  class="f_selb deleteDocu"></a>'
							+ '<div class="f_selc fs'+docuId+'" element-invisible=""></div>'
							+ '<div class="f_selc fs_'+docuId+'" element-invisible=""></div>'
							+ '</div>';
						thisFolder.append(html);
						// 修改class
						thisFolder.attr("id","folder"+docuId);
					}else{
						layer_msg(res.msg, 5, 1, null);
					}
				}
			});
		}
	});
	
	// 打包下载
	$('body').on('click', '.downloadFolder', function(e){
		var docuId = $(this).attr('docuid');
		window.location.href = ligent_itemName + "file/docu/downloadZips?docuId="+docuId;
	});
	
	// 下载文件
	$('body').on('click', '.downloadFile', function(e){
		var docuId = $(this).attr('docuid');
		$.ajax({
			type : 'post',
			url : ligent_itemName + 'file/docu/updateCount',
			data : { 'docuId':docuId },
			dataType : 'json',
			success : function(res){
				if(res.code == 0) {
					var json = res.data;
					window.location.href = ligent_itemName + "main/download?fileName="+json.docuName+"&filePath="+json.docuPath;
					$("#docu"+docuId+" .f_down font").html(json.downloadCount);
				}else{
					layer_msg(res.msg, 5, 1, null);
				}
			}
		});
	});
	
	// 浏览文件
	$('body').on('click', '.browseFile', function(e){
		var docuId = $(this).attr('docuid');
		browseFile(docuId);
	});
	
	/**
	 * 更多操作-鼠标点击事件
	 */
	$('body').on('click', '.handleDocu', function(e){
		var docuId = $(this).attr("docuid");
		var typeId = $(this).attr("typeid");
		
		// 清空所有右击弹框
		emptyTips(docuId);
		// 不再派发事件
		e.stopPropagation();
		
		var html;
		if(typeId==folderTypeId){
			html = '<div class="f_view">'+
				'<div docuid="'+docuId+'" class="folderName">打开文件夹</div>'+
				'<div docuid="'+docuId+'" class="downloadFolder">打包下载</div>'+
				'<div docuid="'+docuId+'" typeid="'+typeId+'" class="updateName">重命名</div>'+
				'<div>取消</div>'+
				'</div>';
		}else{
			html = '<div class="f_view">'+
				'<div docuid="'+docuId+'" class="browseFile">浏览文件</div>'+
				'<div docuid="'+docuId+'" class="downloadFile">下载文件</div>'+
				'<div docuid="'+docuId+'" typeid="'+typeId+'" class="updateName">重命名</div>'+
				'<div>取消</div>'+
				'</div>';
		}
		$('.fs'+docuId).html(html);
		$('.fs'+docuId).removeClass('element-invisible');
	});
	
	/**
	 * 描述内容-鼠标悬浮事件
	 */
	$('body').on('mouseover', '.descFile', function(e){
		var docuId = $(this).attr("docuid");
		var userName = $(this).attr("username");
		var docuName = $('.docuName[docuid="'+docuId+'"] .f_name_title').html();
		var createDate = $('#docu'+docuId+' .f_time').html();
		// docuName = docuName.substring(0, docuName.lastIndexOf("."));
		$(".fis"+docuId).html('<div class="finfost">'+docuName+'</div><div>创建人：'+userName+'</div><div>创建日期：'+createDate+'</div>');
		$(".fis"+docuId).show();
	});
	
	/**
	 * 描述内容-鼠标移开事件
	 */
	$('body').on('mouseout', '.descFile', function(e){
		var docuId = $(this).attr("docuid")
		$(".fis"+docuId).hide(150);
	});
	
	/**
	 * 删除文件夹-点击事件
	 */
	$('body').on('click', '.deleteDocu', function(e){
		// 不再派发事件
		e.stopPropagation();
		
		var othis = $(this);
		var docuId = othis.attr("docuid");
		var typeId = othis.attr("typeid");
		
		var hint = typeId==1 ? '文件夹' : '文件';
		layer.confirm('确定要删除该'+hint+'吗？', {
		    title: '温馨提示',
	    	btn: ['确定','取消']
		}, function(index){
			layer.close(index);
			layer_loading();
			// 删除操作
			var action = ligent_itemName + "file/docu/deleteDocu";
			var data = {
					docuId: docuId
				};
			$.post(action, data, function (res) {
				var json = JSON.parse(res);
				layer_closeAll();
				if (json.code == 0){
					layer_msg(json.msg, 1, 1, null);
					othis.parent().parent().remove();
				}else{
					layer_msg(json.msg, 5, null, null);
				}
		    });
		});
	});
	
	/**
	 * 新建文件夹
	 */
	$('body').on('click', '.createFolder', function(e){
		var control = $('#docuList');
		dataHtml = '<div id="folder0" class="f_tb">';
		dataHtml += '<div class="f_name2">';
		dataHtml += '<span docuid="0" class="follink folderName">';
		dataHtml += '<img src="'+folderTypeIcon+'" border="0" align="absmiddle">';
		dataHtml += '<span class="f_name_title">&nbsp;<input paterid="'+thisDocuId+'" placeholder="新建文件夹" type="text" /></span>';
		dataHtml += '</span>';
		dataHtml += '</div>';
		dataHtml += '</div>';
		control.append(dataHtml);
		$('.f_name_title input').focus().val("");
	});
	
	/**
	 * 上传文件
	 */
	$('body').on('click', '.uploadFile', function(e){
		var action = ligent_itemName + "file/docu/upload?paterId="+thisDocuId+"&docuUse="+_docuUse;
		var index = layer.open({
		     type: 2, 
		     title: '上传文件', 
		     area: ['70%', '80%'], 
		     content: action
		 });
	});

	
});



/**
 * 清空文件更多弹框
 */
var emptyTips = function (docuId) {
	$('.f_sela').removeClass('f_focus');
	$('.f_selc').addClass('element-invisible');
	if(docuId){
		$('.f_sela[docuid="'+docuId+'"]').addClass('f_focus');
		$('.fs'+docuId).removeClass('element-invisible');
	}
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
 * 绑定文件列表
 */
function bindingDocuList(docuId){
	// 当前文件夹编号
	thisDocuId = docuId;
	/* 开始 面包屑 */
	if(docuId > 0){
		var docuCrumbs = $('#docuCrumbs span[docuid="'+docuId+'"]');
		if(docuCrumbs.length > 0){
			docuCrumbs.nextAll().remove();
		}else{
			var docuCrumbs = $('#docuCrumbs');
			var folderName = $('#docuList .folderName[docuid="'+docuId+'"] span:last-child').html();
			dataHtml = '<img src="'+folderTypeIcon+'" border="0" align="absmiddle">';
			dataHtml += '<span docuid="'+docuId+'" class="f_tpspan folderName">'+folderName+'</span>';
			docuCrumbs.append(dataHtml);
		}
	}else{
		$('#docuCrumbs').html("");
	}
	/* 结束 面包屑 */
	
	/* 开始 文件列表 */
	var control = $('#docuList');
	control.empty();
	var data = {
			docuId: docuId,
			docuUse: _docuUse
		}
	$.post(ligent_itemName+"file/docu/findAllChild", data, function (data) {
		if(data){
			var json = JSON.parse(data);
			$.each(json, function (i, item) {
				if(item.typeId==folderTypeId){
					dataHtml = '<div id="docu'+item.docuId+'" class="f_tb">';
					dataHtml += '<div class="f_name2">';
					dataHtml += '<span docuid="'+item.docuId+'" class="follink folderName">';
					dataHtml += '<img src="'+item.typeIcon+'" border="0" align="absmiddle">';
					dataHtml += '<span>&nbsp;</span>';
					dataHtml += '<span class="f_name_title">'+item.docuName+'</span>';
					dataHtml += '</span>';
					dataHtml += '</div>';
					dataHtml += '<div class="f_sel">';
					dataHtml += '<a docuid="'+item.docuId+'" typeid="'+item.typeId+'" class="f_sela handleDocu" tabindex="'+item.docuId+'"><span></span><span></span><span></span></a>';
					dataHtml += '<a docuid="'+item.docuId+'" typeid="'+item.typeId+'" class="f_selb deleteDocu"></a>';
					dataHtml += '<div class="f_selc fs'+item.docuId+'" element-invisible=""></div>';
					dataHtml += '<div class="f_selc fs_'+item.docuId+'" element-invisible=""></div>';
					dataHtml += '</div>';
					dataHtml += '</div>';
				}else{
					dataHtml = '<div id="docu'+item.docuId+'" class="f_tb">';
					dataHtml += '<div class="f_name">';
					dataHtml += '<span docuid="'+item.docuId+'" class="follink docuName">';
					dataHtml += '<img src="'+item.typeIcon+'" border="0" align="absmiddle">';
					dataHtml += '<span>&nbsp;</span>';
					dataHtml += '<span class="f_name_title">'+item.docuName+'</span>';
					dataHtml += '</span>';
					dataHtml += '<span class="finfos fks'+item.docuId+'"></span>';
					dataHtml += '<span docuid="'+item.docuId+'" username="'+item.userName+'" class="finfo descFile" style="display:inline;"></span>';
					dataHtml += '<span class="finfos fis'+item.docuId+'"></span>';
					dataHtml += '</div>';
					dataHtml += '<div class="f_size">'+item.docuSizeUnit+'</div>';
					dataHtml += '<div class="f_time">'+item.createTime+'</div>';
					dataHtml += '<div class="f_down">';
					dataHtml += '<font size="4" color="#F81">'+item.downloadCount+'</font>';
					dataHtml += '</div>';
					dataHtml += '<div class="f_sel">';
					dataHtml += '<a docuid="'+item.docuId+'" typeid="'+item.typeId+'" class="f_sela handleDocu" tabindex="'+item.docuId+'"><span></span><span></span><span></span></a>';
					dataHtml += '<a docuid="'+item.docuId+'" typeid="'+item.typeId+'" class="f_selb deleteDocu"></a>';
					dataHtml += '<div class="f_selc fs'+item.docuId+'" element-invisible=""></div>';
					dataHtml += '<div class="f_selc fs_'+item.docuId+'" element-invisible=""></div>';
					dataHtml += '</div>';
					dataHtml += '</div>';
				}
				control.append(dataHtml);
	        });
		}
    });
	/* 结束 文件列表 */
}

/**
 * 绑定文件
 */
function bindingDocu(docu){
	var dataHtml = $(['<div id="docu'+docu.docuId+'" class="f_tb">'
	            ,'<div class="f_name">'
	            ,'<span docuid="'+docu.docuId+'" class="follink docuName">'
	            ,'<img src="'+docu.typeIcon+'" border="0" align="absmiddle">'
	            ,'<span class="f_name_title"> '+docu.docuName+'</span>'
	            ,'</span>'
	            ,'<span class="finfos fks'+docu.docuId+'"></span>'
	            ,'<span docuid="'+docu.docuId+'" username="'+docu.userName+'" class="finfo descFile" style="display:inline;"></span>'
	            ,'<span class="finfos fis'+docu.docuId+'"></span>'
	            ,'</div>'
	            ,'<div class="f_size">'+docu.docuSizeUnit+'</div>'
	            ,'<div class="f_time">'+docu.createTime+'</div>'
	            ,'<div class="f_down">'
	            ,'<font size="4" color="#F81">'+docu.downloadCount+'</font>'
	            ,'</div>'
	            ,'<div class="f_sel">'
	            ,'<a docuid="'+docu.docuId+'" typeId="'+docu.typeId+'" class="f_sela handleDocu" tabindex="'+docu.docuId+'"><span></span><span></span><span></span></a>'
	            ,'<a docuid="'+docu.docuId+'" typeId="'+docu.typeId+'" class="f_selb deleteDocu"></a>'
	            ,'<div class="f_selc fs'+docu.docuId+'" element-invisible=""></div>'
	            ,'<div class="f_selc fs_'+docu.docuId+'" element-invisible=""></div>'
	            ,'</div>'
	            ,'</div>'].join(''));
	var control = $('#docuList');
	control.append(dataHtml);
}
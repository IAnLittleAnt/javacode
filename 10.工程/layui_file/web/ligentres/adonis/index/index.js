//页面初始化
$(function(){
		var docuId=0;
		var _docuUse=1;
		var _sortMode=1;
		var control = $('#fileListLevel1');
		control.empty();
		$.ajax({
			type : 'post',
			url : ligent_itemName + 'index/findAllChilds',
			data : { 'docuId':docuId, 'docuUse':_docuUse, 'sortMode':_sortMode },
			dataType : 'json',
			success : function(res){
				if(res) {
					$.each(res, function (i, item) {
						if(item.typeId==1){
							var className = item.typeId==1?"docuFolder":"docuFile";
							var docuDesc = item.typeId==0?"":($.isEmpty(item.docuDesc)?"":item.docuDesc);
							var dataHtml = $(['<th id="a_level1" data-field="content" data-key="1-0-0" data-minwidth="200" style="background: #25c88a;text-align: center">'
								,'<a href="javascript:void(0)" class="layui-btn-group .layui-btn-primary}" ><div id="th_level1" class="layui-table-cell laytable-cell-1-0-0" align="center" docuId="'+item.docuId+'" docuDesc="'+docuDesc+'" typeId="'+item.typeId+'">'
								,'<span style="color: #fff;font-size: 16px;">'+item.docuName+'</span>'
								,'</div></a>'
					            ,'</th>'].join(''));
							control.append(dataHtml);
							 
							if(i==0){
								$('#docuDesc_id').html(docuDesc);
								showLevel2(item.docuId);
							}
						}
						
			        });
					 
				} 
			}
		});
	});
 
function showLevel2(docuIdLevel2){
	var docuId=docuIdLevel2;
	var _docuUse=1;
	var _sortMode=1;
	var control = $('#fileListLevel2');
	control.empty();
	$.ajax({
		type : 'post',
		url : ligent_itemName + 'index/findAllChilds',
		data : { 'docuId':docuId, 'docuUse':_docuUse, 'sortMode':_sortMode },
		dataType : 'json',
		success : function(res){
			if(res) {
				$.each(res, function (i, item) {
					var className = item.typeId==1?"docuFolder":"docuFile";
					var docuDesc = item.typeId==0?"":($.isEmpty(item.docuDesc)?"":item.docuDesc);
					// 只显示文件夹
					if(item.typeId==1){
						var dataHtml = $(['<a style="padding:5px">'
				            ,'<div style="margin-bottom: 5px;" class="layui-btn layui-btn-primary layui-btn-xm" docuId="'+item.docuId+'" docuDesc="'+docuDesc+'" typeId="'+item.typeId+'" >'
				            ,'<span class="docuName" style="display:-webkit-box;">'+item.docuName+'</span>'
				            ,'</div>'
				            ,'</a>'].join(''));
					control.append(dataHtml);
					if(i==0){
						showLevel3(item.docuId);
					}
					}
		        });
				 
			} 
		}
	});
}
 
function showLevel3(docuIdLevel3){
	var docuId=docuIdLevel3;
	var _docuUse=1;
	var _sortMode=1;
	var control = $('#docuList');
	control.empty();
	$.ajax({
		type : 'post',
		url : ligent_itemName + 'index/findAllChilds',
		data : { 'docuId':docuId, 'docuUse':_docuUse, 'sortMode':_sortMode },
		dataType : 'json',
		success : function(res){
			if(res) {
				$.each(res, function (i, item) {
					var className = item.typeId==1?"docuFolder":"docuFile";
					var docuDesc = item.typeId==0?"":($.isEmpty(item.docuDesc)?"":item.docuDesc);
					var dataHtml = $(['<li>'
			            ,'<div id="docu_id" class="docu '+className+'" docuId="'+item.docuId+'" docuDesc="'+docuDesc+'" typeId="'+item.typeId+'">'
			            ,'<img src="'+item.typeIcong+'" />'
			            ,'<span class="docuFileName" style="display:-webkit-box;"> <a id="a_download" href="javascript:void(0)" docuId="'+item.docuId+'" >'+item.docuName+'</a></span>'
			            ,'</div>'
			            ,'</li>'].join(''));
					control.append(dataHtml);
		        });
				 
				// 设置高度
				var clientHeight = document.documentElement.clientHeight-155;
				document.getElementById("fileCloud").style.height = clientHeight+"px";
				document.getElementById("docuDesc_height").style.height = clientHeight+40+"px";
			} 
		}
	});
}


/**
 * 监听一级文件夹点击事件
 */
$('body').on('click', '#th_level1', function(e){
	// 获取文件编号
	var docuId = $(this).attr("docuId");
	var docuDesc = $(this).attr("docuDesc");
	$('#docuDesc_id').html(docuDesc);
	// 打开文件夹
	showLevel2(docuId);
});


/**
 * 监听二级文件夹点击事件
 */
$('body').on('click', '#fileListLevel2 .layui-btn', function(e){
	// 获取文件编号
	var docuId = $(this).attr("docuId");
	var docuDesc = $(this).attr("docuDesc");
	$('#docuDesc_id').html(docuDesc);
	
	// 打开文件夹
	showLevel3(docuId);
});



/**
 * 监听三级文件夹点击事件
 */
$('body').on('click', '#docu_id', function(e){
	// 获取文件编号
	var docuId = $(this).attr("docuId");
	var docuDesc = $(this).attr("docuDesc");
	$('#docuDesc_id').html(docuDesc);
});

$('body').on('click', '#a_download', function(e){
	// 获取文件编号
	// 获取文件编号
	var docuId = $(this).attr("docuId");
	window.location.href = ligent_itemName + 'file/docu/downloadZips?docuId='+docuId;
});


var $, upload;

layui.config({
	base: ligent_unitPath // 静态资源所在路径
}).extend({
	index: 'lib/index' // 主入口模块
}).use(['index', 'upload'], function() {
    $ = layui.jquery,
	upload = layui.upload;
	
    // 删除失败数量
    var uploadErrorCount = 0;
    // 多文件列表示例
    var demoListView = $('#test-upload-demoList')
    ,uploadListIns = upload.render({
    	elem: '#test-upload-testList',
    	url: ligent_itemName + 'file/docu/insertFile',
    	accept: 'file',
    	multiple: true,
    	auto: false,
    	bindAction: '#test-upload-testListAction',
    	before: function(obj){
    		// 上传loading
    		layer_loading();
    		/* 文件上传前的回调 */
    		// 封装带参上传
    		this.data = {
    				"paterId":$("#paterId").val(),
    				"docuUse":$("#docuUse").val()
    			};
    	},
    	allDone: function(obj){
    		// 关闭loading
    		layer_closeAll();
    		/* 当文件全部被提交后，才触发 */
		    console.log("总文件数:"+obj.total+",请求成功的文件数:"+obj.successful+",请求失败的文件数:"+obj.aborted);
		    if(obj.aborted==0){
			    layer.msg('全部文件上传成功!');
		    	// 关闭本自身窗体
				setTimeout(function(){
			        	// 回调父页面刷新功能
			        	parent.refreshFolder();
						var index = parent.layer.getFrameIndex(window.name); 
						parent.layer.close(index);
					},2000);
		    }else{
		    	layer.msg(obj.aborted+'个文件上传失败!');
		    }
    	},
    	choose: function(obj){
    		// 将每次选择的文件追加到文件队列
	        var files = this.files = obj.pushFile();
	        // 读取本地文件
	        obj.preview(function(index, file, result){
	        	var fileName = file.name;
	        	var suffix = fileName.split('.').pop().toLowerCase();
	        	if(suffix=="jsp"||suffix=="jspx"){
		        	delete files[index]; //删除对应的文件
		        	uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
				    layer.msg('已过滤jsp和jspx文件！');
	        	}else{
	        		var tr = $(['<tr id="upload-'+ index +'">'
    		            ,'<td>'+ file.name +'</td>'
    		            ,'<td><textarea id="content-'+ index +'" placeholder="备注内容" class="layui-textarea"></textarea></td>'
    		            ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
    		            ,'<td>等待上传</td>'
    		            ,'<td>'
    		            ,'<button class="layui-btn layui-btn-sm test-upload-demo-reload layui-hide">重传</button>&nbsp;'
    		            ,'<button class="layui-btn layui-btn-sm layui-btn-danger test-upload-demo-delete">删除</button>'
    		            ,'</td>'
    		            ,'</tr>'].join(''));
    	          
    	        	// 单个重传
    		        tr.find('.test-upload-demo-reload').on('click', function(){
    		        	obj.upload(index, file);
    		        });
    		          
    		        // 删除
    		        tr.find('.test-upload-demo-delete').on('click', function(){
    		        	delete files[index]; //删除对应的文件
    		        	tr.remove();
    		        	uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
    		        });
    		          
    		        demoListView.append(tr);
	        	}
	        });
    	},
    	done: function(res, index, upload){
    		var docuDesc = $("#content-"+index).val();
	        if(res.code == 0){ //上传成功
	        	// 修改描述内容
	        	$.ajax({
	    			type : 'post',
	    			async: true,
	    			url : ligent_itemName + 'file/docu/updateDesc',
	    			data : { 'docuId':res.data.docuId, 'docuDesc':docuDesc },
	    			dataType : 'json',
	    			success : function(res){}
	    		});
	        	
	        	var tr = demoListView.find('tr#upload-'+ index)
	        		,tds = tr.children();
	        	tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
	        	tds.eq(3).html(''); //清空操作
	        	
	        	return delete this.files[index]; //删除文件队列已经上传成功的文件
	        }
	        this.error(index, upload);
    	},
    	error: function(index, upload){
	        var tr = demoListView.find('tr#upload-'+ index)
	        	,tds = tr.children();
	        tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
	        tds.eq(3).find('.test-upload-demo-reload').removeClass('layui-hide'); //显示重传
	    }
    });
	
});

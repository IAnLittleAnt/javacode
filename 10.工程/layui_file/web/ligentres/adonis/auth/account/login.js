layui.config({
	base: ligent_unitPath // 静态资源所在路径
}).extend({
	index: 'lib/index' // 主入口模块
}).use(['index', 'user'], function() {
	var $ = layui.$
	setter = layui.setter,
	admin = layui.admin,
	form = layui.form,
	router = layui.router(),
	search = router.search;
	
	
	// 判断是否有cookie
	var isRememberPassword = getCookie("isRememberPassword");
	if(isRememberPassword && !$.isEmpty(isRememberPassword)){
		$("input[name='remember']").attr("checked", 'true');
		var account = isRememberPassword.substring(0, isRememberPassword.indexOf(","));
		var password = isRememberPassword.substring(isRememberPassword.indexOf(",")+1, isRememberPassword.length);
		$('input[name="account"]').val(account);
		$('input[name="password"]').val(password);
	}
	
	
	var errorCode = $("#errorCode").val();
	if(errorCode==501){
		layer_alert("回话已过去，请重新登录!", 4, null);
	}
	
	form.render();
	
	// 登录提交
	form.on('submit(LAY-user-login-submit)', function(obj) {
		layer_loading();
		
		// 请求登入接口
		admin.req({
			url: ligent_itemName + 'auth/account/logon',
			data: obj.field,
			done: function(json) {
				if(json.code == 0) {
					// 判断是否记住密码
					var isCheck = $("input[name='remember']").is(':checked');
					if(isCheck){
						var account = $('input[name="account"]').val();
						var password = $('input[name="password"]').val();
						var isRememberPassword = account + "," + password;
						setCookie("isRememberPassword",isRememberPassword,30);
					}else{
						setCookie("isRememberPassword","",30);
					}
					
					/*// 请求成功后，写入 access_token
					layui.data(setter.tableName, {
						key: "loginUserId",
						value: json.keyId
					});*/
					// 跳转主页
					layer_msg(json.msg, 1, 1, ligent_itemName + "home/index");
				} else {
					// 重新绑定验证码
					$("#LAY-user-vercode").val("");
					$("#LAY-user-vercode").focus();
					$("#LAY-user-get-vercode").attr('src', ligent_itemName + 'main/captcha?t='+ new Date().getTime());
					
					if(5001 != json.code){
						// 账号密码不正确
						$("#LAY-user-password").val("");
						$("#LAY-user-password").focus();
					}

					layer_closeAll();
					layer_msg(json.msg, 5, null, null);
				}
			}
		});
	
	});
	
	// 关闭加载层
	layer_closeAll();
});


/**
 * 绑定jQuery事件
 */
$(document).ready(function() {
	/**
	 * 鼠标右键事件
	 */
	$(document).contextmenu(function (e) {
        // 屏蔽鼠标右键
		return false;
    });
	
	/**
	 * 忘记密码
	 */
	$('.LAY-user-forget').on('click',function () {
		layer_loading();
		window.location.href = ligent_itemName + "main/forget";
	});
	
	/**
	 * 注册帐号
	 */
	$('.LAY-user-reg').on('click',function () {
		layer_loading();
		window.location.href = ligent_itemName + "main/reg";
	});
	
	/**
	 * 回车事件回调
	 */
	$(document).keydown(function (event) {
        if (event.keyCode == 13) {
        	$('#LAY-user-submit').trigger('click');
        }
	});
	
});


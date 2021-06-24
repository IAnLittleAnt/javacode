/**

 @Name：layuiAdmin 用户登入和注册等
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License: LPPL
    
 */
 
layui.define('form', function(exports){
	var $ = layui.$,
	layer = layui.layer,
	laytpl = layui.laytpl,
  	setter = layui.setter,
  	view = layui.view,
  	admin = layui.admin,
  	form = layui.form;

	var $body = $('body');
  
	// 自定义验证
	form.verify({
		nickname: function(value, item){ //value：表单的值、item：表单的DOM对象
			if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
				return '用户名不能有特殊字符';
			}
			if(/(^\_)|(\__)|(\_+$)/.test(value)){
				return '用户名首尾不能出现下划线\'_\'';
			}
			if(/^\d+\d+\d$/.test(value)){
				return '用户名不能全为数字';
			}
		},
    
    	// 我们既支持上述函数式的方式，也支持下述数组的形式
    	// 数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
		pass: [/^[\S]{6,12}$/,'密码必须6到12位，且不能出现空格'] 
	});
  
  
	// 发送短信验证码
	admin.sendAuthCode({
		elem: '#LAY-user-getsmscode',// 获取验证码按钮
		elemPhone: '#LAY-user-cellphone',// 账号
		elemHasVercode: '#LAY-user-hasVercode',// 是否需要图形验证码
		elemVercode: '#LAY-user-vercode',// 图形验证码
		elemSmsType: '#LAY-user-smsType',// 短信类型
		elemSmscode: '#LAY-user-smscode',// 短信验证码
		elemUserId: '#LAY-user-login-userId',// 登录用户编号(没登录为空)
		ajax: {
			url: ligent_itemName + 'ligent/sms/sendMessage'
		}
	});
	
	// 更换图形验证码
	$body.on('click', '#LAY-user-get-vercode', function(){
		var othis = $(this);
		this.src = ligent_itemName + 'main/captcha?t='+ new Date().getTime()
	});
  
	//对外暴露的接口
	exports('user', {});
});
// layui模块
var _form;
var pwdGrade = 0;// 密码等级
// 初始化本页面数据
var _currentDate = {
		pwd: ligent_itemName+'auth/account/pwd',				// 修改密码页面
		update: ligent_itemName+'auth/account/updatePwdByOld'	// 修改密码方法
};

layui.config({
	base: ligent_unitPath // 静态资源所在路径
}).extend({
	index: 'lib/index' // 主入口模块
}).use(['index', 'form', 'laydate'], function() {
	_form = layui.form;
	
	// 清空原密码
	$('input[name="oldPwd"]').val("").focus();
	
	/**
	 * 监听右上角按钮工具
	 */
	$('body').on('click', '.layui-btn-event', function(e){
		var type = $(this).data('type');
		active[type] ? active[type].call(this, e.target) : '';
	});
	var active = {
		reset: function(e) { 
			/** 重置 */
			layer_loading();
			var parentId = $("#parentId").val();
			window.location.href = _currentDate.pwd;
		},
		submit: function(e) { 
			/** 提交 */
			var oldPwd = $('input[name="oldPwd"]').val();
			if($.isEmpty(oldPwd)){
    			layer_msg('原密码不能为空!', 5, null, null);
				return;
			}
			var nowPwdOne = $('input[name="nowPwd"]').val();
			if($.isEmpty(nowPwdOne)){
    			layer_msg('新密码不能为空!', 5, null, null);
				return;
			}
			if(nowPwdOne.length<6 || pwdGrade<2){
				Primary();
				$("#userPwd").val("").focus();
    			layer_msg('新密码过于简单，请设置密码强度为中级以上!', 5, null, null);
				return;
			}
			var nowPwdTwo = $('#nowPwdTwo').val();
			if($.isEmpty(nowPwdTwo)){
    			layer_msg('确认密码不能为空!', 5, null, null);
				return;
			}
			if(nowPwdOne!=nowPwdTwo){
    			layer_msg('两次输入密码不一致!', 5, null, null);
				return;
			}
			
			layer_loading();
			var form = new FormData(document.getElementById("MyForm"));
			$.ajax({
	            url : _currentDate.update,
	            type : "post",
	            data : form,
	            processData : false,
	            contentType : false,
	            success : function(data){
	        		var json = JSON.parse(data);
	        		if(json.code == 0) {
	        			layer_msg(json.msg, 1, 1, _currentDate.pwd);
	        		} else {
	        			layer_closeAll();
	        			layer_msg(json.msg, 5, null, null);
	        		}
	            },
	            error : function(e){
        			layer_closeAll();
        			layer_msg('操作失败,请稍后再试!', 5, null, null);
	            }
	        });
		},
	};
	
});

//获取焦点事件
$('#userPwd').keyup(function () {
    var __th = $(this);
    
    if (!__th.val()) {
    	$("#pwd_tip").hide();
        Primary();
        return;
    }
	$("#pwd_tip").show();
    /*if (__th.val().length < 6) {
        Weak();
        return;
    }*/
	pwdGrade = checkPassword(__th);
    if (pwdGrade < 1) {
        Primary();
        return;
    }

    if (pwdGrade > 0 && pwdGrade < 2) {
        Weak();
    } else if (pwdGrade >= 2 && pwdGrade < 4) {
        Medium();
    } else if (pwdGrade >= 4) {
        Tough();
    }
});

function checkPassword(pwdinput) {
    var maths, smalls, bigs, corps, cat, num;
    var str = $(pwdinput).val()
    var len = str.length;

    var cat = /.{16}/g
    if (len == 0) return 1;
    if (len > 16) { $(pwdinput).val(str.match(cat)[0]); }
    cat = /.*[\u4e00-\u9fa5]+.*$/
    if (cat.test(str)) {
        return -1;
    }
    cat = /\d/;
    var maths = cat.test(str);
    cat = /[a-z]/;
    var smalls = cat.test(str);
    cat = /[A-Z]/;
    var bigs = cat.test(str);
    var corps = corpses(pwdinput);
    var num = maths + smalls + bigs + corps;

    if (len < 6) { return 1; }

    if (len >= 6 && len <= 8) {
        if (num == 1) return 1;
        if (num == 2 || num == 3) return 2;
        if (num == 4) return 3;
    }

    if (len > 8 && len <= 11) {
        if (num == 1) return 2;
        if (num == 2) return 3;
        if (num == 3) return 4;
        if (num == 4) return 5;
    }

    if (len > 11) {
        if (num == 1) return 3;
        if (num == 2) return 4;
        if (num > 2) return 5;
    }
}

function corpses(pwdinput) {
    var cat = /./g
    var str = $(pwdinput).val();
    var sz = str.match(cat)
    for (var i = 0; i < sz.length; i++) {
        cat = /\d/;
        maths_01 = cat.test(sz[i]);
        cat = /[a-z]/;
        smalls_01 = cat.test(sz[i]);
        cat = /[A-Z]/;
        bigs_01 = cat.test(sz[i]);
        if (!maths_01 && !smalls_01 && !bigs_01) { return true; }
    }
    return false;
}

function Primary() {
    $('#pwdLevel_1').attr('class', 'ywz_zhuce_huixian');
    $('#pwdLevel_2').attr('class', 'ywz_zhuce_huixian');
    $('#pwdLevel_3').attr('class', 'ywz_zhuce_huixian');
}

function Weak() {
    $('#pwdLevel_1').attr('class', 'ywz_zhuce_hongxian');
    $('#pwdLevel_2').attr('class', 'ywz_zhuce_huixian');
    $('#pwdLevel_3').attr('class', 'ywz_zhuce_huixian');
}

function Medium() {
    $('#pwdLevel_1').attr('class', 'ywz_zhuce_hongxian');
    $('#pwdLevel_2').attr('class', 'ywz_zhuce_hongxian2');
    $('#pwdLevel_3').attr('class', 'ywz_zhuce_huixian');
}

function Tough() {
    $('#pwdLevel_1').attr('class', 'ywz_zhuce_hongxian');
    $('#pwdLevel_2').attr('class', 'ywz_zhuce_hongxian2');
    $('#pwdLevel_3').attr('class', 'ywz_zhuce_hongxian3');
}
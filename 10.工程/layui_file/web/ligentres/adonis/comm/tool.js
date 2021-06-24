/**
 * 工具类
 * @author 10011037@qq.com
 * 2016-02-12
 */


/**
 * 判断是否为数字
 */
jQuery.extend({
	isNumber:function (value) {
		var patrn = /^(0|[1-9]\d*)$/;
		if (patrn.test(String(value))){
	        return true
	    }
		return false
	}
}); 

/**
 * 判断是否大于0
 */
jQuery.extend({
	isValid:function (value) {
		if ($.isNumber(value) && value > 0){
	        return true
	    }
		return false
	}
}); 

/**
 * 判断是否为浮点
 */
jQuery.extend({
	isDouble:function (value) {
		var patrn = /^[-\+]?\d+(\.\d+)?$/;
		if(patrn.test(value)){
	        return true
	    }
		return false
	}
});

/**
 * 判断字符串是否为空
 */
jQuery.extend({
	isEmpty:function (str) {
		if(str != null && str != "" && str != "null" && str != "undefined"){
			return false;
		}
		return true;
	}
});

/**
 * 判断字符串是否为手机格式
 */
jQuery.extend({
	isMobile:function (str) {
		str = str.replace(/(^\s*)|(\s*$)/g, "");
		// var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
		var myreg=/^[1][0-9]{10}$/;
		if (!myreg.test(str)) {
	        return false;
	    } else {
	        return true;
	    }
	}
});

/**
 * 判断字符串是否为时间类型(格式：09:30:00)
 */
jQuery.extend({
	isTime:function (str) {
		var a = str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
		if (a == null) {alert('输入的参数不是时间格式'); return false;}
		if (a[1]>24 || a[3]>60 || a[4]>60){
			return false
		}
		return true;
	}
});

/**
 * 判断字符串是否为日期时间类型(格式：2001-01-01)
 */
jQuery.extend({
	isDate:function (str) {
		var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
		if(r==null)return false;
		var d= new Date(r[1], r[3]-1, r[4]);
		return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
	}
});

/**
 * 判断字符串是否为日期类型(格式：2001-01-01 09:30:00)
 */
jQuery.extend({
	isDateTime:function (str) {
		var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
		var r = str.match(reg);
		if(r==null)return false;
		var d= new Date(r[1], r[3]-1,r[4],r[5],r[6],r[7]);
		return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]&&d.getHours()==r[5]&&d.getMinutes()==r[6]&&d.getSeconds()==r[7]);
	}
});

/**
 * 判断字符串是否为图片格式
 */
jQuery.extend({
	isPic:function (str) {
		if($.isEmpty(str)){
			return false
		}
		var index = str.lastIndexOf(".");
		var suffix = str.substring(index).toLowerCase();
		if(suffix!=".bmp"&&suffix!=".png"&&suffix!=".gif"&&suffix!=".jpg"&&suffix!=".jpeg"&&suffix!=".pic"){
			return false
		}
		return true;
	}
});

/**
 * 转换字符串
 */
jQuery.extend({
	toStr:function (value) {
		if($.isEmpty(value)){
			return "";
		}
		return value;
	}
}); 


/**
 * 字符串固定字数格式
 */
jQuery.extend({
	toHint:function (value, num) {
		if($.isEmpty(value)){
			return "";
		}
		if(value.length<=num){
			return value;
		}
		value = value.substr(0, num-3)+"...";
		return value;
	}
}); 

/**
 * 保存Cookie
 */
function setCookie(key,value,t){ 
	if(t>0){
		var oDate=new Date();
		oDate.setDate(oDate.getDate()+t);
		document.cookie=key+"="+value+"; expires="+oDate.toDateString();
	}
}

/**
 * 获取Cookie
 */
function getCookie(key){
	var arr1=document.cookie.split("; ");//由于cookie是通过一个分号+空格的形式串联起来的，所以这里需要先按分号空格截断,变成[name=Jack,pwd=123456,age=22]数组类型；
	for(var i=0;i<arr1.length;i++){
		var arr2=arr1[i].split("=");//通过=截断，把name=Jack截断成[name,Jack]数组；
		if(arr2[0]==key){
			return decodeURI(arr2[1]);
		}
	}
}

//浏览文件
function browseFile(filePath){
	if($.isEmpty(filePath) || filePath.indexOf(".")<0){
		layer_msg("文件类型不支持在线预览", 6);
	}
	var index = filePath.lastIndexOf(".");
	var suffix = filePath.substring(index+1).toLowerCase();
	if("rar,zip,7z,cab,arj,lzh,tar,gz,ace,uue,bz2,jar,iso,mpq".indexOf(suffix)>0){
		// 压缩包
		layer_msg("文件类型不支持在线预览", 6);
	}else{
		var url = 'https://view.officeapps.live.com/op/view.aspx?src=' + ligent_readPath + filePath;
        window.open(url, "_blank", "scrollbars=yes,resizable=1,modal=false,alwaysRaised=yes");
	}
	
	/*else if("doc,docx,dot,dotx,docm,dotm".indexOf(suffix)>0			// word文档
			|| "xls,xlsx,xlsm,xltx,xltm,xlsb,xlam".indexOf(suffix)>0	// excel文档
			|| "ppt,pptx,pptm,ppsx,ppsx,potx,potm".indexOf(suffix)>0	// ppt文档
			|| "txt".indexOf(suffix)>0									// 文本文档
		){
		var url = 'https://view.officeapps.live.com/op/view.aspx?src=' + ligent_readPath + filePath;
        window.open(url, "_blank", "scrollbars=yes,resizable=1,modal=false,alwaysRaised=yes");
	}else{
    	window.open(ligent_readPath + filePath, "_blank", "scrollbars=yes,resizable=1,modal=false,alwaysRaised=yes");
	}*/
}

// Base64编码解码
function Base64() {
    // private property  
    _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";  

    // public method for encoding  
    this.encode = function (input) {  
        var output = "";  
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;  
        var i = 0;  
        input = _utf8_encode(input);  
        while (i < input.length) {  
            chr1 = input.charCodeAt(i++);  
            chr2 = input.charCodeAt(i++);  
            chr3 = input.charCodeAt(i++);  
            enc1 = chr1 >> 2;  
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);  
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);  
            enc4 = chr3 & 63;  
            if (isNaN(chr2)) {  
                enc3 = enc4 = 64;  
            } else if (isNaN(chr3)) {  
                enc4 = 64;  
            }  
            output = output +  
            _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +  
            _keyStr.charAt(enc3) + _keyStr.charAt(enc4);  
        }  
        return output;  
    }  

    // public method for decoding  
    this.decode = function (input) {  
        var output = "";  
        var chr1, chr2, chr3;  
        var enc1, enc2, enc3, enc4;  
        var i = 0;  
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");  
        while (i < input.length) {  
            enc1 = _keyStr.indexOf(input.charAt(i++));  
            enc2 = _keyStr.indexOf(input.charAt(i++));  
            enc3 = _keyStr.indexOf(input.charAt(i++));  
            enc4 = _keyStr.indexOf(input.charAt(i++));  
            chr1 = (enc1 << 2) | (enc2 >> 4);  
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);  
            chr3 = ((enc3 & 3) << 6) | enc4;  
            output = output + String.fromCharCode(chr1);  
            if (enc3 != 64) {  
                output = output + String.fromCharCode(chr2);  
            }  
            if (enc4 != 64) {  
                output = output + String.fromCharCode(chr3);  
            }  
        }  
        output = _utf8_decode(output);  
        return output;  
    }  

    // private method for UTF-8 encoding  
    _utf8_encode = function (string) {
        string = string.replace(/\r\n/g,"\n");  
        var utftext = "";  
        for (var n = 0; n < string.length; n++) {  
            var c = string.charCodeAt(n);  
            if (c < 128) {  
                utftext += String.fromCharCode(c);  
            } else if((c > 127) && (c < 2048)) {  
                utftext += String.fromCharCode((c >> 6) | 192);  
                utftext += String.fromCharCode((c & 63) | 128);  
            } else {  
                utftext += String.fromCharCode((c >> 12) | 224);  
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);  
                utftext += String.fromCharCode((c & 63) | 128);  
            }  

        }  
        return utftext;  
    }  

    // private method for UTF-8 decoding  
    _utf8_decode = function (utftext) {  
        var string = "";  
        var i = 0;  
        var c = c1 = c2 = 0;  
        while ( i < utftext.length ) {  
            c = utftext.charCodeAt(i);  
            if (c < 128) {  
                string += String.fromCharCode(c);  
                i++;  
            } else if((c > 191) && (c < 224)) {  
                c2 = utftext.charCodeAt(i+1);  
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));  
                i += 2;  
            } else {  
                c2 = utftext.charCodeAt(i+1);  
                c3 = utftext.charCodeAt(i+2);  
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));  
                i += 3;  
            }  
        }  
        return string;  
    }  
}


/**
 * 只能输入金额
 */
function onlyMoney(obj){
    //得到第一个字符是否为负号    
    var t = obj.value.charAt(0);      
    //先把非数字的都替换掉，除了数字和.和-号    
    obj.value = obj.value.replace(/[^\d\.\-]/g,'');
    //前两位不能是0加数字      
    obj.value = obj.value.replace(/^0\d[0-9]*/g,''); 
    //必须保证第一个为数字而不是.       
    obj.value = obj.value.replace(/^\./g,'');       
    //保证只有出现一个.而没有多个.       
    obj.value = obj.value.replace(/\.{2,}/g,'.');       
    //保证.只出现一次，而不能出现两次以上       
    obj.value = obj.value.replace('.','$#$').replace(/\./g,'').replace('$#$','.');  
    //如果第一位是负号，则允许添加    
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');  
   if(t == '-'){ return; }
}
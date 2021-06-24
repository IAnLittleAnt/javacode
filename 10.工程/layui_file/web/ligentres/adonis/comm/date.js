var minute = 1000 * 60;
var hour = minute * 60;
var day = hour * 24;
var halfamonth = day * 15;
var month = day * 30;


/**
 * 根据格式获取时间
 * yyyy-MM-dd HH:mm:ss
 */
function formatDate(myDate, fmt){
	if($.isEmpty(myDate)){
		myDate = new Date();
	}else{
		myDate = new Date(myDate);
	}
	if(fmt == null || fmt.length < 1){
		fmt = "yyyy-MM-dd";
	}
    var o = {
        "M+": myDate.getMonth() + 1, //月份 
        "d+": myDate.getDate(), //日 
        "H+": myDate.getHours(), //小时 
        "m+": myDate.getMinutes(), //分 
        "s+": myDate.getSeconds(), //秒 
        "q+": Math.floor((myDate.getMonth() + 3) / 3), //季度 
        "S": myDate.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (myDate.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/**
 * 时间戳转时间
 */
function getDateByTimeStamp(timeStamp) {
	return new Date(parseInt(timeStamp) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');
}

/**
 * 时间转时间戳
 */
function getTimeStampByDate(dateStr){
	dateStr = dateStr.replace(/(^\s*)|(\s*$)/g, "");
	if(dateStr != null && dateStr.length > 19){
		dateStr = dateStr.substr(0,19);
	}
 	return Date.parse(dateStr.replace(/-/gi,"/"));
}

/**
 * 时间跟现在对比
 */
function getDateDiff(dateTimeStamp){
	var result = "";
	var now = new Date().getTime();
	var diffValue = now - dateTimeStamp;
	if(diffValue < 0){
	 	result="刚刚";
		return result;
	}
	var monthC = diffValue/month;
	var weekC = diffValue/(7*day);
	var dayC = diffValue/day;
	var hourC = diffValue/hour;
	var minC = diffValue/minute;
	
	if(monthC>=1){
	 	result = parseInt(monthC) + "月前";
	}else if(weekC>=1){
		result = parseInt(weekC) + "周前";
	}else if(dayC>=1){
	 	result = parseInt(dayC) + "天前";
	}else if(hourC>=1){
	 	result = parseInt(hourC) + "小时前";
	}else if(minC>=1){
	 	result = parseInt(minC) + "分钟前";
	}else{
	 	result = "刚刚";
	}
	return result;
} 

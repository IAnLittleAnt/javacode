package com.cn.comm;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cn.unit.date.DateConst;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * 工具类
 * @author 10011037@qq.com
 * 2016-02-12
 */
public class UseTool {

	/**
	 * 判断Integer是否大于0
	 */
	public static boolean isValid(Integer str) {
		return (str != null && str.intValue() > 0);
	}

	/**
	 * 判断字符串是否为空
	 */
	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str.toString()) || "null".equals(str.toString().trim())
				 || "".equals(str.toString().trim()) || "undefined".equals(str.toString()));
	}

	/**
	 * 判断字符串是否是数字
	 */
	public static boolean isInt(Object str) {
		if (isEmpty(str)) {
			return false;
		}
		str = str.toString().trim();
		Pattern pattern = Pattern.compile("[-+]?[0-9]*");
		return pattern.matcher(str.toString()).matches();
	}
	
	/**
	 * 判断字符串是否是Long类型
	 */
	public static boolean isLong(Object str){
		if (isEmpty(str)) {
			return false;
		}
		str = str.toString().trim();
	    for(int i = str.toString().length(); --i >= 0;){
	        int c = str.toString().charAt(i);
	        if( c < 48 || c > 57 ){
	            return false;
	        }
	    }
	    return true;
	}

	/**
	 * 判断字符串是否是Double
	 */
	public static boolean isDouble(Object str) {
		if(isEmpty(str)){
			return false;
		}
		str = str.toString().trim();
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$"); 
		return pattern.matcher(str.toString()).matches();
	}

	/**
	 * 判断是否为时间类型
	 * @return
	 */
	public static boolean isTime(Object obj) {
		if (UseTool.isEmpty(obj)) {
            return false;
        }
        String datevalue = UseTool.toStr(obj).replaceAll("/", "-");
        
	    String format = DateConst.YYYY_MM_DD;
		if(datevalue.indexOf(":")>=0 && datevalue.indexOf("-")>=0){
    		format= DateConst.YYYY_MM_DD_HH_MM_SS;
    	}else if(datevalue.indexOf(":")>=0){
    		format= DateConst.H_M_S;
    	}
		
        SimpleDateFormat fmt = new SimpleDateFormat(format);
    	try {
    		Date dd = fmt.parse(datevalue);
            if (datevalue.equals(fmt.format(dd))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

	/**
	 * 判断字符串是否是Json
	 */
	public static boolean isJson(Object str) {
		if(isEmpty(str)){
			return false;
		}
		try {
			new JsonParser().parse(str.toString());  
			return true;
		} catch (JsonParseException e) {
		    return false;    
		}    
	}

	/**
	 * 判断字符串是否是手机号码
	 */
	public static boolean isMobile(Object str) {
		if(isEmpty(str)){
			return false;
		}
		// 判断是否为大陆手机
		// String regExp = "^((13[0-9])|(15[^4])|(18[0,1,2,3,5-9])|(17[0-8])|(147))\\d{8}$";  
		// String regExp = "^[1][3,4,5,7,8][0-9]{9}$";
		String regExp = "^[1][0-9]{10}$";
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str.toString());  
        boolean isChina = m.matches();
        if(isChina){
        	return isChina;
        }
		// 判断是否为香港手机
        regExp = "^(5|6|8|9)\\d{7}$";  
        p = Pattern.compile(regExp);  
        m = p.matcher(str.toString());  
        boolean isHK = m.matches();
        return isHK;
	}

	/**
	 * 判断字符串是否是语音格式
	 */
	public static boolean isSpeech(Object str) {
		if (isEmpty(str)) {
			return false;
		}
		str = str.toString().trim().toLowerCase();
		return "amr".equals(str);
	}
	
	/**
	 * 绑定模糊查询条件值
	 */
	public static String toQuery(String str) {
		return "%" + (isEmpty(str) ? "" : str) + "%";
	}

	/**
	 * 过滤SQL注入字符
	 */
	public static String toFilter(Object str) {
		if(isEmpty(str)){
			return null;
		}
		String s = str.toString().trim();
		//String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		String regEx = "[\\[\\]`'/?]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(s);
		s = m.replaceAll("").trim();
		return s;
	}

	/**
	 * 转换为字符串
	 */
	public static String toStr(Object str) {
		return isEmpty(str) ? null : str.toString().trim();
	}

	/**
	 * 转换为Json字符串
	 */
	public static String toJson(Object str) {
		return isEmpty(str) ? null : JSON.toJSONString(str, SerializerFeature.WriteMapNullValue);
	}

	/**
	 * 转换为整型
	 */
	public static Integer toInt(Object str) {
		Integer num = null;
		if(isInt(str)){
			num = Integer.parseInt(str.toString());
		}else{
			if(isDouble(str)){
				num = toDouble(str).intValue();
			}
		}
		return num;
		//return isNumeric(str) ? Integer.parseInt(str.toString()) : isDouble(str) ? toDouble(str).intValue() : null;
	}
	
	/**
	 * 判断字符串是否是Long类型
	 */
	public static Long toLong(Object str){
		if (isEmpty(str)) {
			return null;
		}
		try{
		    return Long.parseLong(str.toString());
		}catch(NumberFormatException e){
		    return null;
		}
	}

	/**
	 * 转换为Double
	 */
	public static Double toDouble(Object str) {
		return isDouble(str) ? Double.parseDouble(str.toString()) : null;
	}
	
	/**
	 * 转换为时间类型
	 */
	public static Date toTime(Object obj) {
		if (isEmpty(obj)) {
			return null;
		}
        String datevalue = UseTool.toStr(obj).replaceAll("/", "-");
        
		String format = DateConst.YYYY_MM_DD;
		if(datevalue.indexOf(":")>=0 && datevalue.indexOf("-")>=0){
			format = DateConst.YYYY_MM_DD_HH_MM_SS;
    	}else if(datevalue.indexOf(":")>=0){
    		format = DateConst.H_M_S;
    	}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(datevalue);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 转换为List
	 */
	public static List<Object> toList(Object obj) {
		List<Object> result = new ArrayList<Object>();
	    if (obj instanceof List<?>) {
	        for (Object o : (List<?>) obj) {
	            result.add(o);
	        }
	    }
		return result;
	}
	
	/**
	 * 处理手机格式
	 */
	public static String handleMobileFormat(String phone){
		String mobile = phone;
		if(!isEmpty(phone) && phone.length() == 11){
			mobile = phone.substring(0,phone.length()-(phone.substring(3)).length())
					+"****"+phone.substring(7);
		}
		return mobile;
	}
	
	/**
	 * 转换字符串编码格式
	 */
	public static String handleStrFormat(String str) {
		String name = "";
		if (str != null && !"".equals(str)) {
			try {
				name = new String(str.getBytes("iso8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return name;
	}
	
	/**
	 * 把数组类型转换为字符串类型
	 * @param array数组
	 * @return 字符串
	 */
	public static String getStrToArray(Object[] array) {
		String ids = "";
		if (array != null) {
			for (Object id : array) {
				ids += id + ",";
			}
			ids = ids.substring(0, ids.length() - 1);
		}
		return ids;
	}
	
	/**
	 * 生成固定长度的随机数
	 * @param n 随机数的位数
	 * @return 固定长度的随机数
	 */
	public static int getFixLenthRandom(int lenth){
		long m = 1;
		for(int i = 1; i < lenth; i++){
			m = m * 10;
		}
		return (int)((Math.random()*9+1)*m);
	}
	
	/**
	 * 生成固定范围的随机数
	 * @param min 最小值
	 * @param max 最大值
	 * @return 随机数
	 */
	public static int getFixScopeRandom(int min, int max){
	    Random random = new Random();
	    return random.nextInt(max) % (max - min + 1) + min;
	}
	
	/**
	 * 获取文件后缀
	 * @param fileName 文件名
	 * @return 文件后缀
	 */
	public static String getSuffix(String fileName) {
		if (fileName != null && fileName != "" && fileName.indexOf(".") >= 0) {
			String suffix = fileName.substring(
					fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
			return suffix;
		}
		return null;
	}
	
	/**
	 * 判断是否为Excel文档
	 * @param fileName 文件名
	 * @return 空代表不是Excel文档
	 */
	public static String isExcel(String fileName) {
		String suffixName = getSuffix(fileName);
		if(isEmpty(suffixName)){
			return null;
		}
		String picSuffix[] = {"xlsx", "xls", "xlsm"};
		for (String suffix : picSuffix) {
			if (suffixName.equals(suffix)) {
				return suffix;
			}
		}
		return null;
	}
	
	/**
	 * 判断是否为图片
	 * @param fileName 文件名
	 * @return 空代表不是图片
	 */
	public static String isPicture(String fileName) {
		String suffixName = getSuffix(fileName);
		if(isEmpty(suffixName)){
			return null;
		}
		String picSuffix[] = {"bmp", "dib", "gif", "jfif", "jpe", "jpeg", "jpg", 
				"png", "tif", "tiff", "ico"};
		for (String suffix : picSuffix) {
			if (suffixName.equals(suffix)) {
				return suffix;
			}
		}
		return null;
	}
	
	/**
	 * 判断是否为视频
	 * @param fileName 文件名
	 * @return 空代表不是视频
	 */
	public static String isVideo(String fileName) {
		String suffixName = getSuffix(fileName);
		if(isEmpty(suffixName)){
			return null;
		}
		String picSuffix[] = {"avi", "rmvb", "rm", "asf", "divx", "mpg", "mov", 
				"mpeg", "mpe", "wmv", "mp4", "mkv", "vob"};
		for (String suffix : picSuffix) {
			if (suffixName.equals(suffix)) {
				return suffix;
			}
		}
		return null;
	}
	
	/**
	 * 文件大小单位转换
	 * @param fileSize 文件大小
	 * @return 大小字符串
	 */
    public static String formetFileSize(Long fileSize) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";  
        if (fileSize < 1024) {
            fileSizeString = df.format((double) fileSize) + "B";
        } else if (fileSize < 1048576) {
            fileSizeString = df.format((double) fileSize / 1024) + "K";
        } else if (fileSize < 1073741824) {
            fileSizeString = df.format((double) fileSize / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileSize / 1073741824) + "G";
        }
        return fileSizeString;
    }
    
}

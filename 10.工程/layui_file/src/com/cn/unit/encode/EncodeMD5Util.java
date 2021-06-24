package com.cn.unit.encode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * @author Adonis_1111@163.com
 * 2016-09-01
 */
public class EncodeMD5Util {
	
	/**
	 * MD5 加密算法
	 * @param obj 加密前数据
	 * @return 加密后数据
	 */
	public static String MD5Encode(String obj){
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(obj.getBytes());
			return EncodeUtil.bcd2Str(md5.digest()); 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

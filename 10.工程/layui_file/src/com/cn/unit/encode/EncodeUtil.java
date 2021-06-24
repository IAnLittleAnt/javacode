package com.cn.unit.encode;

/**
 * 数据加密工具类
 * @author Adonis_1111@163.com
 * 2016-09-01
 */
public class EncodeUtil {
	
	/**
	 * BCD转字符串
	 */
	public static String bcd2Str(byte[] bytes) {
		char temp[] = new char[bytes.length * 2], val;
		
		for (int i = 0; i < bytes.length; i++) {
			val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
			temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

			val = (char) (bytes[i] & 0x0f);
			temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
		}
		return new String(temp);
	}
	
}

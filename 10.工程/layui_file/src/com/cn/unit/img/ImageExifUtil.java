package com.cn.unit.img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 图片属性工具类
 * @author 10011037@qq.com
 * 2016-02-12
 */
public class ImageExifUtil {
	
    /** 
     * 获取图片宽度高度
     * @param file 图片文件
     * @return 宽度/高度
     */  
    public static int[] getImgWH(String picPath) {
    	File file = new File(picPath);
        InputStream is = null;
        BufferedImage src = null;
		int result[] = { 0, 0 };
		try {  
            is = new FileInputStream(file);  
            src = javax.imageio.ImageIO.read(is);  
            result[0] = src.getWidth(null); // 得到源图宽  
            result[1] = src.getHeight(null); // 得到源图高  
            is.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
	
}

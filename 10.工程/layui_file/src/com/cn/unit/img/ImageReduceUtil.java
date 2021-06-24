package com.cn.unit.img;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片压缩工具类
 * @author 10011037@qq.com 
 * 2016-02-12
 */
@SuppressWarnings("restriction")
public class ImageReduceUtil {
	
	// ===源图片路径名称.如:D:\1.jpg
	private String srcpath;
	
	// ===剪切图片存放路径名称.如:D:\2.jpg
	private String subpath;
	
	// ===缩略图最大宽度
	private int width;
	
	// ===缩略图质量
	private int quality;
	
	/**
	 * 压缩图片
	 * @param srcpath 源图片路径
	 * @param subpath 剪切图片存放路径
	 * @param width 缩略图最大宽度
	 * @param quality 缩略图质量
	 */
	public ImageReduceUtil(String srcpath, String subpath, int width, int quality){
		// 缩略图质量
		quality = quality >= 1 ? 1 : quality;
		// 判断图片高度宽度
		int[] exif = ImageExifUtil.getImgWH(srcpath);
		int w_ = exif[0];
		width = width >= w_ ? width : w_;
		
		this.srcpath = srcpath;
		this.subpath = subpath;
		this.width = width;
		this.quality = quality;
	}
	
	
	/**
	 * 裁剪并压缩、使用方型区域颜色平均算法
	 */
	public void resizeFix() {
		File originalFile = new File(getSrcpath());
		File resizedFile = new File(getSubpath());
		
		ImageIcon ii = null;
		try {
			ii = new ImageIcon(originalFile.getCanonicalPath());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Image i = ii.getImage();
		Image resizedImage = null;
		
		int iWidth = i.getWidth(null);
		int iHeight = i.getHeight(null);
		if (iWidth > iHeight) {
			resizedImage = i.getScaledInstance(getWidth(), (getWidth() * iHeight)
					/ iWidth, Image.SCALE_SMOOTH);
		} else {
			resizedImage = i.getScaledInstance((getWidth() * iWidth) / iHeight,
					getWidth(), Image.SCALE_SMOOTH);
		}
		
		// 获取图片中的所有像素
		Image temp = new ImageIcon(resizedImage).getImage();
		
		// 创建缓冲
		BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),
				temp.getHeight(null), BufferedImage.TYPE_INT_RGB);
		
		// 复制图片到缓冲流中
		Graphics g = bufferedImage.createGraphics();
		
		// 清除背景并开始画图
		g.setColor(Color.white);
		g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
		g.drawImage(temp, 0, 0, null);
		g.dispose();
		
		// 柔和图片.
		float softenFactor = 0.05f;
		float[] softenArray = { 0, softenFactor, 0, softenFactor,
				1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
		Kernel kernel = new Kernel(3, 3, softenArray);
		ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
		bufferedImage = cOp.filter(bufferedImage, null);
		bufferedImage.flush();
		try {
			FileOutputStream out = new FileOutputStream(resizedFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);
			param.setQuality(getQuality(), true);
			encoder.setJPEGEncodeParam(param);
			encoder.encode(bufferedImage);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getSrcpath() {
		return srcpath;
	}
	public void setSrcpath(String srcpath) {
		this.srcpath = srcpath;
	}
	public String getSubpath() {
		return subpath;
	}
	public void setSubpath(String subpath) {
		this.subpath = subpath;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	
}

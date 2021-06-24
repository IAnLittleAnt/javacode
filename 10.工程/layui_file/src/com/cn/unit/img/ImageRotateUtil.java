package com.cn.unit.img;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 图片旋转工具类
 * @author 10011037@qq.com 
 * 2016-02-12
 */
public class ImageRotateUtil {
	
	// ===源图片路径名称如:D:\1.jpg
	private String srcpath;
	
	// ===剪切图片存放路径名称.如:D:\2.jpg
	private String subpath;
	
	// ===新图类型 只支持gif,jpg,png
	private String suffix;
	
	// ===旋转角度
	private int degree;
    
    public ImageRotateUtil(String srcpath, String subpath, String suffix, int degree) {
        this.srcpath = srcpath;
        this.subpath = subpath;
        this.suffix = suffix;
        this.degree = degree;
    }
	
	/**
     * 旋转
     */
    public void spin() throws Exception {
        int swidth = 0; // 旋转后的宽度
        int sheight = 0; // 旋转后的高度
        int x; // 原点横坐标
        int y; // 原点纵坐标

        File file = new File(srcpath);
        if (!file.isFile()) {
            throw new Exception("ImageDeal>>>" + file + " 不是一个图片文件!");
        }
        BufferedImage bi = ImageIO.read(file); // 读取该图片
        // 处理角度--确定旋转弧度
        degree = degree % 360;
        if (degree < 0)
            degree = 360 + degree;// 将角度转换到0-360度之间
        double theta = Math.toRadians(degree);// 将角度转为弧度
        
        // 确定旋转后的宽和高
        if (degree == 180 || degree == 0 || degree == 360) {
            swidth = bi.getWidth();
            sheight = bi.getHeight();
        } else if (degree == 90 || degree == 270) {
            sheight = bi.getWidth();
            swidth = bi.getHeight();
        } else {
            swidth = (int) (Math.sqrt(bi.getWidth() * bi.getWidth()
                    + bi.getHeight() * bi.getHeight()));
            sheight = (int) (Math.sqrt(bi.getWidth() * bi.getWidth()
                    + bi.getHeight() * bi.getHeight()));
        }
        
        x = (swidth / 2) - (bi.getWidth() / 2);// 确定原点坐标
        y = (sheight / 2) - (bi.getHeight() / 2);
        
        BufferedImage spinImage = new BufferedImage(swidth, sheight, bi.getType());
        // 设置图片背景颜色
        Graphics2D gs = (Graphics2D) spinImage.getGraphics();
        gs.setColor(Color.white);
        gs.fillRect(0, 0, swidth, sheight);// 以给定颜色绘制旋转后图片的背景
        
        AffineTransform at = new AffineTransform();
        at.rotate(theta, swidth / 2, sheight / 2);// 旋转图象
        at.translate(x, y);
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        spinImage = op.filter(bi, spinImage);
        File sf = new File(subpath);
        ImageIO.write(spinImage, suffix, sf); // 保存图片
        
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
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}

	public static void main(String[] args) {
		ImageRotateUtil operateImage = new ImageRotateUtil(
				"D:/20190215170610.jpg", "D:/20190215170610_2.jpg", "jpg", 90
				);
		try {
			operateImage.spin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
}

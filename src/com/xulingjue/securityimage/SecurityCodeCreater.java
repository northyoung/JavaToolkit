package com.xulingjue.securityimage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class SecurityCodeCreater {
	// 图片的宽度
	private int imageWidth = 15;
	// 图片的高度
	private int imageHeight = 22;
	// 字体大小
	private int fountSize = 18;
	// 字符串长度
	private int codeLength = 4;
	
	private Random random = null;
	
	public void setImageWidth(int width){
		this.imageWidth = width;
	}
	
	public void setImageHeight(int height){
		this.imageHeight = height;
	}
	
	public void setFountSize(int size){
		this.fountSize = size;
	}
	
	public void setCodeLength(int length){
		this.codeLength = length;
	}
	
	//随机字符范围
    private final char[] CHAR_RANGE = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                    'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                    'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
                    'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                    'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                    'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
                    '8', '9'
    };
    
    public SecurityCodeCreater(){
    	this.random = new Random();
    }
    
    /**
     * 生成随机字符串
     * @return
     */
	private String getRandString() {
		
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.codeLength; i++)
			sb.append(CHAR_RANGE[random.nextInt(CHAR_RANGE.length)]);
		
		return sb.toString();
	}
	
	/**
     * 生成随机颜色
     * @param ll 产生颜色值下限(lower limit)
     * @param ul 产生颜色值上限(upper limit)
     * @return 生成的随机颜色对象
     */
    private Color getRandColor(int ll, int ul){
        if (ll > 255) ll = 255;
        if (ll < 1) ll = 1;
        if (ul > 255) ul = 255;
        if (ul < 1) ul = 1;
        if (ul == ll) ul = ll + 1;
        
        int r = random.nextInt(ul - ll) + ll;
        int g = random.nextInt(ul - ll) + ll;
        int b = random.nextInt(ul - ll) + ll;
        
        Color color = new Color(r,g,b);
        return color;
    } 
    
    /**
     * 生成指定字符串的图像数据
     * @param verifyCode 即将被打印的随机字符串
     * @return 生成的图像数据
     * */
    public BufferedImage getImageBuffer(){
        String verifyCode = getRandString();
        //生成画布
        BufferedImage image = new BufferedImage(this.imageWidth * this.codeLength, this.imageHeight, BufferedImage.TYPE_INT_RGB);
        
        //获取图形上下文 （生成画笔）
        Graphics graphics = image.getGraphics();
        //设置背景色（）
        graphics.setColor(getRandColor(1,50));
        //填充矩形区域 ，作为背景
        graphics.fillRect(0, 0, this.imageWidth * 4, this.imageHeight);
        //设置边框颜色
        graphics.setColor(new Color(0,255,0));
       
        //画出边框
        for (int i=0; i<2; i++)
            graphics.drawRect(i, i, this.imageWidth * this.codeLength - i * 2 - 1, this.imageHeight - i * 2 - 1);
        
        //设置随机干扰线条颜色
        graphics.setColor(getRandColor(50,100));
       
        //产生50条干扰线条
        for (int i=0; i<50; i++){
            int x1 = random.nextInt(this.imageWidth * this.codeLength - 4) + 2;
            int y1 = random.nextInt(this.imageHeight - 4) + 2;
            int x2 = random.nextInt(this.imageWidth * this.codeLength - 2 - x1) + x1;
            int y2 = y1;
            graphics.drawLine(x1, y1, x2, y2);
        }
        
        //设置字体
        graphics.setFont(new Font("Times New Roman", Font.PLAIN, this.fountSize));
       
        //画字符串
        for (int i=0; i<this.codeLength; i++){
        	String temp = verifyCode.substring(i, i+1);
            graphics.setColor(getRandColor(100,255));
            graphics.drawString(temp, 13 * i + 6, 16);
        }
        
        //图像生效
        graphics.dispose();
        
        return image;
    }
    
    /**
     * 将验证码图片保存为文件
     * @return
     */
    public File getImagePath(String fileName){
    	BufferedImage image = this.getImageBuffer();
    	File imageFile = null;
    	try {
    		imageFile = new File(fileName);
			ImageIO.write(image,"jpg",imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return imageFile;
    }
    
    public static void main(String [] agrs){
    	SecurityCodeCreater scc = new SecurityCodeCreater();
    	
    	scc.setCodeLength(5);
    	scc.setFountSize(16);
    	
    	File imageFile = scc.getImagePath("d:/gray12.jpg");
    	System.out.println(imageFile.getPath());
    }
    
	
}

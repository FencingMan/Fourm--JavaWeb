package com.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 验证码生成servlet
 * @author 潘声文
 *
 */
@WebServlet("/checkcode")
public class CheckcodeServlet extends HttpServlet{
		
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			//一绘图
			//画布
			BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
			
			//获得画笔
			Graphics g = image.getGraphics();
			
			//给画笔设置颜色
			g.setColor(new Color(255, 255, 255));
			
			//设置画布背景颜色
			g.fillRect(0, 0, 80, 30);
			g.setFont(new Font(null, Font.ITALIC, 24));
			
			//重新设置画笔颜色
			Random r = new Random();
			g.setColor(new Color(r.nextInt(255), r.nextInt(255),r.nextInt(255)));
			
			//生成一个5位随机数字
			//String code = r.nextInt(99999)+"";
			
			//生成5位验证码
			String code = getcode().toLowerCase();
			System.out.println(code);
			HttpSession hs = req.getSession();
			hs.setAttribute("code", code);
			
			//画图
			g.drawString(code, 2, 25);
			//干扰线
			for(int i=0;i<8;i++){
				g.setColor(new Color(r.nextInt(255), r.nextInt(255),r.nextInt(255)));
				g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(30));
			}
			
			//二、压缩图片、输出
			resp.setContentType("image/jpg");
			//输出流
			OutputStream ops = resp.getOutputStream();
			//压缩并输出的方法
			javax.imageio.ImageIO.write(image, "jpeg", ops);
			ops.close();
			
		}

		private String getcode() {
			String code = "";
			String chars = "QAZWSXEDCRFVTGBYHNUJMKLP2345qazwsxedcrfvtgbyhnujmklp6789" ;
			Random r  = new Random();
			for(int i=0 ;i<5;i++){
				code += chars.charAt(r.nextInt(chars.length()));
			}
			return code;
		}
}

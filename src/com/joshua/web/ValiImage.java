package com.joshua.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValiImage
 */
@WebServlet("/ValiImage")
public class ValiImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValiImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setIntHeader("Expires", -1);
		response.setHeader("Catch-Control", "no-catch");
		response.setHeader("Pragma", "no-catch");
		// ��ȡһ��ͼƬ
		int height = 30;
		int width = 120;
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// ��������
		Graphics2D g = (Graphics2D) img.getGraphics();
		// ���ñ���ɫ
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		// ���ñ߿�
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);
		// ��������
		for (int i = 0; i < 5; i++) {
			g.setColor(Color.BLACK);
			g.drawLine(randNum(0, width), randNum(0, height), randNum(0, width), randNum(0, height));
		}
		// д��
		String base="\u7684\u4e00\u662f\u4e86\u6211\u4e0d\u4eba\u5728\u4ed6\u6709\u8fd9\u4e2a\u4e0a\u4eec\u6765\u5230\u65f6\u5927\u5730\u4e3a\u5b50\u4e2d\u4f60\u8bf4\u751f\u56fd\u5e74\u7740\u5c31\u90a3"
		+"\u548c\u8981\u5979\u51fa\u4e5f\u5f97\u91cc\u540e\u81ea\u4ee5\u4f1a\u5bb6\u53ef\u4e0b\u800c\u8fc7\u5929\u53bb\u80fd\u5bf9\u5c0f\u591a\u7136\u4e8e\u5fc3\u5b66\u4e48\u4e4b\u90fd\u597d"		
		+"\u770b\u8d77\u53d1\u5f53\u6ca1\u6210\u53ea\u5982\u4e8b\u628a\u8fd8\u7528\u7b2c\u6837\u9053\u60f3\u4f5c\u79cd\u5f00\u7f8e\u603b\u4ece\u65e0\u60c5\u5df1\u9762\u6700\u5973\u4f46\u73b0"		
		+"\u524d\u4e9b\u6240\u540c\u65e5\u624b\u53c8\u884c\u610f\u52a8\u65b9\u671f\u5b83\u5934\u7ecf\u957f\u513f\u56de\u4f4d\u5206\u7231\u8001\u56e0\u5f88\u7ed9\u540d\u6cd5\u95f4\u65af\u77e5";		
		StringBuffer buffer=new StringBuffer();
		for (int i = 0; i < 4; i++) {
			g.setColor(new Color(randNum(0, 255), randNum(0, 255), randNum(0, 255)));
			g.setFont(new Font("宋体", Font.BOLD, 20));
			int r=randNum(-45,45);
			g.rotate(1.0*r/180*Math.PI, 5+(i*30), 22);
			String s=base.charAt(randNum(0,base.length()-1))+"";
			buffer.append(s);
			g.drawString(s, 5+(i*30), 22);
			g.rotate(1.0*-r/180*Math.PI, 5+(i*30), 22);
		}
		request.getSession().setAttribute("valicode", buffer.toString());
		System.out.println(buffer.toString());
		// ��ͼƬ����������
		ImageIO.write(img, "jpg", response.getOutputStream());
	}

	private Random rd = new Random();

	private int randNum(int begin, int end) {
		return rd.nextInt(end - begin) + begin;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

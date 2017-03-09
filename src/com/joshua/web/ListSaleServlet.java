package com.joshua.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joshua.domain.SaleListForm;
import com.joshua.factory.BasicFactory;
import com.joshua.service.OrderService;

/**
 * Servlet implementation class ListSaleServlet
 */
@WebServlet("/ListSaleServlet")
public class ListSaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListSaleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderService service = BasicFactory.getFactory().getService(OrderService.class);
		// 将查询到的销售榜单信息存储到List中
		List<SaleListForm> list = service.saleList();
		StringBuilder sb = new StringBuilder();
		sb.append("商品编号,商品名称,商品数量\r\n");
		// 循环遍历List中的数据
		for (SaleListForm item : list) {
			sb.append(item.getProd_id() + "," + item.getProd_name() + "," + item.getSal_num() + "\r\n");
		}
		String data = sb.toString();
		// 将销售榜单内容组织成csv格式输出
		String fileName = "Estore销售榜单_" + new Date().toLocaleString() + ".csv";
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
		response.setContentType(this.getServletContext().getMimeType(fileName));
		response.getWriter().write(data);
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

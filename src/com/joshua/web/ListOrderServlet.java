package com.joshua.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joshua.domain.Order;
import com.joshua.domain.OrderItem;
import com.joshua.domain.OrderListForm;
import com.joshua.domain.User;
import com.joshua.factory.BasicFactory;
import com.joshua.service.OrderService;

/**
 * Servlet implementation class ListOrderServlet
 */
@WebServlet("/ListOrderServlet")
public class ListOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListOrderServlet() {
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
		// 从session中获得用户user
		User user = (User) request.getSession().getAttribute("user");
		// 调用service中方法根据根据用户user的id查找所有订单
		List<OrderListForm> list = service.findOrders(user.getId());
		// 将查询到的订单存入request域中并且带入页面显示
		request.setAttribute("list", list);
		request.getRequestDispatcher("orderList.jsp").forward(request, response);
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

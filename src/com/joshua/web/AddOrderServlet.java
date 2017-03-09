package com.joshua.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.joshua.domain.Order;
import com.joshua.domain.OrderItem;
import com.joshua.domain.Prod;
import com.joshua.domain.User;
import com.joshua.factory.BasicFactory;
import com.joshua.service.OrderService;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddOrderServlet() {
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
		try {
			// 创建一个Order的实例
			Order order = new Order();
			// 将订单信息存入上面创建的Order实例中
			order.setId(UUID.randomUUID().toString());
			order.setPaystate(0);
			BeanUtils.populate(order, request.getParameterMap());
			Map<Prod, Integer> cartmap = (Map<Prod, Integer>) request.getSession().getAttribute("cartmap");
			double money = 0;
			List<OrderItem> list = new ArrayList<OrderItem>();
			for (Map.Entry<Prod, Integer> entry : cartmap.entrySet()) {
				money += entry.getKey().getPrice() * entry.getValue();
				OrderItem item = new OrderItem();
				item.setOrder_id(order.getId());
				item.setProduct_id(entry.getKey().getId());
				item.setBuynum(entry.getValue());
				list.add(item);
			}
			order.setMoney(money);
			order.setList(list);
			User user = (User) request.getSession().getAttribute("user");
			order.setUser_id(user.getId());
			// 调用service中的方法将将订单信息添加到数据库Order表中
			service.addOrder(order);
			// 清空cartmap中的信息
			cartmap.clear();
			// 返回主页
			response.getWriter().write("订单生成成功，请前往支付");
			response.setHeader("Refresh", "3;url=index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
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

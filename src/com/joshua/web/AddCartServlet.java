package com.joshua.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joshua.domain.Prod;
import com.joshua.factory.BasicFactory;
import com.joshua.service.ProdService;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdService service = BasicFactory.getFactory().getService(ProdService.class);
		// 1.根据id查找出这个prod
		String id = request.getParameter("id");
		Prod prod = service.findProdById(id);
		// 2.将这个prod添加到cartmap中，如果之前没有这个商品，则添加，数量设置为1，如果已经有了，数量加1
		Map<Prod, Integer> cartmap = (Map<Prod, Integer>) request.getSession().getAttribute("cartmap");
		if (prod == null) {
			throw new RuntimeException("未找到此商品，无法添加到购物车");
		} else {
			cartmap.put(prod, cartmap.containsKey(prod) ? cartmap.get(prod) + 1 : 1);
		}
		//3.重定向到cart.jsp
		response.sendRedirect("cart.jsp");
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

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
 * Servlet implementation class ChangeCartNumServlet
 */
@WebServlet("/ChangeCartNumServlet")
public class ChangeCartNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeCartNumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdService service=BasicFactory.getFactory().getService(ProdService.class);
		//1.获取id
		String id=request.getParameter("id");
		//2.调用service中的方法根据id获取prod信息
		Prod prod=service.findProdById(id);
		Map<Prod,Integer> cartmap=(Map<Prod, Integer>) request.getSession().getAttribute("cartmap");
		if(prod==null){
			throw new RuntimeException("未能找到该商品！");
		}else{
			cartmap.put(prod, Integer.parseInt(request.getParameter("buynum")));
		}
		//3.重定向到cart.jsp
		response.sendRedirect("cart.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

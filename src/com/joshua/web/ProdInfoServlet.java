package com.joshua.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joshua.domain.Prod;
import com.joshua.factory.BasicFactory;
import com.joshua.service.ProdService;

/**
 * Servlet implementation class ProdInfoServlet
 */
@WebServlet("/ProdInfoServlet")
public class ProdInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdService service=BasicFactory.getFactory().getService(ProdService.class);
		//1.获取商品id
		String id=request.getParameter("id");
		//2.调用service中方法根据id查找prod信息
		Prod prod=service.findProdById(id);
		//3.将查找到的prod信息放到prodInfo.jsp页面显示
		if(prod==null){
			throw new RuntimeException("查找不到该商品");
		}else{
			request.setAttribute("prod", prod);
			request.getRequestDispatcher("prodInfo.jsp").forward(request, response);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

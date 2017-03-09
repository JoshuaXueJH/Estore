package com.joshua.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joshua.domain.User;
import com.joshua.factory.BasicFactory;
import com.joshua.service.UserService;

/**
 * Servlet implementation class ActiveServlet
 */
@WebServlet("/ActiveServlet")
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActiveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service=BasicFactory.getFactory().getService(UserService.class);
		//1.获取激活码
		String activecode=request.getParameter("activecode");
		//2.调用service中的方法激活
		User user=service.active(activecode);
		//3.登录用户
		request.getSession().setAttribute("user", user);
		//4.提示用户激活成功,3秒后返回主页
		response.getWriter().write("用户激活成功，三秒后返回用户主页");
		response.setHeader("ReFresh", "3;url=index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

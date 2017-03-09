package com.joshua.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joshua.domain.User;
import com.joshua.factory.BasicFactory;
import com.joshua.service.UserService;
import com.joshua.util.MD5Utils;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogInServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service = BasicFactory.getFactory().getService(UserService.class);
		// 1.获取登录页面的用户名和密码
		String username = request.getParameter("username");
		String password =MD5Utils.md5( request.getParameter("password"));
		// 2.调用service中的方法查找数据库中是否存在匹配的username和password
		User user = service.findUserByUsernameAndPsw(username, password);
		// 3.如果存在，则跳转到index.jso页面，如果不存在，则提示,如果未激活，则提示
		if (user == null) {
			request.setAttribute("msg", "用户名或密码错误，请重新输入！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		if (user.getState() != 1) {
			request.setAttribute("msg", "用户未激活，请到邮箱中先进性激活操作！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		request.getSession().setAttribute("user", user);

		// 处理记住用户名30天开始
		if("true".equals(request.getParameter("remname"))){
			Cookie remnameC = new Cookie("remname",URLEncoder.encode(user.getUsername(),"utf-8"));
			remnameC.setPath("/");
			remnameC.setMaxAge(3600*24*30);
			response.addCookie(remnameC);
		}else{
			Cookie remnameC = new Cookie("remname","");
			remnameC.setPath("/");
			remnameC.setMaxAge(0);
			response.addCookie(remnameC);
		}
		// 处理30天自动登录
		if("true".equals(request.getParameter("autologin"))){
			Cookie autologinC = new Cookie("autologin",URLEncoder.encode(user.getUsername()+":"+user.getPassword(),"utf-8"));
			autologinC.setPath("/");
			autologinC.setMaxAge(3600*24*30);
			response.addCookie(autologinC);
		}

		response.sendRedirect("index.jsp");
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

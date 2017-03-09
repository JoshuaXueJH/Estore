package com.joshua.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import com.joshua.domain.User;
import com.joshua.factory.BasicFactory;
import com.joshua.service.UserService;
import com.joshua.util.MD5Utils;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service=BasicFactory.getFactory().getService(UserService.class);
		try {
			// 1.校验验证码
			String valistr = request.getParameter("valistr");
			String valistr2 = (String) request.getSession().getAttribute("valicode");
			if (valistr == null || valistr2 == null || !valistr.equals(valistr2)) {
				request.setAttribute("msg", "<font color='red'>验证码不正确！</fond>");
				request.getRequestDispatcher("/regist.jsp").forward(request, response);
				return;
			}
			// 2.封装数据，校验数据
			// 3.调用service方法注册用户
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			user.setPassword(MD5Utils.md5(user.getPassword()));
			service.regist(user);
			// 4.回到主页
			response.getWriter().write("注册成功，请到邮箱进行激活登录，三秒后返回主页......");
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

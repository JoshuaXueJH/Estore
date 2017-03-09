package com.joshua.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joshua.domain.User;
import com.joshua.factory.BasicFactory;
import com.joshua.service.UserService;

public class AutologinFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 1.只有未登录的用户才自动登录
		if (req.getSession(false) == null || req.getSession().getAttribute("user") == null) {
			// 2.只有呆了自动登录cookie的用户才自动登录
			Cookie[] cs = req.getCookies();
			Cookie findC = null;
			if (cs != null) {
				for (Cookie c : cs) {
					if ("autologin".equals(c.getName())) {
						findC = c;
						break;
					}
				}
			}
			// 3.只有自动登录cookie中用户名密码都正确的用户才能登录
			if (findC != null) {
				String v = URLDecoder.decode(findC.getValue(), "utf-8");
				String username = v.split(":")[0];
				String password = v.split(":")[1];
				UserService service = BasicFactory.getFactory().getService(UserService.class);
				User user = service.findUserByUsernameAndPsw(username, password);
				if(user!=null){
					req.getSession().setAttribute("user", user);
				}
			}
		}

		// 4.无论是否自动登录，都要放行
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

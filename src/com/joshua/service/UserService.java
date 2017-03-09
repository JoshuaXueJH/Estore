package com.joshua.service;

import com.joshua.annotation.Tran;
import com.joshua.domain.User;

public interface UserService extends Service {

	/**
	 * 注册用户
	 * 
	 * @param user
	 *            封装了user信息
	 */
	@Tran
	void regist(User user);

	/**
	 * 激活用户
	 * 
	 * @param activecode
	 *            用户收到的激活码
	 */
	User active(String activecode);

	/**
	 * 用户登录
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	User findUserByUsernameAndPsw(String username, String password);

	/**
	 * 查看用户名是否存在
	 * @param username
	 * @return
	 */
	boolean hasName(String username);

}

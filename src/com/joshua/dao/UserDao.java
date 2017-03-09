package com.joshua.dao;

import java.sql.SQLException;

import com.joshua.domain.User;

public interface UserDao extends Dao {

	/**
	 * 根据username查找用户
	 * 
	 * @param username
	 *            用户名
	 */
	Object findUserByName(String username);

	/**
	 * 将user信息存入数据库
	 * 
	 * @param user
	 *            封装了user信息
	 */
	void addUser(User user);

	/**
	 * 根据activecode查找用户
	 * 
	 * @param activecode
	 *            用户的activecode
	 * @return 返回找到的user信息
	 */
	User findUserByActiveCode(String activecode);

	/**
	 * 根据id删除用户
	 * 
	 * @param id
	 */
	void deleteById(int id);

	/**
	 * 根据id更新用户的state
	 * 
	 * @param id
	 */
	void updateUserState(int id);

	/**
	 * 根据用户名，密码查找用户
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	User findUserByUsernameAndPsw(String username, String password);

	/**
	 * 根据用户id查找用户
	 * 
	 * @param id
	 *            用户id
	 * @return
	 * @throws SQLException
	 */
	User findUserById(int id);

}

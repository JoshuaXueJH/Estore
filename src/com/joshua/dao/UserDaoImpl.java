package com.joshua.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.joshua.domain.User;
import com.joshua.util.TransactionManager;

public class UserDaoImpl implements UserDao {

	@Override
	public Object findUserByName(String username) {
		String sql = "select * from users where username=?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class), username);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addUser(User user) {
		String sql = "insert into users values(null,?,?,?,?,?,?,?,null)";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql, user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail(),
					user.getRole(), user.getState(), user.getActivecode());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findUserByActiveCode(String activecode) {
		String sql = "select * from users where activecode=?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class), activecode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteById(int id) {
		String sql = "delete from users where id=?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateUserState(int id) {
		String sql = "update users set state = 1 where id=?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findUserByUsernameAndPsw(String username, String password) {
		String sql = "select * from users where username=? and password=?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class), username, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findUserById(int id) {
		String sql = "select * from users where id = ?";
		try {
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class), id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}

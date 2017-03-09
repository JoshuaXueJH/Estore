package com.joshua.service;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import com.joshua.dao.UserDao;
import com.joshua.domain.User;
import com.joshua.factory.BasicFactory;


public class UserServiceImpl implements UserService {
	UserDao dao = BasicFactory.getFactory().getDao(UserDao.class);

	@Override
	public void regist(User user) {
		try {
			// 1.检查用户是否存在
			if (dao.findUserByName(user.getUsername()) != null) {
				throw new RuntimeException("用户名已经存在！");
			}
			// 2.若用户不存在，将用户信息添加到数据库
			user.setRole("user");
			user.setState(0);
			user.setActivecode(UUID.randomUUID().toString());
			dao.addUser(user);
			// 3.发送激活邮件

			Properties prop = new Properties();
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.host", "smtp.126.com");
			prop.setProperty("mail.smtp.auth", "true");
			prop.setProperty("mail.debug", "true");

			Session session = Session.getInstance(prop);

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("xue918715582@126.com"));
//			msg.setRecipients(RecipientType.TO, new InternetAddress[] { new InternetAddress("xue918715582@126.com") });
			msg.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
			msg.setSubject("this is the subject of the mail");
			msg.setText("点击如下连接激活账户,如果不能点击请复制到浏览器地址栏访问:http://localhost:8080/Estore_repeat/ActiveServlet?activecode="
					+ user.getActivecode());

			Transport trans = session.getTransport();
			trans.connect("xue918715582", "xuqiao918715582y");
			trans.sendMessage(msg, msg.getAllRecipients());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public User active(String activecode) {
		User user=dao.findUserByActiveCode(activecode);
		if(user==null){
			throw new RuntimeException("用户激活码不正确");
		}
		if(user.getState()==1){
			throw new RuntimeException("用户已经激活，请不要重复激活");
		}
		if((System.currentTimeMillis()-user.getUpdatetime().getTime())>=24*60*60*1000){
			dao.deleteById(user.getId());
			throw new RuntimeException("激活码超时，此用户作废，请重新注册用户");
		}
		dao.updateUserState(user.getId());
		user.setState(1);
		return user;
	}

	@Override
	public User findUserByUsernameAndPsw(String username, String password) {
		return dao.findUserByUsernameAndPsw(username,password);
	}

	@Override
	public boolean hasName(String username) {
		return dao.findUserByName(username)!=null;
	}

}

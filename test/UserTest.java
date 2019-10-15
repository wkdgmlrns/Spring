package com.spring.test;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserTest {
	
	UserDao dao = new UserDao();
	User user;
	@Test
	public void test1() throws ClassNotFoundException, SQLException 
	{
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		dao = context.getBean("userDao",UserDao.class);
		user = new User();
		user.setId("test2");
		user.setName("test2");
		user.setPassword("test2");
		dao.add(user);
	}
}

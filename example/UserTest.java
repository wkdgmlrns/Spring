package com.spring.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		ApplicationContext context = new GenericXmlApplicationContext("./applicationCTX.xml");
		UserDao dao = context.getBean("userDao",UserDao.class);
		UserBean user = new UserBean();
		user.setId("111");
		user.setName("2222");
		user.setPassword("3333");
		try {
			dao.add(user);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

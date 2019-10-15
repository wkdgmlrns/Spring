package com.spring.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
	
	private ConnectionMaker connection;
	
	@Bean
	public UserDao userDao() 
	{
		UserDao dao = new UserDao();
		dao.setConnection(makeCon());
		return dao;
	}
	
	@Bean
	public ConnectionMaker makeCon() {
		this.connection = new DConnectionMaker();
		return connection;
	}
}

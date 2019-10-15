
package com.spring.example;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class DaoFactory {

	//@Bean
	public UserDao UserDao() {
		UserDao dao = new UserDao();
		dao.setConnectionMaker(FactoryConnection());
		return dao;
	}

	//@Bean
	public ConnectionMaker FactoryConnection() {
		ConnectionMaker connection = new MySqlConnectionMaker();
		return connection;
	}
}

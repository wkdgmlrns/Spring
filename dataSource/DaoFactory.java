package com.spring.dataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import javax.sql.DataSource;

@Configuration
public class DaoFactory {
	
	@Bean
	public UserDao userDao() {
		UserDao dao = new UserDao();
		dao.setDataSource(dataSource());
		return dao;
	}
	
	  @Bean public DataSource dataSource() { SimpleDriverDataSource dataSource =
	  new SimpleDriverDataSource ();
	  
	  dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
	  dataSource.setUrl("jdbc:mysql://localhost:3306/wkdgml?serverTimezone=UTC");
	  dataSource.setUsername("root"); dataSource.setPassword("wkd123");
	  
	  return dataSource; }
	 
}


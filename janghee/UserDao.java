package com.spring.janghee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.janghee.user.User;

public class UserDao {
	
	private ConnectionMaker connectionMaker;

	private Connection c;
	private User user;
	
	public void setConnectionMaker(ConnectionMaker simpleConnectionMaker) {
		this.connectionMaker = simpleConnectionMaker;
	}
	public void add(User user) throws ClassNotFoundException, SQLException {
		this.c =  this.connectionMaker.makeConnection();
		PreparedStatement pstmt = this.c.prepareStatement("insert into user1 values(?,?,?)");
		this.user = user;
		
		pstmt.setString(1, this.user.getId());
		pstmt.setString(2, this.user.getName());
		pstmt.setString(3, this.user.getPassword());
		pstmt.executeUpdate();
		pstmt.close();
		c.close();
	}
	public User get(String id) throws ClassNotFoundException, SQLException {
		this.c =  this.connectionMaker.makeConnection();
		this.user = new User();
		PreparedStatement pstmt = this.c.prepareStatement("select * from user1 where id=?");
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) 
		{
			this.user.setId(rs.getString("id"));
			this.user.setName(rs.getString("name"));
			this.user.setPassword(rs.getString("password"));
		}
		rs.close();
		pstmt.close();
		this.c.close();
		
		return this.user;
	}
}

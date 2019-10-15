package com.spring.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private ConnectionMaker connection;
	private User user;
	
	public void setConnection(ConnectionMaker makeCon) {
		this.connection = makeCon;
	}
	
	public void add(User user) throws SQLException, ClassNotFoundException
	{
		con = connection.makeConnection();
		pstmt = con.prepareStatement("insert into user1 value(?,?,?)");
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getPassword());
		pstmt.executeUpdate();
	}
}

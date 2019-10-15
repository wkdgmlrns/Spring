package com.spring.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	
	private UserBean user;
	private Connection con;
	private PreparedStatement pstmt;
	private ConnectionMaker connection;
	
	public void setConnectionMaker(ConnectionMaker connection) {
		this.connection = connection;
	}
	
	public void add(UserBean user) throws ClassNotFoundException{
		try {
			this.con = connection.makeConnection();
			pstmt = con.prepareStatement("insert into user1 value(?,?,?)");
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPassword());
			pstmt.executeUpdate();
		}catch(SQLException e) {e.printStackTrace();}
		finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

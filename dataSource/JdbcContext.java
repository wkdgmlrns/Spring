package com.spring.dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.janghee.user.User;

public class JdbcContext {
	private DataSource dataSource;
	private User user;
	
	public void setDataSource(DataSource dataSource) 
	{
		this.dataSource = dataSource;
	}
	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) {
		Connection c = null;
		PreparedStatement pstmt = null;
		try {
			c = this.dataSource.getConnection();
			pstmt = stmt.makePreparedStatement(c);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

				}
			}
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public User jdbcContextSelectOne(StatementStrategy stmt) {
		Connection c = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		user = new User();
		try {
			c = this.dataSource.getConnection();
			pstmt = stmt.makePreparedStatement(c);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

				}
			}
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
		}
		return user;
	}
	
	public int jdbcContextGetCount(StatementStrategy stmt) {
		int x=0;
		Connection c = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			c = this.dataSource.getConnection();
			pstmt = stmt.makePreparedStatement(c);
			rs = pstmt.executeQuery();
			if(rs.next()) {x = rs.getInt(1);}
			return x;
		}catch(SQLException e) {e.printStackTrace();}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

				}
			}
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
		}
		return x;
	}
	
	public ArrayList<User> jdbcContexetSelectAll(StatementStrategy stmt){
		Connection c = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> list = new ArrayList<User>();
		try {
			c = this.dataSource.getConnection();
			pstmt = stmt.makePreparedStatement(c);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				list.add(user);
			}
		return list;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

				}
			}
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
		}
		return list;
	}
	public void workWithStatementStrategy(StatementStrategy stmt) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = this.dataSource.getConnection();
			pstmt = stmt.makePreparedStatement(con);
			pstmt.executeUpdate();
		}catch(SQLException e) {e.printStackTrace();}
		finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {}
			}
			if(con != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {}
			}
		}
		
	}
}

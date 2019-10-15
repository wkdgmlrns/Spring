package com.spring.dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import com.janghee.user.User;

public class UserDao {
	private DataSource dataSource;
	private Connection c;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private User user;
	private JdbcContext jdbcContext;

	public void setDataSource(DataSource dataSource) {
		this.jdbcContext = new JdbcContext();
		this.dataSource = dataSource;
	}

	public void add(final User user) throws SQLException {
		this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				PreparedStatement pstmt = c.prepareStatement("insert into user1 values(?,?,?)");
				pstmt.setString(1, user.getId());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getPassword());
				return pstmt;
			}
		});
	}

	public void deleteAll() throws SQLException {
		this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				PreparedStatement pstmt = c.prepareStatement("delete from user1");
				return pstmt;
			}
		});
	}

	public int update(User user) {
		try {
			this.c = dataSource.getConnection();
			pstmt = c.prepareStatement("select id from user1 where id=?");
			pstmt.setString(1, user.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (user.getId().equals(rs.getString(user.getId()))) {
					pstmt = c.prepareStatement("update user1 set name=?,password=? where id=?");
					pstmt.setString(1, user.getName());
					pstmt.setString(2, user.getPassword());
					pstmt.setString(3, user.getId());
					pstmt.executeUpdate();
					return 1;// update 성공
				} else {
					return 0;// 아이디 틀림
				}
			} else {
				return -1;// DB오류
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
		return -2;// db 오류
	}

	public User get(final String id) {		
		user = jdbcContextSelectOne(new StatementStrategy() {
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				PreparedStatement pstmt = c.prepareStatement("select * from user1 where id=?");
				pstmt.setString(1, id);
				return pstmt;
			}
		});
		return user;
	}

	public ArrayList<User> selectAll() {
		
		ArrayList<User> list = jdbcContexetSelectAll(new StatementStrategy() {
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				PreparedStatement pstmt = c.prepareStatement("select * from user1");
				return pstmt;
			}
		});
		return list;
	}
	
	public int getCount() {
		int x = jdbcContextGetCount(new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				PreparedStatement pstmt = c.prepareStatement("select count(*) from user1");
				return pstmt;
			}
		});
		return x;
	}

	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) {
		try {
			this.c = dataSource.getConnection();
			pstmt = stmt.makePreparedStatement(c);
			pstmt.executeUpdate();
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
		user = new User();
		try {
			this.c = dataSource.getConnection();
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
		try {
			pstmt = stmt.makePreparedStatement(dataSource.getConnection());
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
		
		ArrayList<User> list = new ArrayList<User>();
		try {
		pstmt = stmt.makePreparedStatement(dataSource.getConnection());
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
}

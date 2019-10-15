package com.spring.dataSource;

import java.util.ArrayList;
import java.util.Iterator;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.janghee.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserDaoTest {
	@Autowired
	private UserDao dao;
	@Autowired
	private ApplicationContext context;
	private User user;

	@Before
	public void setUp() {
		this.dao = this.context.getBean("userDao", UserDao.class);

		DataSource dataSource = new SingleConnectionDataSource(
				"jdbc:mysql:" + "//localhost:3306/wkdgml?serverTimezone=UTC", "root", "wkd123", true);
		dao.setDataSource(dataSource);

	}
	/*
	 * @Test public void deleteAll() { dao.deleteAll(); }
	 */
	
	@Test
	public void add() {
		user = new User();
		user.setId("test1");
		user.setName("test1");
		user.setPassword("test1");
		dao.add(user);
	}
	@Test
	public void selectOne() {
		user = new User();
		user = dao.get("test1");
		System.out.println(user.getId());
		System.out.println(user.getName());
		System.out.println(user.getPassword());
	}
	@Test
	public void count() {
		int x = dao.getCount();
		System.out.println(x);
	}
	@Test
	public void selecAll() {
		user = new User();
		ArrayList<User> list = new ArrayList<User>();
		list = dao.selectAll();
		Iterator<User> it = list.iterator();
		while(it.hasNext()) {
			user = it.next();
			System.out.println(user.getId());
			System.out.println(user.getName());
			System.out.println(user.getPassword());
		}
	}
	/*
	 * @Test public void deleteAll() { dao.deleteAll(); }
	 */
	/*
	 * @Test public void addAndGet() throws SQLException { // ApplicationContext //
	 * context = new // AnnotationConfigApplicationContext(DaoFactory.class);
	 * dao.deleteAll(); assertThat(dao.getCount(), is(0));
	 * 
	 * User user = new User();
	 * 
	 * user.setId("user"); user.setName("백기선"); user.setPassword("married");
	 * 
	 * dao.add(user); assertThat(dao.getCount(), is(1));
	 * 
	 * System.out.println(user.getId() + "등록");
	 * 
	 * User user2 = dao.get(user.getId()); assertThat(user2.getName(),
	 * is(user.getName())); assertThat(user2.getPassword(), is(user.getPassword()));
	 * }
	 */

	/*
	 * @Test public void CountTest() throws SQLException { // ApplicationContext
	 * context = new // AnnotationConfigApplicationContext(DaoFactory.class); User
	 * user1 = new User("testId1", "testName1", "testPass1"); User user2 = new
	 * User("testId2", "testName2", "testPass2"); User user3 = new User("testId3",
	 * "testName3", "testPass3"); User user4 = new User("testId4", "testName4",
	 * "testPass4");
	 * 
	 * dao.deleteAll(); assertThat(dao.getCount(), is(0));
	 * 
	 * dao.add(user1); assertThat(dao.getCount(), is(1));
	 * 
	 * dao.add(user2); assertThat(dao.getCount(), is(2));
	 * 
	 * dao.add(user3); assertThat(dao.getCount(), is(3));
	 * 
	 * dao.add(user4); assertThat(dao.getCount(), is(4)); }
	 */
	/*
	 * @Test(expected = EmptyResultDataAccessException.class) public void
	 * getUserFailure() throws SQLException{
	 * 
	 * ApplicationContext context = new
	 * GenericXmlApplicationContext("applicationContext.xml"); UserDao dao =
	 * context.getBean("userDao",UserDao.class); dao.deleteAll();
	 * assertThat(dao.getCount(), is(0));
	 * 
	 * dao.get("un_id");
	 * 
	 * }
	 */
}

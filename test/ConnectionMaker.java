package com.spring.test;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
	Connection makeConnection() throws SQLException, ClassNotFoundException;
}

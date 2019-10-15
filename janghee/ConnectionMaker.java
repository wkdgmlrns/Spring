package com.spring.janghee;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
	public abstract Connection makeConnection() throws ClassNotFoundException, SQLException;
}

package com.kh.jdbc.day05.member.common;

import java.sql.*;

public class JDBCTemplate_Old {
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USERNAME = "STUDENT";
	private final String PASSWORD = "STUDENT";
	
	private static JDBCTemplate_Old instance;
	private static Connection conn;
	private JDBCTemplate_Old() {}
	public static JDBCTemplate_Old getInstance() {
		if(instance == null) {
			instance = new JDBCTemplate_Old();
		}
		return instance;
	}
	public Connection getConnection() throws Exception {
		if(conn == null || conn.isClosed()) {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false);
		}
		return conn;
	}
}

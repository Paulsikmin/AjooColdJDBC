package com.kh.jdbc.day05.member.common;

import java.sql.*;

public class JDBCTemplate {
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USERNAME = "STUDENT";
	private final String PASSWORD = "STUDENT";
	
	private static JDBCTemplate instance;
	private static Connection conn;
	private JDBCTemplate() {}
	public static JDBCTemplate getInstance() {
		if(instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}
	public Connection getConnection() throws Exception {
		if(conn == null || conn.isClosed()) {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		return conn;
	}
}

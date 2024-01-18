package com.kh.jdbc.day05.member.common;

import java.io.FileReader;
import java.io.Reader;
import java.sql.*;
import java.util.*;

public class JDBCTemplate {
	
	private Properties prop;
	
//	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
//	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	private final String USERNAME = "STUDENT";
//	private final String PASSWORD = "STUDENT";
	
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
		prop = new Properties();
		Reader reader = new FileReader("resources/dev.properties");
		prop.load(reader);
		String driverName = prop.getProperty("driverName");
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		if(conn == null || conn.isClosed()) {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
		}
		return conn;
	}
}


















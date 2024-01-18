package com.kh.jdbc.day04.pstmt.member.common;

import java.sql.Connection;
import java.sql.DriverManager;

class Singletone {
	private static Singletone instance;
	
	private Singletone() {}
	
	public static Singletone getInstance() {
		if(instance == null) {
			instance = new Singletone();
		}
		return instance;
	}
}

public class JDBCTemplate {
	// 연결작업을 시간이 걸리는 작업임
	// 자주하면 좋지 않아요.
	// 한번 작업하고 만들어 놓은 것 사용하기 위해서 디자인 패턴 적용
	// 디자인 패턴이란?
	// 각기 다른 소프트웨어 모듈이나 기능을 가진 응용 SW를
	// 개발할 때 공통되는 설계 문제를 해결하기 위하여 사용되는 패턴임
	// 1. 생성패턴 : 싱글톤 패턴, 추상팩토리, 팩토리 메서드
	// 2. 구조패턴 : 컴포지트, 데코레이트, ...
	// 3. 행위패턴 : 옵저버, 스테이트, 전략, 템플릿 메서드, ...
	
	// => 한번 작업하고 만들어 놓은 것을 사용하기 위해 싱글톤 패턴 적용
	
	private static JDBCTemplate instance;
	private static Connection conn;
	
	private JDBCTemplate() {} // 아무도 객체 생성을 못하도록 함
	
	public static JDBCTemplate getInstance() {
		if(instance == null) {	// 객체가 만들어져 있는지 체크 후
			instance = new JDBCTemplate(); // 없으면 만들어줌
		}
		return instance; // 만들어 놓은 것을 사용할 수 있도록 리턴해줌
	}
	
	final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final String USERNAME = "STUDENT";
	final String PASSWORD = "STUDENT";
	
	// DBMS 연결을 한번 만들어 놓고 저장해서 쓰는 개념
	// DBCP(DataBase Connection Pool)
	public Connection getConnection() throws Exception {
		if(conn == null || conn.isClosed()) {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		return conn;
	}
}



















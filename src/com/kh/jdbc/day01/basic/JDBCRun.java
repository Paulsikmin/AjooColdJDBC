package com.kh.jdbc.day01.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRun {

	public static void main(String[] args) {
		/*
		 * JDBC 코딩 절차
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성
		 * 3. Statement 객체 생성(쿼리문 실행 준비)
		 * 4. SQL 전송(쿼리문 실행)
		 * 5. 결과 받기(ResultSet으로 받음)
		 * 6. 자원해제(close())
		 */
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "KH";
		String password = "KH";
		String query = "SELECT * FROM DEPARTMENT";
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DBMS 연결 생성 (sqldeveloper 접속 버튼 누름)
			Connection conn = DriverManager
								.getConnection(url, username, password);
			// 3. Statement 객체 생성(쿼리문 실행 준비)
			Statement stmt = conn.createStatement();
			// 4. SQL 전송(명령문 실행, 초록색 재생 버튼 누름)
			// 5. 결과받기(ResultSet)
			ResultSet rset = stmt.executeQuery(query);
			// 배열에 있는 값을 꺼내 쓸 때 함께 쓰는 것은? 한글 3글자 반복문
			// 후처리 필요 (ResultSet에서 꺼내 써야함)
			while(rset.next()) {
//				System.out.println("직원명 : " + rset.getString("EMP_NAME"));
				System.out.print("부서코드 : " + rset.getString("DEPT_ID"));
				System.out.println(", 부서명 : " + rset.getString("DEPT_TITLE"));
			}
			// 6. 자원해제
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}













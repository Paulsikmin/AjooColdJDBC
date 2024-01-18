package com.kh.jdbc.day02.stmt.member.model.dao;

import java.sql.*;
import java.util.*;

import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberDAO {
	final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final String USERNAME = "STUDENT";
	final String PASSWORD = "STUDENT";
	
	// JDBC를 이용하여
	// Oracle DB에 접속하는 클래스
	// JDBC 코딩 절차
	
	public void insertMember(Member member) {
			String query = "INSERT INTO MEMBER_TBL "
					+ "VALUES('"+member.getMemberId()+"', '"
								+member.getMemberPw()+"', '"
								+member.getMemberName()+"', '"
								+member.getGender()+"', "
								+member.getAge()+", '"
								+member.getEmail()+"', '"
								+member.getPhone()+"', '"
								+member.getAddress()+"', '"
								+member.getHobby()+"', sysdate)";
			try {
				Class.forName(DRIVER_NAME);
				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement stmt = conn.createStatement();
	//			ResultSet rset = stmt.executeQuery(query);
				int result = stmt.executeUpdate(query); // DML의 경우 호출하는 메소드
				if(result > 0) {	// autocommit이 되기 때문에 안해도 됨
					// insert 성공
				}else {
					// insert 실패
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}













	/*
	 * 1. 드라이버 등록
	 * 2. DB 연결 생성
	 * 3. 쿼리문 실행 준비
	 * 4. 쿼리문 실행 및 5. 결과 받기
	 * 6. 자원해제(close())
	 */
	public void updateMember(Member member) {
		String query = "UPDATE MEMBER_TBL SET MEMBER_PWD = '"+member.getMemberPw()+"'"
				+ ", EMAIL = '"+member.getEmail()
				+"', PHONE = '"+member.getPhone()
				+"', ADDRESS = '"+member.getAddress()
				+"', HOBBY = '"+member.getHobby()
				+"' WHERE MEMBER_ID = '"+member.getMemberId()+"'";
		
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate(query);
			if(result > 0) {
				// success
			}else {
				// fail
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void deleteMember(String memberId) {
		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate(query);	// DML의 경우  int로 받음
			if(result > 0) {
				// 성공 후 커밋
			}else {
				// 실패면 롤백
			}
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	// 클래스 바로 밑에 코드 못씀
	// 메소드로 감싸줘야 함
	// 메소드 안에 코드를 씀
	// 내가 필요할 때 호출해서 씀
	public List<Member> selectAll() {
		
		String query = "SELECT * FROM MEMBER_TBL";
		List<Member> mList = null;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query); // 초록색 재생버튼 누름
			// rset 전부다 담겨있기 때문에 한 행씩 꺼내서 출력해줘야 함
			mList = new ArrayList<Member>(); // 누락 주의! 꼭 쓰세욤!
			while(rset.next()) {
				Member member = new Member();
				String memberId = rset.getString("MEMBER_ID");
				String memberPwd = rset.getString("MEMBER_PWD");
				String memberName = rset.getString("MEMBER_NAME");
				member.setMemberId(memberId);
				member.setMemberPw(memberPwd);
				member.setMemberName(memberName);
				member.setGender(rset.getString("GENDER").charAt(0)); // gender는 한글자라서 문자 처리
				member.setAge(rset.getInt("AGE")); // getInt 사용
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setHobby(rset.getString("HOBBY"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE")); // getDate 사용
				mList.add(member);		// 누락주의! 하나의 행 데이터를 List에 반복적으로 저장
				// 후처리 : SELECT한 결과값 자바영역인 List에다가 옮기는 작업
			}
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
		return mList;
	}

	public Member selectOneById(String memberId) {
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'";
		Member member = null;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			if(rset.next()) {
				member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPw(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setGender(rset.getString("GENDER").charAt(0)); // gender는 한글자라서 문자 처리
				member.setAge(rset.getInt("AGE")); // getInt 사용
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setHobby(rset.getString("HOBBY"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE")); // getDate 사용
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	
	public Member selectLoginInfo(Member mOne) {
//		String query = "SELECT * FROM STUDENT_TBL WHERE STUDENT_ID = ? AND STUDENT_PWD = ?";
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+mOne.getMemberId()+"' AND MEMBER_PWD = '"+mOne.getMemberPw()+"'";
		Member member = null;
		Connection conn = null;
		Statement stmt = null;
//		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, student.getStudentId());
//			pstmt.setString(2, student.getStudentPwd()); // 시작은 1로 하고 마지막 수는 물음표의 갯수와 같다.(물음표 = 위치홀더)
//			rset = pstmt.executeQuery();
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPw(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setGender(rset.getString("GENDER").charAt(0)); // gender는 한글자라서 문자 처리
				member.setAge(rset.getInt("AGE")); // getInt 사용
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setHobby(rset.getString("HOBBY"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE")); // getDate 사용
			}
			rset.close();
			stmt.close();
//			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
//				rset.close();
//				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
//		return result;
		return member;
	}
}

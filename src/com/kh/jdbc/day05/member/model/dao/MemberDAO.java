package com.kh.jdbc.day05.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day05.member.common.JDBCTemplate;
import com.kh.jdbc.day05.member.model.vo.Member;

public class MemberDAO {
	
	private JDBCTemplate jdbcTemplate;
	
	public MemberDAO() {
		jdbcTemplate = JDBCTemplate.getInstance();
	}

	public int insertMember(Connection conn, Member member) {
		String query = "INSERT INTO MEMBER_TBL VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
//		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		try {
//			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
//			pstmt.setString(4, member.getGender()+"");
			pstmt.setString(4, String.valueOf(member.getGender()));
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getHobby());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateMember(Connection conn, Member member) {
		String query = "UPDATE MEMBER_TBL SET MEMBER_PWD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ?, HOBBY = ? WHERE MEMBER_ID = ?";
//		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		
		try {
//			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getHobby());
			pstmt.setString(6, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";
//		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		
		try {
//			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<Member> selectAllMembers(Connection conn) {
		String query = "SELECT * FROM MEMBER_TBL";
//		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<Member> mList = null;
		try {
//			conn = jdbcTemplate.getConnection();
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			mList = new ArrayList<Member>();
			while(rset.next()) {
				Member member = this.rsetToMember(rset);
				mList.add(member);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mList;	// 누락주의!!!!
	}

	private Member rsetToMember(ResultSet rset) throws SQLException {
		// TODO Auto-generated method stub
		Member member = new Member();
		member.setMemberId(rset.getString("MEMBER_ID"));
		member.setMemberPw(rset.getString("MEMBER_PWD"));
		member.setMemberName(rset.getString("MEMBER_NAME"));
		member.setGender(rset.getString("GENDER").charAt(0));
		member.setAge(rset.getInt("AGE"));
		member.setEmail(rset.getString("EMAIL"));
		member.setPhone(rset.getString("PHONE"));
		member.setAddress(rset.getString("ADDRESS"));
		member.setHobby(rset.getString("HOBBY"));
		member.setEnrollDate(rset.getDate("ENROLL_DATE"));
		return member;
	}

}


















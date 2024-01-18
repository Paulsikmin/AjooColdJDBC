package com.kh.jdbc.day05.member.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.jdbc.day05.member.common.JDBCTemplate;
import com.kh.jdbc.day05.member.model.dao.MemberDAO;
import com.kh.jdbc.day05.member.model.vo.Member;

// 1. 연결을 생성하여 DAO에 전달
// 2. 성공여부에 따라 commit/rollback
public class MemberService {
	private JDBCTemplate jdbcTemplate;
	private MemberDAO mDao;
	
	public MemberService() {
		mDao = new MemberDAO();
		jdbcTemplate = JDBCTemplate.getInstance();
	}
	
	public List<Member> selectAllMembers() {
		List<Member> mList = null; 
		try {
			Connection conn = jdbcTemplate.getConnection();
			mList = mDao.selectAllMembers(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mList;
	}

	public int insertMember(Member member) {
		int result = 0;
		try {
			Connection conn = jdbcTemplate.getConnection();
			result = mDao.insertMember(conn, member);
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result; // 누락주의!!!
	}

	public int updateMember(Member member) {
		int result = 0;
		try {
			Connection conn = jdbcTemplate.getConnection();
			result = mDao.updateMember(conn, member);
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result; // 누락주의!!!
	}

	public int deleteMember(String memberId) {
		int result = 0;
		try {
			Connection conn = jdbcTemplate.getConnection();
			result = mDao.deleteMember(conn, memberId);
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result; // 누락주의!!!
	}
}

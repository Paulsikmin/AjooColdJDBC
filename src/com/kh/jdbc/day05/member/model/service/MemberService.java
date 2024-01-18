package com.kh.jdbc.day05.member.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.jdbc.day05.member.common.JDBCTemplate;
import com.kh.jdbc.day05.member.model.dao.MemberDAO;
import com.kh.jdbc.day05.member.model.vo.Member;

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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result; // 누락주의!!!
	}
}

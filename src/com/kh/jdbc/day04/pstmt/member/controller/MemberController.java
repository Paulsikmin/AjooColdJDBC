package com.kh.jdbc.day04.pstmt.member.controller;

import java.util.List;

import com.kh.jdbc.day04.pstmt.member.model.dao.MemberDAO;
import com.kh.jdbc.day04.pstmt.member.model.vo.Member;

public class MemberController {
//	List<Member> memberList;
	MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}
	
	public Member memberLogin(Member member) {
		Member mOne = mDao.selectLoginInfo(member);
		return mOne;
	}
	
//	mDao.selectAll();	// 이렇게하면 안됨!!
	public List<Member> printAll() {
		List<Member> mList = mDao.selectAll();
		return mList;
	}

	public Member printOneById(String memberId) {
		Member member = mDao.selectOneById(memberId);
		return member;
	}
	
	public void registerMember(Member member) {
		mDao.insertMember(member);
	}
	
	public void modifyMember(Member member) {
		mDao.updateMember(member);
	}

	public void removeMember(String memberId) {
		mDao.deleteMember(memberId);
	}
}


































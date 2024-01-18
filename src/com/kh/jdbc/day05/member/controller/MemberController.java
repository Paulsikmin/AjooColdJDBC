package com.kh.jdbc.day05.member.controller;

import java.util.List;

import com.kh.jdbc.day05.member.model.dao.MemberDAO;
import com.kh.jdbc.day05.member.model.service.MemberService;
import com.kh.jdbc.day05.member.model.vo.Member;

public class MemberController {

	private MemberService mService;
//	private MemberDAO mDao;
	
	public MemberController() {
		mService = new MemberService();
//		mDao = new MemberDAO();
	}
	// 회원 정보 전체 출력
	public List<Member> printAllMembers() {
//		List<Member> mList = mDao.selectAllMembers();
		List<Member> mList = mService.selectAllMembers();
		return mList;
	}
	// 회원 정보 등록
	public int registerMember(Member member) {
		int result = mService.insertMember(member);
		return result;
	}
	// 회원 정보 수정
	public int modifyMember(Member member) {
		int result = mService.updateMember(member);
		return result;
	}
	// 회원 정보 삭제
	public int removeMember(String memberId) {
		int result = mService.deleteMember(memberId);
		return result;
	}

}



















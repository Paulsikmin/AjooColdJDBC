package com.kh.jdbc.day05.member.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day05.member.controller.MemberController;
import com.kh.jdbc.day05.member.model.vo.Member;

public class MemberView {
	
	private MemberController mController;
	public MemberView() {
		mController = new MemberController();
	}
	
	public void startProgram() {
		List<Member> mList = null;
		Member member = null;
		int result = 0;
		String memberId = "";
		end :
		while(true) {
			int choice = this.mainMenu();
			
			switch(choice) {
			case 0 : 
//				member = this.inputLoginInfo();
//				result = mController.checkLoginInfo(member);
				break;
			case 1 : 
				// SELECT * FROM MEMBER_TBL
				mList = mController.printAllMembers();
				this.displayAllMembers(mList);
				break;
			case 2 : 
				member = this.inputMember();
				result = mController.registerMember(member);
				if(result > 0) {
					this.displayMessage("등록 성공!!");
				}else {
					this.displayMessage("등록 실패~");
				}
				break;
			case 3 :
				memberId = this.inputMemberId(); // 입력받아서
				member = this.modifyMember();
				member.setMemberId(memberId);    // member에 넣어줌
				result = mController.modifyMember(member);
				if(result > 0) {
					this.displayMessage("수정 성공!!");
				}else {
					this.displayMessage("수정 실패~");
				}
				break;
			case 4 : 
				memberId = this.inputMemberId();
				result = mController.removeMember(memberId);
				if(result > 0) {
					this.displayMessage("삭제 성공!!");
				}else {
					this.displayMessage("삭제 실패~");
				}
				break;
			case 9 : 
				this.displayMessage("프로그램이 종료되었습니다.");
				break end;
			}
		}
	}
	private Member modifyMember() {
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === 회원 정보 수정 === ===");
		System.out.print("비밀번호 입력 : ");
		String memberPw = sc.next();
		System.out.print("이메일 입력 : ");
		String email = sc.next();
		System.out.print("전화번호 입력 : ");
		String phone = sc.next();
		sc.nextLine();	// 개행 제거, 씹힘방지
		System.out.print("주소 입력 : ");
		String address = sc.nextLine();
		System.out.print("취미 입력 : ");
		String hobby = sc.next();
		Member mOne = new Member(memberPw, email, phone, address, hobby);
		return mOne;
	}

	private String inputMemberId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 입력 : ");
		String memberId = sc.next();
		return memberId;
	}

	private Member inputMember() {
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === 회원 정보 입력 === ===");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별(M, F) : ");
		char gender = sc.next().charAt(0);
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호(-없이) : ");
		String phone = sc.next();
		sc.nextLine();
		System.out.print("주소 : ");
		String addresss = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.next();
		Member member = new Member(memberId, memberPw, memberName, gender, age, email, phone, addresss, hobby);
		return member;
	}

	private void displayAllMembers(List<Member> mList) {
		System.out.println("======== 회원 정보 전체 출력 ========");
//		for(int i = 0; i < mList.size(); i++) {}
		for(Member member : mList) {
			System.out.printf("이름 : %s, 나이 : %d"
					+ ", 아이디 : %s, 성별 : %c, 이메일 : %s"
					+ ", 전화번호 : %s, 주소 : %s, 취미 : %s"
					+ ", 가입날짜 : %s\n"
					, member.getMemberName()
					, member.getAge()
					, member.getMemberId()
					, member.getGender()
					, member.getEmail()
					, member.getPhone()
					, member.getAddress()
					, member.getHobby()
					, member.getEnrollDate());
		}		
	}

	private int mainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === 회원 관리 프로그램(5일차) === ===");
		System.out.println("0. 회원 로그인");
		System.out.println("1. 회원 정보 전체 출력");
		System.out.println("2. 회원 정보 등록");
		System.out.println("3. 회원 정보 수정");
		System.out.println("4. 회원 정보 삭제");
		System.out.println("9. 프로그램 종료");
		System.out.print("메뉴 선택 >> ");
		int choice = sc.nextInt();
		return choice;
	}

	private void displayMessage(String msg) {
		System.out.println(msg);
		
	}

}


















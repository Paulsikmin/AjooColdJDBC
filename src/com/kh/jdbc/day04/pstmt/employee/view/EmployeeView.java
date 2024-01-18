package com.kh.jdbc.day04.pstmt.employee.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day04.pstmt.employee.controller.EmployeeController;
import com.kh.jdbc.day04.pstmt.employee.model.vo.Employee;

public class EmployeeView {
	private EmployeeController eController;
	
	public EmployeeView() {
		eController = new EmployeeController();
	}
	
	public void startProgram() {
		Employee emp = null;
		int result = -1;
		end : 
		while(true) {
			int choice = this.printMenu();
			switch(choice) {
			case 1 : 
				List<Employee> eList = eController.printAllEmployees();
				this.displayAllEmployees(eList);
				break;
			case 2 : 
				emp = this.inputEmployee();
				eController.registerEmployee(emp);
				break;
			case 3 : 
				emp = this.modifyEmployee();
				// result의 결과값이 성공하면 0보다 큼, 실패하면 0임
				// result 변수는 위쪽에 선언하고 재할당하여 재사용함
				result = eController.modifyEmployee(emp);
				if(result > 0) {
					this.displayMessage("수정 성공");
				}else {
					this.displayMessage("수정 실패");
				}
				break;
			case 4 : 
				String empId = this.inputEmpId();
				result = eController.removeEmployee(empId);
				if(result > 0) {
					this.displayMessage("삭제 성공");
				}else {
					this.displayMessage("삭제 실패");
				}
				break;
			case 0 : 
				this.displayMessage("프로그램을 종료합니다.");
				break end;
			}
		}
	}
	

	private Employee modifyEmployee() {
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === 사원 정보 수정 === ===");
		System.out.print("사번 : ");
		String empId = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		Employee employee = new Employee(empId, email, phone);
		return employee;
	}

	private String inputEmpId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("사원번호를 입력해주세요 : ");
		return sc.next();
	}

	private int printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === 사원 관리 프로그램 === ===");
		System.out.println("1. 사원 전체 정보 출력");
		System.out.println("2. 사원 정보 등록");
		System.out.println("3. 사원 정보 수정");
		System.out.println("4. 사원 정보 삭제");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 >> ");
		int input = sc.nextInt();
		return input;
	}
	
	private void displayMessage(String msg) {
		System.out.println(msg);
	}

	private Employee inputEmployee() {
		// View에서 입력받고 Controller 넘겨 준 후 DAO에서 저장하도록 하기위한 Start!!
		// -> 입력받은 값으로 저장하기 위함.
		Scanner sc = new Scanner(System.in);
		System.out.println("======== 사원 정보 입력 ========");
		System.out.print("사번 : ");
		String empId = sc.next();
		System.out.print("사원명 : ");
		String empName = sc.next();
		System.out.print("주민번호 : ");
		String empNo = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("부서코드 : ");
		String deptCode = sc.next();
		System.out.print("직급코드 : ");
		String jobCode = sc.next();
		System.out.print("급여등급 : ");
		String salLevel = sc.next();
		System.out.print("급여 : ");
		int salary = sc.nextInt();
		System.out.print("보너스 : ");
		double bonus = sc.nextDouble();
		System.out.print("매니저 사번 : ");
		String managerId = sc.next();
//		Employee employee = new Employee(); // 텅빈 객체
		Employee employee = new Employee(empId, empName, empNo, email, phone, deptCode, jobCode, salLevel, salary, bonus, managerId); // 채워진 객체
		return employee;
	}

	private void displayAllEmployees(List<Employee> eList) {
		// TODO Auto-generated method stub
		System.out.println("======================================= 사원 정보 전체 출력 ==========================================");
		for(Employee emp : eList) {
			System.out.println("직원명 : " + emp.getEmpName() + ", 사번 : " + emp.getEmpId()
							+ ", 이메일 : " + emp.getEmail() + ", 전화번호 : " + emp.getPhone()
							+ ", 입사일 : " + emp.getHireDate());
		}
	}
}

package com.kh.jdbc.day04.pstmt.employee.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day04.pstmt.employee.common.JDBCTemplate;
import com.kh.jdbc.day04.pstmt.employee.model.vo.Employee;

public class EmployeeDAO {
	
	private JDBCTemplate jdbcTemplate;
	
	public EmployeeDAO() {
		jdbcTemplate = JDBCTemplate.getInstance();
	}
	
	/*
	 * 1. 드라이버 등록
	 * 2. DBMS 연결 생성
	 * 3. Statement 생성
	 * 4. 쿼리문 실행 및 5. 결과받기
	 * 6. 자원해제
	 * 
	 */
	public int insertEmployee(Employee emp) {
//		String query = "INSERT INTO EMPLOYEE VALUES('"
//						+emp.getEmpId()+"', '"
//						+emp.getEmpName()+"', '"
//						+emp.getEmpNo()+"', '"
//						+emp.getEmail()+"', '"
//						+emp.getPhone()+"', '"
//						+emp.getDeptCode()+"', '"
//						+emp.getJobCode()+"', '"
//						+emp.getSalLevel()+"', "
//						+emp.getSalary()+", "
//						+emp.getBonus()+", '"
//						+emp.getManagerId()+"', SYSDATE, null, 'N')";
		String query = "INSERT INTO EMPLOYEE VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, null, 'N')";
		int result = -1;
		try {
			Connection conn = jdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getEmpId());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpNo());
			pstmt.setString(4, emp.getEmail());
			pstmt.setString(5, emp.getPhone());
			pstmt.setString(6, emp.getDeptCode());
			pstmt.setString(7, emp.getJobCode());
			pstmt.setString(8, emp.getSalLevel());
			pstmt.setInt(9, emp.getSalary());	// getSalary() 리턴형이 int이므로 setInt를 써줘야함!!
			pstmt.setDouble(10, emp.getBonus()); // getBonus() 리턴형이 double이므로 setDouble을 써줘야함!!
			pstmt.setString(11, emp.getManagerId());
			result = pstmt.executeUpdate();
//			Statement stmt = conn.createStatement();
//			int result = stmt.executeUpdate(query);
			if(result > 0) {
				// 성공 - commit
			}else {
				// 실패 - rollback
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int updateEmployee(Employee emp) {
//		String query = "UPDATE EMPLOYEE SET EMAIL = '"
//						+emp.getEmail()+"', PHONE = '"
//						+emp.getPhone()+"' WHERE EMP_ID = '"+emp.getEmpId()+"'";
		String query = "UPDATE EMPLOYEE SET EMAIL = ?, PHONE = ? WHERE EMP_ID = ?";
		int result = -1;
		Connection conn = null;
		try {
			conn = jdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getEmail());
			pstmt.setString(2, emp.getPhone());
			pstmt.setString(3, emp.getEmpId());
			result = pstmt.executeUpdate();
//			Statement stmt = conn.createStatement();
//			result = stmt.executeUpdate(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result; // 누락주의!!!!
	}

	public int deleteEmployee(String empId) {
//		String query = "DELETE FROM EMPLOYEE WHERE EMP_ID = '"+empId+"'";
		String query = "DELETE FROM EMPLOYEE WHERE EMP_ID = ?";
		int result = -1;
		try {
			Connection conn = jdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			result = pstmt.executeUpdate();
//			Statement stmt = conn.createStatement();
//			result = stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Employee> selectAllEmployees() {
		String query = "SELECT * FROM EMPLOYEE";
		List<Employee> eList = null;
		try {
			Connection conn = jdbcTemplate.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			eList = new ArrayList<Employee>();
			// 후처리
			while(rset.next()) {
				Employee employee = this.rsetToEmployee(rset);
				eList.add(employee);
//				System.out.println("직원명 : " + rset.getString("EMP_NAME"));
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eList;
	}
	
	private Employee rsetToEmployee(ResultSet rset) throws Exception {
		Employee employee = new Employee();
		String empId = rset.getString("EMP_ID");
		employee.setEmpId(empId);
		employee.setEmpName(rset.getString("EMP_NAME"));
		employee.setEmpNo(rset.getString("EMP_NO"));
		employee.setEmail(rset.getString("EMAIL"));
		employee.setPhone(rset.getString("PHONE"));
		employee.setDeptCode(rset.getString("DEPT_CODE"));
		employee.setJobCode(rset.getString("JOB_CODE"));
		employee.setSalLevel(rset.getString("SAL_LEVEL"));
		employee.setSalary(rset.getInt("SALARY")); // setter 메소드가 int를 매개변수로 받기 때문 getInt() 사용
		employee.setBonus(rset.getDouble("BONUS")); // setter 메소드가 double을 매개변수로 받기 때문 getDouble() 사용
		employee.setManagerId(rset.getString("MANAGER_ID"));
		employee.setHireDate(rset.getDate("HIRE_DATE"));
		employee.setEntDate(rset.getDate("ENT_DATE"));// setter 메소드가 date을 매개변수로 받기 때문 getDate() 사용
		employee.setEntYn(rset.getString("ENT_YN"));
		return employee;
	}

}








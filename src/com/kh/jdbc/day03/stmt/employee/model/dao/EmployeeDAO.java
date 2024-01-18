package com.kh.jdbc.day03.stmt.employee.model.dao;

import java.sql.*;
import java.util.*;

import com.kh.jdbc.day03.stmt.employee.model.vo.Employee;

public class EmployeeDAO {
	final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final String USERNAME = "STUDENT";
	final String PASSWORD = "STUDENT";
	
	/*
	 * 1. 드라이버 등록
	 * 2. DBMS 연결 생성
	 * 3. Statement 생성
	 * 4. 쿼리문 실행 및 5. 결과받기
	 * 6. 자원해제
	 * 
	 */

	public void insertEmployee(Employee emp) {
		String query = "INSERT INTO EMPLOYEE VALUES('"
						+emp.getEmpId()+"', '"
						+emp.getEmpName()+"', '"
						+emp.getEmpNo()+"', '"
						+emp.getEmail()+"', '"
						+emp.getPhone()+"', '"
						+emp.getDeptCode()+"', '"
						+emp.getJobCode()+"', '"
						+emp.getSalLevel()+"', "
						+emp.getSalary()+", "
						+emp.getBonus()+", '"
						+emp.getManagerId()+"', SYSDATE, null, 'N')";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate(query);
			if(result > 0) {
				// 성공 - commit
			}else {
				// 실패 - rollback
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Employee> selectAllEmployees() {
		String query = "SELECT * FROM EMPLOYEE";
		List<Employee> eList = null;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			eList = new ArrayList<Employee>();
			// 후처리
			while(rset.next()) {
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
				eList.add(employee);
//				System.out.println("직원명 : " + rset.getString("EMP_NAME"));
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
		return eList;
	}


	public int deleteEmployee(String empId) {
		String query = "DELETE FROM EMPLOYEE WHERE EMP_ID = '"+empId+"'";
		int result = -1;
		try {
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateEmployee(Employee emp) {
		String query = "UPDATE EMPLOYEE SET EMAIL = '"
						+emp.getEmail()+"', PHONE = '"
						+emp.getPhone()+"' WHERE EMP_ID = '"+emp.getEmpId()+"'";
		int result = -1;
		Connection conn;
		try {
			conn = this.getConnection();
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result; // 누락주의!!!!
	}


	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName(DRIVER_NAME);
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return conn;
	}
}








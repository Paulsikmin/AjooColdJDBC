package com.kh.jdbc.day03.stmt.employee.controller;

import java.util.List;

import com.kh.jdbc.day03.stmt.employee.model.dao.EmployeeDAO;
import com.kh.jdbc.day03.stmt.employee.model.vo.Employee;

public class EmployeeController {
	private EmployeeDAO eDao;
	
	public EmployeeController() {
		eDao = new EmployeeDAO();
	}
	
	public List<Employee> printAllEmployees() {
		List<Employee> eList = eDao.selectAllEmployees();
		return eList;
	}

	public void registerEmployee(Employee emp) {
		eDao.insertEmployee(emp);
	}

	public int modifyEmployee(Employee emp) {
		int result = eDao.updateEmployee(emp);
		return result;
	}

	public int removeEmployee(String empId) {
		int result = eDao.deleteEmployee(empId);
		return result;
	}
}

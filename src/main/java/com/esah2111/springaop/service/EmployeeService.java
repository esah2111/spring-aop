package com.esah2111.springaop.service;

import org.springframework.stereotype.Service;

import com.esah2111.springaop.model.Employee;

@Service
public class EmployeeService {

	public Employee createEmployee(String name, String empId) {

		Employee emp = new Employee();
		emp.setName(name);
		emp.setEmpId(empId);
		return emp;
	}

	public Boolean deleteEmployee(String empId)  {
		
		throw new RuntimeException();
	}
}

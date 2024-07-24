package com.imaginnovative.employee_api.service;

import org.springframework.stereotype.Service;

import com.imaginnovative.employee_api.model.EmpBean;
import com.imaginnovative.employee_api.model.Employee;

@Service
public interface EmployeeService {

	public Employee saveEmployee(Employee e);
	
	public EmpBean calculateTax(long ID);
	
}

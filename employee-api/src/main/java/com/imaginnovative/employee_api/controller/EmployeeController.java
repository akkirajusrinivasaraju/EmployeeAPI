package com.imaginnovative.employee_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovative.employee_api.model.EmpBean;
import com.imaginnovative.employee_api.model.Employee;
import com.imaginnovative.employee_api.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@Validated @RequestBody Employee emp){
		
		Employee savedEmployee = employeeService.saveEmployee(emp);
		
		return ResponseEntity.ok(savedEmployee);
	}
	@GetMapping("/{id}/tax")
	public ResponseEntity<EmpBean> getEmployeeTax(@PathVariable Long id){
		
		EmpBean empbean = employeeService.calculateTax(id);	
		return ResponseEntity.ok(empbean);
		
	}
	
	
}

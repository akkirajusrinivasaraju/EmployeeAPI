package com.imaginnovative.employee_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imaginnovative.employee_api.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM Employee where ID = :id")
	public Employee getEmployeeById(@Param("id") long id);
	
	
}

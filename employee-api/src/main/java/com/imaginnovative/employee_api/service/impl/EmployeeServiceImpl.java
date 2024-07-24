package com.imaginnovative.employee_api.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.imaginnovative.employee_api.model.EmpBean;
import com.imaginnovative.employee_api.model.Employee;
import com.imaginnovative.employee_api.repository.EmployeeRepository;
import com.imaginnovative.employee_api.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository emprepository;
	
	@Override
	public Employee saveEmployee(Employee e) {
		return emprepository.save(e);
	}

	@Override
	public EmpBean calculateTax(long id) {
		
		EmpBean resposebean = new EmpBean();
		Employee e = emprepository.getEmployeeById(id);
		resposebean.setCode(e.getId());
		resposebean.setFirstName(e.getFirstName());
		resposebean.setLastName(e.getLastName());
		
		LocalDate currentDate = LocalDate.now();
		
		LocalDate startfinancialYear = LocalDate.of(currentDate.getYear(),4,1);
		
		if(currentDate.getMonthValue()<4) {
			startfinancialYear = startfinancialYear.minusYears(1);
		}
		double totalSalary = 0;
		if(e.getDoj().isBefore(startfinancialYear)) {
			totalSalary = e.getSalary() *12;
			resposebean.setYearsalary(totalSalary);
		}
		else {
			
			long monthsworked = ChronoUnit.MONTHS.between(e.getDoj().withDayOfMonth(1),currentDate.withDayOfMonth(1));
			double daysinMonth = e.getDoj().lengthOfMonth();
			double partialMonthsal = (daysinMonth - e.getDoj().getDayOfMonth()+1)/daysinMonth * e.getSalary();
			totalSalary = (monthsworked +1 )*e.getSalary()-(e.getSalary()-partialMonthsal);
			resposebean.setYearsalary(totalSalary);
		}
		double tax = 0,cess = 0;
		
		double remainingsalary = totalSalary;
		if(remainingsalary>250000) {
			remainingsalary -=250000;
			if(remainingsalary>250000) {
				tax+=250000*0.05;
				remainingsalary -=250000;
				if(remainingsalary>500000) {
					tax +=500000 * 0.10;
					remainingsalary -= 500000;
					tax+= remainingsalary * 0.20;
				}else {
					tax += remainingsalary * 0.10;
				}
			}else {
				tax += remainingsalary * 0.05;
			}
			
		}
		if(totalSalary > 2500000) {
			cess += (totalSalary - 2500000) * 0.02;
		}
		resposebean.setTaxAmount(tax);
		resposebean.setCessAmount(cess);
		
		return resposebean;
	}

	
	
}

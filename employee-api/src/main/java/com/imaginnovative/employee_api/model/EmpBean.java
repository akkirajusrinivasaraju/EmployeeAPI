package com.imaginnovative.employee_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmpBean {

	private long code;
	private String firstName;
	private String lastName;
	private double Yearsalary;
	private double taxAmount;
	private double cessAmount;
	
}

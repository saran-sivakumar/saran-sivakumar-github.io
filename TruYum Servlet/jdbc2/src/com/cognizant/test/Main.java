package com.cognizant.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.cognizant.jdbc.dao.UserInsertDao;
import com.cognizant.jdbc.model.User;
import com.cognizant.model.Employee;

public class Main {
		
	public static void main(String[] args) throws NumberFormatException, IOException, ParseException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat("#.00");
		Employee employee = new Employee();
		System.out.println("User Name");
		employee.setName(bf.readLine());
		
		System.out.println("Enter date of Birth");
		employee.setDateOfBirth(sdf.parse(bf.readLine()));
		
		System.out.println("User Salary");
		employee.setSalary(Double.parseDouble(bf.readLine()));    
		
		new UserInsertDao().insertUserDetails(user);
}
}
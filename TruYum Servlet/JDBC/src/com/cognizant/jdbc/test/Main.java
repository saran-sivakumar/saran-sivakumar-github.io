package com.cognizant.jdbc.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.cognizant.jdbc.dao.UserDeleteDao;
import com.cognizant.jdbc.dao.UserDisplayDao;
import com.cognizant.jdbc.dao.UserInsertDao;
import com.cognizant.jdbc.dao.UserUpdateDao;
import com.cognizant.jdbc.model.User;


public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException, ParseException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat("#.00");
		int choice = 0;
	
		do {
			System.out.println("\n1.Add");
			System.out.println("2.Update");
			System.out.println("3.Delete");
			System.out.println("4.View");
			System.out.println("5.Exit");
			System.out.println("Enter Your Choice");
			 choice = Integer.parseInt(bf.readLine());
			switch(choice) {
			case 1:
				User user = new User();
				System.out.println("User Name");
				user.setName(bf.readLine());
				
				System.out.println("Enter date of joining");
				user.setDateOfJoining(sdf.parse(bf.readLine()));
				
				System.out.println("User Salary");
				user.setSalary(Double.parseDouble(bf.readLine()));    
				
				System.out.println("user Phone number");
				user.setPhoneNumber(Long.parseLong(bf.readLine()));
				
				new UserInsertDao().insertUserDetails(user);
				display(new UserDisplayDao().getAllUserDetails());
				break;
				
			case 2:
				System.out.println("Enter userId to update");
				int Id = Integer.parseInt(bf.readLine());
				System.out.println("enter");
				long phone=Long.parseLong(bf.readLine());
				new UserUpdateDao() .updateUserDetails(Id,phone);
				display(new UserDisplayDao().getAllUserDetails());
				break;
			
			case 3:
				System.out.println("Enter userId to Delete");
				int userId = Integer.parseInt(bf.readLine());
				new UserDeleteDao() .DeleteUserDetails(userId);
				display(new UserDisplayDao().getAllUserDetails());
				break;
				
			case 4:
				ArrayList<User> userList = new UserDisplayDao().getAllUserDetails();
				display(userList);
				break;
				
			case 5:
				break;
			
			
			default:
				System.out.println("Enter a valid choice");
			}
		}while(choice != 5);
		System.out.println("***************************************************************");

		}
		
	
public static void display(ArrayList<User> userList) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	DecimalFormat df = new DecimalFormat("#.00");
	 userList = new UserDisplayDao().getAllUserDetails();
	System.out.format("%10s%10s%10s%15s%15s", "ID","NAME","DOJ","SALARY","PHONE_NUMBER");
	for(User us: userList) {
		System.out.format("\n%10s%10s%15s%10s%15s", us.getId(),us.getName(),sdf.format(us.getDateOfJoining()),df.format(us.getSalary()),us.getPhoneNumber());
	}
	
}
}

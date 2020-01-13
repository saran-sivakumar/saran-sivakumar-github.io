package com.cognizant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cognizant.dao.ConnectionHandler;
import com.cognizant.jdbc.model.User;
import com.cognizant.model.Employee;

public class EmployeeDisplayDao {
public static final String EMPLOYEE_ALL_DETAILS="select * from user";
	
	public ArrayList<Employee> getAllUserDetails(){
		ArrayList<Employee> employeeList = new ArrayList<>();
		Connection connection = ConnectionHandler.getDbConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(EMPLOYEE_ALL_DETAILS);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			System.out.println(metaData.getColumnCount());
			System.out.println(metaData.getColumnName(1));
			System.out.println(metaData.getTableName(1));
			while(resultSet.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setDateOfBirth(resultSet.getDate("date_of_birth"));
				employee.setSalary(resultSet.getInt("salary"));
				employeeList.add(employee);
	

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				preparedStatement.close();
				connection.close();
			}catch(SQLException e) {
			e.printStackTrace();
			}
		}
		return employeeList;
	}

}

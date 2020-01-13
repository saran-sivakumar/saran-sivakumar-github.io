package com.cognizant.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.ResultSetMetaData;
import com.cognizant.jdbc.model.User;


public class UserDisplayDao {
	
	public static final String USER_ALL_DETAILS="select * from user";
	
	public ArrayList<User> getAllUserDetails(){
		ArrayList<User> userList = new ArrayList<>();
		Connection connection = ConnectionHandler.getDbConnection();
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(USER_ALL_DETAILS);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			System.out.println(metaData.getColumnCount());
			System.out.println(metaData.getColumnName(1));
			System.out.println(metaData.getTableName(1));
			while(resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setDateOfJoining(resultSet.getDate("date_of_joining"));
				user.setSalary(resultSet.getInt("salary"));
				user.setPhoneNumber(resultSet.getLong("phone_number"));
				userList.add(user);
	

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
		return userList;
	}

}

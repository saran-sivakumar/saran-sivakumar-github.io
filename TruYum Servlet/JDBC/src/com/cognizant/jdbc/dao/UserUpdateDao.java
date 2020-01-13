package com.cognizant.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserUpdateDao {
	public static final String UPDATE_USER = "update user set salary = salary+10000,phone_number=? where id=?";
	
	public boolean updateUserDetails(int userId,long phone) {
		Connection connection = ConnectionHandler.getDbConnection();
		PreparedStatement statement = null;
		try {
				
			statement =connection.prepareStatement(UPDATE_USER);
			statement.setLong(1,phone);
			statement.setInt(2, userId);
			
			int noOfRows = statement.executeUpdate();
			if(noOfRows > 0) {
				return true;
				
			}
			else {
				System.out.println("<----Id not found---->");
			}
			
		}catch(SQLException e) {
			System.out.println("User Not Found");
		}finally {
			try {
			statement.close();
			connection.close();
		}catch(SQLException e) {
			
		}
	}
	return false;
	
	}
}

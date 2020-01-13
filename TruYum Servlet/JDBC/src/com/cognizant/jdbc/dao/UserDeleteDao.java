package com.cognizant.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDeleteDao {
	public static final String DELETE_USER = "delete from user where id=?";
	public boolean DeleteUserDetails(int userId) {
	Connection connection = ConnectionHandler.getDbConnection();
	PreparedStatement statement = null;
	try {
		statement =connection.prepareStatement(DELETE_USER);
		statement.setInt(1, userId);
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


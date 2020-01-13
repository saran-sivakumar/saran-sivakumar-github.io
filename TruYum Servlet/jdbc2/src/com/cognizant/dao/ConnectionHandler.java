package com.cognizant.dao;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHandler {
	
	static Connection getDbConnection() {
		BufferedInputStream bufferedInputStream=(BufferedInputStream) ConnectionHandler.class.getClassLoader()
				.getResourceAsStream("db.properties");
		Properties prop = new Properties();
		try {
			prop.load(bufferedInputStream);
		}catch(IOException e) {
			System.out.println("Unable to load/find db.properties file");
		}
		String driver=(String)prop.get("driver");
		//System.out.println("Driver Name ->"+driver);
		try {
		Class.forName(driver);			
		}catch(ClassNotFoundException e) {
			System.out.println("Unable to load mysql driver");
		}
		String user = (String)prop.get("user");
		String password=(String)prop.get("password");
		String url=(String)prop.get("url");
		Connection connection=null;
		//System.out.println("DB Details -> "+user+"--"+password+"--"+url);
		
		try {
			 connection = DriverManager.getConnection(url, user,password);
			//System.out.println(connection);
			 
		}catch(SQLException e){
			System.out.println("unable to connect the database");
			
		}
		return connection;
		
	}
	

}

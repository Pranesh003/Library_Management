package com.kce.book.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
public class Dbutil {
	public static Connection getDBConnection()
	{
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver"); 
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "SYSTEM";
		String pass = "84380";
		Connection connection=DriverManager.getConnection(url,user,pass);
		return connection;
		}
		catch(ClassNotFoundException | SQLException e)
		{
			System.out.println(e);
			return null;
		}
	}
}

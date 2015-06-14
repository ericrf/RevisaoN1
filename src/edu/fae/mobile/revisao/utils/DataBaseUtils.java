package edu.fae.mobile.revisao.utils;


import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class DataBaseUtils {

	public static Connection getConnection() throws Throwable {
		Class.forName("com.mysql.jdbc.Driver");
		return (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mobile_n1","root", "root");
	}

}

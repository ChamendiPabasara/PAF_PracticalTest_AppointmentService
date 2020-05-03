package com;

import java.sql.Connection;
import java.sql.DriverManager;

public class Apointments {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/health_care", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}

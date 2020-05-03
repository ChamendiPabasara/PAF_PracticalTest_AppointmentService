package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

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
	
	public String ReadAppointments() {
		
		String output = "";
		
		try{
			Connection con  = connect();
				if (con == null) {

					return "Error while connecting to the database for reading.";
				}
			
				  output = "<table border=\"1\"><tr><th>Appointment ID</th>"+
					 		"<th>Appointment Date</th> "+
					 		"<th>Appointment Time</th>"
					 		+ "<th>Patient ID</th>"
					 		+"<th>Doctor ID</th>"
					 		+"<th>Hospital ID</th>"+ "<th>Update</th><th>Remove</th></tr>";
				
			LocalDate prvPaymentDate = null;
			String readQuery = "select * from appoinment";
			
			 PreparedStatement pstmt = con.prepareStatement(readQuery);
			 
		
					 		 
				
			 ResultSet rs = pstmt.executeQuery(readQuery); 
			
			 
			while(rs.next()) {
				int AppID = rs.getInt("appoinment_id");
			    Date day = rs.getDate("date");
				String time = rs.getString("time");
				int pid = rs.getInt("patient_patient_id");
				int did = rs.getInt("doctor_doc_id");
				int hosID = rs.getInt("hospital_hosp_id");
			
				
				// Add into the html table
				output += "<tr><td><input id='hidAppIDUpdate'" + "name='hidAppIDUpdate'" + "type='hidden' value='"
						+ AppID + "'>" + AppID + "</td>";
				output += "<td>" + day + "</td>";
				output += "<td>" + time + "</td>";
				output += "<td>" + pid + "</td>";
				output += "<td>" + did + "</td>";
				output += "<td>" + hosID + "</td>";
				// buttons
				output += "<td><input name='btnUpdate'" + "type='button' value='Update'"
						+ "class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove'"
						+ "type='button' value='Remove'" + "class='btnRemove btn btn-danger'" + "data-Appid='" + AppID
						+ "'>" + "</td></tr>";
				

			}
		
		   output += "</table>";
			  return output;
		}
		catch(SQLException e){
			
			e.printStackTrace();
			return "Error occured during retrieving data";
		}
	}

}

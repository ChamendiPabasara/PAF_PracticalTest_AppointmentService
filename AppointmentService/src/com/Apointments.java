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
	
public String addAppointment(Date day, String time, int pid,int did,int hosID) {
	
	String output = "";
		
		try{
			
			Connection con  = connect();
			if (con == null) {
				
				return "Error while connecting to the database for inserting.";
			}
			
			//Query for count AppId for duplicate date & time for same doctor and hospital
			String checkQuery="select count(appoinment_id)  from appoinment where date = ? and time = ? and doctor_doc_id = ? and hospital_hosp_id = ?";
			PreparedStatement preparedstatement = con.prepareStatement(checkQuery);
			
			preparedstatement.setDate(1,day);
			preparedstatement.setString(2,time);
			preparedstatement.setInt(3,did);
			preparedstatement.setInt(4,hosID);
			
			ResultSet newresultset = preparedstatement.executeQuery();
			
			newresultset.next();
			
			//convert count Appointment ids to integer  
			int value = Integer.parseInt(newresultset.getObject(1).toString());
			
			if(value !=0)
			{
				output = "{\"status\":\"error\", \"data\":" + " \"The particular time slot has been reserved please choose another a time slot\"}";
				return "The particular time slot has been reserved please choose another a time slot.";
				
			}
			else {
				//check date is before current date 
				SimpleDateFormat  simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date(System.currentTimeMillis());

				if(day.compareTo(date)<0) {
					
				output = "{\"status\":\"error\", \"data\":" + " \"You cannot request past dates as appointment dates please select a future date.\"}";
				return "You cannot request past dates as appointment dates please select a future date";
				}
				
				else {
					
			String insertAppQuery = " insert into appoinment values (NULL,?, ?, ?, ?, ?)";
			PreparedStatement pstmnt = con.prepareStatement(insertAppQuery);
			
			pstmnt.setDate(1,day);
			pstmnt.setString(2,time);
			pstmnt.setInt(3,pid);
			pstmnt.setInt(4,did);
			pstmnt.setInt(5,hosID);
			

			pstmnt.execute();
			con.close();

			String newAppointments = ReadAppointments();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointments + "\"}";
			return "Appointment added successfully...";
			
				}
			}
		}
		catch(SQLException e){
			output = "{\"status\":\"error\", \"data\":" + " \"Error while inserting the Appointment.\"}";
			return "Error occured during adding an Appoinment\n" + e.getMessage();
		}
		
	}

public String UpdateAppointment(Date day,String time,int AppID) {
	String output = "";
	
	try{
		
		Connection con  = connect();
		if (con == null) {
			
			return "Error while connecting to the database for inserting.";
		}
		
		//get doctor id and hospital id for given appointment id
		String getdocIDQuery = "SELECT doctor_doc_id,hospital_hosp_id  FROM appoinment WHERE appoinment_id = ?";
		
		
		PreparedStatement preparedstatement = con.prepareStatement(getdocIDQuery);
	
		preparedstatement.setInt(1,AppID);
		
	
		ResultSet newresultset = preparedstatement.executeQuery();
		
		
		newresultset.next();
		
		//Assign into variable 
		int docid = newresultset.getInt("doctor_doc_id");
		int hosID = newresultset.getInt("hospital_hosp_id");
		
		//get Count of given info
		String checkQuery="select count(appoinment_id)  from appoinment where date = ? and time = ? and doctor_doc_id = ? and hospital_hosp_id = ?";
		PreparedStatement prstmnt = con.prepareStatement(checkQuery);
		
		prstmnt.setDate(1,day);
		prstmnt.setString(2,time);
		prstmnt.setInt(3,docid);
		prstmnt.setInt(4,hosID);
		
		ResultSet newresultset2 = prstmnt.executeQuery();
		
		newresultset2.next();
		
		//convert count into integer
		int value = Integer.parseInt(newresultset2.getObject(1).toString());
		
		
		if(value !=0)
		{
			output = "{\"status\":\"error\", \"data\":" + "\"The particular time slot has been reserved please choose another a time slot.\"}";
			return "The particular time slot has been reserved please choose another a time slot.";
			
		}
		
		else {
		
		
		String updateAppQuery =  "UPDATE appoinment SET date=?,time=? WHERE appoinment_id=?"; 

		PreparedStatement pstmnt = con.prepareStatement(updateAppQuery);
		pstmnt.setDate(1, day);
		pstmnt.setString(2, time);
		pstmnt.setInt(3, AppID);
		
         pstmnt.execute();
         con.close();
			String newAppointments = ReadAppointments();
			
			output = "{\"status\":\"success\", \"data\": \"" + newAppointments + "\"}";
		return "Apointment Updated successfully...";
		}
	}
	catch(SQLException e){
		output = "{\"status\":\"error\", \"data\":" + "\"Error while updating the Appointment.\"}";
		return "Error occured during Updating an Appointment\n" + e.getMessage();
	}
	
}

}

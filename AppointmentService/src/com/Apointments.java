package com;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.TimeZone;

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
			
				  output = "<table border='1'><tr><th>Appointment ID</th>"+
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
				String AppID = Integer.toString(rs.getInt("appoinment_id")); 
			    String day = rs.getString("date");
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
						+ "type='button' value='Remove'" + "class='btnRemove btn btn-danger'" + "data-appid='" + AppID
						+ "'>" + "</td></tr>";
				

			}
		
		   output += "</table>";
			  
		}
		catch(SQLException e){
			
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		
		}
		return output;
	}
	
public String addAppointment(String day, String time, int pid,int did,int hosID) {
	
	String output = "";
		
		try{
			
			Connection con  = connect();
			if (con == null) {
				
				return "Error while connecting to the database for inserting.";
			}
			
			
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date startDate=null;
			try {
				startDate = formatter.parse(day);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
			//Query for count AppId for duplicate date & time for same doctor and hospital
			String checkQuery="select count(appoinment_id)  from appoinment where date = ? and time = ? and doctor_doc_id = ? and hospital_hosp_id = ?";
			PreparedStatement preparedstatement = con.prepareStatement(checkQuery);
			
			java.sql.Date sDate = new java.sql.Date(startDate.getTime());
			
			preparedstatement.setDate(1,sDate);
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
				
				
			}
			else {
				//check date is before current date 
				
				SimpleDateFormat  simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date(System.currentTimeMillis());

				if(startDate.compareTo(date)<0) {
					
				output = "{\"status\":\"error\", \"data\":" + " \"You cannot request past dates as appointment dates please select a future date.\"}";
				
				}
				
				else {
					
			String insertAppQuery = " insert into appoinment values (NULL,?, ?, ?, ?, ?)";
			PreparedStatement pstmnt = con.prepareStatement(insertAppQuery);
			
			java.sql.Date sDate2 = new java.sql.Date(startDate.getTime());
			
			pstmnt.setDate(1,sDate2);
			pstmnt.setString(2,time);
			pstmnt.setInt(3,pid);
			pstmnt.setInt(4,did);
			pstmnt.setInt(5,hosID);
			

			pstmnt.execute();
			con.close();
		
			String newAppointments = ReadAppointments();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointments + "\"}";
			
				}
			}
		}
		catch(SQLException e){
			output = "{\"status\":\"error\", \"data\":" + " \"Error while inserting the Appointment.\"}";
			
		}
		return output;
	}

public String UpdateAppointment(int AppID,String day,String time) {
	String output = "";
	
	try{
		
		Connection con  = connect();
		if (con == null) {
			
			return "Error while connecting to the database for inserting.";
		}
		
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date startDate2=null;
		String ndate=day.replaceAll("-", "/");
		try {
			
			startDate2 = formatter1.parse(ndate);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		java.sql.Date date1 = new java.sql.Date(startDate2.getTime());
		
		prstmnt.setDate(1,date1);
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
			
			
		}
		
		else {
		
		
		String updateAppQuery =  "UPDATE appoinment  SET time=? ,date =?  WHERE appoinment_id=?"; 

		PreparedStatement pstmnt = con.prepareStatement(updateAppQuery);
		
		

		String[] arrOfStr = day.split("-", 5); 
		String DateArray= arrOfStr[2]+"-"+arrOfStr[0]+"-"+arrOfStr[1];
		
		
		System.out.println("Reconstructed Date "+DateArray);
		System.out.println("Parameter Date "+day);
		pstmnt.setString(1, time);
		pstmnt.setString(2, DateArray);
		pstmnt.setInt(3, AppID);
		
         pstmnt.execute();
         con.close();
			String newAppointments = ReadAppointments();
			
			output = "{\"status\":\"success\", \"data\": \"" + newAppointments + "\"}";
		
		}
	}
	catch(SQLException e){
		output = "{\"status\":\"error\", \"data\":" + "\"Error while updating the Appointment.\"}";
		
	}
	return output;
	
}
public String DeleteAppointment(String AppID) {
	
	String output = "";
	
	try{
		
		Connection con  = connect();
		if (con == null) {
			
			return "Error while connecting to the database for inserting.";
		}
		
		//query for get date 
		
		String getdateQuery="select date  from appoinment where appoinment_id = ?";
		PreparedStatement preparedstatement2 = con.prepareStatement(getdateQuery);
			
		preparedstatement2.setInt(1,Integer.parseInt(AppID));
		ResultSet newresultset = preparedstatement2.executeQuery();
		
		newresultset.next();
		
		 //assign to variable
		  Date day = newresultset.getDate("date");
		
		  System.out.println(day);
		SimpleDateFormat  simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
	
		//check past dates
		
		if(day.compareTo(date)<0) {
			
			output = "{\"status\":\"error\", \"data\":" + "\"You cannot delete past dates as appointment dates only future dates.\"}";
			
		
	}
		
		else {
			
			 String Deletequery = "delete from appoinment where appoinment_id=?";
				
			 PreparedStatement pstmnt = con.prepareStatement(Deletequery);
			 
				pstmnt.setInt(1, Integer.parseInt(AppID));
				pstmnt.execute();
				
				con.close();
				String newAppointments = ReadAppointments();
				output = "{\"status\":\"success\", \"data\": \"" + newAppointments + "\"}";
				
			
	}
	}catch(SQLException e){
		
		output = "{\"status\":\"error\", \"data\":" + "\"Error while deleting the Appointment.\"}";
		System.err.println(e.getMessage());
	}
	return output;
}

}

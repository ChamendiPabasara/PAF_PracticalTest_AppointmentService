package com;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


@WebServlet("/ApointmentsAPI")
public class ApointmentsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	Apointments AppObj = new Apointments();
   
    public ApointmentsAPI() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String dates = request.getParameter("date");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date day = null;
		try {
			day = (Date) dateFormat.parse(dates);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 String output = AppObj.addAppointment(day,
		 request.getParameter("	time"),
		 Integer.parseInt(request.getParameter("patient_patient_id")),
		 Integer.parseInt(request.getParameter("doctor_doc_id ")),
		 Integer.parseInt(request.getParameter("hospital_hosp_id")));
		 
		 response.getWriter().write(output);
		
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

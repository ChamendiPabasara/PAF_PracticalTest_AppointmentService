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


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 Map paras = getParasMap(request);
		 String output=""; 
		
		try {
			output = AppObj.UpdateAppointment(
			 (Date) dateFormat.parse(paras.get("date").toString()),
			 paras.get("time").toString(),
			 Integer.parseInt(paras.get("appoinment_id ").toString()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		response.getWriter().write(output);
		} 

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	private static Map getParasMap(HttpServletRequest request)
    {
     Map<String, String> map = new HashMap<String, String>();
    try {
     Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
     String queryString = scanner.hasNext() ?
     scanner.useDelimiter("\\A").next() : "";
     scanner.close();
     String[] params = queryString.split("&");
     for (String param : params)
     { 
    	 String[] p = param.split("=");
    	 map.put(p[0], p[1]);
     }
      }catch (Exception e)
    	 {
    	 }
    	return map;
    }
}

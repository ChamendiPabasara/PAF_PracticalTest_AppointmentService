package com;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
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
	
		
	    int PID = Integer.parseInt(request.getParameter("patient"));
	    int DID = Integer.parseInt(request.getParameter("doctor"));
	    int HID = Integer.parseInt(request.getParameter("hospital"));
	    String stringdate=request.getParameter("datepicker").toString();
	    String stringtime=request.getParameter("timepicker").toString();
	   	    
		 String output = AppObj.addAppointment( 
				 stringdate,
				 stringtime,
		         PID,
		         DID,
		         HID);
		 System.out.println(output);
		 
		 response.getWriter().write(output);
		
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		
		Map paras = getParasMap(request);
		
		String sdate =  paras.get("datepicker").toString().replace("%2F", "-");
		int buttonID=Integer.parseInt(paras.get("hidAppIDSave").toString());
		String stringtime1=paras.get("timepicker").toString().replace("%3A", ":");
		String stringtime2=stringtime1.replace("+", " ");
		 String output = AppObj.UpdateAppointment(buttonID,
				 sdate,
				 stringtime2
		         );
		 
		
		 
		response.getWriter().write(output);
		System.out.println(output);
		} 
	

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	
		 Map paras = getParasMap(request);
		 String output = AppObj.DeleteAppointment(paras.get("AppID").toString());
		 response.getWriter().write(output);
		 System.out.println(output);
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

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
	    String a=request.getParameter("datepicker").toString();
	    String b=request.getParameter("timepicker").toString();
	    System.out.println("date is:"+a);
	    
	    //SimpleDateFormat availDate = new SimpleDateFormat("dd-MM-yyyy");


	    
	    		
	    System.out.println(a);
		
	    System.out.println(b);
		
	    System.out.println(PID);
		
	    System.out.println(DID);
	    
	    System.out.println(HID);
	    
		 String output = AppObj.addAppointment( 
				 a,
		         b,
		         PID,
		         DID,
		         HID);
		 System.out.println(output);
		 
		 response.getWriter().write(output);
		
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 
		
	/*	try {
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
		 
		response.getWriter().write(output);*/
		
		Map paras = getParasMap(request);
		
		String sdate =  paras.get("datepicker").toString().replace("%2F", "-");
		int a=Integer.parseInt(paras.get("hidAppIDSave").toString());
		String aw1=paras.get("timepicker").toString().replace("%3A", ":");
		String aw=aw1.replace("+", " ");
		 String output = AppObj.UpdateAppointment(a,
				 sdate,
				 aw
		         );
		 
		
		 
		response.getWriter().write(output);
		System.out.println(output);
		} 
	//} 

	
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

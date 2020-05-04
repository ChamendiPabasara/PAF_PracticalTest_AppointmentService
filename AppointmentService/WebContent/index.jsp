<%@page import="com.Apointments"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointments Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Appointments.js"></script>

<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>

  <script>
  $( function() {
    $( "#datepicker" ).datepicker({ minDate: -20, maxDate: "+1M +15D" });
  } );
  
  $(function() {
	  $('#timepicker').timepicker({
		  timeFormat: 'h:mm p',
	        interval: 30,
	        minTime: '10',
	        maxTime: '6:00pm',
		  
	  });
	});
  
  </script>
 
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Appointments Management</h1>
				<form id="formApp" name="formApp">
				
					Select a date : <input id="datepicker" name="date" type="text"
						class="form-control form-control-sm"> 
						
						<br> Select a time :<input type="timepicker" id="timepicker"
						class="form-control form-control-sm"> 
						
						
						<br> Select your PatientID: <input id="patient" name="patient" type="text"
					class="form-control form-control-sm"> <br> 
						
					 <br> Select a doctor : <input id="doctor" name="doctor" type="text"
						class="form-control form-control-sm"> 
						
					<br> Select a hospital: <input id="hospital" name="hospital" type="text"
					class="form-control form-control-sm"> <br> 
					
					<input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary">
						
					 <input type="hidden" id="hidAppIDSave" name="hidAppIDSave" value="">
					 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divAppGrid">
				
					<%
					Apointments AppObj = new Apointments();
						out.print(AppObj.ReadAppointments());
					%>
					
				</div>
			</div>
		</div>
	</div>

</body>
</html>
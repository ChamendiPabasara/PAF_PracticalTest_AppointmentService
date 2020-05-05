$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertSuccess").hide();

});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateAppForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------

	var type = ($("#hidAppIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "ApointmentsAPI",
		type : type,
		data : $("#formApp").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onAppSaveComplete(response.responseText, status);
		}
	});
});

function onAppSaveComplete(response, status) {
	
	
	if (status == "success") {
		var resultSet = JSON.parse(response);

    if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divAppGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();

		
	} 

	else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	 if (status == "Exists Appointment") {
		$("#alertError").text("The particular time slot has been reserved please choose another a time slot.");
		$("#alertError").show();

	}
	$("#hidAppIDSave").val("");
	$("#formApp")[0].reset();
}

// UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidAppIDSave").val(
					$(this).closest("tr").find('#hidAppIDUpdate').val());
			$("#datepicker").val($(this).closest("tr").find('td:eq(1)').text());
			$("#timepicker").val($(this).closest("tr").find('td:eq(2)').text());
			$("#patient").val($(this).closest("tr").find('td:eq(3)').text());
			$("#doctor").val($(this).closest("tr").find('td:eq(4)').text());
			$("#hospital").val($(this).closest("tr").find('td:eq(5)').text());
			
			
		});

// REMOVE==========================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "ApointmentsAPI",
		type : "DELETE",
		data : "AppID=" + $(this).data("appid"),
		dataType : "text",
		complete : function(response, status) {
			onAppDeleteComplete(response.responseText, status);
		}
	});
});

function onAppDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divAppGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// CLIENTMODEL=========================================================================
function validateAppForm() {
	// Date
	if ($("#datepicker").val().trim() == "") {
		return "Select a date.";
	}
	// Time
	if ($("#timepicker").val().trim() == "") {
		return "Select a time.";
	}

	// PatientID-------------------------------
	if ($("#patient").val().trim() == "") {
		return "Insert your patientID.";
	}
	// is numerical value
	var pid = $("#patient").val().trim();
	if (!$.isNumeric(pid)) {
		return "Insert a numerical value for PatientID.";
	}
	
	// doctorID-------------------------------
	if ($("#doctor").val().trim() == "") {
		return "Insert your DoctorID.";
	}
	// is numerical value
	var did = $("#doctor").val().trim();
	if (!$.isNumeric(did)) {
		return "Insert a numerical value for doctorID.";
	}
	
	// hospital ID-------------------------------
	if ($("#hospital").val().trim() == "") {
		return "Insert your hospitalID.";
	}
	// is numerical value
	var hid = $("#hospital").val().trim();
	if (!$.isNumeric(hid)) {
		return "Insert a numerical value for hospitalID.";
	}
	

	return true;
}



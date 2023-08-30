window.onload = load;
function load() {
	document.getElementById('reportCode').focus();
}

/* Maker Wise Vehicle Register */

function manufacturerWiseRegister() {
	var manufacturerWise = $("#manufacturerWise").val();
	if (manufacturerWise == '1') {

		$("#manufacturer").removeAttr("disabled");
		document.getElementById('manufacturerdiv').style.display = '';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';
		
		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';
		
		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';

	} else if (manufacturerWise == '2') {

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';
		
		$("#manufacturer").removeAttr("disabled");
		document.getElementById('manufacturerdiv').style.display = '';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';
		
		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';

	} else {
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';

		$("#issueDateFrom").attr("disabled", "disabled");
		document.getElementById('issueDateFromdiv').style.display = 'none';

		$("#issueDateTo").attr("disabled", "disabled");
		document.getElementById('issueDateTodiv').style.display = 'none';

	}

}


/* Manufacturer Year Wise Vehicle Issue Register */

function makerWiseVehicleRegister() {
	var makerWise = $("#makerWise").val();
	if (makerWise == '1') {

		$("#manufacturerYearFrom").removeAttr("disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = '';

		$("#manufacturerYearTo").removeAttr("disabled");
		document.getElementById('manufacturerYearTodiv').style.display = '';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	} else if (makerWise == '2') {

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#manufacturerYearFrom").removeAttr("disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = '';

		$("#manufacturerYearTo").removeAttr("disabled");
		document.getElementById('manufacturerYearTodiv').style.display = '';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	} else {

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';

		$("#issueDateFrom").attr("disabled", "disabled");
		document.getElementById('issueDateFromdiv').style.display = 'none';

		$("#issueDateTo").attr("disabled", "disabled");
		document.getElementById('issueDateTodiv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	}

}


/* Structured Report */

function structuredReportType() {

	var reportType = $("#reportType").val();
	if (reportType == '1') {

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

	} else if (reportType == '2') {

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#metropolitanArea").removeAttr("disabled");
		document.getElementById('metropolitanAreadiv').style.display = '';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

	} else if (reportType == '3') {

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#district").removeAttr("disabled");
		document.getElementById('districtdiv').style.display = '';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

	} else {

		$("#issueDateFrom").attr("disabled", "disabled");
		document.getElementById('issueDateFromdiv').style.display = 'none';

		$("#issueDateTo").attr("disabled", "disabled");
		document.getElementById('issueDateTodiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';
	}
}


/* UnStructured Report */

function unStructuredReportType() {

	var unStructuredReportType = $("#unStrucReportType").val();
	if (unStructuredReportType == '1') {

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';
		
		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

	} else if (unStructuredReportType == '2') {

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#metropolitanArea").removeAttr("disabled");
		document.getElementById('metropolitanAreadiv').style.display = '';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

	} else if (unStructuredReportType == '3') {

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#district").removeAttr("disabled");
		document.getElementById('districtdiv').style.display = '';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

	} else if (unStructuredReportType == '4') {

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#metropolitanArea").removeAttr("disabled");
		document.getElementById('metropolitanAreadiv').style.display = '';

		$("#vehicleType").removeAttr("disabled");
		document.getElementById('vehicleTypediv').style.display = '';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

	} else if (unStructuredReportType == '5') {

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#district").removeAttr("disabled");
		document.getElementById('districtdiv').style.display = '';

		$("#vehicleType").removeAttr("disabled");
		document.getElementById('vehicleTypediv').style.display = '';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';

	} else if (unStructuredReportType == '6') {

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';

	} else if (unStructuredReportType == '7') {

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#metropolitanArea").removeAttr("disabled");
		document.getElementById('metropolitanAreadiv').style.display = '';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';

	} else if (unStructuredReportType == '8') {

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#district").removeAttr("disabled");
		document.getElementById('districtdiv').style.display = '';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';

	} else if (unStructuredReportType == '9') {

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#metropolitanArea").removeAttr("disabled");
		document.getElementById('metropolitanAreadiv').style.display = '';

		$("#vehicleType").removeAttr("disabled");
		document.getElementById('vehicleTypediv').style.display = '';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';

	} else if (unStructuredReportType == '10') {

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#district").removeAttr("disabled");
		document.getElementById('districtdiv').style.display = '';

		$("#vehicleType").removeAttr("disabled");
		document.getElementById('vehicleTypediv').style.display = '';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';

	} else {

		$("#issueDateFrom").attr("disabled", "disabled");
		document.getElementById('issueDateFromdiv').style.display = 'none';

		$("#issueDateTo").attr("disabled", "disabled");
		document.getElementById('issueDateTodiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
	}
}

function selectReport() {

	var report_code = $("#reportCode").val();

	if (report_code == 1001) {

		$("#registrationNo").removeAttr("disabled");
		document.getElementById('registrationNodiv').style.display = '';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").attr("disabled", "disabled");
		document.getElementById('issueDateFromdiv').style.display = 'none';

		$("#issueDateTo").attr("disabled", "disabled");
		document.getElementById('issueDateTodiv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	} else if (report_code == 1002) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").removeAttr("disabled");
		document.getElementById('chassisNodiv').style.display = '';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").attr("disabled", "disabled");
		document.getElementById('issueDateFromdiv').style.display = 'none';

		$("#issueDateTo").attr("disabled", "disabled");
		document.getElementById('issueDateTodiv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	} else if (report_code == 1003) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").removeAttr("disabled");
		document.getElementById('engineNodiv').style.display = '';

		$("#issueDateFrom").attr("disabled", "disabled");
		document.getElementById('issueDateFromdiv').style.display = 'none';

		$("#issueDateTo").attr("disabled", "disabled");
		document.getElementById('issueDateTodiv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';
	}

	else if (report_code == 1004) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';
	}

	else if (report_code == 1005) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';
	}

	else if (report_code == 1006) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';
	}

	else if (report_code == 1007) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';
	}

	else if (report_code == 1008) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';
	}

	else if (report_code == 1009) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';
	}

	else if (report_code == 1010) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';
	}

	else if (report_code == 1011) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';
	}

	else if (report_code == 1012) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';
	}

	else if (report_code == 1013) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';
	}

	else if (report_code == 1014) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';
	}

	else if (report_code == 1015) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").removeAttr("disabled");
		document.getElementById('vehicleClassdiv').style.display = '';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	} else if (report_code == 1016) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleType").removeAttr("disabled");
		document.getElementById('vehicleTypediv').style.display = '';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#makerWise").attr("disabled", "disabled");
		document.getElementById('makerWisediv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	} else if (report_code == 1017) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").attr("disabled", "disabled");
		document.getElementById('issueDateFromdiv').style.display = 'none';

		$("#issueDateTo").attr("disabled", "disabled");
		document.getElementById('issueDateTodiv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#makerWise").attr("disabled", "disabled");
		document.getElementById('makerWisediv').style.display = 'none';
		
		$("#manufacturerWise").removeAttr("disabled");
		document.getElementById('manufacturerWisediv').style.display = '';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

	} else if (report_code == 1018) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#fuelType").removeAttr("disabled");
		document.getElementById('fuelTypediv').style.display = '';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#makerWise").attr("disabled", "disabled");
		document.getElementById('makerWisediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	} else if (report_code == 1019) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").removeAttr("disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#makerWise").attr("disabled", "disabled");
		document.getElementById('makerWisediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	} else if (report_code == 1020) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#makerWise").attr("disabled", "disabled");
		document.getElementById('makerWisediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	} else if (report_code == 1021) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#cubicCapacityForm").removeAttr("disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = '';

		$("#cubicCapacityTo").removeAttr("disabled");
		document.getElementById('cubicCapacityTodiv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#makerWise").attr("disabled", "disabled");
		document.getElementById('makerWisediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	} else if (report_code == 1022) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").removeAttr("disabled");
		document.getElementById('ladenWeightTodiv').style.display = '';

		$("#ladenWeightFrom").removeAttr("disabled");
		document.getElementById('ladenWeightFromdiv').style.display = '';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#makerWise").attr("disabled", "disabled");
		document.getElementById('makerWisediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	} else if (report_code == 1023) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").removeAttr("disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = '';

		$("#numberOfSeatTo").removeAttr("disabled");
		document.getElementById('numberOfSeatTodiv').style.display = '';

		$("#ladenWeightTo").removeAttr("disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#makerWise").attr("disabled", "disabled");
		document.getElementById('makerWisediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	}

	else if (report_code == 1024) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").attr("disabled", "disabled");
		document.getElementById('issueDateFromdiv').style.display = 'none';

		$("#issueDateTo").attr("disabled", "disabled");
		document.getElementById('issueDateTodiv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#makerWise").removeAttr("disabled");
		document.getElementById('makerWisediv').style.display = '';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	}

	else if (report_code == 1025) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").attr("disabled", "disabled");
		document.getElementById('issueDateFromdiv').style.display = 'none';

		$("#issueDateTo").attr("disabled", "disabled");
		document.getElementById('issueDateTodiv').style.display = 'none';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#year").removeAttr("disabled");
		document.getElementById('yeardiv').style.display = '';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#makerWise").attr("disabled", "disabled");
		document.getElementById('makerWisediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	}

	else if (report_code == 1026) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").attr("disabled", "disabled");
		document.getElementById('issueDateFromdiv').style.display = 'none';

		$("#issueDateTo").attr("disabled", "disabled");
		document.getElementById('issueDateTodiv').style.display = 'none';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#year").removeAttr("disabled");
		document.getElementById('yeardiv').style.display = '';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#makerWise").attr("disabled", "disabled");
		document.getElementById('makerWisediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	}

	else if (report_code == 1027) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#makerWise").attr("disabled", "disabled");
		document.getElementById('makerWisediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	} else if (report_code == 1028) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").attr("disabled", "disabled");
		document.getElementById('issueDateFromdiv').style.display = 'none';

		$("#issueDateTo").attr("disabled", "disabled");
		document.getElementById('issueDateTodiv').style.display = 'none';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").removeAttr("disabled");
		document.getElementById('reportTypediv').style.display = '';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#makerWise").attr("disabled", "disabled");
		document.getElementById('makerWisediv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	}

	else if (report_code == 1029) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").attr("disabled", "disabled");
		document.getElementById('issueDateFromdiv').style.display = 'none';

		$("#issueDateTo").attr("disabled", "disabled");
		document.getElementById('issueDateTodiv').style.display = 'none';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#unStrucReportType").removeAttr("disabled");
		document.getElementById('unStrucReportTypediv').style.display = '';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';

		$("#makerWise").attr("disabled", "disabled");
		document.getElementById('makerWisediv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	}

	else if (report_code == 1030) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").removeAttr("disabled");
		document.getElementById('issueDateTodiv').style.display = '';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#makerWise").attr("disabled", "disabled");
		document.getElementById('makerWisediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	}

	else if (report_code == 1031) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").removeAttr("disabled");
		document.getElementById('issueDateFromdiv').style.display = '';

		$("#issueDateTo").attr("disabled", "disabled");
		document.getElementById('issueDateTodiv').style.display = 'none';

		$("#branchId").removeAttr("disabled");
		document.getElementById('branchNamediv').style.display = '';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").removeAttr("disabled");
		document.getElementById('activitiesdiv').style.display = '';

		$("#noOfDays").removeAttr("disabled");
		document.getElementById('noOfDaysdiv').style.display = '';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#makerWise").attr("disabled", "disabled");
		document.getElementById('makerWisediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	}

	else if (report_code == 1032) {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").attr("disabled", "disabled");
		document.getElementById('issueDateFromdiv').style.display = 'none';

		$("#issueDateTo").attr("disabled", "disabled");
		document.getElementById('issueDateTodiv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").removeAttr("disabled");
		document.getElementById('ownerNamediv').style.display = '';

		$("#fathersName").removeAttr("disabled");
		document.getElementById('fathersNamediv').style.display = '';

		$("#address").removeAttr("disabled");
		document.getElementById('addressdiv').style.display = '';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#makerWise").attr("disabled", "disabled");
		document.getElementById('makerWisediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';

	}

	else {

		$("#registrationNo").attr("disabled", "disabled");
		document.getElementById('registrationNodiv').style.display = 'none';

		$("#chassisNo").attr("disabled", "disabled");
		document.getElementById('chassisNodiv').style.display = 'none';

		$("#engineNo").attr("disabled", "disabled");
		document.getElementById('engineNodiv').style.display = 'none';

		$("#issueDateFrom").attr("disabled", "disabled");
		document.getElementById('issueDateFromdiv').style.display = 'none';

		$("#issueDateTo").attr("disabled", "disabled");
		document.getElementById('issueDateTodiv').style.display = 'none';

		$("#branchId").attr("disabled", "disabled");
		document.getElementById('branchNamediv').style.display = 'none';

		$("#vehicleClass").attr("disabled", "disabled");
		document.getElementById('vehicleClassdiv').style.display = 'none';

		$("#vehicleType").attr("disabled", "disabled");
		document.getElementById('vehicleTypediv').style.display = 'none';

		$("#reportType").attr("disabled", "disabled");
		document.getElementById('reportTypediv').style.display = 'none';

		$("#fuelType").attr("disabled", "disabled");
		document.getElementById('fuelTypediv').style.display = 'none';

		$("#cubicCapacityForm").attr("disabled", "disabled");
		document.getElementById('cubicCapacityFormdiv').style.display = 'none';

		$("#cubicCapacityTo").attr("disabled", "disabled");
		document.getElementById('cubicCapacityTodiv').style.display = 'none';

		$("#ownerName").attr("disabled", "disabled");
		document.getElementById('ownerNamediv').style.display = 'none';

		$("#fathersName").attr("disabled", "disabled");
		document.getElementById('fathersNamediv').style.display = 'none';

		$("#address").attr("disabled", "disabled");
		document.getElementById('addressdiv').style.display = 'none';

		$("#activities").attr("disabled", "disabled");
		document.getElementById('activitiesdiv').style.display = 'none';

		$("#noOfDays").attr("disabled", "disabled");
		document.getElementById('noOfDaysdiv').style.display = 'none';

		$("#year").attr("disabled", "disabled");
		document.getElementById('yeardiv').style.display = 'none';

		$("#numberOfSeatFrom").attr("disabled", "disabled");
		document.getElementById('numberOfSeatFromdiv').style.display = 'none';

		$("#numberOfSeatTo").attr("disabled", "disabled");
		document.getElementById('numberOfSeatTodiv').style.display = 'none';

		$("#ladenWeightTo").attr("disabled", "disabled");
		document.getElementById('ladenWeightTodiv').style.display = 'none';

		$("#ladenWeightFrom").attr("disabled", "disabled");
		document.getElementById('ladenWeightFromdiv').style.display = 'none';

		$("#metropolitanArea").attr("disabled", "disabled");
		document.getElementById('metropolitanAreadiv').style.display = 'none';

		$("#district").attr("disabled", "disabled");
		document.getElementById('districtdiv').style.display = 'none';

		$("#unStrucReportType").attr("disabled", "disabled");
		document.getElementById('unStrucReportTypediv').style.display = 'none';

		$("#makerWise").attr("disabled", "disabled");
		document.getElementById('makerWisediv').style.display = 'none';

		$("#manufacturerYearFrom").attr("disabled", "disabled");
		document.getElementById('manufacturerYearFromdiv').style.display = 'none';

		$("#manufacturerYearTo").attr("disabled", "disabled");
		document.getElementById('manufacturerYearTodiv').style.display = 'none';
		
		$("#manufacturerWise").attr("disabled", "disabled");
		document.getElementById('manufacturerWisediv').style.display = 'none';
		
		$("#manufacturer").attr("disabled", "disabled");
		document.getElementById('manufacturerdiv').style.display = 'none';
	}
}

/*
 * function validate(){ var reportCode = $("#reportCode").val(); var required = '<span
 * style="color:#f43d3a">This Field is Required!</span>'; var formReturn = "";
 * 
 * if (reportCode == '1004'){ var branchId = $("#branchId").val(); var
 * issueDateFrom = $("#issueDateFrom").val(); var issueDateTo =
 * $("#issueDateTo").val();
 * 
 * if(branchId == ""){ $('#branch_validity').html(required);
 * $("#branchId").focus(); formReturn = 'N'; }
 * 
 * if(issueDateFrom == ""){ $('#issueDateFrom_validity').html(required);
 * $("#issueDateFrom").focus(); formReturn = 'N'; }
 * 
 * if(issueDateTo == ""){ $('#issueDateTo_validity').html(required);
 * $("#issueDateTo").focus(); formReturn = 'N'; }
 * 
 * if(formReturn == 'N'){ return false; } } else{ return true; } }
 * 
 * 
 * function validatefield(elm){ }
 */


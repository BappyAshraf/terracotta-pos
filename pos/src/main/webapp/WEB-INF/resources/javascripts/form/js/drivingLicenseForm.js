window.onload = load;	


function load() {
		document.getElementById('reportCode').focus();
	}

	function selectReport() {

		var report_code = $("#reportCode").val();
		
		//alert(report_code)

		if (report_code == 3001) {

			$("#fromDate").removeAttr("disabled");
			document.getElementById('fromDatediv').style.display = '';

			$("#toDate").removeAttr("disabled");
			document.getElementById('toDatediv').style.display = '';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").removeAttr("disabled");
			document.getElementById('genderdiv').style.display = '';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';

		} else if (report_code == 3002) {


			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';
			
		} else if (report_code == 3003) {


			$("#fromDate").removeAttr("disabled");
			document.getElementById('fromDatediv').style.display = '';

			$("#toDate").removeAttr("disabled");
			document.getElementById('toDatediv').style.display = '';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").removeAttr("disabled");
			document.getElementById('genderdiv').style.display = '';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';

			
		}

		else if (report_code == 3004) {
			

			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';

		}

		else if (report_code == 3005) {

			$("#fromDate").removeAttr("disabled");
			document.getElementById('fromDatediv').style.display = '';

			$("#toDate").removeAttr("disabled");
			document.getElementById('toDatediv').style.display = '';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").removeAttr("disabled");
			document.getElementById('licenseTypediv').style.display = '';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';

		}

		else if (report_code == 3006) {

			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';
			$("#licenseType").removeAttr("disabled");
			document.getElementById('licenseTypediv').style.display = '';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';
			
			
		}

		else if (report_code == 3007) {


			$("#fromDate").removeAttr("disabled");
			document.getElementById('fromDatediv').style.display = '';

			$("#toDate").removeAttr("disabled");
			document.getElementById('toDatediv').style.display = '';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").removeAttr("disabled");
			document.getElementById('licenseTypediv').style.display = '';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';
		}

		else if (report_code == 3008) {

			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';

		}

		else if (report_code == 3009) {


			$("#fromDate").removeAttr("disabled");
			document.getElementById('fromDatediv').style.display = '';

			$("#toDate").removeAttr("disabled");
			document.getElementById('toDatediv').style.display = '';

			$("#vehicleType").removeAttr("disabled");
			document.getElementById('vehicleTypediv').style.display = '';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';

		}

		else if (report_code == 3010) {
			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';
			
			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';
		}

		else if (report_code == 3011) {


			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';
			
			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';
		}

		else if (report_code == 3012) {

			$("#fromDate").removeAttr("disabled");
			document.getElementById('fromDatediv').style.display = '';

			$("#toDate").removeAttr("disabled");
			document.getElementById('toDatediv').style.display = '';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").removeAttr("disabled");
			document.getElementById('genderdiv').style.display = '';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';
		}

		else if (report_code == 3013) {

			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';
		}

		else if (report_code == 3014) {

			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';
		}

		else if (report_code == 3015) {

			$("#fromDate").removeAttr("disabled");
			document.getElementById('fromDatediv').style.display = '';

			$("#toDate").removeAttr("disabled");
			document.getElementById('toDatediv').style.display = '';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").removeAttr("disabled");
			document.getElementById('genderdiv').style.display = '';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';
			
		} else if (report_code == 3016) {


			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").removeAttr("disabled");
			document.getElementById('applicantIddiv').style.display = '';
			
		} else if (report_code == 3017) {
			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").removeAttr("disabled");
			document.getElementById('applicantIddiv').style.display = '';
			
		} else if (report_code == 3018) {

			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';
			
		} else if (report_code == 3019) {

			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").removeAttr("disabled");
			document.getElementById('applicantIddiv').style.display = '';
			
			
		} else if (report_code == 3020) {


			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").removeAttr("disabled");
			document.getElementById('applicantIddiv').style.display = '';
			
		} else if (report_code == 3021) {
			$("#fromDate").removeAttr("disabled");
			document.getElementById('fromDatediv').style.display = '';

			$("#toDate").removeAttr("disabled");
			document.getElementById('toDatediv').style.display = '';

			$("#licenseClass").removeAttr("disabled");
			document.getElementById('licenseClassdiv').style.display = '';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';
			
		} 
		else if (report_code == 3022) {
			$("#fromDate").removeAttr("disabled");
			document.getElementById('fromDatediv').style.display = '';

			$("#toDate").removeAttr("disabled");
			document.getElementById('toDatediv').style.display = '';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';
			
		} 
		
		
		else if (report_code == 3023) {


			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';
			
		} 
		
		else if (report_code == 3024) {


			$("#fromDate").removeAttr("disabled");
			document.getElementById('fromDatediv').style.display = '';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';
			
		} 
		
		else if (report_code == 3025) {


			$("#fromDate").removeAttr("disabled");
			document.getElementById('fromDatediv').style.display = '';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';
			
		} 
		else {


			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#vehicleType").attr("disabled", "disabled");
			document.getElementById('vehicleTypediv').style.display = 'none';

			$("#gender").attr("disabled", "disabled");
			document.getElementById('genderdiv').style.display = 'none';

			$("#licenseType").attr("disabled", "disabled");
			document.getElementById('licenseTypediv').style.display = 'none';

			$("#licenseClass").attr("disabled", "disabled");
			document.getElementById('licenseClassdiv').style.display = 'none';

			$("#applicantId").attr("disabled", "disabled");
			document.getElementById('applicantIddiv').style.display = 'none';
			
		}
	}
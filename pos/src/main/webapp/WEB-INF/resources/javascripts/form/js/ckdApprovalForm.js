window.onload = load;	


function load() {
		document.getElementById('reportCode').focus();
	}

	function selectReport() {

		var report_code = $("#reportCode").val();
		
		//alert(report_code)

		if (report_code == 6001) {

			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#ckdId").removeAttr("disabled");
			document.getElementById('ckdIddiv').style.display = '';

			$("#TypeApprovalId").attr("disabled", "disabled");
			document.getElementById('TypeApprovalIddiv').style.display = 'none';

		} else if (report_code == 6002) {


			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#ckdId").attr("disabled", "disabled");
			document.getElementById('ckdIddiv').style.display = 'none';

			$("#TypeApprovalId").removeAttr("disabled");
			document.getElementById('TypeApprovalIddiv').style.display = '';
			
		} else if (report_code == 6003) {

			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#ckdId").attr("disabled", "disabled");
			document.getElementById('ckdIddiv').style.display = 'none';

			$("#TypeApprovalId").removeAttr("disabled");
			document.getElementById('TypeApprovalIddiv').style.display = '';
			
		} else if (report_code == 6004) {
			
			$("#fromDate").removeAttr("disabled");
			document.getElementById('fromDatediv').style.display = '';

			$("#toDate").removeAttr("disabled");
			document.getElementById('toDatediv').style.display = '';

			$("#ckdId").attr("disabled", "disabled");
			document.getElementById('ckdIddiv').style.display = 'none';

			$("#TypeApprovalId").attr("disabled", "disabled");
			document.getElementById('TypeApprovalIddiv').style.display = 'none';
		}

		else {

			$("#fromDate").attr("disabled", "disabled");
			document.getElementById('fromDatediv').style.display = 'none';

			$("#toDate").attr("disabled", "disabled");
			document.getElementById('toDatediv').style.display = 'none';

			$("#ckdId").attr("disabled", "disabled");
			document.getElementById('ckdIddiv').style.display = 'none';

			$("#TypeApprovalId").attr("disabled", "disabled");
			document.getElementById('TypeApprovalIddiv').style.display = 'none';
		}
	}
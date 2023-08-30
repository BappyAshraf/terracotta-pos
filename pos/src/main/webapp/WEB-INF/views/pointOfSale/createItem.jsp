<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<header class="page-header">
	<!-- <h2>Personal Identification</h2> -->

	<div class="left-wrapper pull-left">
		<ol class="breadcrumbs">
			<li><a href="/pos/"> &nbsp;<i class="fa fa-home"></i></a></li>
			<li><span>Point Of Sale &nbsp;</span></li>
			<li><span>Create Item &nbsp;</span></li>
		</ol>
	</div>
</header>



<!-- start: page -->

<div class="row">
	<div class="col-lg-12">

		<!-- message -->

		<c:if test="${!empty message}">
			<div
				class="alert
						<c:choose>
						<c:when test="${mCode == '0000'}"> <!-- false -->
						alert-danger 
						</c:when>
						<c:when test="${mCode == '1111'}"> <!-- true -->
						alert-success
						</c:when>
						<c:otherwise>
						alert-primary
						</c:otherwise>
						</c:choose>
						
						">

				<button class="close" aria-hidden="true" data-dismiss="alert"
					type="button">&times;</button>
				<strong>${message}</strong>
			</div>
		</c:if>

		<!-- message -->

		<form class="form-horizontal form-bordered" method="post" autocomplete="off"
			action="/pos/pointOfSale/saveItem" id="form"
			onkeypress="return event.keyCode != 13;">
			<section class="panel panel-featured panel-featured-primary">
				<header class="panel-heading" style="padding: 7px; margin: 0px">
					<h2 class="panel-title">Create Item</h2>
				</header>

				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group col-md-6">
								<label class="col-md-6 control-label" for="itemName">Item
									Name <span class="required"> * </span> </label>
								<div class="col-md-6">
									<input type="hidden" name="encItemId" id="encItemId"
										value="${pointOfSale.encItemId}"> <input type="text"
										required class="form-control mandatory" name="itemName"
										id="itemName" value="${pointOfSale.itemName}">

								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="col-md-5 control-label" for="itemPrice">Price
								<span class="required"> * </span> </label>
								<div class="col-md-6">
									<input type="text" required class="form-control mandatory"
										name="itemPrice" id="itemPrice"
										value="${pointOfSale.itemPrice}"
										onkeypress="return isNumberKey(event)" />
										
										<div id="erritemPrice"> </div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<footer class="panel-footer">
					<div class="row">
						<div class="col-sm-offset-5">
							<button class="btn btn-sm btn btn-animate btn-animate-side btn btn-primary" 
							type="submit"> <i class="fa fa-save"> </i> Save</button>
							<a href="createItem"><button
									class="btn btn-sm btn  btn-default" type="button" role="button">
									<i class="fa fa-refresh"> </i> Clear</button></a>
						</div>

					</div>
				</footer>


			</section>
		</form>
	</div>
		<div class="col-lg-12">
		<form class="form-horizontal form-bordered" id="form"
			autocomplete="off" action="/pos/pointOfSale/saveOrder" method="post">

			<!-- start: page -->
			<section class="panel panel-featured panel-featured-primary">
				<header class="panel-heading">
					<h2 class="panel-title">Item List</h2>
				</header>

				<div class="panel-body">
					<div class="row">
						<div class="table-responsive">
							<table id="datatable-default"
								class="table table-striped table-condensed table-hover mb-none">
								<thead>
									<tr>
										<th>#</th>
										<th style="text-align: center;">Item Code</th>
										<th>Item Name</th>
										<th style="text-align: center;">Item Price</th>
										<th style="text-align: center;">Edit</th>
									</tr>
								</thead>
								<tbody>
									<%
										int i = 1;
									%>
									<c:if test="${!empty itemList}">

										<c:forEach items="${itemList}" var="list">
											<tr>
												<td>
													<%
														out.print(i);
																i++;
													%>
												</td>
												<td style="text-align: center;">${list.itemCode}</td>
												<td>${list.itemName}</td>
												<td style="text-align: center;">${list.itemPrice}</td>
												<td style="text-align: center;">
													<button type="button" class="btn btn-primary"
														onclick="getItemInfo('${list.encItemId}','${list.itemName}','${list.itemPrice}')">
														<i class="fa fa-edit"></i>
													</button>
												</td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${! empty pendingOrderListNotFound}">
										<tr>
											<td colspan="6"><p>${itemListNotFound}</p></td>
											<td style="display: none;"></td>
											<td style="display: none;"></td>
											<td style="display: none;"></td>
											<td style="display: none;"></td>
											<td style="display: none;"></td>
										</tr>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>


				<footer class="panel-footer">
					<div class="row"></div>
				</footer>

			</section>

		</form>
		<!-- end: page -->

	</div>
</div>

<script>
function getItemInfo(encItemId,itemName,itemPrice) {
	if (encItemId != '') {
		$("#encItemId").val(encItemId);
		$("#itemName").val(itemName);
		$("#itemPrice").val(itemPrice);
		document.getElementById('itemName').readOnly = true;
	}
};

</script>

<!-------------------------------- start modal vehicle class ------------------------------------------>

<div id="modalItemList"
	class="zoom-anim-dialog modal-block modal-block-sm mfp-hide">
	<!-- class="zoom-anim-dialog modal-block modal-block-lg mfp-hide"> -->
	<section class="panel">
		<header class="panel-heading">
			<button class="close modal-dismiss" type="button"
				id="itemListClose">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<h2 class="panel-title">Item List</h2>
		</header>

		<div class="panel-body">

			<div class="row">
				<div class="col-md-12">
					<div style="overflow: scroll; max-height: 300px;">

						<table
							class="table table-striped table-condensed table-hover mb-none ">
							<thead>
								<tr>
									<th>Item Code</th>
									<th>Item Name</th>
									<th>Price</th>
								</tr>
							</thead>
							<tbody id="itemList" style="border-style: inset;">
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>


<!-------------------------------- end modal vehicle class ------------------------------------------>



<script
	src="<c:url value="/resources/javascripts/googleapi.jquery.min.js" />"></script>
<script>

function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    var errid = "err"+evt.target.id;
    var msg = '<label class="error" for="'+evt.target.id+'">Please enter a valid number.</label>';
    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
    	$("#"+errid).html(msg);
        return false;
    } else {
    	$("#"+errid).html('');
        return true;
    }      
}

function getItemList() {

	var link = "/pos/pointOfSale/getItemList";

	$.ajax({
		type : "POST",
		url : link,
		async : true,

		success : function(data) {
			//alert('ssssss!!!')
			$("#itemList").html(data);
		},

		error : function(data) {
			alert('Error!!!')
		}
	});
};

function selectItemList(encItemId,itemName,itemPrice) {
	$("#encItemId").val(encItemId);
	$("#itemName").val(itemName);
	$("#itemPrice").val(itemPrice);
	$("#itemListClose").click();
};
 function isNumber(evt,elem) {
	// var msg = "<label for='"+elem.id+"' class='error'>Please enter a valid number.</label>";
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
    	
    	//$(msg).insertAfter(elem);
    	//alert(elem.id)
    			return false;
    }
    return true;
}
 



function updateSelectOptions(link, parentSelectElementId,
		childSelectElementId) {
	var parentSelectElement = $("#" + parentSelectElementId).val();

	//alert('fff');
	//alert(parentSelectElement);
	if (parentSelectElement == '') {
		$("#" + childSelectElementId + "option").remove();
		$("#" + childSelectElementId).val('');
		var content = '<option value="">Please Select One</option>';
		$('#' + childSelectElementId).html(content);
		//$("#select2-roleId-container").html('Please Select One');
	} else {

		//alert(parentSelectElement);
		//alert(childSelectElementId);

		$.ajax({
			type : "get",
			url : link,
			data : "searchId=" + parentSelectElement,
			async : true,
			success : function(data) {
			//	alert('success');
				$("#" + childSelectElementId).html(data);
				
			}

		});
		
	

		$("#" + childSelectElementId).focus();
	}
};

/* 
function copyTextValue(v) {
	alert("lkjkl");
	
	  if(v.village.checked == true) {
		  alert('village');
		  
		 v.peCountryId.value = v.prCountryId.value;
		  v.peStreet.value = v.prStreet.value;
		  v.peVillage.value = v.prVillage.value;
		  v.peDivisionId.value = v.prDivisionId.value;
		 v.peDistrictId.value = v.prDistrictId.value;
		 v.peThanaId.value = v.prThanaId.value;
		  v.pePostCode.value = v.prPostCode.value;
		  v.peFax.value = v.prFax.value;
		  v.peTelephone.value = v.prTelephone.value;
		  
		  alert(v.peCountryId.value);
	  }
	}
 */


/* function copyTextValue(village) {

	var peCountryId = document.getElementById("peCountryId").value;
	var peStreet = document.getElementById("peStreet").value;
	var peVillage = document.getElementById("peVillage").value;
	var peDivisionId = document.getElementById("peDivisionId").value;
	var peDistrictId = document.getElementById("peDistrictId").value;
	var peThanaId = document.getElementById("peThanaId").value;
	var pePostCode = document.getElementById("pePostCode").value;
	var peFax = document.getElementById("peFax").value;
	var peTelephone = document.getElementById("peTelephone").value;
	
	
	
	
	
	if (village.checked) {
		$('#prCountryId').attr('disabled', 'disabled');
	 }
	else {
		peCountryId = '';
		$('#prCountryId').removeAttr('disabled');
	}
	
	if (village.checked) {
		$('#prStreet').attr('disabled', 'disabled');
	 }
	else {
		peStreet = '';
		$('#prStreet').removeAttr('disabled');
	}
	
	if (village.checked) {
		$('#prVillage').attr('disabled', 'disabled');
	 }
	else {
		peVillage = '';
		$('#prVillage').removeAttr('disabled');
	}
	
	if (village.checked) {
		$('#prDivisionId').attr('disabled', 'disabled');
	 }
	else {
		peDivisionId = '';
		$('#prDivisionId').removeAttr('disabled');
	}
	
	if (village.checked) {
		$('#prDistrictId').attr('disabled', 'disabled');
	 }
	else {
		peDistrictId = '';
		$('#prDistrictId').removeAttr('disabled');
	}
	
	if (village.checked) {
		$('#prThanaId').attr('disabled', 'disabled');
	 }
	else {
		peThanaId = '';
		$('#prThanaId').removeAttr('disabled');
	}
	if (village.checked) {
		$('#prPostCode').attr('disabled', 'disabled');
	 }
	else {
		pePostCode = '';
		$('#prPostCode').removeAttr('disabled');
	}
	if (village.checked) {
		$('#prFax').attr('disabled', 'disabled');
	 }
	else {
		peFax = '';
		$('#prFax').removeAttr('disabled');
	}
	if (village.checked) {
		$('#prTelephone').attr('disabled', 'disabled');
	 }
	else {
		peTelephone = '';
		$('#prTelephone').removeAttr('disabled');
	}
	
	document.getElementById("prCountryId").value.hidden = peCountryId;
	document.getElementById("prStreet").value.hidden = peStreet;
	document.getElementById("prVillage").value.hidden = peVillage;
	document.getElementById("prDivisionId").value.hidden = peDivisionId;
	document.getElementById("prDistrictId").value.hidden = peDistrictId;
	document.getElementById("prThanaId").value.hidden = peThanaId;
	document.getElementById("prFax").value.hidden = peFax;
	document.getElementById("prTelephone").value.hidden = peTelephone;

};
 */
 $(document).ready( function() {
		
		$('#genderId option[value='+ ${personalInfo.genderId}  +']').prop("selected",true);
		$('#nationalityId option[value='+ ${personalInfo.nationalityId}  +']').prop("selected",true);
		$('#bloodId option[value='+ ${personalInfo.bloodId}  +']').prop("selected",true);
		$('#religionId option[value='+ ${personalInfo.religionId}  +']').prop("selected",true);
		$('#peCountryId option[value='+ ${personalInfo.peCountryId}  +']').prop("selected",true);
		$('#peDivisionId option[value='+ ${personalInfo.peDivisionId}  +']').prop("selected",true);
		$('#peJuridictionId option[value='+ ${personalInfo.peJuridictionId}  +']').prop("selected",true);
		$('#peThanaId option[value='+ ${personalInfo.peThanaId}  +']').prop("selected",true);
		$('#prDivisionId option[value='+ ${personalInfo.prDivisionId}  +']').prop("selected",true);
		$('#prCountryId option[value='+ ${personalInfo.prCountryId}  +']').prop("selected",true);
		$('#prJuridictionId option[value='+ ${personalInfo.prJuridictionId}  +']').prop("selected",true);
		$('#prThanaId option[value='+ ${personalInfo.prThanaId}  +']').prop("selected",true);
		
		var encEmpId = $("#encPersonId1").val();
		
		$('#personId').focus();
		if(encEmpId != ''){
			getEmpBranchList(encEmpId);
			$("#savePersonalInfo").html("Update");
		}
	});

function getPersonList() {
	var personId = $("#personId2").val();
	var personName = $("#personName2").val();
	var fathersName = $("#fathersName2").val();
	var dateOfBirth = $("#dateOfBirth2").val();
	var requiredfield = "Minimum One Value of Criteria is Required!!!";

	if (personId == '' && personName == '' && fathersName == ''
			&& dateOfBirth == '') {
		$("#registrationModalmsg").html(
				'<span style="color:red">' + requiredfield + '<span>');
		alert('Minimum One Value of Criteria is Required!!!');
	} else {

		var link = "/brtais/personalInfo/getPersonList";
		$.ajax({
			type : "POST",
			url : link,
			data : "personId=" + personId + "&personName="
					+ personName + "&fathersName=" + fathersName
					+ "&dateOfBirth=" + dateOfBirth,

			/* {"${_csrf.parameterName}":"${_csrf.token}"}, */

			async : true,

			success : function(data) {
				// alert('success')
				$("#personList").html(data);
			},
			error : function(data) {
				alert('Error!!!')
			}
		});

	}
}





</script>


<!-- end: page -->
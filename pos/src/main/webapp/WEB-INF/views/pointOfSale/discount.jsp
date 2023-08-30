<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<header class="page-header">
	<!-- <h2>Personal Identification</h2> -->

	<div class="left-wrapper pull-left">
		<ol class="breadcrumbs">
			<li><a href="/pos/"> &nbsp;<i class="fa fa-home"></i></a></li>
			<li><span>Point Of Sale &nbsp;</span></li>
			<li><span>Discount &nbsp;</span></li>
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
			action="/pos/pointOfSale/saveDiscount" id="form"
			onkeypress="return event.keyCode != 13;">
			<section class="panel panel-featured panel-featured-primary">
			
				<header class="panel-heading">
					<div class="panel-actions">
						<a href="/pos/pointOfSale/discountView"><button
								class="btn btn-primary btn-round" type="button">
								<span><i class="fa fa-arrow-left" aria-hidden="true"></i>
									Back</span>
							</button></a>
					</div>
					<h2 class="panel-title">Discount</h2>
				</header>

				<div class="panel-body">
					<div class="col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-6 control-label" for="personId">Order
								No <span class="required"> * </span> </label>
							<div class="col-md-6">
								<input type="text" required class="form-control mandatory"
									name="orderId" id="orderId" value="${pointOfSale.orderId}"
									onblur="getOrderPrice()"
									onkeypress="return isNumberKey(event)" />
											
									<div id="errorderId"> </div>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label class="col-md-6 control-label" for="nationalityId">Price</label>
							<div class="col-md-6">
								<input type="text" class="form-control mandatory" readonly
									name="orderPrice" id="orderPrice"
									value="${pointOfSale.orderPrice}"/>
											
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group col-md-6">
							<label class="col-md-6 control-label" for="personId">Discount
								Amount <span class="required"> * </span></label>
							<div class="col-md-6">
								<input type="text" required class="form-control mandatory"
									name="discountAmount" id="discountAmount"
									value="${pointOfSale.discountAmount}"
									onkeypress="return isNumberKey(event)" />
											
									<div id="errdiscountAmount"> </div>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label class="col-md-6 control-label"> Reference By 
							<span class="required"> * </span> </label>
							<div class="form-group">
								<div class="col-md-6">
									<select data-plugin-selectTwo class="form-control" required
										id="referenceById" name="referenceById">
										<option value="">Select One</option>
										<c:forEach items="${employeeList}" var="list">
											<option <c:if test="${list.employeeId==pointOfSale.referenceById}">selected="selected"</c:if>
											value="${list.employeeId}">${list.firstName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<footer class="panel-footer">
					<div class="row">
						<div class="col-sm-offset-6">
						<input type="hidden" value="${pointOfSale.encDiscountId}"
								id="encDiscountId" name="encDiscountId">
							<button class="btn btn-primary" type="submit">
							<c:choose>
									<c:when test="${pointOfSale.encDiscountId != null}">
							<i class="fa fa-save"></i> Update
							</c:when>
									<c:otherwise>
							<i class="fa fa-save"></i> Save
							</c:otherwise>
								</c:choose>
							
							</button>
							<a href="discount"><button
									class="btn btn-default" type="button" role="button">
									<i class="fa fa-refresh"></i> Clear</button></a>
						</div>

					</div>
				</footer>


			</section>
		</form>



	</div>
</div>

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
	 
	 

function getOrderPrice () {
var orderId = $("#orderId").val();
	//var link = "/pos/pointOfSale/getOrderPrice";

	$.ajax({
		type : "POST",
		url : "/pos/pointOfSale/getOrderPrice",
		data : "orderId=" + orderId,
		async : true,

		success : function(data) {
			//alert(data.itemPrice);
			//alert('ssssss!!!')
			$("#orderPrice").val(data.itemPrice);
		},

		error : function(data) {
			alert('Error!!!')
		}
	});
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

/* function selectItemList(encItemId,itemName,itemPrice) {
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
} */
 



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
/*  $(document).ready( function() {
		
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
 */
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
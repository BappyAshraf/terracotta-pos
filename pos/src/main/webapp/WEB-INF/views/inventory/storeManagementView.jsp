<%@page import="org.apache.taglibs.standard.tag.common.xml.IfTag"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="col-md-12" var="regCol" />
<c:url value="col-md-6" var="regLabCol" />
<c:url value="col-md-6" var="regFCol" />

<c:url value="col-md-4" var="vehCol" />
<c:url value="col-md-6" var="vehLabCol" />
<c:url value="col-md-6" var="vehFCol" />


<c:url value="col-md-6" var="ownCol" />
<c:url value="col-md-2" var="recCol" />

<!-- Header start Here -->

<header class="page-header">

	<div class="left-wrapper pull-left">
		<ol class="breadcrumbs">
			<li><a href="/pos/"> &nbsp;<i class="fa fa-home"></i>
			</a></li>
			<li><span>Inventory</span></li>
			<li><span>Store Management View</span></li>
		</ol>
	</div>

</header>

<!-- Header end Here -->

<!-- message -->
<div class="col-lg-12" id="msg"></div>

<c:if test="${!empty message}">
	<div class="col-lg-12">
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

	</div>
</c:if>

<!-- message -->

<!-- start: page -->
<form class="form-horizontal form-bordered" method="post" autocomplete="off"
	action="getProductView">

	<div class="row">
		<section class="panel panel-featured panel-featured-primary">
			<div class="col-lg-12">
				<header class="panel-heading">
				<div class="panel-actions">
						<a href="/pos/inventory/storeManagement"><button
								class="btn btn-primary btn-round" type="button">
								<span><i class="fa fa-plus" aria-hidden="true"></i> Add Ingredients</span>
							</button></a>
					</div>
					<h2 class="panel-title">Stored Ingredients List</h2>
				</header>

				<div class="panel-body">
					<div class="col-lg-12">

						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-6 control-label" for="inventoryTypeId">Inventory
									Type</label>
								<div class="col-md-6">
									<select data-plugin-selectTwo class="form-control"
										name="inventoryTypeId" id="inventoryTypeId">
										<option value="">Please Select One</option>
										<c:forEach items="${inventoryTypeList}" var="list">
											<option
												<c:if test="${list.inventoryTypeId == inventory.inventoryTypeId}"> selected="selected"</c:if>
												value="${list.inventoryTypeId}">${list.inventoryTypeName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>



						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-6 control-label" for="registrationTypeId">Employee</label>
								<div class="col-md-6">
									<select data-plugin-selectTwo class="form-control"
										id="employeeId" name="employeeId">
										<option value="">Select One</option>
										<c:forEach items="${employeeList}" var="list">
											<option <c:if test="${list.employeeId==inventory.employeeId}">selected="selected"</c:if>
											value="${list.employeeId}">${list.firstName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-6 control-label" for="vehicleClassId">Ingredient
									Name</label>
								<div class="col-md-6">
									<select data-plugin-selectTwo class="form-control"
										name="productId" id="productId">
										<option value="">Please Select One</option>
										<c:forEach items="${productList}" var="list">
											<option
												<c:if test="${list.productId==inventory.productId}">selected="selected"</c:if>
												value="${list.productId}">${list.productName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>



						<div class="col-md-6">
							<div class="form-group">
								<label class="col-md-6 control-label" for="updateDate">Date</label>
								<div class="col-md-6">
									<input class="form-control" type="text" data-plugin-datepicker
										id="updateDate" data-plugin-masked-input=""
										data-input-mask="99/99/9999" placeholder="DD/MM/YYYY"
										name="updateDate" value="${inventory.updateDate}">
								</div>
							</div>
						</div>

					</div>

					<br />
					<div class="col-lg-12">
						<div class="col-sm-5 col-sm-offset-0">
							<button class="btn btn-sm btn btn-animate btn-animate-side btn btn-primary"
								type="submit">
								<i class="fa fa-search"> </i> 
								Search</button>
							<!-- <button class="mb-xs mt-xs mr-xs btn btn-xs btn-primary"
										type="button" onclick="clearProductView()">Clear</button> -->
							<a href="storeManagementView">
								<button class="btn btn-sm btn  btn-default"
									type="button">
									<i class="fa fa-refresh"> </i> Clear</button>
							</a>
						</div>
						<%-- <div class="col-sm-offset-10">
									<c:if test="${productViewListSize > 0}">
										<p id="listSize">
											Total <strong style="color: red;">${productViewListSize}</strong>
											Record Found
										</p>
									</c:if>
								</div> --%>
					</div>
				</div>





				<div class=" panel-body">

					<p
						<c:if test="${empty productViewListSize}">
					style="display: none"
					</c:if>>
						Total Record Found : <strong><span style="color: red">${productViewListSize}</span></strong>

					</p>

					<div class="table-responsive">
						<div style="overflow: scroll; max-height: 277px;">
							<table
								class="table table-striped table-condensed table-hover mb-none">
								<thead>
									<tr style="border-style: inset;">
										<th style="border-style: inset;">#</th>
										<th style="border-style: inset;">Inventory Type</th>
										<th style="border-style: inset;">Ingredient Name</th>
										<th style="border-style: inset;">Quantity</th>
										<th style="border-style: inset;">Date</th>
										<th style="border-style: inset;">Shop By</th>
									</tr>
								</thead>
								<tbody id="classOneStudentList">
									<c:if test="${!empty productViewList}">
										<%
											int i = 1;
										%>
										<c:forEach items="${productViewList}" var="list">
											<tr style="cursor: pointer">
												<td style="border-style: inset;">
													<%
														out.print(i);
																i++;
													%>
												</td>
												<td style="border-style: inset;">${list.inventoryTypeName}</td>
												<td style="border-style: inset;">${list.productName}</td>
												<td style="border-style: inset;">${list.updateDate}</td>
												<td style="border-style: inset;">${list.price}</td>
												<td style="border-style: inset;">${list.employeeId}</td>
												<td style="border-style: inset;"><a href='#'
													onclick='removetr(this)'><button
															class="btn btn-xs btn-primary" type="button">&times;
														</button></a></td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${! empty productViewListNotFound}">
										<tr>
											<td colspan="6"><p>${productViewListNotFound}</p></td>
										</tr>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<footer class="panel-footer">
					<!--  style="margin:0px" -->
					<!-- <div class="row">
						<div class="col-sm-5 col-sm-offset-0">
							<a href="teacherInfoView">
								<button class="btn btn-primary" type="button">
									<i class="fa fa-trash-o"></i> Clear
								</button>
							</a> <a href="/sms/">
								<button class="btn btn-primary" type="button">
									<i class="fa fa-undo"></i> Cancel
								</button>
							</a>
						</div>
					</div> -->
				</footer>
			</div>
		</section>
	</div>
</form>

<script>
	function teacherDetailsView(encTeacherId) {
		//alert(encStudentId);
		var url = "/sms/teacher/getTeacherDetailsView?encTeacherId="
				+ encodeURIComponent(encTeacherId);
		//alert(url);
		window.location = url;
	}

	function printWindow() {
		window.print();
	}

	function caps(element) {
		element.value = element.value.toUpperCase();
	}

	/* Start Clear Button Function */
	function clearRegistrationAll() {

		/* $("#registrationId").val('');
		$("#applicationStatus").val('');
		$("#currentAuthority").val('');
		$("#registrationNo").val('');
		$("#registrationDate").val('');
		$("#previousRegistrationNo").val(''); */
		$("#certificateIssueNo").val('');
		/* $("#certificateIssueDate").val(''); */
		$("#certificateIssuedBy").val('');
		$("#diaryNo").val('');
		/* $("#diaryDate").val(''); */
		$("#receivedBy").val('');
		$("#callDate").val('');
		/* $("#refusalCode").val(''); */
		/* $("#taxcessionCode").val(''); */
		/* $("#surrenderTypeId").val(''); */
		/* $("#registaringArea").val(''); */
		/* $("#hire").val(''); */
		$("#specialMark").val('');
		$("#paymentOfficeCode").val('');
		/* $("#ownershipTypeName").val(''); */
		$("#remarks").val('');
	}

	function clearVehicleDetailsAll() {

		$("#vehicleId").val('');
		$("#chassisNo").val('');
		$("#vehClassId").val('');
		$("#engineNo").val('');
		$("#vehicleTypeId").val('');
		$("#manufacturerId").val('');
		$("#cbuSkdCkd").val('');
		$("#countryId").val('');
		$("#colour").val('');
		$("#manufactureYear").val('');
		$("#noOfCylinder").val('');
		$("#fuelTypeId").val('');
		$("#horsePower").val('');
		$("#rpm").val('');
		$("#cc").val('');
		$("#noOfSeats").val('');
		$("#standee").val('');
		$("#wheelBase").val('');
		$("#unladenWeight").val('');
		$("#maxWeight").val('');
		$("#airConditioned").val('');

		$("#noOfTyres").val('');
		$("#tyreSize").val('');
		$("#noOfAxle").val('');
		$("#frontAxle2").val('');
		$("#centralAxle1").val('');
		$("#centralAxle2").val('');
		$("#centralAxle3").val('');
		$("#centralAxle4").val('');
		$("#centralAxle5").val('');
		$("#centralAxle6").val('');
		$("#rearAxle3").val('');
		$("#length").val('');
		$("#width").val('');
		$("#height").val('');
		$("#overhangsFront").val('');
		$("#overhangsRear").val('');
		$("#overhangsOther").val('');
		$("#remarks2").val('');
		$("#specApprovedBy").val('');
		$("#specApprovedDate").val('');
	}

	function clearProductView() {
//alert('hekp');
		//$('#inventoryTypeId').html('');
		$('#inventoryTypeList').html('');
		$('#productName').val('');
		$('#employeeId').val('');
		$('#updateDate').val('');
		//$("#ownerInfoSList").html('');

	}

	function clearReceiptAll() {

		$("#receiptId").val('');
		$("#msg").html('');
		$("#receiptInfoList").html('');
	}

	/* End Clear Button Function */

	/* Start Next and Back Button Validation Tab */

	function nextTab(tab) {
		$('#' + tab).click();
	}

	/* Start Registration Validation Tab */
	function registrationNextTab(tab) {
		var callDate = $("#callDate").val();
		var taxcessionCode = $("#taxcessionCode").val();
		var paymentOfficeCode = $("#paymentOfficeCode").val();

		var errorMsg = 'This field is required...';

		var callDateValid = "";
		var taxcessionCodeValid = "";
		var paymentOfficeCodeValid = "";

		if (callDate == '') {
			$('#errCallDate').html(errorMsg);
			$('#errCallDate').show();
			$('#callDate').focus();
			callDateValid = "N";
		} else {
			$('#errCallDate').html('');
			$('#errCallDate').hide();
			callDateValid = "Y";
		}

		if (taxcessionCode == '') {
			$('#errtaxcessionCode').html(errorMsg);
			$('#errtaxcessionCode').show();
			$('#taxcessionCode').focus();
			taxcessionCodeValid = "N";
		} else {
			$('#errtaxcessionCode').html('');
			$('#errtaxcessionCode').hide();
			taxcessionCodeValid = "Y";
		}

		if (paymentOfficeCode == '') {
			$('#errPaymentOfficeCode').html(errorMsg);
			$('#errPaymentOfficeCode').show();
			$('#paymentOfficeCode').focus();
			paymentOfficeCodeValid = "N";
		} else {
			$('#errPaymentOfficeCode').html('');
			$('#errPaymentOfficeCode').hide();
			paymentOfficeCodeValid = "Y";
		}

		if (callDateValid == "Y" && taxcessionCodeValid == "Y"
				&& paymentOfficeCodeValid == "Y") {
			$("#valid").val("Y");
		} else {
			$("#valid").val("N");
		}

		var valid = $("#valid").val();

		//alert(valid);

		if (valid == "Y") {
			$("#valid").val("N");
			$('#errCallDate').html('');
			$('#errtaxcessionCode').html('');
			$('#errPaymentOfficeCode').html('');
			$('#' + tab).click();
		}
	}

	/* End Registration Validation Tab */

	/* Start Vehicle Details Validation Tab */

	function vehicleDetailNextTab(tab) {
		var chassisNo = $("#chassisNo").val();
		var vehClassId = $("#vehClassId").val();
		var engineNo = $("#engineNo").val();
		var vehicleTypeId = $("#vehicleTypeId").val();
		var manufacturerId = $("#manufacturerId").val();
		var countryId = $("#countryId").val();
		var colour = $("#colour").val();
		var manufactureYear = $("#manufactureYear").val();
		var fuelTypeId = $("#fuelTypeId").val();
		var specApprovedBy = $("#specApprovedBy").val();
		var specApprovedDate = $("#specApprovedDate").val();

		var errorMsg = 'This field is required...';

		var chassisNoValid = "";
		var vehClassIdValid = "";
		var engineNoValid = "";
		var vehicleTypeIdValid = "";
		var manufacturerIdValid = "";
		var countryIdValid = "";
		var colourValid = "";
		var manufactureYearValid = "";
		var fuelTypeIdValid = "";
		var specApprovedByValid = "";
		var specApprovedDateValid = "";

		if (chassisNo == '') {
			$('#errChassisNo').html(errorMsg);
			$('#errChassisNo').show();
			$('#chassisNo').focus();
			chassisNoValid = "N";
		} else {
			$('#errChassisNo').html('');
			$('#errChassisNo').hide();
			chassisNoValid = "Y";
		}

		if (vehClassId == '') {
			$('#errVehClassId').html(errorMsg);
			$('#errVehClassId').show();
			$('#vehClassId').focus();
			vehClassIdValid = "N";
		} else {
			$('#errVehClassId').html('');
			$('#errVehClassId').hide();
			vehClassIdValid = "Y";
		}

		if (engineNo == '') {
			$('#errEngineNo').html(errorMsg);
			$('#errEngineNo').show();
			$('#engineNo').focus();
			engineNoValid = "N";
		} else {
			$('#errEngineNo').html('');
			$('#errEngineNo').hide();
			engineNoValid = "Y";
		}

		if (vehicleTypeId == '') {
			$('#errVehicleTypeId').html(errorMsg);
			$('#errVehicleTypeId').show();
			$('#vehicleTypeId').focus();
			vehicleTypeIdValid = "N";
		} else {
			$('#errVehicleTypeId').html('');
			$('#errVehicleTypeId').hide();
			vehicleTypeIdValid = "Y";
		}

		if (manufacturerId == '') {
			$('#errManufacturerId').html(errorMsg);
			$('#errManufacturerId').show();
			$('#manufacturerId').focus();
			manufacturerIdValid = "N";
		} else {
			$('#errManufacturerId').html('');
			$('#errManufacturerId').hide();
			manufacturerIdValid = "Y";
		}

		if (countryId == '') {
			$('#errCountryId').html(errorMsg);
			$('#errCountryId').show();
			$('#countryId').focus();
			countryIdValid = "N";
		} else {
			$('#errCountryId').html('');
			$('#errCountryId').hide();
			countryIdValid = "Y";
		}

		if (colour == '') {
			$('#errColour').html(errorMsg);
			$('#errColour').show();
			$('#colour').focus();
			colourValid = "N";
		} else {
			$('#errColour').html('');
			$('#errColour').hide();
			colourValid = "Y";
		}

		if (manufactureYear == '') {
			$('#errManufactureYear').html(errorMsg);
			$('#errManufactureYear').show();
			$('#manufactureYear').focus();
			manufactureYearValid = "N";
		} else {
			$('#errManufactureYear').html('');
			$('#errManufactureYear').hide();
			manufactureYearValid = "Y";
		}

		if (fuelTypeId == '') {
			$('#errFuelTypeId').html(errorMsg);
			$('#errFuelTypeId').show();
			$('#fuelTypeId').focus();
			fuelTypeIdValid = "N";
		} else {
			$('#errFuelTypeId').html('');
			$('#errFuelTypeId').hide();
			fuelTypeIdValid = "Y";
		}

		if (specApprovedBy == '') {
			$('#errSpecApprovedBy').html(errorMsg);
			$('#errSpecApprovedBy').show();
			$('#specApprovedBy').focus();
			specApprovedByValid = "N";
		} else {
			$('#errSpecApprovedBy').html('');
			$('#errSpecApprovedBy').hide();
			specApprovedByValid = "Y";
		}

		if (specApprovedDate == '') {
			$('#errSpecApprovedDate').html(errorMsg);
			$('#errSpecApprovedDate').show();
			$('#specApprovedDate').focus();
			specApprovedDateValid = "N";
		} else {
			$('#errSpecApprovedDate').html('');
			$('#errSpecApprovedDate').hide();
			specApprovedDateValid = "Y";
		}

		if (chassisNoValid == "Y" && vehClassIdValid == "Y"
				&& engineNoValid == "Y" && vehicleTypeIdValid == "Y"
				&& manufacturerIdValid == "Y" && countryIdValid == "Y"
				&& colourValid == "Y" && manufactureYearValid == "Y"
				&& fuelTypeIdValid == "Y" && specApprovedByValid == "Y"
				&& specApprovedDateValid == "Y") {
			$("#valid").val("Y");
		} else {
			$("#valid").val("N");
		}

		var valid = $("#valid").val();

		if (valid == "Y") {
			$("#valid").val("N");
			$('#errChassisNo').html('');
			$('#errVehClassId').html('');
			$('#errEngineNo').html('');
			$('#errVehicleTypeId').html('');
			$('#errManufacturerId').html('');
			$('#errCountryId').html('');
			$('#errColour').html('');
			$('#errManufactureYear').html('');
			$('#errFuelTypeId').html('');
			$('#errSpecApprovedBy').html('');
			$('#errSpecApprovedDate').html('');
			$('#' + tab).click();
		}
	}

	/* End Vehicle Details Validation Tab */

	function hideErr(a, b) {
		var val = $("#" + b).val();
		if (val == '') {
			$("#valid").val("N");
			$("#" + a).html('This field is required.');
			$("#" + a).show();
		} else {
			$("#" + a).html('');
			$("#" + a).hide();
		}
	}

	/* End Next and Back Validation Button  Tab */

	/* Start Registration ID Search Function */

	/*CLEAR ONLY MODAL */
	function clearRegistrationModal() {
		$("#ownerId2").val('');
		$("#registrationNo2").val('');
		$("#fathersName2").val('');
		$("#vehicleId2").val('');
		$("#chassisNo2").val('');
		$("#engineNo2").val('');
		$("#registrationModalmsg").html('');
		$("#registrationList").html('');
	}

	function getRegistrationList() {
		var ownerId = $("#ownerId2").val();
		var registrationNo = $("#registrationNo2").val();
		var fathersName = $("#fathersName2").val();
		var vehicleId = $("#vehicleId2").val();
		var chassisNo = $("#chassisNo2").val();
		var engineNo = $("#engineNo2").val();
		var requiredfield = "Minimum One Value of Criteria is Required!!!";

		if (ownerId == '' && registrationNo == '' && fathersName == ''
				&& vehicleId == '' && chassisNo == '' && engineNo == '') {
			$("#registrationModalmsg").html(
					'<span style="color:red">' + requiredfield + '<span>');
			alert('Minimum One Value of Criteria is Required!!!');
		} else {

			var link = "/brtais/vdis/registration/getRegistrationList";
			$.ajax({
				type : "POST",
				url : link,
				data : "vehicleId=" + vehicleId + "&registrationNo="
						+ registrationNo + "&engineNo=" + engineNo
						+ "&chassisNo=" + chassisNo + "&ownerName=" + ownerId
						+ "&fathersName=" + fathersName,

				/* {"${_csrf.parameterName}":"${_csrf.token}"}, */

				async : true,
				/* waiting wheel */
				beforeSend : function() {
					$('#ajxLoader').show();
				},
				complete : function() {
					$("#ajxLoader").hide();
				},

				success : function(data) {
					// alert('success')
					$("#registrationList").html(data);
				},
				error : function(data) {
					alert('Error!!!')
				}
			});

		}
	}

	function getRegistration(id) {
		var url = "/brtais/vdis/registration/registrationNormal/getRegistration?encRegistrationId="
				+ encodeURIComponent(id);
		//alert(url);
		window.location = url;
	}

	function getOwnerList(encRegId) {
		if (!encRegId == '') {

			var link = "/brtais/vdis/registration/getOwnerInfoSList";

			//alert(link);
			$.ajax({
				type : "POST",
				url : link,
				data : "encRegistrationId=" + encodeURIComponent(encRegId),
				async : true,

				success : function(data) {
					//alert('ssssss!!!')
					$("#ownerInfoSList").html(data);
				},

				error : function(data) {
					//alert('Error!!!')
				}
			});
		}
	}

	function getReceiptList(encRegId) {
		if (!encRegId == '') {

			var link = "/brtais/vdis/registration/getReceiptInfoList";

			//alert(link);
			$.ajax({
				type : "POST",
				url : link,
				data : "encRegistrationId=" + encodeURIComponent(encRegId),
				async : true,

				success : function(data) {
					//alert('ssssss!!!')
					$("#receiptInfoList").html(data);
				},

				error : function(data) {
					//alert('Error!!!')
				}
			});
		}
	}

	/* End Registration ID Search Function */

	/* Start Cert. Issued By Search Function */
	function getCertificateIssuedByList1() {

		var link = "/brtais/vdis/registration/getCertificateIssuedByList";

		$.ajax({
			type : "POST",
			url : link,
			async : true,

			success : function(data) {
				//alert('ssssss!!!')
				$("#certificateIssuedByList").html(data);
			},

			error : function(data) {
				alert('Error!!!')
			}
		});
	}

	function selectCertificateIssuedBy(employeeId) {
		$("#certificateIssuedBy").val(employeeId);
		$("#certificateIssuedByListClose").click();
	}

	/* End Cert. Issued By Search Function */

	/* Start Bank Code Search Function */
	function getBankCodeList() {

		var link = "/brtais/vdis/registration/getBankCodeList";

		$.ajax({
			type : "POST",
			url : link,
			async : true,

			success : function(data) {
				//alert('ssssss!!!')
				$("#bankCodeList").html(data);
			},

			error : function(data) {
				alert('Error!!!')
			}
		});
	}

	function selectBankCodeList(employeeId) {
		$("#paymentOfficeCode").val(employeeId);
		$("#bankCodeListClose").click();
		hideErr('errPaymentOfficeCode', 'paymentOfficeCode');
	}

	/* End Bank Code Search Function */

	/* Vehicle Details getRecordEngine Function Start */
	function getRecordByEngine() {
		var link = "/brtais/vdis/registration/registrationNormal/getRecordByEngine";
		var engin = $("#engineNo").val();
		$.ajax({
			type : "POST",
			url : link,
			async : true,
			data : "engineNo=" + engin,

			success : function(data) {
				$("#vehicleId").val(data.vehicleId);
				$("#chassisNo").val(data.chassisNo);
				$("#vehClassId").val(data.vehClassId);
				$("#engineNo").val(data.engineNo);
				$("#vehicleTypeId").val(data.vehicleTypeId);
				$("#manufacturerId").val(data.manufacturerId);
				$("#cbuSkdCkd").val(data.cbuSkdCkd);
				$("#countryId").val(data.countryId);
				$("#colour").val(data.colour);
				$("#manufactureYear").val(data.manufactureYear);
				$("#noOfCylinder").val(data.noOfCylinder);
				$("#fuelTypeId").val(data.fuelTypeId);
				$("#horsePower").val(data.horsePower);
				$("#rpm").val(data.rpm);
				$("#cc").val(data.cc);
				$("#noOfSeats").val(data.noOfSeats);
				$("#standee").val(data.standee);
				$("#wheelBase").val(data.wheelBase);
				$("#airConditioned").val(data.airConditioned);
				$("#noOfTyres").val(data.noOfTyres);
				$("#tyreSize").val(data.tyreSize);
				$("#noOfAxle").val(data.noOfAxle);
				$("#frontAxle1").val(data.frontAxle1);
				$("#frontAxle2").val(data.frontAxle2);
				$("#centralAxle1").val(data.centralAxle1);
				$("#centralAxle2").val(data.centralAxle2);
				$("#centralAxle3").val(data.centralAxle3);
				$("#rearAxle1").val(data.rearAxle1);
				$("#rearAxle2").val(data.rearAxle2);
				$("#rearAxle3").val(data.rearAxle3);
				$("#length").val(data.length);
				$("#width").val(data.width);
				$("#height").val(data.height);
				$("#overhangsFront").val(data.overhangsFront);
				$("#overhangsRear").val(data.overhangsRear);
				$("#overhangsOther").val(data.overhangsOther);
				$("#remarks2").val(data.remarks2);
				$("#specApprovedBy").val(data.specApprovedBy);
				$("#specApprovedDate").val(data.specApprovedDate);
			},

			error : function(data) {
				alert('Error!!!')
			}
		});
	}

	/* Vehicle Details getRecordByChassis Function Start */
	function getRecordByChassis() {
		var chassis = $("#chassisNo").val();
		var link = "/brtais/vdis/registration/registrationNormal/getRecordByChassis";
		$.ajax({
			type : "POST",
			url : link,
			async : true,
			data : "chassisNo=" + chassis,

			success : function(data) {
				$("#vehicleId").val(data.vehicleId);
				$("#chassisNo").val(data.chassisNo);
				$("#vehClassId").val(data.vehClassId);
				$("#engineNo").val(data.engineNo);
				$("#vehicleTypeId").val(data.vehicleTypeId);
				$("#manufacturerId").val(data.manufacturerId);
				$("#cbuSkdCkd").val(data.cbuSkdCkd);
				$("#countryId").val(data.countryId);
				$("#colour").val(data.colour);
				$("#manufactureYear").val(data.manufactureYear);
				$("#noOfCylinder").val(data.noOfCylinder);
				$("#fuelTypeId").val(data.fuelTypeId);
				$("#horsePower").val(data.horsePower);
				$("#rpm").val(data.rpm);
				$("#cc").val(data.cc);
				$("#noOfSeats").val(data.noOfSeats);
				$("#standee").val(data.standee);
				$("#wheelBase").val(data.wheelBase);
				$("#airConditioned").val(data.airConditioned);
				$("#noOfTyres").val(data.noOfTyres);
				$("#tyreSize").val(data.tyreSize);
				$("#noOfAxle").val(data.noOfAxle);
				$("#frontAxle1").val(data.frontAxle1);
				$("#frontAxle2").val(data.frontAxle2);
				$("#centralAxle1").val(data.centralAxle1);
				$("#centralAxle2").val(data.centralAxle2);
				$("#centralAxle3").val(data.centralAxle3);
				$("#rearAxle1").val(data.rearAxle1);
				$("#rearAxle2").val(data.rearAxle2);
				$("#rearAxle3").val(data.rearAxle3);
				$("#length").val(data.length);
				$("#width").val(data.width);
				$("#height").val(data.height);
				$("#overhangsFront").val(data.overhangsFront);
				$("#overhangsRear").val(data.overhangsRear);
				$("#overhangsOther").val(data.overhangsOther);
				$("#remarks2").val(data.remarks2);
				$("#specApprovedBy").val(data.specApprovedBy);
				$("#specApprovedDate").val(data.specApprovedDate);
			},

			error : function(data) {
				alert('Error!!!')
			}
		});
	}

	/*End Vehicle Details getRecordByChassis and getRecordEngine Function*/

	function getVehicleClassList() {

		var link = "/brtais/vdis/registration/getVehicleClassList";

		$.ajax({
			type : "POST",
			url : link,
			async : true,

			success : function(data) {
				//alert('ssssss!!!')
				$("#vehicleClassList").html(data);
			},

			error : function(data) {
				alert('Error!!!')
			}
		});
	}

	function getVehicleTypeList() {

		var link = "/brtais/vdis/registration/getVehicleTypeList";

		$.ajax({
			type : "POST",
			url : link,
			async : true,

			success : function(data) {
				//alert('success!!!')
				$("#vehicleTypeList").html(data);
			},

			error : function(data) {
				alert('Error!!!')
			}
		});
	}

	function getManufacturerList() {

		var link = "/brtais/vdis/registration/getManufacturerList";

		$.ajax({
			type : "POST",
			url : link,
			async : true,

			success : function(data) {
				//alert('success!!!')
				$("#manufacturerList").html(data);
			},

			error : function(data) {
				alert('Error!!!')
			}
		});
	}

	function getMakersCountryList() {

		var link = "/brtais/vdis/registration/getMakersCountryList";

		$.ajax({
			type : "POST",
			url : link,
			async : true,

			success : function(data) {
				//alert('success!!!')
				$("#makersCountryList").html(data);
			},

			error : function(data) {
				alert('Error!!!')
			}
		});
	}

	function getColourList() {

		var link = "/brtais/vdis/registration/getColourList";

		$.ajax({
			type : "POST",
			url : link,
			async : true,

			success : function(data) {
				//alert('success!!!')
				$("#colourList").html(data);
			},

			error : function(data) {
				alert('Error!!!')
			}
		});
	}

	function getFuelTypeList() {

		var link = "/brtais/vdis/registration/getFuelTypeList";

		$.ajax({
			type : "POST",
			url : link,
			async : true,

			success : function(data) {
				//alert('success!!!')
				$("#fuelTypeList").html(data);
			},

			error : function(data) {
				alert("Error !!!")
			}
		})
	}

	function getSpecApprovedByList() {

		var link = "/brtais/vdis/registration/getSpecApprovedByList";

		$.ajax({
			type : "POST",
			url : link,
			async : true,
			success : function(data) {
				//alert('success!!!')
				$("#specApprovedByList").html(data);
			},

			error : function(data) {
				alert("Error!!!")
			}
		})
	}

	function selectVehicleClassList(employeeId) {
		$("#vehClassId").val(employeeId);
		$("#vehicleClassListClose").click();
	}

	function selectVehicleTypeList(employeeId) {
		$("#vehicleTypeId").val(employeeId);
		$("#vehicleTypeListClose").click();
	}

	function selectManufacturerList(employeeId) {
		$("#manufacturerId").val(employeeId);
		$("#manufacturerListClose").click();
	}

	function selectMakersCountryList(employeeId) {
		$("#countryId").val(employeeId);
		$("#makersCountryListClose").click();
	}

	function selectColourList(employeeId) {
		$("#colour").val(employeeId);
		$("#colourListClose").click();
	}

	function selectFuelTypeList(employeeId) {
		$("#fuelTypeId").val(employeeId);
		$("#fuelTypeListClose").click();
	}

	function selectSpecApprovedByList(employeeId) {
		$("#specApprovedBy").val(employeeId);
		$("#specApprovedByListClose").click();
	}

	function clearOwnerinfoModal() {
		$("#personId").val('');
		$("#personName").val('');
		$("#fatherName").val('');
		$("#dateOfBirth").val('');
		$("#ownerModalmsg").html('');
		$("#ownerInfoList").html('');
	}

	function getOwnerInfoList() {
		var personId = $("#personId").val();
		var personName = $("#personName").val();
		var fatherName = $("#fatherName").val();
		var dateOfBirth = $("#dateOfBirth").val();
		var address = $("#address").val();
		var requiredfield = "Minimum One Value of Criteria is Required!!!";

		if (personId == '' && personName == '' && fatherName == ''
				&& dateOfBirth == '') {
			$("#ownerModalmsg").html(
					'<span style="color:red">' + requiredfield + '<span>');
			//alert('Minimum One Value of Criteria is Required!!!');
		} else {

			var link = "/brtais/vdis/registration/getOwnerInfoList";
			$.ajax({
				type : "POST",
				url : link,
				data : "personId=" + personId + "&personName=" + personName
						+ "&fathersName=" + fatherName + "&dateOfBirth="
						+ dateOfBirth + "&address=" + address,

				/* {"${_csrf.parameterName}":"${_csrf.token}"}, */

				async : true,

				/* waiting wheel */
				beforeSend : function() {
					$('#ajxLoader').show();
				},
				complete : function() {
					$("#ajxLoader").hide();
				},

				success : function(data) {
					//alert('success')
					$("#ownerInfoList").html(data);
				},
				error : function(data) {
					alert('Error!!!')
				}
			});

		}
	}

	/*  	function getOwnerInfo(id) {
	 var url = "/brtais/vdis/registration/registrationNormal/getOwnerInfo?encPersonId="
	 + encodeURIComponent(id)
	 //alert(url);
	 window.location = url;
	 } */

	function selectOwnerInfo(encId, Id, name, fathersName, address) {
		$("#encPersonId").val(encId);
		$('#personId2').val(Id);
		$('#personName2').val(name);
		$('#fathersName').val(fathersName);
		$('#address').val(address);
		$("#ownerInfoListClose").click();
	}

	function getRecordById() {
		var personId = $("#personId2").val();
		if (personId != '') {

			var link = "/brtais/vdis/registration/registrationNormal/getRecordById";
			$.ajax({
				type : "POST",
				url : link,
				data : "personId=" + personId,
				async : true,
				success : function(data) {
					// alert('success')
					$("#encPersonId").val(data.encPersonId);
					$("#personId2").val(data.personId);
					$("#personName2").val(data.personName);
					$("#fathersName").val(data.fathersName);
					$("#address").val(data.address);
				},
				error : function(data) {
					alert('Error!!!')
				}

			});
		} else {
			$("#encPersonId").val('');
			$("#personId2").val('');
			$("#personName2").val('');
			$("#fathersName").val('');
			$("#address").val('');
		}
	}

	function ownerAddlistBody() {
		var encPersonId = $("#encPersonId").val();
		var personId = $("#personId2").val();
		var personName = $("#personName2").val();
		var fathersName = $("#fathersName").val();
		var address = $("#address").val();
		var jointOwner = $("#jointOwner").val();
		var jointOwnerText = document.getElementById('jointOwner').options[document
				.getElementById('jointOwner').selectedIndex].text
		var hirePurchase = $("#hirePurchase").val();
		var hirePurchaseText = document.getElementById('hirePurchase').options[document
				.getElementById('hirePurchase').selectedIndex].text

		var trIndex = $("#ownerIndex").val();

		var sl = $("#sl").val();
		if (sl == '') {
			sl = 0;
		}
		sl = parseInt(sl) + 1;

		var myElem = document.getElementById("encPersonId_" + encPersonId);
		//var myElem = $("#revenueType_"+revenueType).val();

		/* if (encPersonId == '') {
		alert('At first search a consumer by service request number !');
		} else  */
		if (personId == '') {
			alert('Select Person Id !');
		} else if (personName == '') {
			alert('Enter Person Name !');
		} else if (fathersName == '') {
			alert('Enter Fathers Name !');
		} else if (address == '') {
			alert('Enter Address !');
		} else if (jointOwner == '') {
			alert('Enter joint Owner !');
		} else if (hirePurchase == '') {
			alert('Enter hire Purchase !');
		} else {
			if (trIndex == '') {
				var trIndexResult = '0';
			} else {
				var trIndexResult = parseInt(trIndex) + 1;
			}

			if (myElem == null) {

				var newRowContent = "<tr>"
						/* + "<td style='border-style: inset;'>"
						+ sl
						+ "</td>" */
						+ "<td style='border-style: inset;'>"
						+ personId
						+ "<input type='hidden' id='personId_"+encPersonId+"' value='"+personId+"' name='ownerList["+trIndexResult+"].personId'></td>"
						+ "<input type='hidden' id='encPersonId_"+encPersonId+"' value='"+encPersonId+"' name='ownerList["+trIndexResult+"].encPersonId'></td>"
						+ "<td style='border-style: inset;'>"
						+ personName
						+ "<input type='hidden' id='personName_"+encPersonId+"' value='"+personName+"' name='ownerList["+trIndexResult+"].personName'></td>"
						+ "<td style='border-style: inset;'>"
						+ fathersName
						+ "<input type='hidden' id='fathersName_"+encPersonId+"' value='"+fathersName+"' name='ownerList["+trIndexResult+"].fathersName'></td>"
						+ "<td style='border-style: inset;'>"
						+ address
						+ "<input type='hidden' id='address_"+encPersonId+"' value='"+address+"' name='ownerList["+trIndexResult+"].address'></td>"
						//+ "<td style='border-style: inset;'><a href='#' onclick='removetr(this)'><img src='<c:url value='/resources/images/icon-more.gif' />'/></a></td>"
						+ "<td style='border-style: inset;'><a href='#' onclick='ownerPullUp(&#39;"
						+ encPersonId
						+ "&#39;)'><button class='btn btn-xs btn-primary' type='button'>&times;</button></a></td>"
						+ "</tr>";

				$("#ownerIndex").val(trIndexResult);
				$("#sl").val(sl);

				$(newRowContent).appendTo($("#ownerInfoSList"));

				$("#encPersonId").val('');
				$("#personId2").val('');
				$("#personName2").val('');
				$("#fathersName").val('');
				$("#address").val('');
				//$("#jointOwner").val('');
				//$("#hirePurchase").val('');
			} else {
				alert('Select another Owner');
				$("#personId2").focus();

			}

		}
	};

	function removetr(e) {
		var whichtr = $(e).closest("tr");
		whichtr.remove();
	};

	function ownerPullUp(i) {

		var encPersonId = document.getElementById("encPersonId_" + i).value;
		var personId = document.getElementById("personId_" + i).value;
		var personName = document.getElementById("personName_" + i).value;
		var fathersName = document.getElementById("fathersName_" + i).value;
		var address = document.getElementById("address_" + i).value;

		var whichtr = document.getElementById("encPersonId_" + i).closest("tr");

		$("#encPersonId").val(encPersonId);
		$("#personId2").val(personId);
		$("#personName2").val(personName);
		$("#fathersName").val(fathersName);
		$("#address").val(address);

		whichtr.remove();
	}

	function receiptAddList() {

		var receiptId = $("#receiptId").val();
		var trIndex = $("#receiptIndex").val();
		var content = "";
		var myElem = document.getElementById("eTrackingNo_" + receiptId);

		if (receiptId == '') {
			content = "<div class='alert alert-danger'>"
					+ "<button class='close' aria-hidden='true' data-dismiss='alert' "
				+"type='button'>&times;</button>"
					+ "<strong>Please Enter Receipt ID</strong>" + "</div>"

			$("#msg").html(content);
		} else {

			if (trIndex == '') {
				var trIndexResult = '0';
			} else {
				var trIndexResult = parseInt(trIndex) + 1;
			}

			if (myElem == null) {

				var link = "/brtais/vdis/registration/getReceiptInfoSList";
				$
						.ajax({
							type : "POST",
							url : link,
							data : "receiptId=" + receiptId,

							async : true,

							success : function(data) {
								$("#receiptInfoListNotFound").remove();
								if (data.mCode == 1) {
									//alert("abcd");
									var content = "<tr>"
											+ "<td style='border-style: inset;'><input type='hidden' id='purpose_"+data.receiptId+"'"
	            	  				+" name='receiptList["+trIndexResult+"].purpose' value='"+data.purpose+"' />"
											+ data.purpose
											+ "</td>"
											+ "<td style='border-style: inset;'><input type='hidden' id='encReceiptId_"+data.receiptId+"'"
            	  					+" name='receiptList["+trIndexResult+"].encReceiptId' value='"+data.encReceiptId+"'/>"
											+ " <input type='hidden' id='receiptId_"+data.receiptId+"'"
                    	  				+" name='receiptList["+trIndexResult+"].receiptId' value='"+data.receiptId+"'/>"
											+ data.receiptId
											+ "</td>"
											+ "<td style='border-style: inset;'><input type='hidden' id='principalAmount_"+data.receiptId+"'"
	            	  				+" name='receiptList["+trIndexResult+"].principalAmount' value='"+data.principalAmount+"'/>"
											+ data.principalAmount
											+ "</td>"
											+ "<td style='border-style: inset;'><input type='hidden' id='vatAmount_"+data.receiptId+"'"
	            	  				+" name='receiptList["+trIndexResult+"].vatAmount' value='"+data.vatAmount+"'/>"
											+ data.vatAmount
											+ "</td>"
											+ "<td style='border-style: inset;'><input type='hidden' id='totalAmount_"+data.receiptId+"'"
	            	  				+" name='receiptList["+trIndexResult+"].totalAmount' value='"+data.totalAmount+"'/>"
											+ data.totalAmount
											+ "</td>"/*  + "<td>" */
											+ "<td style='border-style: inset;'><a href='#' onclick='removetr(this)'><button class='btn btn-xs btn-primary' type='button'>&times;</button></a></td>"
											+ "</tr>";
									//alert(content);

									$("#msg").html('');
									$("#receiptInfoList").append(content);
									$("#receiptIndex").val(trIndexResult);
									$("#receiptId").val('');

								} else {
									content = "<div class='alert alert-danger'>"
											+ "<button class='close' aria-hidden='true' data-dismiss='alert' "
        	  				+"type='button'>&times;</button>"
											+ "<strong>"
											+ data.message
											+ "</strong>" + "</div>"

									$("#msg").html(content);
								}
							},
							error : function(data) {
								alert('Error!!!')
							}

						});
				$("#receiptIndex").val(trIndexResult);
			} else {
				content = "<div class='alert alert-danger'>"
						+ "<button class='close' aria-hidden='true' data-dismiss='alert' "
					+"type='button'>&times;</button>"
						+ "<strong>Select another Receipt ID... </strong>"
						+ "</div>"

				$("#msg").html(content);
				$("#receiptId").val('');
				$("#receiptId").focus();
			}

		}
	}

	function getNext() {
		$("#next").show();
		$("#btnCheckReg").hide();
		$("#btnCheckReg").prop('disabled', true);

		$("#next").prop('disabled', false);
	}

	function getCheckRegistration() {
		//alert("getCheckRegistration");
		var chassisNo = $("#chassisNo").val();
		var manufactureYear = $("#manufactureYear").val();

		var registaringArea = $("#registaringArea").val();
		var vehClassId = $("#vehClassId").val();

		$("#checkChassisNo").html(chassisNo);
		$("#checkManufactureYear").html(manufactureYear);

		var link = "/brtais/vdis/registration/registrationNormal/getCheckRegistration";

		//alert(link);
		$.ajax({
			type : "POST",
			url : link,
			data : "registaringArea=" + registaringArea + "&vehClassId="
					+ vehClassId,
			async : true,
			success : function(data) {
				if (data != '') {
					$("#checkRegistrationNo").html(data);
					$('#modalCheckRegistrationListbtn').click();
				}
			},
			error : function(data) {
				alert('Error!!!')
			}
		});

	}
</script>


<!-- end: page -->


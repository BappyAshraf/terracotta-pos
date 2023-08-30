<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>

</script>

			<header class="page-header">
						<!-- <h2>User Info View </h2> -->
					
						<div class="left-wrapper pull-left">
							<ol class="breadcrumbs">
								<li><a href="/pos/"> &nbsp; <i class="fa fa-home"></i> </a> </li>
			<li><span>HR &nbsp;</span></li>
			<li><span>Employee Information &nbsp;</span></li>
		</ol>
		<!-- <a class="sidebar-right-toggle" data-open="sidebar-right"><i class="fa fa-chevron-left"></i></a> -->
	</div>
</header>
<!-- start: page -->
<div class="row">
	<div class="col-lg-12">
		<!-- message -->
		<c:if test="${!empty mCode}">
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
	</div>
	<!-- message -->
	<%-- <form class="form-horizontal form-bordered" action="/cbms/hr/employeeInfo/saveEmployeeInfo"  id="form" method="post" onkeypress="return event.keyCode != 13;" >	 --%>
	<a href="#modalMessage"
		class="btn btn-sm btn-primary modal-with-zoom-anim" type="button"
		id="showModalMessage" style="display: none"></a>
	<!-- -----------------------------------tab code---------- -->
	<div class="col-lg-12">
		<div class="tabs tabs-primary">
			<ul class="nav nav-tabs">
				<li
					class="active"><a
					href="#generalInfo" data-toggle="tab" id="generalInfoTab">Employee
						Information</a></li>

			</ul>
			<div class="tab-content">
				<!-- registration tab start here -->
				<div id="generalInfo"
					class="tab-pane  active">
					<form class="form-horizontal form-bordered form" method="post"
						id="form"
						action="employeeInfo/empInfoSave"
						onkeypress="return event.keyCode != 13;" autocomplete="off">
						<section class="panel panel-featured panel-featured-primary">

							<header class="panel-heading">
								<div class="panel-actions">
									<a href="/pos/hr/employeeInfoView"><button
											class="btn btn-primary btn-round" type="button">
											<span><i class="fa fa-arrow-left" aria-hidden="true"></i>
												Back</span>
										</button></a>

									<!-- <button class="btn btn-primary btn-round" type="button"
										onclick="nextTab('educationTab')">
										<span>Next <i class="fa  fa-arrow-right"
											aria-hidden="true"></i>
										</span>
									</button> -->
								</div>

								<h2 class="panel-title">Employee Information</h2>
							</header>

							<div class="panel-body">

								<div class="row">

									<div class="form-group col-md-12">
										<input type="hidden" id="encEmployeeId" name="encEmployeeId"
											value="${employeeInformation.encEmployeeId}" /> 

										<div class="col-sm-6 form-group">
											<label class="col-md-4 col-sm-3 control-label">
												Employee Name <span class="required">*</span>
											</label>
											<div class="col-md-8">
												<input class="form-control" name="employeeName" required
													id="employeeName" maxlength="50"
													onkeypress="goToNext(event,'knownAs')"
													value="${employeeInformation.employeeName}" />

											</div>
										</div>

										<div class="col-sm-6 form-group">
											<label class="col-md-4 col-sm-3 control-label"> Known
												As <span class="required">*</span>
											</label>
											<div class="col-md-8">
												<input class="form-control" name="knownAs" required
													id="knownAs" onkeypress="goToNext(event,'fatherName')"
													value="${employeeInformation.knownAs}" maxlength="50" />
											</div>
										</div>

									</div>

									<div class="form-group col-md-12">

										<div class="col-sm-6 form-group">
											<label class="col-md-4 col-sm-3 control-label">
												Father Name <span class="required">*</span>
											</label>
											<div class="col-md-8">
												<input class="form-control" name="fatherName" required
													id="fatherName" onkeypress="goToNext(event,'motherName')"
													value="${employeeInformation.fatherName}" maxlength="50" />
											</div>
										</div>

										<div class="col-sm-6 form-group">
											<label class="col-md-4 col-sm-3 control-label">
												Mother Name <span class="required">*</span>
											</label>
											<div class="col-md-8">
												<input class="form-control" name="motherName" required
													id="motherName" onkeypress="goToNext(event,'gender')"
													value="${employeeInformation.motherName}" maxlength="50" />

											</div>
										</div>

									</div>


									<div class="form-group col-md-12">

										<div class="col-sm-6 form-group">
											<label class="col-md-4 col-sm-3 control-label">
												Gender <span class="required">*</span>
											</label>
											<div class="col-md-8">
												<select class="form-control" name="gender" id="gender" required
													onkeypress="goToNext(event,'dateOfBirth')">
													<option value="">Please Select One</option>
													<c:forEach items="${genderList}" var="list">
														<option
															<c:if test="${list.id == employeeInformation.gender}">selected = "selected" </c:if>
															value="${list.id}">${list.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<div class="col-sm-6 form-group">
											<label class="col-md-4 col-sm-3 control-label">Date
												Of Birth</label>
											<div class="col-md-8">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="fa fa-calendar"></i>
													</span> <input type="text" class="form-control" id="dateOfBirth"
														onkeypress="goToNext(event,'religion')" name="dateOfBirth"
														data-plugin-masked-input data-input-mask="99/99/9999"
														placeholder="dd/mm/yyyy"
														value="${employeeInformation.dateOfBirth}" />

												</div>
											</div>
										</div>

									</div>


									<div class="form-group col-md-12">

										<div class="col-sm-6 form-group">
											<label class="col-md-4 col-sm-3 control-label">
												Religion <span class="required">*</span>
											</label>
											<div class="col-md-8">
												<select class="form-control" name="religion" id="religion"
													id="maritalStatus" required
													onkeypress="goToNext(event,'maritalStatus')">
													<option value="">Please Select One</option>
													<c:forEach items="${religionList}" var="list">
														<option
															<c:if test="${list.id == employeeInformation.religion}">selected = "selected" </c:if>
															value="${list.id}">${list.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<div class="col-sm-6 form-group">
											<label class="col-md-4 col-sm-3 control-label">
												Marital Status <span class="required">*</span>
											</label>
											<div class="col-md-8">
												<select class="form-control" name="maritalStatus" required
													id="maritalStatus"
													onkeypress="goToNext(event,'nationalId')">
													<option value="">Please Select One</option>
													<c:forEach items="${maritalStatusList}" var="list">
														<option
															<c:if test="${list.id == employeeInformation.maritalStatus}">selected = "selected" </c:if>
															value="${list.id}">${list.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>

									</div>

								</div>


								<div class="form-group col-md-12">

									<div class="col-sm-6 form-group">
										<label class="col-md-4 col-sm-3 control-label">
											National Id No. </label>
										<div class="col-md-8">
											<input type="text" class="form-control" id="nationalId"
												onkeypress="goToNext(event,'designationId')" maxlength="17"
												minlength="10" number="true" name="nationalId"
												onkeydown="return isNumberKey(event)"
												value="${employeeInformation.nationalId}" />
											<div id="errnationalId"></div>
										</div>
									</div>

									<div class="col-sm-6 form-group">
										<label class="col-md-4 col-sm-3 control-label">
											Designation <span class="required">*</span>
										</label>
										<div class="col-md-8">

											<select data-plugin-selectTwo class="form-control"
												name="designationId" id="designationId" required
												onkeypress="goToNext(event,'joiningDate')">
												<option value="">Please Select One</option>
												<c:forEach items="${designationList}" var="list">
													<option
														<c:if test="${list.id == employeeInformation.designationId}">selected = "selected" </c:if>
														value="${list.id}">${list.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>

								</div>

								<div class="form-group col-md-12">

									<div class="col-sm-6 form-group">
										<label class="col-md-4 col-sm-3 control-label">Joining
											Date <span class="required">*</span>
										</label>
										<div class="col-md-8">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="fa fa-calendar"></i>
												</span> <input type="text" class="form-control" id="joiningDate"
													required onkeypress="goToNext(event,'resigningDate')"
													name="joiningDate" data-plugin-masked-input
													data-input-mask="99/99/9999" placeholder="dd/mm/yyyy"
													value="${employeeInformation.joiningDate}" />

											</div>
										</div>
									</div>

									<div class="col-sm-6 form-group">
										<label class="col-md-4 col-sm-3 control-label">Resigning
											Date</label>
										<div class="col-md-8">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="fa fa-calendar"></i>
												</span> <input type="text" class="form-control" id="resigningDate"
													onkeypress="goToNext(event,'contactNo')"
													name="resigningDate" data-plugin-masked-input
													data-input-mask="99/99/9999" placeholder="dd/mm/yyyy"
													value="${employeeInformation.resigningDate}" />

											</div>
										</div>
									</div>

								</div>


								<div class="form-group col-md-12">

									<div class="col-sm-6 form-group">
										<label class="col-md-4 col-sm-3 control-label">Contact
											No <span class="required">*</span>
										</label>
										<div class="col-md-8">
											<input type="text" class="form-control" id="contactNo"
												onkeypress="goToNext(event,'emailAddress')" maxlength="11"
												onkeydown="return isNumberKey(event)"
												minlength="11" number="true" name="contactNo"
												value="${employeeInformation.contactNo}" />
												<div id="errcontactNo"></div>
										</div>
									</div>

									<div class="col-sm-6 form-group">
										<label class="col-md-4 col-sm-3 control-label">Email</label>
										<div class="col-md-8">
											<input type="email" class="form-control" id="emailAddress"
												onkeypress="goToNext(event,'levelNo')" name="emailAddress"
												value="${employeeInformation.emailAddress}" />
										</div>
									</div>

								</div>
								
								<div class="form-group col-md-12">
								
								
								<div class="col-sm-6 form-group">
										<label class="col-md-4 col-sm-3 control-label">Fixed Salary </label>
										<div class="col-md-8">
											<input type="text" class="form-control" id="fixedSalary"
												onkeypress="goToNext(event,'levelNo')" name="fixedSalary"
												onkeydown="return isNumberKey(event)"
												value="${employeeInformation.fixedSalary}" />
												<div id="errfixedSalary"></div>
										</div>
									</div>
								
										<div class="col-sm-6 form-group">
											<label class="col-md-4 col-sm-3 control-label">Status
												<span class="required">*</span>
											</label>
											<div class="col-md-8">
												<div class="col-md-6">
													<div class="radio-custom radio-primary">
														<input type="radio" id="enabledYN1" name="enabledYN"
															required
															<c:if test="${employeeInformation.enabledYN == 'Y'}">checked </c:if>
															value="Y" onkeypress="goToNext(event,'enabledYN2')" /> <label
															for="enabledYN1">Active</label>
													</div>
												</div>

												<div class="col-md-6">
													<div class="radio-custom radio-primary">
														<input type="radio" id="enabledYN2" name="enabledYN"
															<c:if test="${employeeInformation.enabledYN == 'N'}">checked </c:if>
															value="N" onkeypress="goToNext(event,'preAddress')" /> <label
															for="enabledYN2">Inactive</label>
													</div>
												</div>
												<label class="error" for="enabledYN"></label>
											</div>
										</div>
										
									</div>

							</div>

							<footer class="panel-footer">
								<div class="row">
									<div class="col-sm-12 col-sm-offset-0"></div>
									<div class="btn_div col-sm-offset-0 ">
										<a href="employeeInfo"><button class="btn btn-default"
												type="button" role="button">
												<i class="fa fa-refresh"></i> Clear
											</button></a>
										<button class="btn btn-primary" type="submit" id="submit">
											<i class="fa fa-save"></i>
											<c:choose>
												<c:when test="${!empty employeeInformation.encEmployeeId}">
													<span> <i class="icon wb-loop" aria-hidden="true"></i>
														Update
													</span>
												</c:when>
												<c:otherwise>
													<span> <i class="icon wb-book" aria-hidden="true"></i>
														Save
													</span>
												</c:otherwise>
											</c:choose>
										</button>
									</div>
								</div>
							</footer>

						</section>

					</form>

				</div>
			</div>

		</div>

	</div>

</div>



<script>
	

	/*   function goToNext(e, next) {
		var key;
		
		if (key == 13) {
alert()
			document.getElementById(next).focus();
			return false;

		} else
			return true;
	}  */ 
	
	function goToNext(e, next) {
		var key;
		if (window.event)
			key = window.event.keyCode; //IE
		else
			key = e.which; //firefox
		if (key == 13) {

			document.getElementById(next).focus();
			return false;

		} else
			return true;
	}
	
//===========ON CHANGE SELECTED==========//

	function updateSelectOptions(link, parentSelectElementId,
			childSelectElementId) {
		var parentSelectElement = $("#" + parentSelectElementId).val();

		if (parentSelectElement == '') {
			$("#" + childSelectElementId + "option").remove();
			$("#" + childSelectElementId).val('');
			var content = '<option value="">Please Select One</option>';
			$('#' + childSelectElementId).html(content);
		} else {
			$.ajax({
				type : "get",
				url : link,
				data : "id=" + parentSelectElement,
				async : true,
				success : function(data) {

					$("#" + childSelectElementId).html(data);
				}
			});
			//alert('success');

			$("#" + childSelectElementId).focus();
		}
	};
	
	//===========ON CHANGE SELECTED END==========//
	
	//===========Edit METHOD START==========//
	
	function getEmpEducationInfo(empId,certId){
		var link = "/cbms/hr/employeeInfo/getEmpEducationInfo?encEmployeeId="+encodeURIComponent(empId)+
				"&certificateTypeId="+certId;
				//alert(link);
	 	window.location = link;
	}
	
	
	function getEmpExperienceInfo(empId,expId){
		var link = "/cbms/hr/employeeInfo/getEmpExperienceInfo?encEmployeeId="+encodeURIComponent(empId)+
				"&encEmpExperienceId="+encodeURIComponent(expId);
				//alert(link);
	 	window.location = link;
	}
	
	
	function getEmpSpouseInfo(empId,spouseId){
		var link = "/cbms/hr/employeeInfo/getEmpSpouseInfo?encEmployeeId="+encodeURIComponent(empId)+
				"&encEmpSpouseId="+encodeURIComponent(spouseId);
				//alert(link);
	 	window.location = link;
	}
	
	
	function getEmpChildrenInfo(empId,childId){
		var link = "/cbms/hr/employeeInfo/getEmpChildrenInfo?encEmployeeId="+encodeURIComponent(empId)+
				"&encEmpChildrenId="+encodeURIComponent(childId);
				//alert(link);
	 	window.location = link;
	}
	
	function getEmpTrainingInfo(empId,trainingId){
		var link = "/cbms/hr/employeeInfo/getEmpTrainingInfo?encEmployeeId="+encodeURIComponent(empId)+
				"&encEmpTrainingId="+encodeURIComponent(trainingId);
				//alert(link);
	 	window.location = link;
	}
	
	function getEmpReferenceInfo(empId,referenceId){
		var link = "/cbms/hr/employeeInfo/getEmpReferenceInfo?encEmployeeId="+encodeURIComponent(empId)+
		"&encEmpReferenceId="+encodeURIComponent(referenceId);
				//alert(link);
	 	window.location = link;
	}
	
	//===========Edit METHOD END==========//
	
	
	
	//===========DELETE METHOD START==========//
	
	function delEducation(empId,certId,eduId) {
		 $("#encEmployeeIdForDeleteEdu").val(empId);
		  $("#certificateTypeIdForDelete").val(certId);
		  $("#encEducationIdForDeleteEdu").val(eduId);
	}
	
	function deleteEducationInfo() {
		var empId =  $('#encEmployeeIdForDeleteEdu').val();
		var certId =  $('#certificateTypeIdForDelete').val();
		var eduId =  $('#encEducationIdForDeleteEdu').val();
		
		var link = "/cbms/hr/employeeInfo/deleteEducationInfo?encEmployeeId="+encodeURIComponent(empId)+
				"&certificateTypeId="+certId+"&encEducationId="+encodeURIComponent(eduId);
				//alert(link);
	 	window.location = link;
	}
	//------------delete education end---------------//
	
	function delExperience(empId,experienceId) {
		 $("#encEmployeeIdForDeleteExp").val(empId);
		  $("#encEmpExperienceIdForDelete").val(experienceId);
	}
	function deleteExperienceInfo() {
		var empId =  $('#encEmployeeIdForDeleteExp').val();
		var experienceId =  $('#encEmpExperienceIdForDelete').val();
		
		var link = "/cbms/hr/employeeInfo/deleteExperienceInfo?encEmployeeId="+encodeURIComponent(empId)+
				"&encEmpExperienceId="+encodeURIComponent(experienceId);
				//alert(link);
	 	window.location = link;
	}
	//------------delete experience end---------------//
	
	function delSpouse(empId,spouseId) {
		 $("#encEmployeeIdForDeleteSpo").val(empId);
		  $("#encEmpSpouseIdForDelete").val(spouseId);
	}
	function deleteSpouseInfo() {
		var empId =  $('#encEmployeeIdForDeleteSpo').val();
		var spouseId =  $('#encEmpSpouseIdForDelete').val();
		var link = "/cbms/hr/employeeInfo/deleteSpouseInfo?encEmployeeId="+encodeURIComponent(empId)+
				"&encEmpSpouseId="+encodeURIComponent(spouseId);
				//alert(link);
	 	window.location = link;
	}
	//------------delete spouse end---------------//
	
	function delChildren(empId,childId) {
		 $("#encEmployeeIdForDeleteChi").val(empId);
		  $("#encEmpChildrenIdForDelete").val(childId);
	}
	function deleteChildrenInfo() {
		var empId =  $('#encEmployeeIdForDeleteChi').val();
		var childId =  $('#encEmpChildrenIdForDelete').val();
		var link = "/cbms/hr/employeeInfo/deleteChildrenInfo?encEmployeeId="+encodeURIComponent(empId)+
				"&encEmpChildrenId="+encodeURIComponent(childId);
				//alert(link);
	 	window.location = link;
	}
	//------------delete children end---------------//
	
	function delTraining(empId,trainingId) {
		  $("#encEmployeeIdForDeleteTra").val(empId);
		  $("#encEmpTrainingIdForDelete").val(trainingId);
	}
	function deleteTrainingInfo() {
		var empId =  $('#encEmployeeIdForDeleteTra').val();
		var trainingId =  $('#encEmpTrainingIdForDelete').val();
		var link = "/cbms/hr/employeeInfo/deleteTrainingInfo?encEmployeeId="+encodeURIComponent(empId)+
				"&encEmpTrainingId="+encodeURIComponent(trainingId);
				//alert(link);
	 	window.location = link;
	}
	
	//------------delete Reference Info Start---------------//
	
	function delReference(empId, referenceId) {
		  $("#encEmployeeIdForDeleteRef").val(empId);
		  $("#encEmpReferenceIdForDelete").val(referenceId);
	}
	
	function deleteReferenceInfo() {
		var empId =  $('#encEmployeeIdForDeleteRef').val();
		var referenceId =  $('#encEmpReferenceIdForDelete').val();
		var link = "/cbms/hr/employeeInfo/deleteReferenceInfo?encEmployeeId="+encodeURIComponent(empId)+
				"&encEmpReferenceId="+encodeURIComponent(referenceId);
				//alert(link);
	 	window.location = link;
	}
	
	//------------delete Reference Info End---------------//
	
	//===========DELETE METHOD END==========//
	
	//===========Clear form value Start==========//
	
	function clearEducationInfo () {
		$("#certificateTypeId").val('');
		$("#boardOrVersityName").val('');
		$("#passingYear").val('');
		$("#instituteName").val('');
		$("#result").val('');
		$("#majorSubject").val('');
	}
	
	function clearExperienceInfo () {
		$("#encEmpExperienceId").val('');
		$("#organizationName").val('');
		$("#expDesignation").val('');
		$("#expJoiningDate").val('');
		$("#expResigningDate").val('');
		$("#expJobResponsible").val('');
	}
	
	function clearSpouseInfo () {
		$("#encEmpSpouseId").val('');
		$("#spouseName").val('');
		$("#spouseRelation").val('');
		$("#spouseOccupation").val('');
		$("#spouseDesignation").val('');
		$("#spouseWorkLocation").val('');
	}
	
	function clearChildrenInfo () {
		$("#encEmpChildrenId").val('');
		$("#childName").val('');
		$("#childDateOfBirth").val('');
		$("#childGender").val('');
	}
	
	function clearTrainingInfo () {
		$("#encEmpTrainingId").val('');
		$("#trainingType").val('');
		$("#trainingSponsor").val('');
		$("#trainingTitle").val('');
		$("#trainingInstitute").val('');
		$("#trainingFromDate").val('');
		$("#trainingToDate").val('');
		$("#trainingDuration").val('');
		//$("#trainingCountry").val().text('');
		//document.getElementById("trainingCountry").selectedIndex = 0;
	}
	
	function clearReferenceInfo () {
		$("#encEmpReferenceId").val('');
		$("#referrerId").val('');
		$("#referrerRemark").val('');
	}
	
	//===========Clear form value END==========//

	

	function nextTab(tab) {
		$('#' + tab).click();
	}

 	$(function() {
		$("#uploadPhoto")
				.change(
						function() {
							if (typeof (FileReader) != "undefined") {
								var dvPreview = $("#dvPreviewPhoto");
								dvPreview.html("");
								var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
								$($(this)[0].files)
										.each(
												function() {
													var file = $(this);
													if (regex.test(file[0].name
															.toLowerCase())) {
														var reader = new FileReader();
														reader.onload = function(
																e) {
															var img = $("<img />");
															img
																	.attr(
																			"style",
																			"height:150px;width: 150px");
															img
																	.attr(
																			"src",
																			e.target.result);
															dvPreview
																	.append(img);
														}
														reader
																.readAsDataURL(file[0]);
													} else {
														alert(file[0].name
																+ " is not a valid image file.");
														dvPreview.html("");
														return false;
													}
												});
							} else {
								alert("This browser does not support HTML5 FileReader.");
							}
						});
	}); 

/* 	
	
	function readURL(input) {

	    if (input.files && input.files[0]) {
	        var reader = new FileReader();

	        reader.onload = function (e) {
	            $('#blah').attr('src', e.target.result);
	            $('#blah').attr( "style", "height:150px; width: 150px");
	        }

	        reader.readAsDataURL(input.files[0]);
	    }
	}

	$("#uploadPhoto").change(function(){
	    readURL(this);
	}); */
	
	
	
	$(function() {
		$("#uploadSignature")
		
				.change(
						function() {
							if (typeof (FileReader) != "undefined") {
								var dvPreview = $("#dvPreviewSignature");
								dvPreview.html("");
								var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
								$($(this)[0].files)
										.each(
												function() {
													var file = $(this);
													if (regex.test(file[0].name
															.toLowerCase())) {
														var reader = new FileReader();
														reader.onload = function(
																e) {
															var img = $("<img />");
															img
																	.attr(
																			"style",
																			"height:150px;width: 200px");
															img
																	.attr(
																			"src",
																			e.target.result);
															dvPreview
																	.append(img);
														}
														reader
																.readAsDataURL(file[0]);
													} else {
														alert(file[0].name
																+ " is not a valid image file.");
														dvPreview.html("");
														return false;
													}
												});
							} else {
								alert("This browser does not support HTML5 FileReader.");
							}
						});
	});
	
	
	function addlistBody() {
		var catId = $("#catId").val();
		var catName = $("#catId option:selected").text();

		var myElem = document.getElementById("catId_" + catId);

		var trIndex = $("#trIndex").val();

		if (catId == '') {
			alert('Select Category Name !');
		} else {
			if (trIndex == '') {
				var trIndexResult = '0';
			} else {
				var trIndexResult = parseInt(trIndex);
			}

			if (myElem == null) {
				var trIndexResultSerial = parseInt(trIndex) + 1;

				var newRowContent = "<tr>"
						+ "<td>"
						+ trIndexResultSerial
						+ "</td>"
						+ "<input type='hidden' id='catId_"+ catId+"' value='"+catId+"' name='adCategoryIdList["+trIndexResult+"].adCategoryId'></td>"
						+ "<td >"
						+ catName
						+ "</td>"
						+ "<td>"
						+ "<input onkeyup='disPerOfBasic("+trIndexResult+")' onkeypress='return isNumberKey(event)'  type='text' id='fixedAmount_"+trIndexResult+"' value='' name='adCategoryIdList["+trIndexResult+"].fixedAmount'>"
						+ "</td>"
						+ "<td>"
						+ "<input onkeyup='disfixedAmount("+trIndexResult+")'  onkeypress='return isNumberKey(event)' type='text' id='perOfBasic_"+trIndexResult+"' value='' name='adCategoryIdList["+trIndexResult+"].perOfBasic'>" 
						+" <button class='btn-primary' type='button' onclick='removetr(this)'  >&times;</button>"
						+ "</td>"
						+ "</tr>";

				$("#trIndex").val(trIndexResultSerial);
				// alert(newRowContent);
				$(newRowContent).appendTo($("#categoryList"));

			} else {
				alert('Select another Category Name');
				$("#catId").focus();
			}
		}

	};
	
	
	function removetr(e) {
		  var whichtr = $(e).closest("tr");

		  whichtr.remove();
		 };
		 
	
	function disPerOfBasic(id) {
		var val=$("#fixedAmount_"+id).val();
		if(val != '') {
			$("#perOfBasic_"+id).prop('disabled',true);
		}
		else
			{
			$("#perOfBasic_"+id).prop('disabled',false);
			}
	}
	
	
	function disfixedAmount(id) {
		var val=$("#perOfBasic_"+id).val();
		if(val != '') {
			$("#fixedAmount_"+id).prop('disabled',true);
		}
		else
			{
			$("#fixedAmount_"+id).prop('disabled',false);
			}
	}
	
	function isNumberKey(evt) {
	    var charCode = (evt.which) ? evt.which : event.keyCode;
	    var errid = "err"+evt.target.id;
	    var msg = '<label class="error" for="'+evt.target.id+'">Please enter a valid number.</label>';
	    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57) && (charCode < 96 || charCode > 105)) {
	    	$("#"+errid).html(msg);
	        return false;
	    } else {
	    	$("#"+errid).html('');
	        return true;
	    }      
	}
	
	function getTimeDiff() {
		
		var trainingFromDate = $("#trainingFromDate").val();
		var trainingToDate = $("#trainingToDate").val();
		
		if ( trainingFromDate != '' && trainingToDate != '') {

			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");

			var link = "/cbms/hr/getTimeDifferent";

			
			//alert(totalhour);

			$.ajax({
				type : "POST",
				url : link,
				data : "trainingFromDate=" + trainingFromDate + "&trainingToDate=" + trainingToDate,
				async : true,
				beforeSend : function(xhr) {
					// here it is
					xhr.setRequestHeader(header, token);
				},
				success : function(data) {
					$("#trainingDuration").val(data.trainingDuration);
				},
				error : function(data) {
					alert('Error!!!');
				}
			});
		}
	}
	
</script>



<!-- End Page -->
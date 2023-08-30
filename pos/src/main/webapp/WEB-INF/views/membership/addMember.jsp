<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
</script>

<header class="page-header">
	<!-- <h2>Personal Identification</h2> -->

	<div class="left-wrapper pull-left">
		<ol class="breadcrumbs">
			<li><a href="/pos/"> &nbsp;<i class="fa fa-home"></i></a></li>
			<li><span>Membership &nbsp;</span></li>
			<li><span>Add Member &nbsp;</span></li>
		</ol>
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
		<form class="form-horizontal form-bordered form" method="post"
			id="form" action="addMemberSave" autocomplete="off"
			onkeypress="return event.keyCode != 13;">
			<section class="panel panel-featured panel-featured-primary">

				<header class="panel-heading">
					<h2 class="panel-title">Employee Information</h2>
				</header>

				<div class="panel-body">

					<div class="row">

						<div class="col-md-12">

						<div class="form-group col-sm-6">
								<label class="col-md-4 col-sm-4 control-label"> Member
									Name 
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<div class="input-group mb-md">
										<input type="hidden" id="encMemberId" name="encMemberId"
											value="${membership.encMemberId}" /> <input
											class="form-control mandatory" name="memberName" 
											id="memberName" maxlength="50"
											 onkeyup="this.value = this.value.toUpperCase();"
											value="${membership.memberName}" /> 
											
											<span
											class="input-group-btn"> <a onclick="getMemberList()"
											class="btn btn-mg btn-primary modal-with-move-anim"
											type="submit" href="#modalMemberList"> <i
												class="fa fa-search"></i>
										</a>
										</span>
										
									</div>
								</div>
							</div>
							
							
							<!-------------------------------- start modal member list ------------------------------------------>

										<div id="modalMemberList" class="zoom-anim-dialog modal-block modal-block-lg mfp-hide">
										
									<%-- 	<section class="panel panel-featured panel-featured-primary">
									
											<header class="panel-heading">
												<h2 class="panel-title">Member Info View</h2>
											</header>
											<div class="panel-body">
									
												<div class="row">
									
													<div class="form-group col-md-10">
									
														<div class="col-sm-6 form-group">
															<label class="col-md-4 col-sm-3 control-label"> Member
																No </label>
															<div class="col-md-8">
																<input class="form-control" name="employeeName" id="employeeName"
																	onkeypress="goToNext(event,'fatherName')"
																	value="${employeeInfo.employeeName}" />
									
															</div>
														</div>
									
														<div class="col-sm-6 form-group">
															<label class="col-md-4 col-sm-3 control-label"> Contact
																No </label>
															<div class="col-md-8">
																<input class="form-control" name="fatherName" id="fatherName"
																	onkeypress="goToNext(event,'designationId')"
																	value="${employeeInfo.fatherName}" />
									
															</div>
														</div>
									
														<div class="col-sm-6 form-group">
															<label class="col-md-4 col-sm-3 control-label"> Member
																Name </label>
															<div class="col-md-8">
																<input class="form-control" name="fatherName" id="fatherName"
																	onkeypress="goToNext(event,'designationId')"
																	value="${employeeInfo.fatherName}" />
									
															</div>
														</div>
									
														<div class="col-sm-6 form-group">
															<label class="col-md-4 col-sm-3 control-label"> Known
																As </label>
															<div class="col-md-8">
																<input class="form-control" name="fatherName" id="fatherName"
																	onkeypress="goToNext(event,'designationId')"
																	value="${employeeInfo.fatherName}" />
									
															</div>
														</div>
														
													</div>
									
													<div class="form-group col-md-2">
														<button class="btn btn-animate btn-animate-side btn-primary"
															id="search" onclick="getEmployeeList()" type="button">
															<span> <i class="fa fa-search" aria-hidden="true"></i>
																Search
															</span>
														</button>
													</div>
									
												</div>
									
											</div>
									
									</section> --%>
										
										
									<section class="panel panel-featured panel-featured-primary">
										<header class="panel-heading">
											<h2 class="panel-title">Member List</h2>
										</header>
								
										<div class="panel-body">
								
											<div class="row">
												<div class="col-md-12">
													<div style="overflow: scroll; max-height: 300px;">
								
														<table class="table table-bordered table-striped mb-none" 
														id="memberListTable" role="grid">
															<thead>
																<tr>
																	<th>#</th>
																	<th>Member No</th>
																	<th>Member Name</th>
																	<th>Known As</th>
																	<th>Contact No</th>
																	<th>Address</th>
																	<th>Edit</th>
																</tr>
																
															</thead>
															<tbody id="memberList">
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</section>
									
									
									<footer class="panel-footer">
										<div class="row">
											<div class="col-md-12 text-right">
												<button class="btn btn-default modal-dismiss" >Close</button>
											</div>
										</div>
									</footer>
									
							</div>


<!-------------------------------- end modal member list ------------------------------------------>

							
							
							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label"> Known As
									<span class="required">*</span>
								</label>
								<div class="col-md-8">
									<input class="form-control mandatory" name="knownAs" required
									 onkeyup="this.value = this.value.toUpperCase();"
										id="knownAs" onkeypress="goToNext(event,'fatherName')"
										value="${membership.knownAs}" maxlength="50" />
								</div>
							</div>

						</div>
						
						
							<div class="form-group col-md-12">

							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label"> Gender <span
									class="required">*</span>
								</label>
								<div class="col-md-8">
									<select class="form-control mandatory" name="genderId" required
										id="genderId">
										<option value="">Please Select One</option>
										<c:forEach items="${genderList}" var="list">
											<option
												<c:if test="${list.id == membership.genderId}">selected = "selected" </c:if>
												value="${list.id}">${list.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label"> Religion
									<span class="required">*</span>
								</label>
								<div class="col-md-8">
									<select class="form-control mandatory" name="religionId" required
										id="religionId">
										<option value="">Please Select One</option>
										<c:forEach items="${religionList}" var="list">
											<option
												<c:if test="${list.id == membership.religionId}">selected = "selected" </c:if>
												value="${list.id}">${list.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>

						</div>

						<div class="form-group col-md-12">

							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label">Contact
									No <span class="required">*</span>
								</label>
								<div class="col-md-8">
									<input type="text" class="form-control mandatory"
										id="contactNo" maxlength="11" name="contactNo" required
										placeholder="01xxxxxxxxx"
										onkeydown="return isNumberKey(event)"
										value="${membership.contactNo}" />
									<div id="errcontactNo"></div>
								</div>
							</div>

							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label">Email 
								</label>
								<div class="col-md-8">
									<input type="email" class="form-control"
										id="emailAddress"
										name="emailAddress"
										value="${membership.emailAddress}" />
								</div>
							</div>

						</div>

					

						<div class="form-group col-md-12">
							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label"> Father
									Name </label>
								<div class="col-md-8">
									<input class="form-control" name="fathersName" 
									 onkeyup="this.value = this.value.toUpperCase();"
										id="fathersName"
										value="${membership.fathersName}" maxlength="50" />
								</div>
							</div>

							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label"> Mother
									Name </label>
								<div class="col-md-8">
									<input class="form-control" name="mothersName" id="mothersName"
									 onkeyup="this.value = this.value.toUpperCase();"
										value="${membership.mothersName}" maxlength="50" />
								</div>
							</div>
						</div>


						<div class="form-group col-md-12">

							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label">Date Of
									Birth</label>
								<div class="col-md-8">
									<div class="input-group">
										<span class="input-group-addon"> <i
											class="fa fa-calendar"></i>
										</span> <input type="text" class="form-control" id="dateOfBirth"
											name="dateOfBirth"
											data-plugin-masked-input data-input-mask="99/99/9999"
											data-plugin-datepicker
											placeholder="dd/mm/yyyy"
											value="${membership.dateOfBirth}" />

									</div>
								</div>
							</div>

							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label"> Marital
									Status </label>
								<div class="col-md-8">
									<select class="form-control" name="maritalStatusId"
										id="maritalStatusId">
										<option value="">Please Select One</option>
										<c:forEach items="${maritalStatusList}" var="list">
											<option
												<c:if test="${list.id == membership.maritalStatusId}">selected = "selected" </c:if>
												value="${list.id}">${list.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>


					<div class="form-group col-md-12">
						<div class="col-sm-6 form-group">
							<label class="col-md-4 col-sm-3 control-label"> National
								ID No. </label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="nationalIdNo"
									maxlength="17"
									minlength="10" number="true" name="nationalIdNo"
									onkeydown="return isNumberKey(event)"
									value="${membership.nationalIdNo}" />
								<div id="errnationalId"></div>
							</div>
						</div>
						<div class="col-sm-6 form-group">
							<label class="col-md-4 col-sm-3 control-label">Joining
								Date <span class="required">*</span> </label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="fa fa-calendar"></i>
									</span> <input type="text" class="form-control" id="joiningDate"
										required
										name="joiningDate" data-plugin-masked-input
										data-plugin-datepicker
										data-input-mask="99/99/9999" placeholder="dd/mm/yyyy"
										value="${membership.joiningDate}" />
								</div>
							</div>
						</div>
					</div>

					<div class="form-group col-md-12">
					
						<div class="col-sm-6 form-group">
							<label class="col-md-4 col-sm-3 control-label"> Address
								 </label>
							<div class="col-md-8">
								<input type="text" class="form-control" 
									id="address" name="address"
									value="${membership.address}" />
							</div>
						</div>
					
						<div class="col-sm-6 form-group">
							<label class="col-md-4 col-sm-3 control-label">Status <span
								class="required">*</span>
							</label>
							<div class="col-md-8">
								<div class="col-md-6">
									<div class="radio-custom radio-primary">
										<input type="radio" id="enabledYN1" name="enabledYN" required
											<c:if test="${membership.enabledYN == 'Y'}">checked </c:if>
											value="Y"/> <label
											for="enabledYN1">Active</label>
									</div>
								</div>

								<div class="col-md-6">
									<div class="radio-custom radio-primary">
										<input type="radio" id="enabledYN" name="enabledYN"
											<c:if test="${membership.enabledYN == 'N'}">checked </c:if>
											value="N" /> <label
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
							<a href="addMember"><button class="btn btn-default"
									type="button" role="button">
									<i class="fa fa-refresh"></i> Clear
								</button></a>
							<button class="btn btn-primary" type="submit" id="submit">
								<i class="fa fa-save"></i>
								<c:choose>
									<c:when test="${!empty membership.encMemberId}">
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

<!-------------------------------- start modal vehicle class ------------------------------------------>

<div id="modalProductList"
	class="zoom-anim-dialog modal-block modal-block-sm mfp-hide">
	<!-- class="zoom-anim-dialog modal-block modal-block-lg mfp-hide"> -->
	<section class="panel">
		<header class="panel-heading">
			<button class="close modal-dismiss" type="button"
				id="productListClose">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<h2 class="panel-title">Product List</h2>
		</header>

		<div class="panel-body">

			<div class="row">
				<div class="col-md-12">
					<div style="overflow: scroll; max-height: 300px;">

						<table
							class="table table-striped table-condensed table-hover mb-none ">
							<thead>
								<tr>
									<th>Product Code</th>
									<th>Product Name</th>
									<th>Unit</th>
								</tr>
							</thead>
							<tbody id="productList" style="border-style: inset;">
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>


<!-------------------------------- end modal vehicle class ------------------------------------------>






<script>
	
	function nextTab(tab) {
		$('#' + tab).click();
	}
	
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
	
	
	function removetr(e) {
		  var whichtr = $(e).closest("tr");
		  whichtr.remove();
		 };
		 
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
	
	function clearChildrenInfo () {
		$("#encEmpChildrenId").val('');
		$("#childName").val('');
		$("#childDateOfBirth").val('');
		$("#childGender").val('');
	}
	
	function getMemberList() {
		var link = "/pos/membership/getMemberList";
		//var reservationDate = $("#reservationDate").val();
		
		$.ajax({
			type : "POST",
			url : link,
			async : true,
			success : function(data) {
				$("#memberList").html(data);
				$("#memberListTable").DataTable();
			},
			error : function(data) {
				alert('Error!!!')
			}
		});
	};
	
	
	function getMemberInfo(encMemberId){
		var link = "/pos/membership/getMemberInfo?encMemberId="+encodeURIComponent(encMemberId);
				//alert(link);
	 	window.location = link;
	}
	
	
</script>



<!-- End Page -->
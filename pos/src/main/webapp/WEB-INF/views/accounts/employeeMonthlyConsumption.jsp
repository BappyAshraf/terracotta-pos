<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<header class="page-header">
	<!-- <h2>Employee Consumption Info</h2> -->

	<div class="left-wrapper pull-left">
		<ol class="breadcrumbs">
			<li><a href="/pos/"> &nbsp; <i class="fa fa-home"></i>
			</a></li>
			<li><span>Accounts &nbsp;</span></li>
			<li><span>Employee Consumption Info &nbsp;</span></li>
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


		<div id="modalEmpSearch"
			class="zoom-anim-dialog modal-block modal-block-lg mfp-hide">
			<!-- <section class="panel panel-featured panel-featured-primary"> -->
			<c:import url="/WEB-INF/views/hr/employeeSearch.jsp"></c:import>
			<footer class="panel-footer">
				<div class="row">
					<div class="col-md-12 text-right">
						<button class="btn btn-default modal-dismiss">Close</button>
					</div>
				</div>
			</footer>
			<!-- </section> -->
		</div>

		<!-- message -->

		<section class="panel panel-featured panel-featured-primary">
			<form class="form-horizontal form-bordered" autocomplete="off" id="form"
				action="/pos/accounts/empMonthlyConsumptionSave" method="post">

				<header class="panel-heading">
					<h2 class="panel-title">Employee Consumption Info</h2>
				</header>

				<div class="panel-body">

					<div class="row">

						<div class="form-group col-md-10">
						
						<input type="hidden" value="${employeeMonthlyConsumption.encConsumeId}" name="encConsumeId" />
						
						<input type="hidden" value="${employeeMonthlyConsumption.encEmployeeId}" name="encEmployeeId" />
						

						<div class="col-md-12">	
							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label"> Employee
									Name <span class="required">*</span> </label>
								<div class="col-md-8">
									<div class="input-group">
										<input class="form-control" name="employeeName" readonly required
											id="employeeName" onkeypress="goToNext(event,'fatherName')"
											value="${employeeMonthlyConsumption.employeeName}" /> <span
											class="input-group-btn"><a
											class="btn btn-mg btn-primary modal-with-move-anim"
											type="submit" href="#modalEmpSearch" id="showModalMessage">
												<i class="fa fa-search"></i>
										</a> </span>

									</div>
								</div>
							</div>

							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label">
									Designation </label>
								<div class="col-md-8">
									<input class="form-control" id="designation" name="designation"  readonly 
										onkeypress="goToNext(event,'fatherName')"
										value="${employeeMonthlyConsumption.designation}" />

								</div>
							</div>
						</div>

						<div class="col-md-12">	
							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label">
									Allowance / Deduction <span class="required">*</span> </label>
								<div class="col-md-8">
									<select class="form-control" name="allowanceDeductCategoryId" required
										id="allowanceDeductCategoryId" onkeypress="goToNext(event,'levelNo')">
										<option value="">Please Select One</option>
										<c:forEach items="${allowanceDeducCategoryList}" var="list">
											<option
												<c:if test="${list.id == employeeMonthlyConsumption.allowanceDeductCategoryId}">selected = "selected" </c:if>
												value="${list.id}">${list.name}</option>
										</c:forEach>
									</select>

								</div>
							</div>

						
							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label"> Applicable
									Date <span class="required">*</span> </label>
								<div class="col-md-8">
									<div class="input-group">
										<span class="input-group-addon"> <i
											class="fa fa-calendar"></i>
										</span> <input type="text" class="form-control" id="consumeDate" required
											onkeypress="goToNext(event,'contactNo')" name="consumeDate"
											placeholder="dd/mm/yyyy"
											value="${employeeMonthlyConsumption.consumeDate}"
											data-plugin-datepicker />
									</div>
								</div>
							</div>
						</div>
							
						<div class="col-sm-12 form-group">
							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label"> Purpose <span class="required">*</span>
								</label>
								<div class="col-md-8">
									<input class="form-control" id="purpose" name="purpose" required
										onkeypress="goToNext(event,'fatherName')"
										value="${employeeMonthlyConsumption.purpose}" />
								</div>
							</div>
							
							
							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label"> Amount <span class="required">*</span> </label>
								<div class="col-md-8">
									<input class="form-control" id="amount" name="amount" required
										value="${employeeMonthlyConsumption.amount}" 
										onkeypress="return isNumberKey(event)" />
										
										<div id="erramount"> </div>
								</div>
							</div>
						</div>


						</div>

					</div>

				</div>

				<footer class="panel-footer">
					<div class="row">
						<div class="btn_div col-sm-offset-1 ">
							<a href="empMonthlyConsumption"><button
									class="btn btn-sm btn  btn-default" type="button" role="button">
									<i class="fa fa-refresh"></i> Clear
								</button></a>
							<button
								class="btn btn-sm btn btn-animate btn-animate-side btn btn-primary"
								 type="submit">
								<span> <i class="fa fa-save" aria-hidden="true"></i> Save
								</span>
							</button>

						</div>


					</div>
				</footer>

			</form>
		</section>


		<section class="panel panel-featured panel-featured-primary">
			<header class="panel-heading">
				<h2 class="panel-title">Employee Consumption List</h2>

				<div class="panel-actions" id="resultDiv">
					<p id="result"></p>
				</div>
			</header>


			<div class=" panel-body">
				<div class="table-responsive">
					<div style="overflow: scroll; max-height: 300px;">
						<table
							class="table table-striped table-bordered table-condensed table-hover mb-none"
							role="grid">

							<thead>
								<tr>
									<th>#</th>
									<th>Employee Name</th>
									<th>Designation</th>
									<th>Allowance Deduction category</th>
									<th>Applicable date</th>
									<th>Amount</th>
									<th>Purpose</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>

								<c:if test="${!empty employeeMonthlyConsumptionList}">
									<%
										int i = 1;
									%>
									<c:forEach items="${employeeMonthlyConsumptionList}" var="list">
										<tr ondblclick="getEmployeeInfo('${list.encEmployeeId}')"
											style="cursor: pointer">
											<td>
												<%
													out.print(i);
															i++;
												%>
											</td>
											<td>${list.employeeName}</td>
											<td>${list.designation}</td>
											<td>${list.allowanceDeductCategoryName}</td>
											<td>${list.consumeDate}</td>
											<td>${list.amount}</td>
											<td>${list.purpose}</td>
											<td>
											<c:if test="${list.consumeSysdateYn == 'Y'}">
											<a href="#"
												onclick="getConsumeInfo('${list.encConsumeId}')"><button
														class="btn btn-xs btn-primary" type="button">
														<i class="fa fa-pencil"></i>
													</button></a></c:if>
										</tr>
									</c:forEach>

								</c:if>

								<c:if test="${! empty employeeMonthlyConsumptionNotFound}">
									<tr>
										<td colspan="9"><p>${employeeMonthlyConsumptionNotFound}</p></td>
									</tr>
								</c:if>




							</tbody>
						</table>

					</div>
				</div>
			</div>

		</section>

	</div>
</div>




<!-- end: page -->



<script>
	window.onload = load;
	function load() {
		document.getElementById('employeeName').focus();
	}
	
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
	function getEmployeeInfo(encEmpId,empName,designationName) {
		var link = "/pos/accounts/empMonthlyConsumption/getEmpMonthlyConsumptionList?encEmployeeId="
				+ encodeURIComponent(encEmpId) + "&employeeName=" + empName + "&designation=" + designationName;
		//alert(link);
		window.location = link;
	}
	
	
	function getConsumeInfo(encConsumeId) {
		var link = "/pos/accounts/empMonthlyConsumption/getConsumeInfo?encConsumeId=" + encodeURIComponent(encConsumeId);
		//alert(link);
		window.location = link;
	}
	
	
</script>
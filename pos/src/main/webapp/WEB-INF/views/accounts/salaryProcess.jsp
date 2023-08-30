<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<header class="page-header">
	<!-- <h2>Salary Process</h2> -->

	<div class="left-wrapper pull-left">
		<ol class="breadcrumbs">
			<li><a href="/pos/"> &nbsp; <i class="fa fa-home"></i>
			</a></li>
			<li><span>Accounts &nbsp;</span></li>
			<li><span>Salary Process &nbsp;</span></li>
		</ol>

	</div>
</header>

<!-- start: page -->

<div class="row">




	<div class="col-lg-12">

		<!-- message -->
		<div id="msg">
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



		<div id="modalEmpSearch"
			class="zoom-anim-dialog modal-block modal-block-lg mfp-hide">
			<!-- <section class="panel panel-featured panel-featured-primary"> -->
			<c:import url="/WEB-INF/views/hr/employeeSearch.jsp"></c:import>
			<footer class="panel-footer">
				<div class="row">
					<div class="col-md-12 text-right">
						<button class="btn btn-default modal-dismiss" id="modalClose">Close</button>
					</div>
				</div>
			</footer>
			<!-- </section> -->
		</div>

		<!-- message -->



		<form class="form-horizontal form-bordered"
			action="salaryProcess/salaryProcessSetup" autocomplete="off"
			id="form" method="post">
			<section class="panel panel-featured panel-featured-primary">

				<header class="panel-heading">
					<h2 class="panel-title">Salary Process</h2>
				</header>

				<div class="panel-body">

					<div class="row">

						<div class="form-group col-md-10">


							<input type="hidden" id="encEmployeeId" name="encEmployeeId"
								value="${salaryProcessModel.encEmployeeId}" />


							<div class="col-sm-12 form-group">

								<div class="col-sm-6 form-group">
									<label class="col-md-4 col-sm-3 control-label">
										Employee Name <span class="required">*</span> </label>
									<div class="col-md-8">
										<div class="input-group">
											<input class="form-control" name="employeeName" readonly
												required id="employeeName1"
												onkeypress="goToNext(event,'fatherName')"
												value="${salaryProcessModel.employeeName}" /> <span
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
										<input class="form-control" id="designation"
											name="designation" readonly
											onkeypress="goToNext(event,'fatherName')"
											value="${salaryProcessModel.designation}" />

									</div>
								</div>

							</div>

							<div class="col-sm-12 form-group">
								<div class="col-sm-6 form-group">
									<label class="col-md-4 col-sm-3 control-label">Year <span class="required">*</span> </label>
									<div class="col-md-8">
										<input autofocus type="text" class="form-control" required
											name="year" id="year" placeholder="yyyy"
											value="${salaryProcessModel.year}" maxlength="4"
											onkeypress="return isNumberKey(event)" />

										<div id="erryear"></div>

									</div>
								</div>

								<div class="col-sm-6 form-group">
									<label class="col-md-4 col-sm-3 control-label">Month <span class="required">*</span> </label>
									<div class="col-md-8">
										<select data-plugin-selectTwo class="form-control"
											name="month" id="month" required
											onkeypress="goToNext(event,'search')">
											<option value="">Please Select One</option>
											<option
												<c:if test="${salaryProcessModel.month == '01'}"> selected </c:if>
												value="01">January</option>
											<option
												<c:if test="${salaryProcessModel.month == '02'}"> selected </c:if>
												value="02">February</option>
											<option
												<c:if test="${salaryProcessModel.month == '03'}"> selected </c:if>
												value="03">March</option>
											<option
												<c:if test="${salaryProcessModel.month == '04'}"> selected </c:if>
												value="04">April</option>
											<option
												<c:if test="${salaryProcessModel.month == '05'}"> selected </c:if>
												value="05">May</option>
											<option
												<c:if test="${salaryProcessModel.month == '06'}"> selected </c:if>
												value="06">June</option>
											<option
												<c:if test="${salaryProcessModel.month == '07'}"> selected </c:if>
												value="07">July</option>
											<option
												<c:if test="${salaryProcessModel.month == '08'}"> selected </c:if>
												value="08">August</option>
											<option
												<c:if test="${salaryProcessModel.month == '09'}"> selected </c:if>
												value="09">September</option>
											<option
												<c:if test="${salaryProcessModel.month == '10'}"> selected </c:if>
												value="10">October</option>
											<option
												<c:if test="${salaryProcessModel.month == '11'}"> selected </c:if>
												value="11">November</option>
											<option
												<c:if test="${salaryProcessModel.month == '12'}"> selected </c:if>
												value="12">December</option>
										</select>
									</div>
								</div>
							</div>



						</div>


					</div>

				</div>
				<footer class="panel-footer">
					<div class="row">
						<div class="col-sm-10 col-sm-offset-0"></div>
						
						<div class="btn_div col-sm-offset-0 ">
							<a href="salaryProcess"><button class="btn btn-sm btn btn-default"
												type="button" role="button">
												<i class="fa fa-refresh"></i> Clear
											</button></a>
						</div>

						<div class="btn_div col-sm-offset-0 ">
							<button class="btn btn-sm btn btn-primary" type="submit"
								name="process" role="button">
								<i class="fa  fa-retweet"></i> Process
							</button>
						</div>

						<div class="btn_div col-sm-offset-0 ">
							<button class="btn btn-sm btn btn-primary" type="submit"
								name="search" role="button">
								<i class="fa  fa-search"></i> Search
							</button>
						</div>


					</div>
				</footer>

			</section>

			<section class="panel panel-featured panel-featured-primary"
				id="categorySectionId"
				<c:if test="${empty salaryProcessSetupList && empty salaryProcessSetupListNotFound}"> style="display: none;"  </c:if>>

				<header class="panel-heading">
					<h2 class="panel-title">Category List</h2>
				</header>

				<div class=" panel-body">
					<div class="table-responsive">
						<div style="overflow: scroll; max-height: 300px;">
							<table
								class="table table-striped table-bordered table-condensed table-hover mb-none">

								<thead>
									<tr>
										<th style="text-align: center;">Serial NO.</th>
										<th>Category Name</th>
										<th>Amount</th>
										<th>Remove</th>
									</tr>
								</thead>
								<tbody id="categoryList">

									<c:if test="${!empty salaryProcessSetupList}">
										<%
											int i = 1;
										%>

										<c:forEach items="${salaryProcessSetupList}" var="list"
											varStatus="status">
											<tr style="cursor: pointer">
												<td style="text-align: center;">
													<%
														out.print(i);
																i++;
													%>
												</td>

												<td><input class="flat" type="hidden"
													id="catId_${list.allowanceDeductCategoryId}"
													name="adCategoryIdList[${status.index}].encCategoryId"
													value="${list.encCategoryId}"> <input class="flat"
													type="hidden" id="catId_${list.allowanceDeductCategoryId}"
													name="adCategoryIdList[${status.index}].allowanceDeductCategoryId"
													value="${list.allowanceDeductCategoryId}"> <input
													class="flat" type="hidden"
													id="catId_${list.allowanceDeductCategoryId}"
													name="adCategoryIdList[${status.index}].allowanceDeductCategoryName"
													value="${list.allowanceDeductCategoryName}">

													${list.allowanceDeductCategoryName}</td>

												<td><input style="text-align: right;" class="flat"
													type="text" id="amount_${list.allowanceDeductCategoryId}"
													name="adCategoryIdList[${status.index}].amount"
													onkeypress="return isNumberKey(event)"
													value="${list.amount}"></td>

												<td>
													<%
														if (i > 2) {
																	out.print(
																			"<button class='btn-primary' type='button' onclick='removetr(this)'>&times;</button>");
																}
													%>
												</td>

											</tr>
										</c:forEach>

									</c:if>


									<c:if test="${! empty salaryProcessSetupListNotFound}">
										<tr>
											<td colspan="8"><p>${salaryProcessSetupListNotFound}</p></td>
										</tr>
									</c:if>


								</tbody>

							</table>

							<table
								class="table table-striped table-bordered table-condensed table-hover mb-none">


							</table>

						</div>
					</div>

				</div>

				<footer class="panel-footer">
					<div class="row">
						<div class="col-sm-12 col-sm-offset-0"></div>
						<div class="btn_div col-sm-offset-0 ">
							<!-- <a href="salaryProcess"><button class="btn btn-default"
									type="button" role="button">
									<i class="fa fa-refresh"></i> Clear
								</button></a> -->
							<button class="btn btn-primary" type="submit" role="button"
								name="save">
								<i class="fa fa-save"></i> SAVE
							</button>

						</div>


					</div>
				</footer>
			</section>


			<section class="panel panel-featured panel-featured-primary"
				id="processedDataSectionId"
				<c:if test="${empty salaryProcessSearchList && empty salaryProcessSearchListNotFound}"> style="display: none;" </c:if>>

				<header class="panel-heading">
					<h2 class="panel-title">Salary Processed Data</h2>
				</header>

				<div class=" panel-body">
					<div class="table-responsive">
						<div style="overflow: scroll; max-height: 300px;">
							<table
								class="table table-striped table-bordered table-condensed table-hover mb-none">

								<thead>
									<tr>
										<th style="text-align: center;">Serial NO.</th>
										<th>Category Name</th>
										<th style="text-align: center;">Amount</th>
										<th style="text-align: center;">Finalized</th>
									</tr>
								</thead>
								<tbody id="categoryList">

									<c:if test="${!empty salaryProcessSearchList}">
										<%
											int i = 1;
										%>

										<c:forEach items="${salaryProcessSearchList}" var="list"
											varStatus="status">
											<tr style="cursor: pointer">
												<td style="text-align: center;">
													<%
														out.print(i);
																i++;
													%>
												</td>

												<td>${list.allowanceDeductCategoryName}</td>

												<td
													style="text-align: right; <c:if test="${list.adCategoryTypeId == '2'}"> color: red; </c:if> ">
													${list.amount}</td>

												<td
													style="text-align: center; 
												<c:if test="${list.finalizedYN == 'YES'}"> color: green; </c:if>
												<c:if test="${list.finalizedYN == 'NO'}"> color:  #808000; </c:if> ">
													${list.finalizedYN}</td>

											</tr>
										</c:forEach>




									</c:if>




									<c:if test="${! empty salaryProcessSearchListNotFound}">
										<tr>
											<td colspan="8"><p>${salaryProcessSearchListNotFound}</p></td>
										</tr>
									</c:if>

								</tbody>

								<c:if test="${!empty salaryProcessSearchList}">
									<tbody>
										<tr style="cursor: pointer">
											<td></td>
											<td style="text-align: right; color: green;">Total</td>
											<td style="text-align: right;">${grossSalary}</td>
											<td style="text-align: center; 
											<c:choose>
											<c:when test="${finalizedYN == 'YES'}"> color: green; </c:when>
											<c:otherwise> color:  orange; </c:otherwise> 
											</c:choose>
											 "> 
											${finalizedYN} 
											</td>
										</tr>
									</tbody>
								</c:if>

							</table>

						</div>
					</div>



				</div>

				<footer class="panel-footer">
					<div class="row">
						<div class="col-sm-12 col-sm-offset-0"></div>
						<div class="btn_div col-sm-offset-0 ">

						<c:if test="${!empty encPayrollId && finalizedYN != 'YES'}">
						
						<input type="hidden" value="${encPayrollId}" name="encPayrollId"/>
						
							<button class="btn btn-primary" type="submit" role="button"
								name="finalize">
								<i class="fa fa-save"></i> Finalize
							</button>
						</c:if>

						</div>


					</div>
				</footer>
			</section>


		</form>



	</div>
</div>




<!-- end: page -->



<script>
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
	
	
	function getEmployeeInfo(encEmpId,empName,designationName) {
		$("#encEmployeeId").val(encEmpId);
		$("#employeeName1").val(empName);
		$("#designation").val(designationName);
		$("#modalClose").click();
	}
	
	function removetr(a){
		var whichtr = a.closest("tr");
	    whichtr.remove();  
		}
	
	
	
	
</script>
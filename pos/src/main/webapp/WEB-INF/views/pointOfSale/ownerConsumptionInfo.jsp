<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<header class="page-header">
	<!-- <h2>Employee Consumption Info</h2> -->

	<div class="left-wrapper pull-left">
		<ol class="breadcrumbs">
			<li><a href="/pos/"> &nbsp; <i class="fa fa-home"></i>
			</a></li>
			<li><span>Point Of Sale &nbsp;</span></li>
			<li><span>Owner Consumption Info &nbsp;</span></li>
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
			<form class="form-horizontal form-bordered" autocomplete="off"
				id="form" action="/pos/pointOfSale/ownerConsumptionSave" method="post">

				<header class="panel-heading">
					<h2 class="panel-title">Owner Consumption Info</h2>
				</header>

				<div class="panel-body">

					<div class="row">

						<div class="form-group col-md-10">

							<input type="hidden" value="${ownerConsumptionInfo.encConsumeId}" name="encConsumeId" /> 
							<%-- <input type="hidden" value="${ownerConsumptionInfo.encEmployeeId}" name="encEmployeeId" /> --%>

							<div class="col-md-12 form-group">
								<div class="col-sm-6 form-group">
									<label class="col-md-5 col-sm-5 control-label"> Owner
										Name <span class="required">*</span>
									</label>
									<div class="col-md-7 col-sm-7 col-xs-12">
										<select data-plugin-selectTwo class="form-control"
											name="employeeId" required id="employeeId"
											onkeypress="goToNext(event,'levelNo')"
											onchange="getOwnerConsumptionList(this.value)">
											<option value="">Please Select One</option>
											<c:forEach items="${ownerList}" var="list">
												<option
													<c:if test="${list.employeeId == ownerConsumptionInfo.employeeId}">selected = "selected" </c:if>
													value="${list.employeeId}">${list.firstName}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="col-sm-6 form-group">
									<label class="col-md-5 col-sm-5 control-label"> Consume
										Date <span class="required">*</span>
									</label>
									<div class="col-md-7">
										<div class="input-group">
											<span class="input-group-addon"> <i
												class="fa fa-calendar"></i>
											</span> <input type="text" class="form-control" id="consumeDate"
												required onkeypress="goToNext(event,'contactNo')"
												name="consumeDate" placeholder="dd/mm/yyyy"
												data-plugin-masked-input data-input-mask="99/99/9999"
												value="${ownerConsumptionInfo.consumeDate}"
												data-plugin-datepicker />
										</div>
									</div>
								</div>



							</div>

							<div class="col-sm-12 form-group">

								<div class="col-sm-6 form-group">
									<label class="control-label col-md-5 col-sm-5 col-xs-12">Item
										Name <span class="required">*</span>
									</label>
									<div class="col-md-7 col-sm-7 col-xs-12">
										<select data-plugin-selectTwo class="form-control" id="itemId"
											name="itemId" required>
											<option value="">Select One</option>
											<c:forEach items="${itemList}" var="list">
												<option
													<c:if test="${list.itemId == ownerConsumptionInfo.itemId}">selected = "selected" </c:if>
													value="${list.itemId}">${list.itemName}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="col-sm-6 form-group">
									<label class="col-md-5 control-label" for="quantity">Quantity
										<span class="required">*</span>
									</label>
									<div class="col-md-7 col-sm-7 col-xs-12">
										<input type="text" required class="form-control mandatory"
											onblur="orderAddList()" name="quantity" id="quantity"
											onkeydown="return isNumberKey(event)"
											value="${ownerConsumptionInfo.quantity}">

										<div id="errquantity"></div>

									</div>

								</div>


							</div>

							<div class="col-sm-12 form-group">

								<div class="col-sm-6 form-group">
									<label class="control-label col-md-5 col-sm-5 col-xs-12">
										Remarks </label>
									<div class="col-md-7 col-sm-7 col-xs-12">
										<input class="form-control" id="remarks" name="remarks"
											onkeypress="goToNext(event,'fatherName')"
											value="${ownerConsumptionInfo.remarks}" />
									</div>
								</div>


							</div>


						</div>

					</div>

				</div>

				<footer class="panel-footer">
					<div class="row">
						<div class="btn_div col-sm-offset-1 ">
							<a href="ownerConsumptionInfo"><button
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
				<h2 class="panel-title">Owner Consumption List</h2>

				<div class="panel-actions" id="resultDiv">
					<p id="result"></p>
				</div>
			</header>


			<div class=" panel-body">
				<div class="table-responsive">
					<div style="overflow: scroll; max-height: 1000px;">
						<table
							class="table table-striped table-bordered table-condensed table-hover mb-none"
							id="ownerConsumeDataTable" role="grid">

							<thead>
								<tr>
									<th>#</th>
									<th>Consume Date</th>
									<th>Item Name</th>
									<th>Quantity</th>
									<th>Remarks</th>
									<th>Update By</th>
									<th>Edit</th>
								</tr>
							</thead>

							<tbody id="ownerConsumeData">

								<c:if test="${!empty ownerConsumptionInfoList}">
									<%
										int i = 1;
									%>

									<c:forEach items="${ownerConsumptionInfoList}" var="list">
										<tr>
											<td style="text-align: center;">
												<%
													out.print(i);
												%>
											</td>



											<td style="text-align: left;">${list.consumeDate}</td>
											<td style="text-align: left;">${list.itemName}</td>
											<td style="text-align: center;">${list.quantity}</td>
											<td style="text-align: left;">${list.remarks}</td>
											<td style="text-align: left;">${list.employeeName}</td>

											<td><c:if test="${list.editable == 'Y'}">
													<a href="#"
														onclick="getOwnerConsumeInfo('${list.encConsumeId}')"><button
															class="btn btn-xs btn-primary" type="button">
															<i class="fa fa-pencil"></i>
														</button></a>
												</c:if></td>

										</tr>
										<%
											i = i + 1;
										%>

									</c:forEach>

								</c:if>

								<c:if test="${! empty ownerConsumptionInfoListNotFound}">
									<tr>
										<td colspan="7" class="hiddenRow"><div>${ownerConsumptionInfoListNotFound}</div></td>
										<td style="display: none;"></td>
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

		</section>

	</div>
</div>




<!-- end: page -->



<script>

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

function getOwnerConsumptionList(id) {

	var link = "/pos/accounts/ownerConsumptionInfo/getOwnerConsumptionList";
	//var reservationDate = $("#reservationDate").val();
	
	$.ajax({
		type : "POST",
		data : "employeeId=" +id,
		url : link,
		async : true,

		success : function(data) {
			$("#ownerConsumeData").html(data);
			
			$("#ownerConsumeDataTable").DataTable();
			
		},

		error : function(data) {
			alert('Error!!!')
		}
	});
};
		
	
	function getOwnerConsumeInfo(encConsumeId) {
		var link = "/pos/accounts/ownerConsumptionInfo/getOwnerConsumption?encConsumeId=" + encodeURIComponent(encConsumeId);
		//alert(link);
		window.location = link;
	}

	/* window.onload = load;
	function load() {
		document.getElementById('employeeName').focus();
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
	} */
	
	/* window.onload = load();

	function load() {
		var empId = $("#employeeId").val();
		
		if(empId !='') {
			$("#ownerConsumeDataTable").DataTable();
		}
	}  */
	
	
</script>
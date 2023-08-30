<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.totalHour {
	color: #CC0000;
	font-size: 22px;
	font-weight: 700;
}
</style>

<header class="page-header">
	<!-- <h2>Personal Identification</h2> -->

	<div class="left-wrapper pull-left">
		<ol class="breadcrumbs">
			<li><a href="/pos/"> &nbsp;<i class="fa fa-home"></i></a></li>
			<li><span>Reports &nbsp;</span></li>
			<li><span>Stock Summary Report</span></li>
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

		<form class="form-horizontal form-bordered" action="viewreport" autocomplete="off"
			target="_blank" method="post">

			<%-- <div class="form-group col-md-12">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input type="hidden"
					id="encEffortTrackingId" name="encEffortTrackingId"
					value="${taskTrackingInfo.encEffortTrackingId}" />
			</div> --%>

			<!-- start: page -->
			<section class="panel panel-featured panel-featured-primary">
				<header class="panel-heading">
					<h2 class="panel-title">Stock Summary Report</h2>
				</header>
				<div class="panel-body">
					<br />
					<div class="form-group col-md-12">
						<div class="col-sm-6 form-group">
							<label class="col-md-5 col-sm-3 control-label"> From Date
								<span class="required"> * </span>
							</label>
							<div class="col-md-7 col-sm-7 col-xs-12">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="fa fa-calendar"></i>
									</span> <input type="hidden" name="reportCode" value="601"> <input
										type="text" data-plugin-datepicker id="fromDate"
										name="fromDate" class="form-control mandatory" value="">
								</div>
							</div>
						</div>

						 <div class="col-sm-6 form-group">
							<label class="col-md-5 col-sm-3 control-label"> To Date <span
								class="required"> * </span>
							</label>
							<div class="col-md-7 col-sm-7 col-xs-12">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="fa fa-calendar"></i>
									</span> <input type="text" data-plugin-datepicker id="toDate"
										name="toDate" class="form-control mandatory" value="">
								</div>
							</div>
						</div> 

					</div>
				</div>

				<footer class="panel-footer">
					<div class="row">
						<div class="col-sm-offset-5">
							<button class="btn btn-primary" type="submit">Print</button>
						</div>
					</div>
				</footer>

			</section>

		</form>
		<!-- end: page -->

	</div>
</div>
<script>
	/* 	function inventoryReport() {
	 var fromDate = $("#fromDate").val();
	 var toDate = $("#toDate").val();
	 var inventoryTypeId = $("#inventoryTypeId").val();
	 var productId = $("#productId").val();
	 alert(fromDate);
	 alert(toDate);
	 if (fromDate == '') {
	 content = "<div class='alert alert-danger'>"
	 + "<button class='close' aria-hidden='true' data-dismiss='alert' "
+ "type='button'>&times;</button>"
	 + "<strong>Select From Date !</strong>" + "</div>"

	 $("#msg").html(content);
	 } else if (toDate == '') {
	 content = "<div class='alert alert-danger'>"
	 + "<button class='close' aria-hidden='true' data-dismiss='alert' "
+ "type='button'>&times;</button>"
	 + "<strong>Select To Date !</strong>" + "</div>"

	 $("#msg").html(content);
	 } else {
	 var url = "/pos/reports/viewreport?fromDate=" + fromDate
	 + "&toDate=" + toDate + +"&inventoryTypeId="
	 + inventoryTypeId + +"&productId=" + productId
	 "&reportCode=" + "201";
	 window.open(url, '_blank');
	 }

	 } */
</script>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<header class="page-header">
	<!-- <h2>Personal Identification</h2> -->

	<div class="left-wrapper pull-left">
		<ol class="breadcrumbs">
			<li><a href="/pos/"> &nbsp;<i class="fa fa-home"></i></a></li>
			<li><span>Venue Reservation &nbsp;</span></li>
			<li><span>Venue Reservation View &nbsp;</span></li>
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
			action="/pos/inventory/saveProduct" id="form"
			onkeypress="return event.keyCode != 13;">
			<section class="panel panel-featured panel-featured-primary">
			
				<header class="panel-heading" style="padding: 12px; margin: 0px">
			
			<div class="panel-actions">
						<a href="/pos/venueReservation/reserveVenue"><button
								class="btn btn-primary btn-round" type="button">
								<span><i class="fa fa-plus" aria-hidden="true"></i> Reserve A Venue </span>
							</button></a>
					</div>
			
					<h2 class="panel-title">Reserved Venue Search</h2>
				</header>

				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">

							<div class="form-group col-md-6">
								<label class="col-md-6 control-label" for="reservationDate">Reserve
									Date </label>
								<div class="col-md-6">
									<div class="input-group mb-md">
										<input type="text" data-plugin-datepicker=""
											id="reservationDate" placeholder="DD/MM/YYYY" required
											class="form-control" name="reservationDate"
											value="${venueReservationModel.reservationDate}">
									</div>

								</div>
							</div>

							<div class="form-group col-md-6">
								<label class="col-md-5 control-label" for="venueId">Venue
								</label>
								<div class="col-md-6">
									<select class="form-control input-sm mb-md" required
										name="venueId" id="venueId">
										<option value="">Please Select</option>
										<c:forEach items="${venueList}" var="list">
											<option
												<c:if test="${list.id == venueReservationModel.venueId}">selected="selected"</c:if>
												value="${list.id}">${list.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>

						</div>
					</div>
				</div>

				<footer class="panel-footer">
					<div class="row">
						<div class="btn_div col-sm-offset-0 ">
						
							<a href="/pos/venueReservation/reserveVenueView"><button
									class="btn btn-sm btn  btn-default" type="button" role="button">
									<i class="fa fa-refresh"></i>
									Clear</button></a>
									
							<button class="btn btn-sm btn btn-primary"  onclick="getVenueReservedList()" type="button">
							<i class="fa fa-search"></i> Search</button>
						
						</div>

					</div>
				</footer>

			</section>

			<section class="panel panel-featured panel-featured-primary">
				<header class="panel-heading">
					<h2 class="panel-title">Reserved Venue List</h2>

					<div class="panel-actions" id="resultDiv">
						<p id="result"></p>
					</div>
				</header>


				<div class=" panel-body">
					<div class="table-responsive">
						<div style="overflow: scroll; max-height: 300px;">
							<table class="table table-striped table-bordered table-condensed table-hover mb-none" id="venueReservedListTable" role="grid">

								<thead>
									<tr>
										<th style="text-align:center;">#</th>
										<th style="text-align:center;">Venue </th>
										<th style="text-align:center;">Programme</th>
										<th style="text-align:center;">Reserved Date</th>
										<th style="text-align:center;">Start Time</th>
										<th style="text-align:center;">End Time</th>
										<!-- <th>Employee Level</th> -->
										<th style="text-align:center;">Status</th>
										<th style="text-align:center;">Completed</th>
										<th style="text-align:center;">Select</th>
									</tr>
								</thead>
								<tbody id="venueReservedList"></tbody>
							</table>

						</div>
					</div>
				</div>

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



<script
	src="<c:url value="/resources/javascripts/googleapi.jquery.min.js" />"></script>
<script>

function getVenueReservedList() {

	var link = "/pos/venueReservation/getVenueReservedList";
	var reservationDate = $("#reservationDate").val();
	var venueId = $("#venueId").val();

	$.ajax({
		type : "POST",
		data : "reservationDate=" +reservationDate+ "&venueId=" + venueId,
		url : link,
		async : true,

		success : function(data) {
			//alert('ssssss!!!')
			$("#venueReservedList").html(data);
			$("#venueReservedListTable").DataTable();
		},

		error : function(data) {
			alert('Error!!!')
		}
	});
};




function getReservedVenue(id){
		var link = "/pos/venueReservation/getReservedVenue?encReservationId="+encodeURIComponent(id);
			//alert(link);
	 	window.location = link;
	}

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
 







</script>


<!-- end: page -->
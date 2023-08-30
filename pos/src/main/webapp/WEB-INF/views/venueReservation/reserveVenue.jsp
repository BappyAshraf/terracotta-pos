<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<header class="page-header">
	<!-- <h2>Personal Identification</h2> -->

	<div class="left-wrapper pull-left">
		<ol class="breadcrumbs">
			<li><a href="/pos/"> &nbsp;<i class="fa fa-home"></i></a></li>
			<li><span>Venue Reservation &nbsp;</span></li>
			<li><span>Reserve a venue &nbsp;</span></li>
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

		<form class="form-horizontal form-bordered" method="post"
			action="/pos/venueReservation/saveVenueReservation" id="form"
			autocomplete="off"
			onkeypress="return event.keyCode != 13;">
			<section class="panel panel-featured panel-featured-primary">

				<header class="panel-heading" style="padding: 7px; margin: 0px">
					<h2 class="panel-title">Reserve A Venue</h2>
				</header>

				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">

							<input type="hidden" name="encReservationId"
								id="encReservationId"
								value="${venueReservationModel.encReservationId}">

							<div class="form-group col-md-6">
								<label class="col-md-5 control-label" for="venueId">
									Venue <span class="required">*</span> </label>
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

							<div class="form-group col-md-6">
								<label class="col-md-5 control-label" for="programmeTypeId">
									Programme <span class="required">*</span> </label>
								<div class="col-md-6">
									<select class="form-control input-sm mb-md" required
										name="programmeTypeId" id="programmeTypeId">
										<option value="">Please Select</option>
										<c:forEach items="${programmeList}" var="list">
											<option
												<c:if test="${list.id == venueReservationModel.programmeTypeId}">selected="selected"</c:if>
												value="${list.id}">${list.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="col-md-12">
								<div class="form-group col-md-6">
									<label class="col-md-5 control-label" for="reservationDate">Reserve
										Date <span class="required">*</span> </label>
									<div class="col-md-6">
										<input type="text" data-plugin-datepicker=""
											id="reservationDate" placeholder="DD/MM/YYYY" required
											class="form-control" name="reservationDate"
											value="${venueReservationModel.reservationDate}">
									</div>
								</div>

								<div class="form-group col-md-6">
									<label class="col-md-5 control-label" for="totalGuest">Total
										Guest Approximately </label>
									<div class="col-md-6">
										<input type="text" id="totalGuest" 
											class="form-control" name="totalGuest"
											 onkeydown="return isNumberKey(event)"
											value="${venueReservationModel.totalGuest}">
											
											<div id="errtotalGuest"></div>
											
									</div>
								</div>
							</div>

							<div class="col-md-12">
								<div class="form-group col-md-6">
									<label class="col-md-5 control-label" for="startTime">Start
										Time <span class="required">*</span> </label>
									<div class="col-md-6">
										<input type="text" id="startTime" required
											class="form-control" name="startTime"
											value="${venueReservationModel.startTime}">
											
											<%-- <input type="text" class="form-control" id="startTime"
											name="startTime" value="${venueReservationModel.startTime}"
										onchange="getTimeDiff()" data-plugin-timepicker
										data-plugin-options='{ "minuteStep": 5 }' style="width: 85px"
										required /> --%>
									</div>
								</div>

								<div class="form-group col-md-6">
									<label class="col-md-5 control-label" for="endTime">End
										Time  <span class="required">*</span> </label>
									<div class="col-md-6">
										<input type="text" id="endTime" required class="form-control"
											name="endTime" value="${venueReservationModel.endTime}">
									</div>
								</div>
							</div>


							<div class="col-md-12">
								<div class="form-group col-md-6">
									<label class="col-md-5 control-label" for="membershipId">Membership
										Id </label>
									<div class="col-md-6">
										<input type="text" id="membershipId" 
											class="form-control" name="membershipId"
											value="${venueReservationModel.membershipId}">
											
											<div id="errmembershipId"></div>
											
									</div>
								</div>

								<div class="form-group col-md-6">
									<label class="col-md-5 control-label" for="setProgrammeYN">Set
										Programme <span class="required">*</span> </label>
									<div class="col-md-6">
										<select class="form-control input-sm mb-md" name="setProgrammeYN" id="setProgrammeYN" required
											onkeypress="goToNext(event,'issuedBy')">
											<option value="">Please Select One</option>
											<option value="Y"  <c:if test="${venueReservationModel.setProgrammeYN == 'Y'}">selected</c:if>>YES</option>
											<option value="N"  <c:if test="${venueReservationModel.setProgrammeYN == 'N'}">selected</c:if>>NO</option>
										</select>

									</div>
								</div>
							</div>


							<div class="col-md-12">
								<div class="form-group col-md-6">
									<label class="col-md-5 control-label" for="totalAmount">Total
										Amount  </label>
									<div class="col-md-6">
										<input type="text" id="totalAmount" 
											class="form-control" name="totalAmount"
											 onkeydown="return isNumberKey(event)"
											value="${venueReservationModel.totalAmount}">
											
											<div id="errtotalAmount"></div>
											
									</div>
								</div>

								<div class="form-group col-md-6">
									<label class="col-md-5 control-label" for="advanceAmount">Advance
										Amount <span class="required">*</span> </label>
									<div class="col-md-6">
										<input type="text" id="advanceAmount" required
											class="form-control" name="advanceAmount"
											 onkeydown="return isNumberKey(event)"
											value="${venueReservationModel.advanceAmount}">
											
											<div id="erradvanceAmount"></div>
											
									</div>
								</div>
							</div>


							<div class="col-md-12">
								<div class="form-group col-md-6">
									<label class="col-md-5 control-label" for="personId">Status <span class="required">*</span>
									</label>
									<div class="col-md-6">
										<div class="col-md-6">
											<div class="radio-custom radio-primary">
												<input type="radio" id="statusYN1" name="statusYN" required
													<c:if test="${empty venueReservationModel.statusYN || venueReservationModel.statusYN == 'Y'}">checked</c:if> value="Y"
													onkeypress="goToNext(event,'activityType2')" /> <label
													for="statusYN1">Enable</label>
											</div>
										</div>
										<div class="col-md-6">
											<div class="radio-custom radio-primary">
												<input type="radio" id="statusYN2" name="statusYN"
													<c:if test="${venueReservationModel.statusYN == 'N'}">checked</c:if> value="N"
													onkeypress="goToNext(event,'issueDate')" /> <label
													for="statusYN2">Disable</label>
											</div>
										</div>
									</div>
								</div>

								<div class="form-group col-md-6">
									<label class="col-md-5 control-label" for="remarks">Remarks
									</label>
									<div class="col-md-6">
										<textarea class="form-control" id="remarks"
											onkeypress="goToNext(event,'enabledYN1')" name="remarks"> ${venueReservationModel.remarks}    </textarea>
									</div>
								</div>
							</div>

						</div>
					</div>

				</div>

				<!--  <footer class="panel-footer">
					<div class="row">
						<div class="btn_div col-sm-offset-0 ">
							<button class="btn btn-primary"  onclick="getVenueReservedList()" type="button">Save</button>
							<a href="/pos/venueReservation/reserveVenue"><button
									class="btn btn-primary" type="button" role="button">
									Clear</button></a>
						</div>

					</div>
				</footer>  -->

			</section>


			<section class="panel panel-featured panel-featured-primary">

				<header class="panel-heading" style="padding: 7px; margin: 0px">
					<h2 class="panel-title">Contact Info</h2>
				</header>

				<div class="panel-body">
					<div class="row">


						<div class="col-md-12">
							<div class="form-group col-md-6">
								<label class="col-md-5 control-label" for="conPersonName">Contact
									Person name <span class="required">*</span> </label>
								<div class="col-md-6">
									<input type="text" id="conPersonName" required
										class="form-control" name="conPersonName"
										value="${venueReservationModel.conPersonName}">
								</div>
							</div>

							<div class="form-group col-md-6">
								<label class="col-md-5 control-label" for="email">Email
								</label>
								<div class="col-md-6">
									<input type="text" id="email"  class="form-control"
										name="email" value="${venueReservationModel.email}">
								</div>
							</div>
						</div>


						<div class="col-md-12">
							<div class="form-group col-md-6">
								<label class="col-md-5 control-label" for="phoneNo">Phone
									no. </label>
								<div class="col-md-6">
									<input type="text" id="phoneNo"  class="form-control"
									    onkeydown="return isNumberKey(event)"
										name="phoneNo" value="${venueReservationModel.phoneNo}">
										
										<div id="errphoneNo"></div>
										
								</div>
							</div>

							<div class="form-group col-md-6">
								<label class="col-md-5 control-label" for="mobileNo">Mobile
									No.  <span class="required">*</span> </label>
								<div class="col-md-6">
									<input type="text" id="mobileNo" required class="form-control"
									    maxlength="11"
									    onkeydown="return isNumberKey(event)"
										name="mobileNo" value="${venueReservationModel.mobileNo}">
										
									<div id="errmobileNo"></div>
											
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="form-group col-md-6">
								<label class="col-md-5 control-label" for="personAddress">Address <span class="required">*</span>
								</label>
								<div class="col-md-6">
									<input type="text" id="personAddress" required
										class="form-control" name="personAddress"
										value="${venueReservationModel.personAddress}">
								</div>
							</div>
						</div>

					</div>
				</div>

				<footer class="panel-footer">
					<div class="row">
						<div class="btn_div col-sm-offset-0 ">
						
						<a href="/pos/venueReservation/reserveVenue"><button
									class="btn btn-sm btn  btn-default" type="button" role="button">
									<i class="fa fa-refresh"></i>
									Clear</button></a>
									
							<button class="btn btn-sm btn btn-animate btn-animate-side btn btn-primary" type="submit">
							<i class="fa fa-save"> </i>  Save</button>
									
						</div>

					</div>
				</footer>

			</section>

		</form>

	</div>
</div>




<script
	src="<c:url value="/resources/javascripts/googleapi.jquery.min.js" />">
</script>

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
 

</script>


<!-- end: page -->
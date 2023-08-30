<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<header class="page-header" style="left: 0;">
	<h2>Password Change</h2>

	<div class="right-wrapper pull-left">
		<ol class="breadcrumbs">
			<li><a href="/pos/"> <i class="fa fa-home"></i>
			</a></li>
			<li><span>Password Change &nbsp;</span></li>
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

		<!-- message -->



		<section class="panel panel-featured panel-featured-primary">
			<form class="form-horizontal form-bordered"
				action="/pos/changePassword" method="post"
				onkeypress="return event.keyCode != 13;" id="form">


				<a href="#modalMessage"
					class="btn btn-sm btn-primary modal-with-zoom-anim" type="button"
					id="showModalMessage" style="display: none"></a>




				<header class="panel-heading">
					<div class="panel-actions">
						<a href="/pos/"><button class="btn btn-primary btn-round"
								type="button">
								<span><i class="fa fa-home" aria-hidden="true"></i> Home</span>
							</button></a>
					</div>

					<h2 class="panel-title">Change Password</h2>
				</header>

				<div class="panel-body">

					<div class="row">

						<div class="form-group col-md-6">
							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label">Employee
									ID</label>
								<div class="col-md-8">
									<label>: <b>${employeeid}</b></label>
								</div>
							</div>


							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label">User Name</label>
								<div class="col-md-8">
									<label>: <b>${username}</b></label>
								</div>
							</div>

							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label">Designation</label>
								<div class="col-md-8">
									<label>: <b>${designationName}</b></label>
								</div>
							</div>


							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label">Branch</label>
								<div class="col-md-8">
									<label>: <b>${branchName}</b></label>
								</div>
							</div>

						</div>

						<div class="form-group col-md-6"></div>


						<div class="form-group col-md-6"></div>

						<div class="form-group col-md-12">

							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />


							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label">Current
									Password</label>
								<div class="col-md-8">
									<input type="password" class="form-control"
										onkeypress="goToNext(event, 'newPassword')"
										id="currentPassword" name="currentPassword" />
								</div>
							</div>


						</div>

						<div class="form-group col-md-12">

							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label">New
									Password</label>
								<div class="col-md-8">
									<input type="password" class="form-control" id="newPassword"
										onkeypress="goToNext(event, 'confirmPassword')"
										name="newPassword" onkeypress="goToNext(event,'accName')" />
								</div>
							</div>






						</div>
						<div class="form-group col-md-12">

							<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label">Confirm
									Password</label>
								<div class="col-md-8">
									<input type="password" class="form-control"
										onkeypress="goToNext(event,'submit')" id="confirmPassword"
										name="confirmPassword" />
								</div>


							</div>
						</div>




					</div>

				</div>

				<footer class="panel-footer">
					<div class="row">
						<div class="col-sm-12 col-sm-offset-0"></div>
						<div class="btn_div col-sm-offset-0 ">
							<a href="changePassword"><button class="btn btn-default"
									type="button" role="button">
									<i class="fa fa-refresh"></i> Clear
								</button></a>
							<button class="btn btn-primary" type="submit" id="submit">
								<i class="fa fa-save"></i> Change Password
							</button>

						</div>


					</div>
				</footer>
			</form>


		</section>
	</div>
</div>









<script>
	window.onload = load;
	function load() {
		document.getElementById('currentPassword').focus();
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
</script>




<!-- End Panel Floating Labels -->






<!-- End Page -->
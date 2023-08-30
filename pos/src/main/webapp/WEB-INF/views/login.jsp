<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!doctype html>
<html class="fixed">
<head>

<!-- Basic -->
<title>::.. Restaurant Management System ..::</title>
<meta charset="UTF-8">

<meta name="keywords" content="HTML5 Admin Template" />
<meta name="description"
	content="Porto Admin - Responsive HTML5 Template">
<meta name="author" content="okler.net">

<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="icon" href="<c:url value="/resources/images/tera_final_logo2.png" />"
	type="image/x-icon" />
<!-- Web Fonts  -->
<!-- <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">
 -->
<!-- Vendor CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.css" />" />
<link rel="stylesheet"
	href="<c:url value="/resources/vendor/font-awesome/css/font-awesome.css" />" />
<link rel="stylesheet"
	href="<c:url value="/resources/vendor/magnific-popup/magnific-popup.css" />" />
<link rel="stylesheet"
	href="<c:url value="/resources/vendor/bootstrap-datepicker/css/datepicker3.css" />" />

<!-- Theme CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/stylesheets/theme.css" />" />

<!-- Skin CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/stylesheets/skins/default.css" />" />

<!-- Theme Custom CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/stylesheets/theme-custom.css" />">

<!-- Head Libs -->
<script src="<c:url value="/resources/vendor/modernizr/modernizr.js"/>"></script>

</head>

<body>

	<!-- start: page -->

	<section class="body-sign">

		<div class="center-sign">
			<%-- <a href="/pos/" class="logo pull-left"> <img
				src="<c:url value="/resources/images/tera_final_logo2.png" />"
				height="80" alt="Terracotta" style="margin-top: -31px" />
			</a> --%>

			<div class="panel panel-sign">
				<div class="panel-title-sign mt-xl text-right">
					<h2 class="title text-uppercase text-bold m-none">
						<i class="fa fa-user mr-xs"></i> Sign In
					</h2>
				</div>
				<div class="panel-body">
					<form modelAttribute="loginInfo" action="/pos/validateUser"
						method="post">
						<div class="form-group mb-lg">
							<label>User Name</label>
							<div class="input-group input-group-icon">
								<input name="userName" type="text" class="form-control input-lg" />
								<span class="input-group-addon"> <span
									class="icon icon-lg"> <i class="fa fa-user"></i>
								</span>
								</span>
							</div>
						</div>

						<div class="form-group mb-lg">
							<div class="clearfix">
								<label class="pull-left">Password</label>
								<!-- <a href="pages-recover-password.html" class="pull-right">Lost Password?</a> -->
							</div>
							<div class="input-group input-group-icon">
								<input name="password" type="password"
									class="form-control input-lg" /> <span
									class="input-group-addon"> <span class="icon icon-lg">
										<i class="fa fa-lock"></i>
								</span>
								</span>
							</div>
						</div>

						<c:if test="${!empty displayMessage}">
							<div class="alert alert-danger">
								<button class="close" aria-hidden="true" data-dismiss="alert"
									type="button">&times;</button>
								<strong>${displayMessage}</strong>
							</div>
						</c:if>

						<div class="row">
							<div class="col-sm-8">
								<div class="checkbox-custom checkbox-default"></div>
							</div>

							<div class="col-sm-4 text-right">
								<button type="submit" class="btn btn-primary hidden-xs"
									name="submit">Sign In</button>
							</div>
						</div>



					</form>
				</div>
			</div>



			<p class="text-center text-muted mt-md mb-md">&copy; All Right
				Reserved by ASHRAF IT Ltd. 2019</p>
		</div>
	</section>
	<!-- end: page -->

	<!-- Vendor -->
	<script src="<c:url value="/resources/vendor/jquery/jquery.js"/>"></script>
	<script
		src="<c:url value="/resources/vendor/jquery-browser-mobile/jquery.browser.mobile.js"/>"></script>
	<script
		src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.js"/>"></script>
	<script
		src="<c:url value="/resources/vendor/nanoscroller/nanoscroller.js"/>"></script>
	<script
		src="<c:url value="/resources/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"/>"></script>
	<script
		src="<c:url value="/resources/vendor/magnific-popup/magnific-popup.js"/>"></script>
	<script
		src="<c:url value="/resources/vendor/jquery-placeholder/jquery.placeholder.js"/>"></script>

	<!-- Theme Base, Components and Settings -->
	<script src="<c:url value="/resources/javascripts/theme.js"/>"></script>

	<!-- Theme Custom -->
	<script src="<c:url value="/resources/javascripts/theme.custom.js"/>"></script>

	<!-- Theme Initialization Files -->
	<script src="<c:url value="/resources/javascripts/theme.init.js" />"></script>
</html>






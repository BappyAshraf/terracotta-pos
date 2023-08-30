<%@page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!doctype html>
<html class="fixed">
	<head>
		
		<!-- Basic -->
		<meta charset="UTF-8">

		<title>::.. Terracotta Restaurant ..::</title>
		<meta name="keywords" content="HTML5 Admin Template" />
		<meta name="description" content="Porto Admin - Responsive HTML5 Template">
		<meta name="author" content="okler.net">
		<link rel="icon" href="<c:url value="/resources/images/tera_final_logo2.png" />" type="image/x-icon" />
		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

		<!-- Web Fonts  -->
		<!-- <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css"> -->

		<!-- Vendor CSS -->
		<link rel="stylesheet" href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.css" />"/>
		<link rel="stylesheet" href="<c:url value="/resources/vendor/font-awesome/css/font-awesome.css" />"/>
		<link rel="stylesheet" href="<c:url value="/resources/vendor/magnific-popup/magnific-popup.css" />"/>
		<link rel="stylesheet" href="<c:url value="/resources/vendor/bootstrap-datepicker/css/datepicker3.css" />"/>

		<!-- Specific Page Vendor CSS -->
		<link rel="stylesheet" href="<c:url value="/resources/vendor/select2/select2.css"  />"/>		<link rel="stylesheet" href="<c:url value="/resources/vendor/bootstrap-fileupload/bootstrap-fileupload.min.css" />"/>
		<link rel="stylesheet" href="<c:url value="/resources/vendor/jquery-datatables-bs3/assets/css/datatables.css" />" />

		<!-- Theme CSS -->
		<link rel="stylesheet" href="<c:url value="/resources/stylesheets/theme.css" />"/>

		<!-- Skin CSS -->
		<link rel="stylesheet" href="<c:url value="/resources/stylesheets/skins/default.css" />"/>

		<!-- Theme Custom CSS -->
		<link rel="stylesheet" href="<c:url value="${sessionScope.formColour}" />" />

		<!-- Head Libs -->
		<script src="<c:url value="/resources/vendor/modernizr/modernizr.js" />" ></script>
		
	</head>
	<body>
		<section class="body">

			<!-- start: header -->
			<header class="header">
				
				<tiles:insertAttribute name="header" />
				
			</header>
			<!-- end: header -->

			<div class="inner-wrapper">
				
				<section role="main" class="">
					

					<tiles:insertAttribute name="body" />     
				</section>
			</div>

			
		</section>

		<!-- Vendor -->
		<script src="<c:url value="/resources/vendor/jquery/jquery.js" />"></script>		<script src="<c:url value="/resources/vendor/jquery-browser-mobile/jquery.browser.mobile.js" />"></script>		<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.js" />"></script>		<script src="<c:url value="/resources/vendor/nanoscroller/nanoscroller.js" />"></script>		<script src="<c:url value="/resources/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js" />"></script>
		<script src="<c:url value="/resources/vendor/jquery-maskedinput/jquery.maskedinput.js" />"></script>		<script src="<c:url value="/resources/vendor/magnific-popup/magnific-popup.js" />"></script>		<script src="<c:url value="/resources/vendor/jquery-placeholder/jquery.placeholder.js" />"></script>
		<script src="<c:url value="/resources/javascripts/ui-elements/examples.modals.js" />"></script>
		
		<!-- Specific Page Vendor -->
		<script src="<c:url value="/resources/vendor/select2/select2.js" />"></script>		<script src="<c:url value="/resources/vendor/jquery-autosize/jquery.autosize.js" />"></script>
		<script src="<c:url value="/resources/vendor/jquery-validation/jquery.validate.js" />" ></script>
		<script src="<c:url value="/resources/vendor/jquery-datatables/media/js/jquery.dataTables.js" />"></script>
		<%-- <script src="<c:url value="/resources/vendor/jquery-datatables/extras/TableTools/js/dataTables.tableTools.min.js" />"></script> --%>
		<script src="<c:url value="/resources/vendor/jquery-datatables-bs3/assets/js/datatables.js" />"></script>		<%-- <script src="<c:url value="/resources/vendor/bootstrap-fileupload/bootstrap-fileupload.min.js" />" ></script> --%>
		
		<!-- Theme Base, Components and Settings -->
		<script src="<c:url value="/resources/javascripts/theme.js" />"></script>
		
		<!-- Theme Custom -->
		<script src="<c:url value="/resources/javascripts/theme.custom.js" />"></script>
		
		<!-- Theme Initialization Files -->
		<script src="<c:url value="/resources/javascripts/theme.init.js" />"></script>
		
		
		
		<script src="<c:url value="/resources/javascripts/forms/examples.validation.js" />"></script>
		<script src="<c:url value="/resources/javascripts/tables/examples.datatables.default.js" />"></script>
		
		

	</body>
</html>
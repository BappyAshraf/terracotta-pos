<%@page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


			<header class="page-header">
						<!-- <h2>User Info View </h2> -->
					
						<div class="left-wrapper pull-left">
							<ol class="breadcrumbs">
								<li><a href="/pos/"> &nbsp; <i class="fa fa-home"></i> </a> </li>
								<li><span>HR &nbsp;</span></li>
								<li><span>Employee Info View &nbsp;</span></li>
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
							
							
				<!--  --><!--  -->
			<c:import url="/WEB-INF/views/hr/employeeSearch.jsp"></c:import>


	</div>
						</div>
						
						
						
						
					<!-- end: page -->
					
					
					
					<script>
window.onload = load;
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

	

	function getEmployeeInfo(id){
		var link = "/pos/hr/employeeInfoView/getEmployeeInfo?encEmployeeId="+encodeURIComponent(id);
			//	alert(link);
	 	window.location = link;
	}
	
	
	
	
</script>
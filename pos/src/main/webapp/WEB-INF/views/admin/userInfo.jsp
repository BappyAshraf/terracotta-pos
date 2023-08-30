<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




	<header class="page-header">
						<!-- <h2>User Info </h2> -->
					
						<div class="left-wrapper pull-left">
							<ol class="breadcrumbs">
								<li><a href="/pos/"> &nbsp; <i class="fa fa-home"></i> </a> </li>
								<li><span>Admin &nbsp;</span></li>
								<li><span>User Info &nbsp;</span></li>
							</ol>
					
							<!-- <a class="sidebar-right-toggle" data-open="sidebar-right"><i class="fa fa-chevron-left"></i></a> -->
						</div>
	</header>


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
		
		
		<div id="modalEmpSearch" class="zoom-anim-dialog modal-block modal-block-lg mfp-hide">
					<!-- <section class="panel panel-featured panel-featured-primary"> -->
						<c:import url="/WEB-INF/views/admin/employeeSearch.jsp"></c:import>
						<footer class="panel-footer">
							<div class="row">
								<div class="col-md-12 text-right">
									<button class="btn btn-default modal-dismiss" >Close</button>
								</div>
							</div>
						</footer>
					<!-- </section> -->
				</div>
					
		
				 
							<section class="panel panel-featured panel-featured-primary">
					<form class="form-horizontal form-bordered" action="/pos/admin/saveUserInfo" method="post" id="form" onkeypress="return event.keyCode != 13;" >			
					
					<header class="panel-heading">
					<div class="panel-actions">
						<a href="/pos/admin/userInfoView"><button
								class="btn btn-primary btn-round" type="button">
								<span><i class="fa fa-arrow-left" aria-hidden="true"></i> Back</span>
							</button></a>
					</div>

					<h2 class="panel-title">User Information</h2>
					</header>
					
						<div class="panel-body">

						<div class="row">
						
						<%-- 	<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" /> --%>

							<input type="hidden" id="encUserId" name="encUserId"
								value="${userInfoForm.encUserId}" />
								
							<input type="hidden" id="encEmployeeId" name="encEmployeeId"
								value="${userInfoForm.encEmployeeId}" />

							<input type="hidden" id="userNameValidity"
								value="Y" />


<c:if test="${empty  userInfoForm.encUserId}">
<div class="col-sm-12 form-group">
									<a href="#modalEmpSearch" class="btn btn-sm btn-primary modal-with-zoom-anim" type="button" id="showModalMessage" ><span> <i class="fa fa-search" aria-hidden="true"></i> Select Employee</span></a>
							</div>
							</c:if>

		<div class="col-sm-12 form-group">
							<div class="col-sm-6 form-group">
									<label class="col-md-4 col-sm-3 control-label">Employee Name
									<span class="required">*</span> </label>
									<div class="col-md-8">
										<input autofocus type="text" class="form-control" required  name="employeeName" id="employeeName" value="${userInfoForm.employeeName}" readonly/>
									</div>
							</div>

							<div class="col-sm-6 form-group">
									<label class="col-md-4 col-sm-3 control-label">Designation
									</label>
									<div class="col-md-8">
										<input type="text" class="form-control"
											name="designationName" id="designationName" value="${userInfoForm.designationName}" readonly/>
									</div>
							</div>
			</div>



			<div class="col-sm-12 form-group">
							<div class="col-sm-6 form-group">
									<label class="col-md-4 col-sm-3 control-label">User Name
									<span class="required">*</span> </label>
									<div class="col-md-8">
										<input type="text" class="form-control" <c:if test="${empty userInfoForm.encUserId}">onblur="check_username()"</c:if> onkeypress="goToNext(event,'password')"
											required name="userName" id="userName"  value="${userInfoForm.userName}" 
											<c:if test="${!empty userInfoForm.encUserId}"> readonly </c:if>    /> <!-- onkeypress="checkUserName(event,'password')" -->
											<div id="username_availability_result"></div>
									</div>
							</div>
							
							
							<div class="col-sm-6 form-group">
									<label class="col-md-4 col-sm-3 control-label">Password
									<span class="required">*</span> </label>
									<div class="col-md-8">
										<input type="password" class="form-control" onkeypress="goToNext(event,'authorityId')"
											<c:if test="${empty userInfoForm.encUserId}"> required </c:if>
											name="password" id="password"  value="${userInfoForm.password}" />
									</div>
							</div>
							
			</div>			
			
			<div class="col-sm-12 form-group">	
							<div class="col-sm-6 form-group">
									<label class="col-md-4 col-sm-3 control-label">Status</label>
									<div class="col-md-8">


										<div class="col-md-6">
											<div class="radio-custom radio-primary">
												<input type="radio" id="enabledYN1" name="enabledYN" required <c:if test="${userInfoForm.enabledYN == '1'}">checked </c:if>
													value="1" onkeypress="goToNext(event,'enabledYN2')"/> <label for="enabledYN1">Active</label>
											</div>
										</div>
										<div class="col-md-6">
											<div class="radio-custom radio-primary">
												<input type="radio" id="enabledYN2" name="enabledYN" <c:if test="${userInfoForm.enabledYN == '0'}">checked </c:if>
													value="0" onkeypress="goToNext(event,'submit')"/> <label for="enabledYN2">Inactive</label>
											</div>
										</div>
					<label class="error" for="enabledYN"></label>
									</div>
							</div>
							
			</div>				
							
							<!-- <div class="col-sm-6">
								
							</div> -->
							
							
							
							
							
					
							<div class="col-sm-12" style="background: #b5dadf none repeat scroll 0 0; border-bottom: 1px solid #dadada;padding:10px; margin-top:15px; margin-bottom:15px">
							<h2 class="panel-title col-sm-12">User Roles</h2>
							</div>
							<div class="col-sm-12">

							<div id="roleList">
							<c:if test="${!empty roleList}">
								<c:forEach items="${roleList}" var="list" varStatus="status">
								<div class="col-sm-3 form-group">
								<div class="checkbox-custom chekbox-primary input-group">
										<%-- <h3 class="example-title col-md-12" style="margin-left:16px">${list.roleName}</h3> --%> <!-- style="text-align:center" -->
											<input type="checkbox" class="to-labelauty"  <%-- <c:if test="${list.roleId == 1}">onclick="return false" checked</c:if> --%>
												name="userRoles[${status.index}].roleYN" id="roleYN_${status.index}" <c:if test="${list.roleYN == 'Y'}">checked</c:if>
												value="Y"/>
												
												<label for="roleYN_${status.index}">${list.roleName}</label>
											<input type="hidden" name="userRoles[${status.index}].roleId"
															id="roleId_${status.index}" value="${list.roleId}">
										</div>
								</div>
								</c:forEach>
								</c:if>
								</div>

						
							</div>
</div>
						</div>

						<footer class="panel-footer">
					<div class="row">
						<div class="col-sm-12 col-sm-offset-0"></div>
							<div class="btn_div col-sm-offset-0 ">
							<a href="userInfo"><button class="btn btn-default"
										type="button" role="button"><i class="fa fa-refresh"></i> Clear</button></a>
								<button class="btn btn-primary" type="submit" id="submit">
								<i class="fa fa-save"></i> 
									<c:choose>
									<c:when test="${!empty userInfoForm.encUserId}"><span> <i class="icon wb-loop" aria-hidden="true"></i>
											Update
										</span></c:when>
									<c:otherwise><span> <i class="icon wb-book" aria-hidden="true"></i>
											Save
										</span></c:otherwise>
									</c:choose>
									</button>
								
							</div>


					</div>
				</footer>
							<!-- <div class="text-right">
								<button id="validateButton2"
									class="btn btn-primary waves-effect waves-light" type="submit">Submit</button>
							</div> -->

						</form>
					</section>
				</div> 
				<!-- End Panel Floating Labels -->
			</div>






<script>


function goToNext(e,next)
{
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




	
/* 	function check_username() {
		var username = $('#userName').val();
		var min_chars = 4;
		var characters_error = '<span style="color:#f43d3a">Minimum Character is 4 !</span>';
		var invalid_error = '<span style="color:#f43d3a">Invalid Character !</span>';
		var checking_html = '<img style="" src="<c:url value="/resources/images/animation/hourglass.gif"/>"  height="25" /> Checking...';

		if (username == ''){
			$("#userNameValidity").val('Y');
			$('#username_availability_result').html('');
		} else{
			if (!/^[0-9a-zA-Z_.-]+$/.test(username)) {
				$("#userNameValidity").val('N');
				$('#username_availability_result').html(invalid_error);
			} else {
				if (username.length < min_chars) {
					$('#username_availability_result').html(characters_error);
					$("#userNameValidity").val('N');
					$("#userName").focus();
				} else {
					$('#username_availability_result').html(checking_html);
					check_availability();
				}
			}
		}
	} */

/* 	function check_availability() {

		var username = $('#userName').val();
		var valid_user = '<span style="color:green">\'' + username+ '\' is Valid</span>';
		var user_exist = '<span style="color:#f43d3a">This User Name already exist!</span>'
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		var link = "/cbms/admin/checkUserName";

		jQuery.ajax({
			type : "POST",
			url : link,
			data : "userName=" + username,
			async : true,
			beforeSend : function(xhr) {
				// here it is
				xhr.setRequestHeader(header, token);
			},
			success : function(data) {
				if (data == 0) {
					//alert('valied');
					$('#username_availability_result').html(valid_user);
					$("#userNameValidity").val('Y');
					document.getElementById("password").focus();
				} else {
					//alert('Invalied');
					$('#username_availability_result').html(user_exist);
					$("#userNameValidity").val('N');
					$("#userName").focus();
				}

			}
		});

	} */



/* 	function getWagonList() {

		var wagonNo = $("#wagonNo").val();
		var wheelTypeId = $("#wheelTypeId").val();
		var unitType = $("#unitType").val();
		var wagonTypeId = $("#wagonTypeId").val();

		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		//    alert(token);

		var link = "/cbms/wagon/getWagonList";
		$.ajax({
			type : "POST",
			url : link,
			data : "wagonNo=" + wagonNo + "&wheelTypeId=" + wheelTypeId
					+ "&unitType=" + unitType + "&wagonTypeId=" + wagonTypeId,
			/* {"${_csrf.parameterName}":"${_csrf.token}"}, */

			/* async : true,
			beforeSend : function(xhr) {
				// here it is
				xhr.setRequestHeader(header, token);
			},
			success : function(data) {
				$("#wagonList").html(data);
			},
			error : function(data) {
				alert('Error!!!')
			}
		}); 

		//alert("aaa");

	}  */
	
	
	/* function validate(){
		var userNameValidity = $("#userNameValidity").val();
		var invalid_user = '<span style="color:#f43d3a">This User Name is invalid!</span>'
		
		if (userNameValidity == 'N'){
			$('#username_availability_result').html(invalid_user);
			$("#userName").focus();
			return false;
		} else{
			return true;
		}
		
		
	} */
	
	
	function getEmployeeInfo(id){
		var link = "/pos/admin/getUserEmpInfo?encEmployeeId="+encodeURIComponent(id);
			//	alert(link);
	 	window.location = link;
	}
</script>


         
      
          <!-- End Panel Floating Labels -->
      
    
  
    
    
  
<!-- End Page -->
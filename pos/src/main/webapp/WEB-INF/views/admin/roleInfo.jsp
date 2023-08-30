<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



				<header class="page-header">
						<!-- <h2>User Info View </h2> -->
					
						<div class="left-wrapper pull-left">
							<ol class="breadcrumbs">
								<li><a href="/pos/"> &nbsp; <i class="fa fa-home"></i> </a> </li>
								<li><span>Admin &nbsp;</span></li>
								<li><span>Role Information &nbsp;</span></li>
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
					<form class="form-horizontal form-bordered" action="/pos/admin/roleInfo/saveRoleInfo" id="form" method="post" onkeypress="return event.keyCode != 13;" >			
								
					
					<a href="#modalMessage" class="btn btn-sm btn-primary modal-with-zoom-anim" type="button" id="showModalMessage" style="display: none"></a>
					
				
								

				<header class="panel-heading">
					<div class="panel-actions">
						<a href="/pos/admin/roleInfoView"><button
								class="btn btn-primary btn-round" type="button">
								<span><i class="fa fa-arrow-left" aria-hidden="true"></i> Back</span>
							</button></a>
					</div>

					<h2 class="panel-title">Role Information</h2>
				</header>
				
				<div class="panel-body">
									
					<div class="row">

						<div class="form-group col-md-12">

							<div class="col-sm-6 form-group">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />

							<input type="hidden" id="encRoleId" name="encRoleId"
								value="${roleInfoForm.encRoleId}" />
								
									<label class="col-md-4 col-sm-3 control-label">Role
										Name</label>
									<div class="col-md-8">
										<input type="text" class="form-control" id="roleName" required
											onkeypress="goToNext(event,'enabledYN1')" name="roleName"
											value="${roleInfoForm.roleName}" />
									</div>
							</div>


							<div class="col-sm-6 form-group">
									<label class="col-md-4 col-sm-3 control-label">Role
										Status</label>
									<div class="col-md-8">


										<div class="col-md-6">
											<div class="radio-custom radio-primary">
												<input type="radio" id="enabledYN1" name="enabledYN" required <c:if test="${roleInfoForm.enabledYN == 'Y'}">checked</c:if>
													value="Y" onkeypress="goToNext(event,'enabledYN2')"/> <label for="enabledYN1">Enable</label>
											</div>
										</div>
										<div class="col-md-6">
											<div class="radio-custom radio-primary">
												<input type="radio" id="enabledYN2" name="enabledYN" <c:if test="${roleInfoForm.enabledYN == 'N'}">checked</c:if>
													value="N" onkeypress="goToNext(event,'startDate')"/> <label for="enabledYN2">Disable</label>
											</div>
										</div>
							<label class="error" for="enabledYN"></label>
									</div>
							</div>




						</div>
						
						
							
					</div>
									
									</div>

				<footer class="panel-footer">
					<div class="row">
						<div class="col-sm-12 col-sm-offset-0"></div>
							<div class="btn_div col-sm-offset-0 ">
							<a href="roleInfo"><button class="btn btn-default"
										type="button" role="button"><i class="fa fa-refresh"></i> Clear</button></a>
								<button class="btn btn-primary" type="submit" id="submit">
								<i class="fa fa-save"></i> 
									<c:choose>
									<c:when test="${!empty projectInfo.encProjectId }"><span> <i class="icon wb-loop" aria-hidden="true"></i>
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
			</form>
							

</section></div></div>









<script>
window.onload = load;
function load() {
	document.getElementById('projectName').focus();
}

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




	
	function check_projectname() {
		var projectname = $('#projectName').val();
		var min_chars = 2;
		var characters_error = '<span style="color:#f43d3a">Minimum Character is 2 !</span>';
		var invalid_error = '<span style="color:#f43d3a">Invalid Character !</span>';
		var checking_html = '<img style="" src="<c:url value="/resources/assets/images/animation/loading.gif" />" /> Checking...';

		if (projectname == ''){
			$("#projectNameValidity").val('Y');
			$('#projectname_availability_result').html('');
		} else{
			if (!/^[0-9a-zA-Z_.-]+$/.test(projectname)) {
				$("#projectNameValidity").val('N');
				$('#projectname_availability_result').html(invalid_error);
			} else {
				if (projectname.length < min_chars) {
					$('#projectname_availability_result').html(characters_error);
					$("#projectNameValidity").val('N');
					$("#projectName").focus();
				} else {
					$('#projectname_availability_result').html(checking_html);
					check_availability();
				}
			}
		}
	}

	function check_availability() {

		var projectname = $('#projectName').val();
		var valid_project = '<span style="color:green">\'' + projectname+ '\' is Valid</span>';
		var project_exist = '<span style="color:#f43d3a">This User Name already exist!</span>'
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		var link = "/pos/admin/checkUserName";

		jQuery.ajax({
			type : "POST",
			url : link,
			data : "projectName=" + projectname,
			async : true,
			beforeSend : function(xhr) {
				// here it is
				xhr.setRequestHeader(header, token);
			},
			success : function(data) {
				if (data == 0) {
					//alert('valied');
					$('#projectname_availability_result').html(valid_project);
					$("#projectNameValidity").val('Y');
					document.getElementById("password").focus();
				} else {
					//alert('Invalied');
					$('#projectname_availability_result').html(project_exist);
					$("#projectNameValidity").val('N');
					$("#projectName").focus();
				}

			}
		});

	}



	
	
	
	function validate(){
		var projectNameValidity = $("#projectNameValidity").val();
		var invalid_project = '<span style="color:#f43d3a">This User Name is invalid!</span>'
		
		if (projectNameValidity == 'N'){
			$('#projectname_availability_result').html(invalid_project);
			$("#projectName").focus();
			return false;
		} else{
			return true;
		}
		
		
	}
</script>


         
      
          <!-- End Panel Floating Labels -->
      
    
  
    
    
  
<!-- End Page -->
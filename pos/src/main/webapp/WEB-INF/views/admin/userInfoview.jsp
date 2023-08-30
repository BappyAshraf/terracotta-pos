<%@page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


			<header class="page-header">
						<!-- <h2>User Info View </h2> -->
					
						<div class="left-wrapper pull-left">
							<ol class="breadcrumbs">
								<li><a href="/pos/"> &nbsp; <i class="fa fa-home"></i> </a> </li>
								<li><span>Admin</span></li>
								<li><span>User Info View &nbsp;</span></li>
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
					<form class="form-horizontal form-bordered" action="#" method="post" >			
								
					
					<a href="#modalMessage" class="btn btn-sm btn-primary modal-with-zoom-anim" type="button" id="showModalMessage" style="display: none"></a>
					
					<div id="modalMessage" class="zoom-anim-dialog modal-block mfp-hide">
					<section class="panel">
						<header class="panel-heading">
							<h2 id="msgType" class="panel-title">Danger!</h2>
						</header>
						<div class="panel-body">
							<div class="modal-wrapper">
								<div class="modal-icon" id="msgModalIcon"></div>
								<div class="modal-text">
									<h4></h4>
									<p id="msg"></p>
								</div>
							</div>
						</div>
						<footer class="panel-footer">
							<div class="row">
								<div class="col-md-12 text-right">
									<button class="btn btn-danger modal-dismiss" id="msgModalOk">OK</button>
								</div>
							</div>
						</footer>
					</section>
				</div>
								

				<header class="panel-heading">
					<div class="panel-actions">
						<a href="/pos/admin/userInfo"><button
								class="btn btn-primary btn-round" type="button">
								<span><i class="fa fa-plus"></i> Add
									New User</span>
							</button></a>
					</div>

					<h2 class="panel-title">User Info View</h2>
				</header>



				<div class="panel-body">
									
					<div class="row">

						<div class="form-group col-md-10">

							<div class="col-sm-6 form-group">
								
									<label class="col-md-4 col-sm-3 control-label">Employee
										Name</label>
									<div class="col-md-8">
										<input type="text" class="form-control" id="employeeName"
											onkeypress="goToNext(event,'enabledYN1')" name="employeeName"
											value="${userInfo.employeeName}" />
									</div>
							</div>


							<div class="col-sm-6 form-group">
									<label class="col-md-4 col-sm-3 control-label">User
										Status</label>
									<div class="col-md-8">


										<div class="col-md-6">
											<div class="radio-custom radio-primary">
												<input type="radio" id="enabledYN1" name="enabledYN" checked
													value="1" onkeypress="goToNext(event,'enabledYN2')"/> <label for="enabledYN1">Enable</label>
											</div>
										</div>
										<div class="col-md-6">
											<div class="radio-custom radio-primary">
												<input type="radio" id="enabledYN2" name="enabledYN"
													value="0" onkeypress="goToNext(event,'search')"/> <label for="enabledYN2">Disable</label>
											</div>
										</div>

									</div>
							</div>




						</div>
						
						
								
							<div class="form-group col-md-2">
									<button class="btn btn-animate btn-animate-side btn-primary" id="search"
										onclick="getUserInfoList()" type="button">
										<span> <i class="fa fa-search" aria-hidden="true"></i>
											Search
										</span>
									</button>
									<!-- data-target="#agentModal" data-toggle="modal" -->
								</div>


					</div>
									
									</div>
										
									<!-- <footer class="panel-footer">
									<div class="row">
									<div class="col-sm-12 col-sm-offset-0">
									</div>
									
									</div>
									</footer> -->
								</form>
								</section>
								
								
					<section class="panel panel-featured panel-featured-primary">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="fa fa-caret-down"></a> <a href="#"
									class="fa fa-times"></a>
							</div>
			
							<h2 class="panel-title">User List</h2>
						</header>
						
						
						<div class=" panel-body">
					<div class="table-responsive">
						<div style="overflow: scroll; max-height: 300px;">
							<table class="table table-striped table-bordered table-condensed table-hover mb-none">
							
								<thead>
									<tr>
										<th>Employee Name</th>
										<th>Designation</th>
										<th>User Status</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody id="userList"></tbody>
								</table>
								
								</div>
								</div>
								</div>

		</section>



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

	function getUserInfoList() {

		var employeeName = $("#employeeName").val();
		var enabledYN = $('input[name=enabledYN]:checked').val();
		//var token = $("meta[name='_csrf']").attr("content");
		//var header = $("meta[name='_csrf_header']").attr("content");

		//    alert(token);

		var link = "/pos/admin/getUserInfoList";
		$.ajax({
			type : "POST",
			url : link,
			data : "employeeName=" + employeeName + "&enabledYN=" + enabledYN,
			/* {"${_csrf.parameterName}":"${_csrf.token}"}, */

			async : true,
			/* beforeSend : function(xhr) {
				// here it is
				xhr.setRequestHeader(header, token);
			}, */
			success : function(data) {
				$("#userList").html(data);
				
			},
			
			error : function(data) {
				alert('Error!!!')
			}
		});

		//alert("aaa");

	}

	function getUserInfo(id){
		var link = "/pos/admin/getUserInfo?encUserId="+encodeURIComponent(id);
			//	alert(link);
	 	window.location = link;
	}
	
	
	/* function deleteUserInfo(id) {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var link = "/cbms/admin/deleteUser";
		var conf = confirm('Do you want to delete this ?');
		if (conf == true) {
			$.ajax({
				type : "POST",
				url : link,
				data : "encUserId=" + encodeURIComponent(id),
				async : true,
				beforeSend : function(xhr) {
					// here it is
					xhr.setRequestHeader(header, token);
				},
				success : function(data) {
					if (data.messageCode == '0000') {
						$("#msgType").html("Fail");
	  	     			$("#msgModalIcon").html('<i class="fa fa-times-circle"></i>');
	  	     			$("#modalMessage").addClass("modal-block-danger");
	  	     			$("#modalMessage").removeClass("modal-block-success");
	  	     			$("#msgModalOk").removeClass("btn-success");
	  	     			$("#msgModalOk").addClass("btn-danger");

					} else if (data.messageCode == '1111') {
						$("#msgType").html("Success");
	  	     			$("#msgModalIcon").html('<i class="fa fa-check"></i>');
	  	     			$("#modalMessage").addClass("modal-block-success");
	  	     			$("#modalMessage").removeClass("modal-block-danger");
	  	     			$("#msgModalOk").removeClass("btn-danger");
	  	     			$("#msgModalOk").addClass("btn-success");
					}
					$("#msg").html(data.message);
					$("#showModalMessage").click();
					getUserInfoList();
					
				},
				error : function(data) {
					alert('Error!!!')
				}
			});
		} else {
			return false;
		}
	}
	 */
	
	
</script>
<%@page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


			<header class="page-header">
						<!-- <h2>User Info View </h2> -->
					
						<div class="left-wrapper pull-left">
							<ol class="breadcrumbs">
								<li><a href="/pos/"> &nbsp; <i class="fa fa-home"></i> </a> </li>
								<li><span>Admin &nbsp;</span></li>
								<li><span>Role Info View &nbsp;</span></li>
							</ol>
					
						</div>
					</header>
    
<!-- start: page -->
						
						<div class="row">
						
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
						
						
							<div class="col-lg-12">
							
							
							
							
				
								
								
					<section class="panel panel-featured panel-featured-primary">
						<header class="panel-heading">
									
							<h2 class="panel-title">Role List</h2>
							
							<div class="panel-actions">
							
						<a href="/pos/admin/roleInfo"><button
								class="btn btn-primary btn-round" type="button">
								<span><i class="fa fa-plus" aria-hidden="true"></i> Create New Role</span>
							</button></a>
						
					</div>
						</header>
						
						
						<div class=" panel-body">
					<div class="table-responsive">
						<div style="overflow: scroll; max-height: 300px;">
							<table class="table table-striped table-bordered table-condensed table-hover mb-none">
							
								<thead>
									<tr>
										<th>#</th>
										<th>Role</th>
										<th>Status</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody id="roleList">
								
								<c:if test="${!empty roleList}">
								<% int i = 1; %>
						<c:forEach items="${roleList}" var="list">
							<tr ondblclick="getRole('${list.encRoleId}')" style="cursor: pointer">
										<td>
											<% out.print(i);%>
										</td>
										<td>${list.roleName}</td>
										<td>${list.enabledYNName}</td>
										<td>
								
								<a href="#" onclick="getRole('${list.encRoleId}')"><button class="btn btn-xs btn-primary" type="button"> <i class="fa fa-pencil"></i></button></a>
								<a class="btn btn-xs btn-danger modal-with-zoom-anim" href="#modalDeleteRoleConf" onclick="delRole('${list.encRoleId}')"> <i class="fa fa-trash-o"></i></a>
										</td>
								
							</tr>
							<%i=i+1; %>
						</c:forEach>

	</c:if>
	
	
	<c:if test="${! empty noRoleFound}">
						<tr>
							<td colspan="4"><p>${noRoleFound}</p></td>
						</tr>
	</c:if>
								
								</tbody>
								</table>
								
								</div>
								</div>
								
								</div>
								

		</section>
		
		<!-- Delete Confirmation -->
									<div id="modalDeleteRoleConf" class="zoom-anim-dialog modal-block modal-block-primary mfp-hide">
										<section class="panel">
											<header class="panel-heading">
												<h2 class="panel-title">Are you sure?</h2>
											</header>
											<div class="panel-body">
											<input type="hidden" id="roleForDelete"/>
											
												<div class="modal-wrapper">
													<div class="modal-icon">
														<i class="fa fa-question-circle"></i>
													</div>
													<div class="modal-text">
														<p>Are you sure that you want to delete this Role?</p>
													</div>
												</div>
											</div>
											<footer class="panel-footer">
												<div class="row">
													<div class="col-md-12 text-right">
														<button onclick="confDelRole()" class="btn btn-primary modal-dismiss">Confirm</button>
														<button class="btn btn-default modal-dismiss">Cancel</button>
													</div>
												</div>
											</footer>
										</section>
									</div>	
								
								</div>
								



	</div>
					
						
						
						
						
					<!-- end: page -->
					
					
					
					<script>
					
					

					function getRole(id){
						var link = "/pos/admin/roleInfoView/getRoleInfo?encRoleId="+encodeURIComponent(id);
								//alert(link);
					 	window.location = link;
					}
					
					function delRole(id){
						$("#roleForDelete").val(id);
					}
					
					
					function confDelRole(){
						var id = $("#roleForDelete").val();
						
						var token = $("meta[name='_csrf']").attr("content");
						var header = $("meta[name='_csrf_header']").attr("content");

						 //  alert(token);
						
						var link = "/pos/admin/roleInfoView/deleteRole";
						
						$.ajax({
							type : "POST",
							url : link,
							data : "encRoleId="+encodeURIComponent(id),
							/* {"${_csrf.parameterName}":"${_csrf.token}"}, */

							async : true,
							beforeSend : function(xhr) {
								// here it is
								xhr.setRequestHeader(header, token);
							},
							success : function(data) {
								getRoleList();
								
							},
							
							error : function(data) {
								alert('Error!!!')
							}
						});
					}
					
					function getRoleList(){
												
						var token = $("meta[name='_csrf']").attr("content");
						var header = $("meta[name='_csrf_header']").attr("content");

					//	   alert(token);
						
						var link = "/pos/admin/roleInfoView/getRoleListAjax";
						$.ajax({
							type : "POST",
							url : link,
							async : true,
							beforeSend : function(xhr) {
								// here it is
								xhr.setRequestHeader(header, token);
							},
							success : function(data) {
							//	$("#roleList").html(data);
							},
							
							error : function(data) {
								alert('Error!!!')
							}
						});
					}
					
					
	
</script>
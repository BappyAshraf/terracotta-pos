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
								<li><span>Role Menu Mapping View &nbsp;</span></li>
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
		
		
				 
							<section class="panel panel-featured panel-featured-primary">
					<form class="form-horizontal form-bordered" action="/pos/admin/roleMenuMapping/saveRoleMenuMapping" method="post" id="form" onkeypress="return event.keyCode != 13;" >			
					
					<header class="panel-heading">
					<div class="panel-actions">
						<a href="/pos/admin/roleMenuMapping"><button
								class="btn btn-primary btn-round" type="button">
								<span><i class="fa fa-plus" aria-hidden="true"></i> Role Menu Mapping</span>
							</button></a>
					</div>

					<h2 class="panel-title">Role Menu Mapping View</h2>
					</header>
					
						<div class="panel-body">

						<div class="row">
						
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />


		<div class="col-sm-12 form-group">
							<div class="col-sm-6 form-group">
									<label class="col-md-4 col-sm-3 control-label">Role</label>
									<div class="col-md-8">
										<select data-plugin-selectTwo class="form-control" name="roleId" id="roleId"
										onchange="updateMenuList('roleMenuMappingView/getRoleAllMenuList','roleAllMenuList')">
											<option value="">Please Select One</option>
											<c:forEach items="${roleList}" var="list">
												<option  value="${list.id}"
												<c:if test="${roleMenuMapping.roleId == list.id}">selected = "selected"</c:if>
												>${list.name}</option>
											</c:forEach>
										</select>
									</div>
							</div>

							
			</div>



						
			
					
							<!-- <div class="col-sm-6">
								
							</div> -->
							
							
							
							
							
					
							<div class="col-sm-12" style="background: #b5dadf none repeat scroll 0 0; border-bottom: 1px solid #dadada;padding:10px; margin-top:15px; margin-bottom:15px">
							<h2 class="panel-title col-sm-12">Role Assigned Menu List</h2>
							</div>
							<div class="col-sm-12">

							<table class="table table-striped table-bordered table-condensed table-hover mb-none">
							
								<thead>
									<tr>
										<th><strong>#</strong></th>
										
										<th><strong>Main Menu</strong></th>
											
										<th><strong>Sub Menu</strong></th>

										<th><strong>Insert</strong></th>
										
										<th><strong>Delete</strong></th>

									</tr>
								</thead>
								<tbody id="roleAllMenuList">
									<c:if test="${!empty roleAllMenuList}">
									<%int i=1; %>
										<c:forEach items="${roleAllMenuList}" var="list">
											<tr style="cursor: pointer">
											<td><%out.print(i);i++; %></td>
												<td>${list.mainMenuName}</td>
												<td>${list.menuName}</td>
												<td>${list.insert}</td>
												<td>${list.delete}</td>
											</tr>
										</c:forEach>
									</c:if>

									<c:if test="${! empty roleAllMenuNotFound}">
										<tr>
											<td colspan="4"><p>${roleAllMenuNotFound}</p></td>
										</tr>
									</c:if>



								</tbody>
								</table>

						
							</div>
</div>
						</div>

						<footer class="panel-footer">
					
				</footer>
							

						</form>
					</section>
				</div> 
				<!-- End Panel Floating Labels -->
			</div>

			
			
<script>


	
	
	function updateMenuList(lookupUrl, childElementId) {
		var role = $("#roleId").val();
		var mainMenu = $("#mainMenuId").val();
		//var userId = $("#userTypeId").val();

		
		//var token = $("meta[name='_csrf']").attr("content");
		//var header = $("meta[name='_csrf_header']").attr("content");
		
		if (role == '' || mainMenu == '') {
			$('#' + childElementId).html('');
		} else {
			
			$.ajax({
				type : "POST",
				url : lookupUrl,
				data : "roleId=" + role + "&mainMenuId=" + mainMenu,
				async : true,
				/* beforeSend : function(xhr) {
					// here it is
					xhr.setRequestHeader(header, token);
				}, */
				success : function(data) {

					$("#" + childElementId).html(data);
				}
			});

		}
	};
</script>
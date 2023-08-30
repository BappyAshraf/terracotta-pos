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
								<li><span>Role Menu Mapping &nbsp;</span></li>
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
					<form class="form-horizontal form-bordered" action="/pos/admin/roleMenuMapping/saveRoleMenuMapping" 
					method="post" id="form" onkeypress="return event.keyCode != 13;" >			
					
					<header class="panel-heading">
					<div class="panel-actions">
						<a href="/pos/admin/roleMenuMappingView"><button
								class="btn btn-primary btn-round" type="button">
								<span><i class="fa fa-arrow-left" aria-hidden="true"></i> Back</span>
							</button></a>
					</div>

					<h2 class="panel-title">Role Menu Mapping</h2>
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
										onchange="updateMenuList('roleMenuMapping/getRoleMenuMappingList','roleMenuMappingList')">
											<option value="">Please Select One</option>
											<c:forEach items="${roleList}" var="list">
												<option  value="${list.id}"
												<c:if test="${roleMenuMapping.roleId == list.id}">selected = "selected"</c:if>
												>${list.name}</option>
											</c:forEach>
										</select>
									</div>
							</div>

							<div class="col-sm-6 form-group">
									<label class="col-md-4 col-sm-3 control-label">Parent Menu</label>
									<div class="col-md-8">
										<select class="form-control" name="mainMenuId" id="mainMenuId"
										onchange="updateMenuList('roleMenuMapping/getRoleMenuMappingList','roleMenuMappingList')">
											<option value="">Please Select One</option>
											<c:forEach items="${mainMenuList}" var="list">
												<option  value="${list.id}"
												<c:if test="${roleMenuMapping.mainMenuId == list.id}">selected = "selected"</c:if>
												>${list.name}</option>
											</c:forEach>
										</select>
									</div>
							</div>
			</div>



						
			
					
							<!-- <div class="col-sm-6">
								
							</div> -->
							
							
							
							
							
					
							<div class="col-sm-12" style="background: #b5dadf none repeat scroll 0 0; border-bottom: 1px solid #dadada;padding:10px; margin-top:15px; margin-bottom:15px">
							<h2 class="panel-title col-sm-12">Role Menu List</h2>
							</div>
							<div class="col-sm-12">

							<table class="table table-striped table-bordered table-condensed table-hover mb-none">
							
								<thead>
									<tr>
										<th><div class="checkbox-custom chekbox-primary input-group">
												 <input class="to-labelauty" type="checkbox"
													id="allowYN_" onclick="checkAll(this)"/>
												<label for="allowYN_"><strong> Menu Name</strong></label>
											</div></th>

										<th><div class="checkbox-custom chekbox-primary input-group">
												 <input class="to-labelauty" type="checkbox"
													id="insertYN_" onclick="checkAll(this)"/> 
												<label for="insertYN_"><strong>Insert</strong></label>
											</div></th>

										<th><div class="checkbox-custom chekbox-primary input-group">
												 <input class="to-labelauty" type="checkbox"
													id="deleteYN_" onclick="checkAll(this)"/> 
												<label for="deleteYN_"><strong>Delete</strong></label>
											</div></th>

									</tr>
								</thead>
								<tbody id="roleMenuMappingList">
									<c:if test="${!empty roleMenuMappingList}">

										<c:forEach items="${roleMenuMappingList}" var="list"
											varStatus="status">
											<tr style="cursor: pointer">
												<td>
													<div class="checkbox-custom chekbox-primary input-group">
														<input type="hidden"
															name="roleMenuDetails[${status.index}].encMenuId"
															id="encMenuId_${list.encMenuId}"
															value="${list.encMenuId}">
														
															<input class="to-labelauty" type="checkbox"
																id="allowYN_${list.encMenuId}"
																name="roleMenuDetails[${status.index}].allowYN"
																value="Y"
																<c:if test="${list.allowYN == 'Y' && !empty list.encMenuId}">checked</c:if>>
														
														<label for="allowYN_${list.encMenuId}">${list.menuName}</label>
													</div>
												</td>



												<td>
													<div class="checkbox-custom chekbox-primary input-group">
														<input class="to-labelauty" type="checkbox"
															name="roleMenuDetails[${status.index}].insertYN"
															id="insertYN_${list.encMenuId}" value="Y"
															<c:if test="${list.insert == 'Y'}">checked</c:if>>

														<label for="insertYN_${list.encMenuId}">${list.insertYN}</label>
													</div>
												</td>


												<td>
													<div class="checkbox-custom chekbox-primary input-group">
														<input class="to-labelauty" type="checkbox"
															name="roleMenuDetails[${status.index}].deleteYN"
															id="deleteYN_${list.encMenuId}" value="Y"
															<c:if test="${list.delete == 'Y'}">checked</c:if>>

														<label for="deleteYN_${list.encMenuId}">${list.deleteYN}</label>
													</div>
												</td>


											</tr>
										</c:forEach>
									</c:if>

									<c:if test="${! empty roleMenuNotFound}">
										<tr>
											<td colspan="8"><p>${roleMenuNotFound}</p></td>
										</tr>
									</c:if>



								</tbody>
								</table>

						
							</div>
</div>
						</div>

						<footer class="panel-footer">
					<div class="row">
						<div class="col-sm-12 col-sm-offset-0"></div>
							<div class="btn_div col-sm-offset-0 ">
							<a href="roleMenuMapping"><button class="btn btn-default"
										type="button" role="button"><i class="fa fa-refresh"></i> Clear</button></a>
								<button class="btn btn-primary" type="submit" id="submit">
								<i class="fa fa-save"></i> 
									<c:choose>
									<c:when test="${!empty userInfoForm.encUserId }"><span> <i class="icon wb-loop" aria-hidden="true"></i>
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


		function checkAll(check) {
			var id = check.id;
			if (check.checked) {
				$('input:checkbox[id^="'+id+'"]').each(function(){
                	$('input:checkbox[id^="'+id+'"]').prop("checked", true);
                });
			}

			else{
				$('input:checkbox[id^="'+id+'"]').each(function(){
                	$('input:checkbox[id^="'+id+'"]').prop("checked", false);
                });

			}
		}
	
</script>
         
      
          <!-- End Panel Floating Labels -->
      
    
  
    
    
  
<!-- End Page -->
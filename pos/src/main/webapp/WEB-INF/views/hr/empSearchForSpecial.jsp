<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="panel panel-featured panel-featured-primary">
					<form class="form-horizontal form-bordered" action="#" method="post" >			
					
				
				
				<header class="panel-heading">
				<%if (request.getAttribute("javax.servlet.forward.request_uri").equals("/cbms/hr/employeeInfoView")) {%>
					<div class="panel-actions">
						<a href="/cbms/hr/employeeInfo"><button 
								class="btn btn-primary btn-round" type="button">
								<span><i class="fa fa-plus" aria-hidden="true"></i> Add
									New Employee</span>
							</button></a>
							
					</div>
					<%} %>
		
			<h2 class="panel-title">Employee Info View</h2>
				</header>
			


				<div class="panel-body">
									
					<div class="row">

						<div class="form-group col-md-10">
								
								
								
									<div class="col-sm-6 form-group">
											<label class="col-md-4 col-sm-3 control-label"> Employee Name </label>
											<div class="col-md-8">
												<input class="form-control" name="employeeName" id="employeeName"
										onkeypress="goToNext(event,'fatherName')" value="${employeeInfo.employeeName}"/>
											
											</div>
									</div>
									
									
									<div class="col-sm-6 form-group">
											<label class="col-md-4 col-sm-3 control-label"> Father Name </label>
											<div class="col-md-8">
												<input class="form-control" name="fatherName" id="fatherName"
										onkeypress="goToNext(event,'designationId')" value="${employeeInfo.fatherName}"/>
											
											</div>
									</div>
									
									
									
									<div class="col-sm-6 form-group">
											<label class="col-md-4 col-sm-3 control-label"> Department </label>
											<div class="col-md-8">
												<select class="form-control" name="departmentId" id="departmentId"
										onkeypress="goToNext(event,'search')">
											<option value="">Please Select One</option>
											<c:forEach items="${departmentList}" var="list">
												<option 
												<c:if test="${list.id == employeeInfo.departmentId}">selected = "selected" </c:if>
												value="${list.id}">${list.name}</option>
											</c:forEach>
										</select>
												
											</div>
									</div>
									
									
									<div class="col-sm-6 form-group">
											<label class="col-md-4 col-sm-3 control-label"> Designation </label>
											<div class="col-md-8">
												<select class="form-control" name="designationId" id="designationId"
										onkeypress="goToNext(event,'levelNo')">
											<option value="">Please Select One</option>
											<c:forEach items="${designationList}" var="list">
												<option 
												<c:if test="${list.id == employeeInfo.designationId}">selected = "selected" </c:if>
												value="${list.id}">${list.name}</option>
											</c:forEach>
										</select>
												
											</div>
									</div>
									
									<div class="col-sm-6 form-group">
											<label class="col-md-4 col-sm-3 control-label"> Level No </label>
											<div class="col-md-8">
												<input class="form-control" name="levelNo" id="levelNo"
										onkeypress="goToNext(event,'search')" value="${employeeInfo.levelNo}"/>
											
											</div>
									</div>

										

								</div>
								
								
								
								
									
									
								
							<div class="form-group col-md-2">
								<button class="btn btn-primary" id="search"
										onclick="getEmployeeListForSpecial('Y')" type="button">
										<span> <i class="fa fa-search" aria-hidden="true"></i>
											Search
										</span>
									</button>
									<!-- data-target="#agentModal" data-toggle="modal" -->
								</div>


					</div>
									
					</div>
								
								</form>
								</section>
								
								
					<section class="panel panel-featured panel-featured-primary">
						<header class="panel-heading">
									
							<h2 class="panel-title">Employee List</h2>
						</header>
						
						
						<div class=" panel-body">
					<div class="table-responsive">
						<div style="overflow: scroll; max-height: 300px;">
							<table class="table table-striped table-bordered table-condensed table-hover mb-none" role="grid">
							
								<thead>
									<tr>
										<th>Employee Name</th>
										<th>Father Name</th>
										<th>Department</th>
										<th>Designation</th>
										<th>Employee Status</th>
										<th>Employee Level</th>
										<th>Select</th>
									</tr>
								</thead>
								<tbody id="employeeList"></tbody>
								</table>
								
								</div>
								</div>
								</div>

		</section>
		
		<script>
		
		function getEmployeeListForSpecial(enable) {
			var employeeName = $("#employeeName").val();
			var fatherName = $("#fatherName").val();
			var departmentId = $("#departmentId").val();
			var designationId = $("#designationId").val();
			var levelNo = $("#levelNo").val();
						
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");

			 // alert(token);
			
			var link = "/cbms/hr/getEmployeeInfoListForSpecial";
			$.ajax({
				type : "POST",
				url : link,
				data : "employeeName=" + employeeName +"&fatherName="+fatherName + "&designationId="+designationId+"&departmentId="+departmentId+"&levelNo="+levelNo+"&enabledYN="+enable,
				/* {"${_csrf.parameterName}":"${_csrf.token}"}, */

				async : true,
				beforeSend : function(xhr) {
					// here it is
					xhr.setRequestHeader(header, token);
				},
				success : function(data) {
					$("#employeeList").html(data);
					
				},
				
				error : function(data) {
					alert('Error!!!')
				}
			});
			

		}
		
		</script>
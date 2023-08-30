<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.totalHour {
	color: #CC0000;
	font-size: 22px;
	font-weight: 700;
}
</style>

<header class="page-header">
	<!-- <h2>Personal Identification</h2> -->

	<div class="left-wrapper pull-left">
		<ol class="breadcrumbs">
			<li><a href="/pos/"> &nbsp;<i class="fa fa-home"></i></a></li>
			<li><span>Inventory &nbsp;</span></li>
			<li><span>Kitchen Ingredients &nbsp;</span></li>
		</ol>
	</div>
</header>


<!-- start: page -->

<div class="row">

	<div class="col-lg-12">

		<!-- message -->
		<div id="msg">
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
		</div>

		<form class="form-horizontal form-bordered"
			action="/pos/inventory/updateStoreProduct" method="post">

			<%-- <div class="form-group col-md-12">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input type="hidden"
					id="encEffortTrackingId" name="encEffortTrackingId"
					value="${taskTrackingInfo.encEffortTrackingId}" />
			</div> --%>

			<!-- start: page -->
			<section class="panel panel-featured panel-featured-primary">
				<header class="panel-heading">
					<h2 class="panel-title">Store Ingredients in Kitchen</h2>
				</header>
				<div class="panel-body">
					<br />
					<div class="form-group col-md-12">
						<div class="col-sm-6 form-group">
							<label class="control-label col-md-5 col-sm-5 col-xs-12">Inventory
								Type</label>
							<div class="col-md-7 col-sm-7 col-xs-12">
								<select data-plugin-selectTwo class="form-control"
									id="inventoryTypeId" name="inventoryTypeId" required>
									<option value="">Select One</option>
									<c:forEach items="${inventoryTypeList}" var="list">
										<option
											<c:if test="${list.inventoryTypeId == inventory.inventoryTypeId}">selected = "selected" </c:if>
											value="${list.inventoryTypeId}">${list.inventoryTypeName}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="col-sm-2 form-group"></div>

						<div class="col-sm-5 form-group">
							<label
								class="control-label col-md-7 col-sm-7 col-xs-12 totalHour">Total
								Amount </label>
							<div class="col-md-5 col-sm-5 col-xs-12 totalHour">
								<span id="priceSum"></span>
							</div>
						</div>

					</div>


					<div class="row" style="padding-bottom: 10px">
						<div class="col-sm-6">
							<button id="" class="btn btn-primary" type="button"
								onclick="newData()">
								Add New Ingredient <i class="fa fa-plus"></i>
							</button>
						</div>
					</div>
					<div style="overflow: scroll; max-width: 100%;">
						<table
							class="table table-bordered table-striped table-condensed mb-none"
							id="dataTable">
							<thead>
								<tr>
									<th>Product Name</th>
									<th>Unit</th>
									<th>Unit Price</th>
									<th>Quantity</th>
									<th>Price</th>
									<th>Shop By</th>
									<th>Action</th>

								</tr>
							</thead>
							<tbody>
								<tr id="newdatarow"
									<c:if test="${empty inventory.encInventoryId}">
								style="cursor: pointer; display: none;" 
								</c:if>>



									<td><input type="hidden" id="encInventoryId"
										value="${inventory.encInventoryId}" name="encInventoryId" />

										<select data-plugin-selectTwo class="form-control"
										id="productId" name="productId" style="width: 190px" required>
											<option value="">Select One</option>
											<c:forEach items="${productList}" var="list">
												<option
													<c:if test="${list.productId == inventory.productId}">selected = "selected" </c:if>
													value="${list.productId}">${list.productName}</option>
											</c:forEach>
									</select></td>

									<td><select data-plugin-selectTwo class="form-control"
										id="unitId" name="unitId" style="width: 120px" required
										onkeypress="return isNumberKey(event)">
											<option value="">Select One</option>
											<c:forEach items="${unitList}" var="list">
												<option
													<c:if test="${list.unitId == inventory.unitId}">selected = "selected" </c:if>
													value="${list.unitId}">${list.unitName}</option>
											</c:forEach>
									</select></td>

									<td><input type="text" class="form-control" id="unitPrice"
										name="unitPrice" style="width: 110px"
										onkeypress="return isNumberKey(event)"
										value="${inventory.unitPrice}">
										<div id="errunitPrice"></div></td>

									<td><input type="text" class="form-control" id="quantity"
										name="quantity" value="${inventory.quantity}"
										style="width: 110px" onkeypress="return isNumberKey(event)">
										<div id="errquantity"></div></td>

									<td><input type="text" class="form-control" id="price"
										name="price" style="width: 85px" required readonly="readonly"
										value="${inventory.price}"
										onkeypress="return isNumberKey(event)"
										onclick="getTotalPrice()" />

										<div id="errprice"></div></td>



									<td><select data-plugin-selectTwo class="form-control"
										id="employeeId" name="employeeId" style="width: 120px"
										required>
											<option value="">Select One</option>
											<c:forEach items="${employeeList}" var="list">
												<option
													<c:if test="${list.employeeId == inventory.employeeId}">selected = "selected" </c:if>
													value="${list.employeeId}">${list.firstName}</option>
											</c:forEach>
									</select></td>


									<td><c:choose>
											<c:when test="${!empty inventory.encInventoryId}">
												<button class="btn btn-primary" type="submit"
													style="width: 75px;">
													<span> <i class="fa fa-save"></i> Update
													</span>
												</button>
											</c:when>
											<c:otherwise>
												<a href="#" onclick="saveNewData()">
													<button class="btn btn-primary" type="button"
														style="width: 75px;">
														<span> <i class="fa fa-save"></i> Save
														</span>
													</button>
												</a>
											</c:otherwise>
										</c:choose> <a href="kitchenManagement" onclick="cancelnewdata()">
											<button class="btn btn-default" type="button"
												style="width: 75px;">
												<span> <i class="fa fa-refresh"></i> Cancel
												</span>
											</button>
									</a></td>
								</tr>

							</tbody>
						</table>
					</div>
				</div>

				<footer class="panel-footer">
					<div class="row">
						<div class="btn_div col-sm-offset-1 ">
							<a href="kitchenManagement"><button
									class="btn btn-sm btn  btn-default" type="button" role="button">
									<i class="fa fa-refresh"></i> Refresh
								</button></a>
						</div>
					</div>
				</footer>

			</section>

		</form>

		<section class="panel panel-featured panel-featured-primary">
			<header class="panel-heading">
				<h2 class="panel-title">Kitchen Ingredients List</h2>

				<div class="panel-actions" id="resultDiv">
					<p id="result"></p>
				</div>
			</header>

			<div class="panel-body" id="inventoryDownList">
				<br />
				<div style="overflow: scroll; max-width: 100%;">
					<table
						class="table table-bordered table-striped table-condensed mb-none"
						id="productList">
						<thead>
							<tr>
								<th>#</th>
								<th>Inventory Date</th>
								<th>Product Name</th>
								<th>Unit</th>
								<th>Unit Price</th>
								<th>Quantity</th>
								<th>Price</th>
								<th>Shop By</th>
								<th>Edit</th>
							</tr>
						</thead>
						<tbody id="inventoryList">

							<c:if test="${!empty inventoryList}">
								<%
									int i = 1;
								%>
								<c:forEach items="${inventoryList}" var="list">

									<tr style="cursor: pointer">
										<td>
											<%
												out.print(i);
														i++;
											%>
										</td>
										<td>${list.inventoryDate}</td>
										<td>${list.productName}</td>
										<td>${list.unitName}</td>
										<td>${list.unitPrice}</td>
										<td>${list.quantity}</td>
										<td>${list.price}</td>
										<td>${list.employeeName}</td>
										<td><a href="#"
											onclick="getProductInfo('${list.encInventoryId}')"><button
													class="btn btn-xs btn-primary" type="button">
													<i class="fa fa-pencil"></i>
												</button></a></td>
									</tr>
								</c:forEach>

							</c:if>

							<c:if test="${! empty inventoryListNotFound}">
								<tr>
									<td colspan="9"><p>${inventoryListNotFound}</p></td>
									<td style="display: none;"></td>
									<td style="display: none;"></td>
									<td style="display: none;"></td>
									<td style="display: none;"></td>
									<td style="display: none;"></td>
									<td style="display: none;"></td>
									<td style="display: none;"></td>
									<td style="display: none;"></td>
								</tr>
							</c:if>

						</tbody>

					</table>
				</div>
			</div>

			<footer class="panel-footer">
				<br />
			</footer>

		</section>





		<!-- end: page -->

	</div>
</div>

<input type="hidden" name="inventoryDate" id="inventoryDate"
	value="${inventory.inventoryDate}">

<script>
window.onload = function load() {
	$("#productList").DataTable();
}
function getProductInfo(encInventoryId) {
	var link = "/pos/inventory/getProductInfo?encInventoryId=" + encodeURIComponent(encInventoryId);
	//alert(link);
	window.location = link;
}
function getTotalPrice () {
	//alert('hello');
	var unitPrice = $("#unitPrice").val();
	var quantity = $("#quantity").val();
	
	if (unitPrice == '') {
		alert('Please Enter Unit Price First !');
	} else if (quantity == '') {
		alert('Please Enter Quantity First !');
	} else {
		var totalPrice = unitPrice * quantity;
		//alert(totalPrice);
		$("#price").val(totalPrice);
	}
	
}
function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    var errid = "err"+evt.target.id;
    var msg = '<label class="error" for="'+evt.target.id+'">Please enter a valid number.</label>';
    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
    	$("#"+errid).html(msg);
        return false;
    } else {
    	$("#"+errid).html('');
        return true;
    }      
}
	function newData() {
		$("#newdatarow").show();
		/* $("#projectId").val('');
		$("#taskTypeId").val(''); */
		$("#taskDesc").val('');
		//$("#startTime").val('');
		//$("#endTime").val('');
		$("#totalHour_").val('00:00');
		$("#noOfDefect").val('');
		$("#defectDesc").val('');
	}
	function cancelnewdata() {
		$("#newdatarow").hide();
		$("#encInventoryId").val('');
		$("#unitPrice").val('');
		$("#quantity").val('');
		$("#price").val('');
		$("#employeeId").val('');
	}
	
	
	
	 function saveNewData() {
	//alert('hello');
	var inventoryTypeId = $("#inventoryTypeId").val();
	var productId = $("#productId").val();
	var unitId = $("#unitId").val();
	var unitPrice = $("#unitPrice").val();
	var quantity = $("#quantity").val();
	var price = $("#price").val();
	var employeeId = $("#employeeId").val();
	var msg = "";
	/* alert(inventoryTypeId);
	alert(productId);
	alert(quantity);
	alert(unitId);
	alert(price);
	alert(employeeId); */
	
	if (inventoryTypeId == '') {
		alert("Please Select Inventory Type !!!");
	} else {
		//alert('HELLO');
		//var link = "/cbms/effortTracking/saveEffortTrackingInfo";
		var link = "/pos/inventory/saveKitchenIngredients";
		//alert(link);
		$.ajax({type : "POST",
					url : link,
					
					data : "inventoryTypeId=" + inventoryTypeId 
					+ "&productId=" + productId
					+ "&unitId=" + unitId
					+ "&unitPrice=" + unitPrice
					+ "&quantity=" + quantity 
					+ "&price=" + price 
					+ "&employeeId=" + employeeId,
					async : true,
					 /* beforeSend : function(xhr) {
						// here it is
						xhr.setRequestHeader(header, token);
					},  */
					success : function(data) {
						//alert(data.mCode);
						if (data.mCode == '1111') {
							//cancelnewdata();
							$("#inventoryDate").val(data.inventoryDate)
							getInventoryList();
							
							msg = '<div class="alert alert-success">'
									+ '<button class="close" aria-hidden="true" data-dismiss="alert"'
						+'type="button">x</button>'
									+ '<strong>' + data.message
									+ '</strong></div>';
							$("#effortSearchButton").click();
						} else {
							msg = '<div class="alert alert-danger">'
									+ '<button class="close" aria-hidden="true" data-dismiss="alert"'
				+'type="button">x</button>'
									+ '<strong>' + data.message
									+ '</strong></div>';
						}
						//alert(msg);
						$("#msg").html(msg);
					},
					error : function(data) {
						alert('Error!!!')
					}
				});
	}
} ;
	 function getInventoryList() {
		
		//var inventoryTypeId = $("#inventoryTypeId").val();
		//var inventoryDate = $("#inventoryDate").val();
			
			var link = "/pos/inventory/getInventoryList";
			//alert(link);
			$.ajax({
				type : "POST",
				url : link,
				data : "",
				/* data : "inventoryTypeId=" + inventoryTypeId + "&inventoryDate=" + inventoryDate, */
				async : true,
				success : function(data) {
					$("#inventoryList").html(data);
					$("#productList").show();
					totalPriceCalc();
				},
	
				error : function(data) {
					alert('Error!!!')
				}
			});
	}; 
	
	
	function totalPriceCalc () {
		var inventoryTypeId = $("#inventoryTypeId").val();
		var inventoryDate = $("#inventoryDate").val();
		//alert(inventoryTypeId);
		//alert(updateDate);
			
			var link = "/pos/inventory/getPriceSum";
			//alert(link);
			$.ajax({
				type : "POST",
				url : link,
				data : "inventoryTypeId=" + inventoryTypeId + "&inventoryDate=" + inventoryDate,
				async : true,
				success : function(data) {
					//alert(data.priceSum);
					$("#priceSum").html(data.priceSum);
					//totalPriceCalc();
					//$("#inventoryDownList").show();
				},
				error : function(data) {
					alert('Error!!!')
				}
			});
	
	}
	
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- end: page -->
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
			<li><span>Store Ingredients &nbsp;</span></li>
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

		<form class="form-horizontal form-bordered" action="#" method="post">

			<%-- <div class="form-group col-md-12">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input type="hidden"
					id="encEffortTrackingId" name="encEffortTrackingId"
					value="${taskTrackingInfo.encEffortTrackingId}" />
			</div> --%>

			<!-- start: page -->
			<section class="panel panel-featured panel-featured-primary">
				<header class="panel-heading">
					<h2 class="panel-title">Store Ingredients</h2>
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
										<option value="${list.inventoryTypeId}">${list.inventoryTypeName}</option>
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
									<th>Ingredient Name</th>
									<th>Quantity</th>
									<th>Unit</th>
									<th>Price</th>
									<th>Shop By</th>
									<th>Action</th>

								</tr>
							</thead>
							<tbody>
								<tr style="cursor: pointer; display: none;" id="newdatarow">
									
									<td><select data-plugin-selectTwo class="form-control"
									 id="productId"  style="width: 190px" required>
											<option value="">Select One</option>
											<c:forEach items="${productList}" var="list">
												<option value="${list.productId}">${list.productName}</option>
											</c:forEach>
									</select></td>
									
									<td><input type="text" class="form-control" id="quantity"
										style="width: 110px"
										onkeypress="return isNumberKey(event)" >
										<div id="errquantity"> </div>
										</td>
										
									<td><select data-plugin-selectTwo class="form-control"
										id="unitId" style="width: 120px" required
										onkeypress="return isNumberKey(event)">
											<option value="">Select One</option>
											<c:forEach items="${unitList}" var="list">
												<option value="${list.unitId}">${list.unitName}</option>
											</c:forEach>
									</select>
									</td>
									
									<td><input type="text" class="form-control" id="price"
										name="price" style="width: 85px" required 
										onkeypress="return isNumberKey(event)" />
										
										<div id="errprice"> </div>
										
									</td>

									<td><select data-plugin-selectTwo class="form-control"
										id="employeeId" name="employeeId" style="width: 120px"
										required>
											<option value="">Select One</option>
											<c:forEach items="${employeeList}" var="list">
												<option value="${list.employeeId}">${list.firstName}</option>
											</c:forEach>
									</select></td>


									<td><a href="#" onclick="saveNewData()">
											<button class="btn btn-primary" type="button"
												style="width: 75px;">
												<span> <i class="fa fa-save"></i> Save
												</span>
											</button>

									</a> <a href="#" onclick="cancelnewdata()">
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


				<div class="panel-body" id="inventoryDownList" style="display: none">
					<br />
					<div style="overflow: scroll; max-width: 100%;">
						<table
							class="table table-bordered table-striped table-condensed mb-none"
							id="dataTable">
							<thead>
								<tr>
									<th>#</th>
									<th>Product Name</th>
									<th>Quantity</th>
									<th>Unit</th>
									<th>Price</th>
									<th>Shop By</th>
									<th>Date</th>
								</tr>
							</thead>
							<tbody id="inventoryList">
							</tbody>

						</table>
					</div>
				</div>


				<footer class="panel-footer">
					<br />
				</footer>

			</section>

		</form>
		<!-- end: page -->

	</div>
</div>

<input type="hidden" name="inventoryDate" id="inventoryDate"
	value="${inventory.inventoryDate}">

<script>


/* function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    var errid = "err"+evt.target.id;
    var msg = '<label class="error" for="'+evt.target.id+'">Please enter a valid number.</label>';
    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57) && (charCode < 96 || charCode > 105)) {
    	$("#"+errid).html(msg);
        return false;
    } else {
    	$("#"+errid).html('');
        return true;
    }      
} */


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
		//$("#taskDate").val('');
		//$("#projectId").val('');
		//$("#taskTypeId").val('');
		$("#taskDesc").val('');
		//$("#startTime").val('');
		//$("#endTime").val('');
		$("#totalHour_").val('00:00');
		$("#noOfDefect").val('');
		$("#defectDesc").val('');
	}

	
	
	
	 function saveNewData() {
	//alert('hello');
	var inventoryTypeId = $("#inventoryTypeId").val();
	var productId = $("#productId").val();
	var quantity = $("#quantity").val();
	var unitId = $("#unitId").val();
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
		var link = "/pos/inventory/saveStoreProduct";
		//alert(link);
		$.ajax({type : "POST",
					url : link,
					
					data : "inventoryTypeId=" + inventoryTypeId 
					+ "&productId=" + productId 
					+ "&quantity=" + quantity 
					+ "&unitId=" + unitId
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
	
	var inventoryTypeId = $("#inventoryTypeId").val();
	var inventoryDate = $("#inventoryDate").val();

	//alert(inventoryTypeId);
	//alert(updateDate);
	if (inventoryTypeId == '') {
		alert("Inventory Type Empty!!!")
	} else {
		
		var link = "/pos/inventory/getInventoryList";
		//alert(link);
		$.ajax({
			type : "POST",
			url : link,
			data : "inventoryTypeId=" + inventoryTypeId + "&inventoryDate=" + inventoryDate,
			async : true,
			success : function(data) {
				$("#inventoryList").html(data);
				totalPriceCalc();
				$("#inventoryDownList").show();
			},

			error : function(data) {
				alert('Error!!!')
			}
		});
	}
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
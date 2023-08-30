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
			<li><span>Point of Sale &nbsp;</span></li>
			<li><span>Item Configuration &nbsp;</span></li>
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
					<h2 class="panel-title">Item Configuration</h2>
				</header>
				<div class="panel-body">
					<br />
					<div class="form-group col-md-12">
						<div class="col-sm-6 form-group">
							<label class="control-label col-md-5 col-sm-5 col-xs-12">Item
								Name</label>
							<div class="col-md-7 col-sm-7 col-xs-12">
								<select data-plugin-selectTwo class="form-control" id="itemId" onchange="getItemConfigList()"
									name="itemId" required>
									<option value="">Select One</option>
									<c:forEach items="${itemList}" var="list">
										<option value="${list.itemId}">${list.itemName}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="col-sm-2 form-group"></div>

						<!-- <div class="col-sm-5 form-group">
							<label
								class="control-label col-md-7 col-sm-7 col-xs-12 totalHour">Total
								Amount </label>
							<div class="col-md-5 col-sm-5 col-xs-12 totalHour">
								<span id="priceSum"></span>
							</div>
						</div> -->

					</div>


					<div class="row" style="padding-bottom: 10px">
						<div class="col-sm-6">
							<button id="" class="btn btn-primary" type="button"
								onclick="newData()">
								Add New Product <i class="fa fa-plus"></i>
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
									<th>Quantity</th>
									<th>Unit</th>
									<th>Action</th>

								</tr>
							</thead>
							<tbody>
								<tr style="cursor: pointer; display: none;" id="newdatarow">
									<td style="width: 190px"><select data-plugin-selectTwo
										class="form-control" id="productId" name="productId" required>
											<option value="">Select One</option>
											<c:forEach items="${productList}" var="list">
												<option value="${list.productId}">${list.productName}</option>
											</c:forEach>
									</select></td>
									<td><input type="text" class="form-control" id="quantity"></td>
									
									<td><select data-plugin-selectTwo class="form-control"
										id="unitId" required>
											<option value="">Select One</option>
											<c:forEach items="${unitList}" var="list">
												<option value="${list.unitId}">${list.unitName}</option>
											</c:forEach>
									</select></td>
									<td><a href="#" onclick="saveNewData()">
											<button class="btn btn-primary" type="button">
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


				<div class="panel-body" id="itemConfigDownList" style="display: none">
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
									<th>Action</th>
								</tr>
							</thead>
							<tbody id="itemConfigList">
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

<%-- <input type="hidden" name="updateDate" id="updateDate"
	value="${inventory.updateDate}"> --%>

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

	function newData() {
		$("#newdatarow").show();
	}

	function cancelnewdata() {
		$("#newdatarow").hide();
		$("#productId").select2("val", "");
		$("#quantity").val('');
		$("#unitId").select2("val", "");
	}
	
/* 	function cancelEditOldData(itemId,productId) {
		
		$("#productId_" + itemId + '_' + productId).next(".select2-container").hide();
		$("#quantity_" + itemId + '_' + productId).hide();
		$("#unitId_" + itemId + '_' + productId).next(".select2-container").hide();
		
		$("#edit_"+itmId+'_'+produtId).show();
		
		$("#update_" + itemId + '_' + productId).hide();
		$("#clear_"+itmId+'_'+produtId).hide();
		
	} */
	
	
	function editOldData(itmId,produtId) {
		
		var productId = $("#productId_"+itmId+'_'+produtId).show();
		productId.select2();
		$("#dataProductId_"+itmId+'_'+produtId).hide();
		
		
		var quantity = $("#quantity_"+itmId+'_'+produtId).show();
		//taskTypeId.select2();
		$("#dataQuantity_"+itmId+'_'+produtId).hide();

		var unitId = $("#unitId_"+itmId+'_'+produtId).show();
		unitId.select2();
		$("#dataUnitId_" + itmId+'_'+produtId).hide();
		
		$("#update_"+itmId+'_'+produtId).show();
		//$("#clear_"+itmId+'_'+produtId).show();
		$("#edit_"+itmId+'_'+produtId).hide();
		
	};
	

	function saveNewData() {
		//alert('hello');
		var itemId = $("#itemId").val();
		var productId = $("#productId").val();
		var quantity = $("#quantity").val();
		var unitId = $("#unitId").val();
		//var price = $("#price").val();
		//var employeeId = $("#employeeId").val();
		var msg = "";


		if (itemId == '') {
			alert("Please Select Item !!!");
		} else if (productId == '') {
			alert("Please Select Product !!!");
		} else if (quantity == '') {
			alert("Please Select Quantity !!!");
		} else if (unitId == '') {
			alert("Please Select Unit !!!");
		} else {
			//alert('HELLO');
			//var link = "/cbms/effortTracking/saveEffortTrackingInfo";
			var link = "/pos/pointOfSale/saveItemConfig";
			//alert(link);
			$
					.ajax({
						type : "POST",
						url : link,

						data : "itemId=" + itemId
								+ "&productId=" + productId + "&quantity="
								+ quantity + "&unitId=" + unitId,

						async : true,
						/* beforeSend : function(xhr) {
						// here it is
						xhr.setRequestHeader(header, token);
						},  */
						success : function(data) {
							//alert(data.mCode);
							if (data.mCode == '1111') {
								//cancelnewdata();
								//$("#updateDate").val(data.updateDate)
								getItemConfigList();

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
	};
	

	function getItemConfigList() {

		var itemId = $("#itemId").val();
		//var updateDate = $("#updateDate").val();

		//alert(inventoryTypeId);
		//alert(updateDate);
		if (itemId == '') {
			alert("Item Empty!!!")
		} else {

			var link = "/pos/pointOfSale/getItemConfigList";
			//alert(link);
			$.ajax({
				type : "POST",
				url : link,
				data : "itemId=" + itemId,
				async : true,
				success : function(data) {
					$("#itemConfigList").html(data);
					//totalPriceCalc();
					$("#itemConfigDownList").show();
				},

				error : function(data) {
					alert('Error!!!')
				}
			});
		}
	};

	function totalPriceCalc() {
		var inventoryTypeId = $("#inventoryTypeId").val();
		var updateDate = $("#updateDate").val();

		//alert(inventoryTypeId);
		//alert(updateDate);

		var link = "/pos/inventory/getPriceSum";
		//alert(link);
		$.ajax({
			type : "POST",
			url : link,
			data : "inventoryTypeId=" + inventoryTypeId + "&updateDate="
					+ updateDate,
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
	
	
	function saveUpdateData(itemId,productId) {
		
		var encItemId = $("#encItemId_" + itemId + '_' + productId).val();
		var productId = $("#productId_" + + itemId + '_' + productId).val();
		var quantity = $("#quantity_" + itemId + '_' + productId).val();
		var unitId = $("#unitId_" + itemId + '_' + productId).val();
		
		var msg = "";

	    if (productId == '') {
			alert("Please Select Product !!!");
		} else if (quantity == '') {
			alert("Please Select Quantity !!!");
		} else if (unitId == '') {
			alert("Please Select Unit !!!");
		} else {
			//alert('HELLO');
			//var link = "/cbms/effortTracking/saveEffortTrackingInfo";
			var link = "/pos/pointOfSale/saveItemConfig";
			//alert(link);
			$
					.ajax({
						type : "POST",
						url : link,

						data : 
								"encItemId=" + encodeURIComponent(encItemId)
								+ "&productId=" + productId 
								+ "&quantity=" + quantity 
								+ "&unitId=" + unitId,

						async : true,
						/* beforeSend : function(xhr) {
						// here it is
						xhr.setRequestHeader(header, token);
						},  */
						success : function(data) {
							//alert(data.mCode);
							if (data.mCode == '1111') {
								//cancelnewdata();
								//$("#updateDate").val(data.updateDate)
								getItemConfigList();

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
	};
	
	
	/* function saveUpdateData(effortId) {

	var taskDate = $("#taskDate").val();
	var encEffortTrackingId = $("#encEffortTrackingId_" + effortId).val();
	var projectId = $("#projectId_" + effortId).val();
	var taskTypeId = $("#taskTypeId_" + effortId).val();
	var taskDesc = $("#taskDesc_" + effortId).val();
	var startTime = $("#startTime_" + effortId).val();
	var endTime = $("#endTime_" + effortId).val();
	var noOfDefect = $("#noOfDefect_" + effortId).val();
	var defectDesc = $("#defectDesc_" + effortId).val();
	var msg = "";

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	if (projectId == '') {
		alert("Please Select Project !!!");
	} else {
		var link = "/cbms/effortTracking/saveEffortTrackingInfo";

		//alert(link);

		$
				.ajax({
					type : "POST",
					url : link,
					data : "encEffortTrackingId="
							+ encodeURIComponent(encEffortTrackingId)
							+ "&projectId=" + projectId + "&taskDate="
							+ encodeURIComponent(taskDate) + "&taskTypeId="
							+ taskTypeId + "&taskDesc="
							+ encodeURIComponent(taskDesc) + "&startTime="
							+ startTime + "&endTime=" + endTime
							+ "&noOfDefect=" + noOfDefect + "&defectDesc="
							+ encodeURIComponent(defectDesc),

					async : true,
					beforeSend : function(xhr) {
						// here it is
						xhr.setRequestHeader(header, token);
					},
					success : function(data) {

						if (data.messageCode == '1111') {
							cancelnewdata();
							getEffortList();

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
} */
	
	
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- end: page -->
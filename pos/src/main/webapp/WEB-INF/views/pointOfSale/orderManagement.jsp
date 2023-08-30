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
			<li><span>Order Management &nbsp;</span></li>
		</ol>
	</div>
</header>


<!-- start: page -->

<div class="row">

	<div class="col-lg-12">

		<!-- message -->

		<div id="msg"></div>


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


		<form class="form-horizontal form-bordered" id="form"
			autocomplete="off" action="/pos/pointOfSale/saveOrder" method="post" onkeypress="return event.keyCode != 13;">

			<!-- start: page -->
			<section class="panel panel-featured panel-featured-primary">
				<header class="panel-heading">
					<h2 class="panel-title">Order Management</h2>
				</header>
				<div class="panel-body">
					<br />
					<div class="form-group col-md-12">

						<div class="col-sm-6 form-group">
							<label class="control-label col-md-6 col-sm-6 col-xs-12">Owner
								Food</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<select class="form-control" id="ownerFoodYn" name="ownerFoodYn">
									<option
										<c:if test="${pointOfSale.ownerFoodYn == 'N'}">selected = "selected" </c:if>
										value="N">NO</option>
									<option
										<c:if test="${pointOfSale.ownerFoodYn == 'Y'}">selected = "selected" </c:if>
										value="Y">YES</option>
								</select>
							</div>
						</div>

						<div class="col-sm-6 form-group">
							<label class="control-label col-md-6 col-sm-6 col-xs-12"
								for="orderNote">Item Note</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" class="form-control" id="orderNote"
									maxlength="500" value="${pointOfSale.orderNote}">
							</div>
						</div>

					</div>

					<div class="form-group col-md-12">

						<div class="col-sm-6 form-group">
							<label class="col-md-6 col-sm-6 control-label"> Waiter
								Name <span class="required">*</span>
							</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<select data-plugin-selectTwo class="form-control"
									name="employeeId" required id="employeeId"
									onkeypress="goToNext(event,'levelNo')">
									<option value="">Please Select One</option>
									<c:forEach items="${waiterList}" var="list">
										<option
											<c:if test="${list.employeeId == pointOfSale.employeeId}">selected = "selected" </c:if>
											value="${list.employeeId}">${list.firstName}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="col-sm-6 form-group">
							<label class="control-label col-md-6 col-sm-6 col-xs-12"
								for="tableNo">Table No <span class="required">*</span></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" required class="form-control mandatory"
									name="tableNo" id="tableNo"
									onkeydown="return isNumberKey(event)"
									value="${pointOfSale.tableNo}" onblur="getDuplicteTable(this.value)">

								<div id="errtableNo"></div>

							</div>
						</div>

					</div>
					
					<script>
					 function getDuplicteTable (tableNo) {
						var encOrderId = $("#encOrderId").val();
						//alert(encOrderId);
						if (tableNo != '' && encOrderId == '') {
							//alert(tableNo);
							var link = "/pos/pointOfSale/getDuplicteTable";
							var errMessage = '<label class="error">Order pending in table no '+ tableNo+'</label>';
							$
									.ajax({
										type : "POST",
										url : link,
										data : "tableNo=" + tableNo,
										async : true,

										success : function(data) {
											//alert(data.countDuplicateTable);
											if (data.countDuplicateTable > 0) {
												$("#tableNo").val('');
												$("#errtableNo").html(errMessage);
											} else {
												$("#errtableNo").html('');
											}
											//$("#orderTotalAmountBillPrint").val(data.orderTotalAmount);
											//$("#netPayableAmountBillPrint").val(data.orderTotalAmount); 
										}
									});
						}
					} 
					</script>

					

					<div class="form-group col-md-12">

						<div class="col-sm-6 form-group">
							<label class="control-label col-md-6 col-sm-6 col-xs-12">Item
								Name <c:if test="${pointOfSale.encOrderId == null}"><span class="required">*</span></c:if></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<select data-plugin-selectTwo class="form-control" id="itemId"
									name="itemId" <c:if test="${pointOfSale.encOrderId == null}"> required="required" </c:if>>
									<option value="">Select One</option>
									<c:forEach items="${itemList}" var="list">
										<option value="${list.itemId}">${list.itemName}</option>
									</c:forEach>
								</select>
							</div>
						</div>


						<div class="col-sm-6 form-group">
							<label class="control-label col-md-6 col-sm-6 col-xs-12" for="">Quantity 
							<c:if test="${pointOfSale.encOrderId == null}"><span class="required">*</span></c:if></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" class="form-control <c:if test="${pointOfSale.encOrderId == null}">mandatory</c:if>"
									name="quantity" id="quantity" 
									<c:if test="${pointOfSale.encOrderId == null}"> required="required" </c:if>
									onkeydown="return isNumberKey(event)"
									value="${pointOfSale.quantity}">

								<div id="errquantity"></div>

							</div>
						</div>



					</div>
				</div>

				<div class="panel-body" id="">
					<input type="hidden" id="sl" value="${orderInfoList.size()}">
					<br />
					<div style="overflow: scroll; max-width: 100%;">

						<input type='hidden' id='orderIndex' value="${orderInfoListIndex}" />

						<input type='hidden' id='encOrderId' name='encOrderId'
							value='${pointOfSale.encOrderId}' />

						<table
							class="table table-bordered table-striped table-condensed mb-none"
							id="dataTable">
							<thead>
								<tr>
									<th>Serial No</th>
									<th>Item Name</th>
									<th>Item Price</th>
									<th>Quantity</th>
									<th>Sub Total</th>
									<th>Order Note</th>
									<th>Action</th>
									<%-- <c:if test="${!empty orderInfoList}">
										<th>Edit</th>
									</c:if> --%>
								</tr>
							</thead>
							<tbody id="orderInfoList">

								<c:if test="${!empty orderInfoList}">
									<%
										int j = 0;
									%>
									<c:forEach items="${orderInfoList}" var="list">
										<tr style="cursor: pointer">

											<td style="border-style: inset;" id="sl">
												<%-- ${orderSl} --%> <%
 												out.print(j + 1);
 												%> <input type='hidden' id='encItemOrderId_${list.encItemOrderId}'
												value='${list.encItemOrderId}'
												name='orderModelList[<% out.print(j);%>].encItemOrderId'>

											</td>

											<td style="border-style: inset;">${list.itemName}<input
												type='hidden' id='itemId_${list.itemId}'
												value='${list.itemId}'
												name='orderModelList[<% out.print(j);%>].itemId'>
											</td>

											<td style="border-style: inset;">${list.itemPrice}<input
												type='hidden' id='itemPrice_${list.itemPrice}'
												value='${list.itemPrice}'
												name='orderModelList[<% out.print(j);%>].itemPrice'>
											</td>

											<td style="border-style: inset;">${list.quantity}<input
												type='hidden' id='quantity_${list.quantity}'
												value='${list.quantity}'
												name='orderModelList[<% out.print(j);%>].quantity'>
											</td>

											<td style="border-style: inset;">${list.subTotal}<input
												type='hidden' id='subTotal_${list.subTotal}'
												value='${list.subTotal}'
												name='orderModelList[<% out.print(j);%>].subTotal'>
											</td>

											<td style="border-style: inset;">${list.orderNote}<input
												type='hidden' id='orderNote_${list.orderNote}'
												value='${list.orderNote}'
												name='orderModelList[<% out.print(j);%>].orderNote'>
											</td>

											<td style="border-style: inset;"><c:if
													test="${!empty list.encOrderId }">
													<a href='#' onclick='removetr(this)'><button
															class="btn btn-xs btn-primary" type="button">&times;
														</button></a>
												</c:if></td>

											<%-- <td style="border-style: inset;"><c:if
													test="${!empty list.encOrderId }">
													<a href='#' onclick='removetr(this)'><button
															type="button" class="btn btn-primary"
															onclick="getOrderEditList('${list.encOrderId}')">
															<i class="fa fa-edit"></i>
														</button></a>
												</c:if>
											</td> --%>

										</tr>
										<%
											j = j + 1;
										%>

									</c:forEach>
								</c:if>

								<c:if test="${! empty orderEditListNotFound}">
									<tr id="orderEditListNotFound">
										<td colspan="8"><p>${orderEditListNotFound}</p></td>
									</tr>
								</c:if>
							</tbody>

						</table>
						<p id="totalPrice" style="font-size: 20px"></p>
					</div>
				</div>


				<footer class="panel-footer">
					<div class="row">
						<div class="col-sm-offset-5">
							<button class="btn btn-primary" type="submit">Submit</button>
							<a href="/pos/pointOfSale/orderManagement"><button
									class="btn btn-default" type="button" role="button">Clear</button></a>
							<%-- <c:if test="${pointOfSale.encOrderId != null}">
								<button class="btn btn-primary" type="button"
									onclick="orderFinalizeReport('${pointOfSale.encOrderId}')">Print</button>
							</c:if> --%>
							<a href="/pos/pointOfSale/orderReprint"><button
									class="btn btn-primary" type="button">Re-Print</button></a>
						</div>

					</div>
				</footer>

			</section>

		</form>
		<!-- end: page -->

	</div>
</div>


<div class="row">
	<div class="col-lg-12">
		<form class="form-horizontal form-bordered" id="form"
			autocomplete="off" action="/pos/pointOfSale/saveOrder" method="post">

			<!-- start: page -->
			<section class="panel panel-featured panel-featured-primary">
				<header class="panel-heading">
					<h2 class="panel-title">Pending Order List</h2>
				</header>

				<div class="panel-body">
					<div class="row">
						<div class="table-responsive">
							<table id="datatable-default"
								class="table table-striped table-condensed table-hover mb-none">
								<thead>
									<tr>
										<th>#</th>
										<th>Table No</th>
										<th>Date & Time</th>
										<th>Order No</th>
										<th>Waiter Name</th>
										<th>View</th>
										<th>Edit</th>
										<th>QT</th>
										<th>Bill</th>
										<th>Receipt</th>
										<th>Remove</th>
										<th>Cancel</th>
									</tr>
								</thead>
								<tbody>
									<%
										int i = 1;
									%>
									<c:if test="${!empty pendingOrderList}">

										<c:forEach items="${pendingOrderList}" var="list">
											<tr
												<c:if test="${list.ownerFoodYn == 'Y'}">
											style="color: red"
											</c:if>>
												<td>
													<%
														out.print(i);
																i++;
													%>
												</td>
												<td style="text-align: center;">${list.tableNo}</td>
												<td>${list.orderDate}</td>
												<td style="text-align: center;">${list.orderNo}</td>

												<td>${list.employeeName}</td>

												<td><button
														onclick="getPendingOrderInfoList('${list.encOrderId}')"
														class="btn btn-mg btn-primary modal-with-move-anim"
														href="#modalOrderInfoList" type="button">
														<i class="fa fa-eye"></i>
													</button></td>
												<td><c:if
														test="${list.finalizedYn == 'N' && list.billPrintYn == 'N'}">
														<button type="button" class="btn btn-primary"
															onclick="getOrderEditList('${list.encOrderId}', '${list.employeeId}','${list.tableNo}','${list.ownerFoodYn}','${list.orderNote}')">
															<i class="fa fa-edit"></i>
														</button>
													</c:if></td>
												<td><%-- <c:if test="${list.finalizedYn == 'N'}"> --%>
														<button type="button" class="btn btn-warning"
															onclick="printKitchenQT('${list.encOrderId}')">
															<i class="fa fa-print"></i>
														</button>
													<%-- </c:if> --%></td>

												<td style="text-align: center;" nowrap><c:choose>
														<c:when test="${list.billPrintYn == 'N' && list.finalizedYn == 'N'}">
															<button
																onclick="getOrderTotalAmountBillPrint('${list.encOrderId}')"
																class="btn btn-primary modal-with-move-anim"
																href="#modalBillPrintInfo" type="button">Bill
																Create</button>
														</c:when>
														<c:otherwise>
															<button class="btn btn-primary" type="button"
																onclick="customerBillCopy('${list.encOrderId}')">
																<i class="fa fa-print"></i>
															</button>
														</c:otherwise>
													</c:choose></td>

												<td><c:choose>
														<c:when test="${list.finalizedYn == 'N'}">
															<button
																onclick="getOrderTotalAmount('${list.encOrderId}')"
																class="btn btn-mg btn-success modal-with-move-anim"
																href="#modalMoneyReceiptInfo" type="button">Finalize</button>
														</c:when>
														<c:otherwise>
															<button class="btn btn-success" type="button"
																onclick="customerMoneyReceipt('${list.encOrderId}')">
																<i class="fa fa-print"></i>
															</button>
														</c:otherwise>
													</c:choose></td>

												<td><c:if test="${list.finalizedYn == 'Y'}">
														<button class="btn btn-success" type="button"
															onclick="orderProcessComplete('${list.encOrderId}')">
															<i class="fa fa-eye-slash"></i>
														</button>
													</c:if></td>
												<td><c:if test="${list.finalizedYn == 'N'}">
														<button class="btn btn-xs btn-danger"
															onclick="cancelOrder('${list.encOrderId}')" type="button">&times;</button>
													</c:if></td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${! empty pendingOrderListNotFound}">
										<tr>
											<td colspan="12"><p>${pendingOrderListNotFound}</p></td>
											<td style="display: none;"></td>
											<td style="display: none;"></td>
											<td style="display: none;"></td>
											<td style="display: none;"></td>
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
				</div>


				<footer class="panel-footer">
					<div class="row"></div>
				</footer>

			</section>

		</form>
		<!-- end: page -->

	</div>
</div>

<!----------------------------------------------------- Start Order Finalize Modal ----------------------------------------------------------------------->


<div id="modalMoneyReceiptInfo"
	class="zoom-anim-dialog modal-block modal-block-lg mfp-hide">
	<!-- class="zoom-anim-dialog modal-block modal-block-sm mfp-hide"> modal-block modal-block-full mfp-hide  -->
	<!-- class="mb-xs mt-xs mr-xs modal-sizes btn btn-default"  -->
	<form class="form-horizontal form-bordered" id="formExtra"
		method="post" autocomplete="off"
		action="/pos/pointOfSale/saveOrderFinalize"
		onkeypress="return event.keyCode != 13;">
		<section class="panel panel-featured panel-featured-primary">
			<header class="panel-heading">
				<button class="close modal-dismiss" type="button"
					id="btnModalEmpListClose" onclick="clearFinalizeModal()">
					<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
				</button>
				<h2 class="panel-title">Customer Payment</h2>
			</header>

			<div class="panel-body">


				<div class="col-md-12" id="modalMsg" style="text-align: center;"></div>


				<input type='hidden' id='modal_encOrderId' name='encOrderId' />

				<div class="form-group col-md-12">
					<div class="col-sm-6">
						<label class="col-md-6 control-label" for="orderTotalAmount">
							Payable Amount </label>
						<div class="col-md-6">
							<!-- <p id="orderTotalAmount"></p> -->
							<input type="text" class="form-control" readonly
								name="orderTotalAmount" id="orderTotalAmount"
								value="${pointOfSale.orderTotalAmount}" />
						</div>
					</div>

					<div class="col-md-6">
						<label class="col-md-6 control-label" for="netPayableAmount">
							Net Payable <span class="required"> * </span>
						</label>
						<div class="col-md-6">
							<input type="text" required class="form-control" readonly
								name="netPayableAmount" id="netPayableAmount"
								value="${pointOfSale.netPayableAmount}" />
						</div>
					</div>

				</div>


				<div class="form-group col-md-12">
					<div class="col-sm-6">
						<label class="col-md-6 control-label"> Discount/Gift
							Amount </label>
						<div class="col-md-6">
							<input type="text" class="form-control" name="discountAmount"
								id="discountAmount" value=""
								onkeypress="return isNumberKey(event)"
								onkeyup="getDiscountAmount()" onblur="getDiscountAmount()" />

							<div id="errdiscountAmount"></div>
						</div>
					</div>
					<div class="col-md-6">
						<label class="col-md-6 control-label"> Discount/Gift
							Reason </label>
						<div class="col-md-6">
							<input type="text" class="form-control"
								name="discountReferenceBy" id="discountReferenceBy" value="" />
							<%-- <select data-plugin-selectTwo class="form-control" required
								id="referenceById" name="referenceById">
								<option value="">Select One</option>
								<c:forEach items="${employeeList}" var="list">
									<option
										<c:if test="${list.employeeId==pointOfSale.referenceById}">selected="selected"</c:if>
										value="${list.employeeId}">${list.firstName}</option>
								</c:forEach>
							</select> --%>
						</div>
					</div>

				</div>


				<div class="form-group col-md-12">
					<div class="col-sm-6">
						<label class="col-md-6 control-label" for="cashPayAmount">
							Cash Pay Amount <span class="required"> * </span>
						</label>
						<div class="col-md-6">
							<input type="text" class="form-control mandatory"
								name="cashPayAmount" id="cashPayAmount" required value=""
								onkeypress="return isNumberKey(event)"
								onkeyup="getCashPayAmount()" onblur="getCashPayAmount()" />

							<div id="errcashPayAmount"></div>
						</div>
					</div>
					<div class="col-sm-6">
						<label class="col-md-6 control-label" for="cardPayAmount">
							Card Pay Amount </label>
						<div class="col-md-6">
							<input type="text" class="form-control" name="cardPayAmount"
								id="cardPayAmount" onkeypress="return isNumberKey(event)"
								onkeyup="getCardPayAmount()" onblur="getCardPayAmount()" />

							<div id="errcardPayAmount"></div>
						</div>

					</div>
				</div>

				<div class="form-group col-md-12">
					<div class="col-sm-6">
						<label class="col-md-6 control-label" for="bkashPaymentAmount">
							bKash Pay Amount </label>
						<div class="col-md-6">
							<input type="text" class="form-control" name="bkashPaymentAmount"
								id="bkashPaymentAmount" value=""
								onkeypress="return isNumberKey(event)"
								onkeyup="getBkashPayAmount()" onblur="getBkashPayAmount()" />

							<div id="errbkashPaymentAmount"></div>
						</div>
					</div>
					<div class="col-sm-6">
						<label class="col-md-6 control-label" for="bkashTranNo">
							bKash Transaction No </label>
						<div class="col-md-6">
							<input type="text" class="form-control" name="bkashTranNo"
								id="bkashTranNo" value="" />
						</div>

					</div>
				</div>

				<!-- </div> -->
				<div class="form-group col-md-12">
					<div class="col-sm-6">
						<label class="col-md-6 control-label" for="receivedAmount">Cash
							Received Amount </label>
						<div class="col-md-6">
							<input type="text" class="form-control" name="receivedAmount"
								id="receivedAmount" value=""
								onkeypress="return isNumberKey(event)"
								onkeyup="getChangeAmount()" onblur="getChangeAmount()" />
							<div id="errreceivedAmount"></div>
						</div>
					</div>
					<div class="col-sm-6">
						<label class="col-md-6 control-label" for="changeAmount">Cash
							Change Amount </label>
						<div class="col-md-6">
							<input type="text" class="form-control" readonly
								name="changeAmount" id="changeAmount" value="" />
						</div>
					</div>
				</div>


				<div class="col-md-12"></div>
			</div>
			
			<header class="panel-heading">
				<h2 class="panel-title">Due Section</h2>
			</header>
			<div class="panel-body">


				<!-- <div class="col-md-12" id="modalMsg" style="text-align: center;"></div> -->


				<input type='hidden' id='modal_encOrderId' name='encOrderId' />

				<div class="form-group col-md-12">
					<div class="col-sm-6">
						<label class="col-md-6 control-label" for="">
							Due Customer <span class="required"> * </span></label>
						<div class="col-md-6">
							<select class="form-control" id="dueCustomerYn" name="dueCustomerYn" onchange="getDueCustomerDetails(this.value)">
									<option value="N">NO</option>
									<option value="Y">YES</option>
								</select>
						</div>
					</div>

					<div class="col-md-6" style="display: none" id="customerNameDiv">
						<label class="col-md-6 control-label" for="dueCustomerId">
							Customer Name <span class="required"> * </span>
						</label>
						<div class="col-md-6">
							<select class="form-control" name="dueCustomerId"
								id="dueCustomerId" onchange="getDueDepositeAmount(this.value)">
								<option value="">Please Select One</option>
								<c:forEach items="${customerList}" var="list">
									<option value="${list.dueCustomerId}">${list.customerName}</option>
								</c:forEach>
							</select>
						</div>
					</div>

				</div>


				<div class="form-group col-md-12" style="display: none" id="customerDueDetailDiv">
					<div class="col-sm-6">
						<label class="col-md-6 control-label"> Due/Deposite
							Amount <span class="required"> * </span></label>
						<div class="col-md-6">
							<input type="text" class="form-control"
								id="dueAmount" value="" readonly/>
						</div>
					</div>
					<div class="col-md-6">
						<label class="col-md-6 control-label"> Paid Amount <span class="required"> * </span></label>
						<div class="col-md-6">
							<input type="text" class="form-control"
								name="paidAmount" id="paidAmount"/>
						</div>
					</div>

				</div>

				<div class="col-md-12"></div>
			</div>

			<!-- <input type="text" class="form-control"
								name="billPrintYn" id="billPrintYn"/> -->
			<footer class="panel-footer">
				<div class="row">
					<div class="col-sm-offset-5">
						<button class="btn btn-primary" type="submit" id="paymentSubmit">Submit</button>
						<a href="/pos/pointOfSale/orderManagement"><button
								class="btn btn-default" type="button" role="button">Close</button></a>
						<a href="/pos/pointOfSale/dueCustomer"><button
								class="btn btn-default" type="button" role="button">Add
								Due Customer</button></a>
					</div>
				</div>
			</footer>
		</section>
	</form>
</div>

<script>

function getDueDepositeAmount(dueCustomerId) {
	//alert(dueCustomerId);
	var link = "/pos/pointOfSale/getDueDepositeAmount";
	$
			.ajax({
				type : "POST",
				url : link,
				data : "dueCustomerId=" + dueCustomerId,
				async : true,

				success : function(data) {
					$("#dueAmount").val(data.dueAmount);
				}
			});
	
}

 function getDueCustomerDetails(dueCustomerYn) {
	//alert(dueCustomerYn);
	if (dueCustomerYn == 'Y') {
		$("#customerNameDiv").show();
		$("#customerDueDetailDiv").show();
		$("#customerName").attr(required, true);
		$("#modalMsg").html('');
	} else {
		$("#customerNameDiv").hide();
		$("#customerDueDetailDiv").hide();
	}
	
};

function clearFinalizeModal () {
	//alert('hello');
$("#orderTotalAmount").val('');
$("#netPayableAmount").val('');
$("#discountAmount").val('');
$("#discountReferenceBy").val('');
$("#cashPayAmount").val('');
$("#cardPayAmount").val('');
$("#bkashPaymentAmount").val('');
$("#bkashTranNo").val('');
$("#receivedAmount").val('');
$("#changeAmount").val('');
}
</script>

<!-------------------------------- Ending order finalize Modal --------------------------------------------->



<!----------------------------------------------------- Start bill print Modal ----------------------------------------------------------------------->


<div id="modalBillPrintInfo"
	class="zoom-anim-dialog modal-block modal-block-lg mfp-hide">
	<!-- class="zoom-anim-dialog modal-block modal-block-sm mfp-hide"> modal-block modal-block-full mfp-hide  -->
	<!-- class="mb-xs mt-xs mr-xs modal-sizes btn btn-default"  -->
	<form class="form-horizontal form-bordered" id="formBillPrint"
		method="post" autocomplete="off"
		action="/pos/pointOfSale/saveOrderBillPrint"
		onkeypress="return event.keyCode != 13;">
		<section class="panel panel-featured panel-featured-primary">
			<header class="panel-heading">
				<button class="close modal-dismiss" type="button"
					id="btnModalEmpListClose" onclick="clearBillPrintModal()">
					<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
				</button>
				<h2 class="panel-title">Bill Print</h2>
			</header>

			<div class="panel-body">


				<div class="col-md-12" id="modalMsgBillPrint" style="text-align: center;"></div>


				<input type='hidden' id='modal_encOrderId_bill_print' name='encOrderId' />

				<div class="form-group col-md-12">
					<div class="col-sm-6">
						<label class="col-md-6 control-label" for="orderTotalAmountBillPrint">
							Payable Amount </label>
						<div class="col-md-6">
							<!-- <p id="orderTotalAmount"></p> -->
							<input type="text" class="form-control" readonly
								name="orderTotalAmount" id="orderTotalAmountBillPrint"/>
						</div>
					</div>

					<div class="col-md-6">
						<label class="col-md-6 control-label" for="netPayableAmountBillPrint">
							Net Payable <span class="required"> * </span>
						</label>
						<div class="col-md-6">
							<input type="text" required class="form-control" readonly
								name="netPayableAmount" id="netPayableAmountBillPrint"/>
						</div>
					</div>

				</div>


				<div class="form-group col-md-12">
					<div class="col-sm-6">
						<label class="col-md-6 control-label"> Discount/Gift
							Amount </label>
						<div class="col-md-6">
							<input type="text" class="form-control" name="discountAmount"
								id="discountAmountBillPrint" 
								onkeypress="return isNumberKey(event)"
								onkeyup="getDiscountAmountBillPrint()" />

							<div id="errdiscountAmountBillPrint"></div>
						</div>
					</div>
					<div class="col-md-6">
						<label class="col-md-6 control-label"> Discount/Gift
							Reason </label>
						<div class="col-md-6">
							<input type="text" class="form-control"
								name="discountReferenceBy" id="discountReferenceByBillPrint" value="" />
							<%-- <select data-plugin-selectTwo class="form-control" required
								id="referenceById" name="referenceById">
								<option value="">Select One</option>
								<c:forEach items="${employeeList}" var="list">
									<option
										<c:if test="${list.employeeId==pointOfSale.referenceById}">selected="selected"</c:if>
										value="${list.employeeId}">${list.firstName}</option>
								</c:forEach>
							</select> --%>
						</div>
					</div>

				</div>

			</div>
			<footer class="panel-footer">
				<div class="row">
					<div class="col-sm-offset-5">
						<button class="btn btn-primary" type="submit" id="billPrintSubmit">Submit</button>
						<a href="/pos/pointOfSale/orderManagement"><button
								class="btn btn-default" type="button" role="button">Close</button></a>
					</div>
				</div>
			</footer>
		</section>
	</form>
</div>

<script>
function clearBillPrintModal (){
	//alert('hello');
	$("#orderTotalAmount").val('');
	$("#netPayableAmount").val('');
	$("#discountAmountBillPrint").val('');
	$("#discountReferenceByBillPrint").val('');
}
</script>

<!-------------------------------- Ending bill print Modal --------------------------------------------->


<!-------------------------------- start modal makers country ------------------------------------------>

<div id="modalOrderInfoList"
	class="zoom-anim-dialog modal-block modal-block-lg mfp-hide">
	<!-- class="modal-block modal-block-sm mfp-hide"> -->

	<section class="panel panel-featured panel-featured-primary">
		<header class="panel-heading">
			<button class="close modal-dismiss" type="button"
				id="makersCountryListClose">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<h2 class="panel-title">Pending Order Info Details</h2>
		</header>

		<div class="panel-body">

			<div class="row">
				<div class="col-md-12">
					<div style="overflow: scroll; max-height: 300px;">

						<table
							class="table table-striped table-condensed table-hover mb-none ">
							<thead>
								<tr>
									<th>#</th>
									<th>Item Name</th>
									<th>Quantity</th>
									<th>Item Price</th>
									<th>Sub Total</th>
									<th>Order Note</th>
								</tr>
							</thead>
							<tbody id="pendingOrderInfoList">
							</tbody>

						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>


<!-------------------------------- end modal makers country ------------------------------------------>


<%-- <input type="hidden" name="updateDate" id="updateDate"
	value="${inventory.updateDate}"> --%>

<script type="text/javascript">
 var quantity = document.getElementById("quantity");
//alert(quantity);
quantity.addEventListener("keydown", function (e) {
    if (e.keyCode === 13) {  //checks whether the pressed key is "Enter"
    	orderAddList();
    }
});
</script>

<script>



function getOrderEditList(encOrderId,employeeId,tableNo,ownerFoodYn,orderNote) {
	//alert(encStudentId);
	var url = "/pos/pointOfSale/getOrderEditList?encOrderId="
			+ encodeURIComponent(encOrderId) + "&employeeId=" + employeeId + "&tableNo=" + tableNo + "&ownerFoodYn=" + ownerFoodYn + "&orderNote=" + orderNote;
	//alert(url);
	window.location = url;
}



function orderAddList() {
	//alert('hello');
	var tableNo = $("#tableNo").val();
	var employeeId = $("#employeeId").val();
	var itemId = $("#itemId").val();
	var quantity = $("#quantity").val();
	var orderNote = $("#orderNote").val();
	var trIndex = $("#orderIndex").val();
	var content = "";
	var myElem = document.getElementById("encItemId_" + itemId);
	var totalPrice = "";
	//alert(quantity)

	var sl = $("#sl").val();
	if (sl == '') {
		sl = 0;
	}
	sl = parseInt(sl) + 1;

	if (employeeId == '') {
		content = "<div class='alert alert-danger'>"
				+ "<button class='close' aria-hidden='true' data-dismiss='alert' "
		+ "type='button'>&times;</button>"
				+ "<strong>Select Waiter Name First !</strong>" + "</div>";

		$("#msg").html(content);
		
	} else if (tableNo == '') {
		content = "<div class='alert alert-danger'>"
				+ "<button class='close' aria-hidden='true' data-dismiss='alert' "
		+ "type='button'>&times;</button>"
				+ "<strong>Enter Table No First !</strong>" + "</div>";

		$("#msg").html(content);
		//$("#itemId").focus();
	
	} else if (itemId == '') {
		content = "<div class='alert alert-danger'>"
				+ "<button class='close' aria-hidden='true' data-dismiss='alert' "
		+ "type='button'>&times;</button>"
				+ "<strong>Select Item Name First !</strong>" + "</div>";

		$("#msg").html(content);
		//$("#itemId").focus();
	} else if (quantity == '') {
		content = "<div class='alert alert-danger'>"
				+ "<button class='close' aria-hidden='true' data-dismiss='alert' "
		+ "type='button'>&times;</button>"
				+ "<strong>Select Quantity !</strong>" + "</div>";

		$("#msg").html(content);
		//$("#quantity").focus();
	} else {
		
		/* if (trIndex > 10) {
			content = "<div class='alert alert-danger'>"
				+ "<button class='close' aria-hidden='true' data-dismiss='alert' "
		+ "type='button'>&times;</button>"
				+ "<strong>Maximum 10 Orders Can Be Generated In One Receipt !!</strong>" + "</div>"

		$("#msg").html(content);
		} else { */

		if (trIndex == '') {
			var trIndexResult = '0';
		} else {
			var trIndexResult = parseInt(trIndex) + 1;
		}

		var link = "/pos/pointOfSale/getOrderInfoSList";
		$
				.ajax({
					type : "POST",
					url : link,
					data : "itemId=" + itemId + "&quantity=" + quantity,

					async : true,

					success : function(data) {
						$("#orderInfoListNotFound").remove();
						if (data.mCode == '1111') {
							//alert(data.orderPrice);
							/* var subTotal = data.itemPrice * quantity; */
							//alert(subTotal);
							var content = "<tr>"
									+ "<td style='border-style: inset;'>"
									+ sl
									+ "<input type='hidden'  name='orderModelList["+ trIndexResult+ "].encItemOrderId' value='' />"
									+ "</td>"
									+ "<td style='border-style: inset;'><input type='hidden' id='itemId_" + itemId+ "'" + " name='orderModelList["+ trIndexResult+ "].itemId' value='"+ itemId+ "' />"
									+ data.itemName
									+ "</td>"
									+ "</td>"
									+ "<td style='border-style: inset;'><input type='hidden' id='itemPrice_" + data.itemPrice+ "'" + " name='orderModelList["+ trIndexResult+ "].itemPrice' value='"+ data.itemPrice+ "' />"
									+ data.itemPrice
									+ "</td>"
									+ "<td style='border-style: inset;'><input type='hidden' id='quantity_"+ quantity+ "'"+ " name='orderModelList["+ trIndexResult+ "].quantity' value='"+ quantity+ "'/>"
									+ quantity
									
									+ "<td style='border-style: inset;'><input type='hidden' id='subTotal_" + data.subTotal+ "'" + " name='orderModelList["+ trIndexResult+ "].subTotal' value='"+ data.subTotal+ "' />"
									+ data.subTotal
									+ "</td>"
									+ "<td style='border-style: inset;'><input type='hidden' id='orderNote_"+ orderNote+ "'"+ " name='orderModelList["+ trIndexResult+ "].orderNote' value='"+ orderNote+ "'/>"
									+ orderNote
									
									+ "</td>"
									+ "<td style='border-style: inset;'><a href='#' onclick='removetr(this)'><button class='btn btn-xs btn-primary' type='button'>&times;</button></a></td>"
									/* + "<td>" + "</td>" */
									+ "</tr>";
							// alert(content);

							$("#msg").html('');
							$("#orderInfoList").append(content);
							$("#orderIndex").val(trIndexResult);
							//$("#receiptId").val('');
							$("#sl").val(sl);

							totalPrice = data.orderPrice;
							//alert(totalPrice);
							$("#totalPrice").val(totalPrice);
							
							

						} else {
							content = "<div class='alert alert-danger'>"
									+ "<button class='close' aria-hidden='true' data-dismiss='alert' "
									+ "type='button'>&times;</button>"
									+ "<strong>" + data.message
									+ "</strong>" + "</div>"

							$("#msg").html(content);
									alert(content)
						}
					},
					error : function(data) {
						alert('Error!!!')
					}
				//}
				});
		$("#orderIndex").val(trIndexResult);
	}

}



	function removetr(e) {
		var whichtr = $(e).closest("tr");
		whichtr.remove();
	};

	
		function isNumberKey(evt) {
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
		}


	function getOrderTotalAmount(encOrderId) {
		$("#modal_encOrderId").val(encOrderId);
		var link = "/pos/pointOfSale/getOrderTotalAmount";
		$
				.ajax({
					type : "POST",
					url : link,
					data : "encOrderId=" + encodeURIComponent(encOrderId),
					async : true,

					success : function(data) {
						//alert(data.orderTotalAmount);
						$("#orderTotalAmount").val(data.orderTotalAmount);
						
						if(data.netPayableAmount == '' || data.netPayableAmount == '0') {
							$("#netPayableAmount").val(data.orderTotalAmount); 
						} else {
							$("#netPayableAmount").val(data.netPayableAmount); 
						}
						$("#discountAmount").val(data.discountAmount); 
						$("#discountReferenceBy").val(data.discountReferenceBy); 
					}
				});
			};
			
			
			function getOrderTotalAmountBillPrint(encOrderId) {
				$("#modal_encOrderId_bill_print").val(encOrderId);
				var link = "/pos/pointOfSale/getOrderTotalAmount";
				$
						.ajax({
							type : "POST",
							url : link,
							data : "encOrderId=" + encodeURIComponent(encOrderId),
							async : true,

							success : function(data) {
								//alert(data.orderTotalAmount);
								$("#orderTotalAmountBillPrint").val(data.orderTotalAmount);
								$("#netPayableAmountBillPrint").val(data.orderTotalAmount); 
							}
						});
					};
					
					
					function getDiscountAmountBillPrint () {
						var orderTotalAmountBillPrint = $("#orderTotalAmountBillPrint").val();
						var discountAmountBillPrint = $("#discountAmountBillPrint").val();
						var netPayableAmountBillPrint = $("#netPayableAmountBillPrint").val(); 
						
						if (discountAmountBillPrint != '' && parseInt(discountAmountBillPrint) >=0) {
							
							if(parseInt(discountAmountBillPrint) > parseInt(orderTotalAmountBillPrint)) {
								var msg = '<label class="error">Discount can not be greather than total payable amount</label>';
								$("#modalMsgBillPrint").html(msg);
								$("#netPayableAmountBillPrint").val(orderTotalAmountBillPrint);
							} else {
								$("#modalMsgBillPrint").html('');
								var newOrderTotalAmountBillPrint = parseInt(orderTotalAmountBillPrint) - parseInt(discountAmountBillPrint);
								$("#netPayableAmountBillPrint").val(newOrderTotalAmountBillPrint);
							}
						} else {
							$("#modalMsgBillPrint").html('');
							$("#netPayableAmountBillPrint").val(orderTotalAmountBillPrint);
						}
				  }


	function getDiscountAmount () {
			var orderTotalAmount = $("#orderTotalAmount").val();
			var discountAmount = $("#discountAmount").val();
			var netPayableAmount = $("#netPayableAmount").val(); 
			var cashPayAmount = $("#cashPayAmount").val();
			var cardPayAmount = $("#cardPayAmount").val();
			var bkashPaymentAmount = $("#bkashPaymentAmount").val();
		
			if (discountAmount != '' && parseInt(discountAmount) >=0) {
				
				if(parseInt(discountAmount) > parseInt(orderTotalAmount)) {
					var msg = '<label class="error">Discount can not be greather than total payable amount</label>';
					$("#modalMsg").html(msg);
					$("#netPayableAmount").val(orderTotalAmount);
				} else {
					$("#modalMsg").html('');
					var newOrderTotalAmount = parseInt(orderTotalAmount) - parseInt(discountAmount);
					$("#netPayableAmount").val(newOrderTotalAmount);
				}
			} else {
				$("#modalMsg").html('');
				$("#netPayableAmount").val(orderTotalAmount);
			}
			
			getCashPayAmount();
			getCardPayAmount();
			getBkashPayAmount();
	  }
	  
	  
	function getChangeAmount () {
		var receivedAmount = $("#receivedAmount").val();
		var cashPayAmount = $("#cashPayAmount").val();
		var dueCustomerYn = $("#dueCustomerYn").val();
		
		if (dueCustomerYn == 'N') {
		if(receivedAmount !='') {
		if(cashPayAmount =='') {
			var msg = '<label class="error">Enter cash pay amount first</label>';
			$("#modalMsg").html(msg);
		} else {
		
			if (receivedAmount != '' && parseInt(receivedAmount) > 0) {
				if(parseInt(receivedAmount) < parseInt(cashPayAmount)) {
					var msg = '<label class="error">Received amount  can not be lower than cash pay amount</label>';
					$("#modalMsg").html(msg);
					$("#changeAmount").val('');
				} else {
					$("#modalMsg").html('');
				var changeAmount = parseInt(receivedAmount) - parseInt(cashPayAmount);
				$("#changeAmount").val(changeAmount);
				}
				
			} else {
				$("#modalMsg").html('');
				$("#changeAmount").val('');
			}
		  }
		} else {
			$("#modalMsg").html('');
			$("#changeAmount").val('');
		}
		}
	}


	function getCashPayAmount () {
		var netPayableAmount = $("#netPayableAmount").val(); 
		var cashPayAmount = $("#cashPayAmount").val();
		var cardPayAmount = $("#cardPayAmount").val();
		var bkashPaymentAmount = $("#bkashPaymentAmount").val();
		var dueCustomerYn = $("#dueCustomerYn").val();
		
		//alert(dueCustomerYn);
		 if (dueCustomerYn == 'N') {
	if (cashPayAmount != '' && parseInt(cashPayAmount) >=0) {
		if(cardPayAmount=='') {
			cardPayAmount = '0';
		}
		if(bkashPaymentAmount=='') {
			bkashPaymentAmount = '0';
		}
		cashPayAmount = parseInt(cashPayAmount)+ parseInt(cardPayAmount) + parseInt(bkashPaymentAmount);
		if (parseInt(cashPayAmount) != parseInt(netPayableAmount)) {
			var msg = '<label class="error">Cash, card and bkash pay amount must be equal with net pay amount</label>';
			$("#modalMsg").html(msg);
		} else {
			getChangeAmount();
		}
	  }
		 }
	
	}



	function getCardPayAmount () {
		var netPayableAmount = $("#netPayableAmount").val(); 
		var cashPayAmount = $("#cashPayAmount").val();
		var cardPayAmount = $("#cardPayAmount").val();
		var bkashPaymentAmount = $("#bkashPaymentAmount").val();
		var dueCustomerYn = $("#dueCustomerYn").val();
		
		
		if (dueCustomerYn == 'N') {
		if (cardPayAmount != '' && parseInt(cardPayAmount) >=0) {
			if(cashPayAmount == '') {
				var msg = '<label class="error">Enter cash pay amount first</label>';
				$("#modalMsg").html(msg);
			} else {
				if(bkashPaymentAmount == '') {
					cardPayAmount = parseInt(cashPayAmount)+ parseInt(cardPayAmount);
					if(parseInt(cardPayAmount) != parseInt(netPayableAmount)) {
						var msg = '<label class="error">Cash and card pay amount must be equal with net pay amount</label>';
						$("#modalMsg").html(msg);
					} else {
						$("#modalMsg").html('');
					}
				} else {
					cardPayAmount = parseInt(cashPayAmount)+ parseInt(cardPayAmount) + parseInt(bkashPaymentAmount);
					if(parseInt(cardPayAmount) != parseInt(netPayableAmount)) {
						var msg = '<label class="error">Cash,card and bkash pay amount must be equal with net pay amount</label>';
						$("#modalMsg").html(msg);
					} else {
						$("#modalMsg").html('');
					}
				}
			}
		}
		}
	}


	function getBkashPayAmount () {
		var netPayableAmount = $("#netPayableAmount").val(); 
		var cashPayAmount = $("#cashPayAmount").val();
		var cardPayAmount = $("#cardPayAmount").val();
		var bkashPaymentAmount = $("#bkashPaymentAmount").val();
		var dueCustomerYn = $("#dueCustomerYn").val();
		
		
		if (dueCustomerYn == 'N') {
		if (bkashPaymentAmount != '' && parseInt(bkashPaymentAmount) >=0) {
			if(cashPayAmount == '') {
				var msg = '<label class="error">Enter cash pay amount first</label>';
				$("#modalMsg").html(msg);
			} else {
				if(cardPayAmount == '') {
					bkashPaymentAmount = parseInt(cashPayAmount)+ parseInt(bkashPaymentAmount);
					if(parseInt(bkashPaymentAmount) != parseInt(netPayableAmount)) {
						var msg = '<label class="error">Cash and bkash pay amount must be equal with net pay amount</label>';
						$("#modalMsg").html(msg);
					} else {
						$("#modalMsg").html('');
					}
				} else {
					bkashPaymentAmount = parseInt(cashPayAmount)+ parseInt(cardPayAmount) + parseInt(bkashPaymentAmount);
					if(parseInt(bkashPaymentAmount) != parseInt(netPayableAmount)) {
						var msg = '<label class="error">Cash,card and bkash pay amount must be equal with net pay amount</label>';
						$("#modalMsg").html(msg);
					} else {
						$("#modalMsg").html('');
					}
				}
			}
		}
		}
	};

	function cancelOrder(encOrderId) {
		//alert(encOrderId);
		var conf = confirm("Do You Want To Cancel This Order ? ");
		
		if (conf) {
			var url = "/pos/pointOfSale/cancelOrder?encOrderId=" + encodeURIComponent(encOrderId);
			//alert(url);
			window.location = url;
		}
	};

	function orderProcessComplete(encOrderId) {
		//alert(encOrderId);
		var conf = confirm("Do You Want To Remove This Order From The List ? ");
		
		if (conf) {
			var url = "/pos/pointOfSale/orderProcessComplete?encOrderId=" + encodeURIComponent(encOrderId);
			//alert(url);
			window.location = url;
		}
	}

	function getPendingOrderInfoList(encOrderId) {

		if (!encOrderId == '') {

			var link = "/pos/pointOfSale/getPendingOrderInfoList";

			$.ajax({
				type : "POST",
				url : link,
				data : "encOrderId=" + encodeURIComponent(encOrderId),
				async : true,

				success : function(data) {
					 //alert(data.orderTotalAmount)
					$("#pendingOrderInfoList").html(data);
					//$("#orderTotalAmount").val(data.orderTotalAmount);
				},

				error : function(data) {
					alert('Error!!!')
				}
			});
		}
	};



	  function printKitchenQT(encOrderId) {
		var url = "/pos/reports/printKitchenQT?encOrderId=" + encodeURIComponent(encOrderId) + "&reportCode=" + "501";
		window.open(url, '_blank');

	};  


	function customerMoneyReceipt(encOrderId) {
		//alert(encOrderId);
		var url = "/pos/reports/customerMoneyReceipt?encOrderId=" + encodeURIComponent(encOrderId) + "&reportCode=" + "103";
		window.open(url, '_blank');

	};  
	
	function customerBillCopy(encOrderId) {
		//alert(encOrderId);
		var url = "/pos/reports/customerMoneyReceipt?encOrderId=" + encodeURIComponent(encOrderId) + "&reportCode=" + "104";
		window.open(url, '_blank');

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

		//alert(productId);
		/*alert(productId);
		alert(quantity);
		alert(unitId);
		alert(price);
		alert(employeeId); */

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

						data : "itemId=" + itemId + "&productId=" + productId
								+ "&quantity=" + quantity + "&unitId=" + unitId,

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

	};
	function editOldData(itmId) {
		alert(itmId);
		var productId = $("#productId_" + itmId).show();
		productId.select2();
		$("#dataProductId_" + itmId).hide();

		var quantity = $("#quantity_" + itmId).show();
		//taskTypeId.select2();
		$("#dataQuantity_" + itmId).hide();

		var unitId = $("#unitId_" + itmId).show();
		unitId.select2();
		$("#dataUnitId_" + itmId).hide();

		/* $("#taskDesc_" + effortId).show();
		$("#dataTaskDesc_" + effortId).hide();

		var startTime = $("#startTime_" + effortId).show();
		startTime.timepicker();
		$("#dataStartTime_" + effortId).hide();

		var endTime = $("#endTime_" + effortId).show();
		endTime.timepicker();
		$("#dataEndTime_" + effortId).hide(); 

		$("#totalHour_" + effortId).show();
		$("#dataTotalHour_" + effortId).hide();

		$("#noOfDefect_" + effortId).show();
		$("#dataNoOfDefect_" + effortId).hide();

		$("#defectDesc_" + effortId).show();
		$("#dataDefectDesc_" + effortId).hide();*/

		$("#update_" + itmId).show();
		$("#clear_" + itmId).show();
		$("#edit_" + itmId).hide();
	};

	function cancelEditOldData(effortId) {

		$("#projectId_" + effortId).hide();
		$("#s2id_projectId_" + effortId).hide();
		$("#dataProjectId_" + effortId).show();

		$("#taskTypeId_" + effortId).hide();
		$("#s2id_taskTypeId_" + effortId).hide();
		$("#dataTaskTypeId_" + effortId).show();

		$("#taskDesc_" + effortId).hide();
		$("#dataTaskDesc_" + effortId).show();

		$("#startTime_" + effortId).hide();
		$("#dataStartTime_" + effortId).show();

		$("#endTime_" + effortId).hide();
		$("#dataEndTime_" + effortId).show();

		$("#totalHour_" + effortId).hide();
		$("#dataTotalHour_" + effortId).show();

		$("#noOfDefect_" + effortId).hide();
		$("#dataNoOfDefect_" + effortId).show();

		$("#defectDesc_" + effortId).hide();
		$("#dataDefectDesc_" + effortId).show();

		$("#update_" + effortId).hide();
		$("#clear_" + effortId).hide();
		$("#edit_" + effortId).show();
	};
	

	function saveUpdateData(effortId) {

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
	}
	
		
	
	window.onload = function() {
		window.jQuery();
		
 		$("#paymentSubmit").click(function(e) {
			e.preventDefault();  // stop form from submitting right away
			var error = false;
			
			var cashPayAmount = $("#cashPayAmount").val();
			var dueCustomerYn = $("#dueCustomerYn").val();
			var dueCustomerId = $("#dueCustomerId").val();
			var dueAmount = $("#dueAmount").val();
			var paidAmount = $("#paidAmount").val();
			
			

			if(dueCustomerYn =='Y') {
				if (dueCustomerId == '') {
					var msg = '<label class="error">Select Customer !</label>';
					$("#modalMsg").html(msg);
					$('#modalMsg').find('.error').each(function() {
						 error = true;
					    });
					
					 if (!error) { 
						$('#formExtra').submit();
					} else {
						e.preventDefault();  
					}
				} else if (dueAmount == '') {
					var msg = '<label class="error">Due Amount Cannot Be Empty !</label>';
					$("#modalMsg").html(msg);
					$('#modalMsg').find('.error').each(function() {
						 error = true;
					    });
					
					 if (!error) { 
						$('#formExtra').submit();
					} else {
						e.preventDefault();  
					}
				} else if (paidAmount == '') {
					var msg = '<label class="error">Enter Paid Amount !</label>';
					$("#modalMsg").html(msg);
					$('#modalMsg').find('.error').each(function() {
						 error = true;
					    });
					
					 if (!error) { 
						$('#formExtra').submit();
					} else {
						e.preventDefault();  
					}
				}
				//e.preventDefault();
				
			} else if (cashPayAmount =='') {
				var msg = '<label class="error">Cash amount must be required</label>';
				$("#modalMsg").html(msg);
				
				 $('#modalMsg').find('.error').each(function() {
					 error = true;
				    });
				
				 if (!error) { 
					$('#formExtra').submit();
				} else {
					e.preventDefault();  
				}
			} else {
				$('#modalMsg').find('.error').each(function() {
					 error = true;
				    });
				
				 if (!error) { 
					$('#formExtra').submit();
				} else {
					e.preventDefault();  
				}
			
			}
			}); 

/* 		$("#paymentSubmit").click(function(e) {
			e.preventDefault();  // stop form from submitting right away
			var error = false;
			
			var cashPayAmount = $("#cashPayAmount").val();
			//var dueCustomerYn = $("#dueCustomerYn").val();
			//var dueCustomerId = $("#dueCustomerId").val();
			//var dueAmount = $("#dueAmount").val();
			//var paidAmount = $("#paidAmount").val();

			if(cashPayAmount =='') {
				var msg = '<label class="error">Cash amount must be required</label>';
				$("#modalMsg").html(msg);
			}
			
			 $('#modalMsg').find('.error').each(function() {
				 error = true;
			    });
			
			if (!error) { 
				$('#formExtra').submit();
			} else {
				e.preventDefault();  
			}
			
			}); */
		
		/* $("#billPrintSubmit").click(function(e) {
			e.preventDefault();  // stop form from submitting right away
			var error = false;
			
			 $('#modalMsgBillPrint').find('.error').each(function() {
				 error = true;
			    });
			 
			if (!error) { 
				$('#formBillPrint').submit();
			} else {
				e.preventDefault();  
			}
			}); */
		
		
		
		
	};
	
	$("#billPrintSubmit").click(function(e) {
		e.preventDefault();  // stop form from submitting right away
		var error = false;
		
		 $('#modalMsgBillPrint').find('.error').each(function() {
			 error = true;
		    });
		 
		if (!error) { 
			$('#formBillPrint').submit();
		} else {
			e.preventDefault();  
		}
		});


	</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- end: page -->
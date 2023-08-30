package com.pos.pointOfSale.model;

import java.util.List;


public class PointOfSale {

	private String itemId;
	private String encItemId;
	private String itemCode;
	private String itemName;
	private String itemPrice;
	private String updateBy;
	private String updateDate;
	private String productId;
	private String quantity;
	private String unitId;
	private String productName;
	private String unitName;
	private String encProductId;
	private String orderPrice;
	private String orderId;
	private String encOrderId;
	private String totalPrice;
	private String discountAmount;
	private String message;
	private String mCode;
	private String referenceById;
	private String referenceByName;
	private String discountId;
	private String encDiscountId;
	private String fromDate;
	private String toDate;
	private List<OrderModel> orderModelList;
	
	private String tableNo;
	private String subTotal;
	
	private String itemOrderId;
	private String encItemOrderId;
	private String orderTotalAmount;
	
	private String receivedAmount;
	private String changeAmount;
	private String cardPayAmount;
	private String cashPayAmount;
	private String netPayableAmount;
	
	private String completedYn;
	private String finalizedYn;
	
	private String employeeId;
	private String employeeName;
	private String orderNo;
	private String orderDate;
	
	private String encConsumeId;
	private String consumeDate;
	private String remarks;
	private String editable;
	private String price;
	private String orderNote;
	private String bkashPaymentAmount;
	private String bkashTranNo;
	private String discountReferenceBy;
	
	private String servedBy;
	private String ownerFoodYn;
	
	private String billPrintYn;
	
	private String countDuplicateTable;
	
	private String encDueCustomerId;
	private String dueCustomerId;
	private String phoneNo;
	private String fatherName;
	private String motherName;
	private String email;
	private String customerAddress;
	
	
	private String dueCustomerYn;
	private String customerName;
	private String dueAmount;
	private String paidAmount;
	private String dueCustomerPaymentId;
	private String payDate;
	
	
	
	
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getDueCustomerPaymentId() {
		return dueCustomerPaymentId;
	}
	public void setDueCustomerPaymentId(String dueCustomerPaymentId) {
		this.dueCustomerPaymentId = dueCustomerPaymentId;
	}
	public String getDueAmount() {
		return dueAmount;
	}
	public void setDueAmount(String dueAmount) {
		this.dueAmount = dueAmount;
	}
	public String getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getDueCustomerYn() {
		return dueCustomerYn;
	}
	public void setDueCustomerYn(String dueCustomerYn) {
		this.dueCustomerYn = dueCustomerYn;
	}
	public String getEncDueCustomerId() {
		return encDueCustomerId;
	}
	public void setEncDueCustomerId(String encDueCustomerId) {
		this.encDueCustomerId = encDueCustomerId;
	}
	public String getDueCustomerId() {
		return dueCustomerId;
	}
	public void setDueCustomerId(String dueCustomerId) {
		this.dueCustomerId = dueCustomerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCountDuplicateTable() {
		return countDuplicateTable;
	}
	public void setCountDuplicateTable(String countDuplicateTable) {
		this.countDuplicateTable = countDuplicateTable;
	}
	public String getOwnerFoodYn() {
		return ownerFoodYn;
	}
	public void setOwnerFoodYn(String ownerFoodYn) {
		this.ownerFoodYn = ownerFoodYn;
	}
	public String getServedBy() {
		return servedBy;
	}
	public void setServedBy(String servedBy) {
		this.servedBy = servedBy;
	}
	public String getBkashPaymentAmount() {
		return bkashPaymentAmount;
	}
	public void setBkashPaymentAmount(String bkashPaymentAmount) {
		this.bkashPaymentAmount = bkashPaymentAmount;
	}

	public String getBkashTranNo() {
		return bkashTranNo;
	}
	public void setBkashTranNo(String bkashTranNo) {
		this.bkashTranNo = bkashTranNo;
	}
	public String getDiscountReferenceBy() {
		return discountReferenceBy;
	}
	public void setDiscountReferenceBy(String discountReferenceBy) {
		this.discountReferenceBy = discountReferenceBy;
	}
	public String getOrderNote() {
		return orderNote;
	}
	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getEncConsumeId() {
		return encConsumeId;
	}
	public void setEncConsumeId(String encConsumeId) {
		this.encConsumeId = encConsumeId;
	}
	public String getConsumeDate() {
		return consumeDate;
	}
	public void setConsumeDate(String consumeDate) {
		this.consumeDate = consumeDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getEditable() {
		return editable;
	}
	public void setEditable(String editable) {
		this.editable = editable;
	}
	public String getCompletedYn() {
		return completedYn;
	}
	public void setCompletedYn(String completedYn) {
		this.completedYn = completedYn;
	}
	public String getFinalizedYn() {
		return finalizedYn;
	}
	public void setFinalizedYn(String finalizedYn) {
		this.finalizedYn = finalizedYn;
	}
	public String getNetPayableAmount() {
		return netPayableAmount;
	}
	public void setNetPayableAmount(String netPayableAmount) {
		this.netPayableAmount = netPayableAmount;
	}
	public String getReceivedAmount() {
		return receivedAmount;
	}
	public void setReceivedAmount(String receivedAmount) {
		this.receivedAmount = receivedAmount;
	}
	public String getChangeAmount() {
		return changeAmount;
	}
	public void setChangeAmount(String changeAmount) {
		this.changeAmount = changeAmount;
	}
	public String getCardPayAmount() {
		return cardPayAmount;
	}
	public void setCardPayAmount(String cardPayAmount) {
		this.cardPayAmount = cardPayAmount;
	}
	public String getCashPayAmount() {
		return cashPayAmount;
	}
	public void setCashPayAmount(String cashPayAmount) {
		this.cashPayAmount = cashPayAmount;
	}
	public String getOrderTotalAmount() {
		return orderTotalAmount;
	}
	public void setOrderTotalAmount(String orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}
	public String getItemOrderId() {
		return itemOrderId;
	}
	public void setItemOrderId(String itemOrderId) {
		this.itemOrderId = itemOrderId;
	}
	public String getEncItemOrderId() {
		return encItemOrderId;
	}
	public void setEncItemOrderId(String encItemOrderId) {
		this.encItemOrderId = encItemOrderId;
	}
	public String getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	public String getReferenceByName() {
		return referenceByName;
	}
	public void setReferenceByName(String referenceByName) {
		this.referenceByName = referenceByName;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getDiscountId() {
		return discountId;
	}
	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}
	public String getEncDiscountId() {
		return encDiscountId;
	}
	public void setEncDiscountId(String encDiscountId) {
		this.encDiscountId = encDiscountId;
	}

	public String getReferenceById() {
		return referenceById;
	}
	public void setReferenceById(String referenceById) {
		this.referenceById = referenceById;
	}
	public String getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getEncOrderId() {
		return encOrderId;
	}
	public void setEncOrderId(String encOrderId) {
		this.encOrderId = encOrderId;
	}
	public List<OrderModel> getOrderModelList() {
		return orderModelList;
	}
	public void setOrderModelList(List<OrderModel> orderModelList) {
		this.orderModelList = orderModelList;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getEncProductId() {
		return encProductId;
	}
	public void setEncProductId(String encProductId) {
		this.encProductId = encProductId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getmCode() {
		return mCode;
	}
	public void setmCode(String mCode) {
		this.mCode = mCode;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getEncItemId() {
		return encItemId;
	}
	public void setEncItemId(String encItemId) {
		this.encItemId = encItemId;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getTableNo() {
		return tableNo;
	}
	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getBillPrintYn() {
		return billPrintYn;
	}
	public void setBillPrintYn(String billPrintYn) {
		this.billPrintYn = billPrintYn;
	}
	
	
}

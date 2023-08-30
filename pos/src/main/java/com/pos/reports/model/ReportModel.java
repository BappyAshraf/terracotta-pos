package com.pos.reports.model;

public class ReportModel {

	private String reportCode;
	private String viewType;
	private String jesperName;
	
	private String orderId;
	private String encOrderId;
	private String orderNo;
	private String itemName;
	private String quantity;
	private int itemPrice;
	private int subTotalCount;
	private Double reportItemPrice;
	private Double reportSubTotalCount;
	private String updateBy;
	private String updateDate;
	
	private String inventoryId;
	private String inventoryTypeId;
	private String inventoryTypeName;
	private String productId;
	private String productName;
	private String unitName;
	private String employeeId;
	private String employeeName;
	private String fromDate;
	private String toDate;
	private String itemId;
	private String message;
	private String mCode;
	private String inventoryCost;
	private String discountCost;
	private String wastageCost;
	private String totalCost;
	private String totalOrderPrice;
	
	private String costAnalysisReportType;
	private String year;
	private String month;
	private String employeeSalary;
	private String netIncome;
	private String itemOrderId;
	private String subTotal;
	private String printedYn;
	private String updatedYn;
	private String tableNo;
	private String orderNote;
	
	private String receivedAmount;
	private String changeAmount;
	private String cardPayAmount;
	private String cashPayAmount;
	private String netPayableAmount;
	
	private String completedYn;
	private String finalizedYn;
	private String bkashPaymentAmount;
	private String bkashTranNo;
	private String discountReferenceBy;
	private String orderDate;
	private String waiterId;
	private String waiterName;
	private String orderTotalAmount;
	private String ownerFoodConsumeCost;
	
	
	private Double discountSum;
	private Double cardPaySum;
	private Double cashPaySum;
	private Double bkashPaySum;
	
	private String shopName;
	private String shopAddress;
	private String phoneNo;
	private String email;
	
	
	private String stockDate;
	private String stockIn;
	private String stockOut;
	private String itemOrderProduct;
	private String wastage;
	
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getReportItemPrice() {
		return reportItemPrice;
	}
	public void setReportItemPrice(Double reportItemPrice) {
		this.reportItemPrice = reportItemPrice;
	}
	public Double getReportSubTotalCount() {
		return reportSubTotalCount;
	}
	public void setReportSubTotalCount(Double reportSubTotalCount) {
		this.reportSubTotalCount = reportSubTotalCount;
	}
	public Double getDiscountSum() {
		return discountSum;
	}
	public void setDiscountSum(Double discountSum) {
		this.discountSum = discountSum;
	}
	public Double getCardPaySum() {
		return cardPaySum;
	}
	public void setCardPaySum(Double cardPaySum) {
		this.cardPaySum = cardPaySum;
	}
	public Double getCashPaySum() {
		return cashPaySum;
	}
	public void setCashPaySum(Double cashPaySum) {
		this.cashPaySum = cashPaySum;
	}
	public Double getBkashPaySum() {
		return bkashPaySum;
	}
	public void setBkashPaySum(Double bkashPaySum) {
		this.bkashPaySum = bkashPaySum;
	}
	public int getSubTotalCount() {
		return subTotalCount;
	}
	public void setSubTotalCount(int subTotalCount) {
		this.subTotalCount = subTotalCount;
	}
	public String getOwnerFoodConsumeCost() {
		return ownerFoodConsumeCost;
	}
	public void setOwnerFoodConsumeCost(String ownerFoodConsumeCost) {
		this.ownerFoodConsumeCost = ownerFoodConsumeCost;
	}
	public String getOrderTotalAmount() {
		return orderTotalAmount;
	}
	public void setOrderTotalAmount(String orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getWaiterId() {
		return waiterId;
	}
	public void setWaiterId(String waiterId) {
		this.waiterId = waiterId;
	}
	public String getWaiterName() {
		return waiterName;
	}
	public void setWaiterName(String waiterName) {
		this.waiterName = waiterName;
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
	public String getNetPayableAmount() {
		return netPayableAmount;
	}
	public void setNetPayableAmount(String netPayableAmount) {
		this.netPayableAmount = netPayableAmount;
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
	public String getTableNo() {
		return tableNo;
	}
	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}
	public String getPrintedYn() {
		return printedYn;
	}
	public void setPrintedYn(String printedYn) {
		this.printedYn = printedYn;
	}
	public String getUpdatedYn() {
		return updatedYn;
	}
	public void setUpdatedYn(String updatedYn) {
		this.updatedYn = updatedYn;
	}
	public String getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	public String getItemOrderId() {
		return itemOrderId;
	}
	public void setItemOrderId(String itemOrderId) {
		this.itemOrderId = itemOrderId;
	}
	public String getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(String netIncome) {
		this.netIncome = netIncome;
	}
	public String getTotalOrderPrice() {
		return totalOrderPrice;
	}
	public void setTotalOrderPrice(String totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
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
	public String getInventoryCost() {
		return inventoryCost;
	}
	public void setInventoryCost(String inventoryCost) {
		this.inventoryCost = inventoryCost;
	}
	public String getDiscountCost() {
		return discountCost;
	}
	public void setDiscountCost(String discountCost) {
		this.discountCost = discountCost;
	}
	public String getWastageCost() {
		return wastageCost;
	}
	public void setWastageCost(String wastageCost) {
		this.wastageCost = wastageCost;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getInventoryTypeId() {
		return inventoryTypeId;
	}
	public void setInventoryTypeId(String inventoryTypeId) {
		this.inventoryTypeId = inventoryTypeId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	public String getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getInventoryTypeName() {
		return inventoryTypeName;
	}
	public void setInventoryTypeName(String inventoryTypeName) {
		this.inventoryTypeName = inventoryTypeName;
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
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEncOrderId() {
		return encOrderId;
	}
	public void setEncOrderId(String encOrderId) {
		this.encOrderId = encOrderId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
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
	public String getJesperName() {
		return jesperName;
	}
	public void setJesperName(String jesperName) {
		this.jesperName = jesperName;
	}
	public String getReportCode() {
		return reportCode;
	}
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public String getCostAnalysisReportType() {
		return costAnalysisReportType;
	}
	public void setCostAnalysisReportType(String costAnalysisReportType) {
		this.costAnalysisReportType = costAnalysisReportType;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getEmployeeSalary() {
		return employeeSalary;
	}
	public void setEmployeeSalary(String employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	public String getStockDate() {
		return stockDate;
	}
	public void setStockDate(String stockDate) {
		this.stockDate = stockDate;
	}
	public String getStockIn() {
		return stockIn;
	}
	public void setStockIn(String stockIn) {
		this.stockIn = stockIn;
	}
	public String getStockOut() {
		return stockOut;
	}
	public void setStockOut(String stockOut) {
		this.stockOut = stockOut;
	}
	public String getItemOrderProduct() {
		return itemOrderProduct;
	}
	public void setItemOrderProduct(String itemOrderProduct) {
		this.itemOrderProduct = itemOrderProduct;
	}
	public String getWastage() {
		return wastage;
	}
	public void setWastage(String wastage) {
		this.wastage = wastage;
	}
	
	
	
}

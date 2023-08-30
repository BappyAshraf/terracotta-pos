package com.pos.accounts.model;

public class EmployeeMonthlyConsumption {
	
	private String encConsumeId;
	private String encEmployeeId;
	private String employeeName;
	private String designation;
	private String allowanceDeductCategoryId;
	private String allowanceDeductCategoryName;
	private String consumeDate;
	private String amount;
	private String purpose;
	
	private String updateBy;
	
	private String message;
	private String messageCode;
	private String consumeSysdateYn;
	
	
	
	
	public String getConsumeSysdateYn() {
		return consumeSysdateYn;
	}
	public void setConsumeSysdateYn(String consumeSysdateYn) {
		this.consumeSysdateYn = consumeSysdateYn;
	}
	public String getEncEmployeeId() {
		return encEmployeeId;
	}
	public void setEncEmployeeId(String encEmployeeId) {
		this.encEmployeeId = encEmployeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getAllowanceDeductCategoryId() {
		return allowanceDeductCategoryId;
	}
	public void setAllowanceDeductCategoryId(String allowanceDeductCategoryId) {
		this.allowanceDeductCategoryId = allowanceDeductCategoryId;
	}
	public String getAllowanceDeductCategoryName() {
		return allowanceDeductCategoryName;
	}
	public void setAllowanceDeductCategoryName(String allowanceDeductCategoryName) {
		this.allowanceDeductCategoryName = allowanceDeductCategoryName;
	}
	public String getConsumeDate() {
		return consumeDate;
	}
	public void setConsumeDate(String consumeDate) {
		this.consumeDate = consumeDate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getEncConsumeId() {
		return encConsumeId;
	}
	public void setEncConsumeId(String encConsumeId) {
		this.encConsumeId = encConsumeId;
	}
	

}

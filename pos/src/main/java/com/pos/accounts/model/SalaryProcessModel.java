package com.pos.accounts.model;

import java.util.List;


public class SalaryProcessModel {
	
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
	
	private String month;
	private String year;
	
	private String encCategoryId;
	
	private String encPayrollId;
	private String adCategoryTypeId;
	
	private String finalizedYN;
	
	private List<AdCategoryList> adCategoryIdList;
	
	
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
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
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
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getEncCategoryId() {
		return encCategoryId;
	}
	public void setEncCategoryId(String encCategoryId) {
		this.encCategoryId = encCategoryId;
	}
	public List<AdCategoryList> getAdCategoryIdList() {
		return adCategoryIdList;
	}
	public void setAdCategoryIdList(List<AdCategoryList> adCategoryIdList) {
		this.adCategoryIdList = adCategoryIdList;
	}
	public String getEncPayrollId() {
		return encPayrollId;
	}
	public void setEncPayrollId(String encPayrollId) {
		this.encPayrollId = encPayrollId;
	}
	public String getAdCategoryTypeId() {
		return adCategoryTypeId;
	}
	public void setAdCategoryTypeId(String adCategoryTypeId) {
		this.adCategoryTypeId = adCategoryTypeId;
	}
	public String getFinalizedYN() {
		return finalizedYN;
	}
	public void setFinalizedYN(String finalizedYN) {
		this.finalizedYN = finalizedYN;
	}
	
	
	

}

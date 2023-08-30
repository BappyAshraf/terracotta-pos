package com.pos.admin.model;

import java.util.List;

public class UserInfoForm {
	private String encUserId;
	private String userId;
	private String encEmployeeId;
	private String employeeId;
	
	private String employeeName;
	
	private String designationId;
	private String designationName;
	private String address;
	
	private String roleId;
	private String roleName;
	
	private String userName;
	private String password;
	private String roleYN;
	private String enabledYN;
	private String enabledYNName;
	private String updateBy;
	private String message;
	private String messageCode;
	
	private String bankId;
	private String bankBranchId;
	private String bankAccountNo;
	
	private String employeeOfficeId;
	private String grossPartial;
	
	private List<UserRole> userRoles;
	
	private String encDepartmentId;
	private String departmentName;
	
	private String attendanceIncentiveYN;
	
	private String exemptedPayDeduction;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEncUserId() {
		return encUserId;
	}

	public void setEncUserId(String encUserId) {
		this.encUserId = encUserId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEncEmployeeId() {
		return encEmployeeId;
	}

	public void setEncEmployeeId(String encEmployeeId) {
		this.encEmployeeId = encEmployeeId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDesignationId() {
		return designationId;
	}

	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleYN() {
		return roleYN;
	}

	public void setRoleYN(String roleYN) {
		this.roleYN = roleYN;
	}

	public String getEnabledYN() {
		return enabledYN;
	}

	public void setEnabledYN(String enabledYN) {
		this.enabledYN = enabledYN;
	}

	public String getEnabledYNName() {
		return enabledYNName;
	}

	public void setEnabledYNName(String enabledYNName) {
		this.enabledYNName = enabledYNName;
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

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankBranchId() {
		return bankBranchId;
	}

	public void setBankBranchId(String bankBranchId) {
		this.bankBranchId = bankBranchId;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getEmployeeOfficeId() {
		return employeeOfficeId;
	}

	public void setEmployeeOfficeId(String employeeOfficeId) {
		this.employeeOfficeId = employeeOfficeId;
	}

	public String getGrossPartial() {
		return grossPartial;
	}

	public void setGrossPartial(String grossPartial) {
		this.grossPartial = grossPartial;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getEncDepartmentId() {
		return encDepartmentId;
	}

	public void setEncDepartmentId(String encDepartmentId) {
		this.encDepartmentId = encDepartmentId;
	}

	public String getAttendanceIncentiveYN() {
		return attendanceIncentiveYN;
	}

	public void setAttendanceIncentiveYN(String attendanceIncentiveYN) {
		this.attendanceIncentiveYN = attendanceIncentiveYN;
	}

	public String getExemptedPayDeduction() {
		return exemptedPayDeduction;
	}

	public void setExemptedPayDeduction(String exemptedPayDeduction) {
		this.exemptedPayDeduction = exemptedPayDeduction;
	}

}

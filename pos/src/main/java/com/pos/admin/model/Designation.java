package com.pos.admin.model;

public class Designation {
	
	private String designationId;
	private String designationTypeId;
	private String designationTypeName;
	private String encDesignationId;
    private String designationCode;
    private String designationName;
    private String systemStatus;
    private String updateBy;
    private String updateDate;
    
    private String message;
    private String messageCode;
    
    
	public String getDesignationTypeName() {
		return designationTypeName;
	}
	public void setDesignationTypeName(String designationTypeName) {
		this.designationTypeName = designationTypeName;
	}
	public String getDesignationTypeId() {
		return designationTypeId;
	}
	public void setDesignationTypeId(String designationTypeId) {
		this.designationTypeId = designationTypeId;
	}
	public String getDesignationId() {
		return designationId;
	}
	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}
	public String getEncDesignationId() {
		return encDesignationId;
	}
	public void setEncDesignationId(String encDesignationId) {
		this.encDesignationId = encDesignationId;
	}
	public String getDesignationCode() {
		return designationCode;
	}
	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getSystemStatus() {
		return systemStatus;
	}
	public void setSystemStatus(String systemStatus) {
		this.systemStatus = systemStatus;
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
    

}

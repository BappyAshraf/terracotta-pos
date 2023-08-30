package com.pos.admin.model;

public class RoleMenuDetails {
	
	private String allowYN;
	private String encMenuId;
	private String insertYN;
	private String updateYN;
	private String deleteYN;
	
	
	
	public RoleMenuDetails(){
		
	}
	
	
	public RoleMenuDetails(String allowYN, String encMenuId, String insertYN, String updateYN, String deleteYN){
		this.allowYN = allowYN;
		this.encMenuId = encMenuId;
		this.insertYN = insertYN;
		this.updateYN = updateYN;
		this.deleteYN = deleteYN;
	}
	
	
	public String getAllowYN() {
		return allowYN;
	}
	public void setAllowYN(String allowYN) {
		this.allowYN = allowYN;
	}
	
	public String getEncMenuId() {
		return encMenuId;
	}

	public void setEncMenuId(String encMenuId) {
		this.encMenuId = encMenuId;
	}

	public String getInsertYN() {
		return insertYN;
	}
	public void setInsertYN(String insertYN) {
		this.insertYN = insertYN;
	}
	public String getUpdateYN() {
		return updateYN;
	}
	public void setUpdateYN(String updateYN) {
		this.updateYN = updateYN;
	}
	public String getDeleteYN() {
		return deleteYN;
	}
	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}

}

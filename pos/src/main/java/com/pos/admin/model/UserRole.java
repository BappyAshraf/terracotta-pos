package com.pos.admin.model;

public class UserRole {
	
	private String roleId;
	private String roleYN;
	
	
	public UserRole(String roleId,String roleYN){
		this.roleId = roleId;
		this.roleYN = roleYN;
	}
	
	public UserRole(){
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleYN() {
		return roleYN;
	}

	public void setRoleYN(String roleYN) {
		this.roleYN = roleYN;
	}

	
	
	
	

}

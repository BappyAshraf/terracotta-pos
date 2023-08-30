package com.pos.admin.model;

public class RoleInfo {
	
	private String encRoleId;
	private String parentMenuId;
	private String menuId;
	
	
	public RoleInfo(String encRoleId, String menuId){
		this.encRoleId = encRoleId;
		this.menuId = menuId;
	}
	
	
	public RoleInfo(){
		
	}
	
	
	public String getEncRoleId() {
		return encRoleId;
	}
	public void setEncRoleId(String encRoleId) {
		this.encRoleId = encRoleId;
	}
	public String getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	
	
	

}

package com.pos.admin.model;

import java.util.ArrayList;
import java.util.List;

public class RoleInfoForm {

	
	private String encRoleId;
	private String roleName;
	private String mainMenuId;
	private String mainMenuName;
	private String menuId;
	private String menuName;
	private String enabledYN;
	private String enabledYNName;
	private String message;
	private String messageCode;
	
	
	List<RoleInfo> roles = new ArrayList<RoleInfo>();
	
	
	
	public String getMainMenuId() {
		return mainMenuId;
	}
	public void setMainMenuId(String mainMenuId) {
		this.mainMenuId = mainMenuId;
	}
	public String getMainMenuName() {
		return mainMenuName;
	}
	public void setMainMenuName(String mainMenuName) {
		this.mainMenuName = mainMenuName;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
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
	public String getEncRoleId() {
		return encRoleId;
	}
	public void setEncRoleId(String encRoleId) {
		this.encRoleId = encRoleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	public List<RoleInfo> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleInfo> roles) {
		this.roles = roles;
	}
	
	
	
	
}

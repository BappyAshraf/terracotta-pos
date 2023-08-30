package com.pos.admin.model;

import java.util.List;


public class RoleMenuMapping {
	private String enRoleId;
	private String roleId;
	private String roleName;
	private String menuId;
	private String menuName;
	private String mainMenuName;
	private String userTypeId;
	private String encMenuId;
	private String mainMenuId;
	private String insert;
	private String update;
	private String delete;
	private String updateDate;
	private String allowYN;
	private String insertYN;
	private String updateYN;
	private String deleteYN;
	
	private List<RoleMenuDetails> roleMenuDetails;
	
	private String updateBy;
	private String message;
	private String messageCode;
	
	
	
	
	public String getAllowYN() {
		return allowYN;
	}
	public void setAllowYN(String allowYN) {
		this.allowYN = allowYN;
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
	public List<RoleMenuDetails> getRoleMenuDetails() {
		return roleMenuDetails;
	}
	public void setRoleMenuDetails(List<RoleMenuDetails> roleMenuDetails) {
		this.roleMenuDetails = roleMenuDetails;
	}

	public String getMainMenuName() {
		return mainMenuName;
	}

	public void setMainMenuName(String mainMenuName) {
		this.mainMenuName = mainMenuName;
	}

	public String getInsertYN() {
		return insertYN;
	}

	

	public String getMainMenuId() {
		return mainMenuId;
	}
	public void setMainMenuId(String mainMenuId) {
		this.mainMenuId = mainMenuId;
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

	

	public String getEncMenuId() {
		return encMenuId;
	}
	public void setEncMenuId(String encMenuId) {
		this.encMenuId = encMenuId;
	}
	public String getEnRoleId() {
		return enRoleId;
	}

	public void setEnRoleId(String enRoleId) {
		this.enRoleId = enRoleId;
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

	public String getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}
	
	
	
	public String getInsert() {
		return insert;
	}
	public void setInsert(String insert) {
		this.insert = insert;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

}

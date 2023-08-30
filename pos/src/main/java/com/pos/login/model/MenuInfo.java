package com.pos.login.model;

public class MenuInfo {

	private String MenuId = "";
	private String insert= "";
    private String update= "";
    private String delete= "";
	
	private String prevAction= "";
	private String nextAction= "";
	
	private String ParentMenuId= "";
    private String MenuCommand= "";
    private String MenuActionName= "";    
	private String MenuName= "";
    private String InsertYN= "";
    private String UpdateYN= "";
    private String DeleteYN= "";
    private String UpdateBy= "";
    private String UpdateDate= "";
	public String getMenuId() {
		return MenuId;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
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
	public String getPrevAction() {
		return prevAction;
	}
	public void setPrevAction(String prevAction) {
		this.prevAction = prevAction;
	}
	public String getNextAction() {
		return nextAction;
	}
	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}
	public String getParentMenuId() {
		return ParentMenuId;
	}
	public void setParentMenuId(String parentMenuId) {
		ParentMenuId = parentMenuId;
	}
	public String getMenuCommand() {
		return MenuCommand;
	}
	public void setMenuCommand(String menuCommand) {
		MenuCommand = menuCommand;
	}
	public String getMenuActionName() {
		return MenuActionName;
	}
	public void setMenuActionName(String menuActionName) {
		MenuActionName = menuActionName;
	}
	public String getMenuName() {
		return MenuName;
	}
	public void setMenuName(String menuName) {
		MenuName = menuName;
	}
	public String getInsertYN() {
		return InsertYN;
	}
	public void setInsertYN(String insertYN) {
		InsertYN = insertYN;
	}
	public String getUpdateYN() {
		return UpdateYN;
	}
	public void setUpdateYN(String updateYN) {
		UpdateYN = updateYN;
	}
	public String getDeleteYN() {
		return DeleteYN;
	}
	public void setDeleteYN(String deleteYN) {
		DeleteYN = deleteYN;
	}
	public String getUpdateBy() {
		return UpdateBy;
	}
	public void setUpdateBy(String updateBy) {
		UpdateBy = updateBy;
	}
	public String getUpdateDate() {
		return UpdateDate;
	}
	public void setUpdateDate(String updateDate) {
		UpdateDate = updateDate;
	}
    
    
}

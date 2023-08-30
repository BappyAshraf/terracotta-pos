package com.pos.common;

import com.pos.login.model.MenuInfo;

public class LoginRemoveNull {
	
	public MenuInfo removeNullMenuInfo(MenuInfo menuInfo){ 
	
		if (menuInfo.getMenuId() == null || menuInfo.getMenuId() == "null") {
			menuInfo.setMenuId("");
		}
		if (menuInfo.getParentMenuId() == null || menuInfo.getParentMenuId() == "null") {
		menuInfo.setParentMenuId("");
		}
		if (menuInfo.getMenuCommand() == null || menuInfo.getMenuCommand() == "null") {
		menuInfo.setMenuCommand("");
		}
		if (menuInfo.getMenuActionName() == null || menuInfo.getMenuActionName() == "null") {
		menuInfo.setMenuActionName("");
		}
		if (menuInfo.getMenuName() == null || menuInfo.getMenuName() == "null") {
		menuInfo.setMenuName("");
		}
		if (menuInfo.getInsertYN() == null || menuInfo.getInsertYN() == "null") {
		menuInfo.setInsertYN("");
		}
		if (menuInfo.getUpdateYN() == null || menuInfo.getUpdateYN() == "null") {
		menuInfo.setUpdateYN("");
		}
		if (menuInfo.getDeleteYN() == null || menuInfo.getDeleteYN() == "null") {
		menuInfo.setDeleteYN("");
		}
		
		
		return menuInfo;
    
	}

}

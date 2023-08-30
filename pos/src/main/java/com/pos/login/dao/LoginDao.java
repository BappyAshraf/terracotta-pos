package com.pos.login.dao;

import java.util.List;

import com.pos.login.model.LoginInfo;
import com.pos.login.model.MenuInfo;



public interface LoginDao {

	public LoginInfo ValidateUser(String sUserName, String sUserPass,String sTerminalIp);
	
	public List<MenuInfo> GetPermittedMenuInfo(String UserId, String ParentMenuID);

	public MenuInfo checkUserAuthorization(String UserId, String MenuID);
}

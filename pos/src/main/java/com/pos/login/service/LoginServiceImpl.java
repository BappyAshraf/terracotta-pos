package com.pos.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pos.login.dao.LoginDao;
import com.pos.login.model.LoginInfo;
import com.pos.login.model.MenuInfo;


@Service("loginService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao loginDao;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	
	public LoginInfo ValidateUser(String sUserName, String sUserPass, String sTerminalIp) {
		return loginDao.ValidateUser(sUserName, sUserPass, sTerminalIp);
	}

	public List<MenuInfo> GetPermittedMenuInfo(String UserId, String ParentMenuID){
		return loginDao.GetPermittedMenuInfo(UserId, ParentMenuID);
	}

	public MenuInfo checkUserAuthorization(String UserId, String MenuID) {
		return loginDao.checkUserAuthorization(UserId, MenuID);
	}

	
}

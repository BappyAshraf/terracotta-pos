package com.pos.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.user.dao.UserDao;
import com.pos.user.model.UserInfo;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	public UserInfo userPasswordChng(UserInfo userInfo) {
		return userDao.userPasswordChng(userInfo);
	}
	

}

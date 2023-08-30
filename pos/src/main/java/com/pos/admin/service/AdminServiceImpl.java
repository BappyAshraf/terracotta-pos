package com.pos.admin.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.admin.model.EmployeeInfo;
import com.pos.admin.dao.AdminDao;
import com.pos.admin.model.RoleInfoForm;
import com.pos.admin.model.RoleMenuMapping;
import com.pos.admin.model.UserInfoForm;





@Service

public  class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminInfoDao;
	
	
	public List<RoleInfoForm> getRoleInfoList(RoleInfoForm roleInfoForm){
		return adminInfoDao.getRoleInfoList(roleInfoForm);
	}
	public RoleInfoForm getRoleInfo(RoleInfoForm roleInfoForm){
		return adminInfoDao.getRoleInfo(roleInfoForm);
	}
	public RoleInfoForm saveRoleInfo(RoleInfoForm roleInfoForm){
		return adminInfoDao.saveRoleInfo(roleInfoForm);
	}
	public RoleInfoForm deleteRole(RoleInfoForm roleInfoForm){
		return adminInfoDao.deleteRole(roleInfoForm);
	}
	public List<RoleMenuMapping> getRoleALLMenuList(RoleMenuMapping roleMenuMapping){
		return adminInfoDao.getRoleALLMenuList(roleMenuMapping);
	}
	public List<RoleMenuMapping> getRoleMenuMappingList(RoleMenuMapping roleMenuMapping){
		return adminInfoDao.getRoleMenuMappingList(roleMenuMapping);
	}
	public RoleMenuMapping saveRoleMenuMapping(RoleMenuMapping roleMenuMapping) {
		return adminInfoDao.saveRoleMenuMapping(roleMenuMapping);
	}
	

	public UserInfoForm getUserInfo(UserInfoForm userInfoForm){
		return adminInfoDao.getUserInfo(userInfoForm);
	}

	public UserInfoForm getUserEmpInfo(UserInfoForm userInfoForm){
		return adminInfoDao.getUserEmpInfo(userInfoForm);
	}
	public List<UserInfoForm>getUserInfoList(UserInfoForm userInfoForm){
		return adminInfoDao.getUserInfoList(userInfoForm);
	}
	
	public List<UserInfoForm> getUserRoleList(String encEmploueeId){
		return adminInfoDao.getUserRoleList(encEmploueeId);
	}

	public UserInfoForm saveUserInfo(UserInfoForm userInfoForm) {
		return adminInfoDao.saveUserInfo(userInfoForm);
	}

	public UserInfoForm deleteUserInfo(UserInfoForm userInfoForm) {
		return adminInfoDao.deleteUserInfo(userInfoForm);
	}
	
	public String checkUserName(UserInfoForm userInfoForm){
		return adminInfoDao.checkUserName(userInfoForm);
	}
	
	public EmployeeInfo getEmployeeInfo(EmployeeInfo employeeInfo) {
		return adminInfoDao.getEmployeeInfo(employeeInfo);
	}
	public List<EmployeeInfo> getEmployeeInfoList(EmployeeInfo employeeInfo) {
		return adminInfoDao.getEmployeeInfoList(employeeInfo);
	}
	public EmployeeInfo getEmployeeInfoListCount(EmployeeInfo employeeInfo) {
		return adminInfoDao.getEmployeeInfoListCount(employeeInfo);
	}
	public EmployeeInfo saveEmployeeInfo(EmployeeInfo employeeInfo) {
		return adminInfoDao.saveEmployeeInfo(employeeInfo);
	}
	public EmployeeInfo deleteEmployeeInfo(EmployeeInfo employeeInfo) {
		return adminInfoDao.deleteEmployeeInfo(employeeInfo);
	}
	

}

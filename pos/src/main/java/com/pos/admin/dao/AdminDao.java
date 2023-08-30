package com.pos.admin.dao;

import java.util.List;

import com.pos.admin.model.EmployeeInfo;
import com.pos.admin.model.RoleInfoForm;
import com.pos.admin.model.RoleMenuMapping;
import com.pos.admin.model.UserInfoForm;



public interface AdminDao {
	
	public List<RoleInfoForm> getRoleInfoList(RoleInfoForm roleInfoForm);
	public RoleInfoForm getRoleInfo(RoleInfoForm roleInfoForm);
	public RoleInfoForm saveRoleInfo(RoleInfoForm roleInfoForm);
	public RoleInfoForm deleteRole(RoleInfoForm roleInfoForm);
	public List<RoleMenuMapping> getRoleALLMenuList(RoleMenuMapping roleMenuMapping);
	public List<RoleMenuMapping> getRoleMenuMappingList(RoleMenuMapping roleMenuMapping);
	public RoleMenuMapping saveRoleMenuMapping(RoleMenuMapping roleMenuMapping) ;

	public UserInfoForm getUserInfo(UserInfoForm userInfoForm);
	public UserInfoForm getUserEmpInfo(UserInfoForm userInfoForm);
	public List<UserInfoForm> getUserInfoList(UserInfoForm userInfoForm);
	public List<UserInfoForm> getUserRoleList(String encEmploueeId);
	public UserInfoForm saveUserInfo(UserInfoForm userInfoForm);
	public UserInfoForm deleteUserInfo(UserInfoForm userInfoForm);
	public String checkUserName(UserInfoForm userInfoForm);

	public EmployeeInfo getEmployeeInfo(EmployeeInfo employeeInfo);
	public List<EmployeeInfo> getEmployeeInfoList(EmployeeInfo employeeInfo);
	public EmployeeInfo getEmployeeInfoListCount(EmployeeInfo employeeInfo);
	public EmployeeInfo saveEmployeeInfo(EmployeeInfo employeeInfo);
	public EmployeeInfo deleteEmployeeInfo(EmployeeInfo employeeInfo);
	
}

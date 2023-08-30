package com.pos.hr.dao;

import java.util.List;

import com.pos.hr.model.EmployeeInformation;

public interface HRDao {
	
public List<EmployeeInformation> getEmployeeInfoList(EmployeeInformation employeeInformation);

public EmployeeInformation empInfoSave(EmployeeInformation employeeInformation);

public EmployeeInformation getEmployeeInfo(EmployeeInformation employeeInformation);

}

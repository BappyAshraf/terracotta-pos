package com.pos.hr.service;

import java.util.List;

import com.pos.hr.model.EmployeeInformation;


public interface HRService {
	
	public List<EmployeeInformation> getEmployeeInfoList(EmployeeInformation employeeInformation);
	
	public EmployeeInformation empInfoSave(EmployeeInformation employeeInformation);
	
	public EmployeeInformation getEmployeeInfo(EmployeeInformation employeeInformation);
	
	
				
}

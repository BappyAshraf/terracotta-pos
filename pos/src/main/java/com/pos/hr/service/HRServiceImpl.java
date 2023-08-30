package com.pos.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.hr.dao.HRDao;
import com.pos.hr.model.EmployeeInformation;



@Service
public class HRServiceImpl implements HRService {

	@Autowired
	HRDao hrDao;

	public List<EmployeeInformation> getEmployeeInfoList(EmployeeInformation employeeInformation) {
		return hrDao.getEmployeeInfoList(employeeInformation);
	}
	
	public EmployeeInformation empInfoSave(EmployeeInformation employeeInformation) {
		return hrDao.empInfoSave(employeeInformation);
	}

	public EmployeeInformation getEmployeeInfo(EmployeeInformation employeeInformation) {
		return hrDao.getEmployeeInfo(employeeInformation);
	}
	
	
}

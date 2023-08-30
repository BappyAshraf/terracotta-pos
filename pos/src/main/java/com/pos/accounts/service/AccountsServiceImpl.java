package com.pos.accounts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.accounts.dao.AccountsDao;
import com.pos.accounts.model.EmployeeMonthlyConsumption;
import com.pos.accounts.model.OwnerConsumptionInfo;
import com.pos.accounts.model.SalaryProcessModel;
import com.pos.accounts.model.StockProcess;
import com.pos.accounts.model.SupplierInfo;

@Service
public class AccountsServiceImpl implements AccountsService {

	@Autowired
	AccountsDao accountsDao;
	
	public List<EmployeeMonthlyConsumption> getEmpMonthlyConsumption(EmployeeMonthlyConsumption employeeMonthlyConsumption) {
		return accountsDao.getEmpMonthlyConsumption(employeeMonthlyConsumption);
	}
	
	public EmployeeMonthlyConsumption getConsumeInfo(EmployeeMonthlyConsumption employeeMonthlyConsumption) {
		return accountsDao.getConsumeInfo(employeeMonthlyConsumption);
	}
	
	public EmployeeMonthlyConsumption empMonthlyConsumptionSave(EmployeeMonthlyConsumption employeeMonthlyConsumption) {
		return accountsDao.empMonthlyConsumptionSave(employeeMonthlyConsumption);
	}
	
	public List<SalaryProcessModel> salaryProcessSetup(SalaryProcessModel salaryProcessModel) {
		return accountsDao.salaryProcessSetup(salaryProcessModel);
	}
	
	public List<SalaryProcessModel> salaryProcessedSearch(SalaryProcessModel salaryProcessModel) {
		return accountsDao.salaryProcessedSearch(salaryProcessModel);
	}
	
	public SalaryProcessModel grossSalary(SalaryProcessModel salaryProcessModel) {
		return accountsDao.grossSalary(salaryProcessModel);
	}
	
	public SalaryProcessModel salaryProcessSave(SalaryProcessModel salaryProcessModel) {
		return accountsDao.salaryProcessSave(salaryProcessModel);
	}
	
	public SalaryProcessModel salaryProcessFinalize(SalaryProcessModel salaryProcessModel) {
		return accountsDao.salaryProcessFinalize(salaryProcessModel);
	}

	public List<SupplierInfo> supplierList(SupplierInfo supplierInfo) {
		return accountsDao.supplierList(supplierInfo);
	}

	public SupplierInfo saveSupplier(SupplierInfo supplierInfo) {
		return accountsDao.saveSupplier(supplierInfo);
	}

	public SupplierInfo getSupplierInfo(SupplierInfo supplierInfo) {
		return accountsDao.getSupplierInfo(supplierInfo);
	}
	
	public List<OwnerConsumptionInfo> getOwnerConsumptionList(OwnerConsumptionInfo ownerConsumptionInfo) {
		return accountsDao.getOwnerConsumptionList(ownerConsumptionInfo);
	}
	
	public OwnerConsumptionInfo ownerConsumptionSave(OwnerConsumptionInfo ownerConsumptionInfo) {
		return accountsDao.ownerConsumptionSave(ownerConsumptionInfo);
	}

	public OwnerConsumptionInfo getOwnerConsumption(OwnerConsumptionInfo ownerConsumptionInfo) {
		return accountsDao.getOwnerConsumption(ownerConsumptionInfo);
	}
	
	public OwnerConsumptionInfo finalizeOwnerConsumeInfo(OwnerConsumptionInfo ownerConsumptionInfo) {
		return accountsDao.finalizeOwnerConsumeInfo(ownerConsumptionInfo);
	}
	
	public List<OwnerConsumptionInfo> getOwnerConsumptionHistoryList(OwnerConsumptionInfo ownerConsumptionInfo) {
		return accountsDao.getOwnerConsumptionHistoryList(ownerConsumptionInfo);
	}
	
	public StockProcess processStock(StockProcess stockProcess) {
		return accountsDao.processStock(stockProcess);
	}
	
	

}
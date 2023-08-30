package com.pos.lookup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.lookup.dao.LookupDao;
import com.pos.lookup.model.LookupModel;

@Service("lookupService")
public class LookupServiceImpl implements LookupService{

	@Autowired
	private LookupDao lookupDao;
	
	public List<LookupModel> unitList(LookupModel lookupModel) {
		return lookupDao.unitList(lookupModel);
	}

	public List<LookupModel> productList(LookupModel lookupModel) {
		return lookupDao.productList(lookupModel);
	}

	public List<LookupModel> employeeList(LookupModel lookupModel) {
		return lookupDao.employeeList(lookupModel);
	}

	public List<LookupModel> inventoryTypeList(LookupModel lookupModel) {
		return lookupDao.inventoryTypeList(lookupModel);
	}
	
	public List<LookupModel> inventoryTypeListOther(LookupModel lookupModel) {
		return lookupDao.inventoryTypeListOther(lookupModel);
	}

	public List<LookupModel> itemList(LookupModel lookupModel) {
		return lookupDao.itemList(lookupModel);
	}
	
	public List<LookupModel> roleList(LookupModel lookupModel) {
		return lookupDao.roleList(lookupModel);
	}
	
	public List<LookupModel> mainMenuList(LookupModel lookupModel) {
		return lookupDao.mainMenuList(lookupModel);
	}
	
	/*public List<LookupModel> unitList(LookupModel lookupModel) {
		return lookupDao.unitList(lookupModel);
	}*/
	
	public List<LookupModel> venueList(LookupModel lookupModel) {
		return lookupDao.venueList(lookupModel);
	}
	
	public List<LookupModel> programmeList(LookupModel lookupModel) {
		return lookupDao.programmeList(lookupModel);
	}
	
	public List<LookupModel> designationList() {
	return lookupDao.designationList();
	}
	
	
	public List<LookupModel> genderList() {
		return lookupDao.genderList();
	}

	public List<LookupModel> maritalStatusList() {
		return lookupDao.maritalStatusList();
	}

	public List<LookupModel> religionList() {
		return lookupDao.religionList();
	}
	
	public List<LookupModel> allowanceDeducCategoryListForEmpConsume() {
		return lookupDao.allowanceDeducCategoryListForEmpConsume();
	}
	
	public List<LookupModel> supplierList(LookupModel lookupModel) {
		return lookupDao.supplierList(lookupModel);
	}

	public List<LookupModel> customerList(LookupModel lookupModel) {
		return lookupDao.customerList(lookupModel);
	}


}

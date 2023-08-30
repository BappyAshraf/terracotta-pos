package com.pos.lookup.service;

import java.util.List;

import com.pos.lookup.model.LookupModel;

public interface LookupService {
	public List<LookupModel> unitList(LookupModel lookupModel);
	public List<LookupModel> productList(LookupModel lookupModel);
	public List<LookupModel> employeeList(LookupModel lookupModel);
	public List<LookupModel> inventoryTypeList(LookupModel lookupModel);
	
	public List<LookupModel> inventoryTypeListOther(LookupModel lookupModel);
	
	public List<LookupModel> itemList(LookupModel lookupModel);
	
	public List<LookupModel> roleList(LookupModel lookupModel);
	
	public List<LookupModel> mainMenuList(LookupModel lookupModel);
	
	//public List<LookupModel> unitList(LookupModel lookupModel);
	
	public List<LookupModel> venueList(LookupModel lookupModel);

	public List<LookupModel> programmeList(LookupModel lookupModel); 
	
	public List<LookupModel> designationList();

	public List<LookupModel> genderList();

	public List<LookupModel> maritalStatusList();

	public List<LookupModel> religionList();
	
	public List<LookupModel> allowanceDeducCategoryListForEmpConsume();
	
	public List<LookupModel> supplierList(LookupModel lookupModel);
	
	public List<LookupModel> customerList(LookupModel lookupModel);
}

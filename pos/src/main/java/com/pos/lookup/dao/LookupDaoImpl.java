package com.pos.lookup.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aes.protection.CipherUtils;
import com.pos.lookup.model.LookupModel;

@Repository("lookupDao")
public class LookupDaoImpl implements LookupDao{

	private JdbcTemplate jdbcTemplate;
	CipherUtils oCipherUtils = new CipherUtils();

	@Autowired
	private DataSource dataSource;
	

	public List<LookupModel> unitList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);

		List<LookupModel> oUnitList = new ArrayList<LookupModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("UNIT_ID,");
			sBuilder.append("UNIT_NAME ");
			sBuilder.append("FROM L_UNIT ");

			// String sql = "SELECT BRANCH_ID, NAME FROM L_BRANCH " + "WHERE 1 =
			// 1 ";

			MapSqlParameterSource paramSource = new MapSqlParameterSource();

			// System.out.println(sql);

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setUnitId(String.valueOf(row.get("UNIT_ID")));
				oLookupModel.setUnitName(String.valueOf(row.get("UNIT_NAME")));
				oUnitList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oUnitList;
	}

	public List<LookupModel> productList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);

		List<LookupModel> oProductList = new ArrayList<LookupModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("PRODUCT_ID,");
			sBuilder.append("PRODUCT_NAME ");
			sBuilder.append("FROM L_PRODUCT ");

			// String sql = "SELECT BRANCH_ID, NAME FROM L_BRANCH " + "WHERE 1 =
			// 1 ";

			MapSqlParameterSource paramSource = new MapSqlParameterSource();

			// System.out.println(sql);

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setProductId(String.valueOf(row.get("PRODUCT_ID")));
				oLookupModel.setProductName(String.valueOf(row.get("PRODUCT_NAME")));
				oProductList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oProductList;
	}
	

	public List<LookupModel> employeeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);

		List<LookupModel> oEmployeeList = new ArrayList<LookupModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("EMPLOYEE_ID,");
			sBuilder.append("EMPLOYEE_NAME ");
			sBuilder.append("FROM EMPLOYEE ");
			sBuilder.append("WHERE 1=1 ");

			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			
			/*if(lookupModel.getId() !=null && lookupModel.getId().length() >0) {
				sBuilder.append("AND DESIGNATION_ID = :id ");
				paramSource.addValue("id", lookupModel.getId());
			}*/
			
			 sBuilder.append("ORDER BY EMPLOYEE_NAME ");
				
			 //System.out.println("EMPLOYEE LIST : " + sBuilder);

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setEmployeeId(String.valueOf(row.get("EMPLOYEE_ID")));
				oLookupModel.setFirstName(String.valueOf(row.get("EMPLOYEE_NAME")));
				oEmployeeList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oEmployeeList;
	}
	
	

	public List<LookupModel> inventoryTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);

		List<LookupModel> oInventoryTypeList = new ArrayList<LookupModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("INVENTORY_TYPE_ID,");
			sBuilder.append("INVENTORY_TYPE_NAME ");
			sBuilder.append("FROM L_INVENTORY_TYPE ");
			sBuilder.append("WHERE INVENTORY_TYPE_ID IN(201,202,203,204,205) ");

			// String sql = "SELECT BRANCH_ID, NAME FROM L_BRANCH " + "WHERE 1 = 1 ";

			MapSqlParameterSource paramSource = new MapSqlParameterSource();

			 //System.out.println("INVENTORY_TYPE_list " + sBuilder);

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setInventoryTypeId(String.valueOf(row.get("INVENTORY_TYPE_ID")));
				oLookupModel.setInventoryTypeName(String.valueOf(row.get("INVENTORY_TYPE_NAME")));
				oInventoryTypeList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oInventoryTypeList;
	}
	
	
	public List<LookupModel> inventoryTypeListOther(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);

		List<LookupModel> oInventoryTypeList = new ArrayList<LookupModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("INVENTORY_TYPE_ID,");
			sBuilder.append("INVENTORY_TYPE_NAME ");
			sBuilder.append("FROM L_INVENTORY_TYPE ");
			sBuilder.append("WHERE INVENTORY_TYPE_ID IN(206) ");

			// String sql = "SELECT BRANCH_ID, NAME FROM L_BRANCH " + "WHERE 1 = 1 ";

			MapSqlParameterSource paramSource = new MapSqlParameterSource();

			 //System.out.println("INVENTORY_TYPE_list " + sBuilder);

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setInventoryTypeId(String.valueOf(row.get("INVENTORY_TYPE_ID")));
				oLookupModel.setInventoryTypeName(String.valueOf(row.get("INVENTORY_TYPE_NAME")));
				oInventoryTypeList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oInventoryTypeList;
	}

	public List<LookupModel> itemList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);

		List<LookupModel> oItemList = new ArrayList<LookupModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("ITEM_ID,");
			sBuilder.append("ITEM_NAME ");
			sBuilder.append("FROM L_ITEM ");

			// String sql = "SELECT BRANCH_ID, NAME FROM L_BRANCH " + "WHERE 1 =
			// 1 ";

			MapSqlParameterSource paramSource = new MapSqlParameterSource();

			// System.out.println(sql);

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setItemId(String.valueOf(row.get("ITEM_ID")));
				oLookupModel.setItemName(String.valueOf(row.get("ITEM_NAME")));
				oItemList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oItemList;
	}
	
	public List<LookupModel> roleList(LookupModel lookupModel) {

		jdbcTemplate = new JdbcTemplate(dataSource);

		List<LookupModel> oprojectsList = new ArrayList<LookupModel>();

		try {

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("ROLE_ID,");
			sBuilder.append("ROLE_NAME ");
			sBuilder.append("FROM L_ROLE ");
			sBuilder.append("ORDER BY ROLE_NAME ");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());
			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setId(String.valueOf(row.get("ROLE_ID")));
				oLookupModel.setName(String.valueOf(row.get("ROLE_NAME")));
				oprojectsList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oprojectsList;
	}
	
	public List<LookupModel> mainMenuList(LookupModel lookupModel) {

		jdbcTemplate = new JdbcTemplate(dataSource);

		List<LookupModel> oMainMenuList = new ArrayList<LookupModel>();

		try {

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("PARENT_MENU_ID,");
			sBuilder.append("MENU_NAME ");
			sBuilder.append("FROM MAIN_MENU ");
			sBuilder.append("ORDER BY MENU_NAME ");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());
			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setId(String.valueOf(row.get("PARENT_MENU_ID")));
				oLookupModel.setName(String.valueOf(row.get("MENU_NAME")));
				oMainMenuList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oMainMenuList;
	}
	

	/*public List<LookupModel> unitList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oUnitList = new ArrayList<LookupModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("UNIT_ID,");
			sBuilder.append("UNIT_NAME ");
			sBuilder.append("FROM L_UNIT ");
			// String sql = "SELECT BRANCH_ID, NAME FROM L_BRANCH " + "WHERE 1 =
			// 1 ";
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			// System.out.println(sql);
			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);
			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setUnitId(String.valueOf(row.get("UNIT_ID")));
				oLookupModel.setUnitName(String.valueOf(row.get("UNIT_NAME")));
				oUnitList.add(oLookupModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oUnitList;
	}
*/	
	
	public List<LookupModel> venueList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);

		List<LookupModel> oVenueList = new ArrayList<LookupModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("VENUE_ID,");
			sBuilder.append("VENUE_NAME ");
			sBuilder.append("FROM L_VENUE ");

			// String sql = "SELECT BRANCH_ID, NAME FROM L_BRANCH " + "WHERE 1 =
			// 1 ";

			MapSqlParameterSource paramSource = new MapSqlParameterSource();

			// System.out.println(sql);

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setId(String.valueOf(row.get("VENUE_ID")));
				oLookupModel.setName(String.valueOf(row.get("VENUE_NAME")));
				oVenueList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oVenueList;
		
	}
	
	
	public List<LookupModel> programmeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);

		List<LookupModel> oProgrammeList = new ArrayList<LookupModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("PROGRAM_TYPE_ID, ");
			sBuilder.append("PROGRAM_TYPE_NAME ");
			sBuilder.append("FROM L_PROGRAM ");

			// String sql = "SELECT BRANCH_ID, NAME FROM L_BRANCH " + "WHERE 1 =
			// 1 ";

			MapSqlParameterSource paramSource = new MapSqlParameterSource();

			// System.out.println(sql);

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setId(String.valueOf(row.get("PROGRAM_TYPE_ID")));
				oLookupModel.setName(String.valueOf(row.get("PROGRAM_TYPE_NAME")));
				oProgrammeList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oProgrammeList;
	}
	
	public List<LookupModel> designationList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);

		List<LookupModel> oDesignationList = new ArrayList<LookupModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("DESIGNATION_ID,");
			sBuilder.append("DESIGNATION_NAME ");
			sBuilder.append("FROM L_DESIGNATION ");

			MapSqlParameterSource paramSource = new MapSqlParameterSource();

			// System.out.println(sql);

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setId(String.valueOf(row.get("DESIGNATION_ID")));
				oLookupModel.setName(String.valueOf(row.get("DESIGNATION_NAME")));
				oDesignationList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oDesignationList;
	}	
	
	public List<LookupModel> genderList() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<LookupModel> oGenderList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();

			sBuilder.append(" SELECT GENDER_ID, ");
			sBuilder.append(" GENDER_NAME ");
			sBuilder.append(" FROM  L_GENDER ");
			sBuilder.append(" WHERE ENABLED_YN='Y' ");

			MapSqlParameterSource paramSource = new MapSqlParameterSource();

			sBuilder.append("ORDER BY GENDER_NAME ");

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setId(String.valueOf(row.get("GENDER_ID")));
				oLookupModel.setName(String.valueOf(row.get("GENDER_NAME")));
				oGenderList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oGenderList;
	}

	public List<LookupModel> maritalStatusList() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<LookupModel> oMaritalStatusList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();

			sBuilder.append(" SELECT MARITAL_STATUS_ID, ");
			sBuilder.append(" MARITAL_STATUS_NAME ");
			sBuilder.append(" FROM  L_MARITAL_STATUS ");
			sBuilder.append(" WHERE ENABLED_YN='Y' ");

			MapSqlParameterSource paramSource = new MapSqlParameterSource();

			sBuilder.append("ORDER BY MARITAL_STATUS_NAME ");

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setId(String.valueOf(row.get("MARITAL_STATUS_ID")));
				oLookupModel.setName(String.valueOf(row.get("MARITAL_STATUS_NAME")));
				oMaritalStatusList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oMaritalStatusList;
	}

	public List<LookupModel> religionList() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<LookupModel> oReligionList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();

			sBuilder.append(" SELECT RELIGION_ID, ");
			sBuilder.append(" RELIGION_NAME ");
			sBuilder.append(" FROM  L_RELIGION ");
			sBuilder.append(" WHERE ENABLED_YN='Y' ");

			MapSqlParameterSource paramSource = new MapSqlParameterSource();

			sBuilder.append("ORDER BY RELIGION_NAME ");

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setId(String.valueOf(row.get("RELIGION_ID")));
				oLookupModel.setName(String.valueOf(row.get("RELIGION_NAME")));
				oReligionList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oReligionList;
	}
	
	public List<LookupModel> designationList() {

		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oDesignationList = new ArrayList<LookupModel>();

		try {

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("DESIGNATION_ID,");
			sBuilder.append("DESIGNATION_NAME ");
			sBuilder.append("FROM L_DESIGNATION ");
			sBuilder.append("ORDER BY DESIGNATION_NAME ");
			// sBuilder.append("WHERE ENABLED_YN = 'Y' ");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());
			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setId(String.valueOf(row.get("DESIGNATION_ID")));
				oLookupModel.setName(String.valueOf(row.get("DESIGNATION_NAME")));
				oDesignationList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oDesignationList;
	}
	
	
	public List<LookupModel> allowanceDeducCategoryListForEmpConsume() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oAllowanceDeducCategoryList = new ArrayList<LookupModel>();

		try {

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT AD_CATEGORY_ID, AD_CATEGORY_NAME || ' - ' || EARNING_DEDUCTION_TYPE_NAME AS AD_CATEGORY_NAME  ");
			sBuilder.append("FROM PAYROLL_EARN_DEDUCT_CATEGORY WHERE  AD_CATEGORY_ID <> 1 ");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());
			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setId(String.valueOf(row.get("AD_CATEGORY_ID")));
				oLookupModel.setName(String.valueOf(row.get("AD_CATEGORY_NAME")));
				oAllowanceDeducCategoryList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oAllowanceDeducCategoryList;
	}
	
	
	public List<LookupModel> supplierList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oSupplierList = new ArrayList<LookupModel>();

		try {

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT SUPPLIER_ID, SUPPLIER_NAME ");
			sBuilder.append("FROM SUPPLIER_INFO ");
			sBuilder.append("WHERE ENABLED_YN = 'Y' ");
			
			//System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());
			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setId(String.valueOf(row.get("SUPPLIER_ID")));
				oLookupModel.setName(String.valueOf(row.get("SUPPLIER_NAME")));
				oSupplierList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oSupplierList;
	}

	public List<LookupModel> customerList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> customerList = new ArrayList<LookupModel>();

		try {

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT DUE_CUSTOMER_ID, CUSTOMER_NAME ");
			sBuilder.append("FROM DUE_CUSTOMER ");
			
			//System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());
			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setDueCustomerId(String.valueOf(row.get("DUE_CUSTOMER_ID")));
				oLookupModel.setCustomerName(String.valueOf(row.get("CUSTOMER_NAME")));
				customerList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return customerList;
	}



}
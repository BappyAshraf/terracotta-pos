package com.pos.inventory.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.aes.protection.CipherUtils;
import com.pos.common.RemoveNull;
import com.pos.inventory.model.Inventory;


@Repository("InventoryDao")
public class InventoryDaoImpl implements InventoryDao{

	private SimpleJdbcCall simpleJdbcCall;
	private JdbcTemplate jdbcTemplate;
	CipherUtils oCipherUtils = new CipherUtils();
	@Autowired
	private DataSource dataSource;
	
	RemoveNull oRemoveNull = new RemoveNull();

	
	public List<Inventory> getIngredientsList(Inventory inventory) throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Inventory> oIngredientsList = new ArrayList<Inventory>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT PRODUCT_ID, PRODUCT_CODE, PRODUCT_NAME, UNIT_ID, ");
		sBuilder.append("(SELECT UNIT_NAME FROM L_UNIT WHERE UNIT_ID = P.UNIT_ID) UNIT_NAME ");
		sBuilder.append(" FROM L_PRODUCT P ");
		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		//System.out.println("bbb" + sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			Inventory oInventory = new Inventory();
			oInventory.setEncProductId(oCipherUtils.encrypt(String.valueOf(row.get("PRODUCT_ID"))));
			oInventory.setProductId(oRemoveNull.nullRemove(String.valueOf(row.get("PRODUCT_ID"))));
			oInventory.setProductCode(oRemoveNull.nullRemove(String.valueOf(row.get("PRODUCT_CODE"))));
			oInventory.setProductName(oRemoveNull.nullRemove(String.valueOf(row.get("PRODUCT_NAME"))));
			oInventory.setUnitId(oRemoveNull.nullRemove(String.valueOf(row.get("UNIT_ID"))));
			oInventory.setUnitName(oRemoveNull.nullRemove(String.valueOf(row.get("UNIT_NAME"))));
			oIngredientsList.add(oInventory);

			// System.out.println("abc"+
			// oCipherUtils.encrypt(String.valueOf(row.get("EMPLOYEE_ID"))));

		}

		return oIngredientsList;
	}
	
	public Inventory saveIngredients(Inventory inventory) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		Inventory oInventory = new Inventory();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_INGREDIENTS_SAVE");
			Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("P_PRODUCT_ID", oCipherUtils.decrypt(inventory.getEncProductId()));
			inParamMap.put("P_PRODUCT_NAME", inventory.getProductName());
			inParamMap.put("P_UNIT_ID", inventory.getUnitId());
			inParamMap.put("P_UPDATE_BY", inventory.getUpdateBy());
			inParamMap.put("P_UPDATE_DATE", inventory.getUpdateDate());
			
			//inParamMap.put("P_UPDATE_DATE", student.getUpdateDate());

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oInventory.setMessage((String) outParamMap.get("P_MESSAGE"));
			oInventory.setmCode((String) outParamMap.get("P_MESSAGE_CODE"));
			//System.out.println("studentId " + oStudent.getStudentId());
		} catch (Exception ex) {
			oInventory.setMessage("Error Saving Record !!!");
			oInventory.setmCode("0000");
			ex.printStackTrace();
		}
		return oInventory;
	}
	

	public Inventory saveStoreProduct(Inventory inventory) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		Inventory oInventory = new Inventory();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_STORE_PRODUCT_SAVE");
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			
			inParamMap.put("P_INVENTORY_ID", oCipherUtils.decrypt(oRemoveNull.nullRemove(inventory.getEncInventoryId())));
			inParamMap.put("P_INVENTORY_TYPE_ID", inventory.getInventoryTypeId());
			inParamMap.put("P_PRODUCT_ID", inventory.getProductId());
			inParamMap.put("P_UNIT_ID", inventory.getUnitId());
			inParamMap.put("P_UNIT_PRICE", inventory.getUnitPrice());
			inParamMap.put("P_QUANTITY", inventory.getQuantity());
			inParamMap.put("P_PRICE", inventory.getPrice());
			inParamMap.put("P_ADVANCE_AMOUNT", inventory.getAdvanceAmount());
			inParamMap.put("P_EMPLOYEE_ID", inventory.getEmployeeId());
			inParamMap.put("P_UPDATE_BY", inventory.getUpdateBy());
			inParamMap.put("P_SUPPLIER_ID", inventory.getSupplierId());
			
			System.out.println("inventory.getUnitPrice() " + inventory.getUnitPrice());
			System.out.println("inventory.getPrice() " + inventory.getPrice());
			System.out.println("inventory.getAdvanceAmount() " + inventory.getAdvanceAmount());
			

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oInventory.setMessage((String) outParamMap.get("P_MESSAGE"));
			oInventory.setmCode((String) outParamMap.get("P_MESSAGE_CODE"));
			//oInventory.setInventoryDate((String) outParamMap.get("O_INVENTORY_DATE"));
			//System.out.println("studentId " + oStudent.getStudeP_INVENTORY_IDntId());
		} catch (Exception ex) {
			oInventory.setMessage("Error Saving Record !!!");
			oInventory.setmCode("0000");
			ex.printStackTrace();
		}
		return oInventory;
	}
	
	
	public Inventory getProductInfo(Inventory inventory) throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
		Inventory oInventory = new Inventory();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT INVENTORY_ID, ");
		sBuilder.append(" INVENTORY_TYPE_ID, ");
		sBuilder.append(" PRODUCT_ID, ");
		sBuilder.append(" UNIT_ID, ");
		sBuilder.append(" UNIT_PRICE, ");
		sBuilder.append(" QUANTITY, ");
		sBuilder.append(" PRICE, ");
		sBuilder.append(" EMPLOYEE_ID, ");
		sBuilder.append(" ADVANCE_AMOUNT, ");
		sBuilder.append(" SUPPLIER_ID ");
		sBuilder.append(" FROM INVENTORY_MASTER ");
		sBuilder.append(" WHERE INVENTORY_ID = :inventoryId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("inventoryId", oCipherUtils.decrypt(oRemoveNull.nullRemove(inventory.getEncInventoryId())));
		
		System.out.println("getEncInventoryId "+ oCipherUtils.decrypt(oRemoveNull.nullRemove(inventory.getEncInventoryId())));
		System.out.println("getProductInfo "+sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oInventory.setEncInventoryId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("INVENTORY_ID")))));
			oInventory.setInventoryId(oRemoveNull.nullRemove(String.valueOf(row.get("INVENTORY_ID"))));
			oInventory.setInventoryTypeId(oRemoveNull.nullRemove(String.valueOf(row.get("INVENTORY_TYPE_ID"))));
			oInventory.setProductId(oRemoveNull.nullRemove(String.valueOf(row.get("PRODUCT_ID"))));
			oInventory.setUnitId(oRemoveNull.nullRemove(String.valueOf(row.get("UNIT_ID"))));
			oInventory.setUnitPrice(oRemoveNull.nullRemove(String.valueOf(row.get("UNIT_PRICE"))));
			oInventory.setQuantity(oRemoveNull.nullRemove(String.valueOf(row.get("QUANTITY"))));
			oInventory.setPrice(oRemoveNull.nullRemove(String.valueOf(row.get("PRICE"))));
			oInventory.setEmployeeId(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_ID"))));
			oInventory.setAdvanceAmount(oRemoveNull.nullRemove(String.valueOf(row.get("ADVANCE_AMOUNT"))));
			oInventory.setSupplierId(oRemoveNull.nullRemove(String.valueOf(row.get("SUPPLIER_ID"))));
		
		}
		return oInventory;
	}
	

	public List<Inventory> getInventoryList(Inventory inventory) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Inventory> oInventoryList = new ArrayList<Inventory>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT INVENTORY_ID, ");
		sBuilder.append(" (SELECT PRODUCT_NAME FROM L_PRODUCT WHERE PRODUCT_ID = M.PRODUCT_ID) PRODUCT_NAME, ");
		sBuilder.append(" (SELECT UNIT_NAME FROM L_UNIT WHERE UNIT_ID = M.UNIT_ID) UNIT_NAME, ");
		sBuilder.append(" UNIT_PRICE, ");
		sBuilder.append(" QUANTITY, ");
		sBuilder.append(" PRICE, ");
		sBuilder.append(" (SELECT EMPLOYEE_NAME FROM EMPLOYEE WHERE EMPLOYEE_ID = M.EMPLOYEE_ID) EMPLOYEE_NAME, ");
		sBuilder.append(" ADVANCE_AMOUNT, ");
		sBuilder.append(" (SELECT SUPPLIER_NAME FROM SUPPLIER_INFO WHERE SUPPLIER_ID = M.SUPPLIER_ID) SUPPLIER_NAME, ");
		sBuilder.append(" TO_CHAR(INVENTORY_DATE,'DD-MON-YYYY') INVENT_DATE ");
		sBuilder.append(" FROM INVENTORY_MASTER M ");
		sBuilder.append(" WHERE TRUNC(INVENTORY_DATE) = TRUNC(SYSDATE) ");
		sBuilder.append(" AND INVENTORY_TYPE_ID NOT IN (206) ");
		//sBuilder.append(" AND TRUNC(INVENTORY_DATE) = TO_DATE(:inventoryDate,'DD/MM/YYYY') ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		//paramSource.addValue("inventoryTypeId", inventory.getInventoryTypeId());
		//paramSource.addValue("inventoryDate", inventory.getInventoryDate());
		
		sBuilder.append(" ORDER BY INVENTORY_DATE DESC ");
		
		//System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			Inventory oInventory = new Inventory();
			oInventory.setEncInventoryId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("INVENTORY_ID")))));
			oInventory.setInventoryId(oRemoveNull.nullRemove(String.valueOf(row.get("INVENTORY_ID"))));
			oInventory.setProductName(oRemoveNull.nullRemove(String.valueOf(row.get("PRODUCT_NAME"))));
			oInventory.setUnitName(oRemoveNull.nullRemove(String.valueOf(row.get("UNIT_NAME"))));
			oInventory.setUnitPrice(oRemoveNull.nullRemove(String.valueOf(row.get("UNIT_PRICE"))));
			oInventory.setQuantity(oRemoveNull.nullRemove(String.valueOf(row.get("QUANTITY"))));
			oInventory.setPrice(oRemoveNull.nullRemove(String.valueOf(row.get("PRICE"))));
			oInventory.setEmployeeName(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_NAME"))));
			oInventory.setAdvanceAmount(oRemoveNull.nullRemove(String.valueOf(row.get("ADVANCE_AMOUNT"))));
			oInventory.setSupplierName(oRemoveNull.nullRemove(String.valueOf(row.get("SUPPLIER_NAME"))));
			oInventory.setInventoryDate(oRemoveNull.nullRemove(String.valueOf(row.get("INVENT_DATE"))));
			oInventoryList.add(oInventory);
		}
		return oInventoryList;
	}
	
	
	
	public List<Inventory> getInventoryListSupplier(Inventory inventory) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Inventory> oInventoryListSupplier = new ArrayList<Inventory>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT INVENTORY_ID, ");
		sBuilder.append(" (SELECT PRODUCT_NAME FROM L_PRODUCT WHERE PRODUCT_ID = M.PRODUCT_ID) PRODUCT_NAME, ");
		sBuilder.append(" UNIT_PRICE, ");
		sBuilder.append(" QUANTITY, ");
		sBuilder.append(" (SELECT UNIT_NAME FROM L_UNIT WHERE UNIT_ID = M.UNIT_ID) UNIT_NAME, ");
		sBuilder.append(" PRICE, ");
		sBuilder.append(" (SELECT EMPLOYEE_NAME FROM EMPLOYEE WHERE EMPLOYEE_ID = M.EMPLOYEE_ID) EMPLOYEE_NAME, ");
		sBuilder.append(" TO_CHAR(INVENTORY_DATE,'DD/MM/YYYY') INVENT_DATE, ");
		sBuilder.append(" ADVANCE_AMOUNT, ");
		sBuilder.append(" (SELECT SUPPLIER_NAME FROM SUPPLIER_INFO WHERE SUPPLIER_ID = M.SUPPLIER_ID)SUPPLIER_NAME ");
		sBuilder.append(" FROM INVENTORY_MASTER M ");
		sBuilder.append(" WHERE TRUNC(INVENTORY_DATE) = TRUNC(SYSDATE) ");
		sBuilder.append(" AND INVENTORY_TYPE_ID = :inventoryTypeId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("inventoryTypeId", inventory.getInventoryTypeId());
		//paramSource.addValue("inventoryDate", inventory.getInventoryDate());
		
		sBuilder.append(" ORDER BY INVENTORY_DATE DESC ");
		
		//System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			Inventory oInventory = new Inventory();
			oInventory.setEncInventoryId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("INVENTORY_ID")))));
			oInventory.setInventoryId(oRemoveNull.nullRemove(String.valueOf(row.get("INVENTORY_ID"))));
			oInventory.setProductName(oRemoveNull.nullRemove(String.valueOf(row.get("PRODUCT_NAME"))));
			oInventory.setUnitPrice(oRemoveNull.nullRemove(String.valueOf(row.get("UNIT_PRICE"))));
			oInventory.setQuantity(oRemoveNull.nullRemove(String.valueOf(row.get("QUANTITY"))));
			oInventory.setUnitName(oRemoveNull.nullRemove(String.valueOf(row.get("UNIT_NAME"))));
			oInventory.setPrice(oRemoveNull.nullRemove(String.valueOf(row.get("PRICE"))));
			oInventory.setEmployeeName(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_NAME"))));
			oInventory.setInventoryDate(oRemoveNull.nullRemove(String.valueOf(row.get("INVENT_DATE"))));
			oInventory.setAdvanceAmount(oRemoveNull.nullRemove(oRemoveNull.nullRemove(String.valueOf(row.get("ADVANCE_AMOUNT")))));
			oInventory.setSupplierName(oRemoveNull.nullRemove(oRemoveNull.nullRemove(String.valueOf(row.get("SUPPLIER_NAME")))));
			
			oInventoryListSupplier.add(oInventory);

			// System.out.println("abc"+ oCipherUtils.encrypt(String.valueOf(row.get("EMPLOYEE_ID"))));

		}

		return oInventoryListSupplier;
	}

	public Inventory getPriceSum(Inventory inventory) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		Inventory oInventory = new Inventory();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT SUM (PRICE) SUM_PRICE FROM INVENTORY_MASTER WHERE INVENTORY_TYPE_ID = :inventoryTypeId ");
		sBuilder.append(" AND TRUNC (INVENTORY_DATE) = TRUNC(SYSDATE) ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("inventoryTypeId", inventory.getInventoryTypeId());
		paramSource.addValue("inventoryDate", inventory.getInventoryDate());
		// System.out.println("regId " +
		// oCipherUtils.decrypt(registration.getEncRegistrationId()));
		//System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oInventory.setPriceSum(oRemoveNull.nullRemove((String.valueOf(row.get("SUM_PRICE")))));
			
			//System.out.println("SUM_PRICE " + oInventory.getPriceSum());
			//oTeacher.setTeacherAttachmentFile(oRemoveNull.nullRemove(String.valueOf(row.get("TEACHER_PHOTO"))));

			// oTeacherRemoveNull.removeNullRegistration(oTeacher);
		}
		return oInventory;
	}

	public List<Inventory> getProductView(Inventory inventory) throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Inventory> oProductViewList = new ArrayList<Inventory>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT INVENTORY_ID, ");
		sBuilder.append(" INVENTORY_TYPE_ID, ");
		sBuilder.append(" (SELECT INVENTORY_TYPE_NAME ");
		sBuilder.append(" FROM L_INVENTORY_TYPE ");
		sBuilder.append(" WHERE INVENTORY_TYPE_ID = M.INVENTORY_TYPE_ID) ");
		sBuilder.append(" INVENTORY_TYPE_NAME, ");
		sBuilder.append(" PRODUCT_ID, ");
		sBuilder.append(" (SELECT PRODUCT_NAME FROM L_PRODUCT WHERE PRODUCT_ID = M.PRODUCT_ID) PRODUCT_NAME, ");
		sBuilder.append(" TO_CHAR(UPDATE_DATE,'DD/MM/YYYY') UPDATE_DATE, ");
		sBuilder.append(" PRICE, ");
		sBuilder.append(" EMPLOYEE_ID ");
		sBuilder.append(" FROM INVENTORY_MASTER M ");
		sBuilder.append(" WHERE 1 = 1 ");
		
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		if (inventory.getInventoryTypeId() != null && inventory.getInventoryTypeId().length() > 0) {
			sBuilder.append(" AND INVENTORY_TYPE_ID = :inventoryTypeId ");
			paramSource.addValue("inventoryTypeId", inventory.getInventoryTypeId());
		} 
		if(inventory.getProductId() != null && inventory.getProductId().length() > 0) {
			sBuilder.append(" AND PRODUCT_ID = :productId ");
			paramSource.addValue("productId", inventory.getProductId());
		} 
		if (inventory.getEmployeeId() != null && inventory.getEmployeeId().length() > 0) {
			sBuilder.append(" AND EMPLOYEE_ID = :employeeId ");
			paramSource.addValue("employeeId", inventory.getEmployeeId());
		} 
		if (inventory.getUpdateDate() != null && inventory.getUpdateDate().length() > 0) {
			sBuilder.append(" AND TRUNC (UPDATE_DATE) = TO_DATE (:updateDate, 'DD/MM/YYYY') ");
			paramSource.addValue("updateDate", inventory.getUpdateDate());
		}
		
		//System.out.println(sBuilder);
		//System.out.println("productId " + inventory.getProductId());

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);
		
		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			Inventory oInventory = new Inventory();
			oInventory.setInventoryId(oRemoveNull.nullRemove(String.valueOf(row.get("INVENTORY_ID"))));
			oInventory.setInventoryTypeId(oRemoveNull.nullRemove(String.valueOf(row.get("INVENTORY_TYPE_ID"))));
			oInventory.setInventoryTypeName(oRemoveNull.nullRemove(String.valueOf(row.get("INVENTORY_TYPE_NAME"))));
			oInventory.setProductId(oRemoveNull.nullRemove((String.valueOf(row.get("PRODUCT_ID")))));
			oInventory.setProductName(oRemoveNull.nullRemove((String.valueOf(row.get("PRODUCT_NAME")))));
			oInventory.setUpdateDate(oRemoveNull.nullRemove(String.valueOf(row.get("UPDATE_DATE"))));
			oInventory.setPrice(oRemoveNull.nullRemove(String.valueOf(row.get("PRICE"))));
			oInventory.setEmployeeId(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_ID"))));
			oProductViewList.add(oInventory);
		}
		return oProductViewList;
	}

	public Inventory saveWastage(Inventory inventory) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		Inventory oInventory = new Inventory();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_WASTAGE_SAVE");
			Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("P_WASTAGE_ID", oCipherUtils.decrypt(inventory.getEncWastageId()));
			inParamMap.put("P_INVENTORY_TYPE_ID", inventory.getInventoryTypeId());
			inParamMap.put("P_INGREDIENTS_ID", inventory.getProductId());
			inParamMap.put("P_ITEM_ID", inventory.getItemId());
			inParamMap.put("P_QUANTITY", inventory.getQuantity());
			inParamMap.put("P_UNIT_ID", inventory.getUnitId());
			inParamMap.put("P_PRICE", inventory.getPrice());
			inParamMap.put("P_UPDATE_BY", inventory.getUpdateBy());
			inParamMap.put("P_UPDATE_DATE", inventory.getUpdateDate());

			// inParamMap.put("P_UPDATE_DATE", student.getUpdateDate());

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oInventory.setMessage((String) outParamMap.get("P_MESSAGE"));
			oInventory.setmCode((String) outParamMap.get("P_MESSAGE_CODE"));
			// System.out.println("studentId " + oStudent.getStudentId());
		} catch (Exception ex) {
			oInventory.setMessage("Error Saving Record !!!");
			oInventory.setmCode("0000");
			ex.printStackTrace();
		}
		return oInventory;
	}

	public List<Inventory> getWastageView(Inventory inventory) throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Inventory> oWastageViewList = new ArrayList<Inventory>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT WASTAGE_ID, ");
		sBuilder.append(" INGREDIENTS_ID, ");
		sBuilder.append(" INVENTORY_TYPE_ID, ");
		sBuilder.append(" (SELECT PRODUCT_NAME  FROM L_PRODUCT  WHERE PRODUCT_ID = W.INGREDIENTS_ID) INGREDIENTS_NAME, ");
		sBuilder.append(" ITEM_ID, ");
		sBuilder.append(" (SELECT ITEM_NAME FROM L_ITEM WHERE ITEM_ID = W.ITEM_ID) ITEM_NAME, ");
		sBuilder.append(" QUANTITY, ");
		sBuilder.append(" UNIT_ID, ");
		sBuilder.append(" (SELECT UNIT_NAME FROM L_UNIT WHERE UNIT_ID = W.UNIT_ID) UNIT_NAME, ");
		sBuilder.append(" PRICE_AMOUNT, ");
		sBuilder.append(" UPDATE_BY, ");
		sBuilder.append(" TO_CHAR(UPDATE_DATE,'DD/MM/YYYY') UPDATE_DATE, ");
		//sBuilder.append(" TO_CHAR(WASTAGE_DATE,'DD/MM/YYYY') WASTAGE_DATE, ");
		sBuilder.append(" CASE WHEN TRUNC (UPDATE_DATE) < TRUNC(SYSDATE) THEN 'N' ELSE 'Y' END WASTAGE_SYSDATE_YN ");
		sBuilder.append(" FROM WASTAGE W ");
		sBuilder.append(" WHERE TRUNC (UPDATE_DATE) BETWEEN TO_DATE (:fromDate, 'dd/mm/yyyy') AND  TO_DATE (:toDate, 'dd/mm/yyyy') ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("fromDate", inventory.getFromDate());
		paramSource.addValue("toDate", inventory.getToDate());
		
		if (inventory.getProductId() != null && inventory.getProductId().length() > 0) {
			sBuilder.append(" AND INGREDIENTS_ID = :ingredientsId ");
			paramSource.addValue("ingredientsId", inventory.getProductId());
		} 
		if(inventory.getItemId() != null && inventory.getItemId().length() > 0) {
			sBuilder.append(" AND ITEM_ID = :itemId ");
			paramSource.addValue("itemId", inventory.getItemId());
		}
		
		//System.out.println(sBuilder);
		//System.out.println("productId " + inventory.getProductId());

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);
		
		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			Inventory oInventory = new Inventory();
			oInventory.setEncWastageId(oRemoveNull.nullRemove(oCipherUtils.encrypt((String.valueOf(row.get("WASTAGE_ID"))))));
			oInventory.setWastageId(oRemoveNull.nullRemove((String.valueOf(row.get("WASTAGE_ID")))));
			oInventory.setProductId(oRemoveNull.nullRemove((String.valueOf(row.get("INGREDIENTS_ID")))));
			oInventory.setInventoryTypeId(oRemoveNull.nullRemove((String.valueOf(row.get("INVENTORY_TYPE_ID")))));
			oInventory.setProductName(oRemoveNull.nullRemove((String.valueOf(row.get("INGREDIENTS_NAME")))));
			oInventory.setItemId(oRemoveNull.nullRemove((String.valueOf(row.get("ITEM_ID")))));
			oInventory.setItemName(oRemoveNull.nullRemove((String.valueOf(row.get("ITEM_NAME")))));
			oInventory.setQuantity(oRemoveNull.nullRemove((String.valueOf(row.get("QUANTITY")))));
			oInventory.setUnitId(oRemoveNull.nullRemove((String.valueOf(row.get("UNIT_ID")))));
			oInventory.setUnitName(oRemoveNull.nullRemove((String.valueOf(row.get("UNIT_NAME")))));
			oInventory.setPrice(oRemoveNull.nullRemove((String.valueOf(row.get("PRICE_AMOUNT")))));
			oInventory.setUpdateDate(oRemoveNull.nullRemove((String.valueOf(row.get("UPDATE_DATE")))));
			oInventory.setWastageDate(oRemoveNull.nullRemove((String.valueOf(row.get("WASTAGE_DATE")))));
			oInventory.setWastageSysdateYn(oRemoveNull.nullRemove((String.valueOf(row.get("WASTAGE_SYSDATE_YN")))));
			oInventory.setEmployeeId(oRemoveNull.nullRemove((String.valueOf(row.get("UPDATE_BY")))));
			oWastageViewList.add(oInventory);
		}
		return oWastageViewList;
	}

	public Inventory getWastageInfo(Inventory inventory) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		Inventory oInventory = new Inventory();
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append(" SELECT WASTAGE_ID, ");
		sBuilder.append(" INGREDIENTS_ID, ");
		sBuilder.append(" INVENTORY_TYPE_ID, ");
		sBuilder.append(" ITEM_ID, ");
		sBuilder.append(" QUANTITY, ");
		sBuilder.append(" UNIT_ID, ");
		sBuilder.append(" PRICE_AMOUNT, ");
		sBuilder.append(" UPDATE_BY, ");
		sBuilder.append(" UPDATE_DATE ");
		sBuilder.append(" FROM WASTAGE ");
		sBuilder.append(" WHERE WASTAGE_ID = :encWastageId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("encWastageId", oCipherUtils.decrypt(inventory.getEncWastageId()));

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oInventory.setEncWastageId(oCipherUtils.encrypt(String.valueOf(row.get("WASTAGE_ID"))));
			oInventory.setInventoryTypeId(oRemoveNull.nullRemove((String.valueOf(row.get("INVENTORY_TYPE_ID")))));
			oInventory.setProductId(oRemoveNull.nullRemove(String.valueOf(row.get("INGREDIENTS_ID"))));
			oInventory.setItemId(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_ID"))));
			oInventory.setQuantity(oRemoveNull.nullRemove(String.valueOf(row.get("QUANTITY"))));
			oInventory.setUnitId(oRemoveNull.nullRemove(String.valueOf(row.get("UNIT_ID"))));
			oInventory.setPrice(oRemoveNull.nullRemove(String.valueOf(row.get("PRICE_AMOUNT"))));
		}

		return oInventory;
	}

	public Inventory saveKitchenIngredients(Inventory inventory) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		Inventory oInventory = new Inventory();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_KITCHEN_INGREDIENTS_SAVE");
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			
			inParamMap.put("P_INVENTORY_ID", oCipherUtils.decrypt(oRemoveNull.nullRemove(inventory.getEncInventoryId())));
			inParamMap.put("P_INVENTORY_TYPE_ID", inventory.getInventoryTypeId());
			inParamMap.put("P_PRODUCT_ID", inventory.getProductId());
			inParamMap.put("P_UNIT_ID", inventory.getUnitId());
			inParamMap.put("P_UNIT_PRICE", inventory.getUnitPrice());
			inParamMap.put("P_QUANTITY", inventory.getQuantity());
			inParamMap.put("P_PRICE", inventory.getPrice());
			inParamMap.put("P_ADVANCE_AMOUNT", inventory.getAdvanceAmount());
			inParamMap.put("P_EMPLOYEE_ID", inventory.getEmployeeId());
			inParamMap.put("P_UPDATE_BY", inventory.getUpdateBy());
			inParamMap.put("P_SUPPLIER_ID", inventory.getSupplierId());
			
			System.out.println("inventory.getUnitPrice() " + inventory.getUnitPrice());
			System.out.println("inventory.getPrice() " + inventory.getPrice());
			System.out.println("inventory.getAdvanceAmount() " + inventory.getAdvanceAmount());
			

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oInventory.setMessage((String) outParamMap.get("P_MESSAGE"));
			oInventory.setmCode((String) outParamMap.get("P_MESSAGE_CODE"));
			//oInventory.setInventoryDate((String) outParamMap.get("O_INVENTORY_DATE"));
			//System.out.println("studentId " + oStudent.getStudeP_INVENTORY_IDntId());
		} catch (Exception ex) {
			oInventory.setMessage("Error Saving Record !!!");
			oInventory.setmCode("0000");
			ex.printStackTrace();
		}
		return oInventory;

	}

	public Inventory saveStoreIngredients(Inventory inventory) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		Inventory oInventory = new Inventory();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_STORE_INGREDIENTS_SAVE");
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			
			inParamMap.put("P_STORE_ID", oCipherUtils.decrypt(oRemoveNull.nullRemove(inventory.getEncStoreId())));
			inParamMap.put("P_INVENTORY_TYPE_ID", inventory.getInventoryTypeId());
			inParamMap.put("P_PRODUCT_ID", inventory.getProductId());
			inParamMap.put("P_UNIT_ID", inventory.getUnitId());
			//inParamMap.put("P_UNIT_PRICE", inventory.getUnitPrice());
			inParamMap.put("P_QUANTITY", inventory.getQuantity());
			//inParamMap.put("P_PRICE", inventory.getPrice());
			inParamMap.put("P_ADVANCE_AMOUNT", inventory.getAdvanceAmount());
			//inParamMap.put("P_EMPLOYEE_ID", inventory.getEmployeeId());
			inParamMap.put("P_UPDATE_BY", inventory.getUpdateBy());
			inParamMap.put("P_SUPPLIER_ID", inventory.getSupplierId());
			
			System.out.println("inventory.getUnitPrice() " + inventory.getUnitPrice());
			System.out.println("inventory.getPrice() " + inventory.getPrice());
			System.out.println("inventory.getAdvanceAmount() " + inventory.getAdvanceAmount());
			

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oInventory.setMessage((String) outParamMap.get("P_MESSAGE"));
			oInventory.setmCode((String) outParamMap.get("P_MESSAGE_CODE"));
			//oInventory.setInventoryDate((String) outParamMap.get("O_INVENTORY_DATE"));
			//System.out.println("studentId " + oStudent.getStudeP_INVENTORY_IDntId());
		} catch (Exception ex) {
			oInventory.setMessage("Error Saving Record !!!");
			oInventory.setmCode("0000");
			ex.printStackTrace();
		}
		return oInventory;
	}

	public List<Inventory> getStoreInventoryList(Inventory inventory) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Inventory> oInventoryList = new ArrayList<Inventory>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT STORE_ID, ");
		sBuilder.append(" TO_CHAR (STORE_DATE, 'DD/MM/YYYY') STORE_DATE, ");
		sBuilder.append(" (SELECT INVENTORY_TYPE_NAME FROM L_INVENTORY_TYPE WHERE INVENTORY_TYPE_ID = M.INVENTORY_TYPE_ID) INVENTORY_TYPE_NAME, ");
		sBuilder.append(" (SELECT PRODUCT_NAME FROM L_PRODUCT WHERE PRODUCT_ID = M.PRODUCT_ID) PRODUCT_NAME, ");
		sBuilder.append(" (SELECT UNIT_NAME FROM L_UNIT WHERE UNIT_ID = M.UNIT_ID) UNIT_NAME, ");
		sBuilder.append(" QUANTITY, ");
		sBuilder.append(" SMALL_UNIT, ");
		sBuilder.append(" UPDATE_BY, ");
		sBuilder.append(" TO_CHAR (UPDATE_DATE, 'DD/MM/YYYY') UPDATE_DATE, ");
		sBuilder.append(" CASE WHEN TRUNC(STORE_DATE) < TRUNC(SYSDATE) THEN 'N' ELSE 'Y' END STORE_DATE_YN ");
		sBuilder.append(" FROM STORE_MANAGEMENT M ");
		sBuilder.append(" WHERE TRUNC (STORE_DATE) = TRUNC (SYSDATE) ");
		//sBuilder.append(" AND INVENTORY_TYPE_ID NOT IN (206) ");
		//sBuilder.append(" AND TRUNC(INVENTORY_DATE) = TO_DATE(:inventoryDate,'DD/MM/YYYY') ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		//paramSource.addValue("inventoryTypeId", inventory.getInventoryTypeId());
		//paramSource.addValue("inventoryDate", inventory.getInventoryDate());
		
		sBuilder.append(" ORDER BY STORE_DATE DESC ");
		
		//System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			Inventory oInventory = new Inventory();
			oInventory.setEncStoreId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("STORE_ID")))));
			oInventory.setStoreDate(oRemoveNull.nullRemove(String.valueOf(row.get("STORE_DATE"))));
			oInventory.setInventoryTypeName(oRemoveNull.nullRemove(String.valueOf(row.get("INVENTORY_TYPE_NAME"))));
			oInventory.setProductName(oRemoveNull.nullRemove(String.valueOf(row.get("PRODUCT_NAME"))));
			oInventory.setUnitName(oRemoveNull.nullRemove(String.valueOf(row.get("UNIT_NAME"))));
			oInventory.setQuantity(oRemoveNull.nullRemove(String.valueOf(row.get("QUANTITY"))));
			oInventory.setSmallUnit(oRemoveNull.nullRemove(String.valueOf(row.get("SMALL_UNIT"))));
			oInventory.setUpdateBy(oRemoveNull.nullRemove(String.valueOf(row.get("UPDATE_BY"))));
			oInventory.setUpdateDate(oRemoveNull.nullRemove(String.valueOf(row.get("UPDATE_DATE"))));
			oInventory.setStoreDateYn(oRemoveNull.nullRemove(String.valueOf(row.get("STORE_DATE_YN"))));
			oInventoryList.add(oInventory);
		}
		return oInventoryList;
	}

	public Inventory getStoredIngredientsInfo(Inventory inventory) throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
		Inventory oInventory = new Inventory();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT STORE_ID, ");
		sBuilder.append(" STORE_DATE, ");
		sBuilder.append(" INVENTORY_TYPE_ID, ");
		sBuilder.append(" PRODUCT_ID, ");
		sBuilder.append(" UNIT_ID, ");
		sBuilder.append(" QUANTITY, ");
		sBuilder.append(" SMALL_UNIT, ");
		sBuilder.append(" UPDATE_BY, ");
		sBuilder.append(" UPDATE_DATE ");
		sBuilder.append(" FROM STORE_MANAGEMENT ");
		sBuilder.append(" WHERE STORE_ID = :encStoreId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("encStoreId", oCipherUtils.decrypt(oRemoveNull.nullRemove(inventory.getEncStoreId())));
		
		System.out.println("encStoreId "+ oCipherUtils.decrypt(oRemoveNull.nullRemove(inventory.getEncStoreId())));

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oInventory.setEncStoreId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("STORE_ID")))));
			oInventory.setStoreId(oRemoveNull.nullRemove(String.valueOf(row.get("STORE_ID"))));
			oInventory.setStoreDate(oRemoveNull.nullRemove(String.valueOf(row.get("STORE_DATE"))));
			oInventory.setInventoryTypeId(oRemoveNull.nullRemove(String.valueOf(row.get("INVENTORY_TYPE_ID"))));
			oInventory.setProductId(oRemoveNull.nullRemove(String.valueOf(row.get("PRODUCT_ID"))));
			oInventory.setUnitId(oRemoveNull.nullRemove(String.valueOf(row.get("UNIT_ID"))));
			oInventory.setQuantity(oRemoveNull.nullRemove(String.valueOf(row.get("QUANTITY"))));
			oInventory.setEmployeeId(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_ID"))));
		
		}
		return oInventory;
	}
	
	


	@Override
	public List<Inventory> getStoreHistoryList(Inventory inventory) throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Inventory> oStoreHistoryList = new ArrayList<Inventory>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT STORE_ID, ");
		sBuilder.append(" TO_CHAR (STORE_DATE, 'DD/MM/YYYY') STORE_DATE, ");
		sBuilder.append(" (SELECT INVENTORY_TYPE_NAME FROM L_INVENTORY_TYPE WHERE INVENTORY_TYPE_ID = M.INVENTORY_TYPE_ID) INVENTORY_TYPE_NAME, ");
		sBuilder.append(" (SELECT PRODUCT_NAME FROM L_PRODUCT WHERE PRODUCT_ID = M.PRODUCT_ID) PRODUCT_NAME, ");
		sBuilder.append(" (SELECT UNIT_NAME FROM L_UNIT WHERE UNIT_ID = M.UNIT_ID) UNIT_NAME, ");
		sBuilder.append(" QUANTITY, ");
		sBuilder.append(" SMALL_UNIT, ");
		sBuilder.append(" UPDATE_BY, ");
		sBuilder.append(" TO_CHAR (UPDATE_DATE, 'DD/MM/YYYY') UPDATE_DATE ");
		sBuilder.append(" FROM STORE_MANAGEMENT M ");
		sBuilder.append(" WHERE TRUNC (STORE_DATE) BETWEEN TO_DATE (:fromDate, 'dd/mm/yyyy') AND  TO_DATE (:toDate, 'dd/mm/yyyy') ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("fromDate", inventory.getFromDate());
		paramSource.addValue("toDate", inventory.getToDate());
		
		sBuilder.append(" ORDER BY STORE_DATE DESC ");
		
		//System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			Inventory oInventory = new Inventory();
			oInventory.setEncStoreId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("STORE_ID")))));
			oInventory.setStoreDate(oRemoveNull.nullRemove(String.valueOf(row.get("STORE_DATE"))));
			oInventory.setInventoryTypeName(oRemoveNull.nullRemove(String.valueOf(row.get("INVENTORY_TYPE_NAME"))));
			oInventory.setProductName(oRemoveNull.nullRemove(String.valueOf(row.get("PRODUCT_NAME"))));
			oInventory.setUnitName(oRemoveNull.nullRemove(String.valueOf(row.get("UNIT_NAME"))));
			oInventory.setQuantity(oRemoveNull.nullRemove(String.valueOf(row.get("QUANTITY"))));
			oInventory.setSmallUnit(oRemoveNull.nullRemove(String.valueOf(row.get("SMALL_UNIT"))));
			oInventory.setUpdateBy(oRemoveNull.nullRemove(String.valueOf(row.get("UPDATE_BY"))));
			oInventory.setUpdateDate(oRemoveNull.nullRemove(String.valueOf(row.get("UPDATE_DATE"))));
			oInventory.setStoreDateYn(oRemoveNull.nullRemove(String.valueOf(row.get("STORE_DATE_YN"))));
			oStoreHistoryList.add(oInventory);
		}
		return oStoreHistoryList;
	}

}
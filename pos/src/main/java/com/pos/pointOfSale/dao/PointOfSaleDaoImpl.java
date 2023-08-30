package com.pos.pointOfSale.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
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
import com.pos.accounts.model.OwnerConsumptionInfo;
import com.pos.admin.model.UserInfoForm;
import com.pos.common.RemoveNull;
import com.pos.pointOfSale.model.OrderModel;
import com.pos.pointOfSale.model.PointOfSale;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

@Repository("PointOfSaleDao")
public class PointOfSaleDaoImpl implements PointOfSaleDao {

	private SimpleJdbcCall simpleJdbcCall;
	private JdbcTemplate jdbcTemplate;
	CipherUtils oCipherUtils = new CipherUtils();
	RemoveNull oRemoveNull = new RemoveNull();
	@Autowired
	private DataSource dataSource;

	public PointOfSale saveItem(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		PointOfSale oPointOfSale = new PointOfSale();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_ITEM_SAVE");
			Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("P_ITEM_ID", oCipherUtils.decrypt(pointOfSale.getEncItemId()));
			inParamMap.put("P_ITEM_NAME", pointOfSale.getItemName());
			inParamMap.put("P_ITEM_PRICE", pointOfSale.getItemPrice());
			inParamMap.put("P_UPDATE_BY", pointOfSale.getUpdateBy());
			inParamMap.put("P_UPDATE_DATE", pointOfSale.getUpdateDate());

			// inParamMap.put("P_UPDATE_DATE", student.getUpdateDate());

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oPointOfSale.setMessage((String) outParamMap.get("P_MESSAGE"));
			oPointOfSale.setmCode((String) outParamMap.get("P_MESSAGE_CODE"));
			// System.out.println("studentId " + oStudent.getStudentId());
		} catch (Exception ex) {
			oPointOfSale.setMessage("Error Saving Record !!!");
			oPointOfSale.setmCode("0000");
			ex.printStackTrace();
		}
		return oPointOfSale;
	}

	public List<PointOfSale> getItemList(PointOfSale pointOfSale) throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<PointOfSale> oItemList = new ArrayList<PointOfSale>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT ITEM_ID, ITEM_CODE, ITEM_NAME, ITEM_PRICE ");
		sBuilder.append(" FROM L_ITEM ");
		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		//System.out.println("bbb" + sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			PointOfSale oPointOfSale = new PointOfSale();
			oPointOfSale.setEncItemId(oCipherUtils.encrypt(String.valueOf(row.get("ITEM_ID"))));
			oPointOfSale.setItemId(String.valueOf(row.get("ITEM_ID")));
			oPointOfSale.setItemCode(String.valueOf(row.get("ITEM_CODE")));
			oPointOfSale.setItemName(String.valueOf(row.get("ITEM_NAME")));
			oPointOfSale.setItemPrice(String.valueOf(row.get("ITEM_PRICE")));
			oItemList.add(oPointOfSale);

			// System.out.println("abc"+
			// oCipherUtils.encrypt(String.valueOf(row.get("EMPLOYEE_ID"))));

		}

		return oItemList;
	}

	public PointOfSale saveItemConfig(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		PointOfSale oPointOfSale = new PointOfSale();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_ITEM_CONFIG_SAVE");
			Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("P_ENC_ITEM_ID", oCipherUtils.decrypt(oRemoveNull.nullRemove(pointOfSale.getEncItemId())));
			inParamMap.put("P_ITEM_ID", pointOfSale.getItemId());
			inParamMap.put("P_PRODUCT_ID", pointOfSale.getProductId());
			inParamMap.put("P_QUANTITY", pointOfSale.getQuantity());
			inParamMap.put("P_UNIT_ID", pointOfSale.getUnitId());
			inParamMap.put("P_UPDATE_BY", pointOfSale.getUpdateBy());
			inParamMap.put("P_UPDATE_DATE", pointOfSale.getUpdateDate());
			
			//System.out.println("pointOfSale.getEncItemId() =  " + oCipherUtils.decrypt(pointOfSale.getEncItemId()));


			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oPointOfSale.setMessage((String) outParamMap.get("P_MESSAGE"));
			oPointOfSale.setmCode((String) outParamMap.get("P_MESSAGE_CODE"));
			
		} catch (Exception ex) {
			oPointOfSale.setMessage("Error Saving Record !!!");
			oPointOfSale.setmCode("0000");
			ex.printStackTrace();
		}
		return oPointOfSale;
	}

	public List<PointOfSale> getItemConfigList(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<PointOfSale> oItemConfigList = new ArrayList<PointOfSale>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT ITEM_ID, ");
		sBuilder.append(" M.PRODUCT_ID, ");
		sBuilder.append(" (SELECT PRODUCT_NAME FROM L_PRODUCT WHERE PRODUCT_ID = M.PRODUCT_ID) PRODUCT_NAME, ");
		sBuilder.append(" QUANTITY, ");
		sBuilder.append(" M.UNIT_ID, ");
		sBuilder.append(" (SELECT UNIT_NAME FROM L_UNIT WHERE UNIT_ID = M.UNIT_ID) UNIT_NAME ");
		sBuilder.append(" FROM ITEM_CONFIG M ");
		sBuilder.append(" WHERE ITEM_ID = :itemId ");
		// sBuilder.append(" AND TRUNC(UPDATE_DATE) =
		// TO_DATE(:updateDate,'DD/MM/YYYY') ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("itemId", pointOfSale.getItemId());
		// paramSource.addValue("updateDate", pointOfSale.getUpdateDate());

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			PointOfSale oPointOfSale = new PointOfSale();
			oPointOfSale.setEncItemId(oCipherUtils.encrypt(String.valueOf(row.get("ITEM_ID"))));
			oPointOfSale.setItemId(String.valueOf(row.get("ITEM_ID")));
			oPointOfSale.setProductId(String.valueOf(row.get("PRODUCT_ID")));
			oPointOfSale.setProductName(String.valueOf(row.get("PRODUCT_NAME")));
			oPointOfSale.setQuantity(String.valueOf(row.get("QUANTITY")));
			oPointOfSale.setUnitId(String.valueOf(row.get("UNIT_ID")));
			oPointOfSale.setUnitName(String.valueOf(row.get("UNIT_NAME")));
			oItemConfigList.add(oPointOfSale);

			// System.out.println("abc"+
			// oCipherUtils.encrypt(String.valueOf(row.get("EMPLOYEE_ID"))));

		}

		return oItemConfigList;
	}

	public PointOfSale getOrderInfo(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		PointOfSale oOrderInfo = new PointOfSale();
		ArrayList list = new ArrayList();
		try {
			Connection conn = jdbcTemplate.getDataSource().getConnection();

			CallableStatement oCallStmt = conn.prepareCall("{call PRO_ORDER_INFO(?,?,?,?,?,?,?)}");
			oCallStmt.setString(1, pointOfSale.getItemId());
			oCallStmt.setString(2, pointOfSale.getQuantity());
			oCallStmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			oCallStmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			oCallStmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			//oCallStmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			oCallStmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			oCallStmt.registerOutParameter(7, java.sql.Types.VARCHAR);

			oCallStmt.execute();
			

			oOrderInfo.setmCode(oCallStmt.getString(3));
			oOrderInfo.setMessage(oCallStmt.getString(4));
			oOrderInfo.setItemName(oCallStmt.getString(5));
			oOrderInfo.setItemPrice(oCallStmt.getString(6));
			oOrderInfo.setSubTotal(oCallStmt.getString(7));
			//oOrderInfo.setSubTotal(oCallStmt.getString(5));

/*			System.out.println("3 " + oOrderInfo.getItemName());
			System.out.println("4 " + oOrderInfo.getItemPrice());
			System.out.println("5 " + oOrderInfo.getSubTotal());*/

			/*
			 * try { simpleJdbcCall = new
			 * SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_ORDER_INFO");
			 * Map<String, Object> inParamMap = new HashMap<String, Object>();
			 * 
			 * inParamMap.put("P_ITEM_ID", pointOfSale.getItemId());
			 * inParamMap.put("P_QUANTITY",
			 * Integer.parseInt(pointOfSale.getQuantity()));
			 * 
			 * // inParamMap.put("P_UPDATE_DATE", student.getUpdateDate());
			 * 
			 * Map<String, Object> outParamMap = simpleJdbcCall.execute(new
			 * MapSqlParameterSource().addValues(inParamMap));
			 * 
			 * oOrderInfo.setMessage((String) outParamMap.get("P_MESSAGE"));
			 * oOrderInfo.setmCode((String) outParamMap.get("P_MESSAGE_CODE"));
			 * oOrderInfo.setOrderPrice(()(outParamMap.get("P_ORDER_PRICE")));
			 * 
			 * //oReceiptInfo.setAmountWithoutVat(Integer.toString(oCallStmt.
			 * getInt(42)));
			 * 
			 * System.out.println("price " + oOrderInfo.getItemPrice());
			 */
		} catch (Exception ex) {
			oOrderInfo.setMessage("Error Creating Order !");
			oOrderInfo.setmCode("0000");
			ex.printStackTrace();
		}

		return oOrderInfo;
	}

	/*public PointOfSale saveOrder(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);

		PointOfSale oPointOfSale = new PointOfSale();

		try {
			Connection conn = jdbcTemplate.getDataSource().getConnection();
			// CallableStatement oCallStmt = null;
			// String sResult = "";

			*//*****************************
			 * Receipt Data Manipulation
			 ********************************//*

			StructDescriptor orderRecDescriptor = StructDescriptor.createDescriptor("ORDERARRAY", conn);
			ArrayDescriptor orderArrayDescriptor = ArrayDescriptor.createDescriptor("ORDERARRAYTAB", conn);

			List<OrderModel> orderList = pointOfSale.getOrderModelList();

			int orderArraySize = 0;

			if (orderList != null && orderList.size() > 0) {
				for (OrderModel oOrderModel : orderList) {
					if ((oOrderModel.getItemId() != null && oOrderModel.getItemId().length() > 0)
							&& (oOrderModel.getQuantity() != null && oOrderModel.getQuantity().length() > 0)
							&& (oOrderModel.getItemPrice() != null && oOrderModel.getItemPrice().length() > 0)
							&& (oOrderModel.getSubTotal() != null && oOrderModel.getSubTotal().length() > 0)) {
						orderArraySize = orderArraySize + 1;
						
						 System.out.println("getEncItemOrderId() DE :  "+ oCipherUtils.decrypt(oRemoveNull.nullRemove(oOrderModel.getEncItemOrderId())));
					}
				}
			}
			
			if (orderArraySize == 0) {
				oPointOfSale.setMessage("No Order Found !!");
				oPointOfSale.setmCode("0000");
				return oPointOfSale;
			}
			
			if (orderArraySize > 10) {
				oPointOfSale.setMessage("Maximum 10 Orders Can Be Generated In One Receipt !!");
				oPointOfSale.setmCode("0000");
				return oPointOfSale;
			}

			//System.out.println("orderArraySize = " + orderArraySize);

			Object[] order_array_of_records = new Object[orderArraySize];

			Object[] order_java_record_array = new Object[5];
			int i = 0;

			if (null != orderList && orderList.size() > 0) {
				for (OrderModel oOrderModel : orderList) {
					if ((oOrderModel.getItemId() != null && oOrderModel.getItemId().length() > 0)
							&& (oOrderModel.getQuantity() != null && oOrderModel.getQuantity().length() > 0)
							&& (oOrderModel.getItemPrice() != null && oOrderModel.getItemPrice().length() > 0)
							&& (oOrderModel.getSubTotal() != null && oOrderModel.getSubTotal().length() > 0)) {

						// We create a record by filling an array and then casting it into an Oracle type
						order_java_record_array[0] = oCipherUtils.decrypt(oRemoveNull.nullRemove(oOrderModel.getEncItemOrderId()));
						order_java_record_array[1] = oOrderModel.getItemId();
						order_java_record_array[2] = oOrderModel.getQuantity();
						order_java_record_array[3] = oOrderModel.getItemPrice();
						order_java_record_array[4] = oOrderModel.getSubTotal();
						
						 System.out.println("oOrderModel.getEncItemOrderId() DECR :  "+ oCipherUtils.decrypt(oRemoveNull.nullRemove(oOrderModel.getEncItemOrderId())));

						// cast the java arrays into the Oracle record type for the input record
						STRUCT oracle_record = new STRUCT(orderRecDescriptor, conn, order_java_record_array);
						// store the oracle_record into the array of records which will be passed to the function
						order_array_of_records[i] = oracle_record;
						i = i + 1;
						
						 * System.out.println("java_record_array[0] " +
						 * jointOwner_java_record_array[0]); System.out.println(
						 * "java_record_array[1] " +
						 * jointOwner_java_record_array[1]); System.out.println(
						 * "java_record_array[2] " +
						 * jointOwner_java_record_array[2]); System.out.println(
						 * "java_record_array[3] " +
						 * jointOwner_java_record_array[3]); System.out.println(
						 * "java_record_array[4] " +
						 * jointOwner_java_record_array[4]); System.out.println(
						 * "java_record_array[5] " +
						 * jointOwner_java_record_array[5]); System.out.println(
						 * "java_record_array[6] " +
						 * jointOwner_java_record_array[6]); System.out.println(
						 * "java_record_array[7] " +
						 * jointOwner_java_record_array[7]);
						 
						// System.out.println("java_record_array[0] " +
						// java_record_array[0]);
					}

				}
			}

			ARRAY order_array = new ARRAY(orderArrayDescriptor, conn, order_array_of_records);

			CallableStatement oCallStmt = dataSource.getConnection().prepareCall(
					"{call PRO_ORDER_SAVE(?,?,?,?,?,?,?,?,?)}");
			
			oCallStmt.setObject(1, oCipherUtils.decrypt(oRemoveNull.nullRemove(pointOfSale.getEncOrderId())));
			oCallStmt.setObject(2,order_array);
			oCallStmt.setObject(3, pointOfSale.getTableNo());
			oCallStmt.setObject(4, pointOfSale.getEmployeeId());
			oCallStmt.setObject(5, pointOfSale.getUpdateBy());
			oCallStmt.setObject(6, pointOfSale.getOrderNote());
			oCallStmt.setObject(7, pointOfSale.getOwnerFoodYn());
			oCallStmt.registerOutParameter(8, java.sql.Types.CHAR);
			oCallStmt.registerOutParameter(9, java.sql.Types.CHAR);

			oCallStmt.execute();

			oPointOfSale.setmCode(oCallStmt.getString(8));
			oPointOfSale.setMessage(oCallStmt.getString(9));

		} catch (Exception e) {
			e.printStackTrace();
			oPointOfSale.setMessage("ERROR SAVING RECORD !!!" + e);
			oPointOfSale.setmCode("0000");
		}
		return oPointOfSale;
	}*/
	
	public PointOfSale saveOrder(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);

		PointOfSale oPointOfSale = new PointOfSale();

		try {
			Connection conn = jdbcTemplate.getDataSource().getConnection();
			// CallableStatement oCallStmt = null;
			// String sResult = "";

			/*****************************
			 * Receipt Data Manipulation
			 ********************************/

			StructDescriptor orderRecDescriptor = StructDescriptor.createDescriptor("ORDERARRAY", conn);
			ArrayDescriptor orderArrayDescriptor = ArrayDescriptor.createDescriptor("ORDERARRAYTAB", conn);

			List<OrderModel> orderList = pointOfSale.getOrderModelList();

			int orderArraySize = 0;

			if (orderList != null && orderList.size() > 0) {
				for (OrderModel oOrderModel : orderList) {
					if ((oOrderModel.getItemId() != null && oOrderModel.getItemId().length() > 0)
							&& (oOrderModel.getQuantity() != null && oOrderModel.getQuantity().length() > 0)
							&& (oOrderModel.getItemPrice() != null && oOrderModel.getItemPrice().length() > 0)
							&& (oOrderModel.getSubTotal() != null && oOrderModel.getSubTotal().length() > 0)) {
						orderArraySize = orderArraySize + 1;
						
						 System.out.println("getEncItemOrderId() DE :  "+ oCipherUtils.decrypt(oRemoveNull.nullRemove(oOrderModel.getEncItemOrderId())));
					}
				}
			}
			
			if (orderArraySize == 0) {
				oPointOfSale.setMessage("No Order Found !!");
				oPointOfSale.setmCode("0000");
				return oPointOfSale;
			}
			
			/*if (orderArraySize > 10) {
				oPointOfSale.setMessage("Maximum 10 Orders Can Be Generated In One Receipt !!");
				oPointOfSale.setmCode("0000");
				return oPointOfSale;
			}*/

			System.out.println("orderArraySize = " + orderArraySize);

			Object[] order_array_of_records = new Object[orderArraySize];

			Object[] order_java_record_array = new Object[6];
			int i = 0;

			if (null != orderList && orderList.size() > 0) {
				for (OrderModel oOrderModel : orderList) {
					if ((oOrderModel.getItemId() != null && oOrderModel.getItemId().length() > 0)
							&& (oOrderModel.getQuantity() != null && oOrderModel.getQuantity().length() > 0)
							&& (oOrderModel.getItemPrice() != null && oOrderModel.getItemPrice().length() > 0)
							&& (oOrderModel.getSubTotal() != null && oOrderModel.getSubTotal().length() > 0)) {

						// We create a record by filling an array and then casting it into an Oracle type
						order_java_record_array[0] = oCipherUtils.decrypt(oRemoveNull.nullRemove(oOrderModel.getEncItemOrderId()));
						order_java_record_array[1] = oOrderModel.getItemId();
						order_java_record_array[2] = oOrderModel.getQuantity();
						order_java_record_array[3] = oOrderModel.getItemPrice();
						order_java_record_array[4] = oOrderModel.getSubTotal();
						order_java_record_array[5] = oOrderModel.getOrderNote();
						
						 System.out.println("oOrderModel.getEncItemOrderId() DECR :  "+ oCipherUtils.decrypt(oRemoveNull.nullRemove(oOrderModel.getEncItemOrderId())));

						// cast the java arrays into the Oracle record type for the input record
						STRUCT oracle_record = new STRUCT(orderRecDescriptor, conn, order_java_record_array);
						// store the oracle_record into the array of records which will be passed to the function
						order_array_of_records[i] = oracle_record;
						i = i + 1;
						/*
						 * System.out.println("java_record_array[0] " +
						 * jointOwner_java_record_array[0]); System.out.println(
						 * "java_record_array[1] " +
						 * jointOwner_java_record_array[1]); System.out.println(
						 * "java_record_array[2] " +
						 * jointOwner_java_record_array[2]); System.out.println(
						 * "java_record_array[3] " +
						 * jointOwner_java_record_array[3]); System.out.println(
						 * "java_record_array[4] " +
						 * jointOwner_java_record_array[4]); System.out.println(
						 * "java_record_array[5] " +
						 * jointOwner_java_record_array[5]); System.out.println(
						 * "java_record_array[6] " +
						 * jointOwner_java_record_array[6]); System.out.println(
						 * "java_record_array[7] " +
						 * jointOwner_java_record_array[7]);
						 */
						// System.out.println("java_record_array[0] " +
						// java_record_array[0]);
					}

				}
			}

			ARRAY order_array = new ARRAY(orderArrayDescriptor, conn, order_array_of_records);

			CallableStatement oCallStmt = dataSource.getConnection().prepareCall(
					"{call PRO_ORDER_SAVE(?,?,?,?,?,?,?,?,?)}");
			
			oCallStmt.setObject(1, oCipherUtils.decrypt(oRemoveNull.nullRemove(pointOfSale.getEncOrderId())));
			oCallStmt.setObject(2,order_array);
			oCallStmt.setObject(3, pointOfSale.getTableNo());
			oCallStmt.setObject(4, pointOfSale.getEmployeeId());
			oCallStmt.setObject(5, pointOfSale.getUpdateBy());
			oCallStmt.setObject(6, pointOfSale.getOrderNote());
			oCallStmt.setObject(7, pointOfSale.getOwnerFoodYn());
			oCallStmt.registerOutParameter(8, java.sql.Types.CHAR);
			oCallStmt.registerOutParameter(9, java.sql.Types.CHAR);

			oCallStmt.execute();

			oPointOfSale.setmCode(oCallStmt.getString(8));
			oPointOfSale.setMessage(oCallStmt.getString(9));

		} catch (Exception e) {
			e.printStackTrace();
			oPointOfSale.setMessage("ERROR SAVING RECORD !!!" + e);
			oPointOfSale.setmCode("0000");
		}
		return oPointOfSale;
	}

	public PointOfSale getOrderPrice(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		PointOfSale oPointOfSale = new PointOfSale();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append("SELECT SUM (ITEM_PRICE) ITEM_PRICE FROM ORDER_MANAGEMENT WHERE ORDER_ID = :orderId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("orderId", pointOfSale.getOrderId());

		 //System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oPointOfSale.setItemPrice(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_PRICE"))));
		}
		return oPointOfSale;
	}

	public PointOfSale saveDiscount(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		PointOfSale oPointOfSale = new PointOfSale();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_DISCOUNT_SAVE");
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("P_DISCOUNT_ID", oCipherUtils.decrypt(pointOfSale.getEncDiscountId()));
			inParamMap.put("P_ORDER_NO", pointOfSale.getOrderId());
			inParamMap.put("P_PRICE_AMOUNT", pointOfSale.getOrderPrice());
			inParamMap.put("P_DISCOUNT_AMOUNT", pointOfSale.getDiscountAmount());
			inParamMap.put("P_UPDATE_BY", pointOfSale.getUpdateBy());
			inParamMap.put("P_REFERENCE_BY_ID", pointOfSale.getReferenceById());

			// inParamMap.put("P_UPDATE_DATE", student.getUpdateDate());

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oPointOfSale.setMessage((String) outParamMap.get("P_MESSAGE"));
			oPointOfSale.setmCode((String) outParamMap.get("P_MESSAGE_CODE"));
			// System.out.println("studentId " + oStudent.getStudentId());
		} catch (Exception ex) {
			oPointOfSale.setMessage("Error Saving Record !!!");
			oPointOfSale.setmCode("0000");
			ex.printStackTrace();
		}
		return oPointOfSale;
	}

	public List<PointOfSale> getDiscountList(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<PointOfSale> oDiscountList = new ArrayList<PointOfSale>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT DISCOUNT_ID, ");
		sBuilder.append(" ORDER_ID, ");
		sBuilder.append(" PRICE_AMOUNT, ");
		sBuilder.append(" DISCOUNT_AMOUNT, ");
		sBuilder.append(" UPDATE_BY, ");
		sBuilder.append(" TO_CHAR(UPDATE_DATE,'DD/MM/YYYY') UPDATE_DATE, ");
		sBuilder.append(" REFERENCE_BY, ");
		sBuilder.append(" (SELECT EMPLOYEE_NAME FROM EMPLOYEE WHERE EMPLOYEE_ID = D.REFERENCE_BY) REFERENCE_BY_NAME ");
		sBuilder.append(" FROM DISCOUNT D ");
		sBuilder.append(" WHERE TRUNC (UPDATE_DATE) BETWEEN TO_DATE (:fromDate, 'dd/mm/yyyy') AND  TO_DATE (:toDate, 'dd/mm/yyyy') ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("fromDate", pointOfSale.getFromDate());
		paramSource.addValue("toDate", pointOfSale.getToDate());
		
		if (pointOfSale.getOrderId() != null && pointOfSale.getOrderId().length() > 0) {
			sBuilder.append(" AND ORDER_ID = :orderId ");
			paramSource.addValue("orderId", pointOfSale.getOrderId());
		} 
		if(pointOfSale.getReferenceById() != null && pointOfSale.getReferenceById().length() > 0) {
			sBuilder.append(" AND REFERENCE_BY = :referenceById ");
			paramSource.addValue("referenceById", pointOfSale.getReferenceById());
		} 
		//System.out.println(sBuilder);
		//System.out.println("productId " + inventory.getProductId());

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);
		
		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			PointOfSale oPointOfSale = new PointOfSale();
			oPointOfSale.setEncDiscountId(oRemoveNull.nullRemove(oCipherUtils.encrypt((String.valueOf(row.get("DISCOUNT_ID"))))));
			oPointOfSale.setDiscountId(oRemoveNull.nullRemove((String.valueOf(row.get("DISCOUNT_ID")))));
			oPointOfSale.setEncOrderId(oRemoveNull.nullRemove(oCipherUtils.encrypt((String.valueOf(row.get("ORDER_ID"))))));
			oPointOfSale.setOrderId(oRemoveNull.nullRemove((String.valueOf(row.get("ORDER_ID")))));
			oPointOfSale.setOrderPrice(oRemoveNull.nullRemove((String.valueOf(row.get("PRICE_AMOUNT")))));
			oPointOfSale.setDiscountAmount(oRemoveNull.nullRemove((String.valueOf(row.get("DISCOUNT_AMOUNT")))));
			oPointOfSale.setUpdateDate(oRemoveNull.nullRemove((String.valueOf(row.get("UPDATE_DATE")))));
			oPointOfSale.setUpdateBy(oRemoveNull.nullRemove((String.valueOf(row.get("UPDATE_BY")))));
			oPointOfSale.setReferenceByName(oRemoveNull.nullRemove((String.valueOf(row.get("REFERENCE_BY_NAME")))));
			oDiscountList.add(oPointOfSale);
		}
		return oDiscountList;
	}

	public PointOfSale getDiscountInfo(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		PointOfSale oPointOfSale = new PointOfSale();
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append(" SELECT DISCOUNT_ID, ");
		sBuilder.append(" ORDER_ID, ");
		sBuilder.append(" PRICE_AMOUNT, ");
		sBuilder.append(" DISCOUNT_AMOUNT, ");
		sBuilder.append(" REFERENCE_BY ");
		sBuilder.append(" FROM DISCOUNT D ");
		sBuilder.append(" WHERE DISCOUNT_ID = :encDiscountId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("encDiscountId", oCipherUtils.decrypt(pointOfSale.getEncDiscountId()));

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oPointOfSale.setEncDiscountId(oCipherUtils.encrypt(String.valueOf(row.get("DISCOUNT_ID"))));
			oPointOfSale.setDiscountId(oRemoveNull.nullRemove(String.valueOf(row.get("DISCOUNT_ID"))));
			oPointOfSale.setOrderId(oRemoveNull.nullRemove(String.valueOf(row.get("ORDER_ID"))));
			oPointOfSale.setOrderPrice(oRemoveNull.nullRemove(String.valueOf(row.get("PRICE_AMOUNT"))));
			oPointOfSale.setDiscountAmount(oRemoveNull.nullRemove(String.valueOf(row.get("DISCOUNT_AMOUNT"))));
			oPointOfSale.setReferenceById(oRemoveNull.nullRemove(String.valueOf(row.get("REFERENCE_BY"))));
		}

		return oPointOfSale;
	}

	public List<PointOfSale> getOrderInfoList(PointOfSale pointOfSale) throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<PointOfSale> oOrderInfoList = new ArrayList<PointOfSale>();
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		StringBuilder sBuilder = new StringBuilder();

		try {
			sBuilder.append(" SELECT ITEM_ID, ");
			sBuilder.append(" (SELECT ITEM_NAME FROM L_ITEM WHERE ITEM_ID = OM.ITEM_ID) ITEM_NAME, ");
			sBuilder.append(" QUANTITY, ");
			sBuilder.append(" ITEM_PRICE ");
			sBuilder.append(" FROM ORDER_MANAGEMENT OM ");
			sBuilder.append(" WHERE ORDER_ID = :encOrderId ");

			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("encOrderId", oCipherUtils.decrypt(pointOfSale.getEncOrderId()));
			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);
			

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {

				PointOfSale oPointOfSale = new PointOfSale();
				oPointOfSale.setItemName(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_NAME"))));
				oPointOfSale.setQuantity(oRemoveNull.nullRemove(String.valueOf(row.get("QUANTITY"))));
				oPointOfSale.setItemPrice(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_PRICE"))));
				// oRegistrationRemoveNull.removeNullRegistration(oRegistration);
				oOrderInfoList.add(oPointOfSale);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return oOrderInfoList;
	}

	public List<PointOfSale> getOrderList(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<PointOfSale> oOrderList = new ArrayList<PointOfSale>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT ORDER_ID, ");
		sBuilder.append(" ORDER_NO, ");
		sBuilder.append(" TO_CHAR(ORDER_DATE,'DD/MM/YYYY HH12:MI:SS AM') ORDER_DATE, ");
		sBuilder.append(" TABLE_NO, ");
		sBuilder.append(" (SELECT KNOWN_AS FROM EMPLOYEE WHERE EMPLOYEE_ID = OM.WAITER_ID) SERVED_BY, ");
		sBuilder.append(" NET_PAY_AMOUNT, ");
		sBuilder.append(" (SELECT KNOWN_AS FROM EMPLOYEE WHERE EMPLOYEE_ID = OM.UPDATE_BY) UPDATE_BY, ");
		sBuilder.append(" TO_CHAR(UPDATE_DATE,'DD/MM/YYYY') UPDATE_DATE ");
		sBuilder.append(" FROM ORDER_MANAGEMENT OM ");
		sBuilder.append(" WHERE TRUNC (ORDER_DATE) BETWEEN TO_DATE (:fromDate, 'dd/mm/yyyy') AND  TO_DATE (:toDate, 'dd/mm/yyyy') ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("fromDate", pointOfSale.getFromDate());
		paramSource.addValue("toDate", pointOfSale.getToDate());
		
		if (pointOfSale.getOrderId() != null && pointOfSale.getOrderId().length() > 0) {
			sBuilder.append(" AND ORDER_ID = :orderId ");
			paramSource.addValue("orderId", pointOfSale.getOrderId());
		}
		if (pointOfSale.getUpdateBy() != null && pointOfSale.getUpdateBy().length() > 0) {
			sBuilder.append(" AND UPDATE_BY = :updateBy ");
			paramSource.addValue("updateBy", pointOfSale.getUpdateBy());
		}
		//System.out.println(sBuilder);
		//System.out.println("productId " + inventory.getProductId());

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);
		
		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			PointOfSale oPointOfSale = new PointOfSale();
			oPointOfSale.setEncOrderId(oRemoveNull.nullRemove(oCipherUtils.encrypt((String.valueOf(row.get("ORDER_ID"))))));
			oPointOfSale.setOrderId(oRemoveNull.nullRemove((String.valueOf(row.get("ORDER_ID")))));
			oPointOfSale.setOrderNo(oRemoveNull.nullRemove((String.valueOf(row.get("ORDER_NO")))));
			oPointOfSale.setOrderDate(oRemoveNull.nullRemove((String.valueOf(row.get("ORDER_DATE")))));
			oPointOfSale.setTableNo(oRemoveNull.nullRemove((String.valueOf(row.get("TABLE_NO")))));
			oPointOfSale.setServedBy(oRemoveNull.nullRemove((String.valueOf(row.get("SERVED_BY")))));
			oPointOfSale.setNetPayableAmount(oRemoveNull.nullRemove((String.valueOf(row.get("NET_PAY_AMOUNT")))));
			oPointOfSale.setUpdateDate(oRemoveNull.nullRemove((String.valueOf(row.get("UPDATE_DATE")))));
			oPointOfSale.setUpdateBy(oRemoveNull.nullRemove((String.valueOf(row.get("UPDATE_BY")))));
			oOrderList.add(oPointOfSale);
		}
		return oOrderList;
	}

	public List<PointOfSale> getPendingOrderList(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<PointOfSale> oOrderList = new ArrayList<PointOfSale>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT ORDER_ID, ");
		sBuilder.append(" ORDER_NO, ");
		sBuilder.append(" TABLE_NO, ");
		sBuilder.append(" TO_CHAR(ORDER_DATE,'DD/MM/YYYY HH12:MI:SS AM') ORDER_DATE, ");
		sBuilder.append(" WAITER_ID, ");
		sBuilder.append(" (SELECT KNOWN_AS FROM EMPLOYEE WHERE EMPLOYEE_ID = OM.WAITER_ID) WAITER_NAME, ");
		sBuilder.append(" FINALIZED_YN, ");
		sBuilder.append(" COMPLETED_YN, ");
		sBuilder.append(" OWNER_FOOD_YN, ");
		sBuilder.append(" BILL_PRINT_YN, ");
		sBuilder.append(" (SELECT KNOWN_AS FROM EMPLOYEE WHERE EMPLOYEE_ID = OM.UPDATE_BY) UPDATE_BY ");
		sBuilder.append(" FROM ORDER_MANAGEMENT OM ");
		sBuilder.append(" WHERE COMPLETED_YN = 'N' ");
		sBuilder.append(" AND ORDER_CANCELED_YN IS NULL ");
		sBuilder.append(" ORDER BY ORDER_DATE DESC ");
		
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		System.out.println("order list : " + sBuilder);
		//System.out.println("productId " + inventory.getProductId());

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);
		
		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			PointOfSale oPointOfSale = new PointOfSale();
			oPointOfSale.setEncOrderId(oRemoveNull.nullRemove(oCipherUtils.encrypt((String.valueOf(row.get("ORDER_ID"))))));
			oPointOfSale.setOrderNo(oRemoveNull.nullRemove((String.valueOf(row.get("ORDER_NO")))));
			oPointOfSale.setTableNo(oRemoveNull.nullRemove((String.valueOf(row.get("TABLE_NO")))));
			oPointOfSale.setOrderDate(oRemoveNull.nullRemove((String.valueOf(row.get("ORDER_DATE")))));
			oPointOfSale.setEmployeeId(oRemoveNull.nullRemove((String.valueOf(row.get("WAITER_ID")))));
			oPointOfSale.setEmployeeName(oRemoveNull.nullRemove((String.valueOf(row.get("WAITER_NAME")))));
			oPointOfSale.setFinalizedYn(oRemoveNull.nullRemove((String.valueOf(row.get("FINALIZED_YN")))));
			oPointOfSale.setCompletedYn(oRemoveNull.nullRemove((String.valueOf(row.get("COMPLETED_YN")))));
			oPointOfSale.setUpdateBy(oRemoveNull.nullRemove((String.valueOf(row.get("UPDATE_BY")))));
			oPointOfSale.setOwnerFoodYn(oRemoveNull.nullRemove((String.valueOf(row.get("OWNER_FOOD_YN")))));
			oPointOfSale.setBillPrintYn(oRemoveNull.nullRemove((String.valueOf(row.get("BILL_PRINT_YN")))));
			oOrderList.add(oPointOfSale);
		}
		return oOrderList;
	}

	public List<PointOfSale> getPendingOrderInfoList(PointOfSale pointOfSale) throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<PointOfSale> oPendingOrderInfoList = new ArrayList<PointOfSale>();
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		StringBuilder sBuilder = new StringBuilder();

		try {
			
			sBuilder.append(" SELECT ITEM_ORDER_ID, ");
			sBuilder.append(" ORDER_ID, ");
			sBuilder.append(" ITEM_ID, ");
			sBuilder.append(" (SELECT ITEM_NAME FROM L_ITEM WHERE ITEM_ID = IO.ITEM_ID) ITEM_NAME, ");
			sBuilder.append(" QUANTITY, ");
			sBuilder.append(" ITEM_PRICE, ");
			sBuilder.append(" SUB_TOTAL, ");
			sBuilder.append(" ORDER_NOTE, ");
			sBuilder.append(" UPDATE_BY, ");
			sBuilder.append(" UPDATE_DATE ");
			sBuilder.append(" FROM ITEM_ORDER IO ");
			sBuilder.append(" WHERE ORDER_ID = :encOrderId ");

			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("encOrderId", oCipherUtils.decrypt(pointOfSale.getEncOrderId()));
			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);
			

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {

				PointOfSale oPointOfSale = new PointOfSale();
				oPointOfSale.setEncItemOrderId(oCipherUtils.encrypt(String.valueOf(row.get("ITEM_ORDER_ID"))));
				oPointOfSale.setItemOrderId(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_ORDER_ID"))));
				oPointOfSale.setEncItemId(oCipherUtils.encrypt(String.valueOf(row.get("ITEM_ID"))));
				oPointOfSale.setItemId(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_ID"))));
				oPointOfSale.setItemName(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_NAME"))));
				oPointOfSale.setQuantity(oRemoveNull.nullRemove(String.valueOf(row.get("QUANTITY"))));
				oPointOfSale.setItemPrice(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_PRICE"))));
				oPointOfSale.setSubTotal(oRemoveNull.nullRemove(String.valueOf(row.get("SUB_TOTAL"))));
				oPointOfSale.setOrderNote(oRemoveNull.nullRemove(String.valueOf(row.get("ORDER_NOTE"))));
				// oRegistrationRemoveNull.removeNullRegistration(oRegistration);
				oPendingOrderInfoList.add(oPointOfSale);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return oPendingOrderInfoList;
	};
	
	public PointOfSale cancelOrder(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		PointOfSale oPointOfSale = new PointOfSale();
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		try {

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append(" UPDATE ORDER_MANAGEMENT SET ORDER_CANCELED_YN = 'Y'  ");
			sBuilder.append(" WHERE ORDER_ID = :orderId  ");

			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("orderId", oCipherUtils.decrypt(oRemoveNull.nullRemove(pointOfSale.getEncOrderId())));

			npjt.update(sBuilder.toString(), paramSource);

			oPointOfSale.setMessage("Order Caceled Successfully");
			oPointOfSale.setmCode("1111");

		} catch (Exception ex) {
			oPointOfSale.setMessage("Error Canceling Record !!!");
			oPointOfSale.setmCode("0000");
			ex.printStackTrace();
		}
		return oPointOfSale;
	}

	/*public PointOfSale cancelOrder(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		PointOfSale oPointOfSale = new PointOfSale();
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_CANCEL_ORDER");
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("P_ORDER_ID", oCipherUtils.decrypt(pointOfSale.getEncOrderId()));

			// inParamMap.put("P_UPDATE_DATE", student.getUpdateDate());

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oPointOfSale.setMessage((String) outParamMap.get("P_MESSAGE"));
			oPointOfSale.setmCode((String) outParamMap.get("P_MESSAGE_CODE"));
			// System.out.println("studentId " + oStudent.getStudentId());
			 
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append(" UPDATE ORDER_MANAGEMENT SET ORDER_CANCELED_YN = 'Y'  ");
			sBuilder.append(" WHERE ORDER_ID = :orderId  ");
			
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("orderId", oCipherUtils.decrypt(oRemoveNull.nullRemove(pointOfSale.getEncOrderId())));
			
			Map<String, Object> rows = npjt.queryForMap(sBuilder.toString(), paramSource);
			
			
				
				} catch (Exception ex) {
			oPointOfSale.setMessage("Error Canceling Record !!!" + ex);
			oPointOfSale.setmCode("0000");
			ex.printStackTrace();
		}
		return oPointOfSale;
	}*/

	public List<PointOfSale> getOrderEditList(PointOfSale pointOfSale) throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<PointOfSale> oOrderEditList = new ArrayList<PointOfSale>();
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		StringBuilder sBuilder = new StringBuilder();

		try {
			sBuilder.append(" SELECT ITEM_ORDER_ID, ");
			sBuilder.append(" ORDER_ID, ");
			sBuilder.append(" ITEM_ID, ");
			sBuilder.append(" (SELECT ITEM_NAME FROM L_ITEM WHERE ITEM_ID = IO.ITEM_ID) ITEM_NAME, ");
			sBuilder.append(" QUANTITY, ");
			sBuilder.append(" ITEM_PRICE, ");
			sBuilder.append(" SUB_TOTAL, ");
			sBuilder.append(" ORDER_NOTE, ");
			sBuilder.append(" UPDATE_BY, ");
			sBuilder.append(" UPDATE_DATE ");
			sBuilder.append(" FROM ITEM_ORDER IO ");
			sBuilder.append(" WHERE ORDER_ID = :encOrderId ");

			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("encOrderId", oCipherUtils.decrypt(pointOfSale.getEncOrderId()));
			// System.out.println(sBuilder);
			sBuilder.append(" ORDER BY ITEM_ORDER_ID ASC ");

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);
			

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {

				PointOfSale oPointOfSale = new PointOfSale();
				oPointOfSale.setEncItemOrderId(oCipherUtils.encrypt(String.valueOf(row.get("ITEM_ORDER_ID"))));
				oPointOfSale.setEncOrderId(oCipherUtils.encrypt(String.valueOf(row.get("ORDER_ID"))));
				oPointOfSale.setOrderId(oRemoveNull.nullRemove(String.valueOf(row.get("ORDER_ID"))));
				oPointOfSale.setTableNo(oRemoveNull.nullRemove(String.valueOf(row.get("TABLE_NO"))));
				oPointOfSale.setItemId(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_ID"))));
				oPointOfSale.setItemName(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_NAME"))));
				oPointOfSale.setQuantity(oRemoveNull.nullRemove(String.valueOf(row.get("QUANTITY"))));
				oPointOfSale.setItemPrice(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_PRICE"))));
				oPointOfSale.setSubTotal(oRemoveNull.nullRemove(String.valueOf(row.get("SUB_TOTAL"))));
				oPointOfSale.setOrderNote(oRemoveNull.nullRemove(String.valueOf(row.get("ORDER_NOTE"))));
				// oRegistrationRemoveNull.removeNullRegistration(oRegistration);
				oOrderEditList.add(oPointOfSale);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return oOrderEditList;
	}

	public PointOfSale getOrderTotalAmount(String encOrderId) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		PointOfSale oPointOfSale = new PointOfSale();
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append("SELECT SUM (SUB_TOTAL) ORDER_TOTAL_AMOUNT, ");
		sBuilder.append("OM.NET_PAY_AMOUNT, ");
		sBuilder.append("OM.DISCOUNT_AMOUNT, ");
		sBuilder.append("OM.DISCOUNT_REFERENCE_BY ");
		sBuilder.append("FROM ITEM_ORDER IO, ORDER_MANAGEMENT OM ");
		sBuilder.append("WHERE IO.ORDER_ID = OM.ORDER_ID AND OM.ORDER_ID = :orderId  ");
		sBuilder.append("GROUP BY OM.ORDER_ID,OM.NET_PAY_AMOUNT,OM.DISCOUNT_AMOUNT, OM.DISCOUNT_REFERENCE_BY ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("orderId", oCipherUtils.decrypt(encOrderId));

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oPointOfSale.setOrderTotalAmount(oRemoveNull.nullRemove(String.valueOf(row.get("ORDER_TOTAL_AMOUNT"))));
			oPointOfSale.setNetPayableAmount(oRemoveNull.nullRemove(String.valueOf(row.get("NET_PAY_AMOUNT"))));
			oPointOfSale.setDiscountAmount(oRemoveNull.nullRemove(String.valueOf(row.get("DISCOUNT_AMOUNT"))));
			oPointOfSale.setDiscountReferenceBy(oRemoveNull.nullRemove(String.valueOf(row.get("DISCOUNT_REFERENCE_BY"))));
		}

		return oPointOfSale;
	}

	public PointOfSale saveOrderFinalize(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		PointOfSale oPointOfSale = new PointOfSale();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_ORDER_FINALIZE");
			Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("P_ORDER_ID", oCipherUtils.decrypt(oRemoveNull.nullRemove(pointOfSale.getEncOrderId())));
			inParamMap.put("P_PAYABLE_AMOUNT", pointOfSale.getOrderTotalAmount());
			inParamMap.put("P_NET_PAY_AMOUNT", pointOfSale.getNetPayableAmount());
			inParamMap.put("P_RECEIVED_AMOUNT", pointOfSale.getReceivedAmount());
			inParamMap.put("P_CHANGE_AMOUNT", pointOfSale.getChangeAmount());
			inParamMap.put("P_CASH_PAY_AMOUNT", pointOfSale.getCashPayAmount());
			inParamMap.put("P_CARD_PAY_AMOUNT", pointOfSale.getCardPayAmount());
			inParamMap.put("P_BKASH_PAYMENT_AMOUNT", pointOfSale.getBkashPaymentAmount());
			inParamMap.put("P_BKASH_TRAN_NO", pointOfSale.getBkashTranNo());
			inParamMap.put("P_DISCOUNT_AMOUNT", pointOfSale.getDiscountAmount());
			inParamMap.put("P_DISCOUNT_REFERENCE_BY", pointOfSale.getDiscountReferenceBy());
			inParamMap.put("P_BILL_PRINT_YN", pointOfSale.getBillPrintYn());
			inParamMap.put("P_UPDATE_BY", pointOfSale.getUpdateBy());
			inParamMap.put("P_DUE_CUSTOMER_YN", pointOfSale.getDueCustomerYn());
			inParamMap.put("P_DUE_CUSTOMER_ID", pointOfSale.getDueCustomerId());
			//inParamMap.put("P_DUE_DEPOSITE_AMOUNT", pointOfSale.getDueAmount());
			
			//pointOfSale.setPaidAmount("200");
			if (pointOfSale.getPaidAmount() == null || pointOfSale.getPaidAmount() == "") {
				pointOfSale.setPaidAmount("0");
			}
			inParamMap.put("P_PAID_AMOUNT", pointOfSale.getPaidAmount());
			
			System.out.println("ORDER iD : " + oCipherUtils.decrypt(oRemoveNull.nullRemove(pointOfSale.getEncOrderId())));
			
			System.out.println("P_PAYABLE_AMOUNT"+ pointOfSale.getOrderTotalAmount());
			System.out.println("P_NET_PAY_AMOUNT"+ pointOfSale.getNetPayableAmount());
			System.out.println("P_RECEIVED_AMOUNT"+ pointOfSale.getReceivedAmount());
			System.out.println("P_CHANGE_AMOUNT"+ pointOfSale.getChangeAmount());
			System.out.println("P_CASH_PAY_AMOUNT"+ pointOfSale.getCashPayAmount());
			System.out.println("P_CARD_PAY_AMOUNT"+ pointOfSale.getCardPayAmount());
			System.out.println("P_BKASH_PAYMENT_AMOUNT"+ pointOfSale.getBkashPaymentAmount());
			System.out.println("P_BKASH_TRAN_NO"+ pointOfSale.getBkashTranNo());
			System.out.println("P_DISCOUNT_AMOUNT"+ pointOfSale.getDiscountAmount());
			System.out.println("P_DISCOUNT_REFERENCE_BY"+ pointOfSale.getDiscountReferenceBy());
			System.out.println("P_BILL_PRINT_YN"+ pointOfSale.getBillPrintYn());
			System.out.println("P_UPDATE_BY"+ pointOfSale.getUpdateBy());
			
			System.out.println("P_DUE_CUSTOMER_YN: " + pointOfSale.getDueCustomerYn());
			System.out.println("P_DUE_CUSTOMER_ID : " + pointOfSale.getDueCustomerId());
			System.out.println("P_PAID_AMOUNT : " + pointOfSale.getPaidAmount());

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oPointOfSale.setMessage((String) outParamMap.get("P_MESSAGE"));
			oPointOfSale.setmCode((String) outParamMap.get("P_MESSAGE_CODE"));
			// System.out.println("studentId " + oStudent.getStudentId());
		} catch (Exception ex) {
			oPointOfSale.setMessage("Error Saving Record !!! " + ex);
			oPointOfSale.setmCode("0000");
			ex.printStackTrace();
		}
		return oPointOfSale;
	}

	public List<PointOfSale> getOwnerConsumptionList(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<PointOfSale> oOwnerConsumptionInfoList = new ArrayList<PointOfSale>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT CONSUMPTION_ID, ");
		sBuilder.append("ITEM_ID, ");
		sBuilder.append(" (SELECT ITEM_NAME FROM L_ITEM WHERE ITEM_ID = OFC.ITEM_ID) ITEM_NAME, ");
		sBuilder.append("QUANTITY, ");
		sBuilder.append("TO_CHAR (CONSUME_DATE, 'DD-MON-YYYY') CONSUME_DATE, ");
		sBuilder.append("REMARKS, ");
		sBuilder.append("(SELECT EMPLOYEE_NAME ");
		sBuilder.append("FROM EMPLOYEE ");
		sBuilder.append("WHERE EMPLOYEE_ID = OFC.UPDATE_BY) ");
		sBuilder.append("UPDATE_BY, ");
		sBuilder.append(" CASE WHEN TRUNC (UPDATE_DATE) = TRUNC (SYSDATE) THEN 'Y' ELSE 'N' END ");
		sBuilder.append(" EDITABLE ");
		sBuilder.append(" FROM OWNER_FOOD_CONSUMPTION OFC ");
		sBuilder.append("WHERE 1 = 1 ");
		sBuilder.append(" AND OWNER_ID = :empId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		/*if(ownerConsumptionInfo.getEmployeeId() != null && ownerConsumptionInfo.getEmployeeId().length() > 0) {
		} */
		
		paramSource.addValue("empId", pointOfSale.getEmployeeId());
		
		sBuilder.append("ORDER BY CONSUME_DATE DESC ");
		
		System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);
		
		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			PointOfSale oPointOfSale = new PointOfSale();
			oPointOfSale.setEncConsumeId(oRemoveNull.nullRemove(oCipherUtils.encrypt((String.valueOf(row.get("CONSUMPTION_ID"))))));
			oPointOfSale.setItemId(oRemoveNull.nullRemove((String.valueOf(row.get("ITEM_ID")))));
			oPointOfSale.setItemName(oRemoveNull.nullRemove((String.valueOf(row.get("ITEM_NAME")))));
			oPointOfSale.setQuantity(oRemoveNull.nullRemove((String.valueOf(row.get("QUANTITY")))));
			oPointOfSale.setConsumeDate(oRemoveNull.nullRemove((String.valueOf(row.get("CONSUME_DATE")))));
			oPointOfSale.setRemarks(oRemoveNull.nullRemove((String.valueOf(row.get("REMARKS")))));
			oPointOfSale.setEmployeeName(oRemoveNull.nullRemove((String.valueOf(row.get("UPDATE_BY")))));
			oPointOfSale.setEditable(oRemoveNull.nullRemove((String.valueOf(row.get("EDITABLE")))));
			
			oOwnerConsumptionInfoList.add(oPointOfSale);
		}
		return oOwnerConsumptionInfoList;
	}

	public PointOfSale saveOwnerConsumption(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		PointOfSale oOwnerConsumptionInfo = new PointOfSale();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_OWNER_CONSUME_SAVE");

			Map<String, Object> inParamMap = new HashMap<String, Object>();
			
			inParamMap.put("P_CONSUMPTION_ID", oCipherUtils.decrypt(oRemoveNull.nullRemove(pointOfSale.getEncConsumeId())));
			inParamMap.put("P_EMPLOYEE_ID", pointOfSale.getEmployeeId());
			inParamMap.put("P_CONSUME_DATE", pointOfSale.getConsumeDate());
			inParamMap.put("P_ITEM_ID", pointOfSale.getItemId());
			inParamMap.put("P_QUANTITY", pointOfSale.getQuantity());
			inParamMap.put("P_PRICE", pointOfSale.getPrice());
			inParamMap.put("P_REMARKS", pointOfSale.getRemarks());
			inParamMap.put("P_UPDATE_BY", pointOfSale.getUpdateBy());
			
			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oOwnerConsumptionInfo.setMessage(oRemoveNull.nullRemove((String) outParamMap.get("P_MESSAGE")));
			oOwnerConsumptionInfo.setmCode(oRemoveNull.nullRemove((String) outParamMap.get("P_MESSAGE_CODE")));
			
			System.out.println("oOwnerConsumptionInfo.setMessage() " + oOwnerConsumptionInfo.getMessage());

		} catch (Exception ex) {
			ex.printStackTrace();
			oOwnerConsumptionInfo.setMessage("Error Occured!!!");
			oOwnerConsumptionInfo.setmCode("0000");
		}
		return oOwnerConsumptionInfo;
	}

	public PointOfSale getOwnerConsumption(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		 
		PointOfSale oOwnerConsumptionInfo = new PointOfSale();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT CONSUMPTION_ID, ");
		sBuilder.append("OWNER_ID, ");
		sBuilder.append("TO_CHAR (CONSUME_DATE, 'DD/MM/YYYY')CONSUME_DATE, ");
		sBuilder.append("ITEM_ID, ");
		sBuilder.append("QUANTITY, ");
		sBuilder.append("REMARKS ");
		sBuilder.append("FROM OWNER_FOOD_CONSUMPTION ");
		sBuilder.append("WHERE CONSUMPTION_ID = :cosumeId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		paramSource.addValue("cosumeId", oCipherUtils.decrypt(oRemoveNull.nullRemove(pointOfSale.getEncConsumeId())));
		
		//System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);
		
		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oOwnerConsumptionInfo.setEncConsumeId(oRemoveNull.nullRemove(oCipherUtils.encrypt((String.valueOf(row.get("CONSUMPTION_ID"))))));
			oOwnerConsumptionInfo.setEmployeeId(oRemoveNull.nullRemove((String.valueOf(row.get("OWNER_ID")))));
			oOwnerConsumptionInfo.setConsumeDate(oRemoveNull.nullRemove((String.valueOf(row.get("CONSUME_DATE")))));
			oOwnerConsumptionInfo.setItemId(oRemoveNull.nullRemove((String.valueOf(row.get("ITEM_ID")))));
			oOwnerConsumptionInfo.setQuantity(oRemoveNull.nullRemove((String.valueOf(row.get("QUANTITY")))));
			oOwnerConsumptionInfo.setRemarks(oRemoveNull.nullRemove((String.valueOf(row.get("REMARKS")))));
		}
		return oOwnerConsumptionInfo;
	}

	public PointOfSale orderProcessComplete(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		PointOfSale oPointOfSale = new PointOfSale();
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		try {

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append(" UPDATE ORDER_MANAGEMENT SET COMPLETED_YN = 'Y'  ");
			sBuilder.append(" WHERE ORDER_ID = :orderId  ");

			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("orderId", oCipherUtils.decrypt(oRemoveNull.nullRemove(pointOfSale.getEncOrderId())));

			npjt.update(sBuilder.toString(), paramSource);

			oPointOfSale.setMessage("Order Removed Successfully");
			oPointOfSale.setmCode("1111");

		} catch (Exception ex) {
			oPointOfSale.setMessage("Error Removing Record !!!");
			oPointOfSale.setmCode("0000");
			ex.printStackTrace();
		}
		return oPointOfSale;
	}

	public PointOfSale getDuplicteTable(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		PointOfSale oPointOfSale = new PointOfSale();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append(" SELECT COUNT (TABLE_NO) DEPLICATE_TABLE ");
		sBuilder.append(" FROM ORDER_MANAGEMENT ");
		sBuilder.append(" WHERE FINALIZED_YN = 'N' AND TRUNC (ORDER_DATE) = TRUNC (SYSDATE) AND TABLE_NO = :tableNo ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("tableNo", pointOfSale.getTableNo());

		 //System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oPointOfSale.setCountDuplicateTable(oRemoveNull.nullRemove(String.valueOf(row.get("DEPLICATE_TABLE"))));
		}
		return oPointOfSale;
	}

	public PointOfSale saveDueCustomer(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		PointOfSale oPointOfSale = new PointOfSale();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_DUE_CUSTOMER_SAVE");
			Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("P_DUE_CUSTOMER_ID", oCipherUtils.decrypt(pointOfSale.getEncDueCustomerId()));
			inParamMap.put("P_CUSTOMER_NAME", pointOfSale.getCustomerName());
			inParamMap.put("P_PHONE_NO", pointOfSale.getPhoneNo());
			inParamMap.put("P_FATHER_NAME", pointOfSale.getFatherName());
			inParamMap.put("P_MOTHER_NAME", pointOfSale.getMotherName());
			inParamMap.put("P_EMAIL", pointOfSale.getEmail());
			inParamMap.put("P_CUSTOMER_ADDRESS", pointOfSale.getCustomerAddress());
			inParamMap.put("P_UPDATE_BY", pointOfSale.getUpdateBy());

			// inParamMap.put("P_UPDATE_DATE", student.getUpdateDate());

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oPointOfSale.setMessage((String) outParamMap.get("P_MESSAGE"));
			oPointOfSale.setmCode((String) outParamMap.get("P_MESSAGE_CODE"));
			// System.out.println("studentId " + oStudent.getStudentId());
		} catch (Exception ex) {
			oPointOfSale.setMessage("Error Saving Record !!!");
			oPointOfSale.setmCode("0000");
			ex.printStackTrace();
		}
		return oPointOfSale;
	}

	public List<PointOfSale> getDueCustomerList(PointOfSale pointOfSale) throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<PointOfSale> oDueCustomerList = new ArrayList<PointOfSale>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT DUE_CUSTOMER_ID, ");
		sBuilder.append(" CUSTOMER_NAME, ");
		sBuilder.append(" FATHER_NAME, ");
		sBuilder.append(" MOTHER_NAME, ");
		sBuilder.append(" PHONE_NO, ");
		sBuilder.append(" EMAIL, ");
		sBuilder.append(" ADDRESS ");
		sBuilder.append(" FROM DUE_CUSTOMER ");
		//sBuilder.append(" WHERE DUE_CUSTOMER_ID = :encDueCustomerId ");
		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		//System.out.println("bbb" + sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			PointOfSale oPointOfSale = new PointOfSale();
			oPointOfSale.setEncDueCustomerId(oCipherUtils.encrypt(String.valueOf(row.get("DUE_CUSTOMER_ID"))));
			oPointOfSale.setDueCustomerId(oRemoveNull.nullRemove(String.valueOf(row.get("DUE_CUSTOMER_ID"))));
			oPointOfSale.setCustomerName(oRemoveNull.nullRemove(String.valueOf(row.get("CUSTOMER_NAME"))));
			oPointOfSale.setFatherName(oRemoveNull.nullRemove(String.valueOf(row.get("FATHER_NAME"))));
			oPointOfSale.setMotherName(oRemoveNull.nullRemove(String.valueOf(row.get("MOTHER_NAME"))));
			oPointOfSale.setPhoneNo(oRemoveNull.nullRemove(String.valueOf(row.get("PHONE_NO"))));
			oPointOfSale.setEmail(oRemoveNull.nullRemove(String.valueOf(row.get("EMAIL"))));
			oPointOfSale.setCustomerAddress(oRemoveNull.nullRemove(String.valueOf(row.get("ADDRESS"))));
			oDueCustomerList.add(oPointOfSale);

			// System.out.println("abc"+
			// oCipherUtils.encrypt(String.valueOf(row.get("EMPLOYEE_ID"))));

		}

		return oDueCustomerList;
	}

	public PointOfSale getDueCustomerInfo(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		PointOfSale oPointOfSale = new PointOfSale();
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append(" SELECT DUE_CUSTOMER_ID, ");
		sBuilder.append(" CUSTOMER_NAME, ");
		sBuilder.append(" FATHER_NAME, ");
		sBuilder.append(" MOTHER_NAME, ");
		sBuilder.append(" PHONE_NO, ");
		sBuilder.append(" EMAIL, ");
		sBuilder.append(" ADDRESS ");
		sBuilder.append(" FROM DUE_CUSTOMER ");
		sBuilder.append(" WHERE DUE_CUSTOMER_ID = :encDueCustomerId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("encDueCustomerId", oCipherUtils.decrypt(pointOfSale.getEncDueCustomerId()));

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oPointOfSale.setEncDueCustomerId(oCipherUtils.encrypt(String.valueOf(row.get("DUE_CUSTOMER_ID"))));
			oPointOfSale.setDueCustomerId(oRemoveNull.nullRemove(String.valueOf(row.get("DUE_CUSTOMER_ID"))));
			oPointOfSale.setCustomerName(oRemoveNull.nullRemove(String.valueOf(row.get("CUSTOMER_NAME"))));
			oPointOfSale.setFatherName(oRemoveNull.nullRemove(String.valueOf(row.get("FATHER_NAME"))));
			oPointOfSale.setMotherName(oRemoveNull.nullRemove(String.valueOf(row.get("MOTHER_NAME"))));
			oPointOfSale.setPhoneNo(oRemoveNull.nullRemove(String.valueOf(row.get("PHONE_NO"))));
			oPointOfSale.setEmail(oRemoveNull.nullRemove(String.valueOf(row.get("EMAIL"))));
			oPointOfSale.setCustomerAddress(oRemoveNull.nullRemove(String.valueOf(row.get("ADDRESS"))));
		}

		return oPointOfSale;
	}

	public PointOfSale getDueDepositeAmount(String dueCustomerId) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		PointOfSale oPointOfSale = new PointOfSale();
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append(" SELECT DC.DUE_CUSTOMER_ID, ");
		sBuilder.append(" NVL (DCP.DUE_DEPOSITE_AMOUNT, 0) DUE_DEPOSITE_AMOUNT, ");
		sBuilder.append(" DC.CUSTOMER_NAME ");
		sBuilder.append(" FROM DUE_CUSTOMER_PAYMENT DCP, DUE_CUSTOMER DC ");
		sBuilder.append(" WHERE DCP.DUE_CUSTOMER_ID = DC.DUE_CUSTOMER_ID ");
		sBuilder.append(" AND DC.DUE_CUSTOMER_ID = :dueCustomerId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("dueCustomerId", dueCustomerId);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oPointOfSale.setDueAmount(oRemoveNull.nullRemove(String.valueOf(row.get("DUE_DEPOSITE_AMOUNT"))));
			oPointOfSale.setDueCustomerId(oRemoveNull.nullRemove(String.valueOf(row.get("DUE_CUSTOMER_ID"))));
			oPointOfSale.setCustomerName(oRemoveNull.nullRemove(String.valueOf(row.get("CUSTOMER_NAME"))));
		}

		return oPointOfSale;
	}

	public PointOfSale saveDueCollection(PointOfSale pointOfSale) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		PointOfSale oPointOfSale = new PointOfSale();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_DUE_COLLECTION_SAVE");
			Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("P_DUE_CUSTOMER_ID", pointOfSale.getDueCustomerId());
			inParamMap.put("P_DUE_DEPOSITE_AMOUNT", pointOfSale.getDueAmount());
			inParamMap.put("P_PAID_AMOUNT", pointOfSale.getPaidAmount());
			inParamMap.put("P_UPDATE_BY", pointOfSale.getUpdateBy());

			// inParamMap.put("P_UPDATE_DATE", student.getUpdateDate());

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oPointOfSale.setMessage((String) outParamMap.get("P_MESSAGE"));
			oPointOfSale.setmCode((String) outParamMap.get("P_MESSAGE_CODE"));
			// System.out.println("studentId " + oStudent.getStudentId());
		} catch (Exception ex) {
			oPointOfSale.setMessage("Error Saving Record !!!");
			oPointOfSale.setmCode("0000");
			ex.printStackTrace();
		}
		return oPointOfSale;
	}

	public List<PointOfSale> getDueCollectionHistoryList(PointOfSale pointOfSale) throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<PointOfSale> oDueCollectionHistoryList = new ArrayList<PointOfSale>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT DUE_CUSTOMER_PAYMENT_ID, ");
		sBuilder.append(" DUE_DEPOSITE_AMOUNT, ");
		sBuilder.append(" PAID_AMOUNT, ");
		sBuilder.append(" TO_CHAR(PAY_DATE, 'DD/MM/YYYY')PAY_DATE ");
		sBuilder.append(" FROM DUE_CUSTOMER_PAYMENT_HISTORY ");
		sBuilder.append(" WHERE TRUNC (PAY_DATE) BETWEEN TO_DATE (:fromDate, 'dd/mm/yyyy') ");
		sBuilder.append(" AND TO_DATE (:toDate, 'dd/mm/yyyy') ");
		sBuilder.append(" AND DUE_CUSTOMER_ID = :dueCustomerId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("dueCustomerId", pointOfSale.getDueCustomerId());
		paramSource.addValue("fromDate", pointOfSale.getFromDate());
		paramSource.addValue("toDate", pointOfSale.getToDate());
		
		// System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			PointOfSale oPointOfSale = new PointOfSale();
			oPointOfSale.setDueCustomerPaymentId(oCipherUtils.encrypt(String.valueOf(row.get("DUE_CUSTOMER_PAYMENT_ID"))));
			oPointOfSale.setDueAmount(oRemoveNull.nullRemove(String.valueOf(row.get("DUE_DEPOSITE_AMOUNT"))));
			oPointOfSale.setPaidAmount(oRemoveNull.nullRemove(String.valueOf(row.get("PAID_AMOUNT"))));
			oPointOfSale.setPayDate(oRemoveNull.nullRemove(String.valueOf(row.get("PAY_DATE"))));
			oDueCollectionHistoryList.add(oPointOfSale);
		}

		return oDueCollectionHistoryList;
	}

}

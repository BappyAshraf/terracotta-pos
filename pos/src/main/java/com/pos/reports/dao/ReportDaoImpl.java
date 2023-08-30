package com.pos.reports.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import com.pos.pointOfSale.model.PointOfSale;
import com.pos.reports.model.ReportModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Repository("ReportDao")
public class ReportDaoImpl implements ReportDao{

	private SimpleJdbcCall simpleJdbcCall;
	private JdbcTemplate jdbcTemplate;
	CipherUtils oCipherUtils = new CipherUtils();
	RemoveNull oRemoveNull = new RemoveNull();
	
	@Autowired
	private DataSource dataSource;

	
	
	public ReportModel searchReportDetails(ReportModel reportModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("select JESPER_NAME from report_list where report_code = :reportCode");
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("reportCode", reportModel.getReportCode());
			
			//System.out.println("reportCode " + reportModel.getReportCode());
			
			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				reportModel.setJesperName(String.valueOf(row.get("JESPER_NAME")));
				
				System.out.println("JESPER_NAME " + reportModel.getJesperName());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return reportModel;
	}

	public JRDataSource orderFinalizeData(ReportModel reportModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<ReportModel> list = new ArrayList<ReportModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
			StringBuilder sBuilder = new StringBuilder();

			sBuilder.append(" SELECT ORDER_ID, ORDER_NO, ");
			sBuilder.append(" (SELECT ITEM_NAME FROM L_ITEM WHERE ITEM_ID = OM.ITEM_ID) ITEM_NAME, ");
			sBuilder.append(" QUANTITY, ");
			sBuilder.append(" ITEM_PRICE, ");
			sBuilder.append(" UPDATE_BY, ");
			sBuilder.append(" UPDATE_DATE ");
			sBuilder.append(" FROM ORDER_MANAGEMENT OM ");
			sBuilder.append(" WHERE OM.ORDER_ID = :orderId ");

			MapSqlParameterSource paramSource = new MapSqlParameterSource();

			paramSource.addValue("orderId", oCipherUtils.decrypt(reportModel.getEncOrderId()));
			

			// System.out.println(sBuilder);

			// paramSource.addValue("regNo", reportModel.getRegistrationNo());

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				ReportModel oReportModel = new ReportModel();
				// oReportModel.setEncRegisterId(oCipherUtils.encrypt(String.valueOf(row.get("REGISTER_ID"))));
				oReportModel.setOrderId(oRemoveNull.nullRemove(String.valueOf(row.get("ORDER_ID"))));
				oReportModel.setOrderNo(oRemoveNull.nullRemove(String.valueOf(row.get("ORDER_NO"))));
				oReportModel.setItemName(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_NAME"))));
				oReportModel.setQuantity(oRemoveNull.nullRemove(String.valueOf(row.get("QUANTITY"))));
				oReportModel.setItemPrice(Integer.parseInt(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_PRICE")))));
				oReportModel.setUpdateBy(oRemoveNull.nullRemove(String.valueOf(row.get("UPDATE_BY"))));
				oReportModel.setUpdateDate(oRemoveNull.nullRemove(String.valueOf(row.get("UPDATE_DATE"))));
				
				//System.out.println("price " + reportModel.getItemPrice());
				
				list.add(oReportModel);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JRDataSource ds = new JRBeanCollectionDataSource(list);

		return ds;
	}

	public JRDataSource inventoryReportData(ReportModel reportModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<ReportModel> list = new ArrayList<ReportModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
			StringBuilder sBuilder = new StringBuilder();

			sBuilder.append(" SELECT INVENTORY_ID, ");
			sBuilder.append(" (SELECT INVENTORY_TYPE_NAME FROM L_INVENTORY_TYPE WHERE INVENTORY_TYPE_ID = IM.INVENTORY_TYPE_ID) INVENTORY_TYPE_NAME, ");
			sBuilder.append(" (SELECT PRODUCT_NAME FROM L_PRODUCT WHERE PRODUCT_ID = IM.PRODUCT_ID) PRODUCT_NAME, ");
			sBuilder.append(" QUANTITY, ");
			sBuilder.append(" (SELECT UNIT_NAME FROM L_UNIT WHERE UNIT_ID = IM.UNIT_ID) UNIT_NAME, ");
			sBuilder.append(" PRICE, ");
			//sBuilder.append(" (SELECT KNOWN_AS FROM EMPLOYEE WHERE EMPLOYEE_ID = IM.UPDATE_BY) EMPLOYEE_NAME, ");
			sBuilder.append(" PROGRAM_YN, ");
			sBuilder.append(" PROGRAM_ID, ");
			sBuilder.append(" UPDATE_BY, ");
			sBuilder.append(" TO_CHAR(UPDATE_DATE,'DD/MM/YYYY') UPDATE_DATE ");
			sBuilder.append(" FROM INVENTORY_MASTER IM ");
			sBuilder.append(" WHERE TRUNC(UPDATE_DATE) BETWEEN TO_DATE(:fromDate,'DD/MM/YYYY') AND TO_DATE(:toDate,'DD/MM/YYYY') ");

			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			
			if (reportModel.getInventoryTypeId() != null && reportModel.getInventoryTypeId() != "") {
				sBuilder.append(" AND INVENTORY_TYPE_ID = :inventoryTypeId ");
				paramSource.addValue("inventoryTypeId", reportModel.getInventoryTypeId());
			} else if (reportModel.getProductId() != null && reportModel.getProductId() != "") {
				sBuilder.append(" AND PRODUCT_ID = :productId ");
				paramSource.addValue("productId", reportModel.getProductId());
			}

			

			paramSource.addValue("fromDate", reportModel.getFromDate());
			paramSource.addValue("toDate", reportModel.getToDate());
			

			// System.out.println(sBuilder);

			// paramSource.addValue("regNo", reportModel.getRegistrationNo());

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				ReportModel oReportModel = new ReportModel();
				// oReportModel.setEncRegisterId(oCipherUtils.encrypt(String.valueOf(row.get("REGISTER_ID"))));
				oReportModel.setInventoryId(oRemoveNull.nullRemove(String.valueOf(row.get("INVENTORY_ID"))));
				oReportModel.setInventoryTypeName(oRemoveNull.nullRemove(String.valueOf(row.get("INVENTORY_TYPE_NAME"))));
				oReportModel.setProductName(oRemoveNull.nullRemove(String.valueOf(row.get("PRODUCT_NAME"))));
				oReportModel.setQuantity(oRemoveNull.nullRemove(String.valueOf(row.get("QUANTITY"))));
				oReportModel.setUnitName(oRemoveNull.nullRemove(String.valueOf(row.get("UNIT_NAME"))));
				oReportModel.setItemPrice(Integer.parseInt(oRemoveNull.nullRemove(String.valueOf(row.get("PRICE")))));
				oReportModel.setEmployeeName(oRemoveNull.nullRemove(String.valueOf(row.get("UPDATE_BY"))));
				oReportModel.setUpdateBy(oRemoveNull.nullRemove(String.valueOf(row.get("UPDATE_BY"))));
				oReportModel.setUpdateDate(oRemoveNull.nullRemove(String.valueOf(row.get("UPDATE_DATE"))));
				
				//System.out.println("price " + reportModel.getItemPrice());
				
				list.add(oReportModel);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JRDataSource ds = new JRBeanCollectionDataSource(list);

		return ds;
	}

	public JRDataSource posReportData(ReportModel reportModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<ReportModel> list = new ArrayList<ReportModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
			StringBuilder sBuilder = new StringBuilder();

			/*sBuilder.append(" SELECT ORDER_ID, ");
			sBuilder.append(" (SELECT ITEM_NAME FROM L_ITEM WHERE ITEM_ID = OM.ITEM_ID) ITEM_NAME, ");
			sBuilder.append(" QUANTITY, ");
			sBuilder.append(" ITEM_PRICE, ");
			sBuilder.append(" SUB_TOTAL, ");
			sBuilder.append(" (SELECT KNOWN_AS FROM EMPLOYEE WHERE EMPLOYEE_ID = OM.UPDATE_BY) UPDATE_BY, ");
			sBuilder.append(" TO_CHAR(UPDATE_DATE,'DD/MM/YYYY') UPDATE_DATE ");
			sBuilder.append(" FROM ITEM_ORDER OM ");
			sBuilder.append(" WHERE TRUNC(UPDATE_DATE) BETWEEN TO_DATE(:fromDate,'DD/MM/YYYY') AND TO_DATE(:toDate,'DD/MM/YYYY') ");*/
			
			// 01/11/2019
			/*sBuilder.append(" SELECT IO.ORDER_ID, ");
			sBuilder.append(" (SELECT ITEM_NAME FROM L_ITEM WHERE ITEM_ID = IO.ITEM_ID)ITEM_NAME, ");
			sBuilder.append(" IO.QUANTITY, ");
			sBuilder.append(" IO.ITEM_PRICE, ");
			sBuilder.append(" IO.SUB_TOTAL, ");
			sBuilder.append(" (SELECT KNOWN_AS FROM EMPLOYEE WHERE EMPLOYEE_ID = OM.UPDATE_BY) UPDATE_BY, ");
			sBuilder.append(" TO_CHAR (IO.UPDATE_DATE, 'DD/MM/YYYY') UPDATE_DATE ");
			sBuilder.append(" FROM ITEM_ORDER IO, ORDER_MANAGEMENT OM ");
			sBuilder.append(" WHERE IO.ORDER_ID = OM.ORDER_ID AND OM.FINALIZED_YN = 'Y' ");
			sBuilder.append(" AND TRUNC (IO.UPDATE_DATE) BETWEEN TO_DATE (:fromDate, 'DD/MM/YYYY')AND TO_DATE (:toDate, 'DD/MM/YYYY') ");*/
			
			
			sBuilder.append(" SELECT ITEM_ID, ");
			sBuilder.append(" (SELECT ITEM_NAME FROM L_ITEM WHERE ITEM_ID = IO.ITEM_ID) ITEM_NAME, ");
			sBuilder.append(" ITEM_PRICE, ");
			sBuilder.append(" SUM (QUANTITY) QUANTITY, ");
			sBuilder.append(" SUM (SUB_TOTAL) SUB_TOTAL ");
			sBuilder.append(" FROM ITEM_ORDER IO, ORDER_MANAGEMENT OM ");
			sBuilder.append(" WHERE     IO.ORDER_ID = OM.ORDER_ID ");
			sBuilder.append(" AND OM.FINALIZED_YN = 'Y' ");
			sBuilder.append(" AND ORDER_CANCELED_YN IS NULL ");
			//sBuilder.append(" AND OWNER_FOOD_YN = 'N' ");
			sBuilder.append(" AND TRUNC (OM.ORDER_DATE) BETWEEN TO_DATE (:fromDate, 'DD/MM/YYYY') AND TO_DATE (:toDate, 'DD/MM/YYYY') ");


			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			
			/*if (reportModel.getItemId() != null && reportModel.getItemId() != "") {
				sBuilder.append(" AND ITEM_ID = :itemId ");
				paramSource.addValue("itemId", reportModel.getItemId());
			}*/
			sBuilder.append(" GROUP BY ITEM_ID, ITEM_PRICE ");
			sBuilder.append(" ORDER BY ITEM_ID ");

			paramSource.addValue("fromDate", reportModel.getFromDate());
			paramSource.addValue("toDate", reportModel.getToDate());
			

			// System.out.println(sBuilder);

			// paramSource.addValue("regNo", reportModel.getRegistrationNo());

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				ReportModel oReportModel = new ReportModel();
				oReportModel.setItemId(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_ID"))));
				oReportModel.setItemName(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_NAME"))));
				oReportModel.setReportItemPrice(Double.parseDouble((oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_PRICE"))))));
				oReportModel.setQuantity(oRemoveNull.nullRemove(String.valueOf(row.get("QUANTITY"))));
				oReportModel.setReportSubTotalCount(Double.parseDouble(oRemoveNull.nullRemove(String.valueOf(row.get("SUB_TOTAL")))));
				//oReportModel.setUpdateBy(oRemoveNull.nullRemove(String.valueOf(row.get("UPDATE_BY"))));
				//oReportModel.setUpdateDate(oRemoveNull.nullRemove(String.valueOf(row.get("UPDATE_DATE"))));
				
				//System.out.println("price " + reportModel.getItemPrice());
				
				list.add(oReportModel);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JRDataSource ds = new JRBeanCollectionDataSource(list);

		return ds;
	}

	public String viewPdfReport(String fileName, HttpServletRequest request, HttpServletResponse response,
			Map parameters) throws JRException, IOException, SQLException, ClassNotFoundException, Exception {
		String sResult = "";
		HttpSession session = request.getSession();
		ServletContext servletContext = session.getServletContext();
		
		//System.out.println("path " + servletContext.getRealPath(fileName));
		
		File reportFile = new File(servletContext.getRealPath(fileName));
		Connection conn = dataSource.getConnection();

		//System.out.println("........Coon......" + conn);

		/*
		 * Connection conn = DriverManager.getConnection(
		 * "jdbc:oracle:thin:@192.168.78.79:1521:orcl", "btcl", "btcl");
		 */

		System.out.println("parameters..................       " + parameters.size());

		byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);
		if (bytes.length > 380) {
			sResult = "Found";
			
			//System.out.println(bytes.length);
		}

		// if (conn != null) try { conn.close(); } catch (Exception e)
		// {e.printStackTrace();}
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);
		ouputStream.flush();
		ouputStream.close();

		return sResult;
	}

	public JRDataSource costAnalysisReportData(ReportModel reportModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<ReportModel> list = new ArrayList<ReportModel>();
		ReportModel oReportModel = new ReportModel();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_DATE_WISE_COST_ANALYSIS");
			Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("P_COST_ANALYSIS_REPORT_TYPE", reportModel.getCostAnalysisReportType());
			inParamMap.put("P_YEAR", reportModel.getYear());
			inParamMap.put("P_MONTH", reportModel.getMonth());
			inParamMap.put("P_FROM_DATE", reportModel.getFromDate());
			inParamMap.put("P_TO_DATE", reportModel.getToDate());

			// inParamMap.put("P_UPDATE_DATE", student.getUpdateDate());

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			
			oReportModel.setEmployeeSalary(oRemoveNull.nullRemove((String) outParamMap.get("P_EMPLOYEE_SALARY")));
			oReportModel.setInventoryCost(oRemoveNull.nullRemove((String) outParamMap.get("P_INVENTORY")));
			oReportModel.setDiscountCost(oRemoveNull.nullRemove((String) outParamMap.get("P_DISCOUNT")));
			oReportModel.setWastageCost(oRemoveNull.nullRemove((String) outParamMap.get("P_WASTAGE")));
			oReportModel.setOwnerFoodConsumeCost(oRemoveNull.nullRemove((String) outParamMap.get("P_OWNER_FOOD_CONSUME")));
			oReportModel.setTotalCost(oRemoveNull.nullRemove((String) outParamMap.get("P_TOTAL_COST")));
			oReportModel.setTotalOrderPrice(oRemoveNull.nullRemove((String) outParamMap.get("P_ORDER_PRICE")));
			oReportModel.setEmployeeSalary(oRemoveNull.nullRemove((String) outParamMap.get("P_EMPLOYEE_SALARY")));
			oReportModel.setNetIncome(oRemoveNull.nullRemove((String) outParamMap.get("P_NET_INCOME")));
			
			//System.out.println("P_EMPLOYEE_SALARY " + oReportModel.getEmployeeSalary());
			//System.out.println("P_TOTAL_COST " + oReportModel.getTotalCost());
			//System.out.println("P_ORDER_PRICE " + oReportModel.getTotalOrderPrice());
			
			list.add(oReportModel);
			
		} catch (Exception ex) {
			oReportModel.setMessage("Error Printing Report !!!");
			oReportModel.setmCode("0000");
			ex.printStackTrace();
		}
		JRDataSource ds = new JRBeanCollectionDataSource(list);

		return ds;
	}

	public JRDataSource kitchenQTReportData(ReportModel reportModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<ReportModel> list = new ArrayList<ReportModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
			StringBuilder sBuilder = new StringBuilder();

			sBuilder.append(" SELECT IO.ITEM_ORDER_ID, ");
			sBuilder.append(" IO.ORDER_ID, ");
			sBuilder.append(" IO.ITEM_ID, ");
			sBuilder.append(" (SELECT ITEM_NAME FROM L_ITEM WHERE ITEM_ID = IO.ITEM_ID) ITEM_NAME, ");
			sBuilder.append(" IO.QUANTITY, ");
			sBuilder.append(" IO.ITEM_PRICE, ");
			sBuilder.append(" IO.SUB_TOTAL, ");
			sBuilder.append(" (SELECT KNOWN_AS FROM EMPLOYEE WHERE EMPLOYEE_ID = IO.UPDATE_BY) UPDATE_BY, ");
			sBuilder.append(" TO_CHAR(IO.UPDATE_DATE,'DD/MM/YYYY HH12:MI:SS AM') UPDATE_DATE, ");
			sBuilder.append(" IO.PRINTED_YN, ");
			sBuilder.append(" IO.UPDATED_YN, ");
			sBuilder.append(" IO.ORDER_NOTE, ");
			sBuilder.append(" (SELECT KNOWN_AS FROM EMPLOYEE WHERE EMPLOYEE_ID = OM.WAITER_ID) SERVED_BY, ");
			sBuilder.append(" OM.TABLE_NO, ");
			sBuilder.append(" OM.ORDER_NO ");
			sBuilder.append(" FROM ITEM_ORDER IO, ORDER_MANAGEMENT OM ");
			sBuilder.append(" WHERE IO.ORDER_ID = OM.ORDER_ID ");
			sBuilder.append(" AND PRINTED_YN = 'N' ");
			sBuilder.append(" AND IO.ORDER_ID = :encOrderId ");
			

			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("encOrderId", oCipherUtils.decrypt(reportModel.getEncOrderId()));
			/*paramSource.addValue("fromDate", reportModel.getFromDate());
			paramSource.addValue("toDate", reportModel.getToDate());*/
			

			// System.out.println(sBuilder);

			// paramSource.addValue("regNo", reportModel.getRegistrationNo());

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				ReportModel oReportModel = new ReportModel();
				// oReportModel.setEncRegisterId(oCipherUtils.encrypt(String.valueOf(row.get("REGISTER_ID"))));
				oReportModel.setItemOrderId(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_ORDER_ID"))));
				oReportModel.setOrderId(oRemoveNull.nullRemove(String.valueOf(row.get("ORDER_ID"))));
				oReportModel.setItemId(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_ID"))));
				oReportModel.setItemName(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_NAME"))));
				oReportModel.setQuantity(oRemoveNull.nullRemove(String.valueOf(row.get("QUANTITY"))));
				oReportModel.setItemPrice(Integer.parseInt(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_PRICE")))));
				oReportModel.setSubTotal(oRemoveNull.nullRemove(String.valueOf(row.get("SUB_TOTAL"))));
				oReportModel.setPrintedYn(oRemoveNull.nullRemove(String.valueOf(row.get("PRINTED_YN"))));
				oReportModel.setUpdatedYn(oRemoveNull.nullRemove(String.valueOf(row.get("UPDATED_YN"))));
				oReportModel.setTableNo(oRemoveNull.nullRemove(String.valueOf(row.get("TABLE_NO"))));
				oReportModel.setOrderNote(oRemoveNull.nullRemove(String.valueOf(row.get("ORDER_NOTE"))));
				oReportModel.setUpdateBy(oRemoveNull.nullRemove(String.valueOf(row.get("UPDATE_BY"))));
				oReportModel.setUpdateDate(oRemoveNull.nullRemove(String.valueOf(row.get("UPDATE_DATE"))));
				oReportModel.setEmployeeName(oRemoveNull.nullRemove(String.valueOf(row.get("SERVED_BY"))));
				oReportModel.setOrderNo(oRemoveNull.nullRemove(String.valueOf(row.get("ORDER_NO"))));
				//System.out.println("price " + reportModel.getItemPrice());
				
				list.add(oReportModel);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JRDataSource ds = new JRBeanCollectionDataSource(list);

		return ds;
	}

	public JRDataSource customerMoneyReceiptData(ReportModel reportModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<ReportModel> list = new ArrayList<ReportModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
			StringBuilder sBuilder = new StringBuilder();

			sBuilder.append(" SELECT OM.ORDER_ID, ");
			sBuilder.append(" OM.ORDER_NO, ");
			sBuilder.append(" TO_CHAR (OM.ORDER_DATE, 'DD/MM/YYYY HH12:MI:SS AM') ORDER_DATE, ");
			sBuilder.append(" OM.TABLE_NO, ");
			sBuilder.append(" OM.WAITER_ID, ");
			sBuilder.append(" (SELECT KNOWN_AS FROM EMPLOYEE WHERE EMPLOYEE_ID = OM.WAITER_ID) WAITER_NAME, ");
			sBuilder.append(" OM.PAYABLE_AMOUNT, ");
			sBuilder.append(" OM.NET_PAY_AMOUNT, ");
			sBuilder.append(" OM.RECEIVED_AMOUNT, ");
			sBuilder.append(" OM.CHANGE_AMOUNT, ");
			sBuilder.append(" OM.CASH_PAY_AMOUNT, ");
			sBuilder.append(" OM.CARD_PAY_AMOUNT, ");
			sBuilder.append(" OM.BKASH_PAYMENT_AMOUNT, ");
			sBuilder.append(" OM.BKASH_TRAN_NO, ");
			sBuilder.append(" OM.DISCOUNT_AMOUNT, ");
			sBuilder.append(" OM.DISCOUNT_REFERENCE_BY, ");
			sBuilder.append(" OM.FINALIZED_YN, ");
			sBuilder.append(" OM.COMPLETED_YN, ");
			sBuilder.append(" (SELECT KNOWN_AS FROM EMPLOYEE WHERE EMPLOYEE_ID = OM.UPDATE_BY) UPDATE_BY, ");
			sBuilder.append(" TO_CHAR (OM.UPDATE_DATE, 'DD/MM/YYYY HH12:MI:SS AM') UPDATE_DATE, ");
			sBuilder.append(" (SELECT ITEM_NAME FROM L_ITEM WHERE ITEM_ID = IO.ITEM_ID) ITEM_NAME, ");
			sBuilder.append(" IO.QUANTITY, ");
			sBuilder.append(" IO.ITEM_PRICE, ");
			sBuilder.append(" IO.SUB_TOTAL ");
			sBuilder.append(" FROM ORDER_MANAGEMENT OM, ITEM_ORDER IO ");
			sBuilder.append(" WHERE OM.ORDER_ID = IO.ORDER_ID AND OM.ORDER_ID = :encOrderId ");
			

			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("encOrderId", oCipherUtils.decrypt(reportModel.getEncOrderId()));
			System.out.println("orderId " + oCipherUtils.decrypt(reportModel.getEncOrderId()));
			/*paramSource.addValue("fromDate", reportModel.getFromDate());
			paramSource.addValue("toDate", reportModel.getToDate());*/
			

			 System.out.println(sBuilder);

			// paramSource.addValue("regNo", reportModel.getRegistrationNo());

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				ReportModel oReportModel = new ReportModel();
				// oReportModel.setEncRegisterId(oCipherUtils.encrypt(String.valueOf(row.get("REGISTER_ID"))));
				oReportModel.setOrderId(oRemoveNull.nullRemove(String.valueOf(row.get("ORDER_ID"))));
				oReportModel.setOrderNo(oRemoveNull.nullRemove(String.valueOf(row.get("ORDER_NO"))));
				oReportModel.setOrderDate(oRemoveNull.nullRemove(String.valueOf(row.get("ORDER_DATE"))));
				oReportModel.setTableNo(oRemoveNull.nullRemove(String.valueOf(row.get("TABLE_NO"))));
				oReportModel.setWaiterId(oRemoveNull.nullRemove(String.valueOf(row.get("WAITER_ID"))));
				oReportModel.setWaiterName(oRemoveNull.nullRemove(String.valueOf(row.get("WAITER_NAME"))));
				oReportModel.setOrderTotalAmount(oRemoveNull.nullRemove(String.valueOf(row.get("PAYABLE_AMOUNT"))));
				oReportModel.setNetPayableAmount(oRemoveNull.nullRemove(String.valueOf(row.get("NET_PAY_AMOUNT"))));
				oReportModel.setReceivedAmount(oRemoveNull.nullRemove(String.valueOf(row.get("RECEIVED_AMOUNT"))));
				oReportModel.setChangeAmount(oRemoveNull.nullRemove(String.valueOf(row.get("CHANGE_AMOUNT"))));
				
				oReportModel.setCashPayAmount(oRemoveNull.nullRemove(String.valueOf(row.get("CASH_PAY_AMOUNT"))));
				/*if (oReportModel.getCashPayAmount().equals("0")) {
					oReportModel.setCashPayAmount("");
				}*/
				oReportModel.setCardPayAmount(oRemoveNull.nullRemove(String.valueOf(row.get("CARD_PAY_AMOUNT"))));
				/*if (oReportModel.getCardPayAmount().equals("0")) {
					oReportModel.setCardPayAmount("");
				}*/
				oReportModel.setBkashPaymentAmount(oRemoveNull.nullRemove(String.valueOf(row.get("BKASH_PAYMENT_AMOUNT"))));
				/*if (oReportModel.getBkashPaymentAmount().equals("0")) {
					oReportModel.setBkashPaymentAmount("");
				}*/
				oReportModel.setBkashTranNo(oRemoveNull.nullRemove(String.valueOf(row.get("BKASH_TRAN_NO"))));
				oReportModel.setDiscountCost(oRemoveNull.nullRemove(String.valueOf(row.get("DISCOUNT_AMOUNT"))));
				if (oReportModel.getDiscountCost().equals("0")) {
					oReportModel.setDiscountCost("");
				}
				oReportModel.setDiscountReferenceBy(oRemoveNull.nullRemove(String.valueOf(row.get("DISCOUNT_REFERENCE_BY"))));
				oReportModel.setFinalizedYn(oRemoveNull.nullRemove(String.valueOf(row.get("FINALIZED_YN"))));
				oReportModel.setCompletedYn(oRemoveNull.nullRemove(String.valueOf(row.get("COMPLETED_YN"))));
				oReportModel.setUpdateBy(oRemoveNull.nullRemove(String.valueOf(row.get("UPDATE_BY"))));
				oReportModel.setUpdateDate(oRemoveNull.nullRemove(String.valueOf(row.get("UPDATE_DATE"))));
				
				oReportModel.setItemName(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_NAME"))));
				oReportModel.setQuantity(oRemoveNull.nullRemove(String.valueOf(row.get("QUANTITY"))));
				oReportModel.setItemPrice(Integer.parseInt(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_PRICE")))));
				oReportModel.setSubTotal(oRemoveNull.nullRemove(String.valueOf(row.get("SUB_TOTAL"))));
				
				//System.out.println("price " + reportModel.getItemPrice());
				
				list.add(oReportModel);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JRDataSource ds = new JRBeanCollectionDataSource(list);

		return ds;
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
	
	
	public ReportModel updateItemWiseKitchenQT(ReportModel reportModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		ReportModel oReportModel = new ReportModel();
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		try {

			StringBuilder sBuilder = new StringBuilder();
			
			sBuilder.append(" UPDATE ITEM_ORDER SET PRINTED_YN = 'Y' WHERE PRINTED_YN <> 'Y' ");
			sBuilder.append(" AND ORDER_ID = :orderId  ");
			   
			/*sBuilder.append(" UPDATE ORDER_MANAGEMENT SET ORDER_CANCELED_YN = 'Y'  ");
			sBuilder.append(" WHERE ORDER_ID = :orderId  ");*/

			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("orderId", oCipherUtils.decrypt(oRemoveNull.nullRemove(reportModel.getEncOrderId())));

			npjt.update(sBuilder.toString(), paramSource);

		} catch (Exception ex) {
			oReportModel.setMessage("Error Updating Record !!!");
			oReportModel.setmCode("0000");
			ex.printStackTrace();
		}
		return oReportModel;
	}

	public ReportModel posReportSumData(ReportModel reportModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		ReportModel oReportModel = new ReportModel();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append(" SELECT COUNT (ORDER_ID) ORDER_ID, ");
		sBuilder.append(" NVL (SUM (DISCOUNT_AMOUNT),0) DISCOUNT_AMOUNT, ");
		sBuilder.append(" NVL (SUM (BKASH_PAYMENT_AMOUNT),0) BKASH_PAYMENT_AMOUNT, ");
		sBuilder.append(" NVL (SUM (CASH_PAY_AMOUNT),0) CASH_PAY_AMOUNT, ");
		sBuilder.append(" NVL (SUM (CARD_PAY_AMOUNT),0) CARD_PAY_AMOUNT ");
		sBuilder.append(" FROM ORDER_MANAGEMENT ");
		sBuilder.append(" WHERE FINALIZED_YN = 'Y' ");
		sBuilder.append(" AND TRUNC (ORDER_DATE) BETWEEN TO_DATE (:fromDate, 'DD/MM/YYYY') AND TO_DATE (:toDate, 'DD/MM/YYYY') ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("fromDate", reportModel.getFromDate());
		paramSource.addValue("toDate", reportModel.getToDate());

		 //System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oReportModel.setOrderId(oRemoveNull.nullRemove(String.valueOf(row.get("ORDER_ID"))));
			oReportModel.setDiscountSum(Double.parseDouble(oRemoveNull.nullRemove(String.valueOf(row.get("DISCOUNT_AMOUNT")))));
			oReportModel.setBkashPaySum(Double.parseDouble(oRemoveNull.nullRemove(String.valueOf(row.get("BKASH_PAYMENT_AMOUNT")))));
			oReportModel.setCashPaySum(Double.parseDouble(oRemoveNull.nullRemove(String.valueOf(row.get("CASH_PAY_AMOUNT")))));
			oReportModel.setCardPaySum(Double.parseDouble(oRemoveNull.nullRemove(String.valueOf(row.get("CARD_PAY_AMOUNT")))));
		}
		return oReportModel;
	}

	@Override
	public ReportModel getShopInfo(ReportModel reportModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		ReportModel oReportModel = new ReportModel();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append(" SELECT EMPLOYEE_ID, ");
		sBuilder.append(" SHOP_NAME, ");
		sBuilder.append(" SHOP_ADDRESS, ");
		sBuilder.append(" PHONE_NO, ");
		sBuilder.append(" EMAIL ");
		sBuilder.append(" FROM USER_INFO ");
		sBuilder.append(" WHERE EMPLOYEE_ID = :empId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("empId", reportModel.getEmployeeId());

		 //System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oReportModel.setEmployeeId(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_ID"))));
			oReportModel.setShopName(oRemoveNull.nullRemove(String.valueOf(row.get("SHOP_NAME"))));
			oReportModel.setShopAddress(oRemoveNull.nullRemove(String.valueOf(row.get("SHOP_ADDRESS"))));
			oReportModel.setPhoneNo(oRemoveNull.nullRemove(String.valueOf(row.get("PHONE_NO"))));
			oReportModel.setEmail(oRemoveNull.nullRemove(String.valueOf(row.get("EMAIL"))));
		}
		return oReportModel;
	}

/*	public ReportModel updateItemWiseKitchenQT(ReportModel reportModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		ReportModel oReportModel = new ReportModel();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_UPDATE_KITCHEN_QT");
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("P_ORDER_ID", oCipherUtils.decrypt(reportModel.getEncOrderId()));

			// inParamMap.put("P_UPDATE_DATE", student.getUpdateDate());

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oReportModel.setMessage((String) outParamMap.get("P_MESSAGE"));
			oReportModel.setmCode((String) outParamMap.get("P_MESSAGE_CODE"));
			// System.out.println("studentId " + oStudent.getStudentId());
		} catch (Exception ex) {
			oReportModel.setMessage("Error Saving Record !!!");
			oReportModel.setmCode("0000");
			ex.printStackTrace();
		}
		return oReportModel;
	}*/
	
	
	/* back up stock summary report report for stock date (only one date) 
	
	  public JRDataSource stockSummaryReportData(ReportModel reportModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<ReportModel> list = new ArrayList<ReportModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT DISTINCT ");
			sBuilder.append("PRODUCT_ID, ");
			sBuilder.append("PRODUCT_NAME, ");
			sBuilder.append("TO_CHAR (V.STOCK_DATE, 'DD/MM/YYYY') STOCK_DATE, ");
			sBuilder.append("V.INVENTORY_TYPE_NAME, ");
			sBuilder.append("(SELECT MASTER_UNIT || ' - ' || SAME_UNIT_NAME ");
			sBuilder.append("FROM VIEW_STOCK_SUMMARY ");
			sBuilder.append("WHERE STOCK_SUMMARY_TYPE_ID = 1 ");
			sBuilder.append("AND PRODUCT_ID = V.PRODUCT_ID ");
			sBuilder.append("AND INVENTORY_TYPE_ID = L.INVENTORY_TYPE_ID ");
			sBuilder.append("AND TO_CHAR(STOCK_DATE, 'DD/MM/YYYY') = :fromDate) ");
			sBuilder.append("STOCK_IN, ");
			sBuilder.append("(SELECT MASTER_UNIT || ' - ' || SAME_UNIT_NAME ");
			sBuilder.append("FROM VIEW_STOCK_SUMMARY ");
			sBuilder.append("WHERE STOCK_SUMMARY_TYPE_ID = 2 ");
			sBuilder.append("AND PRODUCT_ID = V.PRODUCT_ID ");
			sBuilder.append("AND INVENTORY_TYPE_ID = L.INVENTORY_TYPE_ID ");
			sBuilder.append("AND TO_CHAR(STOCK_DATE, 'DD/MM/YYYY') = :fromDate) ");
			sBuilder.append("STOCK_OUT, ");
			sBuilder.append("(SELECT MASTER_UNIT || ' - ' || SAME_UNIT_NAME ");
			sBuilder.append("FROM VIEW_STOCK_SUMMARY ");
			sBuilder.append("WHERE STOCK_SUMMARY_TYPE_ID = 3 ");
			sBuilder.append("AND PRODUCT_ID = V.PRODUCT_ID ");
			sBuilder.append("AND INVENTORY_TYPE_ID = L.INVENTORY_TYPE_ID ");
			sBuilder.append("AND TO_CHAR(STOCK_DATE, 'DD/MM/YYYY') = :fromDate) ");
			sBuilder.append("ITEM_ORDER_PRODUCT, ");
			sBuilder.append("(SELECT MASTER_UNIT || ' - ' || SAME_UNIT_NAME ");
			sBuilder.append("FROM VIEW_STOCK_SUMMARY ");
			sBuilder.append("WHERE STOCK_SUMMARY_TYPE_ID = 4 ");
			sBuilder.append("AND PRODUCT_ID = V.PRODUCT_ID ");
			sBuilder.append("AND INVENTORY_TYPE_ID = L.INVENTORY_TYPE_ID ");
			sBuilder.append("AND TO_CHAR(STOCK_DATE, 'DD/MM/YYYY') = :fromDate) ");
			sBuilder.append("WASTAGE "); 
			sBuilder.append("FROM VIEW_STOCK_SUMMARY V, L_INVENTORY_TYPE L ");
			sBuilder.append("WHERE V.INVENTORY_TYPE_ID = L.INVENTORY_TYPE_ID ");
			sBuilder.append("AND TO_CHAR(V.STOCK_DATE, 'DD/MM/YYYY') = :fromDate ");
			sBuilder.append("ORDER BY UPPER (PRODUCT_NAME) ASC ");
			
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("fromDate", reportModel.getFromDate());
			System.out.println("fromDate " + reportModel.getFromDate());
			System.out.println(sBuilder);

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				ReportModel oReportModel = new ReportModel();
				oReportModel.setProductId(oRemoveNull.nullRemove(String.valueOf(row.get("PRODUCT_ID"))));
				oReportModel.setProductName(oRemoveNull.nullRemove(String.valueOf(row.get("PRODUCT_NAME"))));
				oReportModel.setStockDate(oRemoveNull.nullRemove(String.valueOf(row.get("STOCK_DATE"))));
				oReportModel.setInventoryTypeName(oRemoveNull.nullRemove(String.valueOf(row.get("INVENTORY_TYPE_NAME"))));
				oReportModel.setStockIn(oRemoveNull.nullRemove(String.valueOf(row.get("STOCK_IN"))));
				oReportModel.setStockOut(oRemoveNull.nullRemove(String.valueOf(row.get("STOCK_OUT"))));
				oReportModel.setItemOrderProduct(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_ORDER_PRODUCT"))));
				oReportModel.setWastage(oRemoveNull.nullRemove(String.valueOf(row.get("WASTAGE"))));
				list.add(oReportModel);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		JRDataSource ds = new JRBeanCollectionDataSource(list);
		return ds;
	}*/
	
	
	public JRDataSource stockSummaryReportData(ReportModel reportModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<ReportModel> list = new ArrayList<ReportModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT DISTINCT V.PRODUCT_ID, ");
			sBuilder.append("PRODUCT_NAME, ");
			sBuilder.append("TO_CHAR (V.STOCK_DATE, 'DD/MM/YYYY') STOCK_DATE, ");
			sBuilder.append("V.INVENTORY_TYPE_NAME, ");
			sBuilder.append("SI.UNIT STOCK_IN, ");
			sBuilder.append("SO.UNIT STOCK_OUT, ");
			sBuilder.append("IOP.UNIT ITEM_ORDER_PRODUCT, ");
			sBuilder.append("WA.UNIT WASTAGE ");
			sBuilder.append("FROM VIEW_STOCK_SUMMARY V, ");
			sBuilder.append("L_INVENTORY_TYPE L, ");
			sBuilder.append("(SELECT STOCK_DATE, ");
			sBuilder.append("INVENTORY_TYPE_ID, ");
			sBuilder.append("PRODUCT_ID, ");
			sBuilder.append("MASTER_UNIT || ' - ' || SAME_UNIT_NAME UNIT ");
			sBuilder.append("FROM VIEW_STOCK_SUMMARY ");
			sBuilder.append("WHERE STOCK_SUMMARY_TYPE_ID = 1) SI, ");
			sBuilder.append("(SELECT STOCK_DATE, ");
			sBuilder.append("INVENTORY_TYPE_ID, ");
			sBuilder.append("PRODUCT_ID, ");
			sBuilder.append("MASTER_UNIT || ' - ' || SAME_UNIT_NAME UNIT ");
			sBuilder.append("FROM VIEW_STOCK_SUMMARY ");
			sBuilder.append("WHERE STOCK_SUMMARY_TYPE_ID = 2) SO, ");
			sBuilder.append("(SELECT STOCK_DATE, ");
			sBuilder.append("INVENTORY_TYPE_ID, ");
			sBuilder.append("PRODUCT_ID, ");
			sBuilder.append("MASTER_UNIT || ' - ' || SAME_UNIT_NAME UNIT ");
			sBuilder.append("FROM VIEW_STOCK_SUMMARY ");
			sBuilder.append("WHERE STOCK_SUMMARY_TYPE_ID = 3) IOP, ");
			sBuilder.append("(SELECT STOCK_DATE, ");
			sBuilder.append("INVENTORY_TYPE_ID, ");
			sBuilder.append("PRODUCT_ID, ");
			sBuilder.append("MASTER_UNIT || ' - ' || SAME_UNIT_NAME UNIT ");
			sBuilder.append("FROM VIEW_STOCK_SUMMARY ");
			sBuilder.append("WHERE STOCK_SUMMARY_TYPE_ID = 4) WA ");
			sBuilder.append("WHERE V.INVENTORY_TYPE_ID = L.INVENTORY_TYPE_ID ");
			sBuilder.append("AND V.STOCK_DATE = SI.STOCK_DATE(+) ");
			sBuilder.append("AND V.PRODUCT_ID = SI.PRODUCT_ID(+) ");
			sBuilder.append("AND V.INVENTORY_TYPE_ID = SI.INVENTORY_TYPE_ID(+) ");
			sBuilder.append("AND V.STOCK_DATE = SO.STOCK_DATE(+) ");
			sBuilder.append("AND V.PRODUCT_ID = SO.PRODUCT_ID(+) ");
			sBuilder.append("AND V.INVENTORY_TYPE_ID = SO.INVENTORY_TYPE_ID(+) ");
			sBuilder.append("AND V.STOCK_DATE = IOP.STOCK_DATE(+) ");
			sBuilder.append("AND V.PRODUCT_ID = IOP.PRODUCT_ID(+) ");
			sBuilder.append("AND V.INVENTORY_TYPE_ID = IOP.INVENTORY_TYPE_ID(+) ");
			sBuilder.append("AND V.STOCK_DATE = WA.STOCK_DATE(+) ");
			sBuilder.append("AND V.PRODUCT_ID = WA.PRODUCT_ID(+) ");
			sBuilder.append("AND V.INVENTORY_TYPE_ID = WA.INVENTORY_TYPE_ID(+) ");
			sBuilder.append("AND TO_CHAR (V.STOCK_DATE, 'DD/MM/YYYY') BETWEEN :fromDate  AND :toDate ");
			sBuilder.append("ORDER BY STOCK_DATE, UPPER (PRODUCT_NAME) ASC ");
			
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("fromDate", reportModel.getFromDate());
			paramSource.addValue("toDate", reportModel.getToDate());
			
			System.out.println("fromDate " + reportModel.getFromDate());
			System.out.println("toDate " + reportModel.getToDate());
			
			System.out.println(sBuilder);

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				ReportModel oReportModel = new ReportModel();
				oReportModel.setProductId(oRemoveNull.nullRemove(String.valueOf(row.get("PRODUCT_ID"))));
				oReportModel.setProductName(oRemoveNull.nullRemove(String.valueOf(row.get("PRODUCT_NAME"))));
				oReportModel.setStockDate(oRemoveNull.nullRemove(String.valueOf(row.get("STOCK_DATE"))));
				oReportModel.setInventoryTypeName(oRemoveNull.nullRemove(String.valueOf(row.get("INVENTORY_TYPE_NAME"))));
				oReportModel.setStockIn(oRemoveNull.nullRemove(String.valueOf(row.get("STOCK_IN"))));
				oReportModel.setStockOut(oRemoveNull.nullRemove(String.valueOf(row.get("STOCK_OUT"))));
				oReportModel.setItemOrderProduct(oRemoveNull.nullRemove(String.valueOf(row.get("ITEM_ORDER_PRODUCT"))));
				oReportModel.setWastage(oRemoveNull.nullRemove(String.valueOf(row.get("WASTAGE"))));
				list.add(oReportModel);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		JRDataSource ds = new JRBeanCollectionDataSource(list);
		return ds;
	}
	
	
	
}

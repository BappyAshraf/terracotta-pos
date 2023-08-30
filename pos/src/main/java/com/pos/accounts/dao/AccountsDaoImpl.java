package com.pos.accounts.dao;

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
import com.pos.accounts.model.AdCategoryList;
import com.pos.accounts.model.EmployeeMonthlyConsumption;
import com.pos.accounts.model.OwnerConsumptionInfo;
import com.pos.accounts.model.SalaryProcessModel;
import com.pos.accounts.model.StockProcess;
import com.pos.accounts.model.SupplierInfo;
import com.pos.common.RemoveNull;
import com.pos.hr.model.EmployeeInformation;
import com.pos.pointOfSale.model.PointOfSale;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

@Repository
public class AccountsDaoImpl implements AccountsDao {
	
	private SimpleJdbcCall simpleJdbcCall;
	private JdbcTemplate jdbcTemplate;
	CipherUtils oCipherUtils = new CipherUtils();
	@Autowired
	private DataSource dataSource;
	RemoveNull oRemoveNull = new RemoveNull();



	public List<EmployeeMonthlyConsumption> getEmpMonthlyConsumption(EmployeeMonthlyConsumption employeeMonthlyConsumption) {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<EmployeeMonthlyConsumption> oEmployeeMonthlyConsumptionList = new ArrayList<EmployeeMonthlyConsumption>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT CONSUMPTION_ID, EMPLOYEE_ID, ");
		sBuilder.append("(SELECT EMPLOYEE_NAME ");
		sBuilder.append("FROM EMPLOYEE ");
		sBuilder.append("WHERE EMPLOYEE_ID = EMC.EMPLOYEE_ID) ");
		sBuilder.append("EMPLOYEE_NAME, ");
		sBuilder.append("(SELECT DESIGNATION_NAME ");
		sBuilder.append("FROM L_DESIGNATION ");
		sBuilder.append("WHERE DESIGNATION_ID = (SELECT DESIGNATION_ID ");
		sBuilder.append("FROM EMPLOYEE ");
		sBuilder.append("WHERE EMPLOYEE_ID = EMC.EMPLOYEE_ID)) ");
		sBuilder.append("DESIGNATION_NAME, ");
		sBuilder.append("AD_CATEGORY_ID, ");
		sBuilder.append("(SELECT AD_CATEGORY_NAME || ' - ' || EARNING_DEDUCTION_TYPE_NAME ");
		sBuilder.append("FROM PAYROLL_EARN_DEDUCT_CATEGORY ");
		sBuilder.append("WHERE AD_CATEGORY_ID = EMC.AD_CATEGORY_ID) ");
		sBuilder.append("AD_CATEGORY_NAME, ");
		sBuilder.append("PURPOSE, ");
		sBuilder.append("AMOUNT, ");
		sBuilder.append("TO_CHAR (CONSUME_DATE, 'DD-MON-YYYY') CON_DATE, ");
		sBuilder.append("CASE WHEN TRUNC(CONSUME_DATE) < TRUNC(SYSDATE) THEN 'N' ELSE 'Y' END CONSUME_SYSDATE_YN ");
		sBuilder.append("FROM EMPLOYEE_MONTHLY_CONSUMPTION EMC ");
		sBuilder.append("WHERE EMPLOYEE_ID = :empId ");
		sBuilder.append("ORDER BY CONSUME_DATE DESC ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
			paramSource.addValue("empId", oCipherUtils.decrypt(employeeMonthlyConsumption.getEncEmployeeId()));
		
		//System.out.println("EMP iD  "+ oCipherUtils.decrypt(employeeMonthlyConsumption.getEncEmployeeId()));

		 //System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			EmployeeMonthlyConsumption oEmployeeMonthlyConsumption = new EmployeeMonthlyConsumption();
			
			oEmployeeMonthlyConsumption.setEncConsumeId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("CONSUMPTION_ID")))));
			oEmployeeMonthlyConsumption.setEncEmployeeId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_ID")))));
			oEmployeeMonthlyConsumption.setEmployeeName(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_NAME"))));
			oEmployeeMonthlyConsumption.setDesignation(oRemoveNull.nullRemove(String.valueOf(row.get("DESIGNATION_NAME"))));
			oEmployeeMonthlyConsumption.setAllowanceDeductCategoryName(oRemoveNull.nullRemove(String.valueOf(row.get("AD_CATEGORY_NAME"))));
			oEmployeeMonthlyConsumption.setPurpose(oRemoveNull.nullRemove(String.valueOf(row.get("PURPOSE"))));
			oEmployeeMonthlyConsumption.setAmount(oRemoveNull.nullRemove(String.valueOf(row.get("AMOUNT"))));
			oEmployeeMonthlyConsumption.setConsumeDate(oRemoveNull.nullRemove(String.valueOf(row.get("CON_DATE"))));
			oEmployeeMonthlyConsumption.setConsumeSysdateYn(oRemoveNull.nullRemove(String.valueOf(row.get("CONSUME_SYSDATE_YN"))));
			oEmployeeMonthlyConsumptionList.add(oEmployeeMonthlyConsumption);
		}
		return oEmployeeMonthlyConsumptionList;
	}
	
	
	public EmployeeMonthlyConsumption getConsumeInfo(EmployeeMonthlyConsumption employeeMonthlyConsumption) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		EmployeeMonthlyConsumption oEmployeeMonthlyConsumption = new EmployeeMonthlyConsumption();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		
		sBuilder.append("SELECT CONSUMPTION_ID,	EMPLOYEE_ID, ");
		sBuilder.append("(SELECT EMPLOYEE_NAME ");
		sBuilder.append("FROM EMPLOYEE ");
		sBuilder.append("WHERE EMPLOYEE_ID = EMC.EMPLOYEE_ID) ");
		sBuilder.append("EMPLOYEE_NAME, ");
		sBuilder.append("(SELECT DESIGNATION_NAME ");
		sBuilder.append("FROM L_DESIGNATION ");
		sBuilder.append("WHERE DESIGNATION_ID = (SELECT DESIGNATION_ID ");
		sBuilder.append("FROM EMPLOYEE ");
		sBuilder.append("WHERE EMPLOYEE_ID = EMC.EMPLOYEE_ID)) ");
		sBuilder.append("DESIGNATION_NAME, ");
		sBuilder.append("AD_CATEGORY_ID, ");
		sBuilder.append("PURPOSE, ");
		sBuilder.append("AMOUNT, ");
		sBuilder.append("TO_CHAR (CONSUME_DATE, 'DD-MON-YYYY') CONSUME_DATE ");
		sBuilder.append("FROM EMPLOYEE_MONTHLY_CONSUMPTION EMC ");
		sBuilder.append("WHERE CONSUMPTION_ID = :consumeId ");
		sBuilder.append("ORDER BY CONSUME_DATE DESC ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
			paramSource.addValue("consumeId", oCipherUtils.decrypt(employeeMonthlyConsumption.getEncConsumeId()));
		
		//System.out.println("EMP iD  "+ oCipherUtils.decrypt(employeeMonthlyConsumption.getEncEmployeeId()));

		 //System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {

			oEmployeeMonthlyConsumption.setEncConsumeId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("CONSUMPTION_ID")))));
			oEmployeeMonthlyConsumption.setEncEmployeeId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_ID")))));
			oEmployeeMonthlyConsumption.setEmployeeName(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_NAME"))));
			oEmployeeMonthlyConsumption.setDesignation(oRemoveNull.nullRemove(String.valueOf(row.get("DESIGNATION_NAME"))));
			oEmployeeMonthlyConsumption.setAllowanceDeductCategoryId(oRemoveNull.nullRemove(String.valueOf(row.get("AD_CATEGORY_ID"))));
			oEmployeeMonthlyConsumption.setPurpose(oRemoveNull.nullRemove(String.valueOf(row.get("PURPOSE"))));
			oEmployeeMonthlyConsumption.setAmount(oRemoveNull.nullRemove(String.valueOf(row.get("AMOUNT"))));
			oEmployeeMonthlyConsumption.setConsumeDate(oRemoveNull.nullRemove(String.valueOf(row.get("CONSUME_DATE"))));
		}
		return oEmployeeMonthlyConsumption;
	}
	
	
	public EmployeeMonthlyConsumption empMonthlyConsumptionSave(EmployeeMonthlyConsumption employeeMonthlyConsumption) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		EmployeeMonthlyConsumption oEmployeeMonthlyConsumption = new EmployeeMonthlyConsumption();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_EMP_MONTHLY_CONSUME_SAVE");

			Map<String, Object> inParamMap = new HashMap<String, Object>();
			
			inParamMap.put("P_CONSUMPTION_ID", oCipherUtils.decrypt(oRemoveNull.nullRemove(employeeMonthlyConsumption.getEncConsumeId())));
			inParamMap.put("P_EMPLOYEE_ID", oCipherUtils.decrypt(oRemoveNull.nullRemove(employeeMonthlyConsumption.getEncEmployeeId())));
			inParamMap.put("P_AD_CATEGORY_ID", employeeMonthlyConsumption.getAllowanceDeductCategoryId());
			inParamMap.put("P_CONSUME_DATE", employeeMonthlyConsumption.getConsumeDate());
			inParamMap.put("P_AMOUNT", employeeMonthlyConsumption.getAmount());
			inParamMap.put("P_PURPOSE", employeeMonthlyConsumption.getPurpose());
			inParamMap.put("P_UPDATE_BY", employeeMonthlyConsumption.getUpdateBy());
			
			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oEmployeeMonthlyConsumption.setMessage(oRemoveNull.nullRemove((String) outParamMap.get("P_MESSAGE")));
			oEmployeeMonthlyConsumption.setMessageCode(oRemoveNull.nullRemove((String) outParamMap.get("P_MESSAGE_CODE")));
			
			//System.out.println(" P_NEW_EMP_ID "+ (String) outParamMap.get("P_NEW_EMP_ID"));

		} catch (Exception ex) {
			ex.printStackTrace();
			oEmployeeMonthlyConsumption.setMessage("Error Occured!!!");
			oEmployeeMonthlyConsumption.setMessageCode("0000");
		}
		return oEmployeeMonthlyConsumption;
	}
	
	
	public List<SalaryProcessModel> salaryProcessSetup(SalaryProcessModel salaryProcessModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<SalaryProcessModel> oSalaryProcessModelList = new ArrayList<SalaryProcessModel>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT E.EMPLOYEE_ID, ");
		sBuilder.append("(SELECT AD_CATEGORY_ID ");
		sBuilder.append("FROM PAYROLL_EARN_DEDUCT_CATEGORY ");
		sBuilder.append("WHERE AD_CATEGORY_ID = 1) ");
		sBuilder.append("AD_CATEGORY_ID, ");
		sBuilder.append("(SELECT AD_CATEGORY_NAME || ' - ' || EARNING_DEDUCTION_TYPE_NAME ");
		sBuilder.append("FROM PAYROLL_EARN_DEDUCT_CATEGORY ");
		sBuilder.append("WHERE AD_CATEGORY_ID = 1) ");
		sBuilder.append("AD_CATEGORY_NAME, ");
		sBuilder.append("NVL (E.FIXED_SALARY, 0.00) AMOUNT ");
		sBuilder.append("FROM EMPLOYEE E ");
		sBuilder.append("WHERE EMPLOYEE_ID = :empId ");
		sBuilder.append("UNION ");
		sBuilder.append("SELECT EMPLOYEE_ID, ");
		sBuilder.append("AD_CATEGORY_ID, ");
		sBuilder.append("(SELECT AD_CATEGORY_NAME || ' - ' || EARNING_DEDUCTION_TYPE_NAME ");
		sBuilder.append("FROM PAYROLL_EARN_DEDUCT_CATEGORY ");
		sBuilder.append("WHERE AD_CATEGORY_ID = EMC.AD_CATEGORY_ID) ");
		sBuilder.append("AD_CATEGORY_NAME, ");
		sBuilder.append("AMOUNT ");
		sBuilder.append("FROM EMPLOYEE_MONTHLY_CONSUMPTION EMC ");
		sBuilder.append("WHERE EMPLOYEE_ID = :empId ");
		sBuilder.append("AND  TO_CHAR(CONSUME_DATE, 'YYYY') = :year ");
		sBuilder.append("AND  TO_CHAR(CONSUME_DATE, 'MM') = :month ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
			paramSource.addValue("empId", oCipherUtils.decrypt(salaryProcessModel.getEncEmployeeId()));
			paramSource.addValue("year", salaryProcessModel.getYear());
			paramSource.addValue("month", salaryProcessModel.getMonth());
		
		//System.out.println("EMP iD  "+ oCipherUtils.decrypt(employeeMonthlyConsumption.getEncEmployeeId()));

		 //System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			SalaryProcessModel oSalaryProcessModel = new SalaryProcessModel();
			
			oSalaryProcessModel.setEncEmployeeId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_ID")))));
			oSalaryProcessModel.setAllowanceDeductCategoryId(oRemoveNull.nullRemove(String.valueOf(row.get("AD_CATEGORY_ID"))));
			oSalaryProcessModel.setEncCategoryId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("AD_CATEGORY_ID")))));
			oSalaryProcessModel.setAllowanceDeductCategoryName(oRemoveNull.nullRemove(String.valueOf(row.get("AD_CATEGORY_NAME"))));
			oSalaryProcessModel.setAmount(oRemoveNull.nullRemove(String.valueOf(row.get("AMOUNT"))));
			oSalaryProcessModelList.add(oSalaryProcessModel);
		}
		return oSalaryProcessModelList;
	}
	
	public SalaryProcessModel grossSalary(SalaryProcessModel salaryProcessModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		SalaryProcessModel oSalaryProcessModel = new SalaryProcessModel();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT PD.PAYROLL_ID, ");
		sBuilder.append("SUM (PD.AMOUNT)AMOUNT, ");
		sBuilder.append("(SELECT DECODE (FINALIZED_YN,  'Y', 'YES',  'N', 'NO') ");
		sBuilder.append("FROM PAYROLL_MASTER "); 
		sBuilder.append("WHERE PAYROLL_ID = PM.PAYROLL_ID) ");
		sBuilder.append("FINALIZED_YN ");
		sBuilder.append("FROM PAYROLL_MASTER PM, PAYROLL_DETAIL PD ");
		sBuilder.append("WHERE EMPLOYEE_ID = :empId ");
		sBuilder.append("AND PM.PAYROLL_ID = PD.PAYROLL_ID ");
		sBuilder.append("AND PAYROLL_YEAR = :year ");
		sBuilder.append("AND PAYROLL_MONTH = :month ");
		sBuilder.append("GROUP BY PM.PAYROLL_ID, PD.PAYROLL_ID ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
			paramSource.addValue("empId", oCipherUtils.decrypt(salaryProcessModel.getEncEmployeeId()));
			paramSource.addValue("year", salaryProcessModel.getYear());
			paramSource.addValue("month", salaryProcessModel.getMonth());
		
		//System.out.println("EMP iD  "+ oCipherUtils.decrypt(employeeMonthlyConsumption.getEncEmployeeId()));

		 //System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oSalaryProcessModel.setEncPayrollId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("PAYROLL_ID")))));
			oSalaryProcessModel.setAmount(oRemoveNull.nullRemove(String.valueOf(row.get("AMOUNT"))));
			oSalaryProcessModel.setFinalizedYN(oRemoveNull.nullRemove(String.valueOf(row.get("FINALIZED_YN"))));
		}
		return oSalaryProcessModel;
	}
	
	
	public List<SalaryProcessModel> salaryProcessedSearch(SalaryProcessModel salaryProcessModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<SalaryProcessModel> oSalaryProcessModelList = new ArrayList<SalaryProcessModel>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT PM.EMPLOYEE_ID, PM.PAYROLL_ID, ");
		sBuilder.append("PD.AD_CATEGORY_ID, ");
		sBuilder.append("(SELECT AD_CATEGORY_NAME || ' - ' || EARNING_DEDUCTION_TYPE_NAME ");
		sBuilder.append("FROM PAYROLL_EARN_DEDUCT_CATEGORY ");
		sBuilder.append("WHERE AD_CATEGORY_ID = PD.AD_CATEGORY_ID) ");
		sBuilder.append("AD_CATEGORY_NAME, ");
		sBuilder.append("(SELECT EARNING_DEDUCTION_TYPE_ID ");
		sBuilder.append("FROM PAYROLL_EARN_DEDUCT_CATEGORY ");
		sBuilder.append("WHERE AD_CATEGORY_ID = PD.AD_CATEGORY_ID) ");
		sBuilder.append("EARNING_DEDUCTION_TYPE_ID, ");
		//sBuilder.append("DECODE (PM.FINALIZED_YN,  'Y', 'YES',  'N', 'NO') FINALIZED_YN, ");
		sBuilder.append("ABS(PD.AMOUNT)AMOUNT ");
		sBuilder.append("FROM PAYROLL_MASTER PM, PAYROLL_DETAIL PD ");
		sBuilder.append("WHERE EMPLOYEE_ID = :empId ");
		sBuilder.append("AND PM.PAYROLL_ID = PD.PAYROLL_ID ");
		sBuilder.append("AND PAYROLL_YEAR = :year ");
		sBuilder.append("AND PAYROLL_MONTH = :month ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		paramSource.addValue("empId", oCipherUtils.decrypt(salaryProcessModel.getEncEmployeeId()));
		paramSource.addValue("year", salaryProcessModel.getYear());
		paramSource.addValue("month", salaryProcessModel.getMonth());
		
		//System.out.println("EMP iD  "+ oCipherUtils.decrypt(employeeMonthlyConsumption.getEncEmployeeId()));

		 //System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			SalaryProcessModel oSalaryProcessModel = new SalaryProcessModel();
			
			oSalaryProcessModel.setEncEmployeeId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_ID")))));
			oSalaryProcessModel.setEncPayrollId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("PAYROLL_ID")))));
			//oSalaryProcessModel.setAllowanceDeductCategoryId(oRemoveNull.nullRemove(String.valueOf(row.get("AD_CATEGORY_ID"))));
			//oSalaryProcessModel.setEncCategoryId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("AD_CATEGORY_ID")))));
			oSalaryProcessModel.setAdCategoryTypeId(oRemoveNull.nullRemove(String.valueOf(row.get("EARNING_DEDUCTION_TYPE_ID"))));
			oSalaryProcessModel.setAllowanceDeductCategoryName(oRemoveNull.nullRemove(String.valueOf(row.get("AD_CATEGORY_NAME"))));
			oSalaryProcessModel.setAmount(oRemoveNull.nullRemove(String.valueOf(row.get("AMOUNT"))));
			//oSalaryProcessModel.setFinalizedYN(oRemoveNull.nullRemove(String.valueOf(row.get("FINALIZED_YN"))));
			oSalaryProcessModelList.add(oSalaryProcessModel);
		}
		return oSalaryProcessModelList;
	}
	
	
	public SalaryProcessModel salaryProcessSave(SalaryProcessModel salaryProcessModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		SalaryProcessModel oSalaryProcessModel = new SalaryProcessModel();
		try {
			Connection conn = jdbcTemplate.getDataSource().getConnection();

			StructDescriptor recDescriptor = StructDescriptor.createDescriptor("SALARY_PROCESS_ARRAY", conn);

			ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor("SALARY_PROCESS_ARRAY_TAB", conn);

			List<AdCategoryList> adCategoryIdList = salaryProcessModel.getAdCategoryIdList();
			
			// System.out.println("category list size " + salaryProcessModel.getAdCategoryIdList().size());
			
			int arraySize = 0;

			if (adCategoryIdList != null && adCategoryIdList.size() > 0) {
				for (AdCategoryList oAdCategoryList : adCategoryIdList) {
					if (oAdCategoryList.getEncCategoryId() != null && oAdCategoryList.getEncCategoryId().length() >0) {
						arraySize = arraySize + 1;
					}
				}
			}

			// System.out.println("arraySize = " + arraySize);

			if (arraySize < 1) {
				salaryProcessModel.setMessage("Please at First process data !!");
				salaryProcessModel.setMessageCode("0000");
				return salaryProcessModel;
			}

			Object[] array_of_records = new Object[arraySize];

			Object[] java_record_array = new Object[2];

			int i = 0;

			if (adCategoryIdList !=null  && adCategoryIdList.size() > 0) {
				for (AdCategoryList oAdCategoryList : adCategoryIdList) {

					if (oAdCategoryList.getEncCategoryId() != null && oAdCategoryList.getEncCategoryId().length() >0) {

						java_record_array[0] = oCipherUtils.decrypt(oAdCategoryList.getEncCategoryId());
						java_record_array[1] = oAdCategoryList.getAmount();

						STRUCT oracle_record = new STRUCT(recDescriptor, conn, java_record_array);
						array_of_records[i] = oracle_record;
						i = i + 1;

						// System.out.println("java_record_array[0] " + java_record_array[0]); 
						// System.out.println("java_record_array[1] " + java_record_array[1]);
					}
				}
			}

			ARRAY oracle_array = new ARRAY(arrayDescriptor, conn, array_of_records);

			CallableStatement oCallStmt = dataSource.getConnection().prepareCall("{call PRO_SALARY_PROCESS_SAVE(?,?,?,?,?,?,?)}");

			oCallStmt.setString(1, oCipherUtils.decrypt(salaryProcessModel.getEncEmployeeId()));
			oCallStmt.setString(2, salaryProcessModel.getYear());
			oCallStmt.setString(3, salaryProcessModel.getMonth());
			oCallStmt.setArray(4,  oracle_array);
			oCallStmt.setString(5,  salaryProcessModel.getUpdateBy());
			
			oCallStmt.registerOutParameter(6, java.sql.Types.CHAR);
			oCallStmt.registerOutParameter(7, java.sql.Types.CHAR);

			oCallStmt.execute();

			salaryProcessModel.setMessage(oCallStmt.getString(6));
			salaryProcessModel.setMessageCode(oCallStmt.getString(7));

			oCallStmt.close();
			conn.close();
			
			//System.out.println(" P_NEW_EMP_ID "+ (String) outParamMap.get("P_NEW_EMP_ID"));

		} catch (Exception ex) {
			ex.printStackTrace();
			oSalaryProcessModel.setMessage("Error Occured !!");
			oSalaryProcessModel.setMessageCode("0000");
		}
		return oSalaryProcessModel;
	}
	
	public SalaryProcessModel salaryProcessFinalize(SalaryProcessModel salaryProcessModel) {
		 jdbcTemplate = new JdbcTemplate(dataSource);
		    try{
		StringBuilder sBuilder = new StringBuilder();
		
		sBuilder.append(" UPDATE PAYROLL_MASTER SET FINALIZED_YN = 'Y' WHERE PAYROLL_ID = " + oCipherUtils.decrypt(salaryProcessModel.getEncPayrollId()));
				
		jdbcTemplate.update(sBuilder.toString());
		
		 
		    }  catch (Exception ex)  {
		      ex.printStackTrace();
		    }
		    
		    
		 return salaryProcessModel;
		  
	}

	public List<SupplierInfo> supplierList(SupplierInfo supplierInfo) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<SupplierInfo> oSupplierList = new ArrayList<SupplierInfo>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT SUPPLIER_ID, ");
		sBuilder.append(" SUPPLIER_NAME, ");
		sBuilder.append(" ADDRESS, ");
		sBuilder.append(" MOBILE_NO, ");
		sBuilder.append(" ENABLED_YN ");
		sBuilder.append(" FROM SUPPLIER_INFO ");
		sBuilder.append(" WHERE 1 = 1 ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		if (supplierInfo.getSupplierName() != null && supplierInfo.getSupplierName().length() > 0) {
			sBuilder.append(" AND SUPPLIER_NAME LIKE :supplierName ");
			paramSource.addValue("supplierName", "%" + supplierInfo.getSupplierName()+ "%");
		} 
		if(supplierInfo.getSupplierAddress() != null && supplierInfo.getSupplierAddress().length() > 0) {
			sBuilder.append(" AND ADDRESS LIKE :supplierAddress ");
			paramSource.addValue("supplierAddress", "%" + supplierInfo.getSupplierAddress()+ "%");
		} 
		if(supplierInfo.getMobileNo() != null && supplierInfo.getMobileNo().length() > 0) {
			sBuilder.append(" AND MOBILE_NO = :mobileNo ");
			paramSource.addValue("mobileNo", supplierInfo.getMobileNo());
		} 
		if(supplierInfo.getEnableYn() != null && supplierInfo.getEnableYn().length() > 0) {
			sBuilder.append(" AND ENABLED_YN = :enableYn ");
			paramSource.addValue("enableYn", supplierInfo.getEnableYn());
		} 
		System.out.println(sBuilder);
		//System.out.println("productId " + inventory.getProductId());

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);
		
		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			SupplierInfo oSupplierInfo = new SupplierInfo();
			oSupplierInfo.setEncSupplierId(oRemoveNull.nullRemove(oCipherUtils.encrypt((String.valueOf(row.get("SUPPLIER_ID"))))));
			oSupplierInfo.setSupplierId(oRemoveNull.nullRemove((String.valueOf(row.get("SUPPLIER_ID")))));
			oSupplierInfo.setSupplierName(oRemoveNull.nullRemove((String.valueOf(row.get("SUPPLIER_NAME")))));
			oSupplierInfo.setSupplierAddress(oRemoveNull.nullRemove((String.valueOf(row.get("ADDRESS")))));
			oSupplierInfo.setMobileNo(oRemoveNull.nullRemove((String.valueOf(row.get("MOBILE_NO")))));
			oSupplierInfo.setEnableYn(oRemoveNull.nullRemove((String.valueOf(row.get("ENABLED_YN")))));
			if (oSupplierInfo.getEnableYn().equals("Y")) {
				oSupplierInfo.setEnableYn("Active");
			} else {
				oSupplierInfo.setEnableYn("Inactive");
			}
			oSupplierList.add(oSupplierInfo);
		}
		return oSupplierList;
	}

	public SupplierInfo saveSupplier(SupplierInfo supplierInfo) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		SupplierInfo oSupplierInfo = new SupplierInfo();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_SUPPLIER_SAVE");
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			System.out.println("dfd" + supplierInfo.getEncSupplierId());
			inParamMap.put("P_SUPPLIER_ID", oCipherUtils.decrypt(supplierInfo.getEncSupplierId()));
			inParamMap.put("P_SUPPLIER_NAME", supplierInfo.getSupplierName());
			inParamMap.put("P_SUPPLIER_ADDRESS", supplierInfo.getSupplierAddress());
			inParamMap.put("P_ENABLE_YN", supplierInfo.getEnableYn());
			inParamMap.put("P_SUPPLIER_MOBILE_NO", supplierInfo.getMobileNo());
			inParamMap.put("P_UPDATE_BY", supplierInfo.getUpdateBy());

			// inParamMap.put("P_UPDATE_DATE", student.getUpdateDate());

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oSupplierInfo.setMessage((String) outParamMap.get("P_MESSAGE"));
			oSupplierInfo.setmCode((String) outParamMap.get("P_MESSAGE_CODE"));
			// System.out.println("studentId " + oStudent.getStudentId());
		} catch (Exception ex) {
			oSupplierInfo.setMessage("Error Saving Record !!!");
			oSupplierInfo.setmCode("0000");
			ex.printStackTrace();
		}
		return oSupplierInfo;
	}

	public SupplierInfo getSupplierInfo(SupplierInfo supplierInfo) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		SupplierInfo oSupplierInfo = new SupplierInfo();
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append(" SELECT SUPPLIER_ID, ");
		sBuilder.append(" SUPPLIER_NAME, ");
		sBuilder.append(" MOBILE_NO, ");
		sBuilder.append(" ADDRESS, ");
		sBuilder.append(" ENABLED_YN ");
		sBuilder.append(" FROM SUPPLIER_INFO ");
		sBuilder.append(" WHERE SUPPLIER_ID = :encSupplierId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("encSupplierId", oCipherUtils.decrypt(supplierInfo.getEncSupplierId()));

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oSupplierInfo.setEncSupplierId(oCipherUtils.encrypt(String.valueOf(row.get("SUPPLIER_ID"))));
			oSupplierInfo.setSupplierId(oRemoveNull.nullRemove(String.valueOf(row.get("SUPPLIER_ID"))));
			oSupplierInfo.setSupplierName(oRemoveNull.nullRemove(String.valueOf(row.get("SUPPLIER_NAME"))));
			oSupplierInfo.setMobileNo(oRemoveNull.nullRemove(String.valueOf(row.get("MOBILE_NO"))));
			oSupplierInfo.setSupplierAddress(oRemoveNull.nullRemove(String.valueOf(row.get("ADDRESS"))));
			oSupplierInfo.setEnableYn(oRemoveNull.nullRemove(String.valueOf(row.get("ENABLED_YN"))));
		}

		return oSupplierInfo;
	}
	
	
	//////////////////Owner Consumption Info///////////////////////
	
	public List<OwnerConsumptionInfo> getOwnerConsumptionList(OwnerConsumptionInfo ownerConsumptionInfo) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<OwnerConsumptionInfo> oOwnerConsumptionInfoList = new ArrayList<OwnerConsumptionInfo>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT CONSUMPTION_ID, ");
		sBuilder.append(" (SELECT EMPLOYEE_NAME FROM EMPLOYEE WHERE EMPLOYEE_ID = OFC.OWNER_ID)EMPLOYEE_NAME, ");
		sBuilder.append(" (SELECT MEMBER_NO|| '-' ||MEMBER_NAME FROM MEMBERSHIP WHERE MEMBER_ID = OFC.MEMBER_ID)MEMBER_NO_NAME, ");
		sBuilder.append("ITEM_ID, ");
		sBuilder.append(" (SELECT ITEM_NAME FROM L_ITEM WHERE ITEM_ID = OFC.ITEM_ID) ITEM_NAME, ");
		sBuilder.append("QUANTITY, ");
		sBuilder.append("TO_CHAR (CONSUME_DATE, 'DD-MON-YYYY') CONSUMED_DATE, ");
		sBuilder.append("REMARKS, ");
		sBuilder.append("(SELECT EMPLOYEE_NAME ");
		sBuilder.append("FROM EMPLOYEE ");
		sBuilder.append("WHERE EMPLOYEE_ID = OFC.UPDATE_BY) ");
		sBuilder.append("UPDATE_BY, ");
		sBuilder.append(" CASE WHEN TRUNC (UPDATE_DATE) = TRUNC (SYSDATE) THEN 'Y' ELSE 'N' END ");
		sBuilder.append(" EDITABLE ");
		sBuilder.append(" FROM OWNER_FOOD_CONSUMPTION OFC ");
		sBuilder.append(" WHERE COMPLETED_YN IS NULL OR COMPLETED_YN = 'N' ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		/*if(ownerConsumptionInfo.getEmployeeId() != null && ownerConsumptionInfo.getEmployeeId().length() > 0) {
		paramSource.addValue("empId", ownerConsumptionInfo.getEmployeeId());
		} */
		
		sBuilder.append("ORDER BY CONSUME_DATE DESC, CONSUMPTION_ID DESC ");
		
		//System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);
		
		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			OwnerConsumptionInfo oOwnerConsumptionInfo = new OwnerConsumptionInfo();
			oOwnerConsumptionInfo.setEncConsumeId(oRemoveNull.nullRemove(oCipherUtils.encrypt((String.valueOf(row.get("CONSUMPTION_ID"))))));
			
			oOwnerConsumptionInfo.setEmployeeName(oRemoveNull.nullRemove((String.valueOf(row.get("EMPLOYEE_NAME")))));
			
			oOwnerConsumptionInfo.setMemberName(oRemoveNull.nullRemove((String.valueOf(row.get("MEMBER_NO_NAME")))));
			
			oOwnerConsumptionInfo.setItemId(oRemoveNull.nullRemove((String.valueOf(row.get("ITEM_ID")))));
			oOwnerConsumptionInfo.setItemName(oRemoveNull.nullRemove((String.valueOf(row.get("ITEM_NAME")))));
			oOwnerConsumptionInfo.setQuantity(oRemoveNull.nullRemove((String.valueOf(row.get("QUANTITY")))));
			oOwnerConsumptionInfo.setConsumeDate(oRemoveNull.nullRemove((String.valueOf(row.get("CONSUMED_DATE")))));
			oOwnerConsumptionInfo.setRemarks(oRemoveNull.nullRemove((String.valueOf(row.get("REMARKS")))));
			oOwnerConsumptionInfo.setUpdateBy(oRemoveNull.nullRemove((String.valueOf(row.get("UPDATE_BY")))));
			oOwnerConsumptionInfo.setEditable(oRemoveNull.nullRemove((String.valueOf(row.get("EDITABLE")))));
			
			oOwnerConsumptionInfoList.add(oOwnerConsumptionInfo);
		}
		return oOwnerConsumptionInfoList;
	}
	
	
	public OwnerConsumptionInfo ownerConsumptionSave(OwnerConsumptionInfo ownerConsumptionInfo) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		OwnerConsumptionInfo oOwnerConsumptionInfo = new OwnerConsumptionInfo();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_OWNER_CONSUME_SAVE");

			Map<String, Object> inParamMap = new HashMap<String, Object>();
			
			inParamMap.put("P_CONSUMPTION_ID", oCipherUtils.decrypt(oRemoveNull.nullRemove(ownerConsumptionInfo.getEncConsumeId())));
			inParamMap.put("P_EMPLOYEE_ID", ownerConsumptionInfo.getEmployeeId());
			inParamMap.put("P_MEMBER_ID", ownerConsumptionInfo.getMemberId());
			inParamMap.put("P_CONSUME_DATE", ownerConsumptionInfo.getConsumeDate());
			inParamMap.put("P_ITEM_ID", ownerConsumptionInfo.getItemId());
			inParamMap.put("P_QUANTITY", ownerConsumptionInfo.getQuantity());
			inParamMap.put("P_PRICE", ownerConsumptionInfo.getPrice());
			inParamMap.put("P_REMARKS", ownerConsumptionInfo.getRemarks());
			inParamMap.put("P_UPDATE_BY", ownerConsumptionInfo.getUpdateBy());
			
			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oOwnerConsumptionInfo.setMessage(oRemoveNull.nullRemove((String) outParamMap.get("P_MESSAGE")));
			oOwnerConsumptionInfo.setMessageCode(oRemoveNull.nullRemove((String) outParamMap.get("P_MESSAGE_CODE")));
			
			System.out.println("ownerConsumptionInfo.getEncConsumeId() " + oCipherUtils.decrypt(oRemoveNull.nullRemove(ownerConsumptionInfo.getEncConsumeId())));

		} catch (Exception ex) {
			ex.printStackTrace();
			oOwnerConsumptionInfo.setMessage("Error Occured!!!");
			oOwnerConsumptionInfo.setMessageCode("0000");
		}
		return oOwnerConsumptionInfo;
	}
	
		
	public OwnerConsumptionInfo getOwnerConsumption(OwnerConsumptionInfo ownerConsumptionInfo) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		 
		OwnerConsumptionInfo oOwnerConsumptionInfo = new OwnerConsumptionInfo();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT CONSUMPTION_ID, ");
		sBuilder.append("OWNER_ID, ");
		sBuilder.append("MEMBER_ID, ");
		sBuilder.append("TO_CHAR (CONSUME_DATE, 'DD/MM/YYYY')CONSUME_DATE, ");
		sBuilder.append("ITEM_ID, ");
		sBuilder.append("QUANTITY, ");
		sBuilder.append("REMARKS ");
		sBuilder.append("FROM OWNER_FOOD_CONSUMPTION ");
		sBuilder.append("WHERE CONSUMPTION_ID = :cosumeId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		paramSource.addValue("cosumeId", oCipherUtils.decrypt(oRemoveNull.nullRemove(ownerConsumptionInfo.getEncConsumeId())));
		
		//System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);
		
		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oOwnerConsumptionInfo.setEncConsumeId(oRemoveNull.nullRemove(oCipherUtils.encrypt((String.valueOf(row.get("CONSUMPTION_ID"))))));
			oOwnerConsumptionInfo.setEmployeeId(oRemoveNull.nullRemove((String.valueOf(row.get("OWNER_ID")))));
			oOwnerConsumptionInfo.setMemberId(oRemoveNull.nullRemove((String.valueOf(row.get("MEMBER_ID")))));
			oOwnerConsumptionInfo.setConsumeDate(oRemoveNull.nullRemove((String.valueOf(row.get("CONSUME_DATE")))));
			oOwnerConsumptionInfo.setItemId(oRemoveNull.nullRemove((String.valueOf(row.get("ITEM_ID")))));
			oOwnerConsumptionInfo.setQuantity(oRemoveNull.nullRemove((String.valueOf(row.get("QUANTITY")))));
			oOwnerConsumptionInfo.setRemarks(oRemoveNull.nullRemove((String.valueOf(row.get("REMARKS")))));
		}
		return oOwnerConsumptionInfo;
	}
	
	
	public OwnerConsumptionInfo finalizeOwnerConsumeInfo(OwnerConsumptionInfo ownerConsumptionInfo) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		OwnerConsumptionInfo oOwnerConsumptionInfo = new OwnerConsumptionInfo();
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append(" UPDATE OWNER_FOOD_CONSUMPTION SET COMPLETED_YN = 'Y'  ");
			sBuilder.append(" WHERE CONSUMPTION_ID = :consumeId  ");
			
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("consumeId", oCipherUtils.decrypt(oRemoveNull.nullRemove(ownerConsumptionInfo.getEncConsumeId())));
			
			npjt.update(sBuilder.toString(), paramSource);
			
			oOwnerConsumptionInfo.setMessage("Finalized Successfully");
			oOwnerConsumptionInfo.setMessageCode("1111");
				
				} catch (Exception ex) {
					oOwnerConsumptionInfo.setMessage("Error Finalizing Record !!!");
					oOwnerConsumptionInfo.setMessageCode("0000");
			ex.printStackTrace();
		}
		return oOwnerConsumptionInfo;
	}
	
	
	public List<OwnerConsumptionInfo> getOwnerConsumptionHistoryList(OwnerConsumptionInfo ownerConsumptionInfo) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<OwnerConsumptionInfo> oConsumptionHistoryList = new ArrayList<OwnerConsumptionInfo>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT CONSUMPTION_ID, ");
		sBuilder.append(" (SELECT EMPLOYEE_NAME FROM EMPLOYEE WHERE EMPLOYEE_ID = OFC.OWNER_ID)EMPLOYEE_NAME, ");
		sBuilder.append(" (SELECT MEMBER_NO|| '-' ||MEMBER_NAME FROM MEMBERSHIP WHERE MEMBER_ID = OFC.MEMBER_ID)MEMBER_NO_NAME, ");
		sBuilder.append("ITEM_ID, ");
		sBuilder.append(" (SELECT ITEM_NAME FROM L_ITEM WHERE ITEM_ID = OFC.ITEM_ID) ITEM_NAME, ");
		sBuilder.append("QUANTITY, ");
		sBuilder.append("TO_CHAR (CONSUME_DATE, 'DD-MON-YYYY') CONSUMED_DATE, ");
		sBuilder.append("PRICE, ");
		sBuilder.append("REMARKS, ");
		sBuilder.append("(SELECT EMPLOYEE_NAME ");
		sBuilder.append("FROM EMPLOYEE ");
		sBuilder.append("WHERE EMPLOYEE_ID = OFC.UPDATE_BY) ");
		sBuilder.append("UPDATE_BY, ");
		sBuilder.append(" CASE WHEN TRUNC (UPDATE_DATE) = TRUNC (SYSDATE) THEN 'Y' ELSE 'N' END ");
		sBuilder.append(" EDITABLE ");
		sBuilder.append(" FROM OWNER_FOOD_CONSUMPTION OFC ");
		sBuilder.append(" WHERE COMPLETED_YN = 'Y' ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		/*if(ownerConsumptionInfo.getEmployeeId() != null && ownerConsumptionInfo.getEmployeeId().length() > 0) {
		paramSource.addValue("empId", ownerConsumptionInfo.getEmployeeId());
		} */
		
		sBuilder.append("ORDER BY CONSUME_DATE DESC, CONSUMPTION_ID DESC ");
		
		//System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);
		
		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			OwnerConsumptionInfo oOwnerConsumptionHistory = new OwnerConsumptionInfo();
			oOwnerConsumptionHistory.setEncConsumeId(oRemoveNull.nullRemove(oCipherUtils.encrypt((String.valueOf(row.get("CONSUMPTION_ID"))))));
			oOwnerConsumptionHistory.setEmployeeName(oRemoveNull.nullRemove((String.valueOf(row.get("EMPLOYEE_NAME")))));
			oOwnerConsumptionHistory.setMemberName(oRemoveNull.nullRemove((String.valueOf(row.get("MEMBER_NO_NAME")))));
			oOwnerConsumptionHistory.setItemId(oRemoveNull.nullRemove((String.valueOf(row.get("ITEM_ID")))));
			oOwnerConsumptionHistory.setItemName(oRemoveNull.nullRemove((String.valueOf(row.get("ITEM_NAME")))));
			oOwnerConsumptionHistory.setQuantity(oRemoveNull.nullRemove((String.valueOf(row.get("QUANTITY")))));
			oOwnerConsumptionHistory.setPrice(oRemoveNull.nullRemove((String.valueOf(row.get("PRICE")))));
			oOwnerConsumptionHistory.setConsumeDate(oRemoveNull.nullRemove((String.valueOf(row.get("CONSUMED_DATE")))));
			oOwnerConsumptionHistory.setRemarks(oRemoveNull.nullRemove((String.valueOf(row.get("REMARKS")))));
			oOwnerConsumptionHistory.setUpdateBy(oRemoveNull.nullRemove((String.valueOf(row.get("UPDATE_BY")))));
			oConsumptionHistoryList.add(oOwnerConsumptionHistory);
		}
		return oConsumptionHistoryList;
	}
	
	
	public StockProcess processStock(StockProcess stockProcess) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		StockProcess oStockProcess = new StockProcess();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_STOCK_SUMMARY_PROCESS");

			Map<String, Object> inParamMap = new HashMap<String, Object>();
			
			inParamMap.put("P_STOCK_DATE", stockProcess.getProcessDate());
			inParamMap.put("P_UPDATE_BY", stockProcess.getUpdateBy());
			
			System.out.println("dao stockProcess.getProcessDate() = " + stockProcess.getProcessDate());
			System.out.println("dao stockProcess.getUpdateBy() = " + stockProcess.getUpdateBy());
			
			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oStockProcess.setMessage(oRemoveNull.nullRemove((String) outParamMap.get("P_MESSAGE")));
			oStockProcess.setMessageCode(oRemoveNull.nullRemove((String) outParamMap.get("P_MESSAGE_CODE")));
			
			System.out.println("dao oStockProcess.getMessage() = " + oStockProcess.getMessage());
			System.out.println("dao oStockProcess.getMessageCode() = " + oStockProcess.getMessageCode());
			
		//	System.out.println("ownerConsumptionInfo.getEncConsumeId() " + oCipherUtils.decrypt(oRemoveNull.nullRemove(ownerConsumptionInfo.getEncConsumeId())));

		} catch (Exception ex) {
			ex.printStackTrace();
			oStockProcess.setMessage("Error Occured!!!");
			oStockProcess.setMessageCode("0000");
		}
		return oStockProcess;
	}
		

}
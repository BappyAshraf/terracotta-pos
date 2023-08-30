package com.pos.hr.dao;

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
import com.pos.common.RemoveNull;
import com.pos.hr.model.EmployeeInformation;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

@Repository
public class HRDaoImpl implements HRDao {
	private SimpleJdbcCall simpleJdbcCall;
	private JdbcTemplate jdbcTemplate;
	CipherUtils oCipherUtils = new CipherUtils();
	@Autowired
	private DataSource dataSource;
	RemoveNull oRemoveNull = new RemoveNull();

	// =========== Start General Info ============//

	public List<EmployeeInformation> getEmployeeInfoList(EmployeeInformation employeeInformation) {

		jdbcTemplate = new JdbcTemplate(dataSource);
		List<EmployeeInformation> oEmployeeInfoList = new ArrayList<EmployeeInformation>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT EMPLOYEE_ID, ");
		sBuilder.append("EMPLOYEE_NAME, ");
		sBuilder.append("KNOWN_AS, ");
		sBuilder.append("DESIGNATION_ID, ");
		sBuilder.append("(SELECT DESIGNATION_NAME ");
		sBuilder.append("FROM L_DESIGNATION ");
		sBuilder.append("WHERE DESIGNATION_ID = E.DESIGNATION_ID) ");
		sBuilder.append("DESIGNATION_NAME, ");
		sBuilder.append("DECODE (ENABLED_YN, 'Y', 'ENABLED', 'N', 'DISABLED', 'N/A') ENABLED_YN, ");
		sBuilder.append("CONTACT_NO ");
		sBuilder.append("FROM EMPLOYEE E ");
		sBuilder.append("WHERE 1 = 1 ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		if (employeeInformation.getEmployeeName() != null && employeeInformation.getEmployeeName().length() > 0) {
			sBuilder.append("AND UPPER(EMPLOYEE_NAME) LIKE  UPPER(:name)  ");
			paramSource.addValue("name", "%"+ employeeInformation.getEmployeeName() +"%");
		}
		if (employeeInformation.getKnownAs() != null && employeeInformation.getKnownAs().length() > 0) {
			sBuilder.append("AND UPPER(KNOWN_AS) LIKE  UPPER(:knownAs) ");
			paramSource.addValue("knownAs", "%"+ employeeInformation.getKnownAs() +"%");
		}
		if (employeeInformation.getDesignationId() != null && employeeInformation.getDesignationId().length() > 0) {
			sBuilder.append("AND DESIGNATION_ID = :desId ");
			paramSource.addValue("desId", employeeInformation.getDesignationId());
		}
		if (employeeInformation.getEnabledYN() != null && employeeInformation.getEnabledYN().length() > 0) {
			sBuilder.append("AND ENABLED_YN = :enableYN ");
			paramSource.addValue("enableYN", employeeInformation.getEnabledYN());
		}
		
		//System.out.println("kno as "+ employeeInformation.getKnownAs());

		 //System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			EmployeeInformation oEmployeeInformation = new EmployeeInformation();

			oEmployeeInformation.setEncEmployeeId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_ID")))));
			oEmployeeInformation.setEmployeeId(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_ID"))));
			oEmployeeInformation.setEmployeeName(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_NAME"))));
			oEmployeeInformation.setKnownAs(oRemoveNull.nullRemove(String.valueOf(row.get("KNOWN_AS"))));
			oEmployeeInformation.setDesignationId(oRemoveNull.nullRemove(String.valueOf(row.get("DESIGNATION_ID"))));
			oEmployeeInformation.setDesignationName(oRemoveNull.nullRemove(String.valueOf(row.get("DESIGNATION_NAME"))));
			oEmployeeInformation.setEnabledYNName(oRemoveNull.nullRemove(String.valueOf(row.get("ENABLED_YN"))));
			oEmployeeInformation.setContactNo(oRemoveNull.nullRemove(String.valueOf(row.get("CONTACT_NO"))));
			oEmployeeInfoList.add(oEmployeeInformation);
		}
		return oEmployeeInfoList;
	}
	
	
	public EmployeeInformation empInfoSave(EmployeeInformation employeeInformation) {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		EmployeeInformation oEmployeeInformation = new EmployeeInformation();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_EMPLOYEE_INFO_SAVE");

			Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("P_EMPLOYEE_ID", oCipherUtils.decrypt(oRemoveNull.nullRemove(employeeInformation.getEncEmployeeId())));
			inParamMap.put("P_EMPLOYEE_NAME", employeeInformation.getEmployeeName());
			inParamMap.put("P_KNOWN_AS", employeeInformation.getKnownAs());
			inParamMap.put("P_FATHERS_NAME", employeeInformation.getFatherName());
			inParamMap.put("P_MOTHERS_NAME", employeeInformation.getMotherName());
			inParamMap.put("P_GENDER_ID", employeeInformation.getGender());
			inParamMap.put("P_DATE_OF_BIRTH", employeeInformation.getDateOfBirth());
			inParamMap.put("P_RELIGION_ID", employeeInformation.getReligion());
			inParamMap.put("P_MARITAL_STATUS", employeeInformation.getMaritalStatus());
			inParamMap.put("P_NID_NUMBER", employeeInformation.getNationalId());
			inParamMap.put("P_DESIGNATION_ID", employeeInformation.getDesignationId());
			inParamMap.put("P_JOINING_DATE", employeeInformation.getJoiningDate());
			inParamMap.put("P_RESIGNING_DATE", employeeInformation.getResigningDate());
			inParamMap.put("P_CONTACT_NO", employeeInformation.getContactNo());
			inParamMap.put("P_EMAIL_ADDRESS", employeeInformation.getEmailAddress());
			inParamMap.put("P_ENABLED_YN", employeeInformation.getEnabledYN());
			inParamMap.put("P_FIXED_SALARY", employeeInformation.getFixedSalary());
			inParamMap.put("P_UPDATE_BY", employeeInformation.getUpdateBy());
			
			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oEmployeeInformation.setMessage(oRemoveNull.nullRemove((String) outParamMap.get("P_MESSAGE")));
			oEmployeeInformation.setMessageCode(oRemoveNull.nullRemove((String) outParamMap.get("P_MESSAGE_CODE")));
			oEmployeeInformation.setEncEmployeeId(oRemoveNull.nullRemove(oCipherUtils.encrypt((String) outParamMap.get("P_NEW_EMP_ID"))));
			
			//System.out.println(" P_NEW_EMP_ID "+ (String) outParamMap.get("P_NEW_EMP_ID"));

		} catch (Exception ex) {
			ex.printStackTrace();
			oEmployeeInformation.setMessage("Error Occured!!!");
			oEmployeeInformation.setMessageCode("0000");
		}
		return oEmployeeInformation;
	}
	
	
	public EmployeeInformation getEmployeeInfo(EmployeeInformation employeeInformation) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		EmployeeInformation oEmployeeInformation = new EmployeeInformation();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT EMPLOYEE_ID, ");
		sBuilder.append(" EMPLOYEE_NAME, ");
		sBuilder.append(" KNOWN_AS, ");
		sBuilder.append(" FATHER_NAME, ");
		sBuilder.append(" MOTHER_NAME, ");
		sBuilder.append(" GENDER, ");
		sBuilder.append(" TO_CHAR(DATE_OF_BIRTH,'DD/MM/YYYY')DATE_OF_BIRTH, ");
		sBuilder.append(" RELIGION_ID, ");
		sBuilder.append(" MARITAL_STATUS_ID, ");
		sBuilder.append(" VOTER_ID, ");
		sBuilder.append(" DESIGNATION_ID, ");
		sBuilder.append(" TO_CHAR(JOINING_DATE,'DD/MM/YYYY') JOINING_DATE, ");
		sBuilder.append(" TO_CHAR(RESIGNING_DATE,'DD/MM/YYYY') RESIGNING_DATE , ");
		sBuilder.append(" CONTACT_NO, ");
		sBuilder.append(" EMAIL_ADDRESS, ");
		sBuilder.append(" ENABLED_YN, ");
		sBuilder.append(" FIXED_SALARY ");
		sBuilder.append(" FROM EMPLOYEE  ");
		sBuilder.append(" WHERE EMPLOYEE_ID = :employeeId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("employeeId", oCipherUtils.decrypt(employeeInformation.getEncEmployeeId()));
		// System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oEmployeeInformation.setEncEmployeeId(oCipherUtils.encrypt(String.valueOf(row.get("EMPLOYEE_ID"))));
			oEmployeeInformation.setEmployeeId(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_ID"))));
			oEmployeeInformation.setEmployeeName(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_NAME"))));
			oEmployeeInformation.setKnownAs(oRemoveNull.nullRemove(String.valueOf(row.get("KNOWN_AS"))));
			oEmployeeInformation.setFatherName(oRemoveNull.nullRemove(String.valueOf(row.get("FATHER_NAME"))));
			oEmployeeInformation.setMotherName(oRemoveNull.nullRemove(String.valueOf(row.get("MOTHER_NAME"))));
			oEmployeeInformation.setGender(oRemoveNull.nullRemove(String.valueOf(row.get("GENDER"))));
			oEmployeeInformation.setDateOfBirth(oRemoveNull.nullRemove(String.valueOf(row.get("DATE_OF_BIRTH"))));
			oEmployeeInformation.setReligion(oRemoveNull.nullRemove(String.valueOf(row.get("RELIGION_ID"))));
			oEmployeeInformation.setMaritalStatus(oRemoveNull.nullRemove(String.valueOf(row.get("MARITAL_STATUS_ID"))));
			oEmployeeInformation.setNationalId(oRemoveNull.nullRemove(String.valueOf(row.get("VOTER_ID"))));
			oEmployeeInformation.setDesignationId(oRemoveNull.nullRemove(String.valueOf(row.get("DESIGNATION_ID"))));
			oEmployeeInformation.setJoiningDate(oRemoveNull.nullRemove(String.valueOf(row.get("JOINING_DATE"))));
			oEmployeeInformation.setResigningDate(oRemoveNull.nullRemove(String.valueOf(row.get("RESIGNING_DATE"))));
			oEmployeeInformation.setContactNo(oRemoveNull.nullRemove(String.valueOf(row.get("CONTACT_NO"))));
			oEmployeeInformation.setEmailAddress(oRemoveNull.nullRemove(String.valueOf(row.get("EMAIL_ADDRESS"))));
			oEmployeeInformation.setEnabledYN(oRemoveNull.nullRemove(String.valueOf(row.get("ENABLED_YN"))));
			oEmployeeInformation.setFixedSalary(oRemoveNull.nullRemove(String.valueOf(row.get("FIXED_SALARY"))));
			
			}
		return oEmployeeInformation;
	}
	
	

}

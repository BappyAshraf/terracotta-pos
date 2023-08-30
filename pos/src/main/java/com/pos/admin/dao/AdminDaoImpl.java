package com.pos.admin.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
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
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.aes.protection.CipherUtils;

import com.pos.admin.model.Designation;
import com.pos.admin.model.EmployeeInfo;

import com.pos.admin.model.RoleInfoForm;
import com.pos.admin.model.RoleMenuDetails;
import com.pos.admin.model.RoleMenuMapping;
import com.pos.admin.model.UserInfoForm;
import com.pos.admin.model.UserRole;
import com.pos.common.RemoveNull;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

@Repository
public class AdminDaoImpl implements AdminDao {

	private SimpleJdbcCall simpleJdbcCall;
	private JdbcTemplate jdbcTemplate;
	CipherUtils oCipherUtils = new CipherUtils();
	@Autowired
	private DataSource dataSource;
	//RemoveNull oUserInfoRemoveNull = new RemoveNull();
	RemoveNull oRemoveNull = new RemoveNull();

	//////////////////////////// RoleInfo //////////////////////////////

	public List<RoleInfoForm> getRoleInfoList(RoleInfoForm roleInfoForm) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<RoleInfoForm> oUserList = new ArrayList<RoleInfoForm>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT ROLE_ID, ROLE_NAME,");
		sBuilder.append("CASE WHEN ENABLED_YN = 'Y' THEN 'ENABLE' ");
		sBuilder.append("ELSE 'DISABLE' END ENABLED_YN_NAME ");
		sBuilder.append("FROM L_ROLE ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		sBuilder.append("ORDER BY UPPER (ROLE_NAME)");
		
		//System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			RoleInfoForm oRoleInfoForm = new RoleInfoForm();
			oRoleInfoForm.setEncRoleId(oCipherUtils.encrypt(String.valueOf(row.get("ROLE_ID"))));
			oRoleInfoForm.setRoleName(oRemoveNull.nullRemove(String.valueOf(row.get("ROLE_NAME"))));
			oRoleInfoForm.setEnabledYNName(oRemoveNull.nullRemove(String.valueOf(row.get("ENABLED_YN_NAME"))));
			oUserList.add(oRoleInfoForm);
		}

		return oUserList;
	}

	public RoleInfoForm getRoleInfo(RoleInfoForm roleInfoForm) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		RoleInfoForm oRoleInfoForm = new RoleInfoForm();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append("SELECT ROLE_ID, ROLE_NAME,");
		sBuilder.append("ENABLED_YN ");
		sBuilder.append("FROM L_ROLE ");
		sBuilder.append("WHERE ROLE_ID = :roleId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("roleId", oCipherUtils.decrypt(roleInfoForm.getEncRoleId()));

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oRoleInfoForm
					.setEncRoleId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("USER_ID")))));
			oRoleInfoForm
					.setEncRoleId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("ROLE_ID")))));
			oRoleInfoForm.setRoleName(oRemoveNull.nullRemove(String.valueOf(row.get("ROLE_NAME"))));
			oRoleInfoForm.setEnabledYN(oRemoveNull.nullRemove(String.valueOf(row.get("ENABLED_YN"))));

		}
		return oRoleInfoForm;
	}

	public RoleInfoForm saveRoleInfo(RoleInfoForm roleInfoForm) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		RoleInfoForm oRoleInfoForm = new RoleInfoForm();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_ROLE_INFO_SAVE");

			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("P_ROLE_ID", oCipherUtils.decrypt(roleInfoForm.getEncRoleId()));
			inParamMap.put("P_ROLE_NAME", roleInfoForm.getRoleName());
			inParamMap.put("P_ENABLED_YN", roleInfoForm.getEnabledYN());

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oRoleInfoForm.setMessage((String) outParamMap.get("P_MESSAGE"));
			oRoleInfoForm.setMessageCode((String) outParamMap.get("P_MESSAGE_CODE"));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oRoleInfoForm;
	}

	public RoleInfoForm deleteRole(RoleInfoForm roleInfoForm) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		RoleInfoForm oRoleInfoForm = new RoleInfoForm();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_L_ROLE_DELETE");

			Map<String, Object> inParamMap = new HashMap<String, Object>();
			
			//System.out.println("role " + oCipherUtils.decrypt(roleInfoForm.getEncRoleId()));
			
			inParamMap.put("P_ROLE_ID", oCipherUtils.decrypt(roleInfoForm.getEncRoleId()));

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oRoleInfoForm.setMessage((String) outParamMap.get("P_MESSAGE"));
			oRoleInfoForm.setMessageCode((String) outParamMap.get("P_MESSAGE_CODE"));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oRoleInfoForm;
	}

	public List<RoleMenuMapping> getRoleALLMenuList(RoleMenuMapping roleMenuMapping) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<RoleMenuMapping> oRoleaLLMenuList = new ArrayList<RoleMenuMapping>();
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append("SELECT MM.MENU_NAME PARENT_MENU_NAME, MI.MENU_NAME, ");
		sBuilder.append("CASE WHEN RMM.INSERT_YN = 'Y' THEN 'Yes' ELSE 'No' END INSERT_YN, ");
		sBuilder.append("CASE WHEN RMM.DELETE_YN = 'Y' THEN 'Yes' ELSE 'No' END DELETE_YN ");
		sBuilder.append("FROM MENU_INFO MI, MAIN_MENU MM, ROLE_MENU_MAPPING RMM ");
		sBuilder.append("WHERE MM.PARENT_MENU_ID = MI.PARENT_MENU_ID ");
		sBuilder.append("AND MI.MENU_ID = RMM.MENU_ID AND RMM.ROLE_ID = :roleId ");
		sBuilder.append("ORDER BY MM.PARENT_MENU_ID, MI.MENU_ID ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("roleId", roleMenuMapping.getRoleId());

		// System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			RoleMenuMapping oRoleMenuMapping = new RoleMenuMapping();
			oRoleMenuMapping.setMainMenuName(oRemoveNull.nullRemove(String.valueOf(row.get("PARENT_MENU_NAME"))));
			oRoleMenuMapping.setMenuName(oRemoveNull.nullRemove(String.valueOf(row.get("MENU_NAME"))));
			oRoleMenuMapping.setInsert(oRemoveNull.nullRemove(String.valueOf(row.get("INSERT_YN"))));
			oRoleMenuMapping.setDelete(oRemoveNull.nullRemove(String.valueOf(row.get("DELETE_YN"))));

			oRoleaLLMenuList.add(oRoleMenuMapping);
		}
		return oRoleaLLMenuList;
	}

	public List<RoleMenuMapping> getRoleMenuMappingList(RoleMenuMapping roleMenuMapping) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<RoleMenuMapping> oRoleMenuMappingList = new ArrayList<RoleMenuMapping>();
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append("SELECT M.MENU_ID, ");
		sBuilder.append("M.MENU_NAME, ");
		sBuilder.append("CASE WHEN  ");
		sBuilder.append(
				"(SELECT COUNT(*)  FROM ROLE_MENU_MAPPING WHERE MENU_ID = M.MENU_ID AND ROLE_ID = :role ) = 1 THEN 'Y' ELSE 'N' ");
		sBuilder.append("END ALLOW_YN, ");
		sBuilder.append(
				"(SELECT INSERT_YN FROM ROLE_MENU_MAPPING WHERE MENU_ID = M.MENU_ID AND ROLE_ID = :role ) INSERT_YN, ");

		sBuilder.append(
				"(SELECT DELETE_YN FROM ROLE_MENU_MAPPING WHERE MENU_ID = M.MENU_ID AND ROLE_ID = :role ) DELETE_YN ");
		sBuilder.append("FROM  MENU_INFO M ");
		sBuilder.append("WHERE 1=1 ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("role", roleMenuMapping.getRoleId());
		if (roleMenuMapping.getMainMenuId() != null) {
			sBuilder.append(" AND M.PARENT_MENU_ID = :mainMenuId ");
			paramSource.addValue("mainMenuId", roleMenuMapping.getMainMenuId());
		} else {
			sBuilder.append(" AND M.MENU_ID IN (SELECT MENU_ID FROM ROLE_MENU_MAPPING WHERE ROLE_ID = :role) ");
		}
		
		 System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			RoleMenuMapping oRoleMenuMapping = new RoleMenuMapping();
			oRoleMenuMapping.setEncMenuId(oCipherUtils.encrypt(String.valueOf(row.get("MENU_ID"))));
			oRoleMenuMapping.setMenuName(String.valueOf(row.get("MENU_NAME")));
			oRoleMenuMapping.setAllowYN(String.valueOf(row.get("ALLOW_YN")));
			oRoleMenuMapping.setInsert(String.valueOf(row.get("INSERT_YN")));
			if (String.valueOf(row.get("INSERT_YN")).equals("Y")) {
				oRoleMenuMapping.setInsertYN("YES");
			} else {
				oRoleMenuMapping.setInsertYN("NO");
			}

			oRoleMenuMapping.setDelete(String.valueOf(row.get("DELETE_YN")));
			if (String.valueOf(row.get("DELETE_YN")).equals("Y")) {
				oRoleMenuMapping.setDeleteYN("YES");
			} else {
				oRoleMenuMapping.setDeleteYN("NO");
			}
			oRoleMenuMappingList.add(oRoleMenuMapping);
		}
		return oRoleMenuMappingList;
	}

	public RoleMenuMapping saveRoleMenuMapping(RoleMenuMapping roleMenuMapping) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			Connection conn = jdbcTemplate.getDataSource().getConnection();
			
			//System.out.println("conn " + conn);

			StructDescriptor recDescriptor = StructDescriptor.createDescriptor("MENUARRAY", conn);

			ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor("MENUARRAYTAB", conn);

			List<RoleMenuDetails> roleMenuDetails = roleMenuMapping.getRoleMenuDetails();

			/* get array size */

			int arraySize = 0;
			
			//System.out.println("roleMenuDetails " + roleMenuDetails);
			
			if (null != roleMenuDetails && roleMenuDetails.size() > 0) {
				for (RoleMenuDetails oRoleMenuDetails : roleMenuDetails) {

					// System.out.println("getEncMenuId" +
					// oRoleMenuDetails.getEncMenuId());
					// System.out.println("getAllowYN" +
					// oRoleMenuDetails.getAllowYN());
					if (oRoleMenuDetails.getAllowYN() != null) {
						arraySize = arraySize + 1;

					}

				}
			}

			//System.out.println("arraySize = " + arraySize);

			Object[] array_of_records = new Object[arraySize];

			/* get array size */

			Object[] java_record_array = new Object[3];

			int i = 0;

			if (null != roleMenuDetails && roleMenuDetails.size() > 0) {
				for (RoleMenuDetails oRoleMenuDetails : roleMenuDetails) {

					if (oRoleMenuDetails.getInsertYN() == null) {
						oRoleMenuDetails.setInsertYN("N");
					}

					if (oRoleMenuDetails.getDeleteYN() == null) {
						oRoleMenuDetails.setDeleteYN("N");
					}

					if (oRoleMenuDetails.getAllowYN() != null) {
						java_record_array[0] = oCipherUtils.decrypt(oRoleMenuDetails.getEncMenuId());
						java_record_array[1] = oRoleMenuDetails.getInsertYN();
						java_record_array[2] = oRoleMenuDetails.getDeleteYN();

						STRUCT oracle_record = new STRUCT(recDescriptor, conn, java_record_array);
						array_of_records[i] = oracle_record;
						i = i + 1;
						//System.out.println("java_record_array[0] " + java_record_array[0]);
						//System.out.println("java_record_array[1] " + java_record_array[1]);
						//System.out.println("java_record_array[2] " + java_record_array[2]);
					}
				}
			}

			ARRAY oracle_array = new ARRAY(arrayDescriptor, conn, array_of_records);

			CallableStatement oCallStmt = dataSource.getConnection()
					.prepareCall("{call PRO_ROLE_MENU_SAVE(?,?,?,?,?,?)}");

			oCallStmt.setString(1, roleMenuMapping.getRoleId());
			oCallStmt.setString(2, roleMenuMapping.getMainMenuId());
			oCallStmt.setArray(3, oracle_array);
			oCallStmt.setString(4, roleMenuMapping.getUpdateBy());

			oCallStmt.registerOutParameter(5, java.sql.Types.CHAR);
			oCallStmt.registerOutParameter(6, java.sql.Types.CHAR);

			oCallStmt.execute();

			roleMenuMapping.setMessage(oCallStmt.getString(5));
			roleMenuMapping.setMessageCode(oCallStmt.getString(6));

			oCallStmt.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return roleMenuMapping;

	}

	//////////////////// UserInfo///////////////////////

	public UserInfoForm getUserInfo(UserInfoForm userInfoForm) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		UserInfoForm oUserInfoForm = new UserInfoForm();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append("SELECT U.LOGIN_ID,");
		sBuilder.append("E.EMPLOYEE_ID,");
		sBuilder.append("E.KNOWN_AS,");
		sBuilder.append("D.DESIGNATION_NAME,");
		sBuilder.append("U.USERNAME,");
		sBuilder.append("U.ACCOUNT_ENABLED ");
		sBuilder.append("FROM USER_INFO U, EMPLOYEE E, L_DESIGNATION D ");
		sBuilder.append("WHERE E.EMPLOYEE_ID = U.EMPLOYEE_ID AND E.DESIGNATION_ID = D.DESIGNATION_ID AND  U.LOGIN_ID = :userId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("userId", oCipherUtils.decrypt(userInfoForm.getEncUserId()));

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oUserInfoForm.setEncUserId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("LOGIN_ID")))));
			oUserInfoForm.setEncEmployeeId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_ID")))));
			oUserInfoForm.setEmployeeName(oRemoveNull.nullRemove(String.valueOf(row.get("KNOWN_AS"))));
			oUserInfoForm.setDesignationName(oRemoveNull.nullRemove(String.valueOf(row.get("DESIGNATION_NAME"))));
			oUserInfoForm.setUserName(oRemoveNull.nullRemove(String.valueOf(row.get("USERNAME"))));
			oUserInfoForm.setEnabledYN(oRemoveNull.nullRemove(String.valueOf(row.get("ACCOUNT_ENABLED"))));
			// oUserInfoRemoveNull.removeNullUserInfo(oUserInfoForm);

		}
		return oUserInfoForm;
	}

	public UserInfoForm getUserEmpInfo(UserInfoForm userInfoForm) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		UserInfoForm oUserInfoForm = new UserInfoForm();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append("SELECT U.LOGIN_ID, ");
		sBuilder.append("E.EMPLOYEE_ID, ");
		sBuilder.append("E.KNOWN_AS, ");
		sBuilder.append("D.DESIGNATION_NAME, ");
		sBuilder.append("U.USERNAME, ");
		sBuilder.append("U.ACCOUNT_ENABLED ");
		sBuilder.append("FROM USER_INFO U, ");
		sBuilder.append("EMPLOYEE E, ");
		sBuilder.append("L_DESIGNATION D ");
		sBuilder.append("WHERE E.EMPLOYEE_ID = U.EMPLOYEE_ID(+) ");
		sBuilder.append("AND E.DESIGNATION_ID = D.DESIGNATION_ID ");
		sBuilder.append("AND E.EMPLOYEE_ID = :employeeId ");
		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("employeeId", oCipherUtils.decrypt(userInfoForm.getEncEmployeeId()));

		// System.out.println("employeeId
		// "+oCipherUtils.decrypt(userInfoForm.getEncEmployeeId()));
		// System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oUserInfoForm.setEncUserId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("LOGIN_ID")))));
			oUserInfoForm.setEncEmployeeId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_ID")))));
			oUserInfoForm.setEmployeeName(oRemoveNull.nullRemove(String.valueOf(row.get("KNOWN_AS"))));
			//oUserInfoForm.setEncDepartmentId(oCipherUtils.encrypt(oRemoveNull.nullRemove(String.valueOf(row.get("DEPARTMENT_ID")))));
			//oUserInfoForm.setDepartmentName(oRemoveNull.nullRemove(String.valueOf(row.get("DEPARTMENT_NAME"))));
			oUserInfoForm.setDesignationName(oRemoveNull.nullRemove(String.valueOf(row.get("DESIGNATION_NAME"))));
			//oUserInfoForm.setUserName(oRemoveNull.nullRemove(String.valueOf(row.get("USER_NAME"))));
			//oUserInfoForm.setAddress(oRemoveNull.nullRemove(String.valueOf(row.get("PRE_ADDRESS_DETAILS"))));
			oUserInfoForm.setEnabledYN(oRemoveNull.nullRemove(String.valueOf(row.get("ACCOUNT_ENABLED"))));
			//oUserInfoForm.setBankId(oRemoveNull.nullRemove(String.valueOf(row.get("BANK_ID"))));
			//oUserInfoForm.setBankBranchId(oRemoveNull.nullRemove(String.valueOf(row.get("BANK_BRANCH_ID"))));
			//oUserInfoForm.setBankAccountNo(oRemoveNull.nullRemove(String.valueOf(row.get("BANK_ACCOUNT_NO"))));
			//oUserInfoForm.setEmployeeOfficeId(oRemoveNull.nullRemove(String.valueOf(row.get("OFFICE_ID"))));
			//oUserInfoForm.setGrossPartial(oRemoveNull.nullRemove(String.valueOf(row.get("SALARY_TYPE"))));
			//oUserInfoForm.setAttendanceIncentiveYN(oRemoveNull.nullRemove(String.valueOf(row.get("ATTENDANCE_INCENTIVE_YN"))));
			//oUserInfoForm.setExemptedPayDeduction(oRemoveNull.nullRemove(String.valueOf(row.get("EXEMPTED_PAY_DEDUCTION"))));
			
		}

		return oUserInfoForm;
	}

	public String checkUserName(UserInfoForm userInfoForm) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		String userCount = "";

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT COUNT(*) COUNT_USER FROM USER_INFO WHERE USER_NAME = :userName");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("userName", userInfoForm.getUserName());

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			userCount = String.valueOf(row.get("COUNT_USER"));
		}
		return userCount;
	}

	public List<UserInfoForm> getUserInfoList(UserInfoForm userInfoForm) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<UserInfoForm> oUserList = new ArrayList<UserInfoForm>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT U.LOGIN_ID, ");
		sBuilder.append("E.KNOWN_AS, ");
		sBuilder.append("D.DESIGNATION_NAME, ");
		sBuilder.append("CASE WHEN ACCOUNT_ENABLED = 1 THEN 'ENABLE' ");
		sBuilder.append("WHEN ACCOUNT_ENABLED = 2 THEN 'DISABLE' ");
		sBuilder.append("END ENABLED_YN_NAME ");
		sBuilder.append("FROM  USER_INFO U, EMPLOYEE E, L_DESIGNATION D ");
		sBuilder.append("WHERE U.EMPLOYEE_ID = E.EMPLOYEE_ID ");
		sBuilder.append("AND E.DESIGNATION_ID = D.DESIGNATION_ID ");
		sBuilder.append("AND E.ENABLED_YN = 'Y' "); 
		sBuilder.append("AND U.ACCOUNT_ENABLED = :enabledYN ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		if (userInfoForm.getEmployeeName() != null && userInfoForm.getEmployeeName().length() > 0) {
			sBuilder.append("AND UPPER (E.KNOWN_AS) LIKE UPPER(:employeeName) ");
			paramSource.addValue("employeeName", "%" + userInfoForm.getEmployeeName() + "%");
		}
		if (userInfoForm.getEnabledYN() != null && userInfoForm.getEnabledYN().length() > 0) {
			sBuilder.append("AND U.ACCOUNT_ENABLED =:enabledYN ");
			paramSource.addValue("enabledYN", userInfoForm.getEnabledYN());
		}

		sBuilder.append("ORDER BY UPPER (E.KNOWN_AS)");
		
		// System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			UserInfoForm oUserInfoForm = new UserInfoForm();
			oUserInfoForm.setEncUserId(oCipherUtils.encrypt(String.valueOf(row.get("LOGIN_ID"))));
			oUserInfoForm.setEmployeeName(oRemoveNull.nullRemove(String.valueOf(row.get("KNOWN_AS"))));
			oUserInfoForm.setDesignationName(oRemoveNull.nullRemove(String.valueOf(row.get("DESIGNATION_NAME"))));
			oUserInfoForm.setEnabledYNName(oRemoveNull.nullRemove(String.valueOf(row.get("ENABLED_YN_NAME"))));
			oUserList.add(oUserInfoForm);
		}

		return oUserList;
	}

	public List<UserInfoForm> getUserRoleList(String userId) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<UserInfoForm> oUserRoleList = new ArrayList<UserInfoForm>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT R.ROLE_ID,");
		sBuilder.append("R.ROLE_NAME ");
		if (userId != null && userId.length() > 0) {
			sBuilder.append(",CASE WHEN R.ROLE_ID IN ");
			sBuilder.append("(SELECT ROLE_ID FROM USER_ROLES ");
			sBuilder.append("WHERE ROLE_ID = R.ROLE_ID ");

			sBuilder.append("AND USER_ID = :encUserId ");
			paramSource.addValue("encUserId", userId);

			sBuilder.append(") THEN 'Y' ELSE 'N' END ");
			sBuilder.append("ROLE_YN ");
		}
		sBuilder.append("FROM   L_ROLE R ");
		sBuilder.append("WHERE R.ENABLED_YN = 'Y' ");
		sBuilder.append("ORDER BY R.ROLE_ID ");
		
		// System.out.println(sBuilder);
		// System.out.println("User ID "+userId);
		 
		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			UserInfoForm oUserInfoForm = new UserInfoForm();
			oUserInfoForm.setRoleId(oRemoveNull.nullRemove(String.valueOf(row.get("ROLE_ID"))));
			oUserInfoForm.setRoleName(oRemoveNull.nullRemove(String.valueOf(row.get("ROLE_NAME"))));
			oUserInfoForm.setRoleYN(oRemoveNull.nullRemove(String.valueOf(row.get("ROLE_YN"))));
			oUserRoleList.add(oUserInfoForm);
		}

		return oUserRoleList;
	}

	public UserInfoForm saveUserInfo(UserInfoForm userInfoForm) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
			// String hashedPassword = passwordEncoder.encode(password);
			
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(userInfoForm.getPassword().getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String hashtext = bigInt.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			
			String currentPassword = hashtext;
			
			
			m.reset();
			m.update(userInfoForm.getPassword().getBytes());
			digest = m.digest();
			bigInt = new BigInteger(1, digest);
			hashtext = bigInt.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			
			String newPassword = hashtext;
			
			Connection conn = jdbcTemplate.getDataSource().getConnection();
			// Connection dconn = ((DelegatingConnection)
			// conn).getInnermostDelegate();
			// Connection dconn = ((DelegatingConnection)
			// conn).getInnermostDelegate();

			StructDescriptor recDescriptor = StructDescriptor.createDescriptor("ROLEARRAY", conn);

			ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor("ROLEARRAYTAB", conn);

			// System.out.println("recDescriptor ="+recDescriptor +"--
			// arrayDescriptor ="+arrayDescriptor);

			List<UserRole> userRoles = userInfoForm.getUserRoles();

			/* get array size */

			int arraySize = 0;

			if (null != userRoles && userRoles.size() > 0) {
				for (UserRole ouserRole : userRoles) {
					if (ouserRole.getRoleId() != null && ouserRole.getRoleYN() != null) {
						arraySize = arraySize + 1;

					}
				}
			}

			//System.out.println("arraySize = " + arraySize);

			Object[] array_of_records = new Object[arraySize];

			/* get array size */

			Object[] java_record_array = new Object[1];

			int i = 0;

			if (null != userRoles && userRoles.size() > 0) {
				for (UserRole ouserRole : userRoles) {
					if (ouserRole.getRoleId() != null && ouserRole.getRoleYN() != null) {
						// System.out.println("roleId "+ouserRole.getRoleId());
						java_record_array[0] = ouserRole.getRoleId();

						STRUCT oracle_record = new STRUCT(recDescriptor, conn, java_record_array);
						array_of_records[i] = oracle_record;
						i = i + 1;
						// System.out.println("java_record_array[0] " +
						// java_record_array[0]);
					}
				}
			}

			ARRAY oracle_array = new ARRAY(arrayDescriptor, conn, array_of_records);

			CallableStatement oCallStmt = dataSource.getConnection()
					.prepareCall("{call PRO_USER_INFO_SAVE(?,?,?,?,?,?,?,?,?)}");

			oCallStmt.setString(1, oCipherUtils.decrypt(userInfoForm.getEncUserId()));
			oCallStmt.setString(2, oCipherUtils.decrypt(userInfoForm.getEncEmployeeId()));
			oCallStmt.setString(3, userInfoForm.getUserName());
			if (userInfoForm.getPassword() != "") {
				oCallStmt.setString(4, newPassword);
			} else {
				oCallStmt.setString(4, null);
			}

			oCallStmt.setString(5, userInfoForm.getEnabledYN());
			oCallStmt.setArray(6, oracle_array);
			oCallStmt.setString(7, userInfoForm.getUpdateBy());

			oCallStmt.registerOutParameter(8, java.sql.Types.CHAR);
			oCallStmt.registerOutParameter(9, java.sql.Types.CHAR);

			oCallStmt.execute();

			userInfoForm.setMessage(oCallStmt.getString(8));
			userInfoForm.setMessageCode(oCallStmt.getString(9));
			
			//System.out.println("1 = " + oCipherUtils.decrypt(userInfoForm.getEncUserId()));
			//System.out.println("2 = " + oCipherUtils.decrypt(userInfoForm.getEncEmployeeId()));
			//System.out.println("3 = " + userInfoForm.getUserName());
			//System.out.println("4 = " + null);
			//System.out.println("5 = " + userInfoForm.getEnabledYN());
			//System.out.println("6 = " + oracle_array);
			//System.out.println("7 = " + userInfoForm.getUpdateBy());
			

			oCallStmt.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return userInfoForm;
	}

	public UserInfoForm deleteUserInfo(UserInfoForm userInfoForm) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		UserInfoForm oUserInfo = new UserInfoForm();

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_EMPLOYEE_MAPPING_DELETE");

			Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("P_EMPLOYEE_ID", userInfoForm.getEmployeeId());

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oUserInfo.setMessage((String) outParamMap.get("P_MESSAGE"));
			oUserInfo.setMessageCode((String) outParamMap.get("P_MESSAGE_CODE"));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return oUserInfo;

	}


	/*
	 * ************************Employee
	 * Info************************************************************
	 */

	public EmployeeInfo getEmployeeInfo(EmployeeInfo employeeInfo) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		EmployeeInfo oEmployeeInfo = new EmployeeInfo();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT EMPLOYEE_ID ,");
		sBuilder.append(" EMPLOYEE_NAME  ,");
		sBuilder.append(" FATHERS_NAME  ,");
		sBuilder.append(" PRE_ADDRESS_DETAILS  ,");
		sBuilder.append(" DEPARTMENT_ID  ,");
		sBuilder.append(" DESIGNATION_ID  ,");
		sBuilder.append(" CONTACT_NO  ,");
		sBuilder.append(" EMAIL_ADDRESS  ,");
		sBuilder.append(" TO_CHAR(JOINING_DATE,'DD/MM/YYYY') JOINING_DATE ,");
		sBuilder.append(" TO_CHAR(RESIGNING_DATE,'DD/MM/YYYY') RESIGNING_DATE  ,");
		sBuilder.append(" ENABLED_YN,LEVEL_NO ");
		sBuilder.append(" FROM EMPLOYEE_INFO ");
		sBuilder.append(" WHERE EMPLOYEE_ID=:employeeId ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("employeeId", oCipherUtils.decrypt(employeeInfo.getEncEmployeeId()));
		// System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oEmployeeInfo.setEncEmployeeId(oCipherUtils.encrypt(String.valueOf(row.get("EMPLOYEE_ID"))));
			oEmployeeInfo.setEmployeeId(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_ID"))));
			oEmployeeInfo.setEmployeeName(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_NAME"))));
			oEmployeeInfo.setFatherName(oRemoveNull.nullRemove(String.valueOf(row.get("FATHERS_NAME"))));
			oEmployeeInfo.setAddress(oRemoveNull.nullRemove(String.valueOf(row.get("PRE_ADDRESS_DETAILS"))));
			oEmployeeInfo.setDepartmentId(oRemoveNull.nullRemove(String.valueOf(row.get("DEPARTMENT_ID"))));
			oEmployeeInfo.setDesignationId(oRemoveNull.nullRemove(String.valueOf(row.get("DESIGNATION_ID"))));
			oEmployeeInfo.setContactNo(oRemoveNull.nullRemove(String.valueOf(row.get("CONTACT_NO"))));
			oEmployeeInfo.setEmailAddress(oRemoveNull.nullRemove(String.valueOf(row.get("EMAIL_ADDRESS"))));
			oEmployeeInfo.setJoiningDate(oRemoveNull.nullRemove(String.valueOf(row.get("JOINING_DATE"))));
			oEmployeeInfo.setResigningDate(oRemoveNull.nullRemove(String.valueOf(row.get("RESIGNING_DATE"))));
			oEmployeeInfo.setEnabledYN(oRemoveNull.nullRemove(String.valueOf(row.get("ENABLED_YN"))));
			oEmployeeInfo.setLevelNo(oRemoveNull.nullRemove(String.valueOf(row.get("LEVEL_NO"))));
		}

		return oEmployeeInfo;
	}

	public List<EmployeeInfo> getEmployeeInfoList(EmployeeInfo employeeInfo) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<EmployeeInfo> oEmployeeList = new ArrayList<EmployeeInfo>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" SELECT EMPLOYEE_ID ,EMPLOYEE_NAME, ");
		sBuilder.append(" KNOWN_AS,");
		//sBuilder.append(" FATHERS_NAME,");
		//sBuilder.append(" PRE_ADDRESS_DETAILS,");
		//sBuilder.append("(SELECT DEPARTMENT_NAME FROM L_DEPARTMENT WHERE DEPARTMENT_ID = E.DEPARTMENT_ID) DEPARTMENT_NAME  ,");
		sBuilder.append("(SELECT DESIGNATION_NAME FROM L_DESIGNATION WHERE DESIGNATION_ID = E.DESIGNATION_ID) DESIGNATION_NAME  ,");
		sBuilder.append(" CONTACT_NO,");
		sBuilder.append(" EMAIL_ADDRESS  ,");
		//sBuilder.append(" TO_CHAR(JOINING_DATE,'DD/MM/YYYY')JOINING_DATE ,");
		//sBuilder.append(" TO_CHAR(RESIGNING_DATE,'DD/MM/YYYY') RESIGNING_DATE  ,");
		sBuilder.append(" ENABLED_YN ,");
		sBuilder.append(" CASE WHEN ENABLED_YN = 'Y' THEN 'ENABLE' ");
		sBuilder.append(" WHEN ENABLED_YN = 'N' THEN 'DISABLE' END ENABLED_YN_NAME  ");
		//sBuilder.append(" ENROLLMENT_ID  ");
		sBuilder.append(" FROM   EMPLOYEE E ");
		sBuilder.append(" WHERE 1=1 ");
			
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		//System.out.println("employeeInfo.getEnabledYN() "+employeeInfo.getEnabledYN());
		
		if (employeeInfo.getEnabledYN() == null || employeeInfo.getEnabledYN().length() == 0 ) {
			sBuilder.append("AND ENABLED_YN = 'Y' ");
		}else if(employeeInfo.getEnabledYN() != null && employeeInfo.getEnabledYN().equals("Y")){
			sBuilder.append("AND ENABLED_YN = 'Y' ");
		}else if(employeeInfo.getEnabledYN() != null && employeeInfo.getEnabledYN().equals("N")){
			sBuilder.append("AND ENABLED_YN = 'N' ");
		}

		if (employeeInfo.getEmployeeName() != null && employeeInfo.getEmployeeName().length() > 0) {
			sBuilder.append("AND UPPER (EMPLOYEE_NAME) LIKE UPPER(:employeeName) ");
			paramSource.addValue("employeeName", "%" + employeeInfo.getEmployeeName() + "%");
		}

	/*	if (employeeInfo.getFatherName() != null && employeeInfo.getFatherName().length() > 0) {
			sBuilder.append("AND UPPER(FATHERS_NAME) LIKE UPPER(:fatherName) ");
			paramSource.addValue("fatherName", "%" + employeeInfo.getFatherName() + "%");
		}

		if (employeeInfo.getDepartmentId() != null && employeeInfo.getDepartmentId().length() > 0) {
			sBuilder.append("AND E.DEPARTMENT_ID = :departmentId ");
			paramSource.addValue("departmentId", employeeInfo.getDepartmentId());
		}*/

		if (employeeInfo.getDesignationId() != null && employeeInfo.getDesignationId().length() > 0) {
			sBuilder.append("AND E.DESIGNATION_ID = :designationId ");
			paramSource.addValue("designationId", employeeInfo.getDesignationId());
		}

	/*	if (employeeInfo.getLevelNo() != null && employeeInfo.getLevelNo().length() > 0) {
			sBuilder.append("AND E.LEVEL_NO = :levelNo ");
			paramSource.addValue("levelNo", employeeInfo.getLevelNo());
		}
		
		if (employeeInfo.getEnrollmentID() != null && employeeInfo.getEnrollmentID().length() > 0) {
			sBuilder.append("AND E.ENROLLMENT_ID = :enrollmentID ");
			paramSource.addValue("enrollmentID", employeeInfo.getEnrollmentID());
		}*/

		if (employeeInfo.getOrderBy() != null && employeeInfo.getOrderBy().length() > 0) {
			sBuilder.append("ORDER BY "+employeeInfo.getOrderBy()+"");
		}else{
			sBuilder.append("ORDER BY EMPLOYEE_ID");	
		}
		
		
		 //System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			EmployeeInfo oEmployeeInfo = new EmployeeInfo();
			oEmployeeInfo.setEncEmployeeId(oCipherUtils.encrypt(String.valueOf(row.get("EMPLOYEE_ID"))));
			oEmployeeInfo.setEmployeeName(oRemoveNull.nullRemove(String.valueOf(row.get("EMPLOYEE_NAME"))));
			oEmployeeInfo.setKnownAs(oRemoveNull.nullRemove(String.valueOf(row.get("KNOWN_AS"))));
			//oEmployeeInfo.setFatherName(oRemoveNull.nullRemove(String.valueOf(row.get("FATHERS_NAME"))));
			//oEmployeeInfo.setAddress(oRemoveNull.nullRemove(String.valueOf(row.get("PRE_ADDRESS_DETAILS"))));
			//oEmployeeInfo.setDepartmentName(oRemoveNull.nullRemove(String.valueOf(row.get("DEPARTMENT_NAME"))));
			oEmployeeInfo.setDesignationName(oRemoveNull.nullRemove(String.valueOf(row.get("DESIGNATION_NAME"))));
			oEmployeeInfo.setContactNo(oRemoveNull.nullRemove(String.valueOf(row.get("CONTACT_NO"))));
			oEmployeeInfo.setEmailAddress(oRemoveNull.nullRemove(String.valueOf(row.get("EMAIL_ADDRESS"))));
			//oEmployeeInfo.setJoiningDate(oRemoveNull.nullRemove(String.valueOf(row.get("JOINING_DATE"))));
			//oEmployeeInfo.setResigningDate(oRemoveNull.nullRemove(String.valueOf(row.get("RESIGNING_DATE"))));
			oEmployeeInfo.setEnabledYNName(oRemoveNull.nullRemove(String.valueOf(row.get("ENABLED_YN_NAME"))));
			//oEmployeeInfo.setLevelNo(oRemoveNull.nullRemove(String.valueOf(row.get("LEVEL_NO"))));
			//oEmployeeInfo.setEnrollmentID(oRemoveNull.nullRemove(String.valueOf(row.get("ENROLLMENT_ID"))));
			oEmployeeList.add(oEmployeeInfo);
		}

		return oEmployeeList;
	}
	
	
	
	public EmployeeInfo getEmployeeInfoListCount(EmployeeInfo employeeInfo) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		EmployeeInfo oEmployeeInfo = new EmployeeInfo();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT (SELECT COUNT(*) ");
		sBuilder.append(" FROM   EMPLOYEE_INFO E ");
		sBuilder.append(" WHERE 1=1 ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		//System.out.println("employeeInfo.getEnabledYN() total "+employeeInfo.getEnabledYN());
		
		if (employeeInfo.getEnabledYN() == null || employeeInfo.getEnabledYN().length() == 0 ) {
			sBuilder.append("AND ENABLED_YN = 'Y' ");
		}else if(employeeInfo.getEnabledYN() != null && employeeInfo.getEnabledYN().equals("Y")){
			sBuilder.append("AND ENABLED_YN = 'Y' ");
		}else if(employeeInfo.getEnabledYN() != null && employeeInfo.getEnabledYN().equals("N")){
			sBuilder.append("AND ENABLED_YN = 'N' ");
		}
		
		if (employeeInfo.getEmployeeName() != null && employeeInfo.getEmployeeName().length() > 0) {
			sBuilder.append("AND UPPER (EMPLOYEE_NAME) LIKE UPPER(:employeeName) ");
			paramSource.addValue("employeeName", "%" + employeeInfo.getEmployeeName() + "%");
		}

		if (employeeInfo.getFatherName() != null && employeeInfo.getFatherName().length() > 0) {
			sBuilder.append("AND UPPER(FATHERS_NAME) LIKE UPPER(:fatherName) ");
			paramSource.addValue("fatherName", "%" + employeeInfo.getFatherName() + "%");
		}

		if (employeeInfo.getDepartmentId() != null && employeeInfo.getDepartmentId().length() > 0) {
			sBuilder.append("AND E.DEPARTMENT_ID = :departmentId ");
			paramSource.addValue("departmentId", employeeInfo.getDepartmentId());
		}

		if (employeeInfo.getDesignationId() != null && employeeInfo.getDesignationId().length() > 0) {
			sBuilder.append("AND E.DESIGNATION_ID = :designationId ");
			paramSource.addValue("designationId", employeeInfo.getDesignationId());
		}

		if (employeeInfo.getLevelNo() != null && employeeInfo.getLevelNo().length() > 0) {
			sBuilder.append("AND E.LEVEL_NO = :levelNo ");
			paramSource.addValue("levelNo", employeeInfo.getLevelNo());
		}

		sBuilder.append(") COUNT_RESULT, (SELECT COUNT(*) FROM   EMPLOYEE_INFO) COUNT_ALL FROM DUAL ");
		// System.out.println(sBuilder);

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			
			oEmployeeInfo.setCountResult(String.valueOf(row.get("COUNT_RESULT")));
			oEmployeeInfo.setCountAll(String.valueOf(row.get("COUNT_ALL")));
			
		}

		return oEmployeeInfo;
	}
	
	

	public EmployeeInfo saveEmployeeInfo(EmployeeInfo employeeInfo) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		EmployeeInfo oEmployeeInfo = new EmployeeInfo();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_EMPLOYEE_INFO_SAVE");

			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("P_EMPLOYEE_ID", oCipherUtils.decrypt(employeeInfo.getEncEmployeeId()));
			inParamMap.put("P_EMPLOYEE_NAME", employeeInfo.getEmployeeName());
			inParamMap.put("P_FATHERS_NAME", employeeInfo.getFatherName());
			inParamMap.put("P_ADDRESS", employeeInfo.getAddress());
			inParamMap.put("P_DEPARTMENT_ID", employeeInfo.getDepartmentId());
			inParamMap.put("P_DESIGNATION_ID", employeeInfo.getDesignationId());
			inParamMap.put("P_CONTACT_NO", employeeInfo.getContactNo());
			inParamMap.put("P_EMAIL_ADDRESS", employeeInfo.getEmailAddress());
			inParamMap.put("P_JOINING_DATE", employeeInfo.getJoiningDate());
			inParamMap.put("P_RESIGNING_DATE", employeeInfo.getResigningDate());
			inParamMap.put("P_ENABLED_YN", employeeInfo.getEnabledYN());
			inParamMap.put("P_LEVEL_NO", employeeInfo.getLevelNo());
			inParamMap.put("P_UPDATE_BY", employeeInfo.getUpdateBy());

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oEmployeeInfo.setMessage((String) outParamMap.get("P_MESSAGE"));
			oEmployeeInfo.setMessageCode((String) outParamMap.get("P_MESSAGE_CODE"));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oEmployeeInfo;
	}

	public EmployeeInfo deleteEmployeeInfo(EmployeeInfo employeeInfo) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		EmployeeInfo oEmployeeInfo = new EmployeeInfo();

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_EMPLOYEE_INFO_DELETE");

			Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("P_EMPLOYEE_ID", oCipherUtils.decrypt(employeeInfo.getEncEmployeeId()));

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oEmployeeInfo.setMessage((String) outParamMap.get("P_MESSAGE"));
			oEmployeeInfo.setMessageCode((String) outParamMap.get("P_MESSAGE_CODE"));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return oEmployeeInfo;

	}


}

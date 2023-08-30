package com.pos.login.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
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

import com.pos.common.LoginRemoveNull;
import com.pos.login.model.LoginInfo;
import com.pos.login.model.MenuInfo;



@Repository("loginDao")
public class LoginDaoImpl implements LoginDao {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;
	
	@Autowired
	private DataSource dataSource;
	
	LoginRemoveNull oLoginRemoveNull = new LoginRemoveNull();
	
	public LoginInfo ValidateUser(String sUserName, String sPassword, String sTerminalIp) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		LoginInfo oLoginInfo = new LoginInfo();
		try {
			////////////////////AES/////////////////////////
			
			
			
			/*String encPassword = oCipherUtils.encrypt(sPassword);
			
			
			
			System.out.println("encPassword "+ encPassword);
			
			String decPassword = oCipherUtils.decrypt(encPassword);
					
			
			System.out.println("decPassword "+ decPassword);*/
			
			
			
			
			////////////////////////AES////////////////////////
			
			
			
			
			
			
			/*key = KeyGenerator.getInstance("DES").generateKey();
			
			System.out.println("key "+key);

			ecipher = Cipher.getInstance("DES");
			dcipher = Cipher.getInstance("DES");

			// initialize the ciphers with the given key

			ecipher.init(Cipher.ENCRYPT_MODE, key);

			dcipher.init(Cipher.DECRYPT_MODE, key);
			
			System.out.println("ecipher "+ecipher);
			System.out.println("dcipher "+dcipher);*/
			
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(sPassword.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String hashtext = bigInt.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			sPassword = hashtext;
			
			System.out.println("sPassword "+sPassword);
			System.out.println("hashtext "+hashtext);
			
			//System.out.println("sPassword "+sPassword);
			
			/*byte[] utf8 = sPassword.getBytes("UTF8");
			
			byte[] enc = ecipher.doFinal(utf8);*/
			
			//-----------------------String encPassword = oEncDecDES.encrypt(sPassword);
			
			/*System.out.println("enc "+enc);
			
			enc = BASE64EncoderStream.encode(enc);
			System.out.println("enc64 "+enc);
			
			String encPassword = new String(enc);*/
			
			//------------------------System.out.println("encPassword "+encPassword);
			
			/*byte[] dec = BASE64DecoderStream.decode(enc.getBytes());

			byte[] decUtf8 = dcipher.doFinal(dec);*/
			
			//------------------------String decPassword = oEncDecDES.decrypt(encPassword);
			
			//String decPassword = new String(decUtf8, "UTF8");
			
			//System.out.println("decPassword "+decPassword);
			
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_GET_LOGIN_DETAILS");
		    Map<String, Object> inParamMap = new HashMap<String, Object>();
		    
		    inParamMap.put("P_USER_NAME", sUserName);
		    inParamMap.put("P_PASSWORD", sPassword);
		    inParamMap.put("P_TERMINAL_IP", sTerminalIp);
		    
		    System.out.println("IP " + sTerminalIp);
		    
		    Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));
			
		    
		    
		    oLoginInfo.setEmployeeId((String) outParamMap.get("O_EMPLOYEE_ID"));
			oLoginInfo.setBranchId((String) outParamMap.get("O_BRANCH_ID"));
			oLoginInfo.setBranchName((String) outParamMap.get("O_BRANCH_NAME"));
			oLoginInfo.setDesignationId((String) outParamMap.get("O_DESIGNATION_ID"));
			oLoginInfo.setDesignationName((String) outParamMap.get("O_DESIGNATION_NAME"));
			oLoginInfo.setUserType((String) outParamMap.get("O_USER_TYPE_ID"));
			oLoginInfo.setFormColour((String) outParamMap.get("O_COLOUR_ID"));
			oLoginInfo.setLoginDate((String) outParamMap.get("O_LOGIN_DATE"));
			oLoginInfo.setDelFlag((String) outParamMap.get("O_DDATA"));
			oLoginInfo.setMultipleBranch((String) outParamMap.get("O_MULTI_BRANCH_YN"));
			oLoginInfo.setForceExpirePwd((String) outParamMap.get("O_FORCE_EXPIRE_PWD"));
			oLoginInfo.setMessage((String) outParamMap.get("O_STATUS_CODE"));
			//oRemoveNullLogin.removeNullLoginInfo(oLoginInfo);
			
			/*System.out.println("empId " + oLoginInfo.getEmployeeId());
			System.out.println("branchId " + oLoginInfo.getBranchId());
			System.out.println("branchName " + oLoginInfo.getBranchName());
			System.out.println("designationId " + oLoginInfo.getDesignationId());
			System.out.println("designationName " + oLoginInfo.getDesignationName());
			System.out.println("userType " + oLoginInfo.getUserType());
			System.out.println("colourId " + oLoginInfo.getFormColour());
			System.out.println("loginDate " + oLoginInfo.getLoginDate());
			System.out.println("ddata " + oLoginInfo.getDelFlag());
			System.out.println("multiBranch " + oLoginInfo.getMultipleBranch());
			System.out.println("forceExpire " + oLoginInfo.getForceExpirePwd());
			System.out.println("statusCode " + oLoginInfo.getMessage());*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return oLoginInfo;
	}

	/*public List<MenuInfo> GetPermittedMenuInfo(String UserId, String ParentMenuID) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<MenuInfo> MenuInfoList = new ArrayList<MenuInfo>();
		//System.out.println("HELLO");
		try {
			StringBuilder sBuilder = new StringBuilder();
			
			
			sBuilder.append(" SELECT DISTINCT RMM.MENU_ID,");
			sBuilder.append(" MI.MENU_NAME,");
			sBuilder.append(" MI.MENU_ACTION_NAME,");
			sBuilder.append(" RMM.INSERT_YN,");
			sBuilder.append(" RMM.UPDATE_BY,");
			sBuilder.append(" RMM.DELETE_YN");
			sBuilder.append(" FROM USER_INFO UI,");
			sBuilder.append(" L_ROLE R,");
			sBuilder.append(" ROLE_MENU_MAPPING RMM,");
			sBuilder.append(" MENU_INFO MI");
			sBuilder.append(" WHERE     R.ROLE_ID = RMM.ROLE_ID");
			sBuilder.append(" AND RMM.MENU_ID = MI.MENU_ID");
			sBuilder.append(" AND UI.EMPLOYEE_ID ='" + UserId + "' ");
			sBuilder.append(" AND MI.PARENT_MENU_ID ='" + ParentMenuID + "' ");
			
			
			sBuilder.append("SELECT MENU_INFO.MENU_ID, ");
			sBuilder.append("MENU_INFO.PARENT_MENU_ID,");
			sBuilder.append("MENU_INFO.MENU_COMMAND,");
			sBuilder.append("MENU_INFO.MENU_NAME,");
			sBuilder.append("USER_MENU_MAPPING.INSERT_YN,");
			sBuilder.append("USER_MENU_MAPPING.UPDATE_YN,");
			sBuilder.append("USER_MENU_MAPPING.DELETE_YN,");
			sBuilder.append("MENU_INFO.MENU_ACTION_NAME ");
			sBuilder.append("FROM USER_INFO INNER JOIN ");
			sBuilder.append("USER_MENU_MAPPING ON USER_INFO.EMPLOYEE_ID = USER_MENU_MAPPING.EMPLOYEE_ID ");
			sBuilder.append("INNER JOIN MENU_INFO ON  ");
			sBuilder.append("USER_MENU_MAPPING.MENU_ID = MENU_INFO.MENU_ID ");
			sBuilder.append("WHERE USER_INFO.EMPLOYEE_ID='" + UserId + "' ");
			sBuilder.append("AND MENU_INFO.PARENT_MENU_ID='" + ParentMenuID + "' ");
			sBuilder.append("ORDER BY MENU_INFO.MENU_ID");
			
			//System.out.println(sBuilder);
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());
			
			for(@SuppressWarnings("rawtypes")
			Map row : rows){
				MenuInfo menuInfo = new MenuInfo();
				
				menuInfo.setMenuId(String.valueOf(row.get("MENU_ID")));
				menuInfo.setParentMenuId(String.valueOf(row.get("PARENT_MENU_ID")));
				menuInfo.setMenuCommand(String.valueOf(row.get("MENU_COMMAND")));
				menuInfo.setMenuName(String.valueOf(row.get("MENU_NAME")));
				menuInfo.setInsertYN(String.valueOf(row.get("INSERT_YN")));
				menuInfo.setUpdateYN(String.valueOf(row.get("UPDATE_YN")));
				menuInfo.setDeleteYN(String.valueOf(row.get("DELETE_YN")));
				menuInfo.setMenuActionName(String.valueOf(row.get("MENU_ACTION_NAME")));
				
				//System.out.println("actionName " + menuInfo.getMenuActionName());
				//oRemoveNullLogin.removeNullMenuInfo(menuInfo);
				MenuInfoList.add(menuInfo);
			}
			
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return MenuInfoList;
	}*/
	
	
	
	public List<MenuInfo> GetPermittedMenuInfo(String UserId, String ParentMenuID) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<MenuInfo> MenuInfoList = new ArrayList<MenuInfo>();
		
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		try {
			StringBuilder sBuilder = new StringBuilder();
			
			sBuilder.append("SELECT DISTINCT UR.USER_ID, ");
			sBuilder.append("UI.EMPLOYEE_ID, ");
			sBuilder.append("MI.PARENT_MENU_ID, ");
			sBuilder.append("RMM.MENU_ID, ");
			sBuilder.append("RMM.INSERT_YN, ");
			sBuilder.append("RMM.DELETE_YN, ");
			sBuilder.append("MI.MENU_NAME, ");
			sBuilder.append("MI.MENU_ACTION_NAME ");
			sBuilder.append("FROM USER_ROLES UR, ");
			sBuilder.append("ROLE_MENU_MAPPING RMM, ");
			sBuilder.append("MENU_INFO MI, ");
			sBuilder.append("USER_INFO UI ");
			sBuilder.append("WHERE UR.ROLE_ID = RMM.ROLE_ID ");
			sBuilder.append("AND RMM.MENU_ID = MI.MENU_ID ");
			sBuilder.append("AND UR.USER_ID = UI.LOGIN_ID ");
			sBuilder.append("AND UI.EMPLOYEE_ID = :empId ");
			sBuilder.append("AND MI.PARENT_MENU_ID = :parentMenuId ");
			
			//System.out.println(sBuilder);
			
			MapSqlParameterSource paramSource = new MapSqlParameterSource();

			paramSource.addValue("empId", UserId);
			paramSource.addValue("parentMenuId", ParentMenuID);
			
			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);
			
			for(@SuppressWarnings("rawtypes")
			Map row : rows){
				MenuInfo menuInfo = new MenuInfo();
				
				menuInfo.setMenuId(String.valueOf(row.get("MENU_ID")));
				menuInfo.setParentMenuId(String.valueOf(row.get("PARENT_MENU_ID")));
				menuInfo.setMenuName(String.valueOf(row.get("MENU_NAME")));
				menuInfo.setInsertYN(String.valueOf(row.get("INSERT_YN")));
				menuInfo.setDeleteYN(String.valueOf(row.get("DELETE_YN")));
				menuInfo.setMenuActionName(String.valueOf(row.get("MENU_ACTION_NAME")));
				
				//menuInfo.setMenuCommand(String.valueOf(row.get("MENU_COMMAND")));
				//menuInfo.setUpdateYN(String.valueOf(row.get("UPDATE_YN")));
				//System.out.println("actionName " + menuInfo.getMenuActionName());
				
				MenuInfoList.add(menuInfo);
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return MenuInfoList;
	}
	
	
	
	public MenuInfo checkUserAuthorization(String UserId, String MenuID) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		MenuInfo menuInfo = new MenuInfo();
		try {
			
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_V2_USER_AUTHENTICATION");
		    Map<String, Object> inParamMap = new HashMap<String, Object>();
		    
		    //System.out.println("P_EMPLOYEE_ID "+ UserId);
		    //System.out.println("P_MENU_ID "+ MenuID);
		    
		    inParamMap.put("P_EMPLOYEE_ID", UserId);
		    inParamMap.put("P_MENU_ID", MenuID);
		    
		    Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));
			
		    menuInfo.setMenuId((String) outParamMap.get("P_CURR_MENU_ID"));
		    menuInfo.setInsert((String) outParamMap.get("P_INSERT_YN"));
		    menuInfo.setUpdate((String) outParamMap.get("P_UPDATE_YN"));
		    menuInfo.setDelete((String) outParamMap.get("P_DELETE_YN"));
		    
		    oLoginRemoveNull.removeNullMenuInfo(menuInfo);
			
			
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return menuInfo;
	}
	
	

}

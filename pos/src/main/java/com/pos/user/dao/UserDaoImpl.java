package com.pos.user.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.aes.protection.CipherUtils;
import com.pos.user.model.UserInfo;


@Repository
public class UserDaoImpl implements UserDao{

	private SimpleJdbcCall simpleJdbcCall;
	private JdbcTemplate jdbcTemplate;
	CipherUtils oCipherUtils = new CipherUtils();
	@Autowired
	private DataSource dataSource;
/*	UserInfoRemoveNull oUserInfoRemoveNull = new UserInfoRemoveNull();*/
	
	
	public UserInfo userPasswordChng(UserInfo userInfo) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		UserInfo oUserInfo = new UserInfo();
		try {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(userInfo.getCurrentPassword().getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		String hashtext = bigInt.toString(16);
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
		
		String currentPassword = hashtext;
		
		
		m.reset();
		m.update(userInfo.getNewPassword().getBytes());
		digest = m.digest();
		bigInt = new BigInteger(1, digest);
		hashtext = bigInt.toString(16);
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
		
		String newPassword = hashtext;
		
		
		
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_USER_PASS_CHANGE");

			//System.out.println("P_EMPLOYEE_ID "+ userInfo.getEmployeeId());
			//System.out.println("P_CURR_PASS "+ currentPassword);
			//System.out.println("P_NEW_PASS "+ newPassword);
			
			
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("P_EMPLOYEE_ID", userInfo.getEmployeeId());
			inParamMap.put("P_CURR_PASS", currentPassword);
			inParamMap.put("P_NEW_PASS", newPassword);

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));
			
			oUserInfo.setMessage((String) outParamMap.get("P_MESSAGE"));
			oUserInfo.setMessageCode((String) outParamMap.get("P_MESSAGE_CODE"));

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		return oUserInfo;
	}

}

package com.pos.venue.dao;

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
import com.pos.venue.model.VenueReservationModel;


@Repository("VenueReservationDao")

public class VenueReservationDaoImpl implements VenueReservationDao {
	
	private SimpleJdbcCall simpleJdbcCall;
	private JdbcTemplate jdbcTemplate;
	CipherUtils oCipherUtils = new CipherUtils();
	
	RemoveNull oRemoveNull = new RemoveNull();

	@Autowired
	private DataSource dataSource;
	
	
	public List<VenueReservationModel> getVenueReservedList(VenueReservationModel venueReservationModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<VenueReservationModel> oVenueReservationList = new ArrayList<VenueReservationModel>();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT RESERVATION_ID,VENUE_ID, ");
		sBuilder.append("(SELECT   VENUE_NAME FROM   L_VENUE WHERE   VENUE_ID = V.VENUE_ID) VENUE_NAME, ");
		sBuilder.append("PROGRAMME_TYPE_ID, ");
		sBuilder.append("(SELECT   PROGRAM_TYPE_NAME FROM   L_PROGRAM  WHERE   PROGRAM_TYPE_ID = V.PROGRAMME_TYPE_ID) PROGRAMME_TYPE_NAME, ");
		sBuilder.append("TO_CHAR(RESERVATION_DATE,'DD/MM/YYYY')RESERVATION_DATE, ");   
		sBuilder.append("START_TIME, ");
		sBuilder.append("END_TIME, ");
		sBuilder.append("DECODE(ENABLED_YN,'Y','ENABLED','N','DISABLED') STATUS, ");
		sBuilder.append("DECODE(COMPLETED_YN,'Y','YES','N','NO')COMPLETED ");
		sBuilder.append("FROM VENUE_RESERVATION V ");
		sBuilder.append("WHERE 1 = 1 ");
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		if (venueReservationModel.getReservationDate() != null && venueReservationModel.getReservationDate().length() > 0) {
			sBuilder.append("AND TO_CHAR (RESERVATION_DATE, 'DD/MM/YYYY') = :reserveDate ");
			paramSource.addValue("reserveDate", venueReservationModel.getReservationDate());
		}

		if (venueReservationModel.getVenueId() != null && venueReservationModel.getVenueId().length() > 0) {
			sBuilder.append(" AND VENUE_ID = :venueId ");
			paramSource.addValue("venueId", venueReservationModel.getVenueId());
		}

		//System.out.println(sBuilder);
		
		//System.out.println("ven id "+ venueReservationModel.getVenueId());
		//System.out.println("res date "+ venueReservationModel.getReservationDate());

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {

			VenueReservationModel oVenueReservation = new VenueReservationModel();

			oVenueReservation.setEncReservationId(oCipherUtils.encrypt((String.valueOf(row.get("RESERVATION_ID")))));
			oVenueReservation.setVenueName(oRemoveNull.nullRemove(String.valueOf(row.get("VENUE_NAME"))));
			oVenueReservation.setProgrammeTypeName(oRemoveNull.nullRemove(String.valueOf(row.get("PROGRAMME_TYPE_NAME"))));
			oVenueReservation.setReservationDate(oRemoveNull.nullRemove(String.valueOf(row.get("RESERVATION_DATE"))));
			oVenueReservation.setStartTime(oRemoveNull.nullRemove(String.valueOf(row.get("START_TIME"))));
			oVenueReservation.setEndTime(oRemoveNull.nullRemove(String.valueOf(row.get("END_TIME"))));
			oVenueReservation.setStatusYN(oRemoveNull.nullRemove(String.valueOf(row.get("STATUS"))));
			oVenueReservation.setCompletedYN(oRemoveNull.nullRemove(String.valueOf(row.get("COMPLETED"))));

			oVenueReservationList.add(oVenueReservation);

		}

		return oVenueReservationList;
	}
	
	public VenueReservationModel getVenueReserved(VenueReservationModel venueReservationModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		VenueReservationModel oVenueReservation = new VenueReservationModel();

		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT RESERVATION_ID,VENUE_ID, ");
		sBuilder.append("PROGRAMME_TYPE_ID, ");
		sBuilder.append("TO_CHAR(RESERVATION_DATE,'DD/MM/YYYY')RESERVATION_DATE, ");   
		sBuilder.append("TOTAL_GUEST, ");
		sBuilder.append("START_TIME, ");
		sBuilder.append("END_TIME, ");
		sBuilder.append("MEMBERSHIP_ID, ");
		sBuilder.append("SET_PROGRAMME_YN, ");
		sBuilder.append("TOTAL_AMOUNT, ");
		sBuilder.append("ADVANCE_AMOUNT, ");
		sBuilder.append("ENABLED_YN, ");
		sBuilder.append("COMPLETED_YN, ");
		sBuilder.append("REMARKS, ");
		
		sBuilder.append("CONTACT_PERSON_NAME, ");
		sBuilder.append("PHONE_NO, ");
		sBuilder.append("MOBILE_NO, ");
		sBuilder.append("EMAIL, ");
		sBuilder.append("ADDRESS ");
		
		sBuilder.append("FROM VENUE_RESERVATION  ");
		sBuilder.append("WHERE RESERVATION_ID = :reservId ");
		sBuilder.append("AND COMPLETED_YN = 'N' ");
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("reservId", oCipherUtils.decrypt(venueReservationModel.getEncReservationId()));
		
		
		//System.out.println(sBuilder);
		
		//System.out.println("res id "+ oCipherUtils.decrypt(venueReservationModel.getEncReservationId()));

		List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

		for (@SuppressWarnings("rawtypes")
		Map row : rows) {
			oVenueReservation.setEncReservationId(oCipherUtils.encrypt((String.valueOf(row.get("RESERVATION_ID")))));
			oVenueReservation.setVenueId(oRemoveNull.nullRemove(String.valueOf(row.get("VENUE_ID"))));
			oVenueReservation.setProgrammeTypeId(oRemoveNull.nullRemove(String.valueOf(row.get("PROGRAMME_TYPE_ID"))));
			oVenueReservation.setReservationDate(oRemoveNull.nullRemove(String.valueOf(row.get("RESERVATION_DATE"))));
			oVenueReservation.setTotalGuest(oRemoveNull.nullRemove(String.valueOf(row.get("TOTAL_GUEST"))));
			oVenueReservation.setStartTime(oRemoveNull.nullRemove(String.valueOf(row.get("START_TIME"))));
			oVenueReservation.setEndTime(oRemoveNull.nullRemove(String.valueOf(row.get("END_TIME"))));
			oVenueReservation.setMembershipId(oRemoveNull.nullRemove(String.valueOf(row.get("MEMBERSHIP_ID"))));
			oVenueReservation.setSetProgrammeYN(oRemoveNull.nullRemove(String.valueOf(row.get("SET_PROGRAMME_YN"))));
			oVenueReservation.setTotalAmount(oRemoveNull.nullRemove(String.valueOf(row.get("TOTAL_AMOUNT"))));
			oVenueReservation.setAdvanceAmount(oRemoveNull.nullRemove(String.valueOf(row.get("ADVANCE_AMOUNT"))));
			oVenueReservation.setStatusYN(oRemoveNull.nullRemove(String.valueOf(row.get("ENABLED_YN"))));
			oVenueReservation.setCompletedYN(oRemoveNull.nullRemove(String.valueOf(row.get("COMPLETED_YN"))));
			oVenueReservation.setRemarks(oRemoveNull.nullRemove(String.valueOf(row.get("REMARKS"))));
			
            oVenueReservation.setConPersonName(oRemoveNull.nullRemove(String.valueOf(row.get("CONTACT_PERSON_NAME"))));
			oVenueReservation.setPhoneNo(oRemoveNull.nullRemove(String.valueOf(row.get("PHONE_NO"))));
			oVenueReservation.setMobileNo(oRemoveNull.nullRemove(String.valueOf(row.get("MOBILE_NO"))));
			oVenueReservation.setEmail(oRemoveNull.nullRemove(String.valueOf(row.get("EMAIL"))));
			oVenueReservation.setPersonAddress(oRemoveNull.nullRemove(String.valueOf(row.get("ADDRESS"))));
		}
		return oVenueReservation;
	}
	
	
	public VenueReservationModel saveVenueReservation(VenueReservationModel venueReservationModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		VenueReservationModel oVenueReservation = new VenueReservationModel();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_VENUE_RESERVATION_SAVE");
			Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("P_RESERVATION_ID", oCipherUtils.decrypt(venueReservationModel.getEncReservationId()));
			inParamMap.put("P_VENUE_ID", venueReservationModel.getVenueId());
			inParamMap.put("P_PROGRAMME_TYPE_ID", venueReservationModel.getProgrammeTypeId());
			inParamMap.put("P_RESERVATION_DATE", venueReservationModel.getReservationDate());
			inParamMap.put("P_START_TIME", venueReservationModel.getStartTime());
			inParamMap.put("P_END_TIME", venueReservationModel.getEndTime());
			inParamMap.put("P_TOTAL_GUEST", venueReservationModel.getTotalGuest());
			inParamMap.put("P_MEMBERSHIP_ID", venueReservationModel.getMembershipId());
			inParamMap.put("P_ADVANCE_AMOUNT", venueReservationModel.getAdvanceAmount());
			inParamMap.put("P_TOTAL_AMOUNT", venueReservationModel.getTotalAmount());
			inParamMap.put("P_SET_PROGRAMME_YN", venueReservationModel.getSetProgrammeYN());
			inParamMap.put("P_ENABLED_YN", venueReservationModel.getStatusYN());
			inParamMap.put("P_REMARKS", venueReservationModel.getRemarks());
			
			inParamMap.put("P_CONTACT_PERSON_NAME", venueReservationModel.getConPersonName());
			inParamMap.put("P_EMAIL", venueReservationModel.getEmail());
			inParamMap.put("P_PHONE_NO", venueReservationModel.getPhoneNo());
			inParamMap.put("P_MOBILE_NO", venueReservationModel.getMobileNo());
			inParamMap.put("P_ADDRESS", venueReservationModel.getPersonAddress());
			
			inParamMap.put("P_UPDATE_BY", venueReservationModel.getUpdateBy());
			
			//inParamMap.put("P_UPDATE_DATE", student.getUpdateDate());

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oVenueReservation.setMessage((String) outParamMap.get("P_MESSAGE"));
			oVenueReservation.setmCode((String) outParamMap.get("P_MESSAGE_CODE"));
			//System.out.println("studentId " + oStudent.getStudentId());
		} catch (Exception ex) {
			oVenueReservation.setMessage("Error Saving Record !!!");
			oVenueReservation.setmCode("0000");
			ex.printStackTrace();
		}
		return oVenueReservation;
	}
	

}

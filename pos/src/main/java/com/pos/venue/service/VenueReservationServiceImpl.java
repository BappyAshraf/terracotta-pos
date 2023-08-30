package com.pos.venue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.lookup.dao.LookupDao;
import com.pos.venue.dao.VenueReservationDao;
import com.pos.venue.model.VenueReservationModel;

@Service
public class VenueReservationServiceImpl implements VenueReservationService {
	
	@Autowired
	private VenueReservationDao venueReservationDao;
	
	public List<VenueReservationModel> getVenueReservedList(VenueReservationModel venueReservationModel) {
		return venueReservationDao.getVenueReservedList(venueReservationModel);
	}

	public VenueReservationModel getVenueReserved(VenueReservationModel venueReservationModel) {
		return venueReservationDao.getVenueReserved(venueReservationModel);
	}
	
	public VenueReservationModel saveVenueReservation(VenueReservationModel venueReservationModel) {
		return venueReservationDao.saveVenueReservation(venueReservationModel);
	}
	
	
}

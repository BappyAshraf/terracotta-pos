package com.pos.venue.dao;

import java.util.List;

import com.pos.venue.model.VenueReservationModel;


public interface VenueReservationDao {
	
	public List<VenueReservationModel> getVenueReservedList(VenueReservationModel venueReservationModel);
	
	public VenueReservationModel getVenueReserved(VenueReservationModel venueReservationModel);
	
	public VenueReservationModel saveVenueReservation(VenueReservationModel venueReservationModel);
	
	
}

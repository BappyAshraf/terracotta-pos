package com.pos.venue.service;

import java.util.List;

import com.pos.venue.model.VenueReservationModel;


public interface VenueReservationService {
	
	public List<VenueReservationModel> getVenueReservedList(VenueReservationModel venueReservationModel);
	
	public VenueReservationModel getVenueReserved(VenueReservationModel venueReservationModel);
	
	public VenueReservationModel saveVenueReservation(VenueReservationModel venueReservationModel);
	

}

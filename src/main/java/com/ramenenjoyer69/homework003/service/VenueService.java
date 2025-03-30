package com.ramenenjoyer69.homework003.service;

import com.ramenenjoyer69.homework003.model.entity.Venue;
import com.ramenenjoyer69.homework003.model.request.VenueRequest;

import java.util.List;

public interface VenueService {

    List<Venue> getAllVenues(Integer page, Integer size);

    Venue getVenueById(Long venueId);

    Venue updateVenueById(Long venueId, VenueRequest request);

    Venue saveVenue(VenueRequest request);

    Venue deleteVenueById(Long venueId);
}

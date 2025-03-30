package com.ramenenjoyer69.homework003.service.implement;

import com.ramenenjoyer69.homework003.model.entity.Venue;
import com.ramenenjoyer69.homework003.model.request.VenueRequest;
import com.ramenenjoyer69.homework003.repository.VenueRepository;
import com.ramenenjoyer69.homework003.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Venue> getAllVenues(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return venueRepository.getAllVenues(offset, size);
    }

    @Override
    public Venue getVenueById(Long venueId) {
        return venueRepository.getVenueById(venueId);
    }

    @Override
    public Venue updateVenueById(Long venueId, VenueRequest request) {
        return venueRepository.updateVenueById(venueId, request);
    }

    @Override
    public Venue saveVenue(VenueRequest request) {
        return venueRepository.saveVenue(request);
    }

    @Override
    public Venue deleteVenueById(Long venueId) {
        return venueRepository.deleteVenueById(venueId);
    }
}

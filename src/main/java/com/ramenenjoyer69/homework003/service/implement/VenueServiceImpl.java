package com.ramenenjoyer69.homework003.service.implement;

import com.ramenenjoyer69.homework003.exception.NotFoundException;
import com.ramenenjoyer69.homework003.model.entity.Venue;
import com.ramenenjoyer69.homework003.model.request.VenueRequest;
import com.ramenenjoyer69.homework003.repository.VenueRepository;
import com.ramenenjoyer69.homework003.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository)  {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Venue> getAllVenues(Integer page, Integer size) throws NotFoundException {
        int offset = (page - 1) * size;
        List<Venue> venues =  venueRepository.getAllVenues(offset, size);
        try{
            if(venues.isEmpty()){
                throw new NotFoundException(("No events found"));
            }
        }catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
        return venues;
    }

    @Override
    public Venue getVenueById(Long venueId) throws NotFoundException {
        Venue venue = venueRepository.getVenueById(venueId);

        try{
            if (venue == null) {
                throw new NotFoundException("No venue with ID [ " +venueId + " ] is found");
            }
        } catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
        return venue;
    }

    @Override
    public Venue updateVenueById(Long venueId, VenueRequest request) throws NotFoundException {
        Venue venue = venueRepository.updateVenueById(venueId, request);

        try{
            if (venue == null) {
                throw new NotFoundException("No venue with ID [ " +venueId + " ] is found");
            }
        }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
        return venue;
    }

    @Override
    public Venue saveVenue(VenueRequest request) {
        return venueRepository.saveVenue(request);
    }

    @Override
    public Venue deleteVenueById(Long venueId) {
        Venue venue = venueRepository.deleteVenueById(venueId);

        try{
            if (venue == null) {
                throw new NotFoundException("No venue with ID [ " +venueId + " ] is found");
            }
        }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
        return venue;
    }
}

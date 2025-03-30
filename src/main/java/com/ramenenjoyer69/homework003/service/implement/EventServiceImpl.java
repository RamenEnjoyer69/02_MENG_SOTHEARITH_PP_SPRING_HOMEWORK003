package com.ramenenjoyer69.homework003.service.implement;

import com.ramenenjoyer69.homework003.exception.NotFoundException;
import com.ramenenjoyer69.homework003.model.entity.Event;
import com.ramenenjoyer69.homework003.model.request.EventRequest;
import com.ramenenjoyer69.homework003.repository.AttendeeRepository;
import com.ramenenjoyer69.homework003.repository.EventAttendeeRepository;
import com.ramenenjoyer69.homework003.repository.EventRepository;
import com.ramenenjoyer69.homework003.repository.VenueRepository;
import com.ramenenjoyer69.homework003.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventAttendeeRepository eventAttendeeRepository;
    private final VenueRepository venueRepository;
    private final AttendeeRepository attendeeRepository;

    public EventServiceImpl(EventRepository eventRepository, EventAttendeeRepository eventAttendeeRepository, VenueRepository venueRepository, AttendeeRepository attendeeRepository) {
        this.eventRepository = eventRepository;
        this.eventAttendeeRepository = eventAttendeeRepository;
        this.venueRepository = venueRepository;
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public List<Event> getAllEvents(int page, int size) throws NotFoundException {
        int offset = (page - 1) * size;
        List<Event> events = eventRepository.getAllEvents(offset, size);
        try{
            if(events.isEmpty()){
                throw new NotFoundException("No events found");
            }
        }catch(Exception e){
            throw new NotFoundException(e.getMessage());
        }

        return events;
    }

    @Override
    public Event getEventById(Long eventId) throws NotFoundException {
        Event event = eventRepository.getEventById(eventId);
        try{

            if(event == null){
                throw new NotFoundException("No event with ID [ " +eventId + " ] is found");
            }
        }catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }

        return event;
    }

    @Override
    public Event updateEventById(Long eventId, EventRequest request) throws NotFoundException {
        if(venueRepository.getVenueById(request.getVenueId())==null){
            throw new NotFoundException("No venue with ID [ " +request.getVenueId() + " ] was found");
        }

        Event event = eventRepository.updateEventById(eventId, request);
        if(event == null){
            throw new NotFoundException("No event with ID [ " +eventId + " ] is found");
        }

        List<Long> existingAttendeeIds = eventAttendeeRepository.getAttendeeIdsByEventId(eventId);
        List<Long> newAttendeeIds = request.getAttendeeIds();


//        remove the ones not on the new list
        for (Long existingAttendeeId : existingAttendeeIds) {
            if (!newAttendeeIds.contains(existingAttendeeId)) {
                eventAttendeeRepository.removeEventAttendees(event.getEventId(), existingAttendeeId);
            }
        }
//          then add the new ones that aint on the old list
        for (Long newAttendeeId : newAttendeeIds) {
            if (!existingAttendeeIds.contains(newAttendeeId)) {
                eventAttendeeRepository.insertEventIdAndAttendeeId(event.getEventId(), newAttendeeId);
            }
        }

        return eventRepository.getEventById(eventId);
    }

    @Override
    public Event saveEvent(EventRequest request) {
        Event event = eventRepository.saveEvent(request);
        for (Long attendeeId : request.getAttendeeIds()) {
            eventAttendeeRepository.insertEventIdAndAttendeeId(event.getEventId(), attendeeId);
        }
        return eventRepository.getEventById(event.getEventId());
    }

    @Override
    public Event deleteEventById(Long eventId) throws NotFoundException {
        if (eventRepository.getEventById(eventId) == null) {
            throw new NotFoundException("No event with ID [ " +eventId + " ] was found");
        }
        return eventRepository.deleteEventById(eventId);
    }
}

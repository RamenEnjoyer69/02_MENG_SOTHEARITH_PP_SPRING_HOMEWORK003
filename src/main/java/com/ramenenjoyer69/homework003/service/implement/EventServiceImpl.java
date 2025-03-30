package com.ramenenjoyer69.homework003.service.implement;

import com.ramenenjoyer69.homework003.model.entity.Event;
import com.ramenenjoyer69.homework003.model.request.EventRequest;
import com.ramenenjoyer69.homework003.repository.EventAttendeeRepository;
import com.ramenenjoyer69.homework003.repository.EventRepository;
import com.ramenenjoyer69.homework003.service.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventAttendeeRepository eventAttendeeRepository;

    public EventServiceImpl(EventRepository eventRepository, EventAttendeeRepository eventAttendeeRepository) {
        this.eventRepository = eventRepository;
        this.eventAttendeeRepository = eventAttendeeRepository;
    }

    @Override
    public List<Event> getAllEvents(int page, int size) {
        int offset = (page - 1) * size;
        return eventRepository.getAllEvents(offset, size);
    }

    @Override
    public Event getEventById(Long eventId) {
        return eventRepository.getEventById(eventId);
    }

    @Override
    public Event updateEventById(Long eventId, EventRequest request) {
        Event event = eventRepository.updateEventById(eventId, request);

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
    public Event deleteEventById(Long eventId) {
        return eventRepository.deleteEventById(eventId);
    }
}

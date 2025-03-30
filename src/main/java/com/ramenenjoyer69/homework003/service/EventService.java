package com.ramenenjoyer69.homework003.service;

import com.ramenenjoyer69.homework003.model.entity.Event;
import com.ramenenjoyer69.homework003.model.request.EventRequest;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(int page, int size);

    Event getEventById(Long eventId);

    Event updateEventById(Long eventId, EventRequest request);

    Event saveEvent(EventRequest request);

    Event deleteEventById(Long eventId);
}

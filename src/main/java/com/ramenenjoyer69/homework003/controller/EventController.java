package com.ramenenjoyer69.homework003.controller;

import com.ramenenjoyer69.homework003.model.entity.Attendee;
import com.ramenenjoyer69.homework003.model.entity.Event;
import com.ramenenjoyer69.homework003.model.request.EventRequest;
import com.ramenenjoyer69.homework003.model.response.DeleteResponse;
import com.ramenenjoyer69.homework003.model.response.Response;
import com.ramenenjoyer69.homework003.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Event>>> getAllEvents(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {

        List<Event> events = eventService.getAllEvents(page, size);

        Response<List<Event>> response = new Response<>(
                "All events have been fetched successfully",
                events,
                HttpStatus.OK,
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);

    }

    @GetMapping("/{event_id}")
    public ResponseEntity<Response<Event>> getEventById(@PathVariable("event_id") Long eventId) {
        Event event = eventService.getEventById(eventId);

        Response<Event> response = new Response<>(
                "The event has been fetched successfully",
                event,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{event_id}")
    public ResponseEntity<Response<Event>> updateEventById(@PathVariable("event_id") Long eventId, @RequestBody EventRequest request) {
        Event event = eventService.updateEventById(eventId, request);

        Response<Event> response = new Response<>(
                "The event has been updated successfully",
                event,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response<Event>> saveEvent(@RequestBody EventRequest request) {
        Event event = eventService.saveEvent(request);

        Response<Event> response = new Response<>(
                "The event has been created successfully",
                event,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{event_id}")
    public ResponseEntity<DeleteResponse<Event>> deleteEventById(@PathVariable("event_id") Long eventId) {
        eventService.deleteEventById(eventId);

        DeleteResponse<Event> response = new DeleteResponse<>(
                "The event has been deleted successfully",
                HttpStatus.OK,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

package com.ramenenjoyer69.homework003.controller;


import com.ramenenjoyer69.homework003.model.entity.Attendee;
import com.ramenenjoyer69.homework003.model.request.AttendeeRequest;
import com.ramenenjoyer69.homework003.model.response.DeleteResponse;
import com.ramenenjoyer69.homework003.model.response.Response;
import com.ramenenjoyer69.homework003.service.AttendeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Attendee>>> getAllAttendees(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        List<Attendee> attendees = attendeeService.getAllAttendees(page, size);

        Response<List<Attendee>> response = new Response<>(
                "All attendees have been fetched successfully",
                attendees,
                HttpStatus.OK,
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


    @GetMapping("/{attendee_id}")
    public ResponseEntity<Response<Attendee>> getAttendeeById(@PathVariable("attendee_id") Long attendee_id) {
        Attendee attendee = attendeeService.getAttendeeById(attendee_id);

        Response<Attendee> response = new Response<>(
                "The attendee has been fetched successfully",
                attendee,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{attendee_id}")
    public ResponseEntity<Response<Attendee>> updateAttendeeById(@Valid @RequestBody AttendeeRequest request, @PathVariable("attendee_id") Long attendeeId) {
        Attendee attendee = attendeeService.updateAttendeeById(request, attendeeId);

        Response<Attendee> response = new Response<>(
                "The attendee has been updated successfully",
                attendee,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping()
    public ResponseEntity<Response<Attendee>> saveAttendee(@Valid @RequestBody AttendeeRequest request) {
        Attendee attendee = attendeeService.saveAttendee(request);

        Response<Attendee> response = new Response<>(
                "The attendee has been created successfully",
                attendee,
                HttpStatus.CREATED,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{attendee_id}")
    public ResponseEntity<DeleteResponse<Attendee>> deleteAttendeeById(@PathVariable("attendee_id") Long attendeeId) {
        attendeeService.deleteAttendeeById(attendeeId);

        DeleteResponse<Attendee> response = new DeleteResponse<>(
                "The attendee has been deleted successfully",
                HttpStatus.OK,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

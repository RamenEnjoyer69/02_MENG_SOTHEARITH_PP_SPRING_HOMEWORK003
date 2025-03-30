package com.ramenenjoyer69.homework003.controller;

import com.ramenenjoyer69.homework003.model.entity.Attendee;
import com.ramenenjoyer69.homework003.model.entity.Venue;
import com.ramenenjoyer69.homework003.model.request.VenueRequest;
import com.ramenenjoyer69.homework003.model.response.DeleteResponse;
import com.ramenenjoyer69.homework003.model.response.Response;
import com.ramenenjoyer69.homework003.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Venue>>> getAllVenues(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        List<Venue> venues = venueService.getAllVenues(page, size);

        Response<List<Venue>> response = new Response<>(
                "All venues have been fetched successfully",
                venues,
                HttpStatus.OK,
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{venue_id}")
    public ResponseEntity<Response<Venue>> getVenueById(@PathVariable("venue_id") Long venueId) {
        Venue venue = venueService.getVenueById(venueId);

        Response<Venue> response = new Response<>(
                "The venue has been fetched successfully",
                venue,
                HttpStatus.OK,
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/{venue_id}")
    public ResponseEntity<Response<Venue>> updateVenueById(@PathVariable("venue_id") Long venueId, @RequestBody VenueRequest request) {
        Venue venue = venueService.updateVenueById(venueId, request);
        Response<Venue> response = new Response<>(
                "The venue has been updated successfully",
                venue,
                HttpStatus.OK,
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping()
    public ResponseEntity<Response<Venue>> saveVenue(@RequestBody VenueRequest request) {
        Venue venue = venueService.saveVenue(request);

        Response<Venue> response = new Response<>(
                "The venue has been created successfully",
                venue,
                HttpStatus.OK,
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{venue_id}")
    public ResponseEntity<DeleteResponse<Venue>> deleteVenueById(@PathVariable("venue_id") Long venueId) {
        Venue venue = venueService.deleteVenueById(venueId);
        DeleteResponse<Venue> response = new DeleteResponse<>(
                "The venue has been deleted successfully",
                HttpStatus.OK,
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}

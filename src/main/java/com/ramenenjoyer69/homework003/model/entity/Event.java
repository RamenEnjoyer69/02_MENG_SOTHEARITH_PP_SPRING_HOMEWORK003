package com.ramenenjoyer69.homework003.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long eventId;
    private String eventName;
    private OffsetDateTime eventDate;
    private Venue venue;
    private List<Attendee> attendees;
}

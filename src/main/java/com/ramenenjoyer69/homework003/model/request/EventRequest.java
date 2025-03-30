package com.ramenenjoyer69.homework003.model.request;

import com.ramenenjoyer69.homework003.model.entity.Venue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    private String eventName;
    private OffsetDateTime eventDate;
    private Long venueId;
    private List<Long> attendeeIds;
}
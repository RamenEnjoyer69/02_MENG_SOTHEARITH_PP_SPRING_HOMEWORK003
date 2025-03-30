package com.ramenenjoyer69.homework003.model.request;

import com.ramenenjoyer69.homework003.model.entity.Venue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    @NotNull(message = "The event name cannot be null")
    @NotBlank(message = "The event name is required")
    @Size(min = 3, max = 100 , message = "The event name must be between 3 to 100 characters")
    private String eventName;

    @NotNull(message = "The event date cannot be null")
    private OffsetDateTime eventDate;

    @NotNull(message = "The venue id cannot be null")
    private Long venueId;

    private List<Long> attendeeIds;
}
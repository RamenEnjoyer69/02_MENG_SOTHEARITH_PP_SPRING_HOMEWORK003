package com.ramenenjoyer69.homework003.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueRequest {

    @NotNull(message = "The venue name cannot be null")
    @NotBlank(message = "The venue name is required")
    @Size(min = 2, max = 100 , message = "The venue name must be between 2 to 100 characters")
    private String venueName;

    @NotNull(message = "The venue location cannot be null")
    @NotBlank(message = "The venue location is required")
    private String location;
}
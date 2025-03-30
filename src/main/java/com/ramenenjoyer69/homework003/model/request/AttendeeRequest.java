package com.ramenenjoyer69.homework003.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {
    @NotNull(message = "The attendee name cannot be null")
    @NotBlank(message = "The attendee name is required")
    @Size(min = 2, max = 100 , message = "The attendee name must be between 2 to 100 characters")
    private String attendeeName;


    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email")
    private String email;
}

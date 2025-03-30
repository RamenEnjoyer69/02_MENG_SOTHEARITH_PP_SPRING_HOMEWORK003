package com.ramenenjoyer69.homework003.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendee {
    private int attendeeId;
    private String attendeeName;
    private String email;
}

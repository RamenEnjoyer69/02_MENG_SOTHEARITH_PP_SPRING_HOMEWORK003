package com.ramenenjoyer69.homework003.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venue {
    private int venueId;
    private String venueName;
    private String location;

}

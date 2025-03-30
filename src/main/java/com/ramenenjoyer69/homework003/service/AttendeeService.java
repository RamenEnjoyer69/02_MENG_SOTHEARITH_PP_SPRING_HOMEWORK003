package com.ramenenjoyer69.homework003.service;

import com.ramenenjoyer69.homework003.model.entity.Attendee;
import com.ramenenjoyer69.homework003.model.request.AttendeeRequest;

import java.util.List;

public interface AttendeeService {

    List<Attendee> getAllAttendees(Integer page, Integer size);

    Attendee getAttendeeById(Long attendeeId);

    Attendee updateAttendeeById(AttendeeRequest request, Long attendeeId);

    Attendee saveAttendee(AttendeeRequest request);

    Attendee deleteAttendeeById(Long attendeeId);
}

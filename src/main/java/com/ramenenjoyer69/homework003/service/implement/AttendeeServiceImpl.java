package com.ramenenjoyer69.homework003.service.implement;


import com.ramenenjoyer69.homework003.model.entity.Attendee;
import com.ramenenjoyer69.homework003.model.request.AttendeeRequest;
import com.ramenenjoyer69.homework003.repository.AttendeeRepository;
import com.ramenenjoyer69.homework003.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public List<Attendee> getAllAttendees(Integer page, Integer size) {
        int offset = (page - 1) * size;

        return attendeeRepository.getAllAttendees(offset, size);
    }

    @Override
    public Attendee getAttendeeById(Long attendeeId) {
        return attendeeRepository.getAttendeeById(attendeeId);
    }

    @Override
    public Attendee updateAttendeeById(AttendeeRequest request, Long attendeeId) {
        return attendeeRepository.updateAttendeeById(request, attendeeId);
    }

    @Override
    public Attendee saveAttendee(AttendeeRequest request) {
        return attendeeRepository.saveAttendee(request);
    }

    @Override
    public Attendee deleteAttendeeById(Long attendeeId) {
        return attendeeRepository.deleteAttendeeById(attendeeId);
    }
}

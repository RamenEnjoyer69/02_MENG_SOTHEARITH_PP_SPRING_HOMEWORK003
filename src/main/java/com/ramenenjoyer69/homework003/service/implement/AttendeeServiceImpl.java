package com.ramenenjoyer69.homework003.service.implement;


import com.ramenenjoyer69.homework003.exception.NotFoundException;
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
    public List<Attendee> getAllAttendees(Integer page, Integer size) throws NotFoundException {
        int offset = (page - 1) * size;
        List<Attendee> attendees = attendeeRepository.getAllAttendees(offset, size);

        try{
            if(attendees.isEmpty()){
                throw new NotFoundException(("No attendees found"));
            }
        }catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
        return attendees;
    }

    @Override
    public Attendee getAttendeeById(Long attendeeId) throws NotFoundException {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
        try{
            if (attendee == null)
                throw new NotFoundException("No attendee with ID [ " + attendeeId + " ] is found");
        }catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
        return attendee;
    }

    @Override
    public Attendee updateAttendeeById(AttendeeRequest request, Long attendeeId) throws NotFoundException {
        Attendee attendee = attendeeRepository.updateAttendeeById(request, attendeeId);
        try{
            if (attendee == null)
                throw new NotFoundException("No attendee with ID [ " + attendeeId + " ] is found");
        }catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
        return attendee;
    }

    @Override
    public Attendee saveAttendee(AttendeeRequest request) {
        return attendeeRepository.saveAttendee(request);
    }

    @Override
    public Attendee deleteAttendeeById(Long attendeeId) throws NotFoundException {
        Attendee attendee = attendeeRepository.deleteAttendeeById(attendeeId);
        try{
            if (attendee == null)
                throw new NotFoundException("No attendee with ID [ " + attendeeId + " ] is found");
        }catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
        return attendee;
    }
}

package com.ramenenjoyer69.homework003.repository;

import com.ramenenjoyer69.homework003.model.entity.Attendee;
import com.ramenenjoyer69.homework003.model.request.AttendeeRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {


    @Results(id = "attendeeMapper",value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")

    })
    @Select("""
        SELECT * FROM attendees
        OFFSET #{offset} LIMIT #{size}
    """)
    List<Attendee> getAllAttendees(int offset, Integer size);

    @ResultMap("attendeeMapper")
    @Select("""
        SELECT * FROM attendees where attendee_id = #{attendeeId}
    """)
    Attendee getAttendeeById(Long attendeeId);

    @ResultMap("attendeeMapper")
    @Select("""
        UPDATE attendees set attendee_name = #{req.attendeeName}, email = #{req.email} where attendee_id = #{attendeeId} returning *
    """)
    Attendee updateAttendeeById(@Param("req") AttendeeRequest request, Long attendeeId);

    @ResultMap("attendeeMapper")
    @Select("""
        INSERT INTO attendees VALUES (default, #{req.attendeeName}, #{req.email}) RETURNING *
    """)
    Attendee saveAttendee(@Param("req") AttendeeRequest request);

    @Select("""
        DELETE FROM attendees where attendee_id = #{attendeeId}
    """)
    Attendee deleteAttendeeById(Long attendeeId);
}

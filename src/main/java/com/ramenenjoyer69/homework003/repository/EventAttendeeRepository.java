package com.ramenenjoyer69.homework003.repository;

import com.ramenenjoyer69.homework003.model.entity.Attendee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventAttendeeRepository {

    @Results(id = "eaMapper", value = {
            @Result(property = "attendeeId",column = "attendee_id"),
            @Result(property = "attendeeName",column = "attendee_name"),
            @Result(property = "email",column = "email")
    })
    @Select("""
        SELECT * FROM event_attendee ea INNER JOIN attendees a ON ea.attendee_id = a.attendee_id WHERE event_id = #{eventId}
    """)
    List<Attendee> getAttendeesByEventId(Long eventId);

    @Select("""
        SELECT attendee_id FROM event_attendee WHERE event_id = #{eventId}
    """)
    List<Long> getAttendeeIdsByEventId(Long eventId);

    @Insert("""
         INSERT INTO event_attendee VALUES (default, #{eventId}, #{attendeeId})
     """)
    void insertEventIdAndAttendeeId(Long eventId, Long attendeeId);

    @Delete("""
        DELETE FROM event_attendee
        WHERE event_id = #{eventId} AND attendee_id = #{attendeeId}
    """)
    void removeEventAttendees(Long eventId, Long attendeeId);
}

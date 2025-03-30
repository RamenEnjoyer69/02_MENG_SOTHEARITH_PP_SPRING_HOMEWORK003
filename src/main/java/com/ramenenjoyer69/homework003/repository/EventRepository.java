package com.ramenenjoyer69.homework003.repository;

import com.ramenenjoyer69.homework003.model.entity.Event;
import com.ramenenjoyer69.homework003.model.request.EventRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventRepository {

    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id",
                    one = @One(select = "com.ramenenjoyer69.homework003.repository.VenueRepository.getVenueById")),
            @Result(property = "attendees", column = "event_id", many = @Many(select = "com.ramenenjoyer69.homework003.repository.EventAttendeeRepository.getAttendeesByEventId"))
    })
    @Select("""
        SELECT * FROM events
        OFFSET #{offset} LIMIT #{size}
    """)
    List<Event> getAllEvents(int offset, int size);

    @ResultMap("eventMapper")
    @Select("""
        SELECT * FROM events where event_id = #{eventId}
    """)
    Event getEventById(Long eventId);

    @ResultMap("eventMapper")
    @Select("""
        UPDATE events set event_name = #{req.eventName}, event_date = #{req.eventDate}, venue_id = #{req.venueId} where event_id = #{eventId} RETURNING *
    """)
    Event updateEventById(Long eventId,@Param("req") EventRequest request);

    @ResultMap("eventMapper")
    @Select("""
        INSERT INTO events VALUES (default, #{req.eventName}, #{req.eventDate}, #{req.venueId}) returning *
    """)
    Event saveEvent(@Param("req") EventRequest request);

    @Select("""
        DELETE FROM events where event_id = #{eventId}
    """)
    Event deleteEventById(Long eventId);
}

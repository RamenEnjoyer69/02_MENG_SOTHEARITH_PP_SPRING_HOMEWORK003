package com.ramenenjoyer69.homework003.repository;

import com.ramenenjoyer69.homework003.model.entity.Venue;
import com.ramenenjoyer69.homework003.model.request.VenueRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {


    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),

    })
    @Select("""
        SELECT * FROM venues
        OFFSET #{offset} LIMIT #{size}
    """)
    List<Venue> getAllVenues(Integer offset, Integer size);

    @ResultMap("venueMapper")
    @Select("""
        SELECT * FROM venues where venue_id = #{venueId}
    """)
    Venue getVenueById(Long venueId);

    @ResultMap("venueMapper")
    @Select("""
        UPDATE venues set venue_name = #{req.venueName}, location = #{req.location} where venue_id = #{venueId} RETURNING *
    """)
    Venue updateVenueById(Long venueId, @Param("req") VenueRequest request);

    @ResultMap("venueMapper")
    @Select("""
        INSERT INTO venues VALUES (default, #{req.venueName}, #{req.location}) RETURNING *
    """)
    Venue saveVenue(@Param("req") VenueRequest request);

    @Select("""
        DELETE FROM venues where venue_id = #{venueId}
    """)
    Venue deleteVenueById(Long venueId);
}

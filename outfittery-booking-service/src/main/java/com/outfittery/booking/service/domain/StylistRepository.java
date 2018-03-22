package com.outfittery.booking.service.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.outfittery.stylist.service.api.events.StylistState;

public interface StylistRepository extends CrudRepository<Stylist, Long>
{

    Stylist findById(long stylistId);
    
    @Query("select s from Stylist s where s.state = :state")
    List<Stylist> findByStylistStateReturnList(@Param("state") StylistState state);
}

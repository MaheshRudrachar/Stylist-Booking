package com.outfittery.booking.service.domain;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long>
{

    Booking findOne(long bookingId);


    Booking findByCustomerId(long stylistId);
}

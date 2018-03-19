package com.outfittery.booking.service.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.outfittery.booking.service.api.events.BookingDomainEvent;

public class BookingAuthorized implements BookingDomainEvent
{

    @Override
    public boolean equals(Object o)
    {
        return EqualsBuilder.reflectionEquals(this, o);
    }


    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}

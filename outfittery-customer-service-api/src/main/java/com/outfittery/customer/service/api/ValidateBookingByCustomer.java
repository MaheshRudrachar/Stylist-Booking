package com.outfittery.customer.service.api;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.eventuate.tram.commands.common.Command;

public class ValidateBookingByCustomer implements Command
{

    private long customerId;

    private long bookingId;


    private ValidateBookingByCustomer()
    {}


    public ValidateBookingByCustomer(long customerId, long bookingId)
    {
        this.customerId = customerId;
        this.bookingId = bookingId;
    }


    public Long getBookingId()
    {
        return bookingId;
    }


    public long getCustomerId()
    {
        return customerId;
    }


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


    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }


    public void setCustomerId(long customerId)
    {
        this.customerId = customerId;
    }


    public void setBookingId(long bookingId)
    {
        this.bookingId = bookingId;
    }
}

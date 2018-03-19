package com.outfittery.booking.service.api.web;

import java.util.Set;

import com.outfittery.stylist.service.api.utils.TimeRange;

public class CreateBookingRequest
{

    private long stylistId;
    private long customerId;
    private Set<TimeRange> bookingSlot;


    public CreateBookingRequest(long stylistId, long customerId, Set<TimeRange> bookingSlot)
    {
        super();
        this.stylistId = stylistId;
        this.customerId = customerId;
        this.bookingSlot = bookingSlot;
    }


    public long getStylistId()
    {
        return stylistId;
    }


    public void setStylistId(long stylistId)
    {
        this.stylistId = stylistId;
    }


    public long getCustomerId()
    {
        return customerId;
    }


    public void setCustomerId(long customerId)
    {
        this.customerId = customerId;
    }


    public Set<TimeRange> getBookingSlot()
    {
        return bookingSlot;
    }


    public void setBookingSlot(Set<TimeRange> bookingSlot)
    {
        this.bookingSlot = bookingSlot;
    }

}

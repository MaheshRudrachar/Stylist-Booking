package com.outfittery.booking.service.web;

import java.util.Set;

import com.outfittery.stylist.service.api.utils.TimeRange;

public class GetBookingResponse
{

    private long bookingId;
    private String state;
    private Set<TimeRange> bookingSlot;


    public Set<TimeRange> getBookingSlot()
    {
        return bookingSlot;
    }


    public void setBookingSlot(Set<TimeRange> bookingSlot)
    {
        this.bookingSlot = bookingSlot;
    }


    public long getBookingId()
    {
        return bookingId;
    }


    public void setBookingId(long bookingId)
    {
        this.bookingId = bookingId;
    }


    public String getState()
    {
        return state;
    }


    public void setState(String state)
    {
        this.state = state;
    }


    private GetBookingResponse()
    {}


    public GetBookingResponse(long bookingId, String state, Set<TimeRange> bookingSlot)
    {

        this.bookingId = bookingId;
        this.state = state;
        this.bookingSlot = bookingSlot;
    }

}

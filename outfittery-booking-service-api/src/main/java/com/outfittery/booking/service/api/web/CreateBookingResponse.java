package com.outfittery.booking.service.api.web;

public class CreateBookingResponse
{

    private long bookingId;


    private CreateBookingResponse()
    {

    }


    public CreateBookingResponse(long bookingId)
    {
        super();
        this.bookingId = bookingId;
    }


    public long getBookingId()
    {
        return bookingId;
    }


    public void setBookingId(long bookingId)
    {
        this.bookingId = bookingId;
    }

}

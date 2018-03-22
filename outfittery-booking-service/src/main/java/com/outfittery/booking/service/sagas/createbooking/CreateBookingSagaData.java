package com.outfittery.booking.service.sagas.createbooking;

import com.outfittery.booking.service.api.events.BookingDetails;

public class CreateBookingSagaData
{

    private long bookingId;

    private BookingDetails bookingDetails;
    private long stylistBookingId;


    public long getBookingId()
    {
        return bookingId;
    }


    private CreateBookingSagaData()
    {}


    public CreateBookingSagaData(long bookingId, BookingDetails bookingDetails)
    {
        this.bookingId = bookingId;
        this.bookingDetails = bookingDetails;
    }


    public BookingDetails getBookingDetails()
    {
        return bookingDetails;
    }


    public void setBookingId(long bookingId)
    {
        this.bookingId = bookingId;
    }


    public void setStylistBookingId(long stylistBookingId)
    {
        this.stylistBookingId = stylistBookingId;
    }


    public long getStylistBookingId()
    {
        return stylistBookingId;
    }
}

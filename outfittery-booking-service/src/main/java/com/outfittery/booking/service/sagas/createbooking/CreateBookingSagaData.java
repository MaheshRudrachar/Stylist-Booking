package com.outfittery.booking.service.sagas.createbooking;

import com.outfittery.booking.service.api.events.BookingDetails;

public class CreateBookingSagaData
{

    private Long bookingId;

    private BookingDetails bookingDetails;
    private long stylistBookingId;


    public Long getBookingId()
    {
        return bookingId;
    }


    private CreateBookingSagaData()
    {}


    public CreateBookingSagaData(Long bookingId, BookingDetails bookingDetails)
    {
        this.bookingId = bookingId;
        this.bookingDetails = bookingDetails;
    }


    public BookingDetails getBookingDetails()
    {
        return bookingDetails;
    }


    public void setBookingId(Long bookingId)
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

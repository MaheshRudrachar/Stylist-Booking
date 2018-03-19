package com.outfittery.booking.service.api.events;

public class BookingCreatedEvent implements BookingDomainEvent
{

    private BookingDetails bookingDetails;


    private BookingCreatedEvent()
    {

    }


    public BookingCreatedEvent(BookingDetails bookingDetails)
    {
        this.bookingDetails = bookingDetails;
    }


    public BookingDetails getBookingDetails()
    {
        return bookingDetails;
    }


    public void setBookingDetails(BookingDetails bookingDetails)
    {
        this.bookingDetails = bookingDetails;
    }

}

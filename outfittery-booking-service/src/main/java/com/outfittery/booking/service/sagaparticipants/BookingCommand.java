package com.outfittery.booking.service.sagaparticipants;

import io.eventuate.tram.commands.common.Command;

public abstract class BookingCommand implements Command
{

    private long bookingId;


    protected BookingCommand()
    {}


    protected BookingCommand(long bookingId)
    {
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

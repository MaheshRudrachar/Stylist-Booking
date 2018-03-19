package com.outfittery.booking.service.sagaparticipants;

public class ApproveBookingCommand extends BookingCommand
{

    private ApproveBookingCommand()
    {}


    public ApproveBookingCommand(long bookingId)
    {
        super(bookingId);
    }
}

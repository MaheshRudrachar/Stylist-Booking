package com.outfittery.booking.service.sagaparticipants;

import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;

import com.outfittery.booking.service.service.api.BookingServiceChannels;

import io.eventuate.tram.commands.common.Success;

public class BookingServiceProxy
{

    public final CommandEndpoint<ApproveBookingCommand> approve = CommandEndpointBuilder
        .forCommand(ApproveBookingCommand.class)
        .withChannel(BookingServiceChannels.bookingServiceChannel)
        .withReply(Success.class)
        .build();

}

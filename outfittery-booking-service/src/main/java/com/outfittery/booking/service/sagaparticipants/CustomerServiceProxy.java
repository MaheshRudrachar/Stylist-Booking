package com.outfittery.booking.service.sagaparticipants;

import com.outfittery.customer.service.api.CustomerServiceChannels;
import com.outfittery.customer.service.api.ValidateBookingByCustomer;

import io.eventuate.tram.commands.common.Success;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;

public class CustomerServiceProxy
{

    public final CommandEndpoint<ValidateBookingByCustomer> validateBooking = CommandEndpointBuilder
        .forCommand(ValidateBookingByCustomer.class)
        .withChannel(CustomerServiceChannels.customerServiceChannel)
        .withReply(Success.class)
        .build();

}

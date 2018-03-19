package com.outfittery.customer.service.domain;

import org.springframework.beans.factory.annotation.Autowired;

import com.outfittery.customer.service.api.ValidateBookingByCustomer;

import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class CustomerServiceCommandHandlers
{

    @Autowired
    private CustomerService consumerService;


    public CommandHandlers commandHandlers()
    {
        return SagaCommandHandlersBuilder
            .fromChannel("customerService")
            .onMessage(ValidateBookingByCustomer.class, this::validateBookingForCustomer)
            .build();
    }


    private Message validateBookingForCustomer(CommandMessage<ValidateBookingByCustomer> cm)
    {
        try
        {
            consumerService.validateBookingForCustomer(cm.getCommand().getCustomerId(), cm.getCommand().getBookingId());
            return withSuccess();
        }
        catch (CustomerVerificationFailedException e)
        {
            return withFailure();
        }
    }
}

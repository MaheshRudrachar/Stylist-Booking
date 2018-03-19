package com.outfittery.booking.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.outfittery.booking.service.domain.BookingService;
import com.outfittery.booking.service.sagaparticipants.ApproveBookingCommand;

import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class BookingCommandHandlers
{

    @Autowired
    private BookingService bookingService;


    public CommandHandlers commandHandlers()
    {
        return SagaCommandHandlersBuilder
            .fromChannel("bookingService")
            .onMessage(ApproveBookingCommand.class, this::approveBooking)

            .build();

    }


    public Message approveBooking(CommandMessage<ApproveBookingCommand> cm)
    {
        long bookingId = cm.getCommand().getBookingId();
        bookingService.approveBooking(bookingId);
        return withSuccess();
    }
}

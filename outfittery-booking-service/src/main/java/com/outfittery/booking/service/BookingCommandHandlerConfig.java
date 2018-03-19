package com.outfittery.booking.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaParticipantConfiguration;

@Configuration
@Import({SagaParticipantConfiguration.class, TramEventsPublisherConfiguration.class})
public class BookingCommandHandlerConfig
{

    @Bean
    public BookingCommandHandlers bookingCommandHandlers()
    {
        return new BookingCommandHandlers();
    }


    @Bean
    public SagaCommandDispatcher bookingCommandHandlersDispatcher(BookingCommandHandlers bookingCommandHandlers)
    {
        return new SagaCommandDispatcher("bookingService", bookingCommandHandlers.commandHandlers());
    }

}

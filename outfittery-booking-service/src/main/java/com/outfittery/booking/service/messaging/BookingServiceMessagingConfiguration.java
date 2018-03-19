package com.outfittery.booking.service.messaging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.outfittery.booking.service.domain.BookingService;
import com.outfittery.booking.service.domain.BookingServiceRepoConfiguration;

import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.messaging.consumer.MessageConsumer;

@Configuration
@Import({BookingServiceRepoConfiguration.class})
public class BookingServiceMessagingConfiguration
{

    @Bean
    public BookingEventConsumer bookingEventConsumer(BookingService bookingService)
    {
        return new BookingEventConsumer(bookingService);
    }


    @Bean
    public DomainEventDispatcher domainEventDispatcher(BookingEventConsumer bookingEventConsumer, MessageConsumer messageConsumer)
    {
        return new DomainEventDispatcher("bookingServiceEvents", bookingEventConsumer.domainEventHandlers(), messageConsumer);
    }
}

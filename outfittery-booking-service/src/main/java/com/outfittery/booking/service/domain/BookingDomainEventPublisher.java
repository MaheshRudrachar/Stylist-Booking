package com.outfittery.booking.service.domain;

import com.outfittery.booking.service.api.events.BookingDomainEvent;

import io.eventuate.tram.events.publisher.DomainEventPublisher;

public class BookingDomainEventPublisher extends AbstractAggregateDomainEventPublisher<Booking, BookingDomainEvent>
{

    public BookingDomainEventPublisher(DomainEventPublisher eventPublisher)
    {
        super(eventPublisher, Booking.class, Booking::getId);
    }

}

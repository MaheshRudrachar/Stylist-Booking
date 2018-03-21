package com.outfittery.booking.service.messaging;

import com.outfittery.booking.service.domain.BookingService;
import com.outfittery.stylist.service.api.events.StylistCreated;
import com.outfittery.stylist.service.api.events.StylistUpdated;

import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;

public class BookingEventConsumer
{

    BookingService bookingService;


    public BookingEventConsumer(BookingService bookingService)
    {
        this.bookingService = bookingService;
    }


    public DomainEventHandlers domainEventHandlers()
    {
        return DomainEventHandlersBuilder.forAggregateType("com.outfittery.stylist.service.domain.Stylist")
            .onEvent(StylistCreated.class, this::createStylist)
            .onEvent(StylistUpdated.class, this::updateStylist)
            .build();
    }


    private void createStylist(DomainEventEnvelope<StylistCreated> de)
    {
        String stylistIds = de.getAggregateId();
        long id = Long.parseLong(stylistIds);
        bookingService.createStylist(id, de.getEvent().getFirstName(), de.getEvent().getLastName(), de.getEvent().getState(), de.getEvent().getStylistShift());
    }


    public void updateStylist(DomainEventEnvelope<StylistUpdated> de)
    {

        String stylistIds = de.getAggregateId();
        long id = Long.parseLong(stylistIds);
        bookingService.stylistStateUpdated(id, de.getEvent().getState());
    }

}

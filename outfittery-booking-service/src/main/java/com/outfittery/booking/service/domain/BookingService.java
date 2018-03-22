package com.outfittery.booking.service.domain;

import org.springframework.transaction.annotation.Transactional;

import com.outfittery.booking.service.api.events.BookingDetails;
import com.outfittery.booking.service.api.events.BookingDomainEvent;
import com.outfittery.booking.service.sagas.createbooking.CreateBookingSagaData;
import com.outfittery.stylist.service.api.events.StylistState;
import com.outfittery.stylist.service.api.utils.TimeRange;

import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.sagas.orchestration.SagaManager;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Transactional
public class BookingService
{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private BookingRepository bookingRepository;

    private StylistRepository stylistRepository;

    private SagaManager<CreateBookingSagaData> createBookingSagaManager;

    private BookingDomainEventPublisher bookingAggregateEventPublisher;


    public BookingService(BookingRepository bookingRepository, DomainEventPublisher eventPublisher,
        StylistRepository stylistRepository, SagaManager<CreateBookingSagaData> createBookingSagaManager,
        BookingDomainEventPublisher bookingAggregateEventPublisher)
    {
        this.bookingRepository = bookingRepository;
        this.stylistRepository = stylistRepository;
        this.createBookingSagaManager = createBookingSagaManager;
        this.bookingAggregateEventPublisher = bookingAggregateEventPublisher;
    }


    public Booking createBooking(long customerId, long stylistId, Set<TimeRange> bookingSlot)
    {
        Stylist stylist = stylistRepository.findById(stylistId);
        if (stylist == null)
            throw new RuntimeException("Stylist not found: " + stylistId);

        ResultWithDomainEvents<Booking, BookingDomainEvent> bookAndEvents = Booking.createBooking(customerId, stylistId, bookingSlot);
        Booking booking = bookAndEvents.result;
        bookingRepository.save(booking);

        bookingAggregateEventPublisher.publish(booking, bookAndEvents.events);

        BookingDetails bookingDetails = new BookingDetails(customerId, stylistId, bookingSlot);
        CreateBookingSagaData data = new CreateBookingSagaData(booking.getId(), bookingDetails);
        createBookingSagaManager.create(data, Booking.class, booking.getId());

        return booking;
    }


    public void createStylist(long id, String firstName, String lastName, StylistState state, Set<TimeRange> stylistShift)
    {
        stylistRepository.save(new Stylist(id, firstName, lastName, stylistShift, state));
    }


    public void stylistStateUpdated(long id, StylistState state)
    {
        Stylist stylist = stylistRepository.findById(id);
        stylist.setState(state);
    }


    Booking updateBooking(long bookingId, Function<Booking, List<BookingDomainEvent>> updater)
    {
        Booking booking = bookingRepository.findById(bookingId);
        bookingAggregateEventPublisher.publish(booking, updater.apply(booking));
        return booking;
    }


    public void approveBooking(long bookingId)
    {
        updateBooking(bookingId, Booking::noteAuthorized);
    }

}

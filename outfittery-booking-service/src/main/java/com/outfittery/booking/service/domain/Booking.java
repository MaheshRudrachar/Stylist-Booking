package com.outfittery.booking.service.domain;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.*;

import com.outfittery.booking.service.api.events.BookingState;
import com.outfittery.stylist.service.api.utils.TimeRange;
import com.outfittery.booking.service.api.events.BookingCreatedEvent;
import com.outfittery.booking.service.api.events.BookingDetails;
import com.outfittery.booking.service.api.events.BookingDomainEvent;
import io.eventuate.tram.events.ResultWithEvents;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.outfittery.booking.service.api.events.BookingState.CREATE_PENDING;
import static com.outfittery.booking.service.api.events.BookingState.AUTHORIZED;

@Entity
@Table(name = "booking")
@Access(AccessType.FIELD)
public class Booking
{

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    @Enumerated(EnumType.STRING)
    private BookingState state;

    private long customerId;

    private long stylistId;
    
    private LocalDateTime bookingGenerated;

    @ElementCollection
    private Set<TimeRange> bookingSlot = new TreeSet<TimeRange>();


    private Booking()
    {

    }


    public Booking(long customerId, long stylistId, Set<TimeRange> bookingSlot)
    {
        super();
        this.customerId = customerId;
        this.stylistId = stylistId;
        this.bookingSlot = bookingSlot;
        this.state = CREATE_PENDING;
        this.bookingGenerated = LocalDateTime.now();
    }


    public void setId(long id)
    {
        this.id = id;
    }


    public long getId()
    {
        return id;
    }


    public long getVersion()
    {
        return version;
    }


    public BookingState getState()
    {
        return state;
    }


    public long getCustomerId()
    {
        return customerId;
    }


    public long getStylistId()
    {
        return stylistId;
    }


    public Set<TimeRange> getBookingSlot()
    {
        return bookingSlot;
    }
    
    public LocalDateTime getBookingGenerated()
    {
        return bookingGenerated;
    }


    public void setBookingGenerated(LocalDateTime bookingGenerated)
    {
        this.bookingGenerated = bookingGenerated;
    }


    public static ResultWithDomainEvents<Booking, BookingDomainEvent> createBooking(long customerId, long stylistId,
        Set<TimeRange> bookingSlot)
    {
        Booking booking = new Booking(customerId, stylistId, bookingSlot);
        List<BookingDomainEvent> events = singletonList(
            new BookingCreatedEvent(new BookingDetails(customerId, stylistId, bookingSlot)));
        return new ResultWithDomainEvents<>(booking, events);
    }


    public List<BookingDomainEvent> noteAuthorized()
    {
        switch (state)
        {
            case CREATE_PENDING:
                this.state = AUTHORIZED;
                return singletonList(new BookingAuthorized());
            default:
                throw new UnsupportedStateTransitionException(state);
        }

    }

}

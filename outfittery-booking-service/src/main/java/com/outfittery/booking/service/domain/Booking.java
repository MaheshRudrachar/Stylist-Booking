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

import static com.outfittery.booking.service.api.events.BookingState.CREATE_PENDING;
import static com.outfittery.booking.service.api.events.BookingState.AUTHORIZED;

@Entity
@Table(name = "booking")
@Access(AccessType.FIELD)
public class Booking
{

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Enumerated(EnumType.STRING)
    private BookingState state;

    private Long customerId;

    private Long stylistId;

    @ElementCollection
    private Set<TimeRange> bookingSlot = new TreeSet<TimeRange>();


    private Booking()
    {

    }


    public Booking(Long customerId, Long stylistId, Set<TimeRange> bookingSlot)
    {
        super();
        this.customerId = customerId;
        this.stylistId = stylistId;
        this.bookingSlot = bookingSlot;
        this.state = CREATE_PENDING;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public Long getId()
    {
        return id;
    }


    public Long getVersion()
    {
        return version;
    }


    public BookingState getState()
    {
        return state;
    }


    public Long getCustomerId()
    {
        return customerId;
    }


    public Long getStylistId()
    {
        return stylistId;
    }


    public Set<TimeRange> getBookingSlot()
    {
        return bookingSlot;
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

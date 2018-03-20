package com.outfittery.booking.service.domain;

import org.junit.Before;
import org.junit.Test;

import com.outfittery.booking.service.api.events.BookingCreatedEvent;
import com.outfittery.booking.service.api.events.BookingDomainEvent;
import com.outfittery.booking.service.api.events.BookingState;
import com.outfittery.booking.service.helper.BookingHelper;

import static com.outfittery.booking.service.helper.StylistHelper.FAKE_STYLIST_ID;
import static com.outfittery.booking.service.helper.StylistHelper.FAKE_STYLIST_NAME;
import static com.outfittery.booking.service.helper.StylistHelper.FAKE_STYLIST_OBJECT;

import static com.outfittery.booking.service.helper.BookingHelper.FAKE_BOOKING_ID;
import static com.outfittery.booking.service.helper.BookingHelper.FAKE_CUSTOMER_ID;
import static com.outfittery.booking.service.helper.BookingHelper.FAKE_BOOKING_DETAILS;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

import java.util.List;

public class BookingTest
{

    private ResultWithDomainEvents<Booking, BookingDomainEvent> createResult;
    private Booking booking;

    @Before
    public void setUp() throws Exception {
      createResult = Booking.createBooking(FAKE_CUSTOMER_ID, FAKE_STYLIST_ID, BookingHelper.fakeBookingSlot());
      booking = createResult.result;
    }

    @Test
    public void shouldCreateBooking() {
      assertEquals(singletonList(new BookingCreatedEvent(FAKE_BOOKING_DETAILS)), createResult.events);

      assertEquals(BookingState.CREATE_PENDING, booking.getState());
      // ...
    }

    @Test
    public void shouldAuthorize() {
      List<BookingDomainEvent> events = booking.noteAuthorized();
      assertEquals(singletonList(new BookingAuthorized()), events);
      assertEquals(BookingState.AUTHORIZED, booking.getState());
    }
}

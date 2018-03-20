package com.outfittery.booking.service.helper;

import com.outfittery.booking.service.api.events.BookingDetails;
import com.outfittery.booking.service.api.events.BookingState;
import com.outfittery.booking.service.domain.Booking;
import com.outfittery.stylist.service.api.utils.TimeRange;

import static com.outfittery.booking.service.helper.StylistHelper.FAKE_STYLIST_ID;
import static com.outfittery.booking.service.helper.StylistHelper.FAKE_STYLIST_NAME;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.TreeSet;

public class BookingHelper
{

    public static long FAKE_CUSTOMER_ID = 1511L;

    public static final int FAKE_QUANTITY = 5;

    public static final BookingDetails FAKE_BOOKING_DETAILS = new BookingDetails(FAKE_CUSTOMER_ID, FAKE_STYLIST_ID,
        fakeBookingSlot());

    public static long FAKE_BOOKING_ID = 99L;

    public static Booking FAKE_BOOKING = makeFakeBooking();

    public static final BookingState FAKE_BOOKING_STATE = BookingState.CREATE_PENDING;

    public static Set<TimeRange> fakeBookingSlot()
    {
        LocalTime start = LocalTime.parse("11:00");
        LocalTime end = LocalTime.parse("11:30");
        TimeRange t1 = new TimeRange(DayOfWeek.TUESDAY, start, end);

        Set<TimeRange> ts1 = new TreeSet<TimeRange>();
        ts1.add(t1);

        return ts1;
    }
    
    private static Booking makeFakeBooking() {
      Booking booking = new Booking(FAKE_CUSTOMER_ID, FAKE_STYLIST_ID, fakeBookingSlot());
      booking.setId(FAKE_BOOKING_ID);
      return booking;
    }
}

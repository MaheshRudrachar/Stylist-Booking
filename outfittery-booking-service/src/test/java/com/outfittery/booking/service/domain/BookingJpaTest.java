package com.outfittery.booking.service.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;

import com.outfittery.booking.service.api.events.BookingState;
import com.outfittery.booking.service.domain.Booking;
import com.outfittery.booking.service.domain.BookingRepository;
import com.outfittery.booking.service.helper.BookingHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookingJpaTestConfiguration.class)
public class BookingJpaTest {

  @Autowired
  private BookingRepository bookingRepository;

  @Autowired
  private TransactionTemplate transactionTemplate;

  @Test
  public void shouldSaveAndLoadBooking() {

    long bookingId = transactionTemplate.execute((ts) -> {
      Booking booking = new Booking(BookingHelper.FAKE_CUSTOMER_ID, BookingHelper.FAKE_BOOKING_ID, BookingHelper.fakeBookingSlot());
      bookingRepository.save(booking);
      return booking.getId();
    });


    transactionTemplate.execute((ts) -> {
      Booking booking = bookingRepository.findById(bookingId);

      assertNotNull(booking);
      assertEquals(BookingState.CREATE_PENDING, booking.getState());
      assertEquals(BookingHelper.FAKE_BOOKING_ID, booking.getId());
      assertEquals(BookingHelper.FAKE_CUSTOMER_ID, booking.getCustomerId());
      assertEquals(BookingHelper.fakeBookingSlot(), booking.getBookingSlot());
      return null;
    });

  }

}
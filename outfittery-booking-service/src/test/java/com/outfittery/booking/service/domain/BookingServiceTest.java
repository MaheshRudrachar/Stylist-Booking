package com.outfittery.booking.service.domain;

import com.outfittery.booking.service.api.events.BookingCreatedEvent;
import com.outfittery.booking.service.helper.BookingHelper;
import com.outfittery.booking.service.sagas.createbooking.CreateBookingSagaData;

import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.sagas.orchestration.SagaManager;

import static org.mockito.Matchers.same;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import static com.outfittery.booking.service.helper.StylistHelper.FAKE_STYLIST_ID;
import static com.outfittery.booking.service.helper.StylistHelper.FAKE_STYLIST_NAME;
import static com.outfittery.booking.service.helper.StylistHelper.FAKE_STYLIST_OBJECT;

import static com.outfittery.booking.service.helper.BookingHelper.FAKE_BOOKING_ID;
import static com.outfittery.booking.service.helper.BookingHelper.FAKE_CUSTOMER_ID;
import static com.outfittery.booking.service.helper.BookingHelper.FAKE_BOOKING_DETAILS;


public class BookingServiceTest
{
    private BookingService bookingService;
    private BookingRepository bookingRepository;
    private DomainEventPublisher eventPublisher;
    private StylistRepository stylistRepository;
    private SagaManager<CreateBookingSagaData> createBookingSagaManager;
    private BookingDomainEventPublisher bookingAggregateEventPublisher;

    @Before
    public void setup() {
      bookingRepository = mock(BookingRepository.class);
      eventPublisher = mock(DomainEventPublisher.class);
      stylistRepository = mock(StylistRepository.class);
      createBookingSagaManager = mock(SagaManager.class);
      
      // Mock DomainEventPublisher AND use the real BookingDomainEventPublisher

      bookingAggregateEventPublisher = mock(BookingDomainEventPublisher.class);

      bookingService = new BookingService(bookingRepository, eventPublisher, stylistRepository,
              createBookingSagaManager, bookingAggregateEventPublisher);
    }


    @Test
    public void shouldCreateBooking() {
      when(stylistRepository.findOne(FAKE_STYLIST_ID)).thenReturn(FAKE_STYLIST_OBJECT);
      when(bookingRepository.save(any(Booking.class))).then(invocation -> {
        Booking booking = (Booking) invocation.getArguments()[0];
        booking.setId(FAKE_BOOKING_ID);
        return booking;
      });

      Booking booking = bookingService.createBooking(FAKE_CUSTOMER_ID, FAKE_STYLIST_ID, BookingHelper.fakeBookingSlot());

      verify(bookingRepository).save(same(booking));

      verify(bookingAggregateEventPublisher).publish(booking,
              Collections.singletonList(new BookingCreatedEvent(FAKE_BOOKING_DETAILS)));

      verify(createBookingSagaManager).create(new CreateBookingSagaData(FAKE_BOOKING_ID, FAKE_BOOKING_DETAILS), Booking.class, FAKE_BOOKING_ID);
    }

    // TODO write tests for other methods

}

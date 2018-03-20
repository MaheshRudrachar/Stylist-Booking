package com.outfittery.booking.service.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import com.outfittery.booking.service.domain.BookingRepository;
import com.outfittery.booking.service.domain.BookingService;
import com.outfittery.booking.service.helper.BookingHelper;

import io.eventuate.javaclient.commonimpl.JSonMapper;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookingControllerTest {

    private BookingService bookingService;
    private BookingRepository bookingRepository;
    private BookingController bookingController;

    @Before
    public void setUp() throws Exception {
        bookingService = mock(BookingService.class);
        bookingRepository = mock(BookingRepository.class);
        bookingController = new BookingController(bookingService, bookingRepository);
    }


    @Test
    public void shouldFindBooking() {

      when(bookingRepository.findById(1L)).thenReturn(BookingHelper.FAKE_BOOKING);

      given().
              standaloneSetup(configureControllers(bookingController)).
      when().
              get("/v1/booking/1").
      then().
              statusCode(200).
              body("bookingId", equalTo(new Long(BookingHelper.FAKE_BOOKING_ID).intValue())).
              body("state", equalTo(BookingHelper.FAKE_BOOKING_STATE.name()))
      ;
    }

    @Test
    public void shouldFindNotBooking() {
      when(bookingRepository.findById(1L)).thenReturn(null);

      given().
              standaloneSetup(configureControllers(new BookingController(bookingService, bookingRepository))).
      when().
              get("/v1/booking/1").
      then().
              statusCode(404)
      ;
    }

    private StandaloneMockMvcBuilder configureControllers(Object... controllers) {
      
      MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(JSonMapper.objectMapper);
      return MockMvcBuilders.standaloneSetup(controllers).setMessageConverters(converter);
    }

  }
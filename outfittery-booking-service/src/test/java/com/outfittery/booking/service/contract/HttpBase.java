package com.outfittery.booking.service.contract;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import com.outfittery.booking.service.domain.BookingRepository;
import com.outfittery.booking.service.domain.BookingService;
import com.outfittery.booking.service.helper.BookingHelper;
import com.outfittery.booking.service.web.BookingController;

import io.eventuate.javaclient.commonimpl.JSonMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

public abstract class HttpBase {

  private StandaloneMockMvcBuilder controllers(Object... controllers) {
    
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(JSonMapper.objectMapper);
    return MockMvcBuilders.standaloneSetup(controllers).setMessageConverters(converter);
  }

  @Before
  public void setup() {
    BookingService bookingService = mock(BookingService.class);
    BookingRepository bookingRepository = mock(BookingRepository.class);
    BookingController bookingController = new BookingController(bookingService, bookingRepository);

    when(bookingRepository.findById(BookingHelper.FAKE_BOOKING_ID)).thenReturn(BookingHelper.FAKE_BOOKING);
    when(bookingRepository.findById(555L)).thenReturn(null);
    RestAssuredMockMvc.standaloneSetup(controllers(bookingController));

  }
}
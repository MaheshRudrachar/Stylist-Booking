package com.outfittery.booking.service.web;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import com.outfittery.booking.service.domain.BookingRepository;
import com.outfittery.booking.service.domain.BookingService;
import com.outfittery.booking.service.domain.StylistRepository;
import com.outfittery.booking.service.helper.BookingHelper;
import com.outfittery.booking.service.helper.StylistHelper;

import io.eventuate.javaclient.commonimpl.JSonMapper;

public class StylistControllerTest
{

    private BookingService bookingService;
    private StylistRepository stylistRepository;
    private StylistController stylistController;

    @Before
    public void setUp() throws Exception {
        bookingService = mock(BookingService.class);
        stylistRepository = mock(StylistRepository.class);
        stylistController = new StylistController(stylistRepository);
    }


    @Test
    public void shouldFindStylist() {

      when(stylistRepository.findOne(1L)).thenReturn(StylistHelper.FAKE_STYLIST_OBJECT);

      given().
              standaloneSetup(configureControllers(stylistController)).
      when().
              get("/v1/stylist/1").
      then().
              statusCode(200).
              body("stylistId", equalTo(new Long(StylistHelper.FAKE_STYLIST_ID).intValue())).
              body("state", equalTo(StylistHelper.FAKE_STYLIST_STATE.name()))
      ;
    }
    
    private StandaloneMockMvcBuilder configureControllers(Object... controllers) {
        
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(JSonMapper.objectMapper);
        return MockMvcBuilders.standaloneSetup(controllers).setMessageConverters(converter);
      }
}

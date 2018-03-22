package com.outfittery.booking.service.domain;


import org.springframework.beans.factory.annotation.Qualifier;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.jsonpath.JsonPath;
import com.outfittery.booking.service.BookingCommandHandlerConfig;
import com.outfittery.booking.service.domain.Booking;
import com.outfittery.booking.service.domain.BookingRepository;
import com.outfittery.booking.service.domain.BookingService;
import com.outfittery.booking.service.domain.StylistRepository;
import com.outfittery.booking.service.helper.BookingHelper;
import com.outfittery.booking.service.helper.StylistHelper;
import com.outfittery.booking.service.messaging.BookingServiceMessagingConfiguration;
import com.outfittery.booking.service.support.MessageConsumerTest;
import com.outfittery.booking.service.web.BookingWebConfiguration;
import com.outfittery.customer.service.api.CustomerServiceChannels;
import com.outfittery.customer.service.api.ValidateBookingByCustomer;
import com.outfittery.stylist.service.api.events.StylistCreated;
import com.outfittery.stylist.service.api.events.StylistState;

import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.CommandMessageHeaders;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import io.eventuate.tram.commands.producer.TramCommandProducerConfiguration;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.inmemory.TramInMemoryConfiguration;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.testutil.TestMessageConsumer;
import io.eventuate.tram.testutil.TestMessageConsumerFactory;
import io.eventuate.util.test.async.Eventually;

import java.util.Collections;
import java.util.function.Predicate;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookingServiceIntegrationTest.TestConfiguration.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingServiceIntegrationTest {


  public static final String STYLIST_ID = "1";
  @Value("${local.server.port}")
  private int port;

  private String baseUrl(String path) {
    return "http://localhost:" + port + path;
  }

  @Configuration
  @EnableAutoConfiguration
  @Import({BookingWebConfiguration.class, BookingServiceMessagingConfiguration.class,  BookingCommandHandlerConfig.class,
          TramCommandProducerConfiguration.class,
          TramInMemoryConfiguration.class})
  public static class TestConfiguration {

    @Bean
    public ChannelMapping channelMapping() {
      return new DefaultChannelMapping.DefaultChannelMappingBuilder().build();
    }

    @Bean
    public TestMessageConsumerFactory testMessageConsumerFactory() {
      return new TestMessageConsumerFactory();
    }


    @Bean
    public DataSource dataSource() {
      EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
      return builder.setType(EmbeddedDatabaseType.H2)
              .addScript("eventuate-tram-embedded-schema.sql")
              .addScript("eventuate-tram-sagas-embedded.sql")
              .build();
    }


    @Bean
    public MessageConsumerTest mockCustomerService() {
      return new MessageConsumerTest("mockCustomerService", CustomerServiceChannels.customerServiceChannel);
    }
  }

  @Autowired
  private DomainEventPublisher domainEventPublisher;

  @Autowired
  private StylistRepository stylistRepository;

  @Autowired
  private BookingService bookingService;

  @Autowired
  private BookingRepository bookingRepository;

  @Autowired
  @Qualifier("mockCustomerService")
  private MessageConsumerTest  mockCustomerService;

  @Test
  public void shouldCreateOrder() {
    domainEventPublisher.publish("com.outfittery.stylist.service.domain.Stylist", STYLIST_ID,
            Collections.singletonList(new StylistCreated("mahesh","rudrachar",StylistState.AVAILABLE,StylistHelper.fakeStylistShift())));

    Eventually.eventually(() -> {
      assertNotNull(stylistRepository.findById(Long.parseLong(STYLIST_ID)));
    });

    long customerId = 1511300065921L;

    Booking booking = bookingService.createBooking(customerId, Long.parseLong(STYLIST_ID), BookingHelper.fakeBookingSlot());

    assertNotNull(bookingRepository.findById(booking.getId()));

    String expectedPayload = "{\"customerId\":1511300065921,\"bookingId\":1}";

    Message message = mockCustomerService.assertMessageReceived(
            commandMessageOfType(ValidateBookingByCustomer.class.getName()).and(withPayload(expectedPayload)));

    System.out.println("message=" + message);

  }

  private Predicate<? super Message> withPayload(String expectedPayload) {
    return (m) -> expectedPayload.equals(m.getPayload());
  }

  private Predicate<Message> forCustomer(long customerId) {
    return (m) -> {
      Object doc = com.jayway.jsonpath.Configuration.defaultConfiguration().jsonProvider().parse(m.getPayload());
      Object s = JsonPath.read(doc, "$.customerId");
      return new Long(customerId).equals(s);
    };
  }

  private Predicate<Message> commandMessageOfType(String commandType) {
    return (m) -> m.getRequiredHeader(CommandMessageHeaders.COMMAND_TYPE).equals(commandType);
  }

}

package com.outfittery.booking.service.contract;

import java.util.Collections;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.outfittery.booking.service.api.events.BookingCreatedEvent;
import com.outfittery.booking.service.domain.BookingDomainEventPublisher;
import com.outfittery.booking.service.helper.BookingHelper;
import com.outfittery.booking.service.support.EventuateContractVerifierConfiguration;

import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.inmemory.TramInMemoryConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessagingBase.TestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMessageVerifier
public abstract class MessagingBase {

  @Configuration
  @EnableAutoConfiguration
  @Import({EventuateContractVerifierConfiguration.class, TramEventsPublisherConfiguration.class, TramInMemoryConfiguration.class})
  public static class TestConfiguration {

    @Bean
    public ChannelMapping channelMapping() {
      return new DefaultChannelMapping.DefaultChannelMappingBuilder().build();
    }

    @Bean
    public BookingDomainEventPublisher bookingAggregateEventPublisher(DomainEventPublisher eventPublisher) {
      return new BookingDomainEventPublisher(eventPublisher);
    }
  }


  @Autowired
  private BookingDomainEventPublisher bookingAggregateEventPublisher;

  protected void bookingCreated() {
    bookingAggregateEventPublisher.publish(BookingHelper.FAKE_BOOKING,
            Collections.singletonList(new BookingCreatedEvent(BookingHelper.FAKE_BOOKING_DETAILS)));
  }

}
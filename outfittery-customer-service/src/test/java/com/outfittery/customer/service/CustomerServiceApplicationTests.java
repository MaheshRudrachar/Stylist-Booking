package com.outfittery.customer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.outfittery.customer.service.api.ValidateBookingByCustomer;
import com.outfittery.customer.service.api.web.CreateCustomerRequest;
import com.outfittery.customer.service.web.CustomerWebConfiguration;

import io.eventuate.tram.commands.producer.CommandProducer;
import io.eventuate.tram.commands.producer.TramCommandProducerConfiguration;
import io.eventuate.tram.inmemory.TramInMemoryConfiguration;
import io.eventuate.tram.testutil.TestMessageConsumer;
import io.eventuate.tram.testutil.TestMessageConsumerFactory;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerServiceApplicationTests.TestConfiguration.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerServiceApplicationTests {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Value("${local.server.port}")
  private int port;

  @Configuration
  @Import({CustomerWebConfiguration.class,
          TramCommandProducerConfiguration.class,
          TramInMemoryConfiguration.class})
  public static class TestConfiguration {

    @Bean
    public TestMessageConsumerFactory testMessageConsumerFactory() {
      return new TestMessageConsumerFactory();
    }

  }

  private String baseUrl(String path) {
    return "http://localhost:" + port + path;
  }

  @Autowired
  private CommandProducer commandProducer;

  @Autowired
  private TestMessageConsumerFactory testMessageConsumerFactory;

  @Test
  public void shouldCreateCustomer() {

    String postUrl = baseUrl("/v1/customers");

    Integer customerId =
            given().
            body(new CreateCustomerRequest("mahesh","rudrachar")).
            contentType("application/json").
            when().
            post(postUrl).
            then().
            statusCode(200).
            extract().
            path("customerId");

    assertNotNull(customerId);


    TestMessageConsumer testMessageConsumer = testMessageConsumerFactory.make();

    long bookingId = 999;

    String messageId = commandProducer.send("customerService", null,
            new ValidateBookingByCustomer(customerId, bookingId),
            testMessageConsumer.getReplyChannel(), Collections.emptyMap());

    testMessageConsumer.assertHasReplyTo(messageId);

  }

}

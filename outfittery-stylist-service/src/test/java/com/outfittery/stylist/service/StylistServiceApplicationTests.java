package com.outfittery.stylist.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.outfittery.stylist.service.api.events.CreateStylistRequest;
import com.outfittery.stylist.service.api.events.CreateStylistStateRequest;
import com.outfittery.stylist.service.api.events.StylistState;
import com.outfittery.stylist.service.api.utils.TimeRange;
import com.outfittery.stylist.service.web.CreateStylistResponse;
import com.outfittery.stylist.service.web.CreateStylistStateResponse;
import com.outfittery.stylist.service.web.StylistWebConfiguration;

import io.eventuate.tram.commands.producer.CommandProducer;
import io.eventuate.tram.commands.producer.TramCommandProducerConfiguration;
import io.eventuate.tram.inmemory.TramInMemoryConfiguration;
import io.eventuate.tram.testutil.TestMessageConsumer;
import io.eventuate.tram.testutil.TestMessageConsumerFactory;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StylistServiceApplicationTests.TestConfiguration.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StylistServiceApplicationTests
{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Before
    public void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Value("${local.server.port}")
    private int port;

    @Configuration
    @Import({StylistTestConfiguration.class, StylistWebConfiguration.class,
        TramCommandProducerConfiguration.class,
        TramInMemoryConfiguration.class})
    public static class TestConfiguration
    {

    }


    private String baseUrl(String path)
    {
        return "http://localhost:" + port + path;
    }

    @Autowired
    RestTemplate restTemplate;


    @Test
    public void shouldCreateStylist() throws Exception
    {

        String postUrl = baseUrl("/v1/stylist");
        
        LocalTime start = LocalTime.now();
        LocalTime end = LocalTime.now().plusMinutes(30);
        TimeRange t1 = new TimeRange(DayOfWeek.TUESDAY, start, end);
        Set<TimeRange> ts1 = new TreeSet<TimeRange>();
        ts1.add(t1);

        CreateStylistRequest request = new CreateStylistRequest("mahesh", "rudrachar", "mahesh.rudrachar@xyz.com", ts1);

        ResponseEntity<CreateStylistResponse> createstylistResponse = restTemplate.postForEntity(baseUrl("/v1/stylist"), request, CreateStylistResponse.class);
        assertEquals(createstylistResponse.getStatusCode(), HttpStatus.OK);
        assertNotNull(createstylistResponse.getBody().getId());
        
        System.out.println("create stylist response ******"+ createstylistResponse.getBody().getId());
    }
    
    @Test
    public void shouldUpdateStylist() throws Exception
    {

        String postUrl = baseUrl("/v1/stylist");
        
        CreateStylistStateRequest request = new CreateStylistStateRequest(1L, StylistState.AVAILABLE);

        ResponseEntity<CreateStylistStateResponse> createstylistStateResponse = restTemplate.postForEntity(baseUrl("/v1/stylist"), request, CreateStylistStateResponse.class);
        assertEquals(createstylistStateResponse.getStatusCode(), HttpStatus.OK);
        assertNotNull(createstylistStateResponse.getBody().getId());
        
        System.out.println("create stylist response ******"+ createstylistStateResponse.getBody().getId());
    }

}

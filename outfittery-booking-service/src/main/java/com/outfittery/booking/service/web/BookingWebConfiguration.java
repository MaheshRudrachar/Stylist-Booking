package com.outfittery.booking.service.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outfittery.booking.service.domain.BookingServiceRepoConfiguration;

import io.eventuate.javaclient.commonimpl.JSonMapper;

@Configuration
@ComponentScan
@Import(BookingServiceRepoConfiguration.class)
public class BookingWebConfiguration
{

    @Bean
    @Primary
    public ObjectMapper objectMapper()
    {
        return JSonMapper.objectMapper;
    }
}

package com.outfittery.booking.service.domain;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.eventuate.javaclient.commonimpl.JSonMapper;

@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
public class BookingJpaTestConfiguration {
    @Bean
    @Primary
    public ObjectMapper objectMapper()
    {
        return JSonMapper.objectMapper.registerModule(new JavaTimeModule());

    }
}

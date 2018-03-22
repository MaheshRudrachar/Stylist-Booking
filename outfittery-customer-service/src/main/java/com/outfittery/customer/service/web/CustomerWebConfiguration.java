package com.outfittery.customer.service.web;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.outfittery.customer.service.domain.CustomerServiceDomainConfiguration;

import io.eventuate.javaclient.commonimpl.JSonMapper;

@Configuration
@ComponentScan
@Import(CustomerServiceDomainConfiguration.class)
public class CustomerWebConfiguration
{

    @Bean
    public HttpMessageConverters customConverters()
    {
        HttpMessageConverter<?> additional = new MappingJackson2HttpMessageConverter();
        return new HttpMessageConverters(additional);
    }
    
    @Bean
    @Primary
    public ObjectMapper objectMapper()
    {
        return JSonMapper.objectMapper.registerModule(new JavaTimeModule());

    }
}

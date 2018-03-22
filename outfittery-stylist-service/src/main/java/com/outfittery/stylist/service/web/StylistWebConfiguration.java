package com.outfittery.stylist.service.web;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.outfittery.stylist.service.domain.StylistServiceDomainConfiguration;

import io.eventuate.javaclient.commonimpl.JSonMapper;
import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;

@Configuration
@ComponentScan
@Import(StylistServiceDomainConfiguration.class)
public class StylistWebConfiguration
{

    @Bean
    @Primary
    public ObjectMapper objectMapper()
    {
        return JSonMapper.objectMapper.registerModule(new JavaTimeModule());

    }
    
    @Bean
    public HttpMessageConverters customConverters()
    {
        HttpMessageConverter<?> additional = new MappingJackson2HttpMessageConverter();
        return new HttpMessageConverters(additional);
    }
    
    @Bean
    public ChannelMapping channelMapping()
    {
        return new DefaultChannelMapping.DefaultChannelMappingBuilder().build();
    }
}

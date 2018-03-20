package com.outfittery.stylist.service;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@EnableAutoConfiguration
public class StylistTestConfiguration
{
    @Bean
    public RestTemplate restTemplate(HttpMessageConverters converters)
    {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler()
        {
            @Override
            protected boolean hasError(HttpStatus statusCode)
            {
                return statusCode != HttpStatus.NOT_FOUND && super.hasError(statusCode);
            }
        });
        List<? extends HttpMessageConverter<?>> httpMessageConverters = Collections.singletonList(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters((List<HttpMessageConverter<?>>) httpMessageConverters);
        return restTemplate;
    }
}

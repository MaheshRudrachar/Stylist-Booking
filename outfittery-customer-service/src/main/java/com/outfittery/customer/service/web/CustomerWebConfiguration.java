package com.outfittery.customer.service.web;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.outfittery.customer.service.domain.CustomerServiceDomainConfiguration;

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
}

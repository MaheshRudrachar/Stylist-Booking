package com.outfittery.stylist.service.domain;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;

@Configuration
@EnableJpaRepositories
@EntityScan
@EnableAutoConfiguration
@Import({TramEventsPublisherConfiguration.class})
@ComponentScan
public class StylistServiceDomainConfiguration
{

    @Bean
    public StylistService stylistService()
    {
        return new StylistService();
    }
    
    @Bean
    public ChannelMapping channelMapping()
    {
        return new DefaultChannelMapping.DefaultChannelMappingBuilder().build();
    }

}

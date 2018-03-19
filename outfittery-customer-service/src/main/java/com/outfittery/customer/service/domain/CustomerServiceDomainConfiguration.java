package com.outfittery.customer.service.domain;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaParticipantConfiguration;

@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
@Import({SagaParticipantConfiguration.class, TramEventsPublisherConfiguration.class})
@ComponentScan
public class CustomerServiceDomainConfiguration
{

    @Bean
    public CustomerService customerService()
    {
        return new CustomerService();
    }


    @Bean
    public ChannelMapping channelMapping()
    {
        return new DefaultChannelMapping.DefaultChannelMappingBuilder().build();
    }


    @Bean
    public CustomerServiceCommandHandlers customerServiceCommandHandlers()
    {
        return new CustomerServiceCommandHandlers();
    }


    @Bean
    public CommandDispatcher commandDispatcher(CustomerServiceCommandHandlers customerServiceCommandHandlers)
    {
        return new SagaCommandDispatcher("customerServiceDispatcher", customerServiceCommandHandlers.commandHandlers());
    }

}

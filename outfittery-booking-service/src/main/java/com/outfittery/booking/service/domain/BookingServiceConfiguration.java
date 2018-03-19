package com.outfittery.booking.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.outfittery.booking.service.sagaparticipants.BookingServiceProxy;
import com.outfittery.booking.service.sagaparticipants.CustomerServiceProxy;
import com.outfittery.booking.service.sagas.createbooking.CreateBookingSaga;
import com.outfittery.booking.service.sagas.createbooking.CreateBookingSagaData;

import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.sagas.orchestration.SagaCommandProducer;
import io.eventuate.tram.sagas.orchestration.SagaManager;
import io.eventuate.tram.sagas.orchestration.SagaManagerImpl;
import io.eventuate.tram.sagas.orchestration.SagaOrchestratorConfiguration;

@Configuration
@Import({TramEventsPublisherConfiguration.class, SagaOrchestratorConfiguration.class})
public class BookingServiceConfiguration
{

    @Bean
    public SagaCommandProducer sagaCommandProducer()
    {
        return new SagaCommandProducer();
    }


    @Bean
    public BookingService bookingService(StylistRepository stylistRepository, BookingRepository bookingRepository,
        DomainEventPublisher eventPublisher, SagaManager<CreateBookingSagaData> createBookingSagaManager,
        BookingDomainEventPublisher bookingAggregateEventPublisher)
    {
        return new BookingService(bookingRepository, eventPublisher, stylistRepository, createBookingSagaManager,
            bookingAggregateEventPublisher);
    }


    @Bean
    public SagaManager<CreateBookingSagaData> createBookingSagaManager(CreateBookingSaga saga)
    {
        return new SagaManagerImpl<>(saga);
    }


    @Bean
    public CreateBookingSaga createBookingSaga(BookingServiceProxy bookingService, CustomerServiceProxy customerService)
    {
        return new CreateBookingSaga(bookingService, customerService);
    }


    @Bean
    public BookingServiceProxy bookingServiceProxy()
    {
        return new BookingServiceProxy();
    }


    @Bean
    public CustomerServiceProxy customerServiceProxy()
    {
        return new CustomerServiceProxy();
    }


    @Bean
    public BookingDomainEventPublisher bookingAggregateEventPublisher(DomainEventPublisher eventPublisher)
    {
        return new BookingDomainEventPublisher(eventPublisher);
    }
}

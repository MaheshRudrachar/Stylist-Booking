package com.outfittery.customer.service.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import io.eventuate.tram.events.ResultWithEvents;
import io.eventuate.tram.events.publisher.DomainEventPublisher;

public class CustomerService
{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DomainEventPublisher domainEventPublisher;


    public void validateBookingForCustomer(long customerId, long bookingId)
    {
        Customer customer = customerRepository.findOne(customerId);
        if (customer == null)
            throw new CustomerNotFoundException();
        customer.validateBookingByConsumer();
    }


    public ResultWithEvents<Customer> create(String firstName, String lastName)
    {
        ResultWithEvents<Customer> rwe = Customer.create(firstName, lastName);
        customerRepository.save(rwe.result);
        domainEventPublisher.publish(Customer.class, rwe.result.getId(), rwe.events);
        return rwe;
    }

}

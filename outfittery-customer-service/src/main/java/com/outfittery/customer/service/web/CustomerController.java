package com.outfittery.customer.service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.outfittery.customer.service.api.web.CreateCustomerRequest;
import com.outfittery.customer.service.api.web.CreateCustomerResponse;
import com.outfittery.customer.service.domain.Customer;
import com.outfittery.customer.service.domain.CustomerService;

import io.eventuate.tram.events.ResultWithEvents;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController
{

    @Autowired
    private CustomerService customerService;


    @RequestMapping(method = RequestMethod.POST)
    public CreateCustomerResponse create(@RequestBody CreateCustomerRequest request)
    {
        ResultWithEvents<Customer> result = customerService.create(request.getFirstName(), request.getLastName());
        return new CreateCustomerResponse(result.result.getId());
    }
}

package com.outfittery.customer.service.api.web;

public class CreateCustomerResponse
{

    private long customerId;


    public long getCustomerId()
    {
        return customerId;
    }


    public void setCustomerId(long customerId)
    {
        this.customerId = customerId;
    }


    public CreateCustomerResponse()
    {

    }


    public CreateCustomerResponse(long customerId)
    {
        this.customerId = customerId;
    }
}

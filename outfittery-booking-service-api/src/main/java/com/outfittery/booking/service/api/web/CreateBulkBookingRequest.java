package com.outfittery.booking.service.api.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.outfittery.stylist.service.api.utils.TimeRange;

public class CreateBulkBookingRequest
{

    private List<CreateBookingRequest> bulkRequest = new ArrayList<CreateBookingRequest>();

    public List<CreateBookingRequest> getBulkRequest()
    {
        return bulkRequest;
    }

    public void setBulkRequest(List<CreateBookingRequest> bulkRequest)
    {
        this.bulkRequest = bulkRequest;
    }

    public CreateBulkBookingRequest(List<CreateBookingRequest> bulkRequest)
    {
        super();
        this.bulkRequest = bulkRequest;
    }
    

}

package com.outfittery.booking.service.api.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.outfittery.stylist.service.api.utils.TimeRange;

public class CreateBulkBookingRequest
{
    private List<CreateBookingRequest> bulkBookingRequest = new ArrayList<CreateBookingRequest>();

    public List<CreateBookingRequest> getBulkBookingRequest()
    {
        return bulkBookingRequest;
    }

    public void setBulkBookingRequest(List<CreateBookingRequest> bulkBookingRequest)
    {
        this.bulkBookingRequest = bulkBookingRequest;
    }

    public CreateBulkBookingRequest(List<CreateBookingRequest> bulkBookingRequest)
    {
        super();
        this.bulkBookingRequest = bulkBookingRequest;
    }
   
}

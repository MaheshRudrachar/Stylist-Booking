package com.outfittery.booking.service.web;

import java.util.TreeSet;

import com.outfittery.stylist.service.api.utils.TimeSpan;

public class GetAvailabilityResponse
{

    private TreeSet<TimeSpan> availability;


    public TreeSet<TimeSpan> getAvailability()
    {
        return availability;
    }


    public void setAvailability(TreeSet<TimeSpan> availability)
    {
        this.availability = availability;
    }


    public GetAvailabilityResponse(TreeSet<TimeSpan> availability)
    {
        super();
        this.availability = availability;
    }

}

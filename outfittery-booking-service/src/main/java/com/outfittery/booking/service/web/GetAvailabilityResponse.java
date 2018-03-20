package com.outfittery.booking.service.web;

import java.util.Set;
import java.util.TreeSet;

import com.outfittery.stylist.service.api.utils.TimeSpan;

public class GetAvailabilityResponse
{

    private Set<TimeSpan> availability = new TreeSet<TimeSpan>();


    public Set<TimeSpan> getAvailability()
    {
        return availability;
    }


    public void setAvailability(Set<TimeSpan> availability)
    {
        this.availability = availability;
    }


    public GetAvailabilityResponse(Set<TimeSpan> availability)
    {
        super();
        this.availability = availability;
    }

}

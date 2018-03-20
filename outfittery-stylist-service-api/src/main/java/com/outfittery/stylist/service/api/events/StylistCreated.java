package com.outfittery.stylist.service.api.events;

import java.util.Set;
import java.util.TreeSet;

import com.outfittery.stylist.service.api.utils.TimeRange;

import io.eventuate.tram.events.common.DomainEvent;

public class StylistCreated implements DomainEvent
{

    private String firstName;
    private String lastName;
    private StylistState state;
    private Set<TimeRange> stylistShift;


    public StylistCreated(String firstName, String lastName, StylistState state, Set<TimeRange> stylistShift)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
        this.stylistShift = stylistShift;
    }


    public StylistState getState()
    {
        return state;
    }


    public void setState(StylistState state)
    {
        this.state = state;
    }


    public Set<TimeRange> getStylistShift()
    {
        return stylistShift;
    }


    public void setStylistShift(Set<TimeRange> stylistShift)
    {
        this.stylistShift = stylistShift;
    }


    public String getFirstName()
    {
        return firstName;
    }


    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }


    public String getLastName()
    {
        return lastName;
    }


    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }


    private StylistCreated()
    {}

}

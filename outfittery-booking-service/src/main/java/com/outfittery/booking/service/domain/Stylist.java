package com.outfittery.booking.service.domain;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.outfittery.stylist.service.api.events.StylistState;
import com.outfittery.stylist.service.api.utils.TimeRange;

import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;

@Entity
@Table(name = "booking_service_stylist")
@Access(AccessType.FIELD)
public class Stylist
{

    @Id
    private long id;
    private String firstName;
    private String lastName;
    
    @ElementCollection
    private Set<TimeRange> stylistShift = new TreeSet<TimeRange>();
    
    private StylistState state;
    
    public Stylist() {
        
    }


    public Stylist(long id, Set<TimeRange> stylistShift)
    {
        this.id = id;
        this.stylistShift = stylistShift;
    }


    public Stylist(long id, String firstName, String lastName, Set<TimeRange> stylistShift, StylistState state)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stylistShift = stylistShift;
        this.state = state;
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


    public long getId()
    {
        return id;
    }


    public StylistState getState()
    {
        return state;
    }


    public Set<TimeRange> getStylistShift()
    {
        return stylistShift;
    }


    public void setState(StylistState state)
    {
        this.state = state;
    }

}

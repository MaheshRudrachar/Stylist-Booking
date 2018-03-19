package com.outfittery.booking.service.domain;

import java.util.Set;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.outfittery.stylist.service.api.events.StylistState;
import com.outfittery.stylist.service.api.utils.TimeRange;

import javax.persistence.AccessType;

@Entity
@Table(name = "booking_service_stylist")
@Access(AccessType.FIELD)
public class Stylist
{

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private Set<TimeRange> stylistShift;
    private StylistState state;


    public Stylist(Long id, Set<TimeRange> stylistShift)
    {
        this.id = id;
        this.stylistShift = stylistShift;
    }


    public Stylist(Long id, String firstName, String lastName, Set<TimeRange> stylistShift, StylistState state)
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


    public Long getId()
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

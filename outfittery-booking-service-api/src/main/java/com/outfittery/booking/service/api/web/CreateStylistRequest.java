package com.outfittery.booking.service.api.web;

import java.util.Set;
import java.util.TreeSet;

import com.outfittery.stylist.service.api.utils.TimeRange;

public class CreateStylistRequest
{
    private String firstName;
    private String lastName;
    private String email;
    private Set<TimeRange> stylistShift = new TreeSet<TimeRange>();
    
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Set<TimeRange> getStylistShift()
    {
        return stylistShift;
    }

    public void setStylistShift(Set<TimeRange> stylistShift)
    {
        this.stylistShift = stylistShift;
    }

    public CreateStylistRequest(String firstName, String lastName, String email, Set<TimeRange> stylistShift)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.stylistShift = stylistShift;
    }

}

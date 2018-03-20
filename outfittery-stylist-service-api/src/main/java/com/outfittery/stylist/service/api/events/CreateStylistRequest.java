package com.outfittery.stylist.service.api.events;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.outfittery.stylist.service.api.utils.TimeRange;

public class CreateStylistRequest
{

    private String firstName;


    public CreateStylistRequest(String firstName, String lastName, String email, Set<TimeRange> stylistShift)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.stylistShift = stylistShift;
    }

    private String lastName;
    private String email;
    private Set<TimeRange> stylistShift = new TreeSet<TimeRange>();
    private StylistState state;


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


    public StylistState getState()
    {
        return state;
    }


    public void setState(StylistState state)
    {
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


    private CreateStylistRequest()
    {

    }
}

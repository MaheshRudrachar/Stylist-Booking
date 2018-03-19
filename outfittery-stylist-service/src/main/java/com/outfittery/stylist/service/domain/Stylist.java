package com.outfittery.stylist.service.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.outfittery.stylist.service.api.events.StylistState;
import com.outfittery.stylist.service.api.utils.TimeRange;

import static com.outfittery.stylist.service.api.events.StylistState.ROOKIE;;

@Entity
@Table(name = "stylist")
@Access(AccessType.FIELD)
public class Stylist
{

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String avatarURL;
    private String phone;
    private Set<TimeRange> stylistShift;

    @Enumerated(EnumType.STRING)
    private StylistState state;


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


    public Stylist(String firstName, String lastName, String email, Set<TimeRange> stylistShift)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = "";
        this.avatarURL = "";
        this.phone = "";
        this.stylistShift = stylistShift;
        this.state = ROOKIE;
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


    public String getEmail()
    {
        return email;
    }


    public void setEmail(String email)
    {
        this.email = email;
    }


    public String getPassword()
    {
        return password;
    }


    public void setPassword(String password)
    {
        this.password = password;
    }


    public String getAvatarURL()
    {
        return avatarURL;
    }


    public void setAvatarURL(String avatarURL)
    {
        this.avatarURL = avatarURL;
    }


    public String getPhone()
    {
        return phone;
    }


    public void setPhone(String phone)
    {
        this.phone = phone;
    }


    public Long getId()
    {
        return id;
    }

}

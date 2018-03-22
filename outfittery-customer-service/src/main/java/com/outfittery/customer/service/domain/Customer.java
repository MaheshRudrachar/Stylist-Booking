package com.outfittery.customer.service.domain;

import javax.persistence.*;

import io.eventuate.tram.events.ResultWithEvents;

@Entity
@Table(name = "Customer")
@Access(AccessType.FIELD)
public class Customer
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String token;
    private String avatarURL;
    private String phone;
    private boolean active;


    private Customer()
    {

    }


    public Customer(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
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


    public String getToken()
    {
        return token;
    }


    public void setToken(String token)
    {
        this.token = token;
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


    public boolean isActive()
    {
        return active;
    }


    public void setActive(boolean active)
    {
        this.active = active;
    }


    public long getId()
    {
        return id;
    }


    public void validateBookingByConsumer()
    {
        // TODO: implement some business logic
    }


    public static ResultWithEvents<Customer> create(String firstName, String lastName)
    {
        return new ResultWithEvents<>(new Customer(firstName, lastName), new CustomerCreated());
    }

}

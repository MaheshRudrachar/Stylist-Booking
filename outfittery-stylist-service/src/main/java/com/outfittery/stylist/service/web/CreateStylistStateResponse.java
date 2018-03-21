package com.outfittery.stylist.service.web;

import java.util.Set;

import com.outfittery.stylist.service.api.events.StylistState;
import com.outfittery.stylist.service.api.utils.TimeRange;

import io.eventuate.tram.events.common.DomainEvent;

public class CreateStylistStateResponse implements DomainEvent
{
    private long id;


    public CreateStylistStateResponse()
    {}


    public long getId()
    {
        return id;
    }


    public void setId(long id)
    {
        this.id = id;
    }


    public CreateStylistStateResponse(long id)
    {
        this.id = id;
    }
}

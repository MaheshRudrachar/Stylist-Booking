package com.outfittery.stylist.service.api.events;

import java.util.Set;
import java.util.TreeSet;

import com.outfittery.stylist.service.api.utils.TimeRange;

public class CreateStylistStateRequest
{

    private long stylisId;
    private StylistState state;
    
    public CreateStylistStateRequest(long stylisId, StylistState state)
    {
        super();
        this.stylisId = stylisId;
        this.state = state;
    }
    
    public long getStylisId()
    {
        return stylisId;
    }

    public void setStylisId(long stylisId)
    {
        this.stylisId = stylisId;
    }

    public StylistState getState()
    {
        return state;
    }

    public void setState(StylistState state)
    {
        this.state = state;
    }
}

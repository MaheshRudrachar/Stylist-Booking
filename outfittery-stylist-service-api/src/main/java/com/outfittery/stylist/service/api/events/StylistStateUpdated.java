package com.outfittery.stylist.service.api.events;

import io.eventuate.tram.events.common.DomainEvent;

public class StylistStateUpdated implements DomainEvent
{
    private StylistState state;


    public StylistState getUpdatedState()
    {
        return state;
    }
}

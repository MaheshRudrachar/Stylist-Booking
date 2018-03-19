package com.outfittery.booking.service.domain;

public class UnsupportedStateTransitionException extends RuntimeException
{
    public UnsupportedStateTransitionException(Enum state)
    {
        super("current state: " + state);
    }
}

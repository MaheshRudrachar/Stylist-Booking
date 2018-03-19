package com.outfittery.booking.service.api.events;

public enum BookingState
{
    CREATE_PENDING,
    AUTHORIZED,
    REJECTED,
    CANCEL_PENDING,
    CANCELLED,
    REVISION_PENDING,
}

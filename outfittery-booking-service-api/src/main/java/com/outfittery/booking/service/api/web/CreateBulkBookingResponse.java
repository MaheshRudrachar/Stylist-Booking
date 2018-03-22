package com.outfittery.booking.service.api.web;

import java.util.ArrayList;
import java.util.List;

public class CreateBulkBookingResponse
{

    private List<Long> bookingIds = new ArrayList<Long>();

    public List<Long> getBookingIds()
    {
        return bookingIds;
    }

    public void setBookingIds(List<Long> bookingIds)
    {
        this.bookingIds = bookingIds;
    }

    public CreateBulkBookingResponse(List<Long> bookingIds)
    {
        super();
        this.bookingIds = bookingIds;
    }

}

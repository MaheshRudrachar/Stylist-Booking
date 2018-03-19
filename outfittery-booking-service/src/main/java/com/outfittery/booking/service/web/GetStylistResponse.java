package com.outfittery.booking.service.web;

public class GetStylistResponse
{

    private long stylistId;


    private GetStylistResponse()
    {

    }


    public long getStylistId()
    {
        return stylistId;
    }


    public void setStylistId(long stylistId)
    {
        this.stylistId = stylistId;
    }


    public GetStylistResponse(long stylistId)
    {
        this.stylistId = stylistId;
    }

}

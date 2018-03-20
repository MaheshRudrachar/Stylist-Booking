package com.outfittery.booking.service.web;

import com.outfittery.booking.service.domain.Stylist;

public class GetStylistStateResponse
{

    private Stylist stylist;


    private GetStylistStateResponse()
    {

    }


    public Stylist getStylist()
    {
        return stylist;
    }


    public void setStylist(Stylist stylist)
    {
        this.stylist = stylist;
    }


    public GetStylistStateResponse(Stylist stylist)
    {
        this.stylist = stylist;
    }

}

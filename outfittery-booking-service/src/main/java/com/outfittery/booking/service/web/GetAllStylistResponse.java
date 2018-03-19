package com.outfittery.booking.service.web;

import java.util.HashMap;
import java.util.Map;

public class GetAllStylistResponse
{

    HashMap<Long, String> stylists;


    public HashMap<Long, String> getStylists()
    {
        return stylists;
    }


    public void setStylists(HashMap<Long, String> stylists)
    {
        this.stylists = stylists;
    }


    public GetAllStylistResponse(Map<Long, String> stylistName)
    {
        super();
        this.stylists = (HashMap<Long, String>) stylistName;
    }
}

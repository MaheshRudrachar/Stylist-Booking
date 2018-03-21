package com.outfittery.stylist.service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.outfittery.stylist.service.api.events.CreateStylistRequest;
import com.outfittery.stylist.service.api.events.CreateStylistStateRequest;
import com.outfittery.stylist.service.domain.Stylist;
import com.outfittery.stylist.service.domain.StylistService;

@RestController
@RequestMapping(path = "/v1/stylist")
public class StylistController
{

    @Autowired
    private StylistService stylistService;


    @RequestMapping(method = RequestMethod.POST)
    public CreateStylistResponse create(@RequestBody CreateStylistRequest request)
    {
        Stylist stylist = stylistService.create(request);
        return new CreateStylistResponse(stylist.getId());
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public CreateStylistStateResponse update(@RequestBody CreateStylistStateRequest request)
    {
        Stylist stylist = stylistService.update(request);
        return new CreateStylistStateResponse(stylist.getId());
    }
}

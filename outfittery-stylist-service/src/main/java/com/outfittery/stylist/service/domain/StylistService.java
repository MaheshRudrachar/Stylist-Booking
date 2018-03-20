package com.outfittery.stylist.service.domain;

import java.util.Collections;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.outfittery.stylist.service.api.events.CreateStylistRequest;
import com.outfittery.stylist.service.api.events.StylistCreated;
import com.outfittery.stylist.service.api.utils.TimeRange;

import io.eventuate.tram.events.publisher.DomainEventPublisher;

@Transactional
public class StylistService
{

    @Autowired
    private StylistRepository stylistRepository;

    @Autowired
    private DomainEventPublisher domainEventPublisher;


    public Stylist create(CreateStylistRequest request)
    {
        Stylist stylist = new Stylist(request.getFirstName(), request.getLastName(), request.getEmail(),
            request.getStylistShift());
        stylistRepository.save(stylist);
        domainEventPublisher.publish(Stylist.class, stylist.getId(),
            Collections.singletonList(new StylistCreated(request.getFirstName(), request.getLastName(), request.getState(), request.getStylistShift())));
        return stylist;
    }
}

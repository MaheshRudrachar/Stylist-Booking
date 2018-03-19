package com.outfittery.booking.service.web;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.outfittery.booking.service.domain.Booking;
import com.outfittery.booking.service.domain.BookingRepository;
import com.outfittery.booking.service.domain.Stylist;
import com.outfittery.booking.service.domain.StylistRepository;
import com.outfittery.stylist.service.api.events.StylistState;
import com.outfittery.stylist.service.api.utils.TimeRange;
import com.outfittery.stylist.service.api.utils.TimeSpan;

@RestController
@RequestMapping(path = "/stylist")
public class StylistController
{

    @Autowired
    private StylistRepository stylistRepository;


    @RequestMapping(path = "/{stylistId}", method = RequestMethod.GET)
    public ResponseEntity<GetStylistResponse> getStylist(@PathVariable long stylistId)
    {
        Stylist stylist = stylistRepository.findOne(stylistId);
        if (stylist == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(new GetStylistResponse(stylistId), HttpStatus.OK);

    }


    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public ResponseEntity<GetAllStylistResponse> getStylists()
    {
        Iterable<Stylist> stylists = stylistRepository.findByStylistStateReturnList(StylistState.AVAILABLE);

        Map<Long, String> stylistCollection = new HashMap<Long, String>();

        if (stylists == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        for (Stylist stylist : stylists)
        {
            stylistCollection.put(stylist.getId(), stylist.getFirstName() + " " + stylist.getLastName());
        }

        return new ResponseEntity<>(new GetAllStylistResponse(stylistCollection), HttpStatus.OK);
    }
}

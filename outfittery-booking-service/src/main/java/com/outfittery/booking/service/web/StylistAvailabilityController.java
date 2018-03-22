package com.outfittery.booking.service.web;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
import com.outfittery.stylist.service.api.utils.TimeRange;
import com.outfittery.stylist.service.api.utils.TimeSpan;

@RestController
@RequestMapping(path = "/v1/availability")
public class StylistAvailabilityController
{

    @Autowired
    private StylistRepository stylistRepository;

    @Autowired
    private BookingRepository bookingRepository;

    private Set<TimeSpan> shifts;
    private Set<TimeSpan> bookedTimes;


    @RequestMapping(path = "/{stylistId}/{date}", method = RequestMethod.GET)
    public ResponseEntity<GetAvailabilityResponse> getStylistAvailability(@PathVariable long stylistId, @PathVariable LocalDate date)
    {
        Stylist stylist = stylistRepository.findById(stylistId);
        Booking booking = bookingRepository.findByStylistId(stylistId);

        if (stylist == null || booking == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
        {
            shifts = new TreeSet<TimeSpan>();
            bookedTimes = new TreeSet<TimeSpan>();

            for (TimeRange styleShift : stylist.getStylistShift())
            {
                if (styleShift.getDay().compareTo(date.getDayOfWeek()) == 0)
                    addShift(styleShift);
            }

            for (TimeRange bookingSlot : booking.getBookingSlot())
            {
                if (bookingSlot.getDate().compareTo(date) == 0)
                    addBooking(bookingSlot);
            }

            Set<TimeSpan> availability = getAvailability();

            return new ResponseEntity<>(new GetAvailabilityResponse(availability), HttpStatus.OK);

        }

    }


    public void addShift(TimeRange shift)
    {
        shifts.add(new TimeSpan(shift.getStart(), shift.getEnd()));
    }


    public void addAllShifts(List<TimeRange> shifts)
    {
        for (TimeRange shift : shifts)
        {
            addShift(shift);
        }
    }


    public void addBooking(TimeRange bookingSlot)
    {
        bookedTimes.add(new TimeSpan(bookingSlot.getStart(), bookingSlot.getEnd()));
    }


    public void addAllBookings(List<TimeRange> bookingSlots)
    {
        for (TimeRange bookingSlot : bookingSlots)
        {
            addBooking(bookingSlot);
        }
    }


    public Set<TimeSpan> getAvailability()
    {
        Set<TimeSpan> availability = new TreeSet<TimeSpan>();

        // iterate over shifts
        for (TimeSpan shift : shifts)
        {
            // availability.add(new TimeSpan(shift.getStart(), shift.getEnd()));

            LocalTime currentTime = shift.start;

            // iterate over booked times
            for (TimeSpan booking : bookedTimes)
            {
                // if the booking takes place during this shift, block out this
                // time
                if (overlap(shift, booking))
                {
                    availability.add(new TimeSpan(currentTime, booking.start));
                    currentTime = booking.end;
                }
            }

            // include any time between last booking & shift end
            if (currentTime.isBefore(shift.end))
            {
                availability.add(new TimeSpan(currentTime, shift.end));
            }
        }

        return availability;
    }


    /**
     * Checks for overlapping availability
     */
    private boolean overlap(TimeSpan t1, TimeSpan t2)
    {
        if (t2.start.isAfter(t1.start) || t2.start.equals(t1.start))
        {
            if (t2.end.isBefore(t1.end) || t2.end.equals(t1.end))
            {
                return true;
            }
        }
        return false;
    }

}

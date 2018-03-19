package com.outfittery.booking.service.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.outfittery.booking.service.api.web.CreateBookingRequest;
import com.outfittery.booking.service.api.web.CreateBookingResponse;
import com.outfittery.booking.service.domain.Booking;
import com.outfittery.booking.service.domain.BookingRepository;
import com.outfittery.booking.service.domain.BookingService;

@RestController
@RequestMapping(path = "/booking")
public class BookingController
{

    private BookingService bookingService;

    private BookingRepository bookingRepository;


    public BookingController(BookingService bookingService, BookingRepository bookingRespository)
    {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
    }


    @RequestMapping(method = RequestMethod.POST)
    public CreateBookingResponse create(@RequestBody CreateBookingRequest request)
    {
        Booking booking = bookingService.createBooking(request.getCustomerId(), request.getStylistId(), request.getBookingSlot());

        return new CreateBookingResponse(booking.getId());
    }


    @RequestMapping(path = "/{bookingId}", method = RequestMethod.GET)
    public ResponseEntity<GetBookingResponse> getBooking(@PathVariable long bookngId)
    {
        Booking booking = bookingRepository.findOne(bookngId);
        if (booking == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(makeGetBookingResponse(booking), HttpStatus.OK);
    }


    private GetBookingResponse makeGetBookingResponse(Booking booking)
    {
        return new GetBookingResponse(booking.getId(), booking.getState().name(), booking.getBookingSlot());
    }

}

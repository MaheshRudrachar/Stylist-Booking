package com.outfittery.booking.service.sagas.createbooking;

import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.outfittery.booking.service.sagaparticipants.ApproveBookingCommand;
import com.outfittery.booking.service.sagaparticipants.BookingServiceProxy;
import com.outfittery.booking.service.sagaparticipants.CustomerServiceProxy;
import com.outfittery.customer.service.api.ValidateBookingByCustomer;

public class CreateBookingSaga implements SimpleSaga<CreateBookingSagaData>
{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private SagaDefinition<CreateBookingSagaData> sagaDefinition;


    public CreateBookingSaga(BookingServiceProxy bookingService, CustomerServiceProxy customerService)
    {
        this.sagaDefinition =

            step()
                .invokeParticipant(customerService.validateBooking, this::makeValidateBookingByCustomer)

                .step()
                .invokeParticipant(bookingService.approve, this::makeApproveBookingCommand)
                .build();

    }


    @Override
    public SagaDefinition<CreateBookingSagaData> getSagaDefinition()
    {
        return sagaDefinition;
    }


    private ValidateBookingByCustomer makeValidateBookingByCustomer(CreateBookingSagaData data)
    {
        return new ValidateBookingByCustomer(data.getBookingDetails().getCustomerId(), data.getBookingId());
    }


    private ApproveBookingCommand makeApproveBookingCommand(CreateBookingSagaData data)
    {
        return new ApproveBookingCommand(data.getBookingId());
    }
}

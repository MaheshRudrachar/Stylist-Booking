package com.outfittery.booking.service.messaging;

import org.junit.Before;
import org.junit.Test;

import com.outfittery.booking.service.domain.BookingService;
import com.outfittery.booking.service.helper.StylistHelper;
import com.outfittery.stylist.service.api.events.StylistCreated;
import com.outfittery.stylist.service.api.events.StylistState;
import com.outfittery.stylist.service.api.utils.TimeRange;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static com.outfittery.booking.service.messaging.MockTramMessageTestSupport.*;

import java.util.Set;

import static com.outfittery.booking.service.helper.StylistHelper.FAKE_STYLIST_ID;
import static com.outfittery.booking.service.helper.StylistHelper.FAKE_FIRST_NAME;
import static com.outfittery.booking.service.helper.StylistHelper.FAKE_LAST_NAME;
import static com.outfittery.booking.service.helper.StylistHelper.FAKE_STYLIST_STATE;;

public class BookingEventConsumerTest
{

    private BookingService bookingService;
    private BookingEventConsumer bookingEventConsumer;


    @Before
    public void setUp() throws Exception
    {
        bookingService = mock(BookingService.class);
        bookingEventConsumer = new BookingEventConsumer(bookingService);
    }


    @Test
    public void shouldCreateStylist()
    {

        given().

            eventHandlers(bookingEventConsumer.domainEventHandlers()).when().aggregate("com.outfittery.stylist.service.domain.Stylist", FAKE_STYLIST_ID)
            .publishes(new StylistCreated(FAKE_FIRST_NAME, FAKE_LAST_NAME, FAKE_STYLIST_STATE, StylistHelper.fakeStylistShift())).then().verify(() -> {
                verify(bookingService).createStylist(FAKE_STYLIST_ID, FAKE_FIRST_NAME, FAKE_LAST_NAME, FAKE_STYLIST_STATE, StylistHelper.fakeStylistShift());
            });

    }
}

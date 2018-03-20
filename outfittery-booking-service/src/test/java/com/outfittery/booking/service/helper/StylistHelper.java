package com.outfittery.booking.service.helper;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import com.outfittery.booking.service.domain.Stylist;
import com.outfittery.stylist.service.api.events.StylistState;
import com.outfittery.stylist.service.api.utils.TimeRange;

public class StylistHelper
{

    public static final String FAKE_STYLIST_NAME = "Fake";
    public static final long FAKE_STYLIST_ID = 1L;
    public static final String FAKE_FIRST_NAME = "Fake_First_Name";
    public static final String FAKE_LAST_NAME = "Fake_Last_Name";
    public static final StylistState FAKE_STYLIST_STATE = StylistState.AVAILABLE;

    public static Set<TimeRange> fakeStylistShift()
    {
        LocalTime start = LocalTime.now();
        LocalTime end = LocalTime.now();
        TimeRange t1 = new TimeRange(DayOfWeek.TUESDAY, start, end);

        Set<TimeRange> ts1 = new TreeSet<TimeRange>();
        ts1.add(t1);

        return ts1;
    }
    
    public static final Stylist FAKE_STYLIST_OBJECT =
        new Stylist(FAKE_STYLIST_ID, FAKE_FIRST_NAME, FAKE_LAST_NAME, fakeStylistShift(), FAKE_STYLIST_STATE);

}

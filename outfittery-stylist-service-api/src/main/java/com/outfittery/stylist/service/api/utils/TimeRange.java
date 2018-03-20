package com.outfittery.stylist.service.api.utils;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable
@Access(AccessType.FIELD)
public class TimeRange implements Comparable<TimeRange>
{

    private DayOfWeek day;
    private LocalTime start;
    private LocalTime end;

    public TimeRange() {
        
    }

    public TimeRange(DayOfWeek day, LocalTime start, LocalTime end)
    {
        this.day = day;
        this.start = start;
        this.end = end;
    }


    public DayOfWeek getDay()
    {
        return day;
    }


    public LocalTime getStart()
    {
        return start;
    }


    public LocalTime getEnd()
    {
        return end;
    }


    /**
     * Return the duration of the shift
     *
     */
    public Duration getDuration()
    {
        return Duration.between(start, end);
    }


    public void setDay(DayOfWeek day)
    {
        this.day = day;
    }


    public void setStart(LocalTime time)
    {
        this.start = time;
    }


    public void setEnd(LocalTime time)
    {
        this.end = time;
    }


    /**
     * Helper to format data types to Strings for processing
     *
     */
    @Override
    public String toString()
    {
        return String.format("Day: %s, Time: %s to %s (%s)", day.toString(), start.toString(), end.toString(),
            getDuration().toString());
    }


    public int compareTo(TimeRange s)
    {
        int byDay = day.compareTo(s.day);

        if (byDay == 0)
        {
            if (start.isBefore(s.start))
            {
                return -1;
            }
            else if (start.equals(s.start))
            {
                if (end.isBefore(s.end))
                {
                    return -1;
                }
                else if (end.equals(s.end))
                {
                    return 0;
                }
                else if (end.isAfter(s.end))
                {
                    return 1;
                }
            }
        }

        return byDay;
    }
}

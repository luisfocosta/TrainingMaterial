/*
Copyright 2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.datetime;

import java.util.Calendar;
import java.util.Date;

/**
Comparison of the use of Calendar/Date and LocalDate/Time APIs.

@author Will Provost
*/
public class DateTime
{
    /**
    We carry out a few common date-management and date-arithmetic tasks,
    using the Calendar and Date classes from java.util.
    */
    public static void useJavaUtilDate()
    {
        Date now = new Date ();
        System.out.println ("  Now: " + now);

        Calendar calendar = Calendar.getInstance ();

        // March 7, 2015 (not, say, February 7, 2015):
        calendar.set (2015, 2, 7);

        // To get the date, naturally, we call getTime()!
        System.out.println ("  Specific date: " + calendar.getTime ());

        Calendar calendar2 = Calendar.getInstance ();
        calendar.set (2015, 2, 7);
        System.out.println ("  Specific date: " + calendar.getTime ());

        // Surprise here ... same date, yes, but that's not sufficient:
        System.out.println ("  Same date? " + calendar.equals (calendar2));

        // What we should have done:
        calendar.setTimeInMillis (0);
        calendar2.setTimeInMillis (0);
        calendar.set (2015, 2, 7);
        calendar2.set (2015, 2, 7);
        System.out.println ("  Same date? " + calendar.equals (calendar2));

        // Move one week into the future and then
        // to the same or following Monday:
        calendar.add (Calendar.WEEK_OF_YEAR, 1);
        while (calendar.get (Calendar.DAY_OF_WEEK) != Calendar.MONDAY)
            calendar.add (Calendar.DAY_OF_YEAR, 1);
        System.out.println ("  Future date: " + calendar.getTime ());

        // Is next month on the same day also a Monday?
        calendar.add (Calendar.MONTH, 1);
        System.out.println ("  Next month a Monday? " +
            (calendar.get (Calendar.DAY_OF_WEEK) == Calendar.MONDAY));

        // This time on that day:
        Calendar nowAsCalendar = Calendar.getInstance ();
        calendar.set (Calendar.HOUR_OF_DAY,
            nowAsCalendar.get (Calendar.HOUR_OF_DAY));
        calendar.set (Calendar.MINUTE, nowAsCalendar.get(Calendar.MINUTE));
        calendar.set (Calendar.SECOND, 0);
        calendar.set (Calendar.MILLISECOND, 0);
        System.out.println ("  This time on that day: " + calendar.getTime ());
    }

    /**
    Call each of the two methods
    {@link #useJavaUtilDateuseJavaUtilDate } and
    {@link #useLocalDateTime useLocalDateTime}.
    */
    public static void main (String[] args)
    {
        System.out.println ("Using java.util.Date and java.util.Calendar:");
        useJavaUtilDate ();
        System.out.println ();
    }
}

/*
Copyright 2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.rental;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
Rental rate and grace-period computations.

@author Will Provost
*/
public class Rental
{
    private static final int WEEKLY_RATE = 200;
    private static final int DAILY_RATE = 45;
    private static final int HOURLY_RATE = 5;
    
    private static final Duration GRACE_PERIOD = Duration.ofHours (3);
    
    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern ("MMMM dd, yyyy 'at' hh:mm a");
    
    /**
    Calculates the rental cost for the given rental and return dates and times.
    Optimizes the cost by overriding many days with one week if the rate
    is better; does the same for hours vs. a day. Also computes the start
    and end times of a grace period for returning the rented item; outside
    of that window, the renter will pay a fixed fee for returning early/late.
    Produces a report to the console of all calculations.
    */
    public static void rent (String startDate, String startTime, 
        String endDate, String endTime)
    {
        LocalDateTime start = LocalDateTime.of
            (LocalDate.parse (startDate), LocalTime.parse (startTime));
        LocalDateTime end = LocalDateTime.of
            (LocalDate.parse (endDate), LocalTime.parse (endTime));
        
        if (!start.isBefore(end))
        {
            System.out.println 
                ("Rental and return times are not in chronological order.");
            return;
        }
        
        long weeks = ChronoUnit.WEEKS.between (start, end);
        LocalDateTime afterWeeks = start.plusWeeks (weeks);
        long days = ChronoUnit.DAYS.between (afterWeeks, end);
        LocalDateTime afterDays = afterWeeks.plusDays (days);
        long hours = ChronoUnit.HOURS.between(afterDays, end);
        LocalDateTime afterHours = afterDays.plusHours (hours);
        if (afterHours.isBefore(end))
            ++hours;
        
        int costForHours = (int) hours * HOURLY_RATE;
        if (costForHours > DAILY_RATE)
        {
            costForHours = 0;
            ++days;
        }

        int costForDays = (int) days * DAILY_RATE;
        if (costForDays + costForHours > WEEKLY_RATE)
        {
            costForDays = costForHours = 0;
            ++weeks;
        }

        int costForWeeks = (int) weeks * WEEKLY_RATE;
        
        int cost = costForHours + costForDays + costForWeeks;
        
        System.out.println ("Rental from " + start.format (FORMATTER) +
            " to " + end.format (FORMATTER) + ":");
        System.out.println ();
        
        if (costForWeeks != 0)
            System.out.format ("  %3d weeks at $%3d/week -- $%3d%n",
                weeks, WEEKLY_RATE, costForWeeks);
        if (costForDays != 0)
            System.out.format ("  %3d days  at $%3d/day  -- $%3d%n",
                days, DAILY_RATE, costForDays);
        if (costForHours != 0)
            System.out.format ("  %3d hours at $%3d/hour -- $%3d%n",
                hours, HOURLY_RATE, costForHours);
        
        System.out.println ("                             ---");
        System.out.format ("                      Total $%3d%n", cost);
        System.out.println ();
        
        System.out.println ("  A fee of $50 is owed if returned");
        System.out.println ("    before " + 
            FORMATTER.format (end.minus (GRACE_PERIOD)) + " or");
        System.out.println ("    after " + 
            FORMATTER.format (end.plus (GRACE_PERIOD)) + ".");
        System.out.println ();
    }
    
    /**
    Calls the {@link #rent rent} method, either with date and time values
    as supplied on the command line or in a sequence of test cases.
    */
    public static void main (String[] args)
    {
        if (args.length > 3)
            rent (args[0], args[1], args[2], args[3]);
        else if (args.length != 0)
            System.out.println ("Please provide rental date and time " +
                "and return date and time.");
        else
        {
            // Test cases, with expected results shown in comment at right:
            rent ("2015-01-22", "10:00:00", "2015-01-25", "15:30:00"); // $165
            rent ("2015-01-22", "09:00:00", "2015-01-25", "18:30:00"); // $180
            rent ("2015-01-22", "10:00:00", "2015-01-31", "15:30:00"); // $320
            rent ("2015-01-22", "10:00:00", "2015-02-03", "15:30:00"); // $400
            rent ("2015-01-22", "10:00:00", "2015-02-02", "15:30:00"); // $400
            rent ("2015-01-22", "10:00:00", "2015-01-22", "22:30:00"); //  $45
        }
    }
}

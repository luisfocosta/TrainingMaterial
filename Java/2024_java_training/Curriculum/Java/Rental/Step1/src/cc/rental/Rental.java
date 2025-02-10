/*
Copyright 2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.rental;

import java.time.Duration;

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

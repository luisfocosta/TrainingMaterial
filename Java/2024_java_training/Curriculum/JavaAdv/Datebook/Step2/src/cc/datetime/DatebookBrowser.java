/*
Copyright 2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import cc.tools.Browser;

/**
Lets the user browse the schedule one day at a time.

@author Will Provost
*/
public class DatebookBrowser
{
    private final DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern ("EEEE, MMMM dd, yyyy");

    private LocalDate currentDate;
    private Datebook datebook = new Datebook ();
    
    /**
    Stores our reference to the datebook, and sets current date to
    the first date in the book.
    */
    public DatebookBrowser (Datebook datebook)
    {
        this.datebook = datebook;
        currentDate = Collections.min (datebook.keySet());
    }
    
    /**
    Shows all events for the current date.
    */
    public void show ()
    {
        
        List<Event> events = datebook.get (currentDate);

        for (Event event : events)
            System.out.format ("    %s  %s%n",
                event.getWhen ().toLocalTime ().toString(), event.getWhat());
        System.out.println ();
    }
    
    /**
    Uses the {@link Browser} to give the user control over browsing by page,
    where each distinct date in the book is one page.
    */
    public void browse ()
    {
        Browser browser = new Browser 
            (
                () -> { currentDate = datebook.ceilingKey (currentDate.plusDays (1)); }, 
                () -> { currentDate = datebook.floorKey (currentDate.plusDays (-1)); }, 
                () -> { currentDate = Collections.min (datebook.keySet ()); }, 
                () -> { currentDate = Collections.max (datebook.keySet ()); }, 
                this::show, 
                () -> currentDate.format (formatter)
            );
        browser.browse (datebook.keySet ().size ());
    }
    
    /**
    {@link Persistence#loadDatebook Load the datebook},
    {@link Datebook#buildSchedule sort it} into a map of dates and lists 
    of events, and then give the user a terminal interface to browse the 
    schedule one day at a time.
    */
    public static void main (String[] args)
    {
        Datebook datebook = Datebook.buildSchedule 
          (Persistence.loadDatebook ().stream ());
        
        if (datebook == null || datebook.size () == 0)
        {
            System.out.println ("No data to show.");
            System.exit (0);
        }
        
        DatebookBrowser browser = new DatebookBrowser (datebook);
        browser.browse ();
    }
}

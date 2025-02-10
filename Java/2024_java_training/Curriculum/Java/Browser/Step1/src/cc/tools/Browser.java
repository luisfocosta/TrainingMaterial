/*
Copyright 2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Supplier;

/**
General-purpose command-line browser that allows the user to work
page-by-page through some set of information. Actual data production
and navigation are supplied by the client code; this class just manages
the user interactions and uses callbacks to notify the client application
when the user enters navigation commands.

@author Will Provost
*/
public class Browser
{
    private Runnable next;
    private Runnable previous;
    private Runnable first;
    private Runnable last;
    private Runnable show;
    private Supplier<String> getLabel;

    /**
    Provide callback implementations for navigation commands (next, previous,
    first, last) and for showing a page and providing a label or heading
    for the page.
    */
    public Browser (Runnable next, Runnable previous, Runnable first,
        Runnable last, Runnable show, Supplier<String> getLabel)
    {
        this.next = next;
        this.previous = previous;
        this.first = first;
        this.last = last;
        this.show = show;
        this.getLabel = getLabel;
    }

    /**
    Initiate user-driven browsing of the data.
    With each valid user command, we call the appropriate command handler,
    and then the callbacks for the label and page data.
    */
    public void browse (int pages)
    {
        final BufferedReader reader = new BufferedReader
            (new InputStreamReader (System.in));

        int page = 1;
        boolean running = true;
        while (running)
        {
            System.out.println ("Page " + page + " of " + pages + ": " +
                getLabel.get ());
            System.out.println ();
            show.run ();

            boolean processed = false;
            while (!processed)
            {
                System.out.println ("Enter \"next\", \"prev\", or \"quit\".");
                String command = null;
                try
                {
                    command = reader.readLine ();
                }
                catch (IOException ex)
                {
                    command = "EXCEPTION: " + ex.getClass().getName();
                }

                switch (command.toLowerCase ())
                {
                case "next":
                    if (page < pages)
                    {
                        next.run ();
                        ++page;
                        processed = true;
                    }
                    else
                        System.out.println ("This is the final page.");
                    
                    break;
                    
                case "prev":
                    if (page > 1)
                    {
                        previous.run ();
                        --page;
                        processed = true;
                    }
                    else
                        System.out.println ("This is the first page.");
                    
                    break;
                    
                case "first":
                    first.run ();
                    page = 1;
                    processed = true;
                    
                    break;
                    
                case "last":
                    last.run ();
                    page = pages;
                    processed = true;
                    break;
                
                case "quit":
                    running = false;
                    processed = true;
                    break;
                    
                default:
                    System.out.println ("Couldn't understand your command.");
                }
            }
        }
    }
}

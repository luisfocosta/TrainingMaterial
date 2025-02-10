/*
Copyright 2005-2015 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.threads;

import java.util.concurrent.atomic.AtomicBoolean;

/**
This application class exercises the {@link Members} class by running
various threads that make repeated calls to its methods.

@author Will Provost
*/
public class Lookup
{
    private static Members membership;
    private static AtomicBoolean running = new AtomicBoolean (true);
    
    /**
    Repeatedly calls {@link Members#updateMembership updateMembership}.
    */
    public static void updater ()
    {
        while (running.get ())
            membership.updateMembership ();
    }
    
    /**
    Repeatedly calls {@link Members#findID findID}.
    */
    public static void lookerUpper ()
    {
        while (running.get ())
            membership.findID (1100);
    }

    /**
    Runs one updating thread and five searching threads.
    Sleeps the main thread for a given amount of time, and then shuts down 
    all the worker threads and shows counts of actions taken by them.
    */
    public static void main (String[] args)
    {
        System.out.println ("Running ...");
        
        membership = new Members ();
        new Thread (Lookup::updater).start ();
        for (int t = 0; t < 5; ++t)
            new Thread (Lookup::lookerUpper).start ();
        
        int time = 1000 * (args.length != 0 ? Integer.parseInt (args[0]) : 10);
        try
        {
            Thread.sleep (time);
        }
        catch (InterruptedException ex) {}
        
        running.set (false);
        
        System.out.format ("Lookups: %12d%n", membership.lookups);
        System.out.format ("Adds:    %12d%n", membership.adds);
        System.out.format ("Drops:   %12d%n", membership.removes);
    }
}

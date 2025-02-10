/*
Copyright 2005-2015 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.threads;

/**
This application class exercises the {@link Members} class by running
various threads that make repeated calls to its methods.

@author Will Provost
*/
public class Lookup
{
    private static Members membership;

    /**
    Runs two threads, one updating the membership rolls and one searching
    them a given number of times.
    */
    public static void main (String[] args)
    {
        int limit = args.length != 0
            ? Integer.parseInt (args[0])
            : Integer.MAX_VALUE;
        
        membership = new Members ();
        Thread updater = new Thread (new Runnable ()
            {
                public void run ()
                {
                    while (true)
                        membership.updateMembership ();
                }
            } );
        updater.setDaemon (true);
        updater.start ();
          
        int attempts = 0;  
        try
        {
            long start = System.currentTimeMillis ();
            
            int roller = 0;
            while (attempts++ < limit)
            {
                membership.findID (1100);
                if (++roller > 1000000)
                {
                    System.out.print ('.');
                    roller = 0;
                }
            }
            
            System.out.println ("Completed in " + 
                (System.currentTimeMillis () - start) + " msec.");
        }
        catch (Exception ex)
        {
            System.out.println ("Failed after " + attempts + " attempt(s).");
            ex.printStackTrace ();
        }
    }
}

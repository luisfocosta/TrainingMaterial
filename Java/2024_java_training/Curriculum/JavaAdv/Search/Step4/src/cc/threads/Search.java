/*
Copyright 2005-2015 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
This class searches for files of a given name under a given root directory.
The search is threaded so that the user can suspend or cancel it via
console input.

@author Will Provost
*/
public class Search
{
    public enum Status { SEARCHING, CANCELED, DONE };

    private static Status status;

    /**
    The search method looks for a file of the given name in the given
    directory, and if it is found it writes the full path of the file to the
    console.  It then recurses through any child directories.
    It synchronizes the reading of each individual file on the 
    {@link #status} value, which keeps the UI clean and allows suspension 
    and cancellation.
    */
    public static void searchForFile (String filename, File path)
        throws IOException
    {
        File candidate = null;
        
        synchronized (status)
        {
            if (status != Status.SEARCHING)
                return;

            String display = path.getCanonicalPath ();
            if (display.length () > 60)
                display = "..." + display.substring (display.length () - 60);

            candidate = new File (path, filename);
            if (candidate.exists ())
                System.out.println ("Found " + candidate.getCanonicalPath ());
        }
        
        for (File child : path.listFiles ())
            if (child.isDirectory ())
                searchForFile (filename, child);
    }
    
    /**
    This class implements <code>run</code> to poll for keyboard input.
    */
    private static class Monitor
        extends Thread
    {
        /**
        As long as the status is {@link #Status SEARCHING}, checks to see
        if any keyboard input has been made available.  If and when it has,
        this method asks for confirmation before setting the status to
        {@link #Status CANCELED}.
        It synchronizes each confirmation/cancellation attempt against the
        {@link #status} value, so that it can cleanly interrupt the ongoing
        search, including console output.
        */
        public void run ()
        {
            BufferedReader in = new BufferedReader
                (new InputStreamReader (System.in));
            while (status == Status.SEARCHING)
                try
                {
                    if (System.in.available () != 0)
                        synchronized (status)
                        {
                            in.readLine ();

                            System.out.println ();
                            System.out.print ("Cancel search? ");

                            String answer = in.readLine ();
                            if (answer.length () != 0 && 
                                (answer.charAt (0) == 'y' || 
                                    answer.charAt (0) == 'Y'))
                                status = Status.CANCELED;
                        }
                    else
                        Thread.sleep (100);
                }
                catch (IOException | InterruptedException ex)
                {
                    System.out.println ();
                    System.out.println (ex.getClass ().getName ());
                    System.out.println ();
                }
        }
    }
    
    /**
    Application method reads filename and optional root directory
    (the default is the working directory), starts a {@link #Monitor},
    and then launches the search on the main thread.
    */
    public static void main (String[] args)
        throws Exception
    {
        if (args.length == 0)
        {
            System.out.println ("Usage: java Search <filename> [<path>]");
            System.exit (-1);
        }
        
        String filename = args[0];
        File path = new File (args.length > 1 ? args[1] : ".");
        
        if (!path.isDirectory ())
        {
            System.out.println 
                ("Second argument must be a directory path.");
            System.exit (-1);
        }

        System.out.println ("Searching for file:  " + filename);
        System.out.println ("From root directory: " + path.getAbsolutePath ());
        System.out.println ("Hit ENTER to cancel ...");
        
        long start = System.currentTimeMillis ();
        status = Status.SEARCHING;
        new Monitor ().start ();
        try
        {
            searchForFile (filename, path);
        }
        catch (IOException ex)
        {
            System.out.println ("IOException while searching.");
        }
        finally
        {
            status = Status.DONE;
        }

        System.out.println ("Time: " + 
            (System.currentTimeMillis () - start) + " msec");
    }
}

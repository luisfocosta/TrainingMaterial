/*
Copyright 2005-2015 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
This class searches for files of a given name under a given root directory.
The search is threaded so that the user can suspend or cancel it via
console input.

@author Will Provost
*/
public class Search
{
    private static final String CLEAR = "" + ((char) 13) +
        "                                        " +
        "                                       " + ((char) 13);

    private static int filesSearched;
    private static int filesFound;
    private static final Set<String> threadIDs = new HashSet<> ();

    /**
    Stream "peek" method that counts files searched and threads used.
    */
    public static void countFilesAndThreads (Path path)
    {
        ++filesSearched;
        threadIDs.add (Thread.currentThread ().toString ());
    }

    /**
    Stream "peek" method that shows current search path in the console.
    */
    public static void showStatus (Path path)
    {
        String display = path.toString ();
        if (display.length () > 60)
            display = "..." + display.substring (display.length () - 60);
        System.out.print (CLEAR + display);
    }

    /**
    Application method reads filename and optional root directory
    (the default is the working directory), starts a {@link #Monitor},
    and then launches the search on the main thread.
    */
    public static void main (String[] args)
        throws Exception
    {
        String filename = args.length > 0 ? args[0] : "Search.java";
        Path path = Paths.get (args.length > 1 ? args[1] : ".");

        System.out.println ("Searching for file:  " + filename);
        System.out.println ("From root directory: " + path);

        long start = System.currentTimeMillis ();
        try
        {
            Files.walk (path)
                 .parallel ()
                 .peek (Search::countFilesAndThreads)
                 .peek (Search::showStatus)
                 .filter (p -> p.endsWith (filename))
                 .peek (p -> ++filesFound)
                 .forEach (p -> System.out.println (CLEAR + "Found " + p));
        }
        catch (IOException ex)
        {
            System.out.println ("IOException while searching.");
        }

        System.out.println ("Time: " +
            (System.currentTimeMillis () - start) + " msec");
        System.out.println ("Files searched: " + filesSearched);
        System.out.println ("Threads used: " + threadIDs.size ());
    }
}

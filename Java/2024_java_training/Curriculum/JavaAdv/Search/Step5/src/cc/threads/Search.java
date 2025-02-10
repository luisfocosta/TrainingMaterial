/*
Copyright 2005-2015 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.threads;

import java.util.concurrent.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

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

    private static final int POOL_SIZE = 20;
    private static ExecutorService threadPool = 
        Executors.newFixedThreadPool (POOL_SIZE);
    private static AtomicInteger workerCount = new AtomicInteger (0);
    private static AtomicInteger pathsThatSpawned = new AtomicInteger (0);
    private static AtomicInteger pathsThatDidNotSpawn = new AtomicInteger (0);

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
        
        File[] children = path.listFiles ();
        int count = children.length;
        for (File child : children)
            if (child.isDirectory ())
            {
                if (--count == 0 || workerCount.get () >= POOL_SIZE)
                {
                    pathsThatDidNotSpawn.incrementAndGet ();
                    searchForFile (filename, child);
                }
                else
                {
                    pathsThatSpawned.incrementAndGet ();
                    threadPool.submit (new Searcher (filename, child));
                }
            }
    }
    
    /**
    Runnable that calls the recursive method; this is used simply to wrap
    recursive calls in new tasks that then can be run in new threads or
    submitted to thread pools.
    */
    private static class Searcher
        implements Runnable
    {
        private String filename;
        private File path;
        
        public Searcher (String filename, File path)
        {
            this.filename = filename;
            this.path = path;
        }
        
        public void run ()
        {
            workerCount.incrementAndGet ();
            
            try
            {
                searchForFile (filename, path);
            }
            catch (IOException ex)
            {
                System.out.println ("IOException while searching.");
            }
            
            if (workerCount.decrementAndGet () == 0)
            {
                System.out.println ();
                ViewThreads.showAllThreads ();
                System.out.println ();

                threadPool.shutdown ();
            }
        }
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
        System.out.println ("Using a pool of " + POOL_SIZE + " threads.");
        System.out.println ("Hit ENTER to cancel ...");
        
        long start = System.currentTimeMillis ();
        status = Status.SEARCHING;
        new Monitor ().start ();
        threadPool.submit (new Searcher (filename, path));
        threadPool.awaitTermination (1, TimeUnit.HOURS);
        status = Status.DONE;

        System.out.println ("Time: " + 
            (System.currentTimeMillis () - start) + " msec");
        System.out.println ("Paths that submitted new tasks: " +
            pathsThatSpawned.get ());
        System.out.println ("Paths that didn't submit tasks: " +
            pathsThatDidNotSpawn.get ());
    }
}

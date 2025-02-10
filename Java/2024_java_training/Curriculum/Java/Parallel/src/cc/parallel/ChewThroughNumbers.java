/*
Copyright 2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.parallel;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
Experiments with parallel processing using streams.

@author Will Provost
*/
public class ChewThroughNumbers
{
    public static final int LIMIT = 1000;

    public static Set<String> threads = new HashSet<> ();

    /**
    Suitable for insertion in a stream's processing chain,
    this method builds a set of unique calling threads which we can count
    when processing is complete.
    */
    public static int countThreads (int x)
    {
        threads.add (Thread.currentThread ().toString ());
        return x;
    }

    /**
    Suitable for insertion in a stream's processing chain,
    this method imposes a 10-millisecond delay each time it is called.
    */
    public static int slowDown (int x)
    {
        try { Thread.sleep (10); } catch (InterruptedException ex) {}
        return x;
    }

    /**
    Builds an IntStream on the class' array of integers, and uses it
    to take the average of all values. Allows the caller to control whether
    we count threads, slow processing down artificially, and whether we
    use sequential or parallel processing.

    @param countThreads If true we cause the stream to call the
        {@link #countThreads countThreads method}
    @param slowDown If true we cause the stream to call the
        {@link #slowDown slowDown method}
    @param parallel If true we call parallel() to get a parallel-processing
        stream before carrying out the work
    */
    public static void chew
        (boolean countThreads, boolean slowDown, boolean parallel)
    {
        System.out.print ("Processing " + LIMIT + " numbers");

        IntStream source = IntStream.iterate (0, x -> x + 1).limit (LIMIT);

        if (parallel)
        {
            source = source.parallel ();
            System.out.print (", in parallel");
        }

        if (countThreads)
        {
            source = source.map (ChewThroughNumbers::countThreads);
            threads.clear ();
            System.out.print (", counting threads");
        }

        if (slowDown)
        {
            source = source.map (ChewThroughNumbers::slowDown);
            System.out.print (", slowing down");
        }

        System.out.println (" ...");
        System.out.println ();

        long start = System.currentTimeMillis ();
        double mean = source.average ().getAsDouble ();
        long time = System.currentTimeMillis () - start;

        System.out.println ("Result: " + mean);
        System.out.println ("Done in " + time + " msec.");
        if (countThreads)
            System.out.println ("Used " + threads.size () + " threads.");
        System.out.println ();
    }

    /**
    Builds the array of integers, and processes it repeatedly,
    using different settings.
    */
    public static void main (String[] args)
    {
        chew (false, false, false);
        chew (true, false, false);
        chew (true, false, true);
        chew (true, true, false);
        chew (true, true, true);
    }
}

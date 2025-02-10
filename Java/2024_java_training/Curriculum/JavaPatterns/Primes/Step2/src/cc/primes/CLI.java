/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.primes;

/**
Command-line interface to the primes finder.

@author Will Provost
*/
public class CLI
{
    /**
    Creates a {@link FindPrimes} engine, sets it to find primes in the range
    1-100,000, and starts it up.
    */
    public static void main (String[] args)
    {
        FindPrimes worker = new FindPrimes 
            (args.length == 0 ? 1000 : Integer.parseInt (args[0]));
        worker.addPrimesListener 
            (prime -> System.out.print (" " + prime));
        worker.find ();
    }
}


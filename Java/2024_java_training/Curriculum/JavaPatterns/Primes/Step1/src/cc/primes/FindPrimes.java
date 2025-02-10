/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.primes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
An engine for finding prime numbers.

@author Will Provost
*/
public class FindPrimes
{
    private long howHigh;
    private List<Long> primes;
    private AtomicBoolean canceled = new AtomicBoolean (false);

    /**
    Create with a "how high" value -- the engine will create a list of all
    prime numbers from one to this number, inclusive.
    */
    @SuppressWarnings("unused") // we like our loop labels
    public FindPrimes (long howHigh)
    {
        this.howHigh = howHigh;
    }
    
    /**
    Trigger the algorithm to find primes.
    This will iterate from 2 to {@link #getHowHigh "how high"} and add any 
    primes to the {@link #getPrimes primes list}.  It observes a 
    {@link #cancel canceled} flag for graceful shutdown.
    */
    public void find ()
    {
        primes = new ArrayList<Long> ();
        canceled.set (false);
        Candidate: for (long candidate = 2; candidate <= howHigh; ++candidate)
        {
            if (canceled.get ())
                return;
            
            Factor: for (long factor = 2; factor <= candidate / 2; ++factor)
                if (candidate % factor == 0)
                    continue Candidate;

            primes.add (candidate);
        }
    }
    
    /**
    Cancel the current {@link #find find} process.
    */
    public void cancel ()
    {
        canceled.set (true);
    }
    
    /**
    Accessor for the "how high" value -- the maximum integer to be tested by
    the {@link #find find} method.
    */
    public long getHowHigh ()
    {
        return howHigh;
    }
    
    /**
    Accessor for the list of found primes, available after or during compilation.
    */
    public List<Long> getPrimes ()
    {
        synchronized (primes)
        {
            return primes;
        }
    }
}


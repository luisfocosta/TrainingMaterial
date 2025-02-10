/*
Copyright 2005, 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.primes;

import java.util.function.Consumer;

import javax.swing.JProgressBar;

/**
A progress bar that can listen for {@link PrimesListener primes events}.

@author Will Provost
*/
public class ProgressBar
    extends JProgressBar
{
    private FindPrimes worker;
    private Consumer<Long> listener = 
        prime -> setValue (prime.intValue ());
        
    /**
    Sets the maximum to the {@link FindPrimes#getHowHigh "how high"} value on
    the primes engine, and the initial value to zero.
    */
    public ProgressBar (FindPrimes worker)
    {
        this.worker = worker;
        
        setMaximum ((int) worker.getHowHigh ());
        setValue (0);
        
        worker.addPrimesListener (listener);
    }

    public void close ()
    {
        worker.removePrimesListener (listener);
    }
}


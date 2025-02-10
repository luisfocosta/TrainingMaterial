/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.thread;

/**
A series of running examples of synchronization blocks.

@author Will Provost
*/
public class RunInSynch
{
    private Object lock1 = new Object ();
    private Object lock2 = new Object ();

    /**
    Runs for roughly three seconds, and shows in standard output when it
    starts, ticks through each second, and finishes.
    */
    private void workForThreeSeconds (String ID)
    {
        System.out.println (ID + " running ...");
        for (int i = 1; i <= 3; ++i)
        {
            System.out.println ("  " + ID + " " + i);
            try
            {
                Thread.sleep (1000);
            }
            catch (InterruptedException ex)
            {
                System.out.println ("Now, that IS strange ...");
            }
        }
        System.out.println (ID + " done.");
    }

    /**
    Calls {@link #workForThreeSeconds workForThreeSeconds};
    note that this is a synchronized method.
    */
    public synchronized void ownLockMethodA ()
    {
        workForThreeSeconds ("ownLockMethodA()");
    }

    /**
    Calls {@link #workForThreeSeconds workForThreeSeconds} in a
    synchronized block, using 'this' as the lock.
    */
    public void ownLockMethodB ()
    {
        synchronized (this)
        {
            workForThreeSeconds ("ownLockMethodB()");
        }
    }

    /**
    Calls {@link #workForThreeSeconds workForThreeSeconds},
    once (the "prelude"), then a second time in a synchronized block,
    using 'this' as the lock, and a third time after the synchronized
    block (the "coda").
    */
    public void ownLockMethodC ()
    {
        workForThreeSeconds ("ownLockMethodC() prelude");
        synchronized (this)
        {
            workForThreeSeconds ("ownLockMethodC()");
        }
        workForThreeSeconds ("ownLockMethodC() coda");
    }

    /**
    Calls {@link #workForThreeSeconds workForThreeSeconds} in a
    synchronized block, using lock1.
    */
    public void lock1MethodA ()
    {
        synchronized (lock1)
        {
            workForThreeSeconds ("lock1MethodA()");
        }
    }

    /**
    Calls {@link #workForThreeSeconds workForThreeSeconds} in a
    synchronized block, using lock1.
    */
    public void lock1MethodB ()
    {
        synchronized (lock1)
        {
            workForThreeSeconds ("lock1MethodB()");
        }
    }

    /**
    Calls {@link #workForThreeSeconds workForThreeSeconds} in a
    synchronized block, using lock2.
    */
    public void lock2MethodA ()
    {
        synchronized (lock2)
        {
            workForThreeSeconds ("lock2MethodA()");
        }
    }

    /**
    Calls {@link #workForThreeSeconds workForThreeSeconds} in a
    synchronized block, using lock2.
    */
    public void lock2MethodB ()
    {
        synchronized (lock2)
        {
            workForThreeSeconds ("lock2MethodB()");
        }
    }

    /**
    Starts two threads, calling {@link #ownLockMethodA ownLockMethodA}
    on one and {@link #ownLockMethodB ownLockMethodB} on the other.
    Joins both threads so that they're done by the time the method returns.
    */
    public void runTwoOfMyOwn ()
    {
        Thread[] threads =
            {
                new Thread (this::ownLockMethodA),
                new Thread (this::ownLockMethodB)
            };
        for (Thread thread : threads)
            thread.start ();
        for (Thread thread : threads)
            try
            {
                thread.join ();
            }
            catch (InterruptedException ex)
            {
                System.out.println ("Now, that IS strange ...");
            }
    }

    /**
    Starts three threads, one each calling
    {@link #ownLockMethodA ownLockMethodA},
    {@link #ownLockMethodB ownLockMethodB}, and
    {@link #ownLockMethodC ownLockMethodC} .
    Joins all threads so that they're done by the time the method returns.
    */
    public void runThreeOfMyOwn ()
    {
        Thread[] threads =
            {
                new Thread (this::ownLockMethodA),
                new Thread (this::ownLockMethodB),
                new Thread (this::ownLockMethodC)
            };
        for (Thread thread : threads)
            thread.start ();
        for (Thread thread : threads)
            try
            {
                thread.join ();
            }
            catch (InterruptedException ex)
            {
                System.out.println ("Now, that IS strange ...");
            }
    }

    /**
    Starts four threads, one each calling
    {@link #lock1MethodA lock1MethodA}, {@link #lock1MethodB lock1MethodB},
    {@link #lock2MethodA lock2MethodA}, and {@link #lock2MethodB lock2MethodB}.
    Joins all threads so that they're done by the time the method returns.
    */
    public void runFourOnLocks1and2 ()
    {
        Thread[] threads =
            {
                new Thread (this::lock1MethodA),
                new Thread (this::lock1MethodB),
                new Thread (this::lock2MethodA),
                new Thread (this::lock2MethodB)
            };
        for (Thread thread : threads)
            thread.start ();
        for (Thread thread : threads)
            try
            {
                thread.join ();
            }
            catch (InterruptedException ex)
            {
                System.out.println ("Now, that IS strange ...");
            }
    }

    /**
    Runs each of a series of thread-synchronization tests, in sequence:
    {@link #runTwoOfMyOwn runTwoOfMyOwn},
    {@link #runThreeOfMyOwn runThreeOfMyOwn}, and
    {@link #runFourOnLocks1and2 runFourOnLocks1and2}.
    */
    public static void main (String[] args)
    {
        RunInSynch tester = new RunInSynch ();

        System.out.println ("Testing two threads ...");
        tester.runTwoOfMyOwn ();
        System.out.println ();

        System.out.println ("Testing three threads ...");
        tester.runThreeOfMyOwn ();
        System.out.println ();

        System.out.println ("Testing four threads ...");
        tester.runFourOnLocks1and2 ();
    }
}

/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.thread;

import java.util.concurrent.Semaphore;

/**
A hypothetical connection pool, which raises the challenge of controlling
access to multiple threads.

@author Will Provost
*/
public class ConnectionPool
{
    private static final int POOL_SIZE = 10;

    private Object[] connections = new Object[POOL_SIZE];
    private boolean[] inUse = new boolean[POOL_SIZE];

    private Semaphore semaphore = new Semaphore (POOL_SIZE);

    /**
    Create a fixed-size pool of objects.
    */
    public ConnectionPool ()
    {
        for (int i = 0; i < POOL_SIZE; ++i)
            connections[i] = new Object ();
    }

    /**
    Acquire a permit from our semaphore.
    Check for a free object, and return it if found.
    Otherwise, throw a state exception -- and this should not happen
    as long as we keep the semaphore and pool sizes equal.
    */
    public Object checkOut ()
    {
          try
          {
              semaphore.acquire ();
          }
          catch (InterruptedException ex)
          {
              System.out.println ("Now, that IS strange ...");
          }

        synchronized (this)
        {
            for (int i = 0; i < POOL_SIZE; ++i)
                if (!inUse[i])
                {
                    inUse[i] = true;
                    return connections[i];
                }
        }

        throw new IllegalStateException ("No connections available.");
    }

    /**
    If the object has not been checked out or has already been checked in,
    throw a state exception. Otherwise, mark the object available for a new
    client, and release our semaphore.
    */
    public synchronized void checkIn (Object connection)
    {
        for (int i = 0; i < POOL_SIZE; ++i)
            if (connections[i] == connection)
            {
                if (!inUse[i])
                    throw new IllegalArgumentException
                        ("This connection has already been returned.");

                inUse[i] = false;
                semaphore.release ();
                return;
            }

        throw new IllegalArgumentException ("No such object in pool.");
    }

    /**
    Exercises the connection pool by checking out an object, sleeping for
    5 seconds, and checking the object back in.
    */
    public void useAConnection ()
    {
        Object connection = checkOut ();
        String ID = Thread.currentThread ().getName ();
        System.out.println (ID + " checked out.");

        try
        {
            Thread.sleep (5000);
        }
        catch (InterruptedException ex)
        {
            System.out.println ("Now, that IS strange ...");
        }

        checkIn (connection);
        System.out.println (ID + " checked in.");
    }

    /**
    Creates a connection pool, and a pool of threads that is twice as large.
    Threads are started at a 1/4-second stagger.
    Each thread tries to {@link #useAConnection use a connection},
    so only the first half get access right away.
    The others wait a few seconds for permits to be released back to the
    semaphore, and then they all run as well.
    */
    public static void main (String[] args)
    {
        ConnectionPool pool = new ConnectionPool ();
        for (int i = 0; i < POOL_SIZE * 2; ++i)
        {
            new Thread (pool::useAConnection).start ();
            try
            {
                Thread.sleep (250);
            }
            catch (InterruptedException ex)
            {
                System.out.println ("Now, that IS strange ...");
            }
        }
    }
}

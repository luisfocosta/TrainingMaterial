/*
Copyright 2005-2015 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.threads;

import java.util.ArrayList;
import java.util.List;

/**
This class implements a membership database.  A very simple membership 
database: all it has are member IDs.  It simulates ongoing creation and
cancellation of memberships by randomly adding and removing members, 
fluctuating in size but maintaining an average size over time such that at
any moment roughly one half of all possible membership IDs are actually
active (in the list) at any given time.

@author Will Provost
*/
public class Members
{
    /**
    Set to 1000.
    */
    private static final int TYPICAL_SIZE = 1000;
    
    /**
    Given a best-guess capacity of 125% of {@link #TYPICAL_SIZE},
    allowing for significant fluctuation.
    */
    private List<Integer> IDs = new ArrayList<> (TYPICAL_SIZE * 5 / 4);
    
    private int nextID;
    public int lookups;
    public int adds;
    public int removes;

    /**
    Initializes membership to a sequence of IDs from 1 to
    {@link #TYPICAL_SIZE}.
    */
    public Members ()
    {
        for (nextID = 1; nextID <= TYPICAL_SIZE; ++nextID)
            IDs.add (nextID);
    }
    
    /**
    Randomly chooses whether to add a member or remove one.  The probability
    is skewed towards maintaining the initial size, but the size will
    fluctuate rapidly and unpredictably.
    */
    public synchronized void updateMembership ()
    {
        if (Math.random () >= ((double) IDs.size () / TYPICAL_SIZE / 2))
        {
            IDs.add (nextID);
            nextID = (nextID + 1) % (TYPICAL_SIZE * 2);
            ++adds;
        }
        else
        {
            IDs.remove ((int) (Math.random () * IDs.size ()));
            ++removes;
        }
    }
    
    /**
    Confirms that a given membership ID is valid; i.e. is in the list of
    active IDs.
    */
    public synchronized boolean findID (int ID)
    {
        ++lookups;
        for (int test : IDs)
            if (ID == test)
                return true;

        return false;
    }
}


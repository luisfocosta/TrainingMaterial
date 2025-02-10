/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.client;

import java.util.*;
import cc.html.Train;

/**
Hard-coded data for use in producing an HTML rendering.

@author Will Provost
*/
public class Data
    extends ArrayList<Train>
{
    /**
    Automatically primes itself with data records.  Thus a usage such as
    <code>for (Data.Record record : new Data ())</code> is viable.
    */
    public Data ()
    {
        add (new Train ("175", "Patriot", "Boston", 
            "Washington", "7:45 a.m.", "9A"));
        add (new Train ("2167", "Acela Express", "Boston", 
            "New York", "1:20 p.m.", "12A"));
        add (new Train ("137", "Regional", "Boston", 
            "Philadelphia", "1:40 p.m.", "4B"));
        add (new Train ("1016", "Lakeshore Limited", "Chicago", 
            "Buffalo", "9:00 a.m.", "5"));
        add (new Train ("14", "Coast Starlight", "Los Angeles", 
            "San Francisco", "10:15 a.m.", "22"));
        add (new Train ("28", "Empire Builder", "Spokane", 
            "Minneapolis / St. Paul", "1:15 a.m.", "2"));
    }
}


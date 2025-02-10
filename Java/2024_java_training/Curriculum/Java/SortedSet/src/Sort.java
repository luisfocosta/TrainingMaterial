/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

import java.util.*;

/**
Builds an array of state names and then sorts by adding to a TreeSet.

@author Will Provost
*/
public class Sort
{
    /**
    Calls {@link buildNamesArray buildNamesArray} and then adds the contents
    one by one to a new TreeSet object.
    Prints the resulting names, and then performs a search for any name
    passed on the command line by calling contains () on the set.
    */
    public static void main (String[] args)
    {
        String[] names = buildNamesArray ();
        SortedSet<String> set = new TreeSet<String> ();
        for (String name : names)
            set.add (name);

        for (String name : set)
            System.out.print (name + " ");
        System.out.println ();
        
        if (args.length != 0)
        {
            String fullName = args[0];
            for (int i = 1; i < args.length; ++i)
                fullName += " " + args[i];
                
            System.out.println (fullName + (set.contains (fullName)
                ? " is a state."
                : " is not a state."));
        }
    }
    
    /**
    Primes the fifty names into the array.
    */    
    public static String[] buildNamesArray ()
    {
        String[] result = 
        {
            "Alaska",
            "Hawaii",
            "California",
            "Oregon",
            "Washington",
            "Idaho",
            "Nevada",
            "Arizona",
            "Utah",
            "Wyoming",
            "Montana",
            "Colorado",
            "New Mexico",
            "North Dakota",
            "South Dakota",
            "Nebraska",
            "Kansas",
            "Oklahoma",
            "Texas",
            "Minnesota",
            "Iowa",
            "Missouri",
            "Louisiana",
            "Mississippi",
            "Arkansas",
            "Tennessee",
            "Kentucky",
            "Illinois",
            "Wisconsin",
            "Michigan",
            "Indiana",
            "Alabama",
            "Ohio",
            "Pennsylvania",
            "West Virginia",
            "Virginia",
            "North Carolina",
            "South Carolina",
            "Georgia",
            "Florida",
            "Maryland",
            "Delaware",
            "New York",
            "New Jersey",
            "Connecticut",
            "Massachusetts",
            "Vermont",
            "New Hampshire",
            "Rhode Island",
            "Maine"
        };
        
        return result;
    }
}


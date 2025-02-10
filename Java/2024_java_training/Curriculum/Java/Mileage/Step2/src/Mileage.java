/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

/**
This class manages a table of distances between various cities as a
jagged array of integers.

@author Will Provost
*/
public class Mileage
{
    public static void main (String[] args)
    {
        String[] origins = 
        {
            "Boston",
            "NYC",
            "Philadelphia",
            "Washington"
        };

        String[] destinations = 
        {
            "Washington",
            "Philadelphia",
            "NYC",
            "Boston"
        };
        
        int[][] mileage = new int[3][];
        mileage[0] = new int[3];
        mileage[1] = new int[2];
        mileage[2] = new int[1];
        mileage[0][0] = 439; mileage[0][1] = 306; mileage[0][2] = 216;
        mileage[1][0] = 226; mileage[1][1] =  93;
        mileage[2][0] = 139;
        
        int testOrigin = 0;
        int testDestination = 1;
        System.out.println ("Distance from " + origins[testOrigin] +
            " to " + destinations[testDestination] + 
            " is " + mileage[testOrigin][testDestination] + " miles.");
    }
}


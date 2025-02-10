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
        for (int i = 0; i < mileage.length; ++i)
            mileage[i] = new int[3 - i];
        mileage[0][0] = 439; mileage[0][1] = 306; mileage[0][2] = 216;
        mileage[1][0] = 226; mileage[1][1] =  93;
        mileage[2][0] = 139;
        
        if (args.length < 2)
        {
            System.out.println 
                ("Usage: java Mileage <origin> <destination>");
            System.exit (-1);
        }
        
        // Gather the parameters -- bail if they're the same name:
        String origin = args[0];
        String destination = args[1];
        if (origin.equals (destination))
        {
            System.out.println ("Distance is definitely zero!");
            System.exit (-1);
        }
        
        // Find the names in the arrays:
        int originIndex = 0, destinationIndex = 0;
        while (originIndex < origins.length &&
                !origins[originIndex].equals (origin))
            ++originIndex;

        while (destinationIndex < destinations.length &&
                !destinations[destinationIndex].equals (destination))
            ++destinationIndex;
        
        // If either name is not found, quit:
        if (originIndex == origins.length || 
            destinationIndex == destinations.length)
        {
            System.out.println ("Couldn't find one or both cities.");
            System.exit (-1);
        }
        
        // Since we don't duplicate distances by building a full rectangular
        //   array, we have to assure that we're in the triangle.
        //   If not, we can still get the information -- just swap
        //   names and indices:
        if (originIndex + destinationIndex > origins.length - 2)
        {
            origin = args[1];
            destination = args[0];
            int temp = originIndex;
            originIndex = origins.length - destinationIndex - 1;
            destinationIndex = origins.length - temp - 1;
        }
        
        // We're ready! Print the information:
        System.out.println ("Distance from " + origin + " to " + destination +
            " is " + mileage[originIndex][destinationIndex] + " miles.");
    }
}


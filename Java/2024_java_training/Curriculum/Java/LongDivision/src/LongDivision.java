/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

/**
This class illustrates recursion: it performs division
operations by recursively subtracting the divisor.

@author Will Provost
*/
public class LongDivision
{
    /**
    Calls {@link divide divide} to get the result.
    */
    public static void main (String[] args)
    {
        if (args.length < 2)
        {
            System.out.println 
                ("Usage: java LongDivision <dividend> <divisor>");
            System.exit (-1);
        }
        
        int dividend = Integer.parseInt (args[0]);
        int divisor = Integer.parseInt (args[1]);
        System.out.println (args[0] + " / " + args[1] + " = " +
            divide (dividend, divisor));
    }
    
    /**
    Returns divide (dividend - divisor, divisor) + 1,
    with a boundary condition that dividend < divisor.
    */
    public static int divide (int dividend, int divisor)
    {
        System.out.println 
            ("  dividend = " + dividend + "; divisor = " + divisor);
          
        if (dividend >= divisor)
            return divide (dividend - divisor, divisor) + 1;
        else
            return 0;
    }
}

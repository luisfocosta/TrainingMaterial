/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

/**
This class does some simple mathematical calculations on two command-line
arguments, parsed as integers, and reports the results.

@author Will Provost
*/
public class DoMath
{
    /**
    This application method parses the command line for two parameters and
    attempts to convert them to integers.  Then it performs mathematical
    operations on the numbers, reporting the results:
    <ol>
    <li>Sum
    <li>Difference
    <li>Product
    <li>Ratio as an integer, and modulus
    </ol>

    @param args Must include two arguments which are integer representations
    */
    public static void main (String[] args)
    {
        int one = Integer.parseInt (args[0]);
        int two = Integer.parseInt (args[1]);

        System.out.println ("Sum of numbers = " + (one + two));
        System.out.println ("Difference of numbers = " + (one - two));
        System.out.println ("Product of numbers = " + (one * two));
        System.out.println ("Dividing numbers = " + (one / two));
        System.out.println ("Modulus/remainder is " + (one % two));
    }
}


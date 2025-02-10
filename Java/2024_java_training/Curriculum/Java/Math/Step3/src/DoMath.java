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
    This method returns the hypotenuse of a right triangle whose sides are
    of length <b>a</b> and <b>b</b>.

    @param a One side of the right triangle
    @param b The other side of the right triangle
    @return The hypotenuse of the right triangle
    */
    private static double hypotenuse (int a, int b)
    {
        int sumOfSquares = a * a + b * b;
        return Math.sqrt (sumOfSquares);
    }

    /**
    This method calculates and returns the circumference of a circle whose
    radius is provided as a parameter.

    @param radius The radius of the circle
    @return The circumference of the circle
    */
    private static double circumference (int radius)
    {
        return radius * 2 * Math.PI;
    }

    /**
    This application method parses the command line for two parameters and
    attempts to convert them to integers.  Then it performs mathematical
    operations on the numbers, reporting the results:
    <ol>
    <li>Sum
    <li>Difference
    <li>Product
    <li>Ratio
    <li>Hypotenuse as an integer
    <li>Circumference of a circle whose radius is the first argument, as an integer
    </ol>

    @param args Must include two arguments which are integer representations
    */
    public static void main (String[] args)
    {
        int one = Integer.parseInt (args[0]);
        int two = Integer.parseInt (args[1]);

        System.out.format ("%-27s = %9d%n", "Sum of numbers", one + two);
        System.out.format ("%-27s = %9d%n", 
            "Difference of numbers", one - two);
        System.out.format ("%-27s = %9d%n", "Product of numbers", one * two);
        System.out.format ("%-27s = %14.4f%n", 
            "Dividing numbers", (double) one / two);
        System.out.format ("%-27s = %14.4f%n", 
            "Hypotenuse with sides given", hypotenuse (one, two));
        System.out.format ("%-27s = %14.4f%n", 
            "Circumference of circle one", circumference (one));
        System.out.format ("%-27s = %14.4f%n", 
            "Circumference of circle two", circumference (two));
    }
}


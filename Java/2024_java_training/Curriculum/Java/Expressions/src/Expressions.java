/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

/**
This application illustrates simple to complex Java expression syntax.

@author Will Provost
*/
public class Expressions
{
    private static int five ()
    {
        return 5;
    }
    
    private static int increment (int x)
    {
        return x + 1;
    }
    
    public static void main (String[] args)
    {
        System.out.println ("Expressions:");
        System.out.println ("  5 = " + 5);
        System.out.println ("  5 + 4 = " + (5 + 4));
        System.out.println ("  5 * 4 + 2 = " + (5 * 4 + 2));
        System.out.println ("  5 * (4 + 2) = " + (5 * (4 + 2)));
        System.out.println ("  5 + five () = " + (5 + five ()));
        System.out.println ("  5 + increment (1) = " + (5 + increment (1)));
        System.out.println ("  increment (5) = " + increment (5));
        System.out.println ("  increment (five ()) = " + increment (five ()));
        System.out.println ("  increment (increment (five ()) / 2) = " + increment (increment (five ()) / 2));
        System.out.println ("  (5 == 4) =  " + (5 == 4));
        System.out.println ("  (5 > 4) =   " + (5 > 4));
        System.out.println ("  !(5 == 4) = " + !(5 == 4));
        System.out.println ("  ((5 == 4) && (5 > 4)) =   " + ((5 == 4) && (5 > 4)));
        System.out.println ("  ((5 == 4) || (5 > 4)) =   " + ((5 == 4) || (5 > 4)));
        System.out.println ("  (!(5 == 4) && !(5 > 4)) = " + (!(5 == 4) && !(5 > 4)));
        System.out.println ();
        
        int x = 1;
        int y = 2;
        System.out.println ("  x = " + x);
        System.out.println ("  y = " + y);
        System.out.println ("  x + 1 = " + (x + 1) + "                   (x = " + x + ")");
        System.out.println ("  x++ = " + x++ + "                     (x = " + x + ")");
        System.out.println ("  ++x = " + ++x + "                     (x = " + x + ")");
        System.out.println ("  increment (x) = " + increment (x) + "           (x = " + x + ")");
        System.out.println ("  increment (x--) = " + increment (x--) + "         (x = " + x + ")");
        System.out.println ("  increment (--x) = " + increment (--x) + "         (x = " + x + ")");
        System.out.println ("  increment (x = five ()) = " + increment (x = five ()) + " (x = " + x + ")");
        System.out.println ("  (x = 4) =           " + (x = 4));
        System.out.println ("  (x = 4) + 1 =       " + ((x = 4) + 1));
        System.out.println ("  (x *= 7) =          " + (x *= 7));
        System.out.println ("  (y = (x = 3) + 2) = " + (y = (x = 3) + 2));
        System.out.println ("  y % 3 =             " + (y % 3));
    }
}


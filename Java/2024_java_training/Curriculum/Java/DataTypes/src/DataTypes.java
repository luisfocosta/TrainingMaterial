/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

/**
This application declares local variables of each of the Java primitive types,
and then assigns various legal values to them.

@author Will Provost
*/
public class DataTypes
{
    public static void main (String[] args)
    {
        // Declare a variable of each of the numeric types;
        //   then try setting various values into it;
        //   then print the final value to the console.
        
        byte b = 0;
        b = (byte) 0b01111111;  // this is 2 ^ 7 - 1
        b = (byte) 0b10000000;  // this is negative 2 ^ 7
          // This would not compile: b = 128; (this is 2 ^ 7)
          // This would not compile: b = -255;
        System.out.println ("Final value of byte    b  = " + b);

        short s = 7;
        s = 32767;  // this is 2 ^ 15 - 1
        s = -32768; // this is negative 2 ^ 15
          // This would not compile: s = 32768; (this is 2 ^ 15)
          // This would not compile: s = -65000;
        System.out.println ("Final value of short   s  = " + s);

        int i = 520;
        i = 2147483647;  // this is 2 ^ 31 - 1
        i = -2147483648; // this is negative 2 ^ 31
          // This would not compile: i = 2147483648; (this is 2 ^ 31)
          // This would not compile: i = 0x80000000; (this is 2 ^ 31 in hex)
          // This would not compile: i = -0x100000000; (-2 ^ 32 in hex)
          // This would not compile: i = 5.5; (not an integral value)
          // This would fail at runtime: i = 5 / 0;
        System.out.println ("Final value of int     i  = " + i);

        long l = -1;
        l = -3;
        l = 9_223_372_036_854_775_807L;  // this is 2 ^63 - 1
          // Note the need for L suffix to create literal number this big
        l = -9_223_372_036_854_775_808L; // this is negative 2 ^ 63
          // This would not compile: s = 9223372036854775808L; (this is 2 ^ 63)
        System.out.println ("Final value of long    l  = " + l);

        float f = 0;
        f = 5;
        f = 80.08F; // here double-precision is the default for literals,
                    // so we need the F suffix
        f = -0.000054F;
        System.out.println ("Final value of float   f  = " + f);

        double d = 7;
        d = 0.000000000000000001;
        d = Double.NaN; // IEEE "not a number"
        d = Double.POSITIVE_INFINITY;
        d = 5.0 / 0; // results in Double.POSITIVE_INFINITY
        d = 18446744073709551615.0; // maximum positive value of a long)
        d = 18446744.073709551615; // same thing, put decimal point anywhere)
          // This would not compile: d = 0.184467440737095516151; (overflow)
        System.out.println ("Final value of double  d  = " + d);

        char c = 'a';
        c = 0;
        c = 65;   // this is 'A'
        c = '*';
          // This would not compile: c = "*"; (a string, not a char)
          // This would not compile: c = 128; (overflow)
        System.out.println ("Final value of char    c  = " + c);

        boolean bo = true;
        bo = false;
          // This would not compile: bo = 0; (number)
          // This would not compile: bo = null; (object reference)
        System.out.println ("Final value of boolean bo = " + bo);
            
        // Enumerations:
        Color co = Color.red;
        co = Color.valueOf ("blue");
          // This would compile but throw an IllegalArgumentException: 
          // co = Color.valueOf ("yellow");
        System.out.println ("String  value of Color co = " + co);
        System.out.println ("Ordinal value of Color co = " + co.ordinal ());
        
        // Summarize value ranges for numeric types:
        
        System.out.println ();
        System.out.println ("Legal value ranges:");
        System.out.println 
            ("  Byte    " + Byte.MIN_VALUE + " to " + Byte.MAX_VALUE);
        System.out.println 
            ("  Short   " + Short.MIN_VALUE + " to " + Short.MAX_VALUE);
        System.out.println 
            ("  Integer " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE);
        System.out.println 
            ("  Long    " + Long.MIN_VALUE + " to " + Long.MAX_VALUE);
        System.out.println 
            ("  Float   " + Float.MIN_VALUE + " to " + Float.MAX_VALUE);
        System.out.println 
            ("  Double  " + Double.MIN_VALUE + " to " + Double.MAX_VALUE);
    }
}

enum Color { red, green, blue, black, white };


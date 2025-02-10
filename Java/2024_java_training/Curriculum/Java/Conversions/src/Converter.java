/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

/**
This class' application method performs many conversions between
types and includes some hypothetical code which can be
uncommented to check compiler behavior.

@author Will Provost
*/
public class Converter
{
    public static void main (String[] args)
    {
        byte b = 5;

        // These are all widening conversions,
        //   and so the compiler performs them implicitly:
        short s = b;
        int i = s;
        long l = i;

        // Try going the other way, though, and the compiler insists
        //   that you explicitly accept any loss of precision:
        i = (int) l;
        s = (short) i;
        b = (byte) s;

        // Set a number that would only fit in a long, and observe the
        //   actual loss of precision when you narrow it down:
        l = 0x111111111L;
        i = (int) l;
        s = (short) i;
        b = (byte) s;

        System.out.println ("Long: " + l);
        System.out.println ("Converted to integer: " + i);
        System.out.println ("Converted to short: " + s);
        System.out.println ("Converted to byte: " + b);

        // Widening:
        float f = 3.9F;
        double d = f;

        // Narrowing:
        f = (float) d;

        // Widening:
        d = i;
        d = l;

        // Narrowing - both ways:
        l = (long) f;  // might lose fractional part
        f = l; // might lose overall precision, but not range

        // Enumerations:
        Color color = Color.green;
          // Won't compile -- incompatible types:
          // i = (int) color;
          // color = (Color) 0;
        i = color.ordinal ();
        String colorName = color.name ();
        System.out.println ("Ordinal position of " + colorName + " is " + i);

        System.out.println ("Starting object reference conversions ...");

        // Widening conversion to convert a derived-class reference to a
        //  base-class reference:
        Derived myObject = new Derived ();
        Base myBaseRef = myObject;

        // Narrowing conversion to convert to derived-class type.
        //  If this fails at runtime, it's not lost data as with numbers;
        //  the JVM throws an exception.  So, check the type first:
        @SuppressWarnings("unused")
        Derived myDerivedRef = null;
        if (myBaseRef instanceof Derived)
            myDerivedRef = (Derived) myBaseRef;

        // Same procedure, but now baseRef is actually a Base object:
        Base baseRef = new Base ();

        @SuppressWarnings("unused")
        Derived derivedRef2 = null;
        if (baseRef instanceof Derived)
            derivedRef2 = (Derived) baseRef;
        // Comment out the instanceof test, get a ClassCastException

        System.out.println ("Object reference conversions complete.");
    }
}

enum Color { red, green, blue, white, black }

class Base
{
    public int x;
}

class Derived
        extends Base
{
    public int y;
}

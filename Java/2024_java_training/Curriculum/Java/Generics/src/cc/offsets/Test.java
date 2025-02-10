/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.offsets;

/**
This application exercises the {@link Offset} and {@link Segment} generics,
and includes some commented-out code that would not compile, to illustrate
both "cans" and "cannots" of Java generics.

@author Will Provost
*/
public class Test
{
    // These are "flag types" -- defined just to enable strict type checking:
    public static class TypeA {};
    public static class TypeB {};

    @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
    public static void main (String[] args)
    {
        Offset<TypeA> a1 = new Offset<TypeA> (1);
        Offset<TypeA> a2 = new Offset<TypeA> (2);
        Offset<TypeA> a3 = a1.add (a2);
        System.out.println (a3.getOffset ());

        Offset<TypeB> b1 = new Offset<TypeB> (3);
        Offset<TypeB> b2 = new Offset<TypeB> (4);
        Offset<TypeB> b3 = b1.add (b2);
        System.out.println (b3.getOffset ());

        // Won't compile:
        //Offset<TypeB> b4 = b1.add (a1); // bad argument
        //Offset<TypeB> b4 = a1.add (a2); // bad assignment

        System.out.println ("a1's class: " + a1.getClass ().getName ());
        System.out.println ("a1 instanceof Offset: " +
            (a1 instanceof Offset));

        // Won't compile!
        //System.out.println ("a1 instanceof Offset<TypeA>: " +
        //    a1 instanceof Offset<TypeA>); // no such type at runtime
        //System.out.println ("a1 instanceof Offset<TypeB>: " +
        //    a1 instanceof Offset<TypeB>); // ditto

        // The following are legal, purely for legacy integration;
        // this is not good practice for new code:
        Offset o1 = new Offset (4);
        Offset o2 = a1.add (o1);
        Offset<TypeA> a5 = o1.add (o2); // hmm, don't need typecast ...

        System.out.println ("o1's class: " + o1.getClass ().getName ());
        System.out.println ("o1 instanceof Offset: " +
            (o1 instanceof Offset));

        // Won't compile:
        //Class c = Offset<TypeA>.class; // No such thing
    }
}


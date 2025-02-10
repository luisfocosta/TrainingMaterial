/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.generics;

/**
This application exercises the {@link Offset} and {@link Segment} generics,
and includes some commented-out code that would not compile, to illustrate
both "cans" and "cannots" of Java generics.

@author Will Provost
*/
public class TestOffsets
{
    // These are "flag types" -- defined just to enable strict type checking:
    public static class TypeA {};
    public static class TypeB {};
    
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
        // this is not good practice for new code, and generates warnings:
        Offset o = new Offset (4);
        Offset a1Legacy = a1;
        Offset a1Plusb1 = b1.add (a1Legacy);
        System.out.println (a1Plusb1.getOffset ());
        
        System.out.println ("o's class: " + o.getClass ().getName ());
        System.out.println ("o instanceof Offset: " +
            (o instanceof Offset));
            
        // Won't compile:
        //Class c = Offset<TypeA>.class; // No such thing

        Offset<TypeB[]> ba1 = new Offset<TypeB[]> (3);
        Offset<TypeB[]> ba2 = new Offset<TypeB[]> (4);
        Offset<TypeB[]> ba3 = ba1.add (ba2);
        System.out.println (ba3.getOffset ());
        
    }
}


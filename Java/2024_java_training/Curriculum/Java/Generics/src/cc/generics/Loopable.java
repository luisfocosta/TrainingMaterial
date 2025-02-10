/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.generics;

import java.util.*;

/**
This parameterized type functions as a simple adapter so that ordinary
Java iterators can be used in the Java-1.5 for-each loop.  It wraps an
existing iterator and returns it through the Iterable<T> interface, which is
the required process for the new looping construct.

@author Will Provost
*/
public class Loopable<T>
    implements Iterable<T>
{
    /**
    Create a Loopable on an existing iterator.
    */
    public Loopable (Iterator<T> delegate)
    {
        this.delegate = delegate;
    }
    
    /**
    Return the wrapped iterator when asked by the for loop.
    */
    public Iterator<T> iterator ()
    {
        return delegate;
    }
    
    private Iterator<T> delegate;
    
    /**
    Exercises the adapter by wrapping a string iterator.
    This is overwrought in and of itself, because we could just loop over
    the underlying vector; but it illustrates how to wrap legacy code that
    provides iterators or how to continue to use iterators in a class'
    public interface while still taking advantage of the new looping
    construct.
    */
    public static void main (String[] args)
    {
        Vector<String> strings = new Vector<String> ();
        strings.add ("Hello");
        strings.add ("there");
        Iterator<String> each = strings.iterator ();
        
        for (String s : new Loopable<String> (each))
            System.out.println (s);
    }
}

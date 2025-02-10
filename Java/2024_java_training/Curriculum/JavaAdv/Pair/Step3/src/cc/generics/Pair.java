/*
Copyright 2004-2015 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.generics;

import java.util.ArrayList;
import java.util.List;

/**
A generic pair of object references.

@author Will Provost
*/
public class Pair<A,B>
{
    public A a;
    public B b;

    protected Pair () {}
    
    /**
    Create a pair by providing the object references A and B.
    */
    public Pair (A a, B b)
    {
        this.a = a;
        this.b = b;
    }
    
    /**
    Simple string representation as "a,b".
    */
    @Override
    public String toString ()
    {
        return a.toString () + "," + b.toString ();
    }

    /**
    Accessor for the A value.
    */
    public A getA ()
    {
        return a;
    }

    /**
    Mutator for the A value.
    */
    public void setA (A a)
    {
        this.a = a;
    }

    /**
    Accessor for the B value.
    */
    public B getB ()
    {
        return b;
    }

    /**
    Mutator for the B value.
    */
    public void setB (B b)
    {
        this.b = b;
    }

    /**
    Absorb values from the given pair, but taking its A value as our B,
    and its B as our A, using fields directly.
    */
    public void flipFrom1 (Pair<? extends B,? extends A> source)
    {
        a = source.b;
        b = source.a;
    }
    
    /**
    Absorb values from the given pair, but taking its A value as our B,
    and its B as our A, using accessor methods.
    */
    public void flipFrom2 (Pair<? extends B,? extends A> source)
    {
        a = source.getB ();
        b = source.getA ();
    }
    
    /**
    Pass values to the given pair, but setting its A value based on our B,
    and its B based on our A, using fields directly.
    */
    public void flipTo1 (Pair<B,A> target)
    {
        target.a = b;
        target.b = a;
    }
    
    /**
    Pass values to the given pair, but setting its A value based on our B,
    and its B based on our A, using mutator methods.
    */
    public void flipTo2 (Pair<B,A> target)
    {
        target.setA (b);
        target.setB (a);
    }

    /**
    Synthesize a single list of pairs from two separate lists of values.
    The lists must be of the same size.
    */
    public static <X,Y> List<Pair<X,Y>> convert 
        (List<? extends X> xList, List<? extends Y> yList)
    {
        if (xList.size () != yList.size ())
            throw new IllegalArgumentException ("Uneven lengths!");
        
        List<Pair<X,Y>> result = new ArrayList<> (xList.size ());
        for (int i = 0; i < xList.size (); ++i)
            result.add (new Pair<> (xList.get (i), yList.get (i)));
        
        return result;
    }
}

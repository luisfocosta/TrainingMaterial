/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.generics;

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
}

/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.inherit;

import cc.generics.Validator;

/**
A {@link cc.generics.Validator} dedicated to {@link A} objects.

@author Will Provost
*/
public class AValidator
    implements Validator<A>
{
    /**
    Value of a must be positive.
    */
    public boolean isValid (A a)
    {
        return a != null && a.getA () > 0;
    }

    /**
    Unsupported.
    */
    public A correct (A candidate)
    {
        throw new UnsupportedOperationException ();
    }
}

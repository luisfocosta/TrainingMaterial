/*
Copyright 2005-2015 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.inherit;

import cc.generics.Validator;

/**
A {@link cc.generics.Validator} dedicated to {@link B} objects.

@author Will Provost
*/
public class BValidator
    implements Validator<B>
{
    private Validator<A> delegate = new AValidator ();
    
    /**
    Value of b must be even.
    */
    public boolean isValid (B b)
    {
        return delegate.isValid (b) && b.getB () % 2 == 0;
    }

    /**
    Unsupported.
    */
    public B correct (B candidate)
    {
        throw new UnsupportedOperationException ();
    }
}

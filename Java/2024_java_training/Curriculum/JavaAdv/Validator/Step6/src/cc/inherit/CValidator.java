/*
Copyright 2005-2015 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.inherit;

import cc.generics.Validator;

/**
A {@link cc.generics.Validator} dedicated to {@link C} objects.

@author Will Provost
*/
public class CValidator
    implements Validator<C>
{
    private Validator<B> delegate = new BValidator ();
    
    /**
    Value of c must be ten or less.
    */
    public boolean isValid (C c)
    {
        return delegate.isValid (c) && c.getC () <= 10;
    }

    /**
    Unsupported.
    */
    public C correct (C candidate)
    {
        throw new UnsupportedOperationException ();
    }
}

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
    /**
    Value of c must be ten or less.
    */
    public boolean isValid (C c)
    {
        return c != null && c.getC () <= 10;
    }

    /**
    Unsupported.
    */
    public C correct (C candidate)
    {
        throw new UnsupportedOperationException ();
    }
}

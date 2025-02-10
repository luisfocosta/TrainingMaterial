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
    extends DelegatingValidator<C,B>
{
    /**
    Set our own, default validator for {@link B} as delegate.
    */
    public CValidator ()
    {
        super (new BValidator ());
    }
    
    /**
    Set the given validator for {@link B} as delegate.
    */
    public CValidator (Validator<B> delegate)
    {
        super (delegate);
    }
    
    /**
    Value of c must be ten or less.
    */
    public boolean isValid (C c)
    {
        return super.isValid (c) && c.getC () <= 10;
    }

    /**
    Unsupported.
    */
    public C correct (C candidate)
    {
        throw new UnsupportedOperationException ();
    }
}

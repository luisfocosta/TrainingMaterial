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
    extends DelegatingValidator<B,A>
{
    /**
    Set our own, default validator for {@link A} as delegate.
    */
    public BValidator ()
    {
        super (new AValidator ());
    }
    
    /**
    Set the given validator for {@link A} as delegate.
    */
    public BValidator (Validator<A> delegate)
    {
        super (delegate);
    }
    
    /**
    Value of b must be even.
    */
    public boolean isValid (B b)
    {
        return super.isValid (b) && b.getB () % 2 == 0;
    }

    /**
    Unsupported.
    */
    public B correct (B candidate)
    {
        throw new UnsupportedOperationException ();
    }
}

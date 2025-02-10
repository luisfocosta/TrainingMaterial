/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.inherit;

import cc.generics.Validator;

/**
Generic validator that delegates to a validator for a base type
before enforcing its own validation constraints.

@author Will Provost
*/
public class DelegatingValidator<T extends D,D>
    implements Validator<T>
{
    private Validator<D> delegate;

    /**
    Set our delegate validator to the given one.
    */
    public DelegatingValidator (Validator<D> delegate)
    {
        this.delegate = delegate;
    }
    
    /**
    Call our delegate validator and return what it returns.
    */
    @Override
    public boolean isValid (T candidate)
    {
        return delegate.isValid (candidate);
    }

    /**
    Call our delegate validator and return what it returns.
    */
    @Override
    @SuppressWarnings("unchecked") // we know we'll get a T instance back
    public T correct (T candidate)
    {
        return (T) delegate.correct (candidate);
    }
    
    
}

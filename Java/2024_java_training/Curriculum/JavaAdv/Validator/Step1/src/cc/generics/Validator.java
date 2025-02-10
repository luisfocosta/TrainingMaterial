/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.generics;

/**
This parameterized interface defines the semantics for validating an object
of its parameter type.

@author Will Provost
*/
public interface Validator<T>
{
    /**
    Returns true if the given object is valid according to this validator's
    constraints.
    */
    public boolean isValid (T candidate);
    
    /**
    Optional method returns a corrected version of the given object.
    If {@link #isValid isValid (candidate)} returns true, then this
    method must return the given object, unmodified.
    Implementors may throw <code>UnsupportedOperationException</code>.
    */
    public T correct (T candidate);
}

/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.generics;

import java.util.*;

/**
This parameterized class wraps any collection and offers a simple validation
process.  Validation can be further parameterized by plugging in a
{@link Validator} implementation, either generic or for a given type.

@author Will Provost
*/
public class ValidCollection<T>
{
    /**
    Create the wrapper object based on a given collection object.
    */
    public ValidCollection (Collection<T> data)
    {
        this.data = data;
    }
    
    /**
    Validate all elements of the underlying collection, using a provided
    validator.
    */
    public boolean validate (Validator<T> validator)
    {
        for (T candidate : data)
            if (!validator.isValid (candidate))
                return false;
                
        return true;
    }    

    private Collection<T> data;
}

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
    extends ArrayList<T>
{
    /**
    Singleton instance of the {@link #DefaultValidator}.
    */
    public static DefaultValidator defaultValidator
        = new DefaultValidator ();

    /**
    Validate all elements of the underlying collection, using the
    {@link #defaultValidator}.
    */
    public boolean validate ()
    {
        return validate (defaultValidator);
    }
    
    /**
    Validate all elements of the underlying collection, using a provided
    validator.
    */
    public boolean validate (Validator<? super T> validator)
    {
        for (T candidate : this)
            if (!validator.isValid (candidate))
                return false;
                
        return true;
    }    
    
    /**
    Default implementation of {@link Validator} for any object.
    Simply tests to see that the object reference is not null,
    and doesn't implement the {@link #correct correct} method.
    */
    public static class DefaultValidator
        implements Validator<Object>
    {
        private DefaultValidator () {}
        
        public boolean isValid (Object candidate)
        {
            return candidate != null;
        }
        
        public Object correct (Object candidate)
        {
            throw new UnsupportedOperationException ();
        }
    }
}

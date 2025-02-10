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
    public ValidCollection (Collection<? extends T> data)
    {
        this.data = data;
    }
    
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
        for (T candidate : data)
            if (!validator.isValid (candidate))
                return false;
                
        return true;
    }    

    /**
    Utility method to validate all elements of a collection, without needing
    to create the wrapper object.  Uses the {@link #defaultValidator}.
    */
    public static <T> boolean validate (Collection<? extends T> candidates)
    {
        return validate (candidates, defaultValidator);
    }
    
    /**
    Utility method to validate all elements of a collection, without needing
    to create the wrapper object.  Uses the given validator.
    */
    public static <T> boolean validate 
        (Collection<? extends T> candidates, Validator<? super T> validator)
    {
        return new ValidCollection<T> (candidates).validate (validator);
    }    

    private Collection<? extends T> data;
    
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

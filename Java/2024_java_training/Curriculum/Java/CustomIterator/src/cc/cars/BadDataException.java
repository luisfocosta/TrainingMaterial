/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars;

/**
Exception to express invalidity in the persistent data.

@author Will Provost
*/
public class BadDataException
    extends Exception
{
    private Object source;

    /**
    Build by providing the object with the bad data 
    and a descriptive message.
    */
    public BadDataException (Object source, String message)
    {
        super (message);
        this.source = source;
    }
    
    /**
    Accessor for the <code>source</code> property.
    */
    public Object getSource ()
    {
        return source;
    }
}


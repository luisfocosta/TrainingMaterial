/*
Copyright 2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.ex;

/**
This class acts as a dead-simple demonstration of the auto-close capability
defined for the Java-7 try-with-resources feature.

@author Will Provost
*/
public class MyResource
    implements AutoCloseable
{
    /**
    This method is carelessly written and can't handle strings with less than
    five characters. So it will succeed or fail depending on input ...
    */
    public void parse (String text)
    {
        @SuppressWarnings("unused")
        char c = ' ';
        for (int i = 0; i < 5; ++i)
            c = text.charAt (i);
    }

    /**
    Implementation of the auto-close hook method: we just print a line
    to the system output stream.
    */
    public void close ()
    {
        System.out.println ("I was closed automatically!");
    }
}

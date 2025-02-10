/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

import java.util.logging.*;

/**
A brief demonstration of J2SE standard logging.
*/
public class Chattering
{
    @SuppressWarnings("null")
    public static void main (String[] args)
    {
        try
        {
            log.fine ("Debugging just for us");
            log.info  ("Information just for us");

            root.fine ("A debugging message");
            root.info  ("An informational message");
            root.warning  ("A warning message");
            root.severe ("An error message");

            Object x = null;
            System.out.println (x.getClass ().getName ());
        }
        catch (Exception ex)
        {
            root.log (Level.SEVERE, "Exception", ex);
        }
    }

    static Logger root = Logger.getLogger ("");
    static Logger log = Logger.getLogger (Chattering.class.getName ());
}

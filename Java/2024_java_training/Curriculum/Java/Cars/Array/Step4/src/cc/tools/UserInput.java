/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
This class wraps console input in a utility method that can be called
synchronously.
*/
public class UserInput
{
    private static BufferedReader reader = new BufferedReader
        (new InputStreamReader (System.in));

    /**
    Wraps a "call" to the user to get a single string of input.
    Does this by reading a line from the standard input stream by way of
    a buffered reader.
    */
    public static String getString () 
    {
        try
        {
            return reader.readLine ();
        }
        catch (IOException ex) {}
        
        return "EXCEPTION";
    }
}


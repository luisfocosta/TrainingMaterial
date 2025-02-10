/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.files;

import java.io.*;

/**
Test application for {@link Snoop}.

@author Will Provost
*/
public class Poll
{
    public static void log (FolderEvent ev, String effect)
    {
        System.out.println ("File " + effect + " " + 
            ev.getSource () + ": " + ev.getFile ().getName ());
    }
    
    /**
    Creates a new {@link Snoop} for each directory passed as a command-line
    argument.  Wires one {@link #Handler Handler} to all sources.
    */
    public static void main (String[] args)
    {
        String[] toProcess = args.length == 0
            ? new String[] { "." }
            : args;
             
        try
        {
            for (String arg : toProcess)
            {
                System.out.println ("Polling directory " +
                    new File (arg).getCanonicalPath () + " ...");
                Snoop.startNew (arg, 
                    ev -> log (ev, "added to"),
                    ev -> log (ev, "changed in"),
                    ev -> log (ev, "removed from"));
            }
        }
        catch (IOException ex) 
        {
            ex.printStackTrace ();
        }
    }
}


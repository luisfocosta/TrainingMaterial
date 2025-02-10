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
    /**
    Creates a new {@link Snoop} for each directory passed as a command-line
    argument.  Wires one {@link #Handler Handler} to all sources.
    */
    public static void main (String[] args)
    {
        if (args.length == 0)
        {
            System.out.println ("Usage: java cc.files.Poll <dir1> ... <dirN>");
            System.exit (-1);
        }
        
        FolderListener handler = new Handler ();
        try
        {
            for (String arg : args)
            {
                System.out.println ("Polling directory " +
                    new File (arg).getCanonicalPath () + " ...");
                Snoop.startNew (arg, handler);
            }
        }
        catch (IOException ex) 
        {
            ex.printStackTrace ();
        }
    }
    
    private static class Handler
        implements FolderListener
    {
        public void fileAdded (FolderEvent ev)
        {
            System.out.println ("File added to " + ev.getSource () + 
                ": " + ev.getFile ().getName ());
        }

        public void fileChanged (FolderEvent ev)
        {
            System.out.println ("File changed in " + ev.getSource () + 
                ": " + ev.getFile ().getName ());
        }

        public void fileRemoved (FolderEvent ev)
        {
            System.out.println ("File removed from " + ev.getSource () + 
                ": " + ev.getFile ().getName ());
        }
    }
}


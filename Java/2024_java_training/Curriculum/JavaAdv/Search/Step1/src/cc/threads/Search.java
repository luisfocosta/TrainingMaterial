/*
Copyright 2005-2015 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.threads;

import java.io.File;
import java.io.IOException;

/**
This class searches for files of a given name under a given root directory.
The search is threaded so that the user can suspend or cancel it via
console input.

@author Will Provost
*/
public class Search
{
    private static final String CLEAR = "" + ((char) 13) + 
        "                                        " +
        "                                       " + ((char) 13);
        
    /**
    The search method looks for a file of the given name in the given
    directory, and if it is found it writes the full path of the file to the
    console.  It then recurses through any child directories.
    */
    public static void searchForFile (String filename, File path)
        throws IOException
    {
        File candidate = null;
        
        System.out.print (CLEAR);
        String display = path.getCanonicalPath ();
        if (display.length () > 60)
            display = "..." + display.substring (display.length () - 60);
        System.out.print ("Searching " + display);

        candidate = new File (path, filename);
        if (candidate.exists ())
        {
            System.out.print (CLEAR);
            System.out.println ("Found " + candidate.getCanonicalPath ());
        }        
        
        for (File child : path.listFiles ())
            if (child.isDirectory ())
                searchForFile (filename, child);
    }
    
    /**
    Application method reads filename and optional root directory
    (the default is the working directory), starts a {@link #Monitor},
    and then launches the search on the main thread.
    */
    public static void main (String[] args)
    {
        if (args.length == 0)
        {
            System.out.println ("Usage: java Search <filename> [<path>]");
            System.exit (-1);
        }
        
        String filename = args[0];
        File path = new File (args.length > 1 ? args[1] : ".");
        
        if (!path.isDirectory ())
        {
            System.out.println 
                ("Second argument must be a directory path.");
            System.exit (-1);
        }

        System.out.println ("Searching for file:  " + filename);
        System.out.println ("From root directory: " + path.getAbsolutePath ());
        System.out.println ("Hit ENTER to cancel ...");
        
        try
        {
            searchForFile (filename, path);
        }
        catch (IOException ex)
        {
            System.out.println ("IOException while searching.");
        }
    }
}

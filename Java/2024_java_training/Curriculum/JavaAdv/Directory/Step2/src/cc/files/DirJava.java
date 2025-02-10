/*
Copyright 1999-2011 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.files;

import java.io.*;

/**
This application simply lists the files in the current directory.

@author Will Provost
*/
public class DirJava
{
    public static void main (String[] args)
    {
        File currentDir = new File (args.length != 0 ? args[0] : ".");
        if (currentDir.isDirectory ())
        {
            File[] contents = currentDir.listFiles ();
            for (File file : contents)
                System.out.format ("%s  %12d  %s%n",
                    new java.util.Date (file.lastModified ()),
                    file.length (),
                    file.getName ());
        }
    }
}

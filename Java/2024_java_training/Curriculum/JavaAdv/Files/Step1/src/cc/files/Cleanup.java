/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
Utility to clean up backup copies of files.

@author Will Provost
*/
public class Cleanup
{
    private static final String BACKUP_SUFFIX = ".bak";

    /**
    Clean up the backup file at the given path.
    */
    public static void cleanup (Path path)
    {
        try
        {
            Files.delete (path);
        }
        catch (IOException ex)
        {
            System.out.println ("Couldn't remove file: " + path);
        }
    }

    /**
    Clean up all backup files, as recognized by a standard extension,
    under the given root path.
    */
    public static void cleanupAll (Path path)
    {
        try
        {
            Files.list (path)
                 .filter (p -> p.toFile ().isFile ())
                 .filter (p -> p.toString ().endsWith (BACKUP_SUFFIX))
                 .forEach (Cleanup::cleanup);
        }
        catch (IOException ex)
        {
            System.out.println ("Couldn't list files under " + path);
        }

        try
        {
            Files.list (path)
                 .filter (p -> p.toFile ().isDirectory ())
                 .forEach (Cleanup::cleanupAll);
        }
        catch (IOException ex)
        {
            System.out.println ("Couldn't recurse under " + path);
        }
    }

    /**
    Clean up all backup copies under a root path given by a command-line
    argument, or defaulted to the working directory.
    */
    public static void main (String[] args)
    {
        String root = args.length > 0 ? args[0] : ".";

        try
        {
            Cleanup.cleanupAll (Paths.get (root));
            System.out.println ("Removed backups from \"" + root + "\".");
        }
        catch (Exception ex)
        {
            System.out.println 
                ("Couldn't remove backups under \"" + root + "\".");
            ex.printStackTrace ();
        }
    }
}

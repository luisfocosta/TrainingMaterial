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
Utility to make backup copies of all files of a certain type.

@author Will Provost
*/
public class Backup
{
    private static final String BACKUP_SUFFIX = ".bak";

    private String suffix;

    /**
    Provide a standard suffix by which to recognize backup sources.
    */
    public Backup (String suffix)
    {
        this.suffix = suffix;
    }

    /**
    Create a backup copy of the file at the given path.
    */
    public void backup (Path path)
    {
        try
        {
            Files.copy (path, Paths.get (path.toString () + BACKUP_SUFFIX));
        }
        catch (IOException ex)
        {
            System.out.println ("Couldn't copy file: " + path);
        }
    }

    /**
    Walk the tree rooted at the given path, searching for files with
    the configured suffix or extension. {@link #backup Back up} each one.
    */
    public void backupAll (Path path)
    {
    }

    /**
    {@link #backupAll Back up all files} under a given root with a given
    suffix or extension.
    */
    public static void main (String[] args)
    {
        String root = args.length > 0 ? args[0] : ".";
        String suffix = args.length > 1 ? args[1] : ".dat";

        try
        {
            new Backup (suffix).backupAll (Paths.get (root));
            System.out.println ("Created backups of all " + suffix +
                " files under \"" + root + "\".");
        }
        catch (Exception ex)
        {
            System.out.println 
                ("Couldn't run backups under \"" + root + "\".");
            ex.printStackTrace ();
        }
    }
}

/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.files;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
Computes a checksum on a given file.

@author Will Provost
*/
public class Checksum
{
    /**
    Loads a file whose name is given on the command line, or a default name.
    Calculates a running checksum by streaming all lines of the file and
    all characters of each line.
    */
    public static void main (String[] args)
    {
        String source = args.length > 0 ? args[0] : "src/cc/files/Checksum.java";

        try
        {
          System.out.println ("Checksum for " + source + " is " +
          Files.lines (Paths.get (source))
               .flatMapToInt (line -> line.chars ())
               .reduce ((x,y) -> (byte) (x + y))
               .getAsInt ());
        }
        catch (Exception ex)
        {
            System.out.println ("Couldn't process " + source);
            ex.printStackTrace ();
        }
    }
}

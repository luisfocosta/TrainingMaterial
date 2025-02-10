/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.inventory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cc.license.Signer;
import cc.util.ListSorter;

/**
Merges inventory files from multiple locations.
Uses adapters in {@link cc.util} to adapt multiple arrays of strings to 
{@link cc.license.Signer Signer}, which requires a single iterator.

@author Will Provost
*/
public class MergeInventories
{
    /**
    Application method calls {@link #getAllSubpaths getAllSubpaths}, passing
    the command-line arguments as root paths; then calls 
    {@link cc.license.Signer#sign Signer.sign} to compile a single list and 
    stamp a signature on it.  Prints out the resulting signed "message."
    */
    public static void main (String[] args)
        throws Exception
    {
        final String FOLDER = "inventory";
        final String[] FILENAMES = { "Abingdon", "Cuddesdon", "Oxford" };

        long startTime = System.currentTimeMillis ();
        
        Path folder = Paths.get (FOLDER);
        
        List<Iterator<? extends String>> iterators = new ArrayList<> ();
        for (int f = 0; f < FILENAMES.length; ++f)
            iterators.add 
                (Files.lines (folder.resolve (FILENAMES[f])).iterator ());
        
        ListSorter<String> sorter = new ListSorter<String> (iterators);
        
        Signer signer = new Signer ("inventory/Complete");
        int records = signer.sign (sorter);
        
        System.out.format ("Processed %d records in %1.3f seconds.%n", records,
            ((double) (System.currentTimeMillis () - startTime)) / 1000);
    }
}


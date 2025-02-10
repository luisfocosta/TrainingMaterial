/*
Copyright 2004-2011 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.files;

import java.io.File;

/**
Utility that analyzes a directory tree recursively.  
Reads total number of files, total size of file set in bytes, and 
breaks down immediate children (directories and files) to percentage of
total bytes used.

@author Will Provost
*/
public class Analysis
{
    private long bytes;
    private long files;
    private File target;

    /**
    Constructor sets the target property and then
    calls the {@link runAnalysis runAnalysis} method.
    */
    public Analysis (File target)
    {
        this.target = target;
        runAnalysis ();
    }

    /**
    Accessor for the <code>bytes</code> property.
    */
    public long getBytes ()
    {
        return bytes;
    }

    /**
    Accessor for the <code>files</code> property.
    */
    public long getFiles ()
    {
        return files;
    }

    /**
    Accessor for the <code>target</code> property.
    */
    public File getTarget ()
    {
        return target;
    }
    
    /**
    Count the objects in the directory, and get the sizes of each.
    This recurses through new calls to this method for each child object.
    Or, if this is a file, simply report size and "1" for count,
    and don't recurse.
    */
    protected void runAnalysis ()
    {
        if (target.isFile ())
        {
            bytes = target.length ();
            files = 1;
            return;
        }

        String[] contents = target.list ();
        bytes = files = 0;
        Analysis[] childProps = new Analysis[contents.length];
        for (int f = 0; f < contents.length; ++f)
        {
            File child = new File (target, contents[f]);
            childProps[f] = new Analysis (child);
            bytes += childProps[f].getBytes ();
            files += childProps[f].getFiles ();
        }
    }

    /**
    Create an instance on the passed directory, or the working directory
    by default, and then report on total files, total bytes used,
    and percentage used by each direct child of the root directory.
    */
    public static void main (String[] args)
    {
        Analysis top = new Analysis 
            (new File (args.length != 0 ? args[0] : "."));
            
        System.out.format ("Size : %9d", top.getBytes ());
        System.out.println ();
        System.out.format ("Files: %9d", top.getFiles ());
        System.out.println ();
    }
}

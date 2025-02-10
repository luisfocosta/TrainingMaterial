/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.files;

import java.io.*;

/**
Event that captures a change to a directory: added, removed, or updated 
file.

@author Will Provost
*/
public class FolderEvent
{
    private File file;
    private String source;
    private long detected;

    /**
    Pass the file in question and the system time at which the change was
    first detected.
    */
    public FolderEvent (File file, String source, long detected)
    {
        this.file = file;
        this.source = source;
        this.detected = detected;
    }

    /**
    Accessor for the file in question.
    */
    public File getFile ()
    {
        return file;
    }
    
    /**
    Accessor for the source directory.
    */
    public String getSource ()
    {
        return source;
    }
    
    /**
    Accessor for the time of detection.
    */
    public long getWhenDetected ()
    {
        return detected;
    }
}


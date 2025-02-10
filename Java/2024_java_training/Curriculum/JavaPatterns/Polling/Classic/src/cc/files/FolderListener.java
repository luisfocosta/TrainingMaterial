/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.files;

/**
Listener interface for {@link FolderEvent}s.

@author Will Provost
*/
public interface FolderListener
{
    /**
    Fired when a file is added to a directory under surveillance.
    */
    public void fileAdded (FolderEvent ev);
    
    /**
    Fired when a file is changed or updated in a directory under surveillance.
    */
    public void fileChanged (FolderEvent ev);
    
    /**
    Fired when a file is removed from a directory under surveillance.
    */
    public void fileRemoved (FolderEvent ev);
}


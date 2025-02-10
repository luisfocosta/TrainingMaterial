/*
Copyright 2005, 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.files;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
Utility that polls a given directory for changes and fires
{@link FolderListener folder events}.

@author Will Provost
*/
public class Snoop
{
    private static final int INTERVAL = 1000; // msec

    private File path;
    private Timer timer;

    private List<FolderListener> listeners = new LinkedList<> ();
    private Map<String,Long> contents = new HashMap<> ();

    /**
    Pass the target path, which must be a directory.
    */
    public Snoop (String path)
    {
        this.path = new File (path);

        captureImage (false);
    }

    /**
    Start monitoring.
    */
    public void start ()
    {
        timer = new Timer ();
        timer.schedule (new TimerTask ()
            {
                public void run  ()
                {
                    captureImage (true);
                }
            }, INTERVAL, INTERVAL);
    }

    /**
    Stop monitoring.
    */
    public void stop ()
    {
        timer.cancel ();
    }

    /**
    Register a folder listener.
    */
    public synchronized void addFolderListener (FolderListener listener)
    {
        listeners.add (listener);
    }

    /**
    Un-register a folder listener.
    */
    public synchronized void removeFolderListener (FolderListener listener)
    {
        listeners.remove (listener);
    }

    /**
    Convenience method to start a new thread on this runnable object.
    */
    public static Snoop startNew (String path, FolderListener listener)
    {
        Snoop result = new Snoop (path);
        result.addFolderListener (listener);
        result.start ();
        return result;
    }

    /**
    Helper method for firing events: returns a thread-safe copy of the
    listeners list.
    */
    private synchronized List<FolderListener> getRecipients ()
    {
        return new ArrayList<FolderListener> (listeners);
    }

    /**
    Helper to fire events for added files.
    */
    private void fireAddedEvent (File file)
    {
        FolderEvent ev = new FolderEvent
            (file, path.getName (), System.currentTimeMillis ());
        for (FolderListener recipient : getRecipients ())
            recipient.fileAdded (ev);
    }

    /**
    Helper to fire events for updated/replaced files.
    */
    private void fireChangedEvent (File file)
    {
        FolderEvent ev = new FolderEvent
            (file, path.getName (), System.currentTimeMillis ());
        for (FolderListener recipient : getRecipients ())
            recipient.fileChanged (ev);
    }

    /**
    Helper to fire events for removed files.
    */
    private void fireRemovedEvent (File file)
    {
        FolderEvent ev = new FolderEvent
            (file, path.getName (), System.currentTimeMillis ());
        for (FolderListener recipient : getRecipients ())
            recipient.fileRemoved (ev);
    }

    /**
    Creates a new map of filename to modified time, and in the process fires
    events if the <code>compare</code> flag is set.
    */
    private void captureImage (boolean compare)
    {
        Map<String,Long> newContents = new HashMap<String,Long> ();
        synchronized (contents)
        {
            for (File file : path.listFiles ())
            {
                if (compare)
                {
                    if (!contents.containsKey (file.getName ()))
                        fireAddedEvent (file);
                    else if (contents.get (file.getName ()) <
                                        file.lastModified ())
                        fireChangedEvent (file);
                }

                contents.remove (file.getName ());
                newContents.put (file.getName (), file.lastModified ());
            }

            if (compare)
                for (String name : contents.keySet ())
                    fireRemovedEvent (new File (name));
        }

        contents = newContents;
    }
}


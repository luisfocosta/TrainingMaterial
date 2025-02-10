/*
Copyright 2005 Will Provost.
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
import java.util.TreeMap;
import java.util.function.Consumer;

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
    
    public enum EventType { ADD, CHANGE, REMOVE };
    
    private Map<EventType,List<Consumer<FolderEvent>>> listeners =
        new TreeMap<> ();
    private Map<String,Long> contents = new HashMap<> ();

    /**
    Pass the target path, which must be a directory.
    */
    public Snoop (String path)
    {
        this.path = new File (path);
        
        for (EventType eventType : EventType.values ())
            listeners.put 
                (eventType, new LinkedList<Consumer<FolderEvent>> ());
        
        captureImage (false);
    }
    
    /**
    Register a folder listener.
    */
    public synchronized void addListener 
        (EventType eventType, Consumer<FolderEvent> listener)
    {
        listeners.get (eventType).add (listener);
    }
    
    /**
    Un-register a folder listener.
    */
    public synchronized void removeListener 
        (EventType eventType, Consumer<FolderEvent> listener)
    {
        listeners.get (eventType).remove (listener);
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
    Convenience method to start a new thread on this runnable object.
    */
    public static Snoop startNew (String path, 
        Consumer<FolderEvent> addListener, 
        Consumer<FolderEvent> changeListener, 
        Consumer<FolderEvent> removeListener)
    {
        Snoop result = new Snoop (path);
        
        if (addListener != null)
            result.addListener (EventType.ADD, addListener);
        if (changeListener != null)
            result.addListener (EventType.CHANGE, changeListener);
        if (removeListener != null)
            result.addListener (EventType.REMOVE, removeListener);
        
        result.start ();
        return result;
    }

    /**
    Helper method for firing events: returns a thread-safe copy of the
    listeners list.
    */
    private synchronized List<Consumer<FolderEvent>> clone 
        (EventType eventType)
    {
        return new ArrayList<Consumer<FolderEvent>> 
            (listeners.get (eventType));
    }
    
    /**
    Helper to fire events for added files.
    */
    private void fireEvent (File file, EventType eventType)
    {
        FolderEvent ev = new FolderEvent 
            (file, path.getName (), System.currentTimeMillis ());
        for (Consumer<FolderEvent> recipient : clone (eventType))
            recipient.accept (ev);
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
                        fireEvent (file, EventType.ADD);
                    else if (contents.get (file.getName ()) <
                                        file.lastModified ())
                        fireEvent (file, EventType.CHANGE);
                }

                contents.remove (file.getName ());
                newContents.put (file.getName (), file.lastModified ());
            }

            if (compare)
                for (String name : contents.keySet ())
                    fireEvent (new File (name), EventType.REMOVE);
        }
        
        contents = newContents;
    }
}


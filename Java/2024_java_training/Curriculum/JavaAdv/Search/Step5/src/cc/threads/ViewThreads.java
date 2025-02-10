/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.threads;

/**
This application browses the thread tree of its own JVM.

@author Will Provost
*/
public class ViewThreads
{
    /**
    Recursive method to show a single thread group; iterate over its threads
    and call {@link #showThread showThread}; and iterate over its subgroups
    and show them via this method.
    */
    public static void showGroup (ThreadGroup group, String indent)
    {
        System.out.println (indent + "  " + group.getName ());
        Thread[] threads = new Thread[group.activeCount ()];
        int nThreads = group.enumerate (threads, false);
        for (int i = 0; i < nThreads; ++i)
            showThread (threads[i], indent + "  ");
        
        ThreadGroup[] groups = new ThreadGroup[group.activeGroupCount ()];
        int nGroups = group.enumerate (groups, false);
        for (int i = 0; i < nGroups; ++i)
            showGroup (groups[i], indent + "  ");
    }
    
    /**
    Shows the given thread by name, marking it if it is the current thread.
    */
    public static void showThread (Thread thread, String indent)
    {
        System.out.print (indent + 
            (thread == Thread.currentThread () ? "* " : "  "));
        System.out.println (thread.getName ());
    }
    
    
    /**
    Start with the current thread's group, walk the parent axis, and then 
    call {@link @showGroup showGroup}.
    */
    public static void showAllThreads ()
    {
        ThreadGroup group = Thread.currentThread ().getThreadGroup ();
        while (group.getParent () != null)
            group = group.getParent ();
            
        showGroup (group, "");
    }
    
    /**
    Trigger a dump of all threads, and exit.
    */
    public static void main (String[] args)
    {
        showAllThreads ();
    }
}


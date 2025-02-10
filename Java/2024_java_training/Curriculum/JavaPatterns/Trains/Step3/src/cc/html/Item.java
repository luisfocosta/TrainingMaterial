/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.html;

import java.io.*; 

/**
Interface for a component that can produce a single item in a set.

@author Will Provost
*/
public interface Item
{
    /**
    Write the item content.
    */
    public void writeItem (PrintWriter out, Train train)
        throws IOException;
}


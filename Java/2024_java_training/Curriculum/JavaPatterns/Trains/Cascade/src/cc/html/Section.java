/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.html;

import java.io.*;
import java.util.List;

/**
Interface for a section of an HTML page.

@author Will Provost
*/
public interface Section
{
    /**
    Write the header or starting content.
    */
    public void writeHeader (PrintWriter out, List<Train> data)
        throws IOException;

    /**
    Write the footer or ending content.
    */
    public void writeFooter (PrintWriter out, List<Train> data)
        throws IOException;
        
    /**
    Factory method for items that work in this section.
    */
    public Item createItem ();
}


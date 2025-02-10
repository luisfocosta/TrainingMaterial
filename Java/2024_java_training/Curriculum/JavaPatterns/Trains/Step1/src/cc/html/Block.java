/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.html;

import java.io.*;
import java.util.List;

/**
Interface for a component that can produce a header and footer section.

@author Will Provost
*/
public interface Block
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
}


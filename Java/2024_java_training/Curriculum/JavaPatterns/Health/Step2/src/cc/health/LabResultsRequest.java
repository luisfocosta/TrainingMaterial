/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.health;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
Writes a request for lab test results.

@author Will Provost
*/
public class LabResultsRequest
    extends InfoRequest
{
    private Iterable<String> codes;
    private String dateRequested;

    /**
    Constructor passes most arguments along to the 
    {@link InfoRequest#InfoRequest superclass constructor};
    saves lab codes and date of test request.
    */
    public LabResultsRequest (String requestorName, String requestorEMail, 
        String subjectID, String credentials, Iterable<String> codes,
        String dateRequested)
    {
        super (requestorName, requestorEMail, subjectID, credentials);
      
        this.codes = codes;
        this.dateRequested = dateRequested;
    }
    
    /**
    Adds a sentence stating the date of the original request for lab tests.
    */
    protected void writeNotes (PrintWriter writer)
    {
        writer.println 
            ("Lab tests were ordered by this office on " + dateRequested);
    }
    
    /**
    Writes fields for information requested and specific lab codes.
    */
    protected void writeRequestDetails (PrintWriter writer)
    {
        writer.println ("Information requested: Lab test results");
        writer.println ("Test codes:");
        for (String code : codes)
            writer.println ("    " + code);
        writer.println ();
    }
    
    /**
    Application method for testing purposes only:
    instantiates the class with hardcoded information, and calls
    {@link #writeRequest writeRequest}.
    */
    public static void main (String[] args)
    {
        ArrayList<String> codes = new ArrayList<String> ();
        Collections.addAll (codes, "7033", "0085X", "21-889");
        InfoRequest helper = new LabResultsRequest ("Dr. Demento", 
            "demento@whatsit.com", "Will Provost", "quicksand", 
            codes, "10/10/2005");
        helper.writeRequest (System.out);
    }
}


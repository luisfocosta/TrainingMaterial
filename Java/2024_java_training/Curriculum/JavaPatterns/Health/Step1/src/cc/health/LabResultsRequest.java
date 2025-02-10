/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.health;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
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
    Writes a request email of a prescribed format, using helper methods in
    the base class.
    */
    public void writeRequest (OutputStream out)
    {
        PrintWriter writer = new PrintWriter (new OutputStreamWriter (out));

        writeSMTPHeader (writer);
        
        // Buffer the upcoming content:
        StringWriter capture = new StringWriter ();
        PrintWriter buffer = new PrintWriter (capture);
        
        writeIntroParagraph (buffer);

        // This should go in writeNotes ():
        buffer.println 
            ("Lab tests were ordered by this office on " + dateRequested);

        writeSignoff (buffer);
        writeSubjectInfo (buffer);

        // This should go in writeRequestDetails ():
        buffer.println ("Information requested: Lab test results");
        buffer.println ("Test codes:");
        for (String code : codes)
            buffer.println ("    " + code);
        buffer.println ();

        // Capture the content so far, flush it through to the main writer,
        // and generate the digital signature:
        String signedContent = capture.toString ();
        writer.print (signedContent);
        
        writeSignature (writer, signedContent);
        writeSMTPTerminator (writer);
        
        writer.flush ();
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


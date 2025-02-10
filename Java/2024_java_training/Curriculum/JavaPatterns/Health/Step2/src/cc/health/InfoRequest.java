/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.health;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;

/**
Base type for subclasses that write requests for health-care information.
Implements {@link #writeRequest writeRequest} with the use of template methods
{@link #writeNotes writeNotes} and 
{@link #writeRequestDetails writeRequestDetails}.

@author Will Provost
*/
public abstract class InfoRequest
{
    protected String requestorName;
    protected String requestorEMail;
    protected String subjectID;
    protected String credentials;

    /**
    Construct with common features including requestor and subject 
    information.
    */
    protected InfoRequest (String requestorName, String requestorEMail, 
        String subjectID, String credentials)
    {
        this.requestorName = requestorName;
        this.requestorEMail = requestorEMail;
        this.subjectID = subjectID;
        this.credentials = credentials;
    }
    
    /**
    Writes a request email of a prescribed format, including a digital
    signature, with the aid of template methods {@link #writeNotes writeNotes}
    and {@link #writeRequestDetails writeRequestDetails}.
    */
    public void writeRequest (OutputStream out)
    { 
        PrintWriter writer = new PrintWriter (new OutputStreamWriter (out));

        writer.println ("MAIL FROM:<request@healthcare_r_us.com>");
        writer.println ("RCPT TO:" + requestorEMail);
        writer.println ("DATA");

        // Start buffering output so that we can capture this segment
        // for a digital signature:
        StringWriter capture = new StringWriter ();
        PrintWriter buffer = new PrintWriter (capture);
    
        buffer.println ("We hereby request the following patient data, ");
        buffer.println ("with credentials and authorizations as shown below.");
        buffer.println ();

        writeNotes (buffer);

        buffer.println ("If you have any questions, please contact our office.");
        buffer.println ();
        buffer.println ("Thank you,");
        buffer.println (requestorName);
        buffer.println ();

        buffer.println ("Subject ID: " + subjectID);
        buffer.println ("Credentials: " + credentials);

        writeRequestDetails (buffer);

        // Capture the content so far, flush it through to the main writer,
        // and generate the digital signature:
        String signedContent = capture.toString ();
        writer.print (signedContent);
    
        writer.println ("BEGIN_SIGNATURE");
        
        try ( FileInputStream in = new FileInputStream("KeyStore"); )
        {
            KeyStore store = 
                KeyStore.getInstance (KeyStore.getDefaultType ());
            store.load (in, "ccstudent".toCharArray ());
  
            KeyStore.PrivateKeyEntry entry = (KeyStore.PrivateKeyEntry)
                store.getEntry ("Capstone", new KeyStore.PasswordProtection 
                    ("ccstudent".toCharArray ()));
            PrivateKey key = entry.getPrivateKey ();
  
            Signature signer = Signature.getInstance ("SHA1withDSA");
            signer.initSign (key);
            signer.update (signedContent.getBytes ());
            byte[] signature = signer.sign (); 
            for (byte b : signature)
               writer.print (Integer.toHexString ((b + 256) % 256));
        }
        catch (IOException ex)
        {
            System.out.println ("IOException trying to load key pair.");
            System.out.println ("Is there a file \"KeyStore\"?");
            System.out.println ();
            writer.print ("Signature failed.");
        }
        catch (GeneralSecurityException ex)
        {
            System.out.println ("Key can't be loaded from keystore.");
            System.out.println ();
            writer.print ("Signature failed.");
        }

        writer.println ();
        writer.println ("END_SIGNATURE");
        writer.println ();

        writer.println (".");

        writer.flush ();
    }

    /**
    Template method allowing the subclass to write additional information
    into the message text.
    */
    protected abstract void writeNotes (PrintWriter out);
    
    /**
    Template method allowing the subclass to write additional information
    into the detail section of the message.
    */
    protected abstract void writeRequestDetails (PrintWriter out);

}

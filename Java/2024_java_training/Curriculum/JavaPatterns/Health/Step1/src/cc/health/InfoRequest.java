/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.health;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;

/**
Base type for subclasses that write requests for health-care information.
Provides helper methods that can be assembled by a subclass to implement
the primary {@link #writeRequest public method}.

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
    Writes a request email of a prescribed format.
    */
    public abstract void writeRequest (OutputStream out);
    
    protected void writeSMTPHeader (PrintWriter writer)
    { 
        writer.println ("MAIL FROM:<request@healthcare_r_us.com>");
        writer.println ("RCPT TO:" + requestorEMail);
        writer.println ("DATA");
    }
    
    /**
    Writes a stock introductory paragraph.
    */
    protected void writeIntroParagraph (PrintWriter writer)
    {
        writer.println ("We hereby request the following patient data, ");
        writer.println ("with credentials and authorizations as shown below.");
        writer.println ();
    }
    
    /**
    Writes a signoff paragraph and the name of the requestor.
    */
    protected void writeSignoff (PrintWriter writer)
    {
        writer.println ("If you have any questions, please contact our office.");
        writer.println ();
        writer.println ("Thank you,");
        writer.println (requestorName);
        writer.println ();
    }
    
    /**
    Writes the patient info, including credentials showing patient 
    approval of the request.
    */
    protected void writeSubjectInfo (PrintWriter writer)
    {
        writer.println ("Subject ID: " + subjectID);
        writer.println ("Credentials: " + credentials);
    }
    
    /**
    Writes a digital signature based on provided content to be signed,
    and a prepared key in a keystore file.
    */
    protected void writeSignature (PrintWriter writer, String signedContent)
    {
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
    }

    /**
    Writes a terminal string to end the mail message.
    */
    protected void writeSMTPTerminator (PrintWriter writer)
    {
        writer.println (".");
    }
}

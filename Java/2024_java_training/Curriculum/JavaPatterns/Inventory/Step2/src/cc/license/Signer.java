/*
Copyright 2005, 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.license;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;


/**
Utility that digitally signs a list of strings.

@author Will Provost
*/
public class Signer
{
    private int bufferSize = 4096;
    private String filename;
    
    /**
    Create a signer with a destination filename.
    */
    public Signer (String filename)
    {
        this.filename = filename;
    }
    
    /**
    Produce strings as lines in a text file, and then 
    sign using provided key.
    */
    public int sign (Iterable<String> list)
    {
        final String endLine = System.getProperty ("line.separator");
        
        Signature signer = null;
        try ( FileInputStream in = new FileInputStream("KeyStore"); )
        {
            KeyStore store = 
                KeyStore.getInstance (KeyStore.getDefaultType ());
            store.load (in, "ccstudent".toCharArray ());
  
            KeyStore.PrivateKeyEntry entry = (KeyStore.PrivateKeyEntry)
                store.getEntry ("Capstone", new KeyStore.PasswordProtection 
                    ("ccstudent".toCharArray ()));
            PrivateKey key = entry.getPrivateKey ();
  
            signer = Signature.getInstance ("SHA1withDSA");
            signer.initSign (key);
        }
        catch (IOException ex)
        {
            System.out.println ("IOException trying to load key pair.");
            System.out.println ("Is there a file \"KeyStore\"?");
            System.out.println ();
            
            return 0;
        }
        catch (GeneralSecurityException ex)
        {
            System.out.println ("Key can't be loaded from keystore.");
            System.out.println ();
            
            return 0;
        }

        int records = 0;
        StringBuilder result = new StringBuilder ();
        try ( PrintWriter out = new PrintWriter (new FileWriter (filename)); )
        {
            for (String name : list)
            {
                ++records;
                result.append (name).append (endLine);
                if (result.length () > bufferSize)
                {
                    out.print (result.toString ());
                    out.flush ();
                    
                    signer.update (result.toString ().getBytes ());
                    
                    result = new StringBuilder ();
                }
            }
            
            result.append (endLine)
                  .append ("BEGIN SIGNATURE")
                  .append (endLine);
    
            byte[] signature = signer.sign (); 
            for (byte b : signature)
               result.append (Integer.toHexString ((b + 256) % 256));
            
            result.append (endLine)
                  .append ("END SIGNATURE")
                  .append (endLine);
            
            out.print (result.toString ());
        }
        catch (IOException ex)
        {
            System.out.println ("Couldn't write file.");
            ex.printStackTrace ();
            System.out.println ();
        }
        catch (SignatureException ex)
        {
            System.out.println ("Couldn't complete digital signature.");
            ex.printStackTrace ();
            System.out.println ();
        }
        
        return records;
    }
}


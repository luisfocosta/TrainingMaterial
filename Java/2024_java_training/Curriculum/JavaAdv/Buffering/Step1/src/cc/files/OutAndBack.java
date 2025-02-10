/*
Copyright 2004-2011 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.files;

import java.io.*;

/**
Produces a file of 1,000,000 bytes, then reads it back, then deletes it.
Does this twice, once without buffering and once with it, and compares
the performance.

@author Will Provost
*/
public class OutAndBack
{
    public static void main (String[] args)
    {
        try
        {
            final int LENGTH = 1000000;
            long start;
            
            start = System.currentTimeMillis ();
            System.out.print ("Writing file ... ");
            try (OutputStream out = new FileOutputStream ("test.dat"); )
            {
                for (int i = 0; i < LENGTH; ++i)
                    out.write (i);
            }
            System.out.println ("Done in " + 
                (System.currentTimeMillis () - start) + " milliseconds.");

            start = System.currentTimeMillis ();
            System.out.print ("Reading file ... ");
            try (InputStream in = new FileInputStream ("test.dat"); )
            {
                for (int i = 0; i < LENGTH; ++i)
                    in.read ();
            }
            System.out.println ("Done in " + 
                (System.currentTimeMillis () - start) + " milliseconds.");

            new File ("test.dat").delete ();
        }
        catch (Exception ex)
        {
            ex.printStackTrace ();
        }
    }
}

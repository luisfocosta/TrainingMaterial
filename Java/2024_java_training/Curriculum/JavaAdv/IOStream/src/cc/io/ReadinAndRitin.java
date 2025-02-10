/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
Exercises the I/O Streams API, using byte-array streams first to write some
information into memory, and then read it back.

@author Will Provost
*/
public class ReadinAndRitin
{
    /**
    Write a sequence of characters to a ByteArrayOutputStream.
    Then read them back, variously to standard output and a byte-array buffer.
    */
    public static void main (String[] args)
    {
        final byte[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes ();

        try
        (
            ByteArrayOutputStream out = new ByteArrayOutputStream ();
        )
        {
            out.write (97);
            out.write (98);
            out.write (99);
            out.write (' ');
            out.write (alphabet);
            out.write (' ');
            out.write (alphabet, 10, 10);
            out.write (' ');
            out.write (120);
            out.write (121);
            out.write (122);

            byte[] buffer = out.toByteArray ();
            byte[] destination = new byte[26];
            try
            (
                ByteArrayInputStream in = new ByteArrayInputStream (buffer);
            )
            {
                System.out.println ("First three characters:");
                for (int i = 0; i < 3; ++i)
                    System.out.print ((char) in.read ());
                System.out.println ();
                in.read ();

                System.out.println ("Uppercase alphabet:");
                in.read (destination);
                System.out.println (new String (destination));
                in.read ();

                if (in.markSupported ())
                    in.mark (-1); // Doesn't matter for byte streams

                System.out.println ("Next ten characters:");
                for (int i = 0; i < 10; ++i)
                    System.out.print ((char) in.read ());
                System.out.println ();
                in.read ();

                in.read (destination, 23, 3);
                System.out.println ("Mixed alphabet:");
                System.out.println (new String (destination));

                // And 'rithmetic!
                if (in.markSupported ())
                {
                    in.reset ();

                    System.out.println ("Same ten characters, modified:");
                    for (int i = 0; i < 10; ++i)
                        System.out.print ((char) (in.read () + 32));
                }
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace ();
        }
    }
}

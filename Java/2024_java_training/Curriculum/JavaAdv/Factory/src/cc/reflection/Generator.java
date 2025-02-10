/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.reflection;

import java.io.*;

/**
This class was used to generate the data files consumed by the
{@link Factory} class.

@author Will Provost
*/
public class Generator
{
    /**
    Using a single source phrase, this method writes out a list of the tokens
    in the phrase to one file "Strings.ser", and a list of the lengths of
    those tokens to another file "Numbers.ser".
    */
    public static void main (String[] args)
    {
        try
        (
            ObjectOutputStream out1 = new ObjectOutputStream
                (new FileOutputStream ("Strings.ser"));
            ObjectOutputStream out2 = new ObjectOutputStream
                (new FileOutputStream ("Numbers.ser"));
        )
        {
            String source = "Those are pearls that were his eyes";
            String[] tokens = source.split (" ");

            out1.writeInt (tokens.length);
            out2.writeInt (tokens.length);
            for (String token : tokens)
            {
                out1.writeObject (token);
                out2.writeObject (Integer.valueOf (token.length ()));
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace ();
        }
    }
}

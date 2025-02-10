/*
Copyright 2004-2011 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

import java.io.*;
import java.util.*;
import java.text.*;

/**
This class records test results and can read and write to a given file
as well as read from the user and print to the console.
A simple command-line interface allows creation of new records or
reading out old records.

@author Will Provost
*/
public class DNA
{
    private static final char[] map = { 'A', 'C', 'G', 'T' };
    private static final DateFormat dateFormat = 
        DateFormat.getDateInstance (DateFormat.LONG);
    private static final DateFormat timeFormat = 
        DateFormat.getTimeInstance (DateFormat.SHORT);

    private String codons;
    private String name;
    private Date whenSequenced;
    private double results = Double.NaN;

    /**
    Read lines of input from the console to initialize all fields.
    Then get a filename from the user and call 
    {@link writeToFile writeToFile}.
    */
    public void readFromUser ()
    {
        try (BufferedReader in = new BufferedReader 
                (new InputStreamReader (System.in)); )
        {
            whenSequenced = new Date ();
            System.out.println ("Creating a new test record - "
                + dateFormat.format (whenSequenced) + ", " 
                + timeFormat.format (whenSequenced));
            System.out.println ("Test name:");
            name = in.readLine ();
            System.out.println ("Codons:");
            codons = in.readLine ();
            
            while (Double.isNaN (results))
                try
                {
                    System.out.println ("Results:");
                    results = Double.parseDouble (in.readLine ());
                }
                catch (NumberFormatException ex) 
                {
                    System.out.println ("Results must be a number.");
                }

            System.out.println ("Filename:");
            writeToFile (in.readLine ());
        }
        catch (IOException ex)
        {
            ex.printStackTrace ();
        }
    }
    
    /**
    Print the test record to the console.
    */
    public void print ()
    {
        System.out.println ("Test \"" + name + "\"");
        System.out.println ("  Sequenced " + dateFormat.format (whenSequenced)
            + ", " + timeFormat.format (whenSequenced));
        System.out.println ("  Codons: " + codons);
        System.out.println ("  Results: " + results);
    }
    
    /**
    Read the test record from the given filename.  Uses a DataInputStream
    to read formatted fields, including a compressed byte array which is
    passed to {@link decode decode} to get the full string of codons.
    */
    public void readFromFile (String filename)
        throws IOException
    {
    }
    
    /**
    Writes the record to a given file using a DataOutputStream.
    Calls {@link encode encode} to compress the codon string and writes
    that as a byte array.
    */
    public void writeToFile (String filename)
        throws IOException
    {
    }
    
    /**
    The algorithm to compress the codon string to a byte array.
    */
    private byte[] encode ()
    {
        byte[] compressed = new byte[(codons.length () + 3) / 4];
        for (int a = 0; a < codons.length (); ++a)
        {
            int index = a / 4;
            int rotation = (a % 4) * 2;
            int find = 0;
            while (find < map.length && map[find] != codons.charAt (a))
                ++find;
            
            compressed[index] |= find << rotation;
        }
        
        return compressed;
    }
    
    /**
    The algorithm to decode the compressed byte array.  Needs the expected
    length because this encoding doesn't capture whether the string ran
    out to fill the final byte exactly; the last few bits could be
    significant or they could be junk.
    */
    private void decode (byte[] compressed, int length)
    {
        StringBuffer reconstituted = new StringBuffer (compressed.length * 4);
        for (int b = 0; b < compressed.length; ++b)
            for (int r = 0; r < 8; r += 2)
                reconstituted.append (map[(compressed[b] & (3 << r)) >> r]);
        
        codons = reconstituted.substring (0, length);
    }
    
    /**
    If the user doesn't provide an argument, calls 
    {@link readFromUser readFromUser}.  If an argument is provided it's
    taken as a filename and passed to {@link readFromFile readFromFile},
    and then the record is printed to the console using {@link print print}.
    */
    public static void main (String[] args)
    {
        DNA dna = new DNA ();
        if (args.length != 0)
            try
            {
                dna.readFromFile (args[0]);
                dna.print ();
            }
            catch (IOException ex)
            {
                ex.printStackTrace ();
            }
        else
            dna.readFromUser ();
    }
}


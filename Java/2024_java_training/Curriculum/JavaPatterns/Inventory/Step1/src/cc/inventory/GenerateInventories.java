/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.inventory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.security.SecureRandom;
import java.util.Random;

/**
Generates a set of inventory files, each individually sorted and with
distinct part numbers.

@author Will Provost
*/
public class GenerateInventories
{
    public static void main (String[] args)
        throws Exception
    {
        final String FOLDER = "inventory";
        final String[] FILENAMES = { "Abingdon", "Cuddesdon", "Oxford" };
        
        final int BASE = 26;
        final int SIZE = 6;
        final int TOTAL = args.length != 0 
            ? Integer.parseInt (args[0]) 
            : 1000000;
        
        int maxIncrement = 1;
        for (int i = 0; i < SIZE; ++i)
            maxIncrement *= BASE;
        maxIncrement = (int) ((maxIncrement / TOTAL) * 1.9);
        
        final Random generator = new SecureRandom ();

        int[] numbers = new int[SIZE];
        byte[] part = new byte[SIZE];
        
        PrintStream[] files = new PrintStream[FILENAMES.length];
        try
        {
            File folder = new File (FOLDER);
            folder.mkdir ();
            for (int f = 0; f < files.length; ++f)
                files[f] = new PrintStream (new BufferedOutputStream 
                    (new FileOutputStream (new File (folder, FILENAMES[f])),
                        8192));
            
            int file = 0;

            for (int r = 0; r < TOTAL; ++r)
            {
                numbers[SIZE - 1] += generator.nextDouble () * maxIncrement;
                
                for (int p = SIZE - 1; p >= 0; --p)
                {
                    int over = numbers[p] / BASE;
                    if (p > 0)
                        numbers[p - 1] += over;
                    numbers[p] -= over * BASE;
                    
                    part[p] = (byte) (numbers[p] + 'A');
                }
                
                file = (file + (int) (generator.nextDouble () * files.length)) 
                    % files.length;
                
                files[file].write (part);
                files[file].format 
                    ("%4d%n", (int) (generator.nextDouble () * 1000));
            }
        }
        catch (IOException ex)
        {
            System.out.println ("Couldn't write inventory file.");
            ex.printStackTrace ();
        }
        finally
        {
            for (int f = 0; f < files.length; ++f)
                files[f].close ();
        }
    }
}

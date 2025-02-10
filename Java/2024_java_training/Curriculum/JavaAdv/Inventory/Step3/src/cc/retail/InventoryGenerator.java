/*
Copyright 2000-2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.retail;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
Utility to generate inventory data files of various sizes.

@author Will Provost
*/
public class InventoryGenerator
    implements AutoCloseable
{
    private DataOutputStream out;
    private static final long WIDTH = 308915776L; // 26 to the 6th power

    /**
    Wrap the given stream in a data output stream.
    */
    public InventoryGenerator (OutputStream out)
        throws IOException
    {
        this.out = new DataOutputStream (out);
    }
    
    /**
    Close the underlying stream.
    */
    public void close ()
        throws IOException
    {
        out.close ();
    }
    
    /**
    Encode the part number as six uppercase letters.
    */
    private void encodePartID (long partNumber)
        throws IOException
    {
        for (int c = 0; c < 6; ++c)
        {
            out.writeByte ((char) (partNumber % 26 + 'A'));
            partNumber /= 26;
        }
    }
    
    /**
    Move to the next part number in a deterministic sequence.
    */
    private long advancePartNumber (long number, int quantity)
    {
        number = (number * number * number) % WIDTH + quantity; 
        number = (number * number) % WIDTH;
        number = ((number << 1) ^ (number >> 1)) % WIDTH;
        return number;
    }
    
    /**
    Generate the given number of part records, each with part number and
    quantity found in inventory.
    */
    public void generate (int length)
        throws IOException
    {
        out.writeInt (length);
        
        int quantity = 1;
        int lastQuantity = 0;
        long partNumber = 0x00765432L; // arbitrary
        
        while (length-- != 0)
        {
            encodePartID (partNumber);
            out.writeInt ((quantity & Integer.MAX_VALUE) % 10000);
            
            int hold = quantity;
            quantity += lastQuantity;
            lastQuantity = hold;
            
            partNumber = advancePartNumber (partNumber, quantity);
        }
        out.flush ();
    }
    
    /**
    Runs the generator to create a file with the given number
    of part number - quantity pairs.
    */
    public static void main (String[] args)
    {
        boolean huge = args.length != 0 && args[0].equalsIgnoreCase ("huge");
        String filename = "inventory/inventory_" + 
            (huge ? "huge" : "medium") + ".dat";
        
        System.out.print ("Generating " + filename + " ...");
        try ( InventoryGenerator generator = new InventoryGenerator
                (new FileOutputStream (filename)); )
        {
            generator.generate (huge ? 5000000 : 500000);
            System.out.println (" done.");
        }
        catch (Exception ex)
        {
            ex.printStackTrace ();
        }
    }
}

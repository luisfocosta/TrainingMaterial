/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

/**
Converts a DNA/RNA string to a compressed byte array by packing each character
into 2 bits.

@author Will Provost
*/
public class DNA
{
    public static void main (String[] args)
    {
        String codons = (args.length != 0
            ? args[0]
            : "GCCATTGGCACCTAA");
        System.out.println (codons);

        // Encoding: create a compressed array of bytes
        byte[] compressed = new byte[codons.length ()];
        for (int a = 0; a < codons.length (); ++a)
            compressed[a] = (byte) codons.charAt (a);
            
        // Decoding: reconstitute the string from the array:
        String reconstituted = "";
        for (byte b : compressed)
            reconstituted += (char) b;
    
        System.out.println (reconstituted);
    }
}


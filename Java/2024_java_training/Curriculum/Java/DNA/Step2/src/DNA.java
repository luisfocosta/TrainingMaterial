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
        byte[] compressed = new byte[(codons.length () + 3) / 4];
        char[] map = { 'A', 'C', 'G', 'T' };
        for (int a = 0; a < codons.length (); ++a)
        {
            int index = a / 4;
            int rotation = (a % 4) * 2;
            int find = 0;
            while (find < map.length && map[find] != codons.charAt (a))
                ++find;
            if (find == map.length)
            {
                System.out.println ("Couldn't find code letter.");
                System.exit (-1);
            }                
            
            compressed[index] |= find << rotation;
        }
            
        // Decoding: reconstitute the string from the array:
        String reconstituted = "";
        int totalLength = 0;
        for (byte b : compressed)
            for (int r = 0; r < 8 && totalLength < codons.length (); 
                    r += 2, ++totalLength)
                reconstituted += map[(b & (3 << r)) >> r];
    
        System.out.println (reconstituted);
    }
}


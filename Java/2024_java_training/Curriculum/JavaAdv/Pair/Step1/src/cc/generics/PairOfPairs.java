/*
Copyright 2004-2015 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.generics;

import java.util.ArrayList;
import java.util.List;

/**
An application that uses {@link Pair}s.

@author Will Provost
*/
public class PairOfPairs
{
    public static void main (String[] args)
    {
        Pair<String,Integer> score1 = new Pair<String,Integer> ("Will", 60);
        System.out.println ("score1: " + score1);
        
        // This would not compile:
        //score1.a = new Integer (60);
        //score1.b = "Will";
        
        Pair<String,String> sPair = new Pair<String,String> ("This", "That");
        sPair.b = "Will";
        System.out.println ("sPair: " + sPair);
        
        Pair<String[],Integer[]> pairOfArrays = 
            new Pair<String[],Integer[]> 
            ( 
                new String[] { "Will", "Stacey" }, 
                new Integer[] { 60, 40 }
            );
        System.out.println ("pairOfArrays: " + pairOfArrays);
        
        List<Pair<String,Integer>> quantities = new ArrayList<> ();
        quantities.add (new Pair<> ("A", 5));
        System.out.println ("quantities:");
        for (Pair<String,Integer> quantity : quantities)
            System.out.format ("%-12s %4d%n", quantity.a, quantity.b);
    }
}

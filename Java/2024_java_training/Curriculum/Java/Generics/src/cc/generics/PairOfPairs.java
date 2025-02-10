/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.generics;

/**
An application that uses {@link Pair}s.

@author Will Provost
*/
public class PairOfPairs
{
    public static void main (String[] args)
    {
        @SuppressWarnings("unused")
        Pair<String,Integer> score1 =
            new Pair<String,Integer> ("Will", Integer.valueOf(60));

        // This would not compile:
        //score1.a = Integer.valueOf(60);
        //score1.b = "Will";

        Pair<String,String> sPair = new Pair<> ("This", "That");
        sPair.b = "Will";
    }
}

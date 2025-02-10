/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.generics;

import java.util.*;

/**
A stripped-down version of the Scores application that illustrates the use
of the generic {@link Pair} class by keeping scores as pairs of names and
integer values.  This also illustrates the ability to nest generic types
as the subject types of other generics, as we hold a list of pairs.

@author Will Provost
*/
public class Scores
{
    public static void main (String[] args)
    {
        List<Pair<String,Integer>> scores =
            new ArrayList<Pair<String,Integer>> ();
        scores.add (new Pair<String,Integer> ("Will", 60));
        scores.add (new Pair<String,Integer> ("Stacey", 70));

        for (Pair<String,Integer> score : scores)
        {
            System.out.format ("%12s%3d", score.a, score.b);
            System.out.println ();
        }

        System.out.println (scores instanceof ArrayList<?>);

        @SuppressWarnings("unused")
        Pair<String,String> oPair = new Pair<> ("This", "That");
    }
}

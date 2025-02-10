import java.util.*;
import java.io.*;

/**
This class keeps very simple statistics on a collection of scores 
added one at a time.  It updates transient fields to hold the stats
so that they can be returned immediately when queried.

@author Will Provost
*/
// Copyright 2004 William W. Provost and Capstone Courseware, LLC

public class Stats
    implements Serializable
{
    private static final String FILENAME = "Stats.ser";
    
    private Vector<Integer> scores = new Vector<Integer> ();
    private transient int sampleSize = 0;
    private transient double mean = 0.0;

    /**
    Add to the persistent collection of statistics; or, if no arguments 
    are provided, build a starter set by default.
    */
    public static void main (String[] args)
    {
        try
        {
            Stats stats = null;
            if (args.length != 0)
                try ( ObjectInputStream in = new ObjectInputStream
                        (new FileInputStream (FILENAME));)
                {
                    stats = (Stats) in.readObject ();
                    stats.addScore (Integer.parseInt (args[0]));
                }
            else
            {
                stats = new Stats ();
                stats.addScore (100);
                stats.addScore (62);
                stats.addScore (80);
                stats.addScore (93);
                stats.addScore (82);
            }
            
            System.out.print ("Scores:");
            for (int score : stats.getScores ())
                System.out.print (" " + score);
            System.out.println ();
            System.out.println ("Sample size: " + stats.getSampleSize ());
            System.out.println ("Mean score: " + stats.getMean ());
            
            try ( ObjectOutputStream out = new ObjectOutputStream 
                    (new FileOutputStream (FILENAME)); )
            {
                out.writeObject (stats);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace ();
        }
    }
    
    /**
    Call this to add a score to the sample.  The method will
    record the score and also update the cached statistics:
    increment {@link Stats#sampleSize} and recalculate 
    {@link Stats#mean} using a shortcut that doesn't 
    require recalculating the sum.
    */
    public void addScore (int score)
    {
        scores.add (score);
        mean = ((mean * sampleSize) + score) / (++sampleSize);
    }

    /**
    Accessor for the sample size.
    */
    public int getSampleSize ()
    {
        return sampleSize;
    }

    /**
    Accessor for scores collection.
    */
    public Iterable<Integer> getScores ()
    {
        return Collections.unmodifiableList (scores);
    }
    
    /**
    Accessor for the mean.
    */
    public double getMean ()
    {
        return mean;
    }
}

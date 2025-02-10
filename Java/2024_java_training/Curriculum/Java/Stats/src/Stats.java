/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

/**
This class keeps very simple statistics on a collection of scores
added one at a time.  It forgets the scores themselves but keeps a running
sample size and mean score.

@author Will Provost
*/
public class Stats
{
    private int sampleSize = 0;
    private double mean = 0.0;

    /**
    Add to the persistent collection of statistics; or, if no arguments
    are provided, build a starter set by default.
    */
    public static void main (String[] args)
    {
        Stats stats = new Stats ();
        stats.addScores (100, 62, 80, 93, 82);
        stats.report ();

        stats.addScores (99);
        stats.report ();

        stats.addScores ();
        stats.report ();
    }

    /**
    Report the current sample size and mean score.
    */
    public void report ()
    {
        System.out.println ("Sample size: " + sampleSize);
        System.out.println ("Mean score: " + mean);
        System.out.println ();
    }

    /**
    Call this to add a score to the sample.  The method will
    record the score and also update the cached statistics:
    increment {@link Stats#sampleSize} and recalculate
    {@link Stats#mean} using a shortcut that doesn't
    require recalculating the sum.
    */
    public void addScores (int... scores)
    {
        System.out.print ("Adding scores:");

        for (int score : scores)
        {
            System.out.print (" " + score);
            mean = ((mean * sampleSize) + score) / (++sampleSize);
        }

        System.out.println ();
    }

    /**
    Accessor for the sample size.
    */
    public int getSampleSize ()
    {
        return sampleSize;
    }

    /**
    Accessor for the mean.
    */
    public double getMean ()
    {
        return mean;
    }
}

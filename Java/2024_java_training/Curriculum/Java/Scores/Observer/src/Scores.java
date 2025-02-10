/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
This application works through a collection of {@link Record} objects to
calculate grades and statistics.

@author Will Provost
*/
public class Scores
{
    /**
    Data type for record-keeping
    */
    public static class Record
        implements Comparable<Record>
    {
        public String name;
        public int score;
        public String grade;

        public Record (String name, int score)
        {
            this.name = name;
            this.score = score;
        }

        public int compareTo (Record other)
        {
            return Integer.compare (score, other.score);
        }
    }

    /**
    Callback interface for notifications of failing grades.
    */
    public interface GradeObserver
    {
        public void failing (String name, int score);
    }

    private GradeObserver observer;

    /**
    Accessor for the registered grades observer.
    */
    public GradeObserver getObserver ()
    {
        return observer;
    }

    /**
    Mutator for the registered grades observer.
    */
    public void setObserver (GradeObserver observer)
    {
        this.observer = observer;
    }

    /**
    works through the given list of records and fills in the grades.
    Develops some simple statistics on the data set.
    Also notifies any registered {@link #GradeObserver observers}
    if and when it hits failing grades.
    */
    @SuppressWarnings("unused") // We like our loop labels.
    public void analyze (List<Record> scores)
    {

        System.out.println ("Student                 Score   Grade");
        System.out.println ("---------------------   -----   -----");
        for (Record record : scores)
        {
            int score = record.score;
            String grade = null;
            if (score >= 60)
                grade = "" + (char) (68 - (score - 60) / 10);
            else
            {
                grade = "F";
                observer.failing (record.name, record.score);
            }

            record.grade = grade;
            System.out.print (record.name);
            for (int fill = record.name.length (); fill < 24; ++fill)
                System.out.print (" ");
            System.out.println ("  " + score + "      " + grade);
        }
        System.out.println ();

        List<Record> sorted = new ArrayList<> (scores);
        Collections.sort (sorted, Collections.reverseOrder ());

        System.out.println ("Student                 Score   Grade");
        System.out.println ("---------------------   -----   -----");
        for (Record record : sorted)
            System.out.format ("%-24s  %3d      %1s%n",
                record.name, record.score, record.grade);

        Map<String,Integer> grades = new TreeMap<> ();
        int total = 0;
        Student: for (Record record : scores)
        {
            Integer frequency = grades.get (record.grade);
            grades.put (record.grade,
                frequency != null ? frequency + 1 : 1);

            total += record.score;
        }

        System.out.println ("Statistics:");
        for (Map.Entry<String,Integer> entry : grades.entrySet ())
            System.out.format ("  There were %1d %1ss given.%n",
                entry.getValue (), entry.getKey ());
        System.out.println ();

        System.out.println ("The mean score was " +
            ((double) total / scores.size ()));
        System.out.println ();
    }

    /**
    Creates a set of ten student records. Creates an instance of this class
    to analyze the scores. Creates an {@link Intervention} object and lets
    it know where to find the instance of this class, so that the intervention
    object can register itself as an observer on the process.
    Then runs the analysis and calls the
    {@link Intervention#sendLetters sendLetters}
    method on the intervention object.
    */
    public static void main (String[] args)
    {
        List<Record> scores = new ArrayList<> (10);
        scores.add (new Record ("Suzie Q", 76));
        scores.add (new Record ("Peggy Fosnacht", 91));
        scores.add (new Record ("Boy George", 80));
        scores.add (new Record ("Flea", 55));
        scores.add (new Record ("Captain Hook", 71));
        scores.add (new Record ("Nelson Mandela", 98));
        scores.add (new Record ("The Mighty Thor", 70));
        scores.add (new Record ("Oedipa Maas", 88));
        scores.add (new Record ("Uncle Sam", 69));
        scores.add (new Record ("The Tick", 60));

        Scores worker = new Scores ();
        Intervention intervention = new Intervention (worker);

        worker.analyze (scores);
        intervention.sendLetters ();
    }
}

/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

import java.util.ArrayList;
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
    @SuppressWarnings("unused") // We like our loop labels.
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
        
        System.out.println ("Student                 Score   Grade");
        System.out.println ("---------------------   -----   -----");
        for (Record record : scores)
        {
            int score = record.score;
            String grade = null;
            if (score >= 60)
                grade = "" + (char) (68 - (score - 60) / 10);
            else
                grade = "F";

            record.grade = grade;
            System.out.print (record.name);
            for (int fill = record.name.length (); fill < 24; ++fill)
                System.out.print (" ");
            System.out.println ("  " + score + "      " + grade);
        }
        System.out.println ();

        List<Record> sorted = new ArrayList<> (scores.size ());
        Source: for (Record record : scores)
        {
            Destination: for (int s = 0; s < sorted.size (); ++s)
            {
                Record test = sorted.get (s);
                if (record.score > test.score)
                {
                    sorted.add (s, record);
                    continue Source;
                }
            }
            sorted.add (record);
        }

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
}

/**
A simple data structure for use by the main application.
*/
class Record
{
    public String name;
    public int score;
    public String grade;

    public Record (String name, int score)
    {
        this.name = name;
        this.score = score;
    }
}

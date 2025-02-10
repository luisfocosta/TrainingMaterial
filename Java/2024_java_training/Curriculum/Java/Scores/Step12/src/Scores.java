/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
This application works through a collection of {@link Record} objects to
calculate grades and statistics.

@author Will Provost
*/
public class Scores
{
    /**
    Helper method to print a header for a listing of students and results.
    */
    public static void header (String title)
    {
        System.out.println ();
        System.out.println (title);
        System.out.println ();
        System.out.println ("Student                 Score   Grade");
        System.out.println ("---------------------   -----   -----");
    }
    
    /**
    Helper method to report on a single student record.
    */
    public static void report (Record record)
    {
        System.out.format ("%-24s  %3d      %1s%n",
            record.name, record.score, record.grade);
    }

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
        
        scores.stream().forEach(r -> 
            r.grade = r.score == 100
                ? "A"
                : (r.score >= 60
                    ? "" + (char) (68 - (r.score - 60) / 10)
                    : "F"));

        header("All grades:");
        scores.stream().forEach(Scores::report);

        header("Sorted highest-to-lowest:");
        scores.stream().sorted(Collections.reverseOrder ())
            .forEach(Scores::report);

        header("Sorted alphabetically by name:");
        scores.stream().sorted((x,y) -> x.name.compareTo (y.name))
            .forEach(Scores::report);

        System.out.println ();
        System.out.println ("Statistics:");
        System.out.println ();
        for (Map.Entry<String,Long> entry : 
            scores.stream().collect(Collectors.groupingBy
                (r -> r.grade, TreeMap::new, 
                    Collectors.counting())).entrySet ())
            System.out.format ("  There were %1d %1ss given.%n",
                entry.getValue (), entry.getKey ());
        System.out.println ();

        System.out.println ("The mean score was " + 
            scores.stream().collect(Collectors.averagingDouble(r -> r.score)));
        System.out.println ();
    }

    private static class Record
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
}

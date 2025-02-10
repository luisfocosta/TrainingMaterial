/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

import java.util.ArrayList;
import java.util.List;

/**
This application works through a collection of {@link Record} objects to
calculate grades and statistics.

@author Will Provost
*/
public class Scores
{
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
        /* The rest of the code won't compile or run until we refactor
           to use lists and maps:
        for (int student = 0; student < scores.length; ++student)
        {
            int score = scores[student];
            String grade = null;
            if (score >= 60)
                grade = "" + (char) (68 - (score - 60) / 10);
            else
                grade = "F";

            grades[student] = grade;
            System.out.print (names[student]);
            for (int fill = names[student].length (); fill < 24; ++fill)
                System.out.print (" ");
            System.out.println ("  " + score + "      " + grade);
        }
        System.out.println ();
        
        String[] possibleGrades = { "A", "B", "C", "D", "F" };
        int[] frequency = { 0, 0, 0, 0, 0 };
        int total = 0;
        Student: for (int student = 0; student < scores.length; ++student)
        {            
            Grade: for (int g = 0; g < possibleGrades.length; ++g)
                if (grades[student].equals (possibleGrades[g]))
                {
                    ++frequency[g];
                    break Grade;
                }
            
            total += scores[student];
        }
        
        System.out.println ("Statistics:");
        for (int g = 0; g < possibleGrades.length; ++g)
            System.out.println ("  There were " + frequency[g] + " " + 
                possibleGrades[g] + "s given.");
        System.out.println ();
        
        System.out.println ("The mean score was " + 
            ((double) total / scores.length));
        System.out.println ();
        */
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

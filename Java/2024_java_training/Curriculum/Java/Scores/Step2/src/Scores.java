/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

/**
This application works through a collection of {@link Record} objects to
calculate grades and statistics.

@author Will Provost
*/
public class Scores
{
    public static void main (String[] args)
    {
        int[] scores = { 76, 91, 80, 55, 71, 98, 70, 88, 69, 60 };
        
        String[] names =
        {
            "Suzie Q",
            "Peggy Fosnacht",
            "Boy George",
            "Flea",
            "Captain Hook",
            "Nelson Mandela",
            "The Mighty Thor",
            "Oedipa Maas",
            "Uncle Sam",
            "The Tick"
        };
        
        for (String name : names)
            System.out.println (name);
        System.out.println ();
            
        String[] grades = new String[scores.length];
        
        System.out.println ("Student                 Score   Grade");
        System.out.println ("---------------------   -----   -----");
        for (int student = 0; student < scores.length; ++student)
        {
            int score = scores[student];
            String grade = null;
            if (score >= 90)
                grade = "A";
            else if (score >= 80)
                grade = "B";
            else if (score >= 70)
                grade = "C";
            else if (score >= 60)
                grade = "D";
            else
                grade = "F";

            grades[student] = grade;
            System.out.print (names[student]);
            for (int fill = names[student].length (); fill < 24; ++fill)
                System.out.print (" ");
            System.out.println ("  " + score + "      " + grade);
        }
        System.out.println ();
    }
}


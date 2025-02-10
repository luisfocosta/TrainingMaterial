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
    @SuppressWarnings("unused") // We like our loop labels.
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
    }
}


/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

import java.util.ArrayList;
import java.util.List;

/**
Sends letters and schedules counseling appointments for students who are
failing classes.

@author Will Provost
*/
public class Intervention
{
    private List<String> failingGrades = new ArrayList<> ();

    /**
    Our implementation of the necessary callback interface: We just
    accumulate names of failing students in a list for later processing.
    */
    private class Observer
        implements Scores.GradeObserver
    {
        public void failing (String name, int score)
        {
            failingGrades.add (name);
        }
    }

    /**
    Create with a reference to a worker that may send us notifications.
    */
    public Intervention (Scores worker)
    {
        worker.setObserver (new Observer ());
    }

    /**
    Send letters and schedule appointments for all failing students.
    */
    public void sendLetters ()
    {
        if (failingGrades.size () != 0)
            System.out.println ("Failing grades:");

        for (String name : failingGrades)
        {
            System.out.println ("  " + name);
            //TODO send letter to student
            //TODO schedule appointment with counselor
        }
    }
}

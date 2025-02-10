/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars;

/**
This JavaBean captures a data structure of information about a test drive.

@author Will Provost
*/
public class TestDriveResults
{
    private String feedback;
    private double perceivedValue;

    /**
    Build by providing both feedback and perceived value.
    */
    public TestDriveResults (String feedback, double perceivedValue)
    {
        this.feedback = feedback;
        this.perceivedValue = perceivedValue;
    }
    
    /**
    Accessor for the <code>feedback</code> property.
    */
    public String getFeedback ()
    {
        return feedback;
    }
    
    /**
    Mutator for the <code>feedback</code> property -- appends to
    existing feedback.
    */
    public void addFeedback (String newValue)
    {
        feedback += " " + newValue;
    }
    
    /**
    Mutator for the <code>feedback</code> property -- replaces current value.
    */
    public void setFeedback (String newValue)
    {
        feedback = newValue;
    }
    
    /**
    Accessor for the <code>perceivedValue</code> property.
    */
    public double getPerceivedValue ()
    {
        return perceivedValue;
    }
    
    /**
    Mutator for the <code>perceivedValue</code> property.
    */
    public void setPerceivedValue (double newValue)
    {
        perceivedValue = newValue;
    }
}


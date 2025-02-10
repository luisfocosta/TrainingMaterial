/*
Copyright 2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.datetime;

import java.time.LocalDateTime;

/**
Primary data type for the datebook, encapsulating an
event description and date/time.

@author Will Provost
*/
public class Event
{
    private LocalDateTime when;
    private String what;

    /**
    Accessor for the date and time.
    */
    public LocalDateTime getWhen()
    {
        return when;
    }

    /**
    Mutator for the date and time.
    */
    public void setWhen(LocalDateTime when)
    {
        this.when = when;
    }

    /**
    Accessor for the description.
    */
    public String getWhat()
    {
        return what;
    }

    /**
    Mutator for the description.
    */
    public void setWhat(String what)
    {
        this.what = what;
    }
}

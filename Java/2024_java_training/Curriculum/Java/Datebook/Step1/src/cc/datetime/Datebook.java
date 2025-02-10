/*
Copyright 2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.datetime;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
Prototypical datebook that can parse text representations of {@link Event}s
sort them into a schedule.

@author Will Provost
*/
public class Datebook
    extends TreeMap<LocalDate,List<Event>>
{
    /**
    Sort the given stream of {@link Event}s into ourselves
    (since we are a map).
    */
    public static Datebook buildSchedule (Stream<String> serializedEvents)
    {
        return null;
    }
}

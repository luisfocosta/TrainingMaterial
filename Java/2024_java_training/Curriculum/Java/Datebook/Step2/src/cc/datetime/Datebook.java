/*
Copyright 2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
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
    Parse the text representation of an event into an {@link Event} object.
    */
    public static Event parse(String serialized)
    {
        int separator = serialized.indexOf (' ');
        Event result = new Event ();
        result.setWhen (LocalDateTime.parse
            (serialized.substring (0, separator)));
        result.setWhat (serialized.substring (separator + 1));

        return result;
    }

    /**
    Sort the given stream of {@link Event}s into ourselves
    (since we are a map).
    */
    public static Datebook buildSchedule (Stream<String> serializedEvents)
    {
        Datebook datebook = new Datebook ();
        datebook.putAll (serializedEvents.map (Datebook::parse)
            .collect (Collectors.groupingBy
                ((ev) -> ev.getWhen ().toLocalDate ())));

        return datebook;
    }

    /*
    Alternate implementation using traditional collections instead of
    stream processing.
    */
    /*
    public static Datebook buildSchedule (List<String> serializedEvents)
    {
        Datebook datebook = new Datebook ();
        for (String serialized : serializedEvents)
        {
            Event event = parse (serialized);

            LocalDate date = event.getWhen ().toLocalDate ();
            if (!containsKey (date))
                datebook.put (date, new ArrayList<> ());
            datebook.get (date).add (event);
        }
        
        return datebook;
    }
    */
}

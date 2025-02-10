/*
Copyright 2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
Exercising the Stream API.

@author Will Provost
*/
public class Streams
{
    /**
    Referenceable method that inserts hyphens in a phone number.
    */
    public static String padNumber (String number)
    {
        return number.substring (0, 3) + "-" +
               number.substring (3, 6) + "-" +
               number.substring (6);
    }

    /**
    Runs through a series of processes on different data sets to illustrate
    usage of the Stream API.
    */
    public static void main (String[] args)
        throws Exception
    {
        System.out.println ("Names with initials:");

        List<String> names = new ArrayList<> ();
        Collections.addAll (names,
            "James T. Kirk",
            "Sugar Ray Leonard",
            "Leonardo da Vinci",
            "Booker T.",
            "J.K. Rowling",
            "George Washington",
            "Confucius",
            "John Stuart Mill",
            "John Cleese",
            "Foghorn Leghorn");

        String regex = ".*[A-Z]\\..*";

        // Collections API:
        int initials = 0;
        for (String name : names)
            if (name.matches (regex))
                ++initials;

        System.out.println ("Using the Collections API: " + initials);

        // Stream API:
        System.out.println ("Using the Stream API: " +
            names.stream ().filter (n -> n.matches (regex)).count ());

        ///////////////////////////////////////////////////////

        System.out.println ();
        System.out.println ("Attractive phone numbers:");

        long[] numbers =
            {
                1234567890L,
                8008181800L,
                5123437900L,
                8585588555L,
                6176161000L,
                2122223300L,
                1234512345L,
                8772272477L,
                4133145000L
            };

        // Array processing:
        System.out.println ("  Using array processing:");
        for (long number : numbers)
        {
            String asString = Long.toString (number);
            Set<Character> distinct = new TreeSet<> ();
            for (int i = 0; i < asString.length (); ++i)
                distinct.add (asString.charAt (i));

            if (distinct.size () <= 4)
                System.out.println ("    " + padNumber (asString));
        }

        // Stream API:
        System.out.println ("  Using the Stream API:");
        LongStream.of (numbers)
            .mapToObj (String::valueOf)
            .filter (n -> n.chars ().distinct ().count () <= 4)
            //.map (Streams::padNumber)
            .forEach (n -> System.out.println ("    " + n));

        ///////////////////////////////////////////////////////

        System.out.println ();
        System.out.println ("Average response time:");

        final SimpleDateFormat formatter =
            new SimpleDateFormat ("h:m");
        Date request = formatter.parse ("9:00");
        List<Date> responses = new ArrayList<> ();
        Collections.addAll (responses,
            formatter.parse ("10:00"),
            formatter.parse ("10:15"),
            formatter.parse ("14:55"),
            formatter.parse ("12:05"),
            formatter.parse ("17:00"),
            formatter.parse ("9:15"),
            formatter.parse ("9:50"),
            formatter.parse ("15:00"));

        long requestInstant = request.getTime ();
        
        // Collections API:
        long total = 0L;
        for (Date response : responses)
            total += response.getTime () - requestInstant;
        System.out.println ("Using the Collections API: " +
            ((int) total / responses.size () / 1000 / 60) + " minutes.");

        // Stream API:
        System.out.format ("Using the Stream API: %d minutes.%n",
            (int) responses.stream ()
                .mapToLong (r -> r.getTime () - requestInstant)
                .average ().getAsDouble() / 1000 / 60);

        ///////////////////////////////////////////////////////

        System.out.println ();
        System.out.println ("Checksum:");
        final int LIMIT = 60606;

        // For loop:
        byte checksum = 0;
        for (int i = 0; i < LIMIT; ++i)
            checksum += i;
        System.out.println ("Using a for loop: " + checksum);

        // Stream API:
        System.out.println ("Using the Streams API: " +
            IntStream.iterate (0, x -> x + 1).limit (LIMIT)
                .reduce ((x,y) -> (byte) (x + y)).getAsInt ());
        System.out.println ("               ... or: " +
            IntStream.range (0, LIMIT)
                .reduce ((x,y) -> (byte) (x + y)).getAsInt ());
    }
}

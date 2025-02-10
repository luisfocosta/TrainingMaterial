/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
This application demonstrates the algorithms available in
java.util.Collections.

@author Will Provost
*/
public class Algorithms
{
    public static void main (String[] args)
    {
        List<Integer> numbers = new ArrayList<> ();
        Collections.addAll (numbers, 1, 2, 3, 4, 5);
        printList ("Start", numbers);

        Collections.reverse (numbers);
        printList ("Reverse", numbers);

        Collections.shuffle (numbers);
        printList ("Shuffle", numbers);

        Collections.swap (numbers, 0, 4);
        printList ("Swap", numbers);

        System.out.format ("%-16s %d%n", "Minimum:", Collections.min (numbers));
        System.out.format ("%-16s %d%n", "Maximum:", Collections.max (numbers));

        Collections.sort (numbers);
        printList ("Sort", numbers);

        List<Integer> moreNumbers = new LinkedList<> ();
        moreNumbers.addAll (numbers);
        Collections.reverse (moreNumbers);
        numbers.addAll (moreNumbers);
        numbers.addAll (Collections.nCopies (3, 3));
        printList ("Expanded", numbers);

        System.out.format ("%-16s %d%n", "Frequency of 3:",
            Collections.frequency (numbers, 3));

        Collections.replaceAll (numbers, 3, 9);
        printList ("Replaced", numbers);

        System.out.format ("%-16s %d%n", "Frequency of 3:",
            Collections.frequency (numbers, 3));

        Collections.sort (numbers, Collections.reverseOrder ());
        printList ("Descending", numbers);

        System.out.format ("%-16s %d%n", "Position of 4:",
            Collections.binarySearch (numbers, 4,
                Collections.reverseOrder ()));
        System.out.format ("%-16s %d%n", "Position of 4:",
            Collections.binarySearch (numbers, 4));
    }

    public static void printList (String label, List<?> list)
    {
        System.out.format ("%-16s ", label + ":");
        for (Object element : list)
            System.out.format ("%s ", element.toString ());

        System.out.println ();
    }
}

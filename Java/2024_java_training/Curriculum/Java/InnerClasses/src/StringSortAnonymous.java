/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

import java.util.*;

/**
This application demonstrates the use of an inner class as a way of 
passing behavior to an algorithm.

@author Will Provost
*/
public class StringSortAnonymous
{
    public static void main (String[] args)
    {
        List<String> strings = new ArrayList<String> ();
        Collections.addAll (strings, "alpha", "beta", "gamma", "delta", "epsilon");
        printList ("Start", strings);
        
        Collections.sort (strings);
        printList ("Sort", strings);
        
        Collections.sort (strings, new Comparator<String> ()
            {
                public int compare (String one, String two)
                {
                    int length1 = (one == null) ? 0 : one.length ();
                    int length2 = (two == null) ? 0 : two.length ();

                    if (length1 != length2)
                        return length1 < length2 ? -1 : 1;

                    if (length1 == 0)
                        return 0;

                    return one.compareTo (two);
                }
            } );
        printList ("Sort by length", strings);
    }
    
    public static void printList (String label, List<?> list)
    {
        System.out.format ("%-16s ", label + ":");
        for (Object element : list)
            System.out.format ("%s ", element.toString ());
            
        System.out.println ();
    }
}

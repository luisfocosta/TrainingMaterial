/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

/**
Three sorting algorithms -- bubble,
insertion and merge -- are implemented over an array of strings,
which for testing purposes is primed with the names of the 50
US states.  A later version of the application builds much larger
data sets and proves out the performance of each sort algorithm.

@author Will Provost
*/
public class Sort
{
    /**
    Primes and sorts the array three times.
    If the user provides a command-line argument, searches for that string
    in the sorted array.
    */
    public static void main (String[] args)
    {
        String[] names = buildNamesArray ();
        System.out.println ();
        System.out.println ("Bubble sort:");
        printNames (bubbleSort (names));

        names = buildNamesArray ();
        System.out.println ();
        System.out.println ("Insertion sort:");
        printNames (insertionSort (names));

        names = buildNamesArray ();
        System.out.println ();
        System.out.println ("Merge sort:");
        printNames (mergeSort (names));

        if (args.length != 0)
        {
            String fullName = args[0];
            for (int i = 1; i < args.length; ++i)
                fullName += " " + args[i];
                
            int found = indexOf (fullName, names);
            System.out.println ();
            System.out.println (fullName + (found != -1
                ? " is a state."
                : " is not a state."));
        }
    }
    
    /**
    Performs a bubble sort on the passed array.
    */
    @SuppressWarnings("unused") // We like our loop labels.
    public static String[] bubbleSort (String[] source)
    {
        return source;
    }

    /**
    Performs an insertion sort on the passed array.
    */
    @SuppressWarnings("unused") // We like our loop labels.
    public static String[] insertionSort (String[] source)
    {
        return source;
    }

    /**
    Performs a merge sort on the passed array, with the help of the
    recursive method {@link mergeHelper mergeHelper}.
    */
    public static String[] mergeSort (String[] source)
    {
        return source;
    }
    
    public static int indexOf (String candidate, String[] sortedArray)
    {
        return -1;
    }
    
    /**
    Prints the contents of the array as a comma-separated list.
    */
    public static void printNames (String[] names)
    {
        for (int i = 0; i < names.length; ++i)
        {
            if (i != 0)
                System.out.print (", ");
            System.out.print (names[i]);
        }
        System.out.println ();
    }

    /**
    Primes the fifty names into the array.
    */    
    public static String[] buildNamesArray ()
    {
        String[] result = 
        {
            "Alaska",
            "Hawaii",
            "California",
            "Oregon",
            "Washington",
            "Idaho",
            "Nevada",
            "Arizona",
            "Utah",
            "Wyoming",
            "Montana",
            "Colorado",
            "New Mexico",
            "North Dakota",
            "South Dakota",
            "Nebraska",
            "Kansas",
            "Oklahoma",
            "Texas",
            "Minnesota",
            "Iowa",
            "Missouri",
            "Louisiana",
            "Mississippi",
            "Arkansas",
            "Tennessee",
            "Kentucky",
            "Illinois",
            "Wisconsin",
            "Michigan",
            "Indiana",
            "Alabama",
            "Ohio",
            "Pennsylvania",
            "West Virginia",
            "Virginia",
            "North Carolina",
            "South Carolina",
            "Georgia",
            "Florida",
            "Maryland",
            "Delaware",
            "New York",
            "New Jersey",
            "Connecticut",
            "Massachusetts",
            "Vermont",
            "New Hampshire",
            "Rhode Island",
            "Maine"
        };
        
        return result;
    }
}


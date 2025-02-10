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
        String[] names = buildBiggerNamesArray ();
        System.out.println ();
        System.out.println ("Bubble sort:");
        long start = System.currentTimeMillis ();
        bubbleSort (names);
        System.out.println 
            ("Done in " + (System.currentTimeMillis () - start) + " milliseconds.");

        names = buildBiggerNamesArray ();
        System.out.println ();
        System.out.println ("Insertion sort:");
        start = System.currentTimeMillis ();
        insertionSort (names);
        System.out.println 
            ("Done in " + (System.currentTimeMillis () - start) + " milliseconds.");

        names = buildBiggerNamesArray ();
        System.out.println ();
        System.out.println ("Merge sort:");
        start = System.currentTimeMillis ();
        mergeSort (names);
        System.out.println 
            ("Done in " + (System.currentTimeMillis () - start) + " milliseconds.");
    }
    
    /**
    Performs a bubble sort on the passed array.
    */
    @SuppressWarnings("unused") // We like our loop labels.
    public static String[] bubbleSort (String[] source)
    {
        int assignments = 0;
        Pass: for (int i = 1; i < source.length; ++i)
        {
            String temp = "No swaps";
            Swap: for (int j = 0; j < source.length - 1; ++j)
                if (source[j].compareTo (source [j + 1]) > 0)
                {
                    assignments += 3;
                    temp = source[j];
                    source[j] = source[j + 1];
                    source[j + 1] = temp;
                }
            
            if (temp.equals ("No swaps"))
            {
                System.out.println ("Bubble sort took " + i + 
                    " passes and performed " + assignments + " assignments.");
                break Pass;
            }
        }
        
        return source;
    }

    /**
    Performs an insertion sort on the passed array.
    */
    @SuppressWarnings("unused") // We like our loop labels.
    public static String[] insertionSort (String[] source)
    {
        int assignments = 0;
        Candidate: for (int i = 1; i < source.length; ++i)
        {
            String temp = source[i];
            int j = i + 1;
            Insert: while (--j != 0 && temp.compareTo (source[j - 1]) < 0)
            {
                ++assignments;
                source[j] = source[j - 1];
            }
            
            if (j != i)
            {
                ++assignments;
                source[j] = temp;
            }
        }

        System.out.println 
            ("Insertion sort performed " + assignments + " assignments.");
        
        return source;
    }

    /**
    Performs a merge sort on the passed array, with the help of the
    recursive method {@link mergeHelper mergeHelper}.
    */
    public static String[] mergeSort (String[] source)
    {
        return source.length > 1
            ? mergeHelper (source, 0, source.length - 1)
            : source;
    }
    
    /**
    For sequences of two elements, simply tests and swaps.
    For longer sequences, splits in two, sorts those, and merges,
    using a temporary buffer.
    */
    public static String[] mergeHelper (String[] source, int left, int right)
    {
        // Optimize for 2-element segments -- just compareTo and swap
        if (left + 1 == right)
        {
            if (source[left].compareTo (source[right]) > 0)
            {
                String temp = source[right];
                source[right] = source[left];
                source[left] = temp;
            }
            
            return source;
        }
        
        int midpoint = (left + right) / 2;
        
        if (left != midpoint)
            mergeHelper (source, left, midpoint);
        if (midpoint + 1 != right)
            mergeHelper (source, midpoint + 1, right);
            
        String[] merged = new String[right - left + 1];
        int next = 0;
        int read1 = left;
        int read2 = midpoint + 1;
        while (read1 <= midpoint && read2 <= right)
            merged[next++] = (source[read1].compareTo (source[read2]) < 0)
                ? source[read1++]
                : source[read2++];

        while (read1 <= midpoint)
           merged[next++] = source[read1++];

        while (read2 <= right)
           merged[next++] = source[read2++];
           
        System.arraycopy (merged, 0, source, left, right - left + 1);

        return source;
    }

    /**
    Performs a binary search on the array.
    */
    public static int indexOf (String candidate, String[] sortedArray)
    {
        int left = 0; 
        int right = sortedArray.length - 1;
        int midpoint = (left + right) / 2;
        while (!sortedArray[midpoint].equals (candidate))
        {
            if (sortedArray[midpoint].compareTo (candidate) < 0)
                left = midpoint + 1;
            else
                right = midpoint - 1;
                
            if (left > right)
                return -1;
                
            midpoint = (left + right) / 2;
        }
        
        return midpoint;
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

    public static String[] buildBiggerNamesArray ()
    {
        String[] base = buildNamesArray ();
        String[] bigger = new String[base.length * base.length];
        int next = 0;
        for (int i = 0; i < base.length; ++i)
            for (int j = 0; j < base.length; ++j)
                bigger[next++] = base[j] + base[i];
        
        return bigger;
    }
}


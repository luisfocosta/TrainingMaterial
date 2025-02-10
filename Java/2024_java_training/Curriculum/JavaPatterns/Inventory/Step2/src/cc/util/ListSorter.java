/*
Copyright 2005, 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
Provides a merged, sorted iterator over multiple source iterators,
which are expected to provide information in sort order as well.

@author Will Provost
*/
public class ListSorter<E extends Comparable<? super E>>
    implements Iterable<E>
{
    private List<Iterator<? extends E>> sources;
    private Comparator<E> comparator;

    /**
    Provide a list of iterators.  This sorter will use the natural order of
    the comparable type E.
    */
    public ListSorter (List<Iterator<? extends E>> sources)
    {
        this (sources, Comparator.naturalOrder ());
    }
    
    /**
    Provide a list of iterators and an alternative comparator, when the
    natural order is not the one you want.
    */
    public ListSorter 
        (List<Iterator<? extends E>> sources, Comparator<E> comparator)
    {
        this.sources = sources;
        this.comparator = comparator;
    }

    /**
    Return a new instance of {@link #SortedMultiIterator}.
    */
    public Iterator<E> iterator ()
    {
        return new SortedMultiIterator (sources);
    }

    /**
    Iterator that works concurrently down several sources, taking the
    "lowest" element from all the ones currently available, until all
    source lists are exhausted.
    */
    protected class SortedMultiIterator
        implements Iterator<E>
    {
        private List<Iterator<? extends E>> sources;
        private List<E> candidates;
        private int size;

        /**
        Prepare a list of "candidates," one from each source.
        */
        public SortedMultiIterator (List<Iterator<? extends E>> sources)
        {
            this.sources = sources;
            
            size = sources.size ();
            candidates = new ArrayList<> (size);
            for (Iterator<? extends E> source : sources)
                candidates.add (source.hasNext () ? source.next () : null);
        }

        /**
        If we still have candidates, we're still running.
        */
        public boolean hasNext ()
        {
            for (E candidate : candidates)
                if (candidate != null)
                    return true;

            return false;
        }

        /**
        Find the "lowest" candidate in the given sort order.
        Return that, after filling its slot in the candidate list
        from the associated source iterator -- or with null when
        that source is exhausted.
        */
        public E next ()
        {
            if (!hasNext ())
                return null;

            E result = null;
            int winner = -1;
            for (int i = 0; i < size; ++i)
            {
                E candidate = candidates.get (i);
                if (candidate != null && (result == null || 
                    comparator.compare (candidate, result) < 0))
                {
                    result = candidate;
                    winner = i;
                }
            }

            Iterator<? extends E> source = sources.get (winner);
            candidates.set (winner, source.hasNext () ? source.next () : null);
            
            return result;
        }

        /**
        Disallow removal with an UnsupportedOperationException.
        */
        public void remove ()
        {
            throw new UnsupportedOperationException ("Read-only iterator");
        }
    }
}


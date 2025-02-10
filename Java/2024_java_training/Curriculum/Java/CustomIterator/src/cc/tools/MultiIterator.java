/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
A general-purpose custom iterator that will span multiple source collections.
The algorithm is:
<ol>
  <li>On construction, prime a local collection of iterators, each ready 
      to work through one of the source collections.</li>
  <li>Also set a "master iterator" that takes these individual iterators
      one at a time.</li>
  <li>As each iterator is exhausted, move to the next one.</li>
  <li>Done when the current iterator and the master iterator are both
      out of elements.</li>
</ol>

Also, implement Iterable<E> so that we can participate in simple for loops,
the same way a collection could.
*/
public class MultiIterator<E>
    implements Iterator<E>, Iterable<E>
{
    /**
    Create a temporary collection of iterators, one for each of the given
    collections, and set {@link #nextIterator} on that.
    Immediately advance to the first non-empty iterator,
    so that we're ready for calls to {@link hasNext hasNext}
    and {@link next next}.
    */
    public MultiIterator 
        (Collection<Collection<? extends E>> collections)
    {
        ArrayList<Iterator<? extends E>> iterators = 
            new ArrayList<Iterator<? extends E>> (collections.size ());
        for (Collection<? extends E> collection : collections)
            iterators.add (collection.iterator ());
            
        nextIterator = iterators.iterator ();
        if (nextIterator.hasNext ())
            iterator = nextIterator.next ();
    }

    /**
    If the current iterator has another element, just return true.
    Otherwise, advance the master iterator until we find a non-empty
    iterator, and return true; or if there are no more non-empty iterators,
    return false.
    */
    public boolean hasNext ()
    {
        if (iterator != null && iterator.hasNext ())
            return true;
            
        while (nextIterator.hasNext ())
        {
            iterator = nextIterator.next ();
            if (iterator.hasNext ())
                return true;
        }

        return false;
    }
    
    /**
    Call {@link #hasNext hasNext} to advance as necessary, and if it returns
    true then we can simply return <code>iterator.next ()</code>.
    */
    public E next ()
    {
        return hasNext ()
            ? iterator.next ()
            : null;
    }
    
    /**
    Disallow removal with an UnsupportedOperationException.
    */
    public void remove ()
    {
        throw new UnsupportedOperationException ("Read-only iterator");
    }
    
    /**
    Return <code>this</code> so that we can participate in simple
    for loops.
    */
    public Iterator<E> iterator ()
    {
        return this;
    }
    
    /**
    Master iterator over individual iterators.
    */
    Iterator<Iterator<? extends E>> nextIterator;

    /**
    Iterator over current source collection.
    */
    Iterator<? extends E> iterator;
}

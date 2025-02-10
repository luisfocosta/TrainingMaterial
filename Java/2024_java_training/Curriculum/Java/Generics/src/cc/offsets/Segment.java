/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.offsets;

/**
This parameterized class is essentially a pair of {@link Offset Offset<T>}
values; it too uses the flag type T to segregate different segment types.

@author Will Provost
*/
public class Segment<T>
{
    private Offset<T> start;
    private Offset<T> end;

    /**
    Build a segment on two integer values -- works for any flag type.
    */
    public Segment (int start, int end)
    { 
        this (new Offset<T> (start), new Offset<T> (end)); 
    }

    /**
    Build a segment based on two offsets of the same flag type.
    */
    public Segment (Offset<T> start, Offset<T> end)
    {
        this.start = start;
        this.end = end;
    }
    
    /**
    Return the start offset.
    */
    public Offset<T> getStart ()
    {
        return start;
    }
    
    /**
    Return the end offset.
    */
    public Offset<T> getEnd ()
    {
        return end;
    }
    
    /**
    Move the whole segment by some delta value.
    Calls {@link Offset#add Offset<T>.add} on each of start and end values.
    */
    public void slide (Offset<T> delta)
    {
        start = start.add (delta);
        end = end.add (delta);
    }
}

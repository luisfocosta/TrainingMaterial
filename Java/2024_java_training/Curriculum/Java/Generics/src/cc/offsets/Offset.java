/*
Copyright 2004-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.offsets;

/**
This parameterized class represents an integer value that measures the offset
from some starting point in some coordinate system or frame of reference.
This is an unusual application of generics in that "type T" is never really
used by this type, except as a marker or "flag" type as a basis for 
compile-time type checking, so that we can have mutually-exclusive
Offset<A> and Offset<B> and be sure they won't be converted arbitrarily
and potentially misused.

@author Will Provost
*/
public class Offset<T>
{
    private int offset;

    /**
    No matter what the flag type, create with an integer value.
    */
    public Offset (int offset)
    { 
        this.offset = offset; 
    }

    /**
    Get the integer value -- flag type is irrelevant.
    */
    public int getOffset () 
    {
        return offset;
    }
    
    /**
    Set the integer value -- flag type is irrelevant.
    */
    public void setOffset (int newValue) 
    {
        offset = newValue;
    }
    
    /**
    Add two offsets -- implicitly asserts that the passed offset is of
    the same (flag) type as our own.
    */
    public Offset<T> add (Offset<T> other) 
    {
        return new Offset<T> (offset + other.getOffset ());
    }
    
    /**
    Subtract two offsets -- implicitly asserts that the passed offset is of
    the same (flag) type as our own.
    */
    public Offset<T> subtract (Offset<T> other) 
    {
        return new Offset<T> (offset - other.getOffset ());
    }
}

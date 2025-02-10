/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.inherit;

/**
Bottom class in a simple inheritance hierarchy.

@author Will Provost
*/
public class C
    extends B
{
    private int c;

    public C (int a, int b, int c)
    {
        super (a, b);
        this.c = c;
    }
    
    public int getC ()
    {
        return c;
    }
    
    public void setC (int newValue)
    {
        c = newValue;
    }
}

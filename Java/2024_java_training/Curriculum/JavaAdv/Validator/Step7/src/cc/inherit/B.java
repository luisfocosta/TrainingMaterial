/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.inherit;

/**
Middle class in a simple inheritance hierarchy.

@author Will Provost
*/
public class B
    extends A
{
    private int b;

    public B (int a, int b)
    {
        super (a);
        this.b = b;
    }
    
    public int getB ()
    {
        return b;
    }
    
    public void setB (int newValue)
    {
        b = newValue;
    }
}

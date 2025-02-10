/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.inherit;

/**
Top class in a simple inheritance hierarchy.

@author Will Provost
*/
public class A
{
    private int a;

    public A (int a)
    {
        this.a = a;
    }
    
    public int getA ()
    {
        return a;
    }
    
    public void setA (int newValue)
    {
        a = newValue;
    }
}

/*
Copyright 2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.ex;

/**
Test application for the {@link MyResource} class.

@author Will Provost
*/
public class MyCaller
{
    public static void main (String[] args)
    {
        String text = args.length != 0 ? args[0] : "Long enough";

        try ( MyResource res = new MyResource (); )
        {
            res.parse (text);
        }
    }
}


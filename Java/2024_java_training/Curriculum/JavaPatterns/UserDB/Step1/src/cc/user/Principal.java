/*
Copyright 2005, 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.user;

import java.io.Serializable;

/**
Base type for any principal in the secure system.

@author Will Provost
*/
public class Principal
    implements Serializable
{
    private String name;

    /**
    Build a principal with its name, which is immutable.
    */
    protected Principal (String name)
    {
        this.name = name;
    }
    
    /**
    Get the name of this principal.
    */
    public String getName ()
    {
        return name;
    }
}


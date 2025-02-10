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
    private Group parent;

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

    /**
    Get the parent node, if any.
    */
    public Group getParent ()
    {
        return parent;
    }
    
    /**
    Set the parent node.
    */
    protected void setParent (Group parent)
    {
        this.parent = parent;
    }

    /**
    Returns true if any of the given principal names can be found,
    either as our own name or anywhere on the parent vector.
    */
    public boolean isAuthorizedAs (String... roles)
    {
        for (String role : roles)
            if (role.equals (name))
                return true;
        
        if (parent != null)
            return parent.isAuthorizedAs (roles);
        
        return false;
    }
    
    /**
    Base implementation returns this object if the name matches.
    This will be fine for leaf nodes.
    */
    public Principal find (String name)
    {
        return name.equals (this.name) ? this : null;
    }
}


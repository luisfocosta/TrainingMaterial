/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.user;

/**
User in the secure system, with a name and password.

@author Will Provost
*/
public class User
    extends Principal
{
    private String password;    

    /**
    Create with both name and password: name is immutable but password
    can change.
    */
    public User (String name, String password)
    {
        super (name);
        setPassword (password);
    }
    
    /**
    Get the current password for comparison.
    */
    public String getPassword ()
    {
        return password;
    }
    
    /**
    Set a new password.
    */
    public void setPassword (String newValue)
    {
        if (newValue.length () < 8)
            throw new IllegalArgumentException 
                ("Passwords must be at least 8 characters.");
        
        password = newValue;
    }
}


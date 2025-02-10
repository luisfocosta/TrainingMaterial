/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.user;

/**
Authentication test.

@author Will Provost
*/
public class Authorize
{
    private static Group realm = Persistence.loadUserDB ();
    
    /**
    Finds the principal by name; tests to see that it is a {@link User},
    and then compares the passwords.
    */
    public static void findAndAuthorize (String username, String... roles)
    {
        System.out.print ("Testing " + username + " ... ");
        
        //TODO
        /*
        Principal found = realm.find (username);
        if (found != null)
        {
            if (found.isAuthorizedAs (roles))
                System.out.println ("authorized.");
            else
                System.out.println ("found, but not authorized.");
        }
        else
            System.out.println ("not found.");
        */
    }
    
    /**
    Tests a series of usernames and passwords, 
    looking for a variety of results.
    */
    public static void main (String[] args)
    {
        findAndAuthorize ("Administrators", "Administrators");
        findAndAuthorize ("gameshowhost", "Administrators");
        findAndAuthorize ("wprovost", "Administrators");
    }
}


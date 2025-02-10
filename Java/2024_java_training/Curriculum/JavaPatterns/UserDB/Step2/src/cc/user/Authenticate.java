/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.user;

/**
Authentication test.

@author Will Provost
*/
public class Authenticate
{
    private static Group realm = Persistence.loadUserDB ();
    
    /**
    Finds the principal by name; tests to see that it is a {@link User},
    and then compares the passwords.
    */
    public static void findAndAuthenticate (String username, String password)
    {
        System.out.print ("Testing " + username + " ... ");
        
        Principal found = realm.find (username);
        if (found != null)
        {
            if (found instanceof User user)
            {
                if (user.getPassword ().equals (password))
                    System.out.println ("authenticated.");
                else
                    System.out.println ("found, but not authenticated.");
            }
            else
                System.out.println ("found, but not a User.");
        }
        else
            System.out.println ("not found.");
    }
    
    /**
    Tests a series of usernames and passwords, 
    looking for a variety of results.
    */
    public static void main (String[] args)
    {
        findAndAuthenticate ("georgiaf", "razzledoozle");
        findAndAuthenticate ("Administrators", "");
        findAndAuthenticate ("gameshowhost", "comeondown");
        findAndAuthenticate ("gameshowhost", "tsohwohsemag");
    }
}


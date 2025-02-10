/*
Copyright 2005, 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.user;

/**
Test application for the user database model.

@author Will Provost
*/
public class Load
{
    /**
    Recursive method that prints the principal's name, and if it is a
    {@link Group}, calls itself on each member with a larger indent string.
    */
    public static void print (Principal subject, String indent)
    {
        System.out.println (indent + subject.getName ());
        if (subject instanceof Group group)
            for (Principal member : group.getMembers ())
                print (member, indent + "  ");
    }
    
    /**
    Uses {@link Persistence} to create a test database, and calls
    {@link #print print} to produce an indented listing of the whole thing.
    */
    public static void main (String[] args)
    {
        Group root = Persistence.loadUserDB ();
        print (root, "");

        try
        {        
            root.addMember (new User ("extra", "extra123"));
            Persistence.saveUserDB ();
        }
        catch (IllegalArgumentException ex)
        {
        }
        catch (Exception ex)
        {
            ex.printStackTrace ();
        }
    }
}


/*
Copyright 2005, 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
Persistence utility loads, maintains, and saves a static user realm.

@author Will Provost
*/
public class Persistence
{
    private static Group root;

    /**
    Save the current user realm to the file system.
    */
    public static void saveUserDB ()
    {
        try ( ObjectOutputStream out = new ObjectOutputStream
            (new FileOutputStream ("Realm.ser")); )
        {
            out.writeObject (root);
        }
        catch (Exception ex)
        {
            System.out.println ("Couldn't write realm file.");
            ex.printStackTrace ();
        }
    }

    /**
    Tries to load a realm file; if not found, prepares a
    hardcoded user database for testing the domain model.
    */
    public static Group loadUserDB ()
    {
        if (root != null)
            return root;

        File realmFile = new File ("Realm.ser");
        if (realmFile.exists ())
        {
            try ( ObjectInputStream in = new ObjectInputStream
                (new FileInputStream ("Realm.ser")); )
            {
                root = (Group) in.readObject ();
            }
            catch (Exception ex)
            {
            }
        }
        else
        {
            root = new Group ("/");

            Group admin = new Group ("Administrators");
            admin.addMember (new User ("administrator", "ccstudent"));
            admin.addMember (new User ("wprovost", "icicleicicle"));
            root.addMember (admin);

            root.addMember (new User ("gameshowhost", "comeondown"));
            root.addMember (new User ("carsalesman", "steprightup"));
        }

        return root;
    }
}


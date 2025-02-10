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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
Persistence utility loads, maintains, and saves a static user realm.

@author Will Provost
*/
public class Persistence
{
    private static final String FILENAME = "Realm.ser";
    private static final Logger LOG =
        Logger.getLogger (Persistence.class.getName ());

    private static Group root;

    /**
    Helper method to get the realm file at a configured location.
    */
    private static File getRealmFile ()
    {
        String filename =
            System.getProperty ("cc.user.realmFilename", FILENAME);

        // This would more properly be config(), but to make it easy
        // to see the outcome in class we set it to info().
        LOG.info ("Realm filename: " + filename);

        return new File (filename);
    }

    /**
    Save the current user realm to the file system.
    */
    public static void saveUserDB ()
    {
        try ( ObjectOutputStream out = new ObjectOutputStream
            (new FileOutputStream (getRealmFile ())); )
        {
            out.writeObject (root);
        }
        catch (Exception ex)
        {
            LOG.log (Level.WARNING, "Couldn't save realm file.", ex);
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

        File realmFile = getRealmFile ();
        if (realmFile.exists ())
        {
            try ( ObjectInputStream in = new ObjectInputStream
                (new FileInputStream (realmFile)); )
            {
                root = (Group) in.readObject ();
            }
            catch (Exception ex)
            {
                LOG.log (Level.WARNING, "Couldn't load realm file.", ex);
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


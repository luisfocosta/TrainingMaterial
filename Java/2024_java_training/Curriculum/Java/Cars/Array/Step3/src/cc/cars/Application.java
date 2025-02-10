/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars;

/**
This is the command-line application for the car dealership.

@author Will Provost
*/
public class Application
{
    private static boolean shownUsage = false;

    /**
    Helper method to print the correct usage of the application.
    Also sets a flag that can be checked by the caller, so that this
    statement is only printed once.
    */
    private static void usage ()
    {
        System.out.println ("Usage: java cc.cars.Application <cmd> <args>");
        System.out.println ("  Commands and arguments:");
        System.out.println ("    list - lists all cars on the lot");
        System.out.println
        ("    price <VIN> - finds a car by VIN and prints details");
        System.out.println
        ("    price <make> <model> - finds a car by make and model and prints details");
        System.out.println ("    drive <VIN> - test-drive a car");
        System.out.println 
        ("    buy <VIN> - talk to a seller and possibly buy the car");
        System.out.println ("    inventory - print entire inventory");
        
        shownUsage = true;
    }
    
    /**
    Helper method to show usage statement, if not show already,
    and then shut down.
    */
    private static void die ()
    {
        if (shownUsage)
            System.out.println
                ("Unrecognized command, or bad parameters.");
        else
            usage ();
        
        System.exit (-1);
    }

    /**
    Application method parses the command line for commands to list, find,
    and buy cars, and for an inventory command.

    @param args Must provide at least one argument, a command for the
        application to run.  Each of these commands takes a different number
        of successive arguments.  Run the application with no arguments for
        details.
    */
    public static void main (String[] args)
    {
    }
}

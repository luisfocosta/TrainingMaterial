/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars;

import cc.tools.UserInput;

/**
This is the command-line application for the car dealership.

@author Will Provost
*/
public class Application
{
    private static final String LIST_CMD      = "list";
    private static final String FIND_CMD      = "price";
    private static final String DRIVE_CMD     = "drive";
    private static final String BUY_CMD       = "buy";

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
        if (args.length == 0)
        {
            usage ();
            
            System.out.println ("Command?");
            args = UserInput.getString ().split (" +");
        }

        Dealership dealership = new Dealership ();

        if (args[0].equals (LIST_CMD))
        {
            System.out.println (dealership.listCars ());
        }
        else if (args[0].equals (FIND_CMD))
        {
            if (args.length < 2)
                die ();

            Car found = (args.length == 2)
                ? dealership.findCar (args[1])
                : dealership.findCar (args[1], args[2]);
            if (found == null)
                System.out.println ("Car not found.");
            else
            {
                System.out.format ("%s: %s $%,1.2f%s%n", 
                    found.getVIN (), found.getShortName (),
                    found.getStickerPrice (),
                    found instanceof UsedCar ? " -- USED" : "");
            }
        }
        else if (args[0].equals (DRIVE_CMD))
        {
            if (args.length < 2)
                die ();

            Car found = dealership.findCar (args[1]);
            if (found == null)
                System.out.println ("Car not found with VIN = " + args[1]);
            else
            {
                TestDriveResults result = found.testDrive ();
                System.out.println (result.getFeedback ());
                System.out.format ("Seems like this car is worth $%,1.2f.%n", 
                    result.getPerceivedValue ());
            }
        }
        else if (args[0].equals (BUY_CMD))
        {
            if (args.length < 2)
                die ();

            Car found = dealership.findCar (args[1]);
            if (found == null)
                System.out.println ("Car not found with VIN = " + args[1]);
            else
            {
                Seller seller = dealership.sellCar (found);
                System.out.format ("\"I can sell the car for $%,1.2f.\"%n",
                    seller.getAskingPrice ());
                
                String offer = "";
                boolean carSold = false;
                while (!carSold && !(offer = UserInput.getString ())
                        .equalsIgnoreCase ("quit"))
                {
                    double money = Double.parseDouble (offer);
                    if (seller.willSellAt (money))
                    {
                        System.out.println ("\"Sold!\"");
                        carSold = true;
                    }
                    else
                        System.out.format ("\"Nope -- can't do it.\"%n");
                }
            }
        }
        else
            die ();
    }
}

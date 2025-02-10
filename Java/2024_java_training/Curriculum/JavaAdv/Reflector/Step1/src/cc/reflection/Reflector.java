/*
Copyright 2004-2015 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.reflection;

/**
This application performs reflection on a class whose name is passed on
the command line.  It walks the superclasses and lists all the public
methods of the class.  Or, if the user provides a method name as well,
the application will dynamically invoke the method and print the results.

@author Will Provost
*/
public class Reflector
{
    public static void main (String[] args)
    {
        try
        {
            if (args.length == 0)
            {
                System.out.println ("Must provide a class name.");
                System.exit (-1);
            }
            
            String className = args[0];
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace ();
        }
    }
}

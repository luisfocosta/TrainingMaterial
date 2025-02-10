/*
Copyright 2004-2015 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.reflection;

import java.lang.reflect.Method;

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
            
            Class<?> targetClass = Class.forName (className);
            System.out.println ("Class name: " + targetClass.getName ());
            
            Class<?> superClass = targetClass;
            String indent = "  ";
            while (superClass != null)
            {
                superClass = superClass.getSuperclass ();
                if (superClass != null)
                    System.out.println (indent + "Superclass: " + 
                        superClass.getName ());
                indent = indent + "  ";
            }
            
            Method[] methods = targetClass.getMethods ();
            for (int m = 0; m < methods.length; ++m)
                System.out.println ("  Method name: " + 
                    methods[m].getName ());
        }
        catch (Exception ex)
        {
            ex.printStackTrace ();
        }
    }
}

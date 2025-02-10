/*
Copyright 2004-2015 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
This application performs reflection on a class whose name is passed on
the command line.  It walks the superclasses and lists all the public
methods of the class.  Or, if the user provides a method name as well,
the application will dynamically invoke the method and print the results.

@author Will Provost
*/
public class Reflector
{
    public static String fullName (Class<?> target)
    {
        StringBuilder result = new StringBuilder (target.getName ());
        TypeVariable<?>[] typeParams = target.getTypeParameters ();
        if (typeParams.length != 0)
        {
            result.append ('<');
            
            boolean firstParam = true;
            for (TypeVariable<?> param : typeParams)
            {
                if (!firstParam)
                    result.append (',');
                    
                result.append (param.getName ());
                Type[] bounds = param.getBounds ();
                boolean firstBound = true;
                for (Type bound : bounds)
                {
                    String boundName = (bound instanceof Class)
                        ? ((Class<?>) bound).getName ()
                        : "**NYI**";
                        
                    if (!boundName.equals ("java.lang.Object"))
                    {
                        result.append (firstBound ? " extends " : " & ");
                        result.append (boundName);
                        
                        firstBound = false;
                    }
                }
                
                firstParam = false;
            }
            
            result.append ('>');
        }        
        
        return result.toString ();
    }
    
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
            
            String methodName = null;
            if (args.length > 1)
                methodName = args[1];
                
            Class<?> targetClass = Class.forName (className);
            System.out.println ("Class name: " + fullName (targetClass));
            
            Class<?> superClass = targetClass;
            String indent = "  ";
            while (superClass != null)
            {
                superClass = superClass.getSuperclass ();
                if (superClass != null)
                    System.out.println (indent + "Superclass: " + 
                        fullName (superClass));
                indent = indent + "  ";
            }
            
            if (methodName == null)
            {
                Method[] methods = targetClass.getMethods ();
                for (int m = 0; m < methods.length; ++m)
                    System.out.println ("  Method name: " + 
                        methods[m].getName ());
            }
            else
            {
                Class<?>[] params = {};
                Method targetMethod = 
                    targetClass.getMethod (methodName, params);
                Object targetObject = 
                        targetClass.getConstructor(params).newInstance ();
                Object[] arguments = {};

                System.out.println (targetMethod.invoke
                    (targetObject, arguments));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace ();
        }
    }
}

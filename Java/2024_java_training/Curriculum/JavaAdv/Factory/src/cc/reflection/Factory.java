/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.reflection;

import java.util.*;
import java.io.*;

/**
This class illustrates using reflection -- specifically the generic
Class<T> class -- within a parameterized method.

@author Will Provost
*/
public class Factory
{
    /**
    Call this method to read a finite list of objects from a file and
    to return them in a collection of a specified type.
    
    @param source The source filename
    @param desiredType The class object for the type you want as the
      type parameter of the returned collection
    */
    public static <T> Collection<T> readObjects 
            (String source, Class<T> desiredType)
        throws IOException, ClassNotFoundException
    {
        ObjectInputStream in = new ObjectInputStream
            (new FileInputStream (source));
        int size = in.readInt ();
        Collection<T> result = new ArrayList<T> (size);
        while (--size != -1)
            result.add (desiredType.cast (in.readObject ()));
        in.close ();
        
        return result;
    }
    
    /**
    The application method exercises {@link #readObjects readObjects}
    by calling it once on "Strings.ser" with desired type of
    <code>String.class</code>, and again on "Numbers.ser" with a desired
    type of <code>Integer.class</code>.
    */
    public static void main (String[] args)
    {
        try
        {
            System.out.println ("Text is:");
            for (String s : readObjects ("Strings.ser", String.class))
                System.out.print (s + " ");
            System.out.println ();

            int total = 0; 
            for (int i : readObjects ("Numbers.ser", Integer.class))
                total += i;

            System.out.format 
                ("Total number of non-space characters is %d.", total);
        }
        catch (Exception ex)
        {
            ex.printStackTrace ();
        }
    }
}

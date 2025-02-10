/*
Copyright 3003-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.math;

/**
Application provides a simple command-line interface to the Ellipsoid class.

@author Will Provost
*/
public class EllipsoidCLI
{
    public static void main (String[] args)
    {
        if (args.length < 3)
        {
            System.out.println ("Usage: java cc.math.Ellipsoid <a> <b> <c>");
            System.exit (-1);
        }
        
        Ellipsoid bean = new Ellipsoid ();
        bean.setA (Double.parseDouble (args[0]));
        bean.setB (Double.parseDouble (args[1]));
        bean.setC (Double.parseDouble (args[2]));
        
        System.out.println ("Three-dimensional ellipsoid -- properties:");
        System.out.println ("  Semi-axis A: " + bean.getA ());
        System.out.println ("  Semi-axis B: " + bean.getB ());
        System.out.println ("  Semi-axis C: " + bean.getC ());
        System.out.println ("  Volume:      " + bean.getVolume ());
        System.out.println ("  Type:        " + bean.getType ());
        System.out.println ("  Definition:");
        System.out.println ("    " + bean.getDefinition ());
        System.out.println ();
    }
}


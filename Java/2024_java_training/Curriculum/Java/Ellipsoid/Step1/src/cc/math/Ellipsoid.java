/*
Copyright 2002-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.math;

/**
Encapsulates a three-dimensional ellipsoid and provides classification
and computation based on lengths of three semi-axes a, b, and c.
Note that all logic is available through JavaBeans-style properties:
the semi-axes are read/write but the volume, type and description are
read-only.
*/
public class Ellipsoid
{
    private double a = 1;
    private double b = 1;
    private double c = 1;

    /**
    Accessor for property <code>a</code>.
    */
    public double getA ()
    {
        return a;
    }
    
    /**
    Mutator for property <code>a</code>.  Rules out non-positive values.
    */
    public void setA (double newValue)
    {
        a = newValue;
    }
    
    /**
    Accessor for property <code>b</code>.
    */
    public double getB ()
    {
        return b;
    }
    
    /**
    Mutator for property <code>b</code>.  Rules out non-positive values.
    */
    public void setB (double newValue)
    {
        b = newValue;
    }
    
    /**
    Accessor for property <code>c</code>.
    */
    public double getC ()
    {
        return c;
    }
    
    /**
    Mutator for property <code>c</code>.  Rules out non-positive values.
    */
    public void setC (double newValue)
    {
        c = newValue;
    }
    
    /**
    Accessor for property <code>volume</code>, which is 4/3 times pi times
    the product of the three semi-axes.
    */
    public double getVolume ()
    {
        return 4 * Math.PI * a * b * c / 3;
    }
    
    /**
    Accessor for property <code>type</code>:  triaxial if none of
    a, b, and c are equal; sphere if they all are equal; oblate or prolate
    spheroid if any two are equal.
    */
    public String getType ()
    {
        if (a == b && a == c)
            return "Sphere";
            
        if (a != b && a != c && b != c)
            return "Triaxial ellipsoid";
        
        double unique;
        double paired;
        if (a != b)
        {
            if (a == c)
            {
                unique = b;
                paired = a;
            }
            else
            {
                unique = a;
                paired = b;
            }
        }
        else
        {
            unique = c;
            paired = a;
        }
        
        return (unique < paired)
            ? "Oblate spheroid"
            : "Prolate spheroid";
    }
    
    /**
    Accessor for property <code>definition</code>.  Provides a fuller
    description of each ellipsoid type.
    */
    public String getDefinition ()
    {
        String type = getType ();
        
        if (type.equals ("Sphere"))
            return "Where A = B = C, offering perfect rotational symmetry in "
                + "all three dimensions.";
        
        if (type.equals ("Triaxial ellipsoid"))
            return "Where none of A, B and C are equal;  the most general " +
                "classification of an ellipsoid.";
        
        if (type.equals ("Oblate spheroid"))
            return "A \"squashed\" ellipsoid with a single axis of " +
                "symmetric rotation that is shorter than the other two " +
                "axes (which are equal).";
        
        if (type.equals ("Prolate spheroid"))
            return "An \"elongated\" ellipsoid with a single axis of " +
                "symmetric rotation that is longer than the other two " +
                "axes (which are equal).";
            
        return "ERROR -- UNKNOWN TYPE.";
    }
}


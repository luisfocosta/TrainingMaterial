/*
Copyright 1999-2014 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cars;

/**
This JavaBeans-style class encapsulates a Car for the car dealership.

@author Will Provost
*/
public class Car
{
    private String make;
    private String model;
    private int year;
    private String VIN;
    private String color;
    private double stickerPrice;
    private int horsepower;
    private int torque;
    private Handling handling;

    public enum Handling 
    { 
        EXCELLENT (.2), GOOD (.1), FAIR (0), POOR (-.1);
        
        private Handling (double adjustment)
        {
            this.adjustment = adjustment;
        }
        
        public double getAdjustment ()
        {
            return adjustment;
        }
        
        private double adjustment;
    };
    
    /**
    Build a car by providing all of the state, except for sold status,
    which will default to false.
    Cars are immutable objects -- that is, once created they cannot be
    changed, as there are no mutator (set) methods.
    */
    public Car
    (
      String make,
      String model,
      int year,
      String VIN,
      String color,
      double stickerPrice,
      int horsepower,
      int torque,
      Handling handling
    )
    {
        this.make = make;
        this.model = model;
        this.year = year;
        this.VIN = VIN;
        this.color = color;
        this.stickerPrice = stickerPrice;
        this.horsepower = horsepower;
        this.torque = torque;
        this.handling = handling;
    }

    /**
    Accessor for the <code>make</code> property.
    */
    public String getMake ()
    {
        return make;
    }

    /**
    Accessor for the <code>model</code> property.
    */
    public String getModel ()
    {
        return model;
    }

    /**
    Accessor for the <code>year</code> property.
    */
    public int getYear ()
    {
        return year;
    }

    /**
    Accessor for the <code>VIN</code> property.
    */
    public String getVIN ()
    {
        return VIN;
    }

    /**
    Accessor for the <code>color</code> property.
    */
    public String getColor ()
    {
        return color;
    }

    /**
    Accessor for the <code>stickerPrice</code> property.
    */
    public double getStickerPrice ()
    {
        return stickerPrice;
    }

    /**
    Short string representation of the car.
    */
    public String getShortName ()
    {
        return "" + year + " " + make + " " + model + " (" + color + ")";
    }

    /**
    Gives an impression of the car based on a test drive, based first 
    on the sticker price but also affected by peak horsepower, torque, 
    and handling.
    */
    public TestDriveResults testDrive ()
    {
        String feedback = "";
        double factor = 1.0;
        
        if (horsepower > 240)
            factor = 1.1;
        else if (horsepower < 160)
            factor = 0.9;

        if (torque > 300)
            factor *= 1.1;
        else if (torque < 200)
            factor *= 0.9;

        if (factor > 1.15)
            feedback += "Feels very powerful!  ";
        else if (factor > 1.0)
            feedback += "Feels powerful ...  ";
        else if (factor == 1.0)
            feedback += "Power is not bad ...  ";
        else if (factor >= 0.9)
            feedback += "Feels a bit weak.  ";
        else 
            feedback += "Feels very weak!  ";

        feedback += "Handling is " + 
            handling.toString ().toLowerCase () + ".";        
        factor += handling.getAdjustment ();
            
        return new TestDriveResults (feedback, getStickerPrice () * factor);
    }

}


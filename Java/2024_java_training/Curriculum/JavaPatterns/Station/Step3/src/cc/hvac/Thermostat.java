/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.hvac;

/**
MMC class for a thermostat.

@author Will Provost
*/
public class Thermostat
    implements AutoCloseable
{
    public enum Function { HEAT, COOL, IDLE };
    
    private String ID;
    private int heatThreshold;
    private int coolThreshold;
    private int temp;
    private boolean operating = true;
    
    /**
    Create with ID and threshold values to trigger heating and cooling.
    */
    public Thermostat (String ID, int heatThreshold, int coolThreshold)
    {
        System.out.println ("  Thermostat created.");
        
        this.ID = ID;
        this.heatThreshold = heatThreshold;
        this.coolThreshold = coolThreshold;
    }
    
    /**
    Accessor for the device ID.
    */
    public String getID ()
    {
        return ID;
    }
     
    /**
    Accessor for the heating threshold.
    */
    public int getHeatThreshold ()
    {
        return heatThreshold;
    }
    
    /**
    Accessor for the cooling threshold.
    */
    public int getCoolThreshold ()
    {
        return coolThreshold;
    }
    
    /**
    Accessor for the current temperature.
    */
    public int getTemp ()
    {
        return temp;
    }
    
    /**
    Mutator for the current temperature.
    */
    public void setTemp (int temp)
    {
        this.temp = temp;
    }
    
    /**
    Accessor for the current functioning state of the device.
    */
    public Function getFunction ()
    {
        if (!operating)
            throw new IllegalStateException 
                ("This device has been shut down.");
        
        if (temp < heatThreshold)
            return Function.HEAT;
        
        if (temp > coolThreshold)
            return Function.COOL;
        
        return Function.IDLE;
    }

    /**
    Shut down the device.
    */
    @Override
    public void close ()
    {
        System.out.println ("  Thermostat closed.");
        operating = false;
    }
}

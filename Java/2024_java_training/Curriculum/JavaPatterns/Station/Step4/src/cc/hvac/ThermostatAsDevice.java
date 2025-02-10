/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.hvac;

import cc.monitor.Device;
import cc.monitor.Station;
import cc.monitor.Status;

/**
Adapter for the {@link Thermostat} to function as a {@link Device}.

@author Will Provost
*/
public class ThermostatAsDevice
    implements Device
{
    private String ID;
    private int heatThreshold;
    private int coolThreshold;
    
    private Thermostat thermostat;
    private int temp;

    /**
    Create the adapter with the same values as for the thermostat itself.
    These will be used each time we need to re-create the underlying
    MMC object.
    */
    public ThermostatAsDevice 
        (String ID, int heatThreshold, int coolThreshold)
    {
        this.ID = ID;
        this.heatThreshold = heatThreshold;
        this.coolThreshold = coolThreshold;
    }
   
    /**
    Accessor for the device ID.
    */
    public String getID ()
    {
        return ID + " thermostat";
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
    Passes the value along to the underlying device, if currently available.
    */
    public void setTemp (int temp)
    {
        this.temp = temp;
        
        if (thermostat != null)
            thermostat.setTemp (temp);
    }
    
    /**
    Accessor for the operating status. The thermostat is considered to
    be running if currently heating or cooling, idle if not, and stopped
    if not between start and stop lifecycle calls.
    */
    public Status getStatus ()
    {
        if (thermostat == null)
            return Status.STOPPED;
        
        return thermostat.getFunction () == Thermostat.Function.IDLE
            ? Status.IDLE
            : Status.RUNNING;
    }

    /**
    We do nothing on initialization.
    */
    public void init (Station station)
    {
    }

    /**
    On startup, create a new underlying {@link Thermostat} with the
    configured values, and set its temperature.
    */
    public void start ()
    {
        thermostat = new Thermostat (ID, heatThreshold, coolThreshold);
        thermostat.setTemp (temp);
    }

    /**
    On shutdown, close the underlying MMC object, and set to null
    so that we'll know it's not available in other methods.
    */
    public void stop ()
    {
        thermostat.close ();
        thermostat = null;
    }

    /**
    We do nothing on destruction.
    */
    public void destroy ()
    {
    }
}

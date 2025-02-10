/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.monitor;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
Monitor for a discrete physical station on the network.

@author Will Provost
*/
public class Station
{
    private Map<String,Device> devices = new HashMap<> ();
    private boolean initialized;
    private boolean destroyed;
    private boolean online;

    /**
    Add a device monitor to the station.
    This will initialize the device monitor, if the station monitor
    has already been initialized.
    */
    public synchronized void addDevice (Device device)
    {
        devices.put (device.getID (), device);

        if (initialized && !destroyed)
            device.init (this);
    }

    /**
    Remove a device monitor from the station.
    This will destroy the device monitor, if it has been initialized.
    */
    public synchronized void removeDevice (Device device)
    {
        if (initialized && !destroyed)
            device.destroy ();

        devices.remove (device.getID ());
    }

    /**
    Initialize the station monitor, allowing allocation of resources.
    This will initialize all devices.
    */
    public void init ()
    {
        if (destroyed)
            throw new IllegalStateException ("Already destroyed!");

        if (initialized)
            throw new IllegalStateException ("Already initialized!");

        initialized = true;
        synchronized (this)
        {
            for (Device device : devices.values ())
                device.init (this);
        }
    }

    /**
    Destroy the station monitor, allowing resource cleanup.
    This will destroy all device monitors.
    */
    public void destroy ()
    {
        if (destroyed)
            throw new IllegalStateException ("Already destroyed!");

        if (!initialized)
            throw new IllegalStateException ("Not yet initialized!");

        destroyed = true;
        synchronized (this)
        {
            for (Device device : devices.values ())
                device.destroy ();
        }
    }

    /**
    Start station monitoring.
    This will start all device monitors.
    */
    public void start ()
    {
        if (online)
            throw new IllegalStateException ("Already online!");

        online = true;
        synchronized (this)
        {
            for (Device device : devices.values ())
                device.start ();
        }
    }

    /**
    Stop station monitoring.
    This will stop all device monitors.
    */
    public void stop  ()
    {
        if (!online)
            throw new IllegalStateException ("Already offline!");

        online = false;
        synchronized (this)
        {
            for (Device device : devices.values ())
                device.stop ();
        }
    }

    /**
    Returns a stream of devices with warning or failed status.
    */
    public Stream<Device> getDevicesInTrouble ()
    {
        return devices.values ().stream ()
            .filter (d -> d.getStatus ().isError ());
    }

    /**
    Returns the status of the device at this station with the given ID.
    */
    public Status getStatusForDevice (String ID)
    {
        if (!devices.containsKey (ID))
            throw new IllegalArgumentException ("No such device: " + ID);

        return devices.get (ID).getStatus ();
    }
}

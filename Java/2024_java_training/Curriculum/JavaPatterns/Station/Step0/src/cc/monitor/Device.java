/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.monitor;

/**
Monitoring interface for a hardware device at a given physical station.

@author Will Provost
*/
public interface Device
{
    /**
    Get the device ID.
    */
    public String getID ();

    /**
    Get the current status.
    */
    public String getStatus ();

    /**
    Initialize the device monitor, with the option to capture a reference to
    the enclosing station.
    */
    public void init (Station station);

    /**
    Destroy the device monitor.
    */
    public void destroy ();

    /**
    Start the device monitor.
    This is mostly an opportunity to create or resume use
    of expensive resources.
    */
    public void start ();

    /**
    Stop the device monitor.
    This is mostly an opportunity to clean up or suspend use of
    expensive resources.
    */
    public void stop ();
}

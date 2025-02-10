/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.security;

import cc.monitor.Device;
import cc.monitor.Station;
import cc.monitor.Status;

/**
Adapter for the {@link Camera} to function as a {@link Device}.

@author Will Provost
*/
public class CameraAsDevice
    implements Device
{
    private Camera camera;

    /**
    Provide a delegate MMC object.
    */
    public CameraAsDevice (Camera camera)
    {
        this.camera = camera;
    }
   
    /**
    Accessor for the device ID.
    */
    public String getID ()
    {
        return camera.getLocation () + " camera";
    }

    /**
    Accessor for the operating status. The camera is either running or
    stopped, depending on its {@link Camera#isOperating operating} 
    property.
    */
    public Status getStatus ()
    {
        return camera.isOperating () ? Status.RUNNING : Status.STOPPED;
    }

    /**
    We do nothing on initialization.
    */
    public void init (Station station)
    {
    }

    /**
    Pass the start call along to the camera.
    */
    public void start ()
    {
        camera.start ();
    }

    /**
    Pass the stop call along to the camera.
    */
    public void stop ()
    {
        camera.stop ();
    }

    /**
    We do nothing on destruction.
    */
    public void destroy ()
    {
    }
}

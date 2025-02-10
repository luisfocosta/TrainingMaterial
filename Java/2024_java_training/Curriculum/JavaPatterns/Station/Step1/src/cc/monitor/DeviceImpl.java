/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.monitor;

/**
Plain-vanilla implementation of {@link Device}.

@author Will Provost
*/
public class DeviceImpl
    implements Device
{
    private String ID;
    private Status status;
    
    /**
    Create with an ID and a status, both of which are immutable.
    */
    public DeviceImpl (String ID, Status status)
    {
        this.ID = ID;
        this.status = status;
    }
    
    /**
    Accessor for the ID.
    */
    public String getID ()
    {
        return ID;
    }

    /**
    Accessor for the operating status.
    */
    public Status getStatus ()
    {
        return status;
    }

    /**
    Does nothing.
    */
    public void init (Station station)
    {
    }

    /**
    Does nothing.
    */
    public void start ()
    {
    }

    /**
    Does nothing.
    */
    public void stop ()
    {
    }

    /**
    Does nothing.
    */
    public void destroy ()
    {
    }
}

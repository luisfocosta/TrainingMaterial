/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.monitor;

/**
Possible device status indicators.

@author Will Provost
*/
public enum Status
{
    RUNNING,
    IDLE,
    DIAGNOSTIC,
    STOPPED,
    WARNING(true),
    FAILED(true),
    UNKNOWN;
    
    private boolean error;
    
    private Status ()
    {
        this (false);
    }
    
    private Status (boolean error)
    {
        this.error = error;
    }
    
    public boolean isError ()
    {
        return error;
    }
}

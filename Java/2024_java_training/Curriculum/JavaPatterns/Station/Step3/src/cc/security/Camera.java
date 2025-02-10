/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.security;

/**


@author Will Provost
*/
public class Camera
{
    private String location;
    private boolean operating;
    
    /**
    Set up the camera with a location code.
    */
    public Camera (String location)
    {
        this.location = location;
    }
    
    /**
    Put the camera online.
    */
    public void start ()
    {
        System.out.println ("  " + location + " camera started.");
        operating = true;
    }
    
    /**
    Take the camera offline.
    */
    public void stop ()
    {
        System.out.println ("  " + location + " camera stopped.");
        operating = false;
    }
    
    /**
    Accessor for the location property.
    */
    public String getLocation ()
    {
        return location;
    }
    
    /**
    Accessor for the operating flag.
    */
    public boolean isOperating ()
    {
        return operating;
    }
}

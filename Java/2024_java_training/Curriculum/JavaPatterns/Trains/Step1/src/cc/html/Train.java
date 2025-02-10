/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.html;

/**
Record type for trains.

@author Will Provost
*/
public class Train
{
    public Train (String number, String name, String origin,
        String destination, String departure, String platform)
    {
        this.number = number;
        this.name = name;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.platform = platform;
    }
        
    public String number;
    public String name;
    public String origin;
    public String destination;
    public String departure;
    public String platform;
}


/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.monitor;

/**
Test for the {@link Station station monitor class}.

@author Will Provost
*/
public class TestStation
{
    /**
    Tests the lifecycle interactions for a station, using mock devices.
    */
    public static void main (String[] args)
    {
        Station station = new Station ();

        Device device1 = null;
        Device device2 = null;

        station.addDevice (device1);
        station.init ();
        station.addDevice (device2);
        station.start ();
        String status = "Status of " + device1.getID () + " was " +
            device1.getStatus () + ".\nStatus of " + device2.getID () +
            " was " + device2.getStatus () + ".";
        station.stop ();
        station.destroy ();

        station.removeDevice (device1);
        station.removeDevice (device2);
        
        System.out.println ();
        System.out.println (status);
    }
}

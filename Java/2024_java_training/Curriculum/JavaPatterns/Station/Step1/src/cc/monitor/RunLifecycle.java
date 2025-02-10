/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.monitor;

import cc.hvac.Thermostat;
import cc.security.Camera;
import cc.transport.Elevator;
/**
Test for the {@link Station station monitor class}.

@author Will Provost
*/
public class RunLifecycle
{
    /**
    Tests the lifecycle interactions for a station with the given devices.
    */
    public static void exercise (Device... devices)
    {
        Station station = new Station ();

        for (Device device : devices)
            station.addDevice (device);
        System.out.println ("Added " + devices.length + " devices.");
        System.out.println ();

        System.out.println ("Initializing station ...");
        station.init ();
        System.out.println ("Station initialized.");
        System.out.println ();

        System.out.println ("Starting station ...");
        station.start ();
        System.out.println ("Station started.");
        System.out.println ();

        System.out.println ("Device status:");
        for (Device device : devices)
        {
            String ID = device.getID ();
            System.out.format ("  %-24s%-12s%n",
                ID, station.getStatusForDevice (ID).toString ());
        }
        System.out.println ();

        System.out.println ("Stopping station ...");
        station.stop ();
        System.out.println ("Station stopped.");
        System.out.println ();

        System.out.println ("Destroying station ...");
        station.destroy ();
        System.out.println ("Station destroyed.");
        System.out.println ();

        System.out.println ("Device status:");
        for (Device device : devices)
        {
            String ID = device.getID ();
            System.out.format ("  %-24s%-12s%n",
                ID, station.getStatusForDevice (ID).toString ());
        }
        System.out.println ();

        for (Device device : devices)
            station.removeDevice (device);
        System.out.println ("Removed " + devices.length + " devices.");
        System.out.println ();
    }

    /**
    Tests the lifecycle interactions for a station, using a variety
    of adapted devices.
    */
    public static void main (String[] args)
    {
        Camera cam1 = new Camera ("Basement");
        Camera cam2 = new Camera ("Lobby");

        Elevator elevator1 = new Elevator ();
        Elevator elevator2 = new Elevator ();
        Elevator elevator3 = new Elevator ();
        elevator3.call ();

        Thermostat therm1 = new Thermostat ("Lobby", 68, 80);
        therm1.setTemp (71);

        Thermostat therm2 = new Thermostat ("Server room", 50, 70);
        therm2.setTemp (71);

        exercise
            (
 
            );
    }
}

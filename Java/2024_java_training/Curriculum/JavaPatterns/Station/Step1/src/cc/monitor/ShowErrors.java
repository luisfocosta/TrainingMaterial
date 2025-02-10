/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.monitor;

/**
Tests the {@link Station}s status filtering.

@author Will Provost
*/
public class ShowErrors
{
    /**
    Adds a set of {@link DeviceImpl devices} to a {@link Station station},
    and then asks the station for all devices with warning or failed status.
    */
    public static void main (String[] args)
    {
        Station station = new Station ();
        station.addDevice (new DeviceImpl ("Device1", Status.RUNNING));
        station.addDevice (new DeviceImpl ("Device2", Status.WARNING));
        station.addDevice (new DeviceImpl ("Device3", Status.IDLE));
        station.addDevice (new DeviceImpl ("Device4", Status.STOPPED));
        station.addDevice (new DeviceImpl ("Device5", Status.FAILED));
        
        System.out.println ("Devices in trouble:");
        station.getDevicesInTrouble ().forEach 
            (d -> System.out.println (d.getID ()));
    }
}

/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.monitor;

import java.lang.reflect.Proxy;

/**
Test for the {@link Station station monitor class}.

@author Will Provost
*/
public class TestStation
{
    /**
    Builds a dynamic proxy to the {@link Device} interface that delegates
    most calls, but suppresses calls to the start/stop methods.
    */
    public static Device createStartStopSuppressor (Device delegate)
    {
        return (Device) Proxy.newProxyInstance
            (delegate.getClass ().getClassLoader (),
             new Class<?>[] { Device.class },
             (target, method, args) ->
                method.getName ().equals ("start") ||
                        method.getName ().equals ("stop")
                    ? null
                    : method.invoke (delegate, args));
    }

    /**
    Creates a dynamic proxy to the {@link Device} interface that acts
    as a mock device, with the given ID and always in the
    {@link Status#RUNNING} state.
    */
    public static Device createDevice (String ID)
    {
        return (Device) Proxy.newProxyInstance
            (TestStation.class.getClassLoader (),
             new Class<?>[] { Device.class },
             (target, method, args) ->
                {
                    System.out.println
                        ("Called: " + method.getName () + " on " + ID);
                    switch (method.getName ())
                    {
                    case "getID":     return ID;
                    case "getStatus": return Status.RUNNING;
                    default:          return null;
                    }
                });
    }

    /**
    Tests the lifecycle interactions for a station, using mock devices.
    */
    public static void main (String[] args)
    {
        Station station = new Station ();

        Device device1 = createDevice ("Device1");
        Device device2 = createDevice ("Device2");

        station.addDevice (device1);
        station.init ();
        station.addDevice (createStartStopSuppressor (device2));
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

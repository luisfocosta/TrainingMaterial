/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.transport;

import cc.monitor.Device;
import cc.monitor.Station;
import cc.monitor.Status;

/**
Adapter for the {@link Elevator} to function as a {@link Device}.

@author Will Provost
*/
public class ElevatorAsDevice
    implements Device
{
    private int ID;
    private Elevator elevator;

    /**
    Provide the delegate MMC object, and an ID to attach to it.
    */
    public ElevatorAsDevice (Elevator elevator, int ID)
    {
        this.elevator = elevator;
        this.ID = ID;
    }
   
    /**
    Accessor for the assigned ID.
    */
    public String getID ()
    {
        return "Elevator " + ID;
    }

    /**
    Accessor for the operating status. The elevator is considered running
    if currently serving a call, idle if waiting in the lobby, and stopped
    if between init and destroy lifecycle calls.
    */
    public Status getStatus ()
    {
        return elevator.isReady ()
            ? (elevator.isCalled () ? Status.RUNNING : Status.IDLE)
            : Status.STOPPED;
    }

    /**
    On initialization, we need to {@link Elevator#powerUp power up} the
    elevator and {@link Elevator#goToLobby send it to the lobby}.
    From this point it will be considered to be either running or idle.
    */
    public void init (Station station)
    {
        elevator.powerUp ();
        elevator.goToLobby ();
    }

    /**
    We do nothing on startup.
    */
    public void start ()
    {
    }

    /**
    We do nothing on shutdown.
    */
    public void stop ()
    {
    }

    /**
    Power the elevator down.
    */
    public void destroy ()
    {
        elevator.powerDown ();
    }
}

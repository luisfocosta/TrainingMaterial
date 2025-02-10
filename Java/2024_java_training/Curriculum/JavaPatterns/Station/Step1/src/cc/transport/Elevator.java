/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.transport;

/**


@author Will Provost
*/
public class Elevator
{
    private boolean poweredUp;
    private boolean ready;
    private boolean called;
    
    public void powerUp ()
    {
        System.out.println ("  Elevator powered up.");
        poweredUp = true;
    }
    
    public void goToLobby ()
    {
        if (!poweredUp)
            throw new IllegalArgumentException ("Must power up first.");
        
        System.out.println ("  Elevator sent to lobby.");
        ready = true;
    }
    
    public void powerDown ()
    {
        System.out.println ("  Elevator powered down.");
        ready = poweredUp = false;
    }
    
    public void call ()
    {
        called = true;
    }

    /**
    Accessor for the ready flag -- true if powered up and settled
    in the lobby.
    */
    public boolean isReady ()
    {
        return ready;
    }
    
    /**
    Accessor for the called flag -- true if currently moving to a new floor.
    */
    public boolean isCalled ()
    {
        return called;
    }
}

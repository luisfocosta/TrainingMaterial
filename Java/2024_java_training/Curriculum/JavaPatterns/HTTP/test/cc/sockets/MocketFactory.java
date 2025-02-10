/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.sockets;

import java.io.IOException;
import java.net.Socket;

/**
Mock socket factory.

@author Will Provost
*/
public class MocketFactory
    extends SocketFactory
{
    /**
    Create a mock socket and return it.
    */
    @Override
    public Socket createSocket (String host, int port)
        throws IOException
    {
        return new Mocket ();
    }

    /**
    Convenience method to install ourselves as the default socket factory.
    */
    public static void install ()
    {
        System.setProperty
            (FACTORY_CLASS_PROPERTY, MocketFactory.class.getName ());
    }
}

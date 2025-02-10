/*
Copyright 2013-2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.sockets;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
Socket factory that looks to a system property to instantiate and
use either itself or a configured subclass.

@author Will Provost
*/
public class SocketFactory
{
    public static final String FACTORY_CLASS_PROPERTY =
        "cc.sockets.SocketFactory.type";

    private static SocketFactory instance;


    private static final Logger LOG =
        Logger.getLogger (SocketFactory.class.getName ());

    protected SocketFactory ()
    {
    }

    /**
    Create the right kind of socket. The default implementation returns a new
    java.net.Socket based on the host and port. Subclasses can override and
    be {@link #createSocket registered} as the default factory implementation.
    */
    public Socket createSocket (String host, int port)
        throws IOException
    {
        return new Socket (host, port);
    }

    /**
    Checks for the system property <code>cc.sockets.SocketFactory.type</code>, with
    a default value of this class' fully-qualified name. Instantiates the class
    and calls {@link #createSocketAsConfigured(String, int) createSocketAsConfigured}.
    */
    public static SocketFactory getInstance ()
    {
        if (instance != null)
            return instance;

        String factoryClass = System.getProperty
            (FACTORY_CLASS_PROPERTY, SocketFactory.class.getName ());
        try
        {
			Class<?>[] params = {};
            instance = (SocketFactory) Class.forName (factoryClass)
            		.getConstructor(params).newInstance ();
            return instance;
        }
        catch (ReflectiveOperationException ex)
        {
            LOG.log (Level.SEVERE,
                "Couldn't instantiate socket factory class " + factoryClass,
                ex);
        }

        return null;
    }
}

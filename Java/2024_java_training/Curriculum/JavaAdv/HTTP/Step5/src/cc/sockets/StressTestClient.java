/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.sockets;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
Socket client that runs a rapid-fire series of requests to the local server.

@author Will Provost
*/
public class StressTestClient
{
    private static final String[] URLs = { "/index.html", "/logo.gif", "/favicon.ico" };
    private static final int HOW_MANY_REQUESTS = 20;
    private static final int HOW_MANY_THREADS = 5;

    private int port;
    private AtomicInteger whichURL = new AtomicInteger (0);

    /**
    Create a client that makes connections to the local host at port 80.
    */
    public StressTestClient ()
    {
        this (80);
    }

    /**
    Create a client that makes connections to the local host
    at the given port.
    */
    public StressTestClient (int port)
    {
        this.port = port;
    }

    /**
    Connect to the server and retrieve the requested resource.
    */
    public void retrieve ()
    {
        StringBuilder result = new StringBuilder ();
        try
        (
            Socket socket = new Socket ("localhost", port);
            PrintWriter  pw = new PrintWriter (socket.getOutputStream(), true);
            InputStream is = new BufferedInputStream (socket.getInputStream());
        )
        {
            pw.println ("GET " +
                URLs[whichURL.getAndIncrement () % URLs.length] +
                    " HTTP/1.1");
            pw.println ();

            int c = -1;
            while ((c = is.read ()) != -1)
                result.append ((char) c);
        }
        catch (IOException e)
        {
            System.out.println (e.getMessage ());
            e.printStackTrace ();
        }
    }

    /**
    Make a fixed number of requests to the server.
    */
    public void sendManyRequests ()
    {
        for (int r = 0; r < HOW_MANY_REQUESTS; ++r)
            retrieve ();
    }

    /**
    Run a set of threads, each of which will make rotating requests to
    the server.
    */
    public static void main (String[] args)
    {
        StressTestClient client = args.length != 0
            ? new StressTestClient (Integer.parseInt (args[0]))
            : new StressTestClient ();
        for (int t = 0; t < HOW_MANY_THREADS; ++t)
            new Thread (client::sendManyRequests).start ();
    }
}

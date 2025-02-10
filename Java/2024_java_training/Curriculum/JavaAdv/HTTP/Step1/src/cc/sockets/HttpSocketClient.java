/*
Copyright 2004, 2013-2015 Edward Rayl and Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.sockets;

/**
 * HTTP socket client that can request a web page from HttpServer. It can also
 * send a shutdown command to the server.
 *
 * @author Edward Rayl
 * @author Will Provost
 */
@SuppressWarnings("unused")
public class HttpSocketClient
{
    public static int port = 80;

    /**
     * Test from the command line -- get the index.html page and show it.
     */
    public static void main(String[] args)
    {
        String URL = null;
        try
        {
            URL = args.length != 0 ? args[0] : "/index.html";
            if (args.length == 2)
                port = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException nfe)
        {
            System.err.println(nfe);
            System.err.println("Usage: java HttpSocketClient <port>");
        }
        HttpSocketClient client = new HttpSocketClient();
    }

}

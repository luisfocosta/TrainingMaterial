/*
Copyright 2004-2015 Edward Rayl and Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.sockets;

import java.io.File;
import java.net.ServerSocket;
import java.util.logging.Logger;

/**
 * Server using the ServerSocket class. It uses HTTP to communicate with the
 * client. It can handle requests for web pages and files and remote server
 * shutdown. It will allow connections from web browsers.
 * 
 * @author Edward Rayl
 * @author Will Provost
 */
@SuppressWarnings("unused")
public class HttpServer
{
    private static final Logger LOG =
        Logger.getLogger (HttpServer.class.getName ());
        
    private static final String SHUTDOWN = "/SHUTDOWN";
    private boolean shutdown = false;
    private ServerSocket serverSocket = null;
    private static int port = 80;
    private String WEBROOT = 
        System.getProperty("user.dir") + File.separator + "webroot";

    /**
     * @param args Port number that the server will use.
     */
    public static void main(String[] args)
    {
        try
        {
            if (args.length != 0)
                port = Integer.parseInt(args[0]);

            HttpServer server = new HttpServer();
        }
        catch (NumberFormatException nfe)
        {
            System.err.println(nfe);
            System.err.println("Usage: java HttpServer [<port>]");
        }
    }
}

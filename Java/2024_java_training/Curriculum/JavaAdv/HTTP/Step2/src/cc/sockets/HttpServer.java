/*
Copyright 2004-2015 Edward Rayl and Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.sockets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Server using the ServerSocket class. It uses HTTP to communicate with the
 * client. It can handle requests for web pages and files and remote server
 * shutdown. It will allow connections from web browsers.
 * 
 * @author Edward Rayl
 * @author Will Provost
 */
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
     * Initialize the serverSocket object
     */
    private void init()
    {
        try
        {
            serverSocket = new ServerSocket
                (port, 1, InetAddress.getByName("localhost"));
            LOG.info("HttpServer running on port " + port);
        }
        catch (BindException be)
        {
            LOG.severe("Another server is running on port " + port);
            System.exit(1);
        }
        catch (IOException e)
        {
            LOG.log(Level.SEVERE, "Couldn't start HTTP server.", e);
            System.exit(1);
        }
    }

    /**
     * Service the request from the client and send a response
     */
    public void service()
    {
        while (!shutdown)
        {
            try
            (
                Socket socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
            )
            {
                HttpRequest request = new HttpRequest(is);
                HttpResponse response = new HttpResponse(os);

                String path = request.getContextPath();
                LOG.info("Responding to " + path);
                if (path.equalsIgnoreCase(SHUTDOWN))
                    shutdown = true;
                else
                    response.sendResource(WEBROOT, path);
            }
            catch (IOException e)
            {
                LOG.log(Level.WARNING, "Couldn't handle request.", e);
            }
            catch (Exception e)
            {
                LOG.log(Level.SEVERE, "Couldn't handle request.", e);
                break;
            }
        }
    }

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
            server.init();
            server.service();
        }
        catch (NumberFormatException nfe)
        {
            System.err.println(nfe);
            System.err.println("Usage: java HttpServer [<port>]");
        }
    }
}

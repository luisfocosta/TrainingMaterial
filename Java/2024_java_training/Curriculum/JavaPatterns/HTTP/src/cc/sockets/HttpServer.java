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
import java.net.SocketException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
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
    private ServerSocket serverSocket = null;
    private static int port = 80;
    private static int threads = 3; // Intentionally small for testing
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
     * Worker that processes an HTTP request on a given socket and quits.
     */
    public class RequestHandler
        implements Runnable
    {
        private Socket socket;
        
        public RequestHandler (Socket socket)
        {
            this.socket = socket;
        }
        
        public void run ()
        {
            try
            (
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
            )
            {
                HttpRequest request = new HttpRequest(is);
                HttpResponse response = new HttpResponse(os);

                String path = request.getContextPath();
                LOG.info("Responding to " + path + " on thread " +
                    Thread.currentThread ().getId ());
                if (path.equalsIgnoreCase(SHUTDOWN))
                    serverSocket.close ();
                else
                    response.sendResource(WEBROOT, path);
            }
            catch (IOException e)
            {
                LOG.log(Level.WARNING, "Couldn't request.", e);
            }
            catch (Exception e)
            {
                LOG.log(Level.SEVERE, "Couldn't process request.", e);
                System.exit(1);
            }
            finally
            {
                try { socket.close (); } catch (Exception ex) {}
            }
        }
    }
    
    /**
     * Service the request from the client and send a response
     */
    public void service()
    {
        ExecutorService threadPool = Executors.newFixedThreadPool (threads);
        
        while (true)
        {
            try
            {
                threadPool.submit (new RequestHandler (serverSocket.accept()));
            }
            catch (SocketException ex)
            {
                System.out.println ("Shutting down server.");
                break;
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

        threadPool.shutdown ();
    }

    /**
     * @param args Port number that the server will use, and thread pool size.
     */
    public static void main(String[] args)
    {
        try
        {
            if (args.length != 0)
                port = Integer.parseInt(args[0]);
            if (args.length > 1)
                threads = Integer.parseInt(args[1]);

            HttpServer server = new HttpServer();
            server.init();
            server.service();
        }
        catch (NumberFormatException nfe)
        {
            System.err.println(nfe);
            System.err.println("Usage: java HttpServer [<port> [<thread-pool-size>]]");
        }
    }
}

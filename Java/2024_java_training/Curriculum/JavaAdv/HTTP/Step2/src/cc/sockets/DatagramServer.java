/*
Copyright 2004 Edward Rayl.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.sockets;

import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Server using the DatagramSocket class. It uses UDP datagrams to communicate
 * with the client. It can handle date and time requests, echoing server
 * connection information, and remote server shutdown.
 * 
 * @author Edward Rayl
 */
public class DatagramServer
{
    private static final Logger LOG =
        Logger.getLogger (DatagramServer.class.getName ());
        
    private boolean shutdown = false;
    private static int port = 8000;
    private DatagramSocket datagramSocket = null;

    /**
     * Initialize the DatagramSocket
     */
    private void init()
    {
        try
        {
            datagramSocket = new DatagramSocket(port);
            LOG.info("DatagramServer running on port " + port);
        }
        catch (BindException be)
        {
            LOG.severe("Another server is running on port " + port);
            System.exit(1);
        }
        catch (IOException e)
        {
            LOG.log(Level.SEVERE, "Couldn't start datagram server.", e);
            System.exit(1);
        }
    }

    /**
     * Process requests and responses
     */
    public void service()
    {
        while (!shutdown)
        {
            try
            {
                byte[] outData = null;

                byte[] buffer = new byte[4096];
                
                DatagramPacket inPacket = 
                    new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(inPacket);

                outData = DatagramResponse.respond(inPacket);
                if (outData == null)
                {
                    outData = "Server shutdown".getBytes();
                    shutdown = true;
                }

                DatagramPacket outPacket = new DatagramPacket(outData, outData.length,
                                inPacket.getAddress(), inPacket.getPort());
                LOG.info("Responding with: " + new String(outData));
                datagramSocket.send(outPacket);
            }
            catch (IOException e)
            {
                LOG.log(Level.WARNING, "Couldn't process request.", e);
            }
            catch (Exception e)
            {
                LOG.log(Level.SEVERE, "Couldn't process request.", e);
                System.exit(1);
            }
        }

        datagramSocket.close();
    }

    /**
     * @param args Port number
     */
    public static void main(String[] args)
    {
        try
        {
            if (args.length != 0)
                port = Integer.parseInt(args[0]);
            
            DatagramServer server = new DatagramServer();
            server.init();
            server.service();
        }
        catch (NumberFormatException nfe)
        {
            System.err.println(nfe);
            System.err.println("Usage: java DatagramServer <port>");
        }
    }
}

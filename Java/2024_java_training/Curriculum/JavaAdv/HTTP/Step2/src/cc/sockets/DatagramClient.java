/*
Copyright 2004 Edward Rayl.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Datagram client that can request the date, time, or echoing server
 * information from DatagramServer. It can also send a shutdown command to the
 * server.
 * 
 * @author Edward Rayl
 */
public class DatagramClient
{
    private static int port = 8000;
    private DatagramSocket datagramSocket;
    private static String command;

    /**
     * Send the request to the server
     */
    private void request()
    {
        try
        {
            // Create the DatagramSocket
            datagramSocket = new DatagramSocket();

            // Set up the DatagramPacket
            byte[] message = command.getBytes();
            // Create the DatagramPacket
            DatagramPacket outPacket = new DatagramPacket
                (message, message.length,
                    InetAddress.getByName("localhost"), port);

            // Send the request
            datagramSocket.send(outPacket);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Receive the response from the server
     */
    public void reponse()
    {
        try
        {
            // Receive the packet from the server
            byte[] buffer = new byte[4096];
            DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(inPacket);

            System.out.println(new String(inPacket.getData(), 0, inPacket.getLength()));

            // Close the socket
            datagramSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * @param args Port number
     */
    public static void main(String[] args)
    {
        // Get the command
        if (args.length == 1)
        {
            command = (args[0]);
            DatagramClient client = new DatagramClient();
            client.request();
            client.reponse();
        }
        else
        {
            System.err.println("Usage: java DatagramClient <command>");
            System.out.println("   where command is Date, Time, Echo, or Shutdown");
        }
    }
}

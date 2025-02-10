/*
Copyright 2004 Edward Rayl.
All rights reserved by Capstone Coursesware, LLC.
*/

package cc.sockets;

import java.net.DatagramPacket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Server datagram response class encapsulating the response to the client and
 * supporting utility classes.
 * 
 * @author Edward Rayl
 */
public class DatagramResponse
{
    private static final String SHUTDOWN = "SHUTDOWN";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-dd-yyyy");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * @param inPacket
     * @return response to client command
     */
    static synchronized byte[] respond(DatagramPacket inPacket)
    {
        // Retrieve the inPacket into a String
        String inData = (new String
            (inPacket.getData(), 0, inPacket.getLength()));

        String response = null;
        if (inData.equalsIgnoreCase("Date"))
            response = dateFormat.format(new Date());
        else if (inData.equalsIgnoreCase("Time"))
            response = timeFormat.format(new Date());
        else if (inData.equalsIgnoreCase("Echo"))
            response = "Host:\t" + inPacket.getAddress() + "\n" + "Port:\t"
                + inPacket.getPort() + "\n" + "Length:\t"
                + inPacket.getLength() + "\n" + "Data:\t" + inData + "\n";
        else if (!inData.equalsIgnoreCase(SHUTDOWN))
            response = "Unknown command";

        return response == null ? null : response.getBytes();
    }
}

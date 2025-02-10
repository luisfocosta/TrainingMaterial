/*
Copyright 2004, 2013-2015 Edward Rayl and Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.sockets;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * HTTP socket client that can request a web page from HttpServer. It can also
 * send a shutdown command to the server.
 *
 * @author Edward Rayl
 * @author Will Provost
 */
public class HttpSocketClient
    implements Retriever
{
    public static int port = 80;

    /**
     * Connect to the server and retrieve the requested resource.
     */
    public String retrieve(String URL)
    {
        StringBuilder result = new StringBuilder();
        try
        (
            Socket socket = SocketFactory.getInstance ()
                .createSocket ("localhost", port);
            PrintWriter  pw = new PrintWriter(socket.getOutputStream(), true);
            InputStream is = new BufferedInputStream(socket.getInputStream());
        )
        {
            pw.println("GET " + URL + " HTTP/1.1");
            pw.println();

            int c = -1;
            while ((c = is.read()) != -1)
                result.append ((char) c);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return result.toString();
    }

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
        System.out.print(client.retrieve(URL));
    }

}

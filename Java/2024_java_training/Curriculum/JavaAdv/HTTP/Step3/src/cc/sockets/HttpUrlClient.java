/*
Copyright 2004 Edward Rayl.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * HTTP URL connection client that can request a web page from HttpServer or
 * other HTTP servers. It can also send a shutdown command to HttpServer. This
 * client returns information about the server after the web page.
 * 
 * @author Edward Rayl
 */
public class HttpUrlClient
{
    private static String urlString = "http://localhost";

    /**
     * Connect to the server
     */
    public void connect()
    {
        try
        {
            URL url = new URL(urlString);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.connect();

            InputStream is = huc.getInputStream();

            int code = huc.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK)
            {
                // Now read the server's response, and write it standard out
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1)
                    System.out.write(buffer, 0, bytesRead);

                // Display some information about the server
                System.out.println();
                System.out.println("URL information:");
                System.out.println("   Content Type: " + huc.getContentType());
                System.out.println("   Content Encoding: " + huc.getContentEncoding());
                System.out.println("   Content Length: " + huc.getContentLength());
                System.out.println("   Date: " + new Date(huc.getDate()));
                System.out.println("   Last Modified: "
                                + new Date(huc.getLastModified()));
                System.out.println("   Expiration: " + new Date(huc.getExpiration()));
                System.out.println("   Request Method: " + huc.getRequestMethod());
                System.out.println("   Response Message: " + huc.getResponseMessage());
                System.out.println("   Response Code: " + code);
            }
            else
                System.out.println("Unexpected response code: " + code);

            // Close stream and connection
            is.close();
            huc.disconnect();
        }
        catch (MalformedURLException mue) {
            System.out.println("Please use a complete and legal URL");
            System.out.println("\tfor example: http://www.acme.com");
        }
        catch (IOException ioe)
        {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            if (args.length == 1)
                urlString = args[0];
        }
        catch (NumberFormatException nfe)
        {
            System.err.println(nfe);
            System.err.println("Usage: java HttpUrlClient <URL>");
        }
        HttpUrlClient client = new HttpUrlClient();
        client.connect();
    }

}

/*
Copyright 2004, 2011 Edward Rayl and Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.sockets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

/**
 * Server HTTP response class encapsulating the response to the client and
 * supporting utility classes.
 * 
 * @author Edward Rayl
 * @author Will Provost
 */
public class HttpResponse
{

    private static final int BUFFER_SIZE = 4096;
    private OutputStream os;
    private PrintWriter pw;
    private String contentType;

    /**
     * @param os OutputStream for the response object.
     */
    public HttpResponse(OutputStream os)
    {
        this.os = os;
        pw = new PrintWriter(os, true);
    }

    /**
     * @param contentType Sets the content type of the response being sent to
     *            the client.
     */
    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    PrintWriter getWriter()
    {
        return new PrintWriter(os, true);
    }

    /**
     * @param dir Path to the context root of the web server
     * @param path Name of the path and file to return to the client
     * @throws IOException
     */
    public void sendResource(String dir, String path) throws IOException
    {
        File file = new File (dir, path);
        try 
        ( 
            FileInputStream fis = new FileInputStream (file); 
        )
        {
            if (path.endsWith("html"))
                contentType = "text/html";
            else if (path.endsWith("jpeg"))
                contentType = "image/jpeg";
            else if (path.endsWith("gif"))
                contentType = "image/gif";
            else if (path.endsWith("ico"))
                contentType = "image/x-icon";

            sendHttpHeader(HttpURLConnection.HTTP_OK, file.length());

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1)
                os.write(buffer, 0, bytesRead);
            os.flush();
        }
        catch (FileNotFoundException e)
        {
            String error404 = "<html><head><title>Capstone Courseware, LLC</title></head>"
                            + "<body><img src=\"./images/logo.gif\" alt=\"Capstone Courseware, LLC Logo\" />"
                            + "<br /><h1>The Dreaded 404</h1>"
                            + "<br />Cannot find page: <b>" + path
                            + "</b></body></html>";
            contentType = "text/html";
            sendHttpHeader(HttpURLConnection.HTTP_NOT_FOUND, error404.length());
            pw.println(error404);
        }
    }

    /**
     * @param httpType HTTP status code to send to the client
     * @param size Size in bytes of the content to send
     */
    private void sendHttpHeader(int httpType, long size)
    {
        switch (httpType)
        {
            case HttpURLConnection.HTTP_OK:
                pw.println("HTTP/1.x 200 OK");
                break;
            case HttpURLConnection.HTTP_NOT_FOUND:
                pw.println("HTTP/1.x 404 Not Found");
                break;
            default:
                // HttpURLConnection.HTTP_BAD_REQUEST
                pw.println("HTTP/1.x 40 Bad HttpRequest");
                break;

        }
        if (contentType != null)
            pw.println("Content-Type: " + contentType);
        if (size > 0)
            pw.println("Content-length: " + size);
        pw.println();
    }

}

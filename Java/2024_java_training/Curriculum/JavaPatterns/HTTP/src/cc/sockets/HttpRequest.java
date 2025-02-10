/*
Copyright 2004 Edward Rayl.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.sockets;

import java.io.InputStream;
import java.io.IOException;

/**
 * Server HTTP request class encapsulating the request from the client and
 * supporting utility classes.
 * 
 * @author Edward Rayl
 */
public class HttpRequest
{
    private static final int BUFFER_SIZE = 1024;
    StringBuffer request = new StringBuffer(BUFFER_SIZE);
    private InputStream is;
    private String contextPath;

    /**
     * @param input InputStream object from the client
     * @throws IOException
     */
    public HttpRequest(InputStream input) throws IOException
    {
        is = input;
        // Read a set of characters from the socket
        int bytesRead;
        byte[] buffer = new byte[BUFFER_SIZE];
        bytesRead = is.read(buffer);
        for (int i = 0; i < bytesRead; i++)
            request.append((char) buffer[i]);
        // System.out.println(request);
        contextPath = parseContextPath(request);
        if (contextPath != null && contextPath.equalsIgnoreCase("/"))
            contextPath = "/index.html";
    }

    /**
     * @return Returns the portion of the request URI that indicates the context
     *         of the request
     */
    public String getContextPath()
    {
        return contextPath;
    }

    /**
     * @param requestString
     * @return String representing the context path which appears after the HTTP
     *         request type. For instance: GET /index.html HTTP/1.1. The context
     *         path in this example would be /index.html
     */
    private String parseContextPath(StringBuffer requestString)
    {
        // Parse the context path,
        int beginIndex, endIndex;
        beginIndex = requestString.indexOf(" ");
        if (beginIndex != -1)
        {
            endIndex = requestString.indexOf(" ", beginIndex + 1);
            if (endIndex > beginIndex)
                return requestString.substring(beginIndex + 1, endIndex);
        }
        return null;
    }
}

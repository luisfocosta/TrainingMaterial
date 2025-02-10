/*
Copyright 2013-2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.sockets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
Mock implementation of <code>java.net.Socket</code> for purposes of
testing the {@link HttpSocketClient}.

@author Will Provost
*/
public class Mocket
    extends Socket
{
    private ByteArrayOutputStream requestSoFar;
    
    /**
    Provide an input stream based on a string, which in turn is based on
    the content received so far from our mock
    {@link #getOutputStream output stream}.
    */
    @Override
    public InputStream getInputStream ()
    {
        String contentSoFar = new String (requestSoFar.toByteArray ());
        return new ByteArrayInputStream 
            ((contentSoFar + " RESPONSE").getBytes());
    }
    
    /**
    Provide a byte-array stream so that we can capture request content
    as written to the stream.
    */
    @Override
    public OutputStream getOutputStream ()
    {
        return requestSoFar = new ByteArrayOutputStream();
    }
}

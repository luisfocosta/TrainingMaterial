/*
Copyright 2013-2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.sockets;

import java.io.ByteArrayInputStream;

import org.junit.Test;
import static org.junit.Assert.*;

import cc.sockets.HttpRequest;

/**
Unit tests for {@link HttpRequest}.

@author Will Provost
*/
public class HttpRequestTest
{
    @Test
    public void testContextPath()
        throws Exception
    {
        final String PATH = "/ThisPathMatters";

        HttpRequest target = new HttpRequest(new ByteArrayInputStream
            (("GET " + PATH + " HTTP/1.1").getBytes()));
        assertEquals (PATH, target.getContextPath ());
    }
}

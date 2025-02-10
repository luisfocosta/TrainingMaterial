/*
Copyright 2013-2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.sockets;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import cc.sockets.HttpSocketClient;

/**
Unit tests for {@link HttpSocketClient}.

@author Will Provost
*/
public class HttpSocketClientTest
{
    /**
     * Sets our test-environment flag as a system property.
     */
    @BeforeClass
    public static void setUpClass ()
        throws Exception
    {
        MocketFactory.install ();
    }

    /**
     * Tests that the client gets the expected response from a mock socket.
     */
    @Test
    public void testRetrieve()
        throws Exception
    {
        HttpSocketClient client = new HttpSocketClient();
        String response = client.retrieve ("/doesnotmatter");
        assertEquals(" RESPONSE",response);
    }
}

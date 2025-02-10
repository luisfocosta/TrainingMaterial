/*
Copyright 2013-2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.sockets;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
Test suite for our sockets classes.
*/
@SuiteClasses
({ 
    HttpRequestTest.class, 
    HttpSocketClientTest.class
})
@RunWith(Suite.class)
public class TestSuite 
{
}

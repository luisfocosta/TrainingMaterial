/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.sockets;

/**
Socket client that runs a rapid-fire series of requests to the local server.

@author Will Provost
*/
public interface Retriever
{
    /**
    Retrive a text resource from a given location.
    */
    public String retrieve (String URL);
}

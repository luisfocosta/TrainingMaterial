/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.client;

import java.io.*;
import cc.html.*;

/**
Client application that produces a list-oriented HTML view of the data.

@author Will Provost
*/
public class ProduceList
{
    /**
    Instantiates an {@link cc.html.list.ListPage}, an
    {@link cc.html.list.ListSection}, and an
    {@link cc.html.list.ListItem}.
    */
    public static void main (String[] args)
    {
        Data data = new Data ();

        Factory factory = new cc.html.list.ListFactory ();
        Block pageProducer = factory.createPageProducer ();
        Block bodyProducer = factory.createSectionProducer ();
        Item itemProducer = factory.createItemProducer ();
        
        try ( PrintWriter out = new PrintWriter (new FileWriter ("Page.html")); )
        {
            pageProducer.writeHeader (out, data);
                bodyProducer.writeHeader (out, data);
                    for (Train train : data)
                        itemProducer.writeItem (out, train);
                bodyProducer.writeFooter (out, data);
            pageProducer.writeFooter (out, data);
        }
        catch (Exception ex)
        {
            ex.printStackTrace ();
        }
    }
}


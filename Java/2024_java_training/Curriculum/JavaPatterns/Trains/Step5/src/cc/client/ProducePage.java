/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.client;

import java.io.*;
import cc.html.*;

/**
Client application that produces an HTML view of the data, relying on 
{@link cc.html.Factory} entirely for its choice of presentation styles.

@author Will Provost
*/
public class ProducePage
{
    /**
    Calls {@link cc.html.Factory#createFactory Factory.createFactory}
    to choose a factory, and then uses only that factory to create its
    page, section, and item HTML producers.
    */
    public static void main (String[] args)
    {
        Data data = new Data ();

        Factory factory = Factory.createFactory ();
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


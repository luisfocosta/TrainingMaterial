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

        Factory factory = cc.html.Factory.createFactory ();
        Page page = factory.createPage ();
        Section body = page.createSection ();
        Item item = body.createItem ();
        
        try
        {
            PrintWriter out = new PrintWriter
                (new FileWriter ("Page.html"));
            
            page.writeHeader (out, data);
                body.writeHeader (out, data);
                    for (Train train : data)
                        item.writeItem (out, train);
                body.writeFooter (out, data);
            page.writeFooter (out, data);
            
            out.close ();
        }
        catch (Exception ex)
        {
            ex.printStackTrace ();
        }
    }
}


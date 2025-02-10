/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.client;

import java.io.*;
import cc.html.*;

/**
Client application that produces an invalid HTML page by mixing list-oriented
and table-oriented production components.

@author Will Provost
*/
public class MakeAMess
{
    /**
    Instantiates an {@link cc.html.list.ListPage} and an
    {@link cc.html.list.ListItem}, but mixes them with an
    {@link cc.html.table.TableSection}, giving bad results.
    */
    public static void main (String[] args)
    {
        Data data = new Data ();

        Block pageProducer = new cc.html.list.ListPage ();
        Block bodyProducer = new cc.html.table.TableSection ();
        Item itemProducer = new cc.html.list.ListItem ();
        
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


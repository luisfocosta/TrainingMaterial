/*
Copyright 2005 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.client;

import java.io.*;
import cc.html.*;

/**
Client application that produces a table-oriented HTML view of the data.

@author Will Provost
*/
public class ProduceTable
{
    /**
    Instantiates an {@link cc.html.table.TablePage}, an
    {@link cc.html.table.TableSection}, and an
    {@link cc.html.table.TableItem}.
    */
    public static void main (String[] args)
    {
        Data data = new Data ();

        Factory factory = new cc.html.table.TableFactory ();
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


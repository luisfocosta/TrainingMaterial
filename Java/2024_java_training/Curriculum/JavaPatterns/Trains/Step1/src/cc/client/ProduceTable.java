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

        Block pageProducer = new cc.html.table.TablePage ();
        Block bodyProducer = new cc.html.table.TableSection ();
        Item itemProducer = new cc.html.table.TableItem ();
        
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


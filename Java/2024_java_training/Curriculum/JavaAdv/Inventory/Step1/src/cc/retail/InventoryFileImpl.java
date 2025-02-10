/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.retail;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
Inventory reader based on a single (possibly very large) file.

@author Will Provost
*/
public class InventoryFileImpl
    implements Inventory
{
    private static final Logger LOG =
        Logger.getLogger (InventoryFileImpl.class.getName ());

    private String filename;

    /**
    Use default filename.
    */
    public InventoryFileImpl ()
    {
        this ("inventory/inventory.dat");
    }

    /**
    Provide a specific inventory filename.
    */
    public InventoryFileImpl (String filename)
    {
        this.filename = filename;
    }

    /**
    Data record that pairs part number and quantity.
    */
    protected class Record
    {
        public String partNumber;
        public int quantity;

        public Record (String partNumber, int quantity)
        {
            this.partNumber = partNumber;
            this.quantity = quantity;
        }
    }

    /**
    Returns a supply of part number / quantity records,
    as loaded from the top of the file on each new call.
    */
    protected class InventoryData
        implements Iterator<Record>, Iterable<Record>, AutoCloseable
    {
        private DataInputStream in;
        private int size;

        public InventoryData ()
        {
            try
            {
                in = new DataInputStream (new FileInputStream (filename));
                size = in.readInt ();
            }
            catch (IOException ex)
            {
                LOG.log (Level.SEVERE,
                    "Couldn't open or read inventory.", ex);

                try { in.close (); } catch (Exception ex2) {}
                in = null;

                size = 0;
            }
        }

        public Iterator<Record> iterator ()
        {
            return this;
        }

        public boolean hasNext ()
        {
            return size > 0;
        }

        public Record next ()
        {
            try
            {
                StringBuffer buffer = new StringBuffer ();
                for (int c = 0; c < 6; ++c)
                    buffer.append ((char) in.readByte ());

                int quantity = in.readInt ();

                --size;
                return new Record (buffer.toString (), quantity);
            }
            catch (IOException ex)
            {
                LOG.log (Level.SEVERE, "Couldn't read item.", ex);

                try { in.close (); } catch (Exception ex2) {}
                in = null;

                size = 0;
            }

            return null;
        }

        public void close ()
        {
            try
            {
                in.close ();
            }
            catch (Exception ex)
            {
                LOG.log (Level.WARNING,
                    "Couldn't close inventory file.", ex);
            }
        }
    }

    /**
    Return the quantity in inventory for the given part number.
    */
    public int getQuantity (String partNumber)
    {
        try ( InventoryData inventory = new InventoryData (); )
        {
            Optional<Record> found = StreamSupport
                .stream (inventory.spliterator (), false)
                .filter ( r -> r.partNumber.equals (partNumber))
                .findFirst ();

            if (!found.isPresent ())
                throw new IllegalArgumentException
                    ("No such part: " + partNumber);

            return found.get ().quantity;
        }
    }

    /**
    Batch version of {@link #getQuantity(String) getQuantity}.
    */
    public List<Integer> getQuantities (List<String> partNumbers)
    {
        List<Integer> results = new ArrayList<> ();
        for (String partNumber : partNumbers)
            try
            {
                results.add (getQuantity (partNumber));
            }
            catch (IllegalArgumentException ex)
            {
                LOG.warning ("Couldn't find part number: " + partNumber);
            }

        return results;
    }

    /**
    Get a list of all part numbers with quantities below the
    given threshold.
    */
    public List<String> getPartsInShortSupply (int min)
    {
        try ( InventoryData inventory = new InventoryData (); )
        {
            return (List<String>)
                StreamSupport.stream (inventory.spliterator (), false)
                    .filter (r -> r.quantity < min)
                    .map (r -> r.partNumber)
                    .collect (Collectors.toCollection (ArrayList::new));
        }
    }
}

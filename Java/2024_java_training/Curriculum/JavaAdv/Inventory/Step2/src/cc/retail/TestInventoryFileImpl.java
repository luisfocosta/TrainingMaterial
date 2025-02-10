/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.retail;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import cc.cache.Cache;

/**
Test for the file-based inventory.

@author Will Provost
*/
public class TestInventoryFileImpl
{
    /**
    Try to look up a series of part numbers, and show quantities for each.
    */
    public static void main (String[] args)
    {
        int index = 0;
        if (args.length != 0)
            index = args[0].equalsIgnoreCase ("medium") ? 1 : 2;
        
        final String[] filenames = { "small", "medium", "huge" };
        final String[][] partNumbers =
          {
            { "SFLACC", "CPREMY", "SFLACC", "OTBASD" },
            { "SYQWTH", "CUCNDE", "SYQWTH", "ATPOOX", "YROINP", "CUCNDE" },
            { "KNBECL", "MLJFKF", "KNBECL", "EBFVEF" }
          };

        Inventory fileInventory = new InventoryFileImpl
            ("inventory/inventory_" + filenames[index] + ".dat");
        InvocationHandler cache = new Cache<Inventory>
            (fileInventory, "getQuantity", 0);
        Inventory inventory = (Inventory) Proxy.newProxyInstance
            (TestInventoryFileImpl.class.getClassLoader (),
             new Class<?>[] { Inventory.class }, cache);

        System.out.println ("Part    Quantity");
        for (String partNumber : partNumbers[index])
            System.out.format ("%s  %8d%n", partNumber,
                inventory.getQuantity (partNumber));
    }
}

/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.retail;

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

        Inventory inventory = new InventoryFileImpl
            ("inventory/inventory_" + filenames[index] + ".dat");

        System.out.println ("Part    Quantity");
        for (String partNumber : partNumbers[index])
            System.out.format ("%s  %8d%n", partNumber,
                inventory.getQuantity (partNumber));
    }
}
